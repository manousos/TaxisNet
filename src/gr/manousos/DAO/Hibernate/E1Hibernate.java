package gr.manousos.DAO.Hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.transaction.Transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;

import gr.manousos.DAO.E1DAO;
import gr.manousos.model.E1;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1relatePersons;
import gr.manousos.model.E1relatePersonsId;
import gr.manousos.model.RelatePerson;

public class E1Hibernate extends GenericDAOImpl<E1, Serializable> implements
	E1DAO {

    private static Log log = LogFactory.getLog(TaxpayerHibernate.class);

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
	    
	    Set<RelatePerson> tmpRelPers = entity.getRelatePersons();
	    entity.setRelatePersons(null);

	    super.makePersistent(entity);
	    getSession().getTransaction().commit();
	    
	    saveRelatePeron(tmpRelPers);
	} catch (Exception e) {
	    log.error("E1 makePersistent error. " + e.getMessage(), e);
	    getSession().getTransaction().rollback();
	} finally {
	    if (getSession().isOpen())
		getSession().close();
	}
	return entity;
    }

    private boolean saveRelatePeron(Set<RelatePerson> RelPers) {
	// E1 e1 = new E1();
	// e1.setId(key);
	// e1.setRelatePersons(RelPers);

	RelatePerson wife = null;
	RelatePerson delegate = null;

	for (RelatePerson rp : RelPers) {
	    if (rp.getType() == 1)
		wife = rp;
	    if (rp.getType() == 2)
		delegate = rp;
	}
	E1relatePersons rlpH = new E1relatePersons();
	E1relatePersons rlpD = new E1relatePersons();

	if (wife != null)
	    rlpH.setRelatePerson(wife);	
	if (delegate != null)
	    rlpD.setRelatePerson(delegate);

	try {
	    getSession().beginTransaction();
	    getSession().saveOrUpdate(rlpH);
	    getSession().saveOrUpdate(rlpD);
	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    log.error("E1 RelatePersons persist error. " + e.getMessage(), e);
	    getSession().getTransaction().rollback();
	} finally {
	    if (getSession().isOpen())
		getSession().close();
	}
	return false;
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
