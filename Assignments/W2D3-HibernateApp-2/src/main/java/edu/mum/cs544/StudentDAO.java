package edu.mum.cs544;

import edu.mum.cs544.helper.EntityManagerHelper;
import org.hibernate.bytecode.enhance.spi.interceptor.EnhancementHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentDAO {

	public StudentDAO() {

	}

	public Student load(long studentid) {
		EntityManager em = EntityManagerHelper.getCurrent();
		TypedQuery<Student> q = em.createQuery("SELECT s from Student s " +
				" JOIN FETCH s.courselist where s.studentid =:id",Student.class).setParameter("id",studentid);
		return q.getSingleResult();
	}
}
