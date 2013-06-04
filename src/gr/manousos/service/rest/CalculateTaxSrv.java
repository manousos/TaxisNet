package gr.manousos.service.rest;

import gr.manousos.DAO.DAOFactory;
import gr.manousos.model.E1;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1expensesRemovedFromTotalIncome;
import gr.manousos.model.E1infoData;
import gr.manousos.model.E1objectiveSpending;
import gr.manousos.model.E1reduceTax;
import gr.manousos.model.E1taxableIncomes;
import gr.manousos.model.IncomeTax;
import gr.manousos.model.IncomeTaxId;

import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author manousos
 * 
 */
@Path("/TaxCalcService")
public class CalculateTaxSrv {
    private static Log log = LogFactory.getLog(CalculateTaxSrv.class);

    Properties config = new Properties();
    DAOFactory dao = DAOFactory.instance(DAOFactory.HIBERNATE);

    @Path("/tax")
    @GET
    @Produces("application/json")
    public IncomeTax calculateAndSaveTax(@QueryParam("tId") int taxpayerId,
	    @QueryParam("year") int year) {

	// getE1
	// getObj()
	// getFinalIncome
	// GET CHILD'S & infand from table 3
	// IS RETIRED
	// βάζω περιορισμό στο αφορολόγιτο έως >0 && <= 12000€
	boolean is65Retired = false;
	boolean isMarriage = false;
	boolean isYoung = false;
	IncomeTax incomeTax = null;

	E1Id key = new E1Id(taxpayerId, year);
	E1 e1 = dao.getE1DAO().findById(key, true);

	if (e1 == null)
	    return incomeTax;
	
	isMarriage = e1.getMarriage() == 1;
	E1objectiveSpending objSpend = dao.getE1DAO()
		.getObjectiveSpendingByE1Id(key);
	E1expensesRemovedFromTotalIncome erfti = dao.getE1DAO()
		.getExpensesRemovedFromTotalIncomeByE1Id(key);
	E1infoData info = dao.getE1DAO().getInfoDataByE1Id(key);
	E1reduceTax reduceTax = dao.getE1DAO().getReduceTaxByE1Id(key);
	E1taxableIncomes taxable = dao.getE1DAO().getTaxableIncomesByE1Id(key);

	try {
	    if (info != null) {
		is65Retired = Utils.toSafeInteger(info.get_013()) == 1;
		isYoung = Utils.toSafeInteger(info.get_017()) == 1;
	    }
	    float principalTotalTaxableIncome = getPrincipalTaxableIncome(
		    erfti, taxable, objSpend, isMarriage, is65Retired);
	    float princepalNoTax = getPrincipalNoTax(reduceTax, isYoung,
		    is65Retired, principalTotalTaxableIncome);
	    if (info != null) {
		is65Retired = Utils.toSafeInteger(info.get_014()) == 1;
		isYoung = Utils.toSafeInteger(info.get_018()) == 1;
	    }
	    float wifeTotalTaxableIncome = getWifeTaxableIncome(erfti, taxable,
		    objSpend, isMarriage, is65Retired);
	    float wifeNoTax = getWifeNoTax(reduceTax, isYoung, is65Retired,
		    wifeTotalTaxableIncome);

	    // incomeTax = new IncomeTax(new IncomeTaxId(taxpayerId, year),
	    // getTax(principalTotalTaxableIncome, princepalNoTax),
	    // getTax(wifeTotalTaxableIncome, wifeNoTax));
	    // incomeTax.setE1(e1);
	    incomeTax = new IncomeTax(e1, getTax(principalTotalTaxableIncome,
		    princepalNoTax), getTax(wifeTotalTaxableIncome, wifeNoTax));
	    incomeTax.setId(new IncomeTaxId(taxpayerId, year));

	    dao.getE1DAO().submitIncomeTax(incomeTax);
	    incomeTax.setE1(null);
	} catch (Exception ex) {
	    log.error("Calculate Tax error :", ex);
	}
	return incomeTax;
    }

