package gr.manousos.DAO;

import java.io.Serializable;

import gr.manousos.model.E1;
import gr.manousos.model.E1Id;

public interface E1DAO extends GenericDAO<E1, Serializable> {
	public int submitE1(E1 e1Object);

	public E1 getE1ById(E1Id id);
}