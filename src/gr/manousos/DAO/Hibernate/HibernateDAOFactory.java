package gr.manousos.DAO.Hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.DAO.E2DAO;
import gr.manousos.DAO.TaxpayerDAO;
import gr.manousos.DAO.UserDAO;
import gr.manousos.model.E2;

public class HibernateDAOFactory extends DAOFactory {

	private static Log log = LogFactory.getLog(HibernateDAOFactory.class);

	private GenericDAOImpl instantiateDAO(Class daoClass) {
		try {
			log.debug("Instantiating DAO: " + daoClass);
			return (GenericDAOImpl) daoClass.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException("Can not instantiate DAO: " + daoClass,
					ex);
		}
	}

	@Override
	public TaxpayerDAO getTaxpayerDAO() {
		return (TaxpayerDAO) instantiateDAO(TaxpayerHibernate.class);

	}

	@Override
	public E2DAO getE2DAO() {
		return (E2DAO) instantiateDAO(E2Hibernate.class);
	}

	@Override
	public UserDAO getUserDAO() {
		return (UserDAO) instantiateDAO(UserHibernate.class);
	}

}
