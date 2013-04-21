package gr.manousos.DAO;

import java.io.Serializable;

import gr.manousos.model.E1;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1infoData;
import gr.manousos.model.E1objectiveSpending;

public interface E1DAO extends GenericDAO<E1, Serializable> {
    public int submitE1(E1 e1Object);

    public E1 getE1ById(E1Id id);

    public E1objectiveSpending getObjectiveSpendingByE1Id(E1Id id);

    public E1infoData getE1InfoDataByE1Id(E1Id id);
}
