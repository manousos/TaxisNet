package gr.manousos.model;

// Generated 2 Μαρ 2013 11:21:38 μμ by Hibernate Tools 3.4.0.CR1

/**
 * E1relatePersonsId generated by hbm2java
 */
public class E1relatePersonsId implements java.io.Serializable {

	private int taxpayerId;
	private int year;
	private int idRelatePerson;

	public E1relatePersonsId() {
	}

	public E1relatePersonsId(int taxpayerId, int year, int idRelatePerson) {
		this.taxpayerId = taxpayerId;
		this.year = year;
		this.idRelatePerson = idRelatePerson;
	}

	public int getTaxpayerId() {
		return this.taxpayerId;
	}

	public void setTaxpayerId(int taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getIdRelatePerson() {
		return this.idRelatePerson;
	}

	public void setIdRelatePerson(int idRelatePerson) {
		this.idRelatePerson = idRelatePerson;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof E1relatePersonsId))
			return false;
		E1relatePersonsId castOther = (E1relatePersonsId) other;

		return (this.getTaxpayerId() == castOther.getTaxpayerId())
				&& (this.getYear() == castOther.getYear())
				&& (this.getIdRelatePerson() == castOther.getIdRelatePerson());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getTaxpayerId();
		result = 37 * result + this.getYear();
		result = 37 * result + this.getIdRelatePerson();
		return result;
	}

}