    @Path("/SaveTax")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean saveTax(IncomeTax tax) {
	E1 e1 = dao.getE1DAO().findById(
		new E1Id(tax.getId().getTaxPayerId(), tax.getId().getYear()),
		false);
	if (e1 != null)
	    return dao.getE1DAO().submitIncomeTax(tax);
	return false;
    }

    private float getTax(float totalIncome, float noTax) {
	if (noTax > 12000)
	    noTax = 12000;
	if (noTax > 5000 && totalIncome < 12000)
	    return (12000 - noTax) * 0.1f;

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
	// ?? ADDITIONAL VAT FROM HOUSES +1.5% AND +3% IF HOUSE>300m2
	return 0;
    }

    private float getPrincipalNoTax(E1reduceTax rt, boolean isYoung,
	    boolean is65Retired, float totalIncom) {
	float reduceTaxValue = 0f;

	if (totalIncom <= 9000f && is65Retired)
	    totalIncom += 9000f;
	if (totalIncom <= 9000f && isYoung)
	    totalIncom += 9000f;

	if (rt != null) {
	    if (Utils.toSafeInteger(rt.get_001()) == 1)
		reduceTaxValue += 2000f;
	    if (Utils.toSafeInteger(rt.get_003()) <= 2)
		reduceTaxValue += 2000 * Utils.toSafeInteger(rt.get_003());
	    else
		reduceTaxValue += 4000 + (Utils.toSafeInteger(rt.get_003()) - 2) * 3000;

	    reduceTaxValue += 2000 * Utils.toSafeInteger(rt.get_005());
	}

	return reduceTaxValue;
    }

    private float getWifeNoTax(E1reduceTax rt, boolean isYoung,
	    boolean is65Retired, float totalIncom) {
	float reduceTaxValue = 0f;

	if (totalIncom <= 9000f && is65Retired)
	    totalIncom += 9000f;
	if (totalIncom <= 9000f && isYoung)
	    totalIncom += 9000f;

	if (rt != null) {
	    if (Utils.toSafeInteger(rt.get_002()) == 1)
		reduceTaxValue += 2000f;
	    reduceTaxValue += Utils.toSafeInteger(rt.get_004())
		    + Utils.toSafeInteger(rt.get_006());
	}

	return reduceTaxValue;
    }

    private float getPrincipalTaxableIncome(
	    E1expensesRemovedFromTotalIncome erfti, E1taxableIncomes taxable,
	    E1objectiveSpending objSpend, boolean isMarriage,
	    boolean isRetired65) {

	float strict = 0f, removedIncomes = 0f, incomes = 0f;

	if (erfti != null)
	    removedIncomes = getPrincipalRemovedFromTotalIncome(erfti);
	if (taxable != null)
	    incomes = getPrincipalTotalTaxableIncome(taxable);
	if (objSpend != null)
	    strict = getPrincipalTotalAnnualStrictCost(objSpend, isMarriage,
		    isRetired65);
	float finalTaxable = 0;

	if ((incomes - removedIncomes) < strict)
	    finalTaxable = strict;
	else
	    finalTaxable = incomes - removedIncomes;

	// αποδείξεις
	if (erfti != null)
	    if (Utils.toSafeFloat(erfti.get_049()) <= 60000
		    && finalTaxable * 0.25 > Utils.toSafeFloat(erfti.get_049()))
		finalTaxable += finalTaxable * 0.1f;

	return finalTaxable;
    }

    private float getWifeTaxableIncome(E1expensesRemovedFromTotalIncome erfti,
	    E1taxableIncomes taxable, E1objectiveSpending objSpend,
	    boolean isMarriage, boolean isRetired65) {
	float removedIncomes = 0f, incomes = 0f, strict = 0f;

	if (erfti != null)
	    removedIncomes = getWifeRemovedFromTotalIncome(erfti);
	if (taxable != null)
	    incomes = getWifeTotalTaxableIncome(taxable);
	if (objSpend != null)
	    strict = getWifeTotalAnnualStrictCost(objSpend, isMarriage,
		    isRetired65);
	float finalTaxable = 0;

	if ((incomes - removedIncomes) < strict)
	    finalTaxable = strict;
	else
	    finalTaxable = incomes - removedIncomes;

	return finalTaxable;
    }

