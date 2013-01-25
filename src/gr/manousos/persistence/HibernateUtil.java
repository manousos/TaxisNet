package gr.manousos.persistence;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {
	private static Log log = LogFactory.getLog(HibernateUtil.class);

	private static Configuration configuration;
	// private static SessionFactory sessionFactory;
	/*
	 * static { // Create the initial SessionFactory from the default
	 * configuration // files try { log.debug("Initializing Hibernate");
	 * 
	 * // Read hibernate.properties, if present configuration = new
	 * Configuration(); // Use annotations: configuration = new
	 * AnnotationConfiguration();
	 * 
	 * // Read hibernate.cfg.xml (has to be present) configuration.configure();
	 * 
	 * // Build and store (either in JNDI or static variable)
	 * rebuildSessionFactory(configuration);
	 * 
	 * log.debug("Hibernate initialized, call HibernateUtil.getSessionFactory()")
	 * ; } catch (Throwable ex) { // We have to catch Throwable, otherwise we
	 * will miss // NoClassDefFoundError and other subclasses of Error
	 * log.error("Building SessionFactory failed." + ex.getMessage(), ex); throw
	 * new ExceptionInInitializerError(ex); } }
	 */
	/**
	 * Returns the Hibernate configuration that was used to build the
	 * SessionFactory.
	 * 
	 * @return Configuration
	 */
	/*
	 * public static Configuration getConfiguration() { return configuration; }
	 */
	/**
	 * Returns the global SessionFactory either from a static variable or a JNDI
	 * lookup.
	 * 
	 * @return SessionFactory
	 */
	/*
	 * public static SessionFactory getSessionFactory() { String sfName =
	 * configuration .getProperty(Environment.SESSION_FACTORY_NAME); if (sfName
	 * != null) { log.debug("Looking up SessionFactory in JNDI"); try { return
	 * (SessionFactory) new InitialContext().lookup(sfName); } catch
	 * (NamingException ex) { throw new RuntimeException(ex); } } else if
	 * (sessionFactory == null) { rebuildSessionFactory(); } return
	 * sessionFactory; }
	 */
	/**
	 * Closes the current SessionFactory and releases all resources.
	 * <p>
	 * The only other method that can be called on HibernateUtil after this one
	 * is rebuildSessionFactory(Configuration).
	 */
	/*
	 * public static void shutdown() { log.debug("Shutting down Hibernate"); //
	 * Close caches and connection pools getSessionFactory().close();
	 * 
	 * // Clear static variables sessionFactory = null; }
	 */
	/**
	 * Rebuild the SessionFactory with the static Configuration.
	 * <p>
	 * Note that this method should only be used with static SessionFactory
	 * management, not with JNDI or any other external registry. This method
	 * also closes the old static variable SessionFactory before, if it is still
	 * open.
	 */
	/*
	 * public static void rebuildSessionFactory() {
	 * log.debug("Using current Configuration to rebuild SessionFactory");
	 * rebuildSessionFactory(configuration); }
	 */
	/**
	 * Rebuild the SessionFactory with the given Hibernate Configuration.
	 * <p>
	 * HibernateUtil does not configure() the given Configuration object, it
	 * directly calls buildSessionFactory(). This method also closes the old
	 * static variable SessionFactory before, if it is still open.
	 * 
	 * @param cfg
	 */
	/*
	 * public static void rebuildSessionFactory(Configuration cfg) {
	 * log.debug("Rebuilding the SessionFactory from given Configuration"); if
	 * (sessionFactory != null && !sessionFactory.isClosed())
	 * sessionFactory.close(); if
	 * (cfg.getProperty(Environment.SESSION_FACTORY_NAME) != null) {
	 * log.debug("Managing SessionFactory in JNDI"); cfg.buildSessionFactory();
	 * } else { log.debug("Holding SessionFactory in static variable");
	 * sessionFactory = cfg.buildSessionFactory(); } configuration = cfg; }
	 * 
	 * /** Simplest solution
	 */

	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
