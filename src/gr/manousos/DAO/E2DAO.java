package gr.manousos.DAO;

import java.io.Serializable;

import gr.manousos.model.E2;
import gr.manousos.model.E2Id;

public interface E2DAO extends GenericDAO<E2, Serializable> {

	public int submitE2(E2 e2Object);

	public E2 getE2ById(E2Id id);
}
