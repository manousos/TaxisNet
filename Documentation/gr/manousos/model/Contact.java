package gr.manousos.model;

// Generated 24 Απρ 2013 9:47:18 μμ by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Contact generated by hbm2java
 */
public class Contact implements java.io.Serializable {

    private Integer contactId;
    private String phone;
    private String fax;
    private String cell;
    private String email;
    private Set<Taxpayer> taxpayers = new HashSet<Taxpayer>(0);
    private Set<RelatePerson> relatePersons = new HashSet<RelatePerson>(0);

    public Contact() {
    }

    public Contact(String phone, String cell, String email) {
	this.phone = phone;
	this.cell = cell;
	this.email = email;
    }

    public Contact(String phone, String fax, String cell, String email,
	    Set<Taxpayer> taxpayers, Set<RelatePerson> relatePersons) {
	this.phone = phone;
	this.fax = fax;
	this.cell = cell;
	this.email = email;
	this.taxpayers = taxpayers;
	this.relatePersons = relatePersons;
    }

    public Integer getContactId() {
	return this.contactId;
    }

    public void setContactId(Integer contactId) {
	this.contactId = contactId;
    }

    public String getPhone() {
	return this.phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getFax() {
	return this.fax;
    }

    public void setFax(String fax) {
	this.fax = fax;
    }

    public String getCell() {
	return this.cell;
    }

    public void setCell(String cell) {
	this.cell = cell;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Set<Taxpayer> getTaxpayers() {
	return this.taxpayers;
    }

    public void setTaxpayers(Set<Taxpayer> taxpayers) {
	this.taxpayers = taxpayers;
    }

    public Set<RelatePerson> getRelatePersons() {
	return this.relatePersons;
    }

    public void setRelatePersons(Set<RelatePerson> relatePersons) {
	this.relatePersons = relatePersons;
    }

}
