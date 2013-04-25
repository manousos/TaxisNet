package gr.manousos.DAO;

import gr.manousos.model.Taxpayer;

import java.io.Serializable;

public interface TaxpayerDAO extends GenericDAO<Taxpayer, Serializable> {
    public String getUserIngoById(String strId);

    public void addTaxpayer(Taxpayer user);

    public Taxpayer getTaxpayerByID(int id);

    public Taxpayer getTaxpayerByUserName(String username);
}