    private float getWifeRemovedFromTotalIncome(
	    E1expensesRemovedFromTotalIncome erfti) {

	float total = 0f;

	if (erfti != null)
	    total = Utils.toSafeFloat(erfti.get_076())
		    * 0.2f
		    + (Utils.toSafeFloat(erfti.get_052())
			    + Utils.toSafeFloat(erfti.get_054())
			    + Utils.toSafeFloat(erfti.get_058())
			    + Utils.toSafeFloat(erfti.get_060())
			    + Utils.toSafeFloat(erfti.get_062())
			    + Utils.toSafeFloat(erfti.get_064())
			    + Utils.toSafeFloat(erfti.get_080())
			    + Utils.toSafeFloat(erfti.get_088())
			    + Utils.toSafeFloat(erfti.get_090())
			    + Utils.toSafeFloat(erfti.get_812())
			    + +Utils.toSafeFloat(erfti.get_814()) + Utils
				.toSafeFloat(erfti.get_816())) * 0.1f;

	return total;
    }

    private float getPrincipalRemovedFromTotalIncome(
	    E1expensesRemovedFromTotalIncome erfti) {
	float total = 0f;
	if (erfti != null)
	    total = Utils.toSafeFloat(erfti.get_075())
		    * 0.2f
		    + (Utils.toSafeFloat(erfti.get_051())
			    + Utils.toSafeFloat(erfti.get_053())
			    + Utils.toSafeFloat(erfti.get_057())
			    + Utils.toSafeFloat(erfti.get_059())
			    + Utils.toSafeFloat(erfti.get_061())
			    + Utils.toSafeFloat(erfti.get_063())
			    + Utils.toSafeFloat(erfti.get_079())
			    + Utils.toSafeFloat(erfti.get_081())
			    + Utils.toSafeFloat(erfti.get_082())
			    + Utils.toSafeFloat(erfti.get_083())
			    + Utils.toSafeFloat(erfti.get_084())
			    + Utils.toSafeFloat(erfti.get_085())
			    + Utils.toSafeFloat(erfti.get_087())
			    + Utils.toSafeFloat(erfti.get_089())
			    + Utils.toSafeFloat(erfti.get_811())
			    + Utils.toSafeFloat(erfti.get_813())
			    + Utils.toSafeFloat(erfti.get_815())
			    + Utils.toSafeFloat(erfti.get_817())
			    + Utils.toSafeFloat(erfti.get_819())
			    + Utils.toSafeFloat(erfti.get_821()) + Utils
				.toSafeFloat(erfti.get_823())) * 0.1f;
	return total;

    }

