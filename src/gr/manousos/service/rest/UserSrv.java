package gr.manousos.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.Taxpayer;
import gr.manousos.model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("/UserService")
public class UserSrv {
	private static Log log = LogFactory.getLog(UserSrv.class);
	private DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

	@Path("/test/{msg}")
	@GET
	// @Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/json")
	public String test(@PathParam("msg") String msg) {
		return "This is a test message: " + msg;
	}

	public void RegisterTaxPayer(Taxpayer user) {
		try {
			// dao.getUserInfoDAO().addTaxpayer(user);
			user = dao.getTaxpayerDAO().makePersistent(user);
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
			user = dao.getTaxpayerDAO().makePersistent(user);
		} catch (Exception ex) {
			log.error("Taxpayer makePersistent error ", ex);
		}
		return user;
	}

	@Path("/getUserByUserName")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByUserName(String username) {
		try {
			return dao.getUserDAO().getUserByUserName(username);
		} catch (Exception e) {
			log.error("Taxpayer makePersistent error ", e);
		}
		return null;
	}

	@Path("/Login")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String Login(@FormParam("username") String username,
			@FormParam("password") String password) {
		try {
			if (dao.getUserDAO().Login(username, password))
				return "1";

		} catch (Exception e) {
			log.error("Taxpayer makePersistent error ", e);
		}
		return "0";
	}

	@Path("/TaxPayer/{id}")
	@GET
	@Produces("application/json")
	public Taxpayer getTaxPayerById(@PathParam("id") int id) {
		return dao.getTaxpayerDAO().getTaxpayerByID(id);

	}

	@Path("/getTaxPayerByUserName/{username}")
	@GET
	@Produces("application/json")
	public Taxpayer getTaxPayerById(@PathParam("username") String username) {
		return dao.getTaxpayerDAO().getTaxpayerByUserName(username);

	}

}