package gr.manousos.model;

// Generated 3 Μαρ 2013 10:07:00 μμ by Hibernate Tools 3.4.0.CR1

/**
 * E1relatePersons generated by hbm2java
 */
public class E1relatePersons implements java.io.Serializable {

	private E1relatePersonsId id;
	private RelatePerson relatePerson;
	private E1 e1;

	public E1relatePersons() {
	}

	public E1relatePersons(E1relatePersonsId id, RelatePerson relatePerson,
			E1 e1) {
		this.id = id;
		this.relatePerson = relatePerson;
		this.e1 = e1;
	}

	public E1relatePersonsId getId() {
		return this.id;
	}

	public void setId(E1relatePersonsId id) {
		this.id = id;
	}

	public RelatePerson getRelatePerson() {
		return this.relatePerson;
	}

	public void setRelatePerson(RelatePerson relatePerson) {
		this.relatePerson = relatePerson;
	}

	public E1 getE1() {
		return this.e1;
	}

	public void setE1(E1 e1) {
		this.e1 = e1;
	}

}
