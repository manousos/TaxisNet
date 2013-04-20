package gr.manousos.service.rest;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1objectiveSpending;
import gr.manousos.model.Taxpayer;

import java.net.URI;
import java.util.Calendar;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author manousos
 *
 */
/**
 * @author manousos
 * 
 */
@Path("/TaxCalkService")
public class CalculateTaxSrv {

    Properties config = new Properties();
    DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

    @Path("/tax")
    @GET
    @Produces("application/json")
    public float tax(float totalIncome) {
	// getE1
	// getObj()
	// getFinalIncome
	E1Id key = new E1Id(9, 2013);

	E1objectiveSpending objSpend = dao.getE1DAO()
		.getObjectiveSpendingByE1Id(key);

	if (objSpend != null) {
	    float total = 0f;
	    total = getHousesObjValue(objSpend)
		    + getCarObjValue(objSpend)
		    + getBoatObjValue(objSpend)
		    + calckAirplaneObj(objSpend.getAircraftPowerLibres(),
			    getAirplaneType(objSpend.getAircraftType()))
		    + getPoolObjValue(objSpend)
		    + calckOtherObj(objSpend.get_707(), objSpend.get_719()
			    + objSpend.get_720(),
			    objSpend.get_721() + objSpend.get_722(),
			    objSpend.get_723() + objSpend.get_724(),
			    objSpend.get_725() + objSpend.get_726(),
			    objSpend.get_727() + objSpend.get_728(),
			    objSpend.get_769());
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

    private float getPoolObjValue(E1objectiveSpending objSpend) {
	float fromPools = 0f;
	float percent = 0f;

	percent = objSpend.getPoolPrincipalCoOwnerIndoor()
		+ objSpend.getPoolWifeCoOwnerIndoor();
	if (objSpend.get_767() > 0)
	    fromPools += calckPoolObj(false, objSpend.get_767(), percent);

	percent = 0f;
	percent = objSpend.getPoolPrincipalCoOwnerOutdoor()
		+ objSpend.getPoolWifeCoOwnerOutdoor();
	if (objSpend.get_768() > 0)
	    fromPools += calckPoolObj(true, objSpend.get_768(), percent);

	return fromPools;
    }

    private float getBoatObjValue(E1objectiveSpending objSpend) {
	float fromBoats = 0f;
	float percent = 0f;

	percent = objSpend.getPercentPrincipalCoOwner1()
		+ objSpend.getPercentWifeCoOwner1();

	fromBoats += calckBoatObj(
		(Calendar.YEAR - objSpend.getFirstRegister1()),
		objSpend.get_747(),
		getBoatType(objSpend.getSailShip1(),
			objSpend.getAccommodationSpace1()), percent);

	percent = 0f;
	percent = objSpend.getPercentPrincipalCoOwner2()
		+ objSpend.getPercentWifeCoOwner2();
	fromBoats += calckBoatObj(
		(Calendar.YEAR - objSpend.getFirstRegister2()),
		objSpend.get_748(),
		getBoatType(objSpend.getSailShip2(),
			objSpend.getAccommodationSpace2()), percent);

	return fromBoats + objSpend.get_731() + objSpend.get_732();
    }

    private float getCarObjValue(E1objectiveSpending objSpend) {
	float fromCars = 0f;

	fromCars += calkCarObj(
		(Calendar.getInstance().get(Calendar.YEAR - objSpend.get_775())),
		objSpend.get_703(), objSpend.get_771());
	fromCars += calkCarObj(
		(Calendar.getInstance().get(Calendar.YEAR - objSpend.get_776())),
		objSpend.get_704(), objSpend.get_772());
	fromCars += calkCarObj(
		(Calendar.getInstance().get(Calendar.YEAR - objSpend.get_777())),
		objSpend.get_705(), objSpend.get_773());
	fromCars += calkCarObj(
		(Calendar.getInstance().get(Calendar.YEAR - objSpend.get_778())),
		objSpend.get_706(), objSpend.get_774());

	return fromCars;
    }

    private float getHousesObjValue(E1objectiveSpending objSpend) {
	float percent = 0f;
	float fromHouses = 0f;

	percent = objSpend.get_213() + objSpend.get_214();
	fromHouses += calckPrimaryHouseObj(objSpend.get_211(),
		objSpend.get_216(), IntToBoolean(objSpend.get_240()), percent);
	fromHouses += calckHelpAreaObj(objSpend.get_212(), objSpend.get_216(),
		IntToBoolean(objSpend.get_240()));

	percent = 0f;
	percent = objSpend.get_220() + objSpend.get_221();
	fromHouses += calckOtherHouseObj(objSpend.get_218(),
		objSpend.get_223(), IntToBoolean(objSpend.get_241()), percent);
	fromHouses += calckOtherHelpAreaObj(objSpend.get_219(),
		objSpend.get_223(), IntToBoolean(objSpend.get_241()));

	percent = 0f;
	percent = objSpend.get_227() + objSpend.get_228();
	fromHouses += calckOtherHouseObj(objSpend.get_225(),
		objSpend.get_230(), IntToBoolean(objSpend.get_242()), percent);
	fromHouses += calckOtherHelpAreaObj(objSpend.get_226(),
		objSpend.get_230(), IntToBoolean(objSpend.get_242()));

	fromHouses += objSpend.get_707() + objSpend.get_708();

	return fromHouses;
    }

    private boolean IntToBoolean(int value) {
	return (value == 1) ? true : false;
    }

    private float addOwnerPercent(float value, float percent) {
	return value + value * percent;
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

    /**
     * @param area
     * @param zonePrice
     * @param zeroFloor
     * @param ownPercent
     * @return object price for primary house
     * 
     */
    private float calckPrimaryHouseObj(float area, float zonePrice,
	    boolean zeroFloor, float ownPercent) {

	if (area < 80)
	    return addOwnerPercent(
		    zeroFloorAdditionalTax(
			    applyHouseZonePrice(zonePrice, area * 40),
			    zeroFloor), ownPercent);
	if (area < 120)
	    return addOwnerPercent(
		    zeroFloorAdditionalTax(
			    applyHouseZonePrice(zonePrice,
				    3200 + (area - 80) * 65), zeroFloor),
		    ownPercent);
	if (area < 200)
	    return addOwnerPercent(
		    zeroFloorAdditionalTax(
			    applyHouseZonePrice(zonePrice, 7800 + (area * 110)),
			    zeroFloor), ownPercent);
	if (area < 300)
	    return addOwnerPercent(
		    zeroFloorAdditionalTax(
			    applyHouseZonePrice(zonePrice, 2200 + (area * 200)),
			    zeroFloor), ownPercent);
	if (area > 300)
	    return addOwnerPercent(
		    zeroFloorAdditionalTax(
			    applyHouseZonePrice(zonePrice, 60000 + (area * 400)),
			    zeroFloor), ownPercent);

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

    private float calkCarObj(int years, float engineVolume, float ownPercent) {
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

    private BoatType getBoatType(int isSailBoat, int hasAccomontation) {
	BoatType type;
	if (isSailBoat == 1)
	    type = BoatType.SailingOrGreekTraditional;
	else if (hasAccomontation == 1)
	    type = BoatType.MachineWithAccommodation;
	else
	    type = BoatType.Machine;
	return type;
    }

    /**
     * @param years
     * @param meters
     * @param type
     *            is a boat type
     * @param totalCrewPayments
     * @param ownPercent
     * @return
     */
    private float calckBoatObj(int years, float meters, BoatType type,
	    float ownPercent) {

	switch (type) {
	case Machine:
	    if (meters < 5)
		return addOwnerPercent(4000f, ownPercent);
	    else
		return addOwnerPercent(4000 + 2000 * (meters - 5), ownPercent);
	case MachineWithAccommodation:
	    return addOwnerPercent(
		    bigBoatReduceByYear(bigBoatCalck(meters), years),
		    ownPercent);

	case SailingOrGreekTraditional:
	    return addOwnerPercent(
		    (bigBoatReduceByYear(bigBoatCalck(meters), years)) / 2,
		    ownPercent);

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
    private float calckPoolObj(boolean isInside, float area, float percent) {
	if (isInside)
	    return addOwnerPercent(poolObjByAreaCalck(area) * 2, percent);
	else
	    return addOwnerPercent(poolObjByAreaCalck(area), percent);
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

    private AirplaneType getAirplaneType(String strType) {
	if (strType.trim().toLowerCase().equals("ανεμόπτερο")
		|| strType.trim().toUpperCase().equals("ΑΝΕΜΟΠΤΕΡΟ"))
	    return AirplaneType.Windplanes;
	else if (strType.trim().toUpperCase().equals("ΑΕΡΙΟΠΡΟΩΘΟΥΜΕΝΑ")
		|| strType.trim().toLowerCase().equals("αεροπροωθούμενα")
		|| strType.trim().toLowerCase().equals("jet"))
	    return AirplaneType.Jet;
	else
	    return AirplaneType.Propeller;
    }

    // END Airplane TAX

    // Other taxes
    // private float privateSchool(float totalPayment) {
    // return totalPayment * 0.1f + totalPayment;
    // }

    private float calckOtherObj(float housekeeping, float carLeasing,
	    float boatLeasing, float mobGraterThan10Th, float charity,
	    float loanDepreciation, float privateSchool) {
	return housekeeping + carLeasing + boatLeasing + +mobGraterThan10Th
		+ charity + loanDepreciation + privateSchool + privateSchool
		* 0.1f;
    }
    // End Other taxes
}
