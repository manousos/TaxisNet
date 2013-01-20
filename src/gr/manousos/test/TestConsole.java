package gr.manousos.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.E2;
import gr.manousos.model.E2coOwner;
import gr.manousos.model.E2estate;
import gr.manousos.model.E2otherEstate;
import gr.manousos.model.Taxpayer;
import gr.manousos.model.E2Id;
import gr.manousos.service.UserSrv;

public class TestConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);
		// UserSrvImplService userService = new UserSrvImplService();
		// UserSrv userClient = userService.getUserSrvImplPort();
		// gr.manousos.model.Taxpayer taxpayer = userClient.getTaxPayerById(9);

		Taxpayer t = dao.getUserInfoDAO().getTaxpayerByID(9);

		System.out.println("AFM : " + t == null ? "einai null" : t.getAfm());

		// E2 primary KEY
		E2Id key = new E2Id();
		key.setTaxierId(t.getId());
		key.setYear(Calendar.getInstance().get(Calendar.YEAR));

		// master table !
		E2 e2 = new E2();
		e2.setId(key);
		e2.setIsComplete(0);
		e2.setTaxpayer(t);
		// dao.getE2DAO().makePersistent(e2);

		E2estate e2estateObj = new E2estate();
		e2estateObj.setArea(10);
		e2estateObj.setE2(e2);
		e2estateObj.setFrom(2);
		e2estateObj.setLocation("location test");
		e2estateObj.setMonthlyRental(100);
		e2estateObj.setPosition("position from test");
		e2estateObj.setRersentCoOwner(50);
		e2estateObj.setTenantAfm("0000000000");
		e2estateObj.setTenantFullName("tenantFullName from test");
		e2estateObj.setTo(10);
		e2estateObj.setUsage("usage from test");

		E2coOwner e2coOwners = new E2coOwner();
		e2coOwners.setAddress("address from test");
		e2coOwners.setAfm("0000000");
		e2coOwners.setFullName("fullName from test");
		e2coOwners.setPercent(50);
		e2coOwners.setRent(new Float(100f));
		e2coOwners.setE2estate(e2estateObj);

		E2otherEstate otherEst = new E2otherEstate();
		otherEst.setArea(111);
		otherEst.setE2(e2);
		otherEst.setLocation("o location from test");
		otherEst.setPosition("o position from test");
		otherEst.setTitle("o title from test");
		otherEst.setUsage("o usage from test");

		Set<E2estate> listOfE2estates = new HashSet<E2estate>();
		Set<E2coOwner> listOfE2coOwner = new HashSet<E2coOwner>();
		Set<E2otherEstate> listOfOtherEstates = new HashSet<E2otherEstate>();

		listOfOtherEstates.add(otherEst);
		listOfE2coOwner.add(e2coOwners);
		listOfE2estates.add(e2estateObj);

		e2.setE2otherEstates(listOfOtherEstates);
		e2estateObj.setE2coOwners(listOfE2coOwner);
		e2.setE2estates(listOfE2estates);		

		dao.getE2DAO().makePersistent(e2);		
	}
}
