package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Employee;

public class Test {

	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessFact = cfg.buildSessionFactory();
		Session sess = sessFact.openSession();
		Employee obj1 = new Employee("Ujjwal", "IT", "CEO", 5000000);
		Employee obj2 = new Employee("Suraj", "Sports", "Batsman", 90000);
		sess.beginTransaction();
		sess.persist(obj1);
		sess.persist(obj2);
		sess.getTransaction().commit();
		//Way 2 :No need to first create and then commit tansation.
	}

}