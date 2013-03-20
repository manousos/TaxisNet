package gr.manousos.model;

// Generated 20 Μαρ 2013 11:40:46 μμ by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * E2estate generated by hbm2java
 */
public class E2estate implements java.io.Serializable {

    private Integer estateId;
    private E2 e2;
    private String location;
    private String position;
    private String estateUsage;
    private float area;
    private String tenantFullName;
    private String tenantAfm;
    private int fromMonth;
    private int toMonth;
    private float monthlyRental;
    private float rersentCoOwner;
    private float revenueFreeHome;
    private float revenueFreeOffice;
    private float revenuePrivateHotel;
    private float revenuePrivateOffice;
    private Set<E2coOwner> e2coOwners = new HashSet<E2coOwner>(0);

    public E2estate() {
    }

    public E2estate(E2 e2, String location, String position,
	    String estateUsage, float area, String tenantFullName,
	    String tenantAfm, int fromMonth, int toMonth, float monthlyRental,
	    float rersentCoOwner, float revenueFreeHome,
	    float revenueFreeOffice, float revenuePrivateHotel,
	    float revenuePrivateOffice) {
	this.e2 = e2;
	this.location = location;
	this.position = position;
	this.estateUsage = estateUsage;
	this.area = area;
	this.tenantFullName = tenantFullName;
	this.tenantAfm = tenantAfm;
	this.fromMonth = fromMonth;
	this.toMonth = toMonth;
	this.monthlyRental = monthlyRental;
	this.rersentCoOwner = rersentCoOwner;
	this.revenueFreeHome = revenueFreeHome;
	this.revenueFreeOffice = revenueFreeOffice;
	this.revenuePrivateHotel = revenuePrivateHotel;
	this.revenuePrivateOffice = revenuePrivateOffice;
    }

    public E2estate(E2 e2, String location, String position,
	    String estateUsage, float area, String tenantFullName,
	    String tenantAfm, int fromMonth, int toMonth, float monthlyRental,
	    float rersentCoOwner, float revenueFreeHome,
	    float revenueFreeOffice, float revenuePrivateHotel,
	    float revenuePrivateOffice, Set<E2coOwner> e2coOwners) {
	this.e2 = e2;
	this.location = location;
	this.position = position;
	this.estateUsage = estateUsage;
	this.area = area;
	this.tenantFullName = tenantFullName;
	this.tenantAfm = tenantAfm;
	this.fromMonth = fromMonth;
	this.toMonth = toMonth;
	this.monthlyRental = monthlyRental;
	this.rersentCoOwner = rersentCoOwner;
	this.revenueFreeHome = revenueFreeHome;
	this.revenueFreeOffice = revenueFreeOffice;
	this.revenuePrivateHotel = revenuePrivateHotel;
	this.revenuePrivateOffice = revenuePrivateOffice;
	this.e2coOwners = e2coOwners;
    }

    public Integer getEstateId() {
	return this.estateId;
    }

    public void setEstateId(Integer estateId) {
	this.estateId = estateId;
    }

    public E2 getE2() {
	return this.e2;
    }

    public void setE2(E2 e2) {
	this.e2 = e2;
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

    public String getTenantFullName() {
	return this.tenantFullName;
    }

    public void setTenantFullName(String tenantFullName) {
	this.tenantFullName = tenantFullName;
    }

    public String getTenantAfm() {
	return this.tenantAfm;
    }

    public void setTenantAfm(String tenantAfm) {
	this.tenantAfm = tenantAfm;
    }

    public int getFromMonth() {
	return this.fromMonth;
    }

    public void setFromMonth(int fromMonth) {
	this.fromMonth = fromMonth;
    }

    public int getToMonth() {
	return this.toMonth;
    }

    public void setToMonth(int toMonth) {
	this.toMonth = toMonth;
    }

    public float getMonthlyRental() {
	return this.monthlyRental;
    }

    public void setMonthlyRental(float monthlyRental) {
	this.monthlyRental = monthlyRental;
    }

    public float getRersentCoOwner() {
	return this.rersentCoOwner;
    }

    public void setRersentCoOwner(float rersentCoOwner) {
	this.rersentCoOwner = rersentCoOwner;
    }

    public float getRevenueFreeHome() {
	return this.revenueFreeHome;
    }

    public void setRevenueFreeHome(float revenueFreeHome) {
	this.revenueFreeHome = revenueFreeHome;
    }

    public float getRevenueFreeOffice() {
	return this.revenueFreeOffice;
    }

    public void setRevenueFreeOffice(float revenueFreeOffice) {
	this.revenueFreeOffice = revenueFreeOffice;
    }

    public float getRevenuePrivateHotel() {
	return this.revenuePrivateHotel;
    }

    public void setRevenuePrivateHotel(float revenuePrivateHotel) {
	this.revenuePrivateHotel = revenuePrivateHotel;
    }

    public float getRevenuePrivateOffice() {
	return this.revenuePrivateOffice;
    }

    public void setRevenuePrivateOffice(float revenuePrivateOffice) {
	this.revenuePrivateOffice = revenuePrivateOffice;
    }

    public Set<E2coOwner> getE2coOwners() {
	return this.e2coOwners;
    }

    public void setE2coOwners(Set<E2coOwner> e2coOwners) {
	this.e2coOwners = e2coOwners;
    }

}
