package gr.manousos.model;

// Generated 8 Ιουν 2013 9:45:48 μμ by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * RelatePerson generated by hbm2java
 */
public class RelatePerson implements java.io.Serializable {

    private Integer idRelatePerson;
    private Contact contact;
    private Integer type;
    private String address;
    private String afm;
    private String fname;
    private String lname;
    private String fatherName;
    private String atid;
    private Set<E1> e1s = new HashSet<E1>(0);

    public RelatePerson() {
    }

    public RelatePerson(Contact contact, Integer type, String address,
	    String afm, String fname, String lname, String fatherName,
	    String atid, Set<E1> e1s) {
	this.contact = contact;
	this.type = type;
	this.address = address;
	this.afm = afm;
	this.fname = fname;
	this.lname = lname;
	this.fatherName = fatherName;
	this.atid = atid;
	this.e1s = e1s;
    }

    public Integer getIdRelatePerson() {
	return this.idRelatePerson;
    }

    public void setIdRelatePerson(Integer idRelatePerson) {
	this.idRelatePerson = idRelatePerson;
    }

    public Contact getContact() {
	return this.contact;
    }

    public void setContact(Contact contact) {
	this.contact = contact;
    }

    public Integer getType() {
	return this.type;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public String getAddress() {
	return this.address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getAfm() {
	return this.afm;
    }

    public void setAfm(String afm) {
	this.afm = afm;
    }

    public String getFname() {
	return this.fname;
    }

    public void setFname(String fname) {
	this.fname = fname;
    }

    public String getLname() {
	return this.lname;
    }

    public void setLname(String lname) {
	this.lname = lname;
    }

    public String getFatherName() {
	return this.fatherName;
    }

    public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
    }

    public String getAtid() {
	return this.atid;
    }

    public void setAtid(String atid) {
	this.atid = atid;
    }

    public Set<E1> getE1s() {
	return this.e1s;
    }

    public void setE1s(Set<E1> e1s) {
	this.e1s = e1s;
    }

}
