package gr.manousos.DAO.Hibernate;

import static org.hibernate.criterion.Restrictions.eq;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import gr.manousos.DAO.TaxpayerDAO;
import gr.manousos.model.Taxpayer;

public class TaxpayerHibernate extends GenericDAOImpl<Taxpayer, Serializable>
		implements TaxpayerDAO {

	private static Log log = LogFactory.getLog(TaxpayerHibernate.class);

	@Override
	public Taxpayer findById(Serializable id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Taxpayer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Taxpayer> findByExample(Taxpayer exampleInstance,
			String... excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Taxpayer makePersistent(Taxpayer entity) {
		try {
			getSession().beginTransaction();
			// getSession().save(entity);
			super.makePersistent(entity);
			getSession().getTransaction().commit();
		} catch (Exception ex) {
			log.error("Taxpayer makePersistent error ", ex);
			getSession().getTransaction().rollback();
		} finally {
			if (getSession().isOpen())
				getSession().close();
		}

		return entity;
	}

	@Override
	public void makeTransient(Taxpayer entity) {
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
	public String getUserIngoById(String strId) {
		String s = "";
		getSession().beginTransaction();
		/*
		 * 4 different ways!! Criteria crit =
		 * getSession().createCriteria(getPersistentClass()); crit.add(eq("ID",
		 * strId)); UserBean user1 = (UserBean) crit.uniqueResult();
		 * System.out.println("user1 obj" + user1.getID()); UserBean user2 =
		 * (UserBean) getSession().get(UserBean.class, strId); //HIT'S DB
		 * System.out.println("user2 obj" + user2.getID()); UserBean user3 =
		 * (UserBean) getSession().load(UserBean.class, strId);
		 * System.out.println("user3 obj" + user3.getID());
		 */
		getSession().beginTransaction();
		Query query = getSession().createQuery(
				"from UserBean use where use.ID =:ID").setString("ID", strId);
		// getSession().getTransaction().commit();
		List<?> result = query.list();
		Iterator<?> it = result.iterator();
		while (it.hasNext()) {
			Taxpayer user = (Taxpayer) it.next();
			// s = String
			// .format("USER ID : %s , ADDRESS  : %s , DATE OF BIRTH  : %s  , CONTACT NO  :  %s",
			// user.getID(), user.getAddress(), user.getDOB()
			// .toGMTString(), user.getContactNo());
		}

		getSession().close();

		if (!s.equals("")) {
			return s;
		} else {
			return "No Data Found";
		}

	}

	@Override
	public void addTaxpayer(Taxpayer user) {

		try {
			getSession().beginTransaction();
			getSession().save(user);
			getSession().getTransaction().commit();
		} catch (Exception ex) {
			log.error("addTaxpayer Error= " + ex.toString());
			getSession().getTransaction().rollback();
		} finally {
			if (getSession().isOpen())
				getSession().close();
		}
	}

	@Override
	public Taxpayer getTaxpayerByID(int id) {
		Taxpayer taxPayer = null;
		// Session mySess = getSession();
		try {
			getSession().beginTransaction();
			// mySess.beginTransaction();
			taxPayer = (Taxpayer) super.findById(id, true);
			// //
			// getSession().get(Taxpayer.class,
			// id);
			// getSession().load(Taxpayer.class,id);
			getSession().getTransaction().commit();
			// mySess.getTransaction().commit();
		} catch (Exception ex) {
			log.error("getTaxpayerByID Error= " + ex.toString());
			getSession().getTransaction().rollback();
		} /*
		 * finally { getSession().close(); }
		 */
		//getSession().update(taxPayer);
		return taxPayer;
	}
}
