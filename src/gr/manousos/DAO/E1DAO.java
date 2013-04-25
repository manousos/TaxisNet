package gr.manousos.DAO;

import java.io.Serializable;

import gr.manousos.model.E1;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1expensesRemovedFromTotalIncome;
import gr.manousos.model.E1infoData;
import gr.manousos.model.E1objectiveSpending;
import gr.manousos.model.E1taxableIncomes;
import gr.manousos.model.E1reduceTax;
import gr.manousos.model.IncomeTax;

public interface E1DAO extends GenericDAO<E1, Serializable> {
    public int submitE1(E1 e1Object);

    public E1objectiveSpending getObjectiveSpendingByE1Id(E1Id id);

    public E1infoData getInfoDataByE1Id(E1Id id);

    public E1expensesRemovedFromTotalIncome getExpensesRemovedFromTotalIncomeByE1Id(
	    E1Id id);

    public E1taxableIncomes getTaxableIncomesByE1Id(E1Id id);

    public E1reduceTax getReduceTaxByE1Id(E1Id id);

    boolean submitIncomeTax(IncomeTax tax);
}
