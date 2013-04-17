package gr.manousos.service.rest;

import gr.manousos.model.E1objectiveSpending;
import gr.manousos.model.Taxpayer;

import java.net.URI;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Path("/TaxCalkService")
public class CalculateTaxSrv {

    Properties config = new Properties();
    
    @Path("/tax")
    @GET
    @Produces("application/json")
    public float tax(float totalIncome) {
	// getE1
	// getObj()
	// getFinalIncome

	E1objectiveSpending objSpend = null;

	ClientConfig wsConfig = new DefaultClientConfig();
	Client client = Client.create(wsConfig);

	try {
	    config.load(getClass().getClassLoader().getResourceAsStream(
		    "config.properties"));

	    WebResource restSrv = client.resource(new URI("http://localhost:"
		    + wsConfig.getProperty("web_port") + "/TaxisNet/rest/"));
	    objSpend = (E1objectiveSpending) restSrv
		    .path("DocumentService/getObjectiveSpendingByE1Id")
		    .queryParam("year", "2013").queryParam("tId", "9")
		    .accept(MediaType.APPLICATION_JSON)
		    .get(E1objectiveSpending.class);
	} catch (Exception ex) {
	    // this.error = ex.toString();
	}
	if (objSpend != null) {
	}
	
	if (totalIncome > 5000 && totalIncome < 12000)
	    return (totalIncome - 5000) * 0.1f;
	if (totalIncome > 12000 && totalIncome < 16000)
	    return 700 + (totalIncome - 12000) * 0.18f;
	if (totalIncome > 16000 && totalIncome < 26000)
	    return 1420 + (totalIncome - 16000) * 0.25f;
	if (totalIncome > 26000 && totalIncome < 40000)
	    return 3920 + (totalIncome - 26000) * 0.35f;
	if (totalIncome > 40000 && totalIncome < 60000)
	    return 8820 + (totalIncome - 40000) * 0.38f;
	if (totalIncome > 60000 && totalIncome < 100000)
	    return 16420 + (totalIncome - 60000) * 0.40f;
	if (totalIncome > 100000)
	    return 32420 + (totalIncome - 100000) * 0.45f;

	return 0;
    }

    // Life taxes
    private float lifeObjTax(boolean marriage, boolean retired65YearOld) {

	if (marriage && retired65YearOld)
	    return 5000 - 5000 * 0.3f;
	if (marriage)
	    return 5000f;
	if (retired65YearOld)
	    return 3000 - 3000 * 0.3f;

	return 3000f;
    }

    // End Life taxes

    // House taxes
    private float applyHouseZonePrice(float zonePrice, float houseObjTax) {
	if (zonePrice > 5000)
	    return houseObjTax * 0.7f;
	if (zonePrice > 2800 && houseObjTax < 4900)
	    return houseObjTax * 0.4f;

	return houseObjTax;
    }

    private float zeroFloorAdditionalTax(float houseObjTax, boolean hasZeroFloor) {
	if (hasZeroFloor)
	    return houseObjTax * 0.2f;

	return houseObjTax;
    }

    private float calckPrimaryHouseObj(float area, float zonePrice,
	    boolean zeroFloor, float ownPercent) {

	if (area < 80)
	    return zeroFloorAdditionalTax(
		    applyHouseZonePrice(zonePrice, area * 40), zeroFloor)
		    * ownPercent;
	if (area < 120)
	    return zeroFloorAdditionalTax(
		    applyHouseZonePrice(zonePrice, 3200 + (area - 80) * 65),
		    zeroFloor) * ownPercent;
	if (area < 200)
	    return zeroFloorAdditionalTax(
		    applyHouseZonePrice(zonePrice, 7800 + (area * 110)),
		    zeroFloor) * ownPercent;
	if (area < 300)
	    return zeroFloorAdditionalTax(
		    applyHouseZonePrice(zonePrice, 2200 + (area * 200)),
		    zeroFloor) * ownPercent;
	if (area > 300)
	    return zeroFloorAdditionalTax(
		    applyHouseZonePrice(zonePrice, 60000 + (area * 400)),
		    zeroFloor) * ownPercent;

	return 0;
    }

    private float calckOtherHouseObj(float area, float zonePrice,
	    boolean zeroFloor, float ownPercent) {
	return calckPrimaryHouseObj(area, zonePrice, zeroFloor, ownPercent) / 2;
    }

