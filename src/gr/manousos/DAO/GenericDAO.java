package gr.manousos.DAO;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
	T findById(ID id, boolean lock);

	List<T> findAll();

	List<T> findByExample(T exampleInstance, String... excludeProperty);

	T makePersistent(T entity);

	void makeTransient(T entity);

	/**
	 * Affects every managed instance in the current persistence context!
	 */
	void flush();

	/**
	 * Affects every managed instance in the current persistence context!
	 */
	void clear();
}
