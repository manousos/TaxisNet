package gr.manousos.DAO.Hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.transaction.Transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;

import gr.manousos.DAO.E1DAO;
import gr.manousos.model.Contact;
import gr.manousos.model.E1;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1relatePersons;
import gr.manousos.model.E1relatePersonsId;
import gr.manousos.model.RelatePerson;

public class E1Hibernate extends GenericDAOImpl<E1, Serializable> implements
	E1DAO {

    private static Log log = LogFactory.getLog(E1Hibernate.class);

    @Override
    public E1 findById(Serializable id, boolean lock) {
	// TODO Auto-generated method stub
	return null;
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
	    /*
	     * Set<RelatePerson> tmpRelPers = entity.getRelatePersons();
	     * getSession().beginTransaction(); for (RelatePerson rp :
	     * tmpRelPers) { getSession().save(rp.getContact());
	     * rp.setContact(null); getSession().save(rp); }
	     * getSession().getTransaction().commit();
	     * getSession().beginTransaction(); entity.setRelatePersons(null);
	     * getSession().save(entity);
	     * getSession().getTransaction().commit();
	     */
	    super.makePersistent(entity);
	    getSession().getTransaction().commit();
	    /*
	     * super.makePersistent(entity); if (saveRelatePerson(tmpRelPers,
	     * entity)) getSession().getTransaction().commit(); else
	     * getSession().getTransaction().rollback();
	     */
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
	// TODO Auto-generated method stub
	return null;
    }

}
