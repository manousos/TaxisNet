package gr.manousos.model;

// Generated 20 Μαρ 2013 11:40:46 μμ by Hibernate Tools 3.4.0.CR1

/**
 * E2otherEstate generated by hbm2java
 */
public class E2otherEstate implements java.io.Serializable {

    private Integer id;
    private E2 e2;
    private String title;
    private String location;
    private String position;
    private String estateUsage;
    private float area;

    public E2otherEstate() {
    }

    public E2otherEstate(E2 e2, String title, String location, String position,
	    String estateUsage, float area) {
	this.e2 = e2;
	this.title = title;
	this.location = location;
	this.position = position;
	this.estateUsage = estateUsage;
	this.area = area;
    }

    public Integer getId() {
	return this.id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public E2 getE2() {
	return this.e2;
    }

    public void setE2(E2 e2) {
	this.e2 = e2;
    }

    public String getTitle() {
	return this.title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getLocation() {
	return this.location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getPosition() {
	return this.position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public String getEstateUsage() {
	return this.estateUsage;
    }

    public void setEstateUsage(String estateUsage) {
	this.estateUsage = estateUsage;
    }

    public float getArea() {
	return this.area;
    }

    public void setArea(float area) {
	this.area = area;
    }

}