    private float getPrincipalTotalTaxableIncome(E1taxableIncomes taxable) {
	float total = 0f;

	total = Utils.toSafeFloat(taxable.get_301())
		+ Utils.toSafeFloat(taxable.get_321())
		+ Utils.toSafeFloat(taxable.get_303())
		+ Utils.toSafeFloat(taxable.get_317())
		+ Utils.toSafeFloat(taxable.get_461())
		+ Utils.toSafeFloat(taxable.get_921())
		+ Utils.toSafeFloat(taxable.get_919())
		+ Utils.toSafeFloat(taxable.get_915())
		+ Utils.toSafeFloat(taxable.get_923())
		+ Utils.toSafeFloat(taxable.get_335())
		+ Utils.toSafeFloat(taxable.get_337())
		+ Utils.toSafeFloat(taxable.get_339())
		+ Utils.toSafeFloat(taxable.get_465())
		+ Utils.toSafeFloat(taxable.get_467())
		+ Utils.toSafeFloat(taxable.get_475())
		+ Utils.toSafeFloat(taxable.get_479())
		+ Utils.toSafeFloat(taxable.get_481())
		+ Utils.toSafeFloat(taxable.get_401())
		+ Utils.toSafeFloat(taxable.get_403())
		+ Utils.toSafeFloat(taxable.get_405())
		+ Utils.toSafeFloat(taxable.get_407())
		+ Utils.toSafeFloat(taxable.get_413())
		+ Utils.toSafeFloat(taxable.get_415())
		+ Utils.toSafeFloat(taxable.get_425())
		+ Utils.toSafeFloat(taxable.get_501())
		+ Utils.toSafeFloat(taxable.get_503())
		+ Utils.toSafeFloat(taxable.get_505())
		+ Utils.toSafeFloat(taxable.get_507())
		+ Utils.toSafeFloat(taxable.get_511())
		+ Utils.toSafeFloat(taxable.get_515())
		+ Utils.toSafeFloat(taxable.get_517())
		+ Utils.toSafeFloat(taxable.get_103())
		+ Utils.toSafeFloat(taxable.get_121())
		+ Utils.toSafeFloat(taxable.get_105())
		+ Utils.toSafeFloat(taxable.get_107())
		+ Utils.toSafeFloat(taxable.get_109())
		+ Utils.toSafeFloat(taxable.get_101())
		+ Utils.toSafeFloat(taxable.get_909())
		+ Utils.toSafeFloat(taxable.get_111())
		+ Utils.toSafeFloat(taxable.get_113())
		+ Utils.toSafeFloat(taxable.get_129())
		+ Utils.toSafeFloat(taxable.get_143())
		+ Utils.toSafeFloat(taxable.get_145())
		+ Utils.toSafeFloat(taxable.get_147())
		+ Utils.toSafeFloat(taxable.get_141())
		+ Utils.toSafeFloat(taxable.get_701())
		+ Utils.toSafeFloat(taxable.get_123())
		+ Utils.toSafeFloat(taxable.get_125())
		+ Utils.toSafeFloat(taxable.get_151())
		+ Utils.toSafeFloat(taxable.get_163())
		+ Utils.toSafeFloat(taxable.get_165())
		+ Utils.toSafeFloat(taxable.get_159())
		+ Utils.toSafeFloat(taxable.get_175())
		+ Utils.toSafeFloat(taxable.get_131())
		+ Utils.toSafeFloat(taxable.get_133())
		+ Utils.toSafeFloat(taxable.get_741())
		+ Utils.toSafeFloat(taxable.get_291())
		+ Utils.toSafeFloat(taxable.get_389())
		+ Utils.toSafeFloat(taxable.get_391())
		+ Utils.toSafeFloat(taxable.get_463())
		+ Utils.toSafeFloat(taxable.get_471())
		+ Utils.toSafeFloat(taxable.get_411())
		+ Utils.toSafeFloat(taxable.get_411())
		+ Utils.toSafeFloat(taxable.get_421())
		+ Utils.toSafeFloat(taxable.get_509())
		+ Utils.toSafeFloat(taxable.get_513())
		+ Utils.toSafeFloat(taxable.get_295())
		+ Utils.toSafeFloat(taxable.get_171())
		+ Utils.toSafeFloat(taxable.get_173())
		+ Utils.toSafeFloat(taxable.get_395())
		+ Utils.toSafeFloat(taxable.getAgrTotalNetincome1())
		+ Utils.toSafeFloat(taxable.getAgrTotalNetincome2())
		+ Utils.toSafeFloat(taxable.getAgrTotalNetincome3())
		+ Utils.toSafeFloat(taxable.getAgrTotalNetincome4());

	return total;
    }

