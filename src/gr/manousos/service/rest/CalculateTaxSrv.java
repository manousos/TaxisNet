package gr.manousos.service.rest;

public class CalculateTaxSrv {

    public float tax(float totalIncome) {

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
}
