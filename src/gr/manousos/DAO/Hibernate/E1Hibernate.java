package gr.manousos.DAO.Hibernate;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gr.manousos.DAO.E1DAO;
import gr.manousos.model.E1;
import gr.manousos.model.E1Id;

public class E1Hibernate extends GenericDAOImpl<E1, Serializable> implements
		E1DAO {

	private static Log log = LogFactory.getLog(TaxpayerHibernate.class);

	@Override
	public E1 findById(Serializable id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E1> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E1> findByExample(E1 exampleInstance, String... excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E1 makePersistent(E1 entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeTransient(E1 entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public int submitE1(E1 e1) {
		try {
			getSession().beginTransaction();

			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			log.error("E1 Hibernate Submit error ", e);
		}
		return 0;
	}

	@Override
	public E1 getE1ById(E1Id id) {
		// TODO Auto-generated method stub
		return null;
	}

}