    private float calckHelpAreaObj(float area, float zonePrice,
	    boolean zeroFloor) {
	return zeroFloorAdditionalTax(
		applyHouseZonePrice(zonePrice, 40 * area), zeroFloor);
    }

    private float calckOtherHelpAreaObj(float area, float zonePrice,
	    boolean zeroFloor) {
	return zeroFloorAdditionalTax(
		applyHouseZonePrice(zonePrice, 40 * area), zeroFloor) / 2;
    }

    // End House taxes

    // Car taxes
    private float carOldestReduceTax(float tax, int years) {
	if (years > 5 && years < 10)
	    return tax - tax * 0.3f;
	if (years > 10)
	    return tax - tax * 0.5f;

	return tax;
    }

    private float clakCarObj(int years, float engineVolume, float ownPercent) {
	if (engineVolume < 1200)
	    return carOldestReduceTax(4000, years) * ownPercent;
	if (engineVolume < 2000)
	    return carOldestReduceTax(4000 + 600 * (engineVolume - 1200), years)
		    * ownPercent;
	if (engineVolume < 3000)
	    return carOldestReduceTax(4000 + 900 * (engineVolume - 2000), years)
		    * ownPercent;
	if (engineVolume > 3000)
	    return carOldestReduceTax(4000 + 1200 * (engineVolume - 3000),
		    years) * ownPercent;

	return 0;
    }

    // End Car taxes

    // Boats

    enum BoatType {
	MachineWithAccommodation, Machine, SailingOrGreekTraditional
    }

    private float calckBoatObj(int years, float meters, BoatType type,
	    float totalCrewPayments, float ownPercent) {

	switch (type) {
	case Machine:
	    if (meters < 5)
		return totalCrewPayments + 4000f * ownPercent;
	    else
		return totalCrewPayments + 4000 + 2000 * (meters - 5)
			* ownPercent;
	case MachineWithAccommodation:
	    return totalCrewPayments
		    + bigBoatReduceByYear(bigBoatCalck(meters), years)
		    * ownPercent;

	case SailingOrGreekTraditional:
	    return (totalCrewPayments + bigBoatReduceByYear(
		    bigBoatCalck(meters), years)) / 2 * ownPercent;

	}
	return 0;
    }

    private float bigBoatReduceByYear(float tax, int years) {
	if (years > 10)
	    return tax - tax * 0.3f;
	if (years > 5)
	    return tax - tax * 0.15f;

	return tax;
    }

    private float bigBoatCalck(float meters) {
	if (meters < 7)
	    return 12000f;
	if (meters < 10)
	    return 12000 + (meters - 7) * 3000;
	if (meters < 12)
	    return 21000 + (meters - 10) * 7500;
	if (meters < 15)
	    return 36000 + (meters - 12) * 15000;
	if (meters < 18)
	    return 81000 + (meters - 15) * 22500;
	if (meters < 22)
	    return 148500 + (meters - 18) * 30000;
	if (meters > 22)
	    return 268500 + (meters - 22) * 50000;

	return 0;
    }

    // END BAOT TAX

    // POOL TAX
    private float calckPoolObj(boolean isInside, float area) {
	if (isInside)
	    return poolObjByAreaCalck(area) * 2;
	else
	    return poolObjByAreaCalck(area);
    }

    private float poolObjByAreaCalck(float area) {
	if (area < 60)
	    return area * 160;
	else
	    return 9600 + (area - 60) * 320;
    }

    // END POOL TAX

    // Airplane TAX
    enum AirplaneType {
	Windplanes, Propeller, Jet
    }

    private float calckAirplaneObj(int power, AirplaneType type) {
	switch (type) {
	case Windplanes:
	    return 8.000f;
	case Propeller:
	    if (power < 150)
		return 65000f;
	    else
		return 65000 + (power - 150) * 500;
	case Jet:
	    return power * 200;
	default:
	    return 0;
	}
    }

    // END Airplane TAX

    // Other taxes
    // private float privateSchool(float totalPayment) {
    // return totalPayment * 0.1f + totalPayment;
    // }

    private float calckOtherObj(float housekeeping, float carLeasing,
	    float boatLeasing, float pool, float estateLeasing,
	    float mobGraterThan10Th, float charity, float loanDepreciation,
	    float privateSchool) {
	return housekeeping + carLeasing + boatLeasing + pool + estateLeasing
		+ mobGraterThan10Th + charity + loanDepreciation
		+ privateSchool + privateSchool * 0.1f;
    }
    // End Other taxes
}
