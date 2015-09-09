package com.jason.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.jason.hibernate.model.Employee;
import com.jason.hibernate.util.HibernateUtil;

public class HibernateJasonTest {
	public static void main(String[] args) {

		System.out.println("Temp Dir:" + System.getProperty("java.io.tmpdir"));

		// Initialize Sessions
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled=" + stats.isStatisticsEnabled());

		Session session = sessionFactory.openSession();
		Session otherSession = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Transaction otherTransaction = otherSession.beginTransaction();

	       Employee emp = (Employee) session.load(Employee.class, 3L);
	       printData(emp, stats, 1);
		
		
		// Release resources
		transaction.commit();
		otherTransaction.commit();
		sessionFactory.close();
	}

	private static void printStats(Statistics stats, int i) {
		System.out.println("***** " + i + " *****");
		System.out.println("Fetch Count=" + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
	}

	private static void printData(Employee emp, Statistics stats, int count) {
		System.out.println(count + ":: Name=" + emp.getName() + ", Zipcode=" + emp.getAddress().getZipcode());
		printStats(stats, count);
	}
}
