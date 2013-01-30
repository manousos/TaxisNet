package gr.manousos.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.E2;
import gr.manousos.model.E2Id;

@Path("/DocumentService")
public class DocumentSrv {
	private static Log log = LogFactory.getLog(DocumentSrv.class);
	private DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

	@Path("/submitE2")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String finalSubmitE2(E2 entity) {
		String res = "";

		try {
			dao.getE2DAO().makePersistent(entity);
			res = "E2 Saved !!";
		} catch (Exception ex) {
			log.error("document service E2 error ", ex);
			res = ex.toString();
		}

		return res;
	}

	@Path("/test/{msg}")
	@GET
	@Produces("application/json")
	public String test(@PathParam("msg") String msg) {
		return "This is a test message: " + msg;
	}

	public E2 getE2DocById(int taxPayerID, int year) {
		return dao.getE2DAO().getE2ById(new E2Id(taxPayerID, year));
	}
}