    private float getWifeTotalTaxableIncome(E1taxableIncomes taxable) {
	float total = 0f;

	if (taxable != null)
	    total = Utils.toSafeFloat(taxable.get_302())
		    + Utils.toSafeFloat(taxable.get_322())
		    + Utils.toSafeFloat(taxable.get_304())
		    + Utils.toSafeFloat(taxable.get_318())
		    + Utils.toSafeFloat(taxable.get_462())
		    + Utils.toSafeFloat(taxable.get_922())
		    + Utils.toSafeFloat(taxable.get_920())
		    + Utils.toSafeFloat(taxable.get_916())
		    + Utils.toSafeFloat(taxable.get_924())
		    + Utils.toSafeFloat(taxable.get_336())
		    + Utils.toSafeFloat(taxable.get_338())
		    + Utils.toSafeFloat(taxable.get_340())
		    + Utils.toSafeFloat(taxable.get_466())
		    + Utils.toSafeFloat(taxable.get_468())
		    + Utils.toSafeFloat(taxable.get_476())
		    + Utils.toSafeFloat(taxable.get_480())
		    + Utils.toSafeFloat(taxable.get_482())
		    + Utils.toSafeFloat(taxable.get_402())
		    + Utils.toSafeFloat(taxable.get_404())
		    + Utils.toSafeFloat(taxable.get_406())
		    + Utils.toSafeFloat(taxable.get_408())
		    + Utils.toSafeFloat(taxable.get_414())
		    + Utils.toSafeFloat(taxable.get_416())
		    + Utils.toSafeFloat(taxable.get_426())
		    + Utils.toSafeFloat(taxable.get_502())
		    + Utils.toSafeFloat(taxable.get_504())
		    + Utils.toSafeFloat(taxable.get_506())
		    + Utils.toSafeFloat(taxable.get_508())
		    + Utils.toSafeFloat(taxable.get_512())
		    + Utils.toSafeFloat(taxable.get_516())
		    + Utils.toSafeFloat(taxable.get_518())
		    + Utils.toSafeFloat(taxable.get_104())
		    + Utils.toSafeFloat(taxable.get_122())
		    + Utils.toSafeFloat(taxable.get_106())
		    + Utils.toSafeFloat(taxable.get_106())
		    + Utils.toSafeFloat(taxable.get_110())
		    + Utils.toSafeFloat(taxable.get_102())
		    + Utils.toSafeFloat(taxable.get_910())
		    + Utils.toSafeFloat(taxable.get_112())
		    + Utils.toSafeFloat(taxable.get_114())
		    + Utils.toSafeFloat(taxable.get_130())
		    + Utils.toSafeFloat(taxable.get_144())
		    + Utils.toSafeFloat(taxable.get_146())
		    + Utils.toSafeFloat(taxable.get_148())
		    + Utils.toSafeFloat(taxable.get_142())
		    + Utils.toSafeFloat(taxable.get_702())
		    + Utils.toSafeFloat(taxable.get_124())
		    + Utils.toSafeFloat(taxable.get_126())
		    + Utils.toSafeFloat(taxable.get_152())
		    + Utils.toSafeFloat(taxable.get_164())
		    + Utils.toSafeFloat(taxable.get_166())
		    + Utils.toSafeFloat(taxable.get_160())
		    + Utils.toSafeFloat(taxable.get_176())
		    + Utils.toSafeFloat(taxable.get_132())
		    + Utils.toSafeFloat(taxable.get_134())
		    + Utils.toSafeFloat(taxable.get_742())
		    + Utils.toSafeFloat(taxable.get_292())
		    + Utils.toSafeFloat(taxable.get_390())
		    + Utils.toSafeFloat(taxable.get_392())
		    + Utils.toSafeFloat(taxable.get_464())
		    + Utils.toSafeFloat(taxable.get_472())
		    + Utils.toSafeFloat(taxable.get_412())
		    + Utils.toSafeFloat(taxable.get_412())
		    + Utils.toSafeFloat(taxable.get_422())
		    + Utils.toSafeFloat(taxable.get_510())
		    + Utils.toSafeFloat(taxable.get_514())
		    + Utils.toSafeFloat(taxable.get_296())
		    + Utils.toSafeFloat(taxable.get_172())
		    + Utils.toSafeFloat(taxable.get_172())
		    + Utils.toSafeFloat(taxable.get_396())
		    + Utils.toSafeFloat(taxable.getAgrTotalWifeNetincome1())
		    + Utils.toSafeFloat(taxable.getAgrTotalWifeNetincome2())
		    + Utils.toSafeFloat(taxable.getAgrTotalWifeNetincome3())
		    + Utils.toSafeFloat(taxable.getAgrTotalWifeNetincome4());

	return total;
    }

