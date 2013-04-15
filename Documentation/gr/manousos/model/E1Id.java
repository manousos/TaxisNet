package gr.manousos.model;

// Generated Apr 16, 2013 12:57:37 AM by Hibernate Tools 3.4.0.CR1

/**
 * E1Id generated by hbm2java
 */
public class E1Id implements java.io.Serializable {

    private int taxpayerId;
    private int year;

    public E1Id() {
    }

    public E1Id(int taxpayerId, int year) {
	this.taxpayerId = taxpayerId;
	this.year = year;
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

    public boolean equals(Object other) {
	if ((this == other))
	    return true;
	if ((other == null))
	    return false;
	if (!(other instanceof E1Id))
	    return false;
	E1Id castOther = (E1Id) other;

	return (this.getTaxpayerId() == castOther.getTaxpayerId())
		&& (this.getYear() == castOther.getYear());
    }

    public int hashCode() {
	int result = 17;

	result = 37 * result + this.getTaxpayerId();
	result = 37 * result + this.getYear();
	return result;
    }

}
