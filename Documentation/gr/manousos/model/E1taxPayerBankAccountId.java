package gr.manousos.model;

// Generated 3 Ιουν 2013 11:58:42 μμ by Hibernate Tools 3.4.0.CR1

/**
 * E1taxPayerBankAccountId generated by hbm2java
 */
public class E1taxPayerBankAccountId implements java.io.Serializable {

    private int tid;
    private int year;

    public E1taxPayerBankAccountId() {
    }

    public E1taxPayerBankAccountId(int tid, int year) {
	this.tid = tid;
	this.year = year;
    }

    public int getTid() {
	return this.tid;
    }

    public void setTid(int tid) {
	this.tid = tid;
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
	if (!(other instanceof E1taxPayerBankAccountId))
	    return false;
	E1taxPayerBankAccountId castOther = (E1taxPayerBankAccountId) other;

	return (this.getTid() == castOther.getTid())
		&& (this.getYear() == castOther.getYear());
    }

    public int hashCode() {
	int result = 17;

	result = 37 * result + this.getTid();
	result = 37 * result + this.getYear();
	return result;
    }

}