    private float getWifeTotalAnnualStrictCost(E1objectiveSpending objSpend,
	    boolean isMarriage, boolean isRetired65) {

	float total = 0f;

	if (objSpend != null)
	    total = getWifeHousesObjValue(objSpend)
		    + getWifeCarObjValue(objSpend)
		    + getBoatWifeObjValue(objSpend)
		    + Utils.toSafeFloat(objSpend.get_716())
		    + Utils.toSafeFloat(objSpend.get_766())
		    + Utils.toSafeFloat(objSpend.get_708())
		    + Utils.toSafeFloat(objSpend.get_720())
		    + Utils.toSafeFloat(objSpend.get_722())
		    + Utils.toSafeFloat(objSpend.get_724())
		    + Utils.toSafeFloat(objSpend.get_726())
		    + Utils.toSafeFloat(objSpend.get_728())
		    + lifeObjTax(isMarriage, isRetired65);

	if (isRetired65)
	    return total * 0.3f;

	return total;
    }

    private float getPrincipalTotalAnnualStrictCost(
	    E1objectiveSpending objSpend, boolean isMarriage,
	    boolean isRetired65) {

	float total = 0f;
	if (objSpend != null)
	    total = getPrincipalHousesObjValue(objSpend)
		    + getPrincipalCarObjValue(objSpend)
		    + getBoatPrincipalObjValue(objSpend)
		    + Utils.toSafeFloat(objSpend.get_715())
		    + Utils.toSafeFloat(objSpend.get_765())
		    + Utils.toSafeFloat(objSpend.get_707())
		    + Utils.toSafeFloat(objSpend.get_719())
		    + Utils.toSafeFloat(objSpend.get_721())
		    + Utils.toSafeFloat(objSpend.get_723())
		    + Utils.toSafeFloat(objSpend.get_725())
		    + Utils.toSafeFloat(objSpend.get_727())
		    + (Utils.toSafeFloat(objSpend.get_769()) * 0.1f)
		    + Utils.toSafeFloat(objSpend.get_770())
		    + lifeObjTax(isMarriage, isRetired65);

	if (isRetired65)
	    return total * 0.3f;

	return total;
    }

    private float getPoolObjValue(E1objectiveSpending objSpend) {
	float fromPools = 0f;
	float percent = 0f;

	percent = objSpend.getPoolPrincipalCoOwnerIndoor()
		+ objSpend.getPoolWifeCoOwnerIndoor();
	if (Utils.toSafeFloat(objSpend.get_767()) > 0)
	    fromPools += calckPoolObj(false,
		    Utils.toSafeFloat(objSpend.get_767()), percent);

	percent = 0f;
	percent = objSpend.getPoolPrincipalCoOwnerOutdoor()
		+ objSpend.getPoolWifeCoOwnerOutdoor();
	if (Utils.toSafeFloat(objSpend.get_768()) > 0)
	    fromPools += calckPoolObj(true,
		    Utils.toSafeFloat(objSpend.get_768()), percent);

	return fromPools;
    }

    private float getBoatWifeObjValue(E1objectiveSpending objSpend) {
	return Utils.toSafeFloat(objSpend.get_712())
		+ Utils.toSafeFloat(objSpend.get_714())
		+ Utils.toSafeFloat(objSpend.get_732());
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

	return Utils.toSafeFloat(objSpend.get_711())
		+ Utils.toSafeFloat(objSpend.get_713())
		+ Utils.toSafeFloat(objSpend.get_731());
    }

    private float getWifeCarObjValue(E1objectiveSpending objSpend) {
	return Utils.toSafeFloat(objSpend.get_852())
		+ Utils.toSafeFloat(objSpend.get_854())
		+ Utils.toSafeFloat(objSpend.get_856())
		+ Utils.toSafeFloat(objSpend.get_858());
    }

