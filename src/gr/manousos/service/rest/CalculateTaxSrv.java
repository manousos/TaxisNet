package gr.manousos.service.rest;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1expensesRemovedFromTotalIncome;
import gr.manousos.model.E1objectiveSpending;
import gr.manousos.model.E1taxableIncomes;
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
    public float calculateTax(float totalIncome) {
	// getE1
	// getObj()
	// getFinalIncome

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

    private float getPrincipalTaxableIncome(E1Id key,
	    E1objectiveSpending spend, E1taxableIncomes taxable) {
	float removedIncomes = getPrincipalRemovedFromTotalIncome(key);
	float incomes = getPrincipalTotalTaxableIncome(taxable);
	float strict = getPrincipalTotalAnnualStrictCost(spend, key);

	return 0f;
    }

    private float getWifeTaxableIncome() {
	return 0f;
    }

    private float getWifeRemovedFromTotalIncome(E1Id key) {
	E1expensesRemovedFromTotalIncome erfti = dao.getE1DAO()
		.getExpensesRemovedFromTotalIncomeByE1Id(key);
	float total = 0f;

	if (erfti != null)
	    total = erfti.get_076()
		    * 0.2f
		    + (erfti.get_052() + erfti.get_054() + erfti.get_058()
			    + erfti.get_060() + erfti.get_062()
			    + erfti.get_064() + erfti.get_080()
			    + erfti.get_088() + erfti.get_090()
			    + erfti.get_812() + +erfti.get_814() + +erfti
				.get_816()) * 0.1f;

	return total;
    }

    private float getPrincipalRemovedFromTotalIncome(E1Id key) {
	E1expensesRemovedFromTotalIncome erfti = dao.getE1DAO()
		.getExpensesRemovedFromTotalIncomeByE1Id(key);
	float total = 0f;
	if (erfti != null)
	    total = erfti.get_075()
		    * 0.2f
		    + (erfti.get_051() + erfti.get_053() + erfti.get_057()
			    + erfti.get_059() + erfti.get_061()
			    + erfti.get_063() + erfti.get_079()
			    + erfti.get_081() + erfti.get_082()
			    + erfti.get_083() + erfti.get_084()
			    + erfti.get_085() + erfti.get_087()
			    + erfti.get_089() + erfti.get_811()
			    + +erfti.get_813() + +erfti.get_815()
			    + +erfti.get_817() + erfti.get_819()
			    + erfti.get_821() + erfti.get_823()) * 0.1f;
	return total;

    }

    private float getPrincipalTotalTaxableIncome(E1taxableIncomes taxable) {
	float total = 0f;

	total = taxable.get_301() + taxable.get_321() + taxable.get_303()
		+ taxable.get_317() + taxable.get_461() + taxable.get_921()
		+ taxable.get_919() + taxable.get_915() + taxable.get_923()
		+ taxable.get_335() + taxable.get_337() + taxable.get_339()
		+ taxable.get_465() + taxable.get_467() + taxable.get_475()
		+ taxable.get_479() + taxable.get_481() + taxable.get_401()
		+ taxable.get_403() + taxable.get_405() + taxable.get_407()
		+ taxable.get_413() + taxable.get_415() + taxable.get_425()
		+ taxable.get_501() + taxable.get_503() + taxable.get_505()
		+ taxable.get_507() + taxable.get_511() + taxable.get_515()
		+ taxable.get_517() + taxable.get_103() + taxable.get_121()
		+ taxable.get_105() + taxable.get_107() + taxable.get_109()
		+ taxable.get_101() + taxable.get_909() + taxable.get_111()
		+ taxable.get_113() + taxable.get_129() + taxable.get_143()
		+ taxable.get_145() + taxable.get_147() + taxable.get_141()
		+ taxable.get_701() + taxable.get_123() + taxable.get_125()
		+ taxable.get_151() + taxable.get_163() + taxable.get_165()
		+ taxable.get_159() + taxable.get_175() + taxable.get_131()
		+ taxable.get_133() + taxable.get_741() + taxable.get_291()
		+ taxable.get_389() + taxable.get_391() + taxable.get_463()
		+ taxable.get_471() + taxable.get_411() + taxable.get_411()
		+ taxable.get_421() + taxable.get_509() + taxable.get_513()
		+ taxable.get_295() + taxable.get_171() + taxable.get_173()
		+ taxable.get_395() + taxable.getAgrTotalNetincome1()
		+ taxable.getAgrTotalNetincome2()
		+ taxable.getAgrTotalNetincome3()
		+ taxable.getAgrTotalNetincome4();

	return total;
    }

    private float getWifeTotalTaxableIncome(E1taxableIncomes taxable) {
	float total = 0f;

	total = taxable.get_302() + taxable.get_322() + taxable.get_304()
		+ taxable.get_318() + taxable.get_462() + taxable.get_922()
		+ taxable.get_920() + taxable.get_916() + taxable.get_924()
		+ taxable.get_336() + taxable.get_338() + taxable.get_340()
		+ taxable.get_466() + taxable.get_468() + taxable.get_476()
		+ taxable.get_480() + taxable.get_482() + taxable.get_402()
		+ taxable.get_404() + taxable.get_406() + taxable.get_408()
		+ taxable.get_414() + taxable.get_416() + taxable.get_426()
		+ taxable.get_502() + taxable.get_504() + taxable.get_506()
		+ taxable.get_508() + taxable.get_512() + taxable.get_516()
		+ taxable.get_518() + taxable.get_104() + taxable.get_122()
		+ taxable.get_106() + taxable.get_106() + taxable.get_110()
		+ taxable.get_102() + taxable.get_910() + taxable.get_112()
		+ taxable.get_114() + taxable.get_130() + taxable.get_144()
		+ taxable.get_146() + taxable.get_148() + taxable.get_142()
		+ taxable.get_702() + taxable.get_124() + taxable.get_126()
		+ taxable.get_152() + taxable.get_164() + taxable.get_166()
		+ taxable.get_160() + taxable.get_176() + taxable.get_132()
		+ taxable.get_134() + taxable.get_742() + taxable.get_292()
		+ taxable.get_390() + taxable.get_392() + taxable.get_464()
		+ taxable.get_472() + taxable.get_412() + taxable.get_412()
		+ taxable.get_422() + taxable.get_510() + taxable.get_514()
		+ taxable.get_296() + taxable.get_172() + taxable.get_172()
		+ taxable.get_396() + taxable.getAgrTotalWifeNetincome1()
		+ taxable.getAgrTotalWifeNetincome2()
		+ taxable.getAgrTotalWifeNetincome3()
		+ taxable.getAgrTotalWifeNetincome4();

	return total;
    }

    private float getWifeTotalAnnualStrictCost(E1objectiveSpending objSpend,
	    E1Id key) {
	Boolean isMarriage = IntToBoolean(dao.getE1DAO().getE1ById(key)
		.getMarriage());
	float total = 0f;

	if (objSpend != null)
	    total = +getWifeCarObjValue(objSpend)
		    + getBoatWifeObjValue(objSpend)
		    + objSpend.get_716()
		    + objSpend.get_766()
		    + objSpend.get_708()
		    + objSpend.get_720()
		    + objSpend.get_722()
		    + objSpend.get_724()
		    + objSpend.get_726()
		    + objSpend.get_728()
		    + lifeObjTax(isMarriage, IntToBoolean(dao.getE1DAO()
			    .getInfoDataByE1Id(key).get_014()));

	return total;
    }

    private float getPrincipalTotalAnnualStrictCost(
	    E1objectiveSpending objSpend, E1Id key) {

	float total = 0f;
	Boolean isMarriage = IntToBoolean(dao.getE1DAO().getE1ById(key)
		.getMarriage());

	if (objSpend != null)
	    total = getHousesObjValue(objSpend)
		    + getPrincipalCarObjValue(objSpend)
		    + getBoatPrincipalObjValue(objSpend)
		    + objSpend.get_715()
		    + objSpend.get_765()
		    + objSpend.get_707()
		    + objSpend.get_719()
		    + objSpend.get_721()
		    + objSpend.get_723()
		    + objSpend.get_725()
		    + objSpend.get_727()
		    + (objSpend.get_769() * 0.1f)
		    + objSpend.get_770()
		    + lifeObjTax(isMarriage, IntToBoolean(dao.getE1DAO()
			    .getInfoDataByE1Id(key).get_013()));

	return total;
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

    private float getBoatWifeObjValue(E1objectiveSpending objSpend) {
	return objSpend.get_712() + objSpend.get_714() + objSpend.get_732();
    }

    private float getBoatPrincipalObjValue(E1objectiveSpending objSpend) {
	// float fromBoats = 0f;
	// float percent = 0f;
	//
	// percent = objSpend.getPercentPrincipalCoOwner1()
	// + objSpend.getPercentWifeCoOwner1();
	//
	// fromBoats += calckBoatObj(
	// (Calendar.YEAR - objSpend.getFirstRegister1()),
	// objSpend.get_747(),
	// getBoatType(objSpend.getSailShip1(),
	// objSpend.getAccommodationSpace1()), percent);
	//
	// percent = 0f;
	// percent = objSpend.getPercentPrincipalCoOwner2()
	// + objSpend.getPercentWifeCoOwner2();
	// fromBoats += calckBoatObj(
	// (Calendar.YEAR - objSpend.getFirstRegister2()),
	// objSpend.get_748(),
	// getBoatType(objSpend.getSailShip2(),
	// objSpend.getAccommodationSpace2()), percent);

	return objSpend.get_711() + objSpend.get_713() + objSpend.get_731();
    }

    private float getWifeCarObjValue(E1objectiveSpending objSpend) {
	return objSpend.get_852() + objSpend.get_854() + objSpend.get_856()
		+ objSpend.get_858();
    }

    private float getPrincipalCarObjValue(E1objectiveSpending objSpend) {
	return objSpend.get_851() + objSpend.get_853() + objSpend.get_855()
		+ objSpend.get_857();
	// float fromCars = 0f;
	//
	// fromCars += calkCarObj(
	// (Calendar.getInstance().get(Calendar.YEAR - objSpend.get_775())),
	// objSpend.get_703(), objSpend.get_771());
	// fromCars += calkCarObj(
	// (Calendar.getInstance().get(Calendar.YEAR - objSpend.get_776())),
	// objSpend.get_704(), objSpend.get_772());
	// fromCars += calkCarObj(
	// (Calendar.getInstance().get(Calendar.YEAR - objSpend.get_777())),
	// objSpend.get_705(), objSpend.get_773());
	// fromCars += calkCarObj(
	// (Calendar.getInstance().get(Calendar.YEAR - objSpend.get_778())),
	// objSpend.get_706(), objSpend.get_774());

	// return fromCars;
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
	    return 2500 - 2500 * 0.3f;
	if (marriage)
	    return 2500;
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
}
