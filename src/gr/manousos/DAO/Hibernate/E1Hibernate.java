package gr.manousos.DAO.Hibernate;

import gr.manousos.DAO.E1DAO;
import gr.manousos.model.E1;
import gr.manousos.model.E1Id;
import gr.manousos.model.E1expensesRemovedFromTotalIncome;
import gr.manousos.model.E1infoData;
import gr.manousos.model.E1objectiveSpending;
import gr.manousos.model.E1reduceTax;
import gr.manousos.model.E1taxableIncomes;
import gr.manousos.model.IncomeTax;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
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
	    e1.setIncomeTax(null); // Error, maybe wrong relation
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

	    entity.getE1dataFromTaxPayerFolder().setE1(entity);
	    entity.getE1expensesRemovedFromTotalIncome().setE1(entity);
	    entity.getE1incomesReduceTaxes().setE1(entity);
	    entity.getE1infoData().setE1(entity);
	    entity.getE1nauticalincomes().setE1(entity);
	    entity.getE1objectiveSpending().setE1(entity);
	    entity.getE1personDataBorneTaxpayer().setE1(entity);
	    entity.getE1prepaidTaxes().setE1(entity);
	    entity.getE1reduceTax().setE1(entity);
	    entity.getE1taxableIncomes().setE1(entity);
	    entity.getE1taxPayerBankAccount().setE1(entity);

	    super.makePersistent(entity);
	    getSession().getTransaction().commit();

	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 makePersistent error. " + e.getMessage(), e);
	    throw new HibernateException(e);
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
    public E1objectiveSpending getObjectiveSpendingByE1Id(E1Id id) {
	E1objectiveSpending objSpend = null;
	try {
	    getSession().beginTransaction();

	    Criteria cr = getSession()
		    .createCriteria(E1objectiveSpending.class);
	    cr.add(Restrictions.eq("id.tid", id.getTaxpayerId()));
	    cr.add(Restrictions.eq("id.year", id.getYear()));

	    objSpend = (E1objectiveSpending) cr.uniqueResult();

	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 Hibernate getObjectiveSpendingByE1Id error ", e);
	}

	return objSpend;
    }

    @Override
    public E1infoData getInfoDataByE1Id(E1Id id) {
	E1infoData infoData = null;
	try {
	    getSession().beginTransaction();
	    String q = "from E1infoData e1inf  where e1inf.id.year=:year and e1inf.id.tid=:tid";
	    Query query = getSession().createQuery(q);
	    query.setInteger("tid", id.getTaxpayerId());
	    query.setInteger("year", id.getYear());

	    Object rec = query.uniqueResult();
	    if (rec != null)
		infoData = (E1infoData) rec;

	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 Hibernate getE1InfoDataByE1Id error ", e);
	}
	return infoData;
    }

    @Override
    public E1expensesRemovedFromTotalIncome getExpensesRemovedFromTotalIncomeByE1Id(
	    E1Id id) {
	E1expensesRemovedFromTotalIncome expensesRemovedFromTotalIncome = null;
	try {
	    getSession().beginTransaction();
	    String q = "from E1expensesRemovedFromTotalIncome e1erfti where e1erfti.id.year=:year and e1erfti.id.tid=:taxpayerId";
	    Query query = getSession().createQuery(q);
	    query.setInteger("taxpayerId", id.getTaxpayerId());
	    query.setInteger("year", id.getYear());

	    Object rec = query.uniqueResult();
	    if (rec != null)
		expensesRemovedFromTotalIncome = (E1expensesRemovedFromTotalIncome) rec;

	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error(
		    "E1 Hibernate getE1expensesRemovedFromTotalIncomeByE1Id error ",
		    e);
	}
	return expensesRemovedFromTotalIncome;
    }

    @Override
    public E1taxableIncomes getTaxableIncomesByE1Id(E1Id id) {
	E1taxableIncomes e1taxableIncomes = null;

	try {
	    getSession().beginTransaction();
	    String q = "from E1taxableIncomes e1ti where e1ti.id.year=:year and e1ti.id.tid=:taxpayerId";
	    Query query = getSession().createQuery(q);
	    query.setInteger("taxpayerId", id.getTaxpayerId());
	    query.setInteger("year", id.getYear());

	    Object rec = query.uniqueResult();
	    if (rec != null)
		e1taxableIncomes = (E1taxableIncomes) rec;

	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 Hibernate getTaxableIncomesByE1Id error ", e);
	}

	return e1taxableIncomes;
    }

    @Override
    public E1reduceTax getReduceTaxByE1Id(E1Id id) {
	E1reduceTax e1ReduceTax = null;
	try {
	    getSession().beginTransaction();
	    String q = "from E1reduceTax e1rt where e1rt.id.year=:year and e1rt.id.tid=:taxpayerId";
	    Query query = getSession().createQuery(q);
	    query.setInteger("taxpayerId", id.getTaxpayerId());
	    query.setInteger("year", id.getYear());

	    Object rec = query.uniqueResult();
	    if (rec != null)
		e1ReduceTax = (E1reduceTax) rec;

	    getSession().getTransaction().commit();
	} catch (Exception e) {
	    getSession().getTransaction().rollback();
	    log.error("E1 Hibernate getReduceTaxByE1Id error ", e);
	}
	return e1ReduceTax;
    }

    @Override
    public boolean submitIncomeTax(IncomeTax tax) {
	boolean saved = false;
	try {
	    getSession().beginTransaction();
	    getSession().saveOrUpdate(tax);
	    getSession().getTransaction().commit();
	    saved = true;

	    // } catch (HibernateException he) {
	    // throw he;
	} catch (Exception ex) {
	    log.error("Taxpayer makePersistent error ", ex);
	    getSession().getTransaction().rollback();
	    throw new HibernateException(ex.getMessage());
	} finally {
	    if (getSession().isOpen())
		getSession().close();
	}

	return saved;
    }
}