    private float getPrincipalCarObjValue(E1objectiveSpending objSpend) {
	return Utils.toSafeFloat(objSpend.get_851())
		+ Utils.toSafeFloat(objSpend.get_853())
		+ Utils.toSafeFloat(objSpend.get_855())
		+ Utils.toSafeFloat(objSpend.get_857());
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

    private float getPrincipalHousesObjValue(E1objectiveSpending objSpend) {

	float fromHouses = 0f;

	if (Utils.toSafeFloat(objSpend.get_213()) > 0) {
	    fromHouses += calckPrimaryHouseObj(
		    Utils.toSafeFloat(objSpend.get_211()),
		    Utils.toSafeFloat(objSpend.get_216()),
		    Utils.toSafeInteger(objSpend.get_240()) == 1,
		    Utils.toSafeFloat(objSpend.get_213()));
	    fromHouses += calckHelpAreaObj(
		    Utils.toSafeFloat(objSpend.get_212()),
		    Utils.toSafeFloat(objSpend.get_216()),
		    Utils.toSafeInteger(objSpend.get_240()) == 1);
	}
	if (Utils.toSafeFloat(objSpend.get_220()) > 0) {
	    fromHouses += calckOtherHouseObj(
		    Utils.toSafeFloat(objSpend.get_218()),
		    Utils.toSafeFloat(objSpend.get_223()),
		    Utils.toSafeInteger(objSpend.get_241()) == 1,
		    Utils.toSafeFloat(objSpend.get_220()));
	    fromHouses += calckOtherHelpAreaObj(
		    Utils.toSafeFloat(objSpend.get_219()),
		    Utils.toSafeFloat(objSpend.get_223()),
		    Utils.toSafeInteger(objSpend.get_241()) == 1);
	}
	if (Utils.toSafeFloat(objSpend.get_227()) > 0) {
	    fromHouses += calckOtherHouseObj(
		    Utils.toSafeFloat(objSpend.get_225()),
		    Utils.toSafeFloat(objSpend.get_230()),
		    Utils.toSafeInteger(objSpend.get_242()) == 1,
		    Utils.toSafeFloat(objSpend.get_227()));
	    fromHouses += calckOtherHelpAreaObj(
		    Utils.toSafeFloat(objSpend.get_226()),
		    Utils.toSafeFloat(objSpend.get_230()),
		    Utils.toSafeInteger(objSpend.get_242()) == 1);

	    fromHouses += Utils.toSafeFloat(objSpend.get_707());
	}
	return fromHouses;
    }

    private float getWifeHousesObjValue(E1objectiveSpending objSpend) {

	float fromHouses = 0f;
	if (Utils.toSafeFloat(objSpend.get_214()) > 0) {
	    fromHouses += calckPrimaryHouseObj(
		    Utils.toSafeFloat(objSpend.get_211()),
		    Utils.toSafeFloat(objSpend.get_216()),
		    Utils.toSafeInteger(objSpend.get_240()) == 1,
		    Utils.toSafeFloat(objSpend.get_214()));
	    fromHouses += calckHelpAreaObj(
		    Utils.toSafeFloat(objSpend.get_212()),
		    Utils.toSafeFloat(objSpend.get_216()),
		    Utils.toSafeInteger(objSpend.get_240()) == 1);
	}
	if (Utils.toSafeFloat(objSpend.get_221()) > 0) {
	    fromHouses += calckOtherHouseObj(
		    Utils.toSafeFloat(objSpend.get_218()),
		    Utils.toSafeFloat(objSpend.get_223()),
		    Utils.toSafeInteger(objSpend.get_241()) == 1,
		    Utils.toSafeFloat(objSpend.get_221()));
	    fromHouses += calckOtherHelpAreaObj(
		    Utils.toSafeFloat(objSpend.get_219()),
		    Utils.toSafeFloat(objSpend.get_223()),
		    Utils.toSafeInteger(objSpend.get_241()) == 1);
	}
	if (Utils.toSafeFloat(objSpend.get_228()) > 0) {
	    fromHouses += calckOtherHouseObj(
		    Utils.toSafeFloat(objSpend.get_225()),
		    Utils.toSafeFloat(objSpend.get_230()),
		    Utils.toSafeInteger(objSpend.get_242()) == 1,
		    Utils.toSafeFloat(objSpend.get_228()));
	    fromHouses += calckOtherHelpAreaObj(
		    Utils.toSafeFloat(objSpend.get_226()),
		    Utils.toSafeFloat(objSpend.get_230()),
		    Utils.toSafeInteger(objSpend.get_242()) == 1);
	}

	fromHouses += Utils.toSafeFloat(objSpend.get_708());

	return fromHouses;
    }

    private float addOwnerPercent(float value, float percent) {
	return value * percent;
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
