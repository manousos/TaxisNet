package gr.manousos.DAO.Hibernate;

import static org.hibernate.criterion.Restrictions.eq;

import java.io.Serializable;

import gr.manousos.DAO.UserDAO;
import gr.manousos.model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;

public class UserHibernate extends GenericDAOImpl<User, Serializable>
		implements UserDAO {
	
	private static Log log = LogFactory.getLog(UserHibernate.class);
	
	@Override
	public User getUserByUserName(String username) {
		try {
			getSession().beginTransaction();

			Criteria crit = getSession().createCriteria(User.class);
			crit.add(eq("userName", username));
			User user = (User) crit.uniqueResult();

			getSession().getTransaction().commit();

			return user;
		} catch (Exception e) {
			log.error("getUserByUserName Error= " + e.toString());
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Boolean Login(String username, String password) {
		User user = null;
		try {
			getSession().beginTransaction();

			Criteria crit = getSession().createCriteria(User.class);
			crit.add(eq("userName", username));
			crit.add(eq("password", password));
			crit.setLockMode(LockMode.NONE);
			user = (User) crit.uniqueResult();

			getSession().getTransaction().commit();
		} catch (Exception e) {
			log.error("Login Error= " + e.toString());
			getSession().getTransaction().rollback();
		}
		if (user != null)
			return true;

		return false;
	}
}
