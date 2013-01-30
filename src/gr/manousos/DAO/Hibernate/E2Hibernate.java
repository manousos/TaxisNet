/**
 * 
 */
package gr.manousos.DAO.Hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Transaction;

import gr.manousos.DAO.E2DAO;
import gr.manousos.model.*;

/**
 * @author manousos
 * 
 */
public class E2Hibernate extends GenericDAOImpl<E2, Serializable> implements
		E2DAO {

	private static Log log = LogFactory.getLog(TaxpayerHibernate.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.GenericDAO#findById(java.io.Serializable, boolean)
	 */
	@Override
	public E2 findById(Serializable id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.GenericDAO#findAll()
	 */
	@Override
	public List<E2> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.GenericDAO#findByExample(java.lang.Object,
	 * java.lang.String[])
	 */
	@Override
	public List<E2> findByExample(E2 exampleInstance, String... excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.GenericDAO#makePersistent(java.lang.Object)
	 */
	@Override
	public E2 makePersistent(E2 entity) {
		try {
			getSession().beginTransaction();
			super.makePersistent(entity);
			this.submitEstates(entity);
			getSession().getTransaction().commit();
		} catch (Exception ex) {
			log.error("E2 makePersistent error. " + ex.getMessage(), ex);
			getSession().getTransaction().rollback();
		} finally {
			if (getSession().isOpen())
				getSession().close();
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.GenericDAO#makeTransient(java.lang.Object)
	 */
	@Override
	public void makeTransient(E2 entity) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.GenericDAO#flush()
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.GenericDAO#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gr.manousos.DAO.E2DAO#submitE2(gr.manousos.model.E2)
	 */
	@Override
	public void submitEstates(E2 e2Object) {
		try {
			for (E2estate estate : e2Object.getE2estates()) {
				// just for avoid MarshalException on rest services!
				estate.setE2(e2Object);
				getSession().saveOrUpdate(estate);
				for (E2coOwner coOwner : estate.getE2coOwners()) {
					/*
					 * Just to avoid this:
					 * com.sun.jersey.api.client.ClientHandlerException:
					 * javax.ws.rs.WebApplicationException:
					 * javax.xml.bind.MarshalException - with linked exception:
					 * [com.sun.istack.SAXException2: A cycle is detected in the
					 * object graph. This will cause infinitely deep XML:
					 * gr.manousos.model.E2estate@2f6b904d ->
					 * gr.manousos.model.E2coOwner@8135daf ->
					 * gr.manousos.model.E2estate@2f6b904d]
					 */
					coOwner.setE2estate(estate);
					getSession().saveOrUpdate(coOwner);
				}
			}

			for (E2otherEstate othEstates : e2Object.getE2otherEstates()) {
				// just for avoid MarshalException on rest services!
				othEstates.setE2(e2Object);
				getSession().saveOrUpdate(othEstates);
			}
		} catch (Exception ex) {
			log.error("E2 submit childs error. " + ex.getMessage(), ex);
			getSession().getTransaction().rollback();
		}
	}

	@Override
	public E2 getE2ById(E2Id id) {
		E2 e2Obj = null;

		Transaction tx = getSession().beginTransaction();
		e2Obj = (E2) super.findById(id, false);
		tx.commit();

		return e2Obj;
	}

}
