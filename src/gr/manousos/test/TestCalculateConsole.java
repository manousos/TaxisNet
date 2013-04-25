package gr.manousos.test;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.E1Id;
import gr.manousos.model.IncomeTax;
import gr.manousos.service.rest.CalculateTaxSrv;

public class TestCalculateConsole {
    static DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

    /**
     * @param args
     */
    public static void main(String[] args) {
	// System.out.println(calculateTax(9, 2003).getPrincipalTax());
	CalculateTaxSrv co = new CalculateTaxSrv();
	IncomeTax tax = co.calculateAndSaveTax(9, 2013);
	//tax.setE1(dao.getE1DAO().findById(new E1Id(9, 2013), false));
	System.out.println(tax.getPrincipalTax());
	//co.saveTax(tax);
    }
}
