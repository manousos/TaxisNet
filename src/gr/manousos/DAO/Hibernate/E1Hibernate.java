package gr.manousos.DAO.Hibernate;

import gr.manousos.DAO.E1DAO;
import gr.manousos.model.E1;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1objectiveSpending;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class E1Hibernate extends GenericDAOImpl<E1, Serializable> implements
	E1DAO {

    private static Log log = LogFactory.getLog(E1Hibernate.class);

    @Override
    public E1 findById(Serializable id, boolean lock) {
	E1 e1 = null;
	try {
	    getSession().beginTransaction();
	    e1 = (E1) super.findById(id, lock);
	    getSession().getTransaction().commit();
	} catch (Exception ex) {
	    log.error("getE1ByID Error= " + ex.toString());
	    getSession().getTransaction().rollback();
	}

	return e1;
    }

    @Override
    public List<E1> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<E1> findByExample(E1 exampleInstance, String... excludeProperty) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public E1 makePersistent(E1 entity) {
	try {
	    getSession().beginTransaction();

	    super.makePersistent(entity);
	    getSession().getTransaction().commit();

	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 makePersistent error. " + e.getMessage(), e);
	} finally {
	    if (getSession().isOpen())
		getSession().close();
	}
	return entity;
    }

    @Override
    public void makeTransient(E1 entity) {
	// TODO Auto-generated method stub

    }

    @Override
    public void flush() {
	// TODO Auto-generated method stub

    }

    @Override
    public void clear() {
	// TODO Auto-generated method stub

    }

    @Override
    public int submitE1(E1 e1) {
	try {
	    getSession().beginTransaction();

	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 Hibernate Submit error ", e);
	}
	return 0;
    }

    @Override
    public E1 getE1ById(E1Id id) {
	return super.findById(id, false);
    }

    @Override
    public E1objectiveSpending getObjectiveSpendingByE1Id(E1Id id) {
	E1objectiveSpending objSpend = null;
	try {
	    getSession().beginTransaction();
	    String q = "select e1.e1objectiveSpending from E1 e1 where e1.id.year=:year and e1.id.taxpayerId=:taxpayerId";
	    Query query = getSession().createQuery(q);
	    query.setInteger("taxpayerId", id.getTaxpayerId());
	    query.setInteger("year", id.getYear());

	    Object rec = query.uniqueResult();
	    if (rec != null)
		objSpend = (E1objectiveSpending) rec;

	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 Hibernate getObjectiveSpendingByE1Id error ", e);
	}
	
	return objSpend;
    }
}
