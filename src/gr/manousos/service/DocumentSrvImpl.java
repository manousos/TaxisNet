package gr.manousos.service;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.E2;
import gr.manousos.model.E2Id;

@WebService(targetNamespace = "http://service.manousos.gr/", portName = "DocumentSrvImplPort", serviceName = "DocumentSrvImplService", endpointInterface = "gr.manousos.service.DocumentSrv")
public class DocumentSrvImpl implements DocumentSrv {

	private static Log log = LogFactory.getLog(UserSrvImpl.class);
	private DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

	@Override
	public E2 finalSubmitE2(E2 entity) {
		try {
			entity = dao.getE2DAO().makePersistent(entity);// .submitE2(entity);
		} catch (Exception ex) {
			log.error("document service E2 error ", ex);
		}

		return entity;
	}

	@Override
	public String test(String msg) {
		return "Your test message: " + msg;
	}

	@Override
	public E2 getE2DocById(int taxPayerID, int year) {
		return dao.getE2DAO().getE2ById(new E2Id(taxPayerID, year));
	}
}
