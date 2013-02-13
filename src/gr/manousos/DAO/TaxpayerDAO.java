package gr.manousos.DAO;

import gr.manousos.model.Taxpayer;
import gr.manousos.model.User;

import java.io.Serializable;

public interface TaxpayerDAO extends GenericDAO<Taxpayer, Serializable> {
	public String getUserIngoById(String strId);

	public void addTaxpayer(Taxpayer user);

	public Taxpayer getTaxpayerByID(int id);

	public User getUserByUserName(String username);

	public Boolean Login(String username, String password);

}
