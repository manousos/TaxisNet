package gr.manousos.model;

// Generated 2 Μαρ 2013 11:21:38 μμ by Hibernate Tools 3.4.0.CR1

/**
 * E2Id generated by hbm2java
 */
public class E2Id implements java.io.Serializable {

	private int taxierId;
	private int year;

	public E2Id() {
	}

	public E2Id(int taxierId, int year) {
		this.taxierId = taxierId;
		this.year = year;
	}

	public int getTaxierId() {
		return this.taxierId;
	}

	public void setTaxierId(int taxierId) {
		this.taxierId = taxierId;
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
		if (!(other instanceof E2Id))
			return false;
		E2Id castOther = (E2Id) other;

		return (this.getTaxierId() == castOther.getTaxierId())
				&& (this.getYear() == castOther.getYear());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getTaxierId();
		result = 37 * result + this.getYear();
		return result;
	}

}
