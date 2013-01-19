package gr.manousos.service;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.Taxpayer;

@WebService(targetNamespace = "http://service.manousos.gr/", endpointInterface = "gr.manousos.service.UserSrv", portName = "UserSrvImplPort", serviceName = "UserSrvImplService")
public class UserSrvImpl implements UserSrv {

	private static Log log = LogFactory.getLog(UserSrvImpl.class);
	private DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

	public String test(String msg) {
		return "This is a test message: " + msg;
	}

	public void RegisterTaxPayer(Taxpayer user) {
		try {
			// dao.getUserInfoDAO().addTaxpayer(user);
			user = dao.getUserInfoDAO().makePersistent(user);
		} catch (Exception ex) {
			// System.err.println("submit Register Error= " + ex.toString());
		}
	}

	public Taxpayer Register(Taxpayer user) {
		try {
			// dao.getUserInfoDAO().addTaxpayer(user);
			user = dao.getUserInfoDAO().makePersistent(user);
		} catch (Exception ex) {
			log.error("Taxpayer makePersistent error ", ex);
		}
		return user;
	}

	@Override
	public Taxpayer getTaxPayerById(int id) {
		return dao.getUserInfoDAO().getTaxpayerByID(id);
	}
}
