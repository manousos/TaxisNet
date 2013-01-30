package gr.manousos.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.Taxpayer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("/UserService")
public class UserSrv {
	private static Log log = LogFactory.getLog(UserSrv.class);
	private DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

	
	@Path("/test/{msg}")
	@GET
//	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/json")
	public String test(@PathParam("msg") String msg) {
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

	@Path("/Register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Taxpayer Register(Taxpayer user) {
		try {
			// dao.getUserInfoDAO().addTaxpayer(user);
			user = dao.getUserInfoDAO().makePersistent(user);
		} catch (Exception ex) {
			log.error("Taxpayer makePersistent error ", ex);
		}
		return user;
	}

	@Path("/TaxPayer/{id}")
	@GET
//	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/json")
	public Taxpayer getTaxPayerById(@PathParam("id") int id) {
		return dao.getUserInfoDAO().getTaxpayerByID(id);
	}
}
