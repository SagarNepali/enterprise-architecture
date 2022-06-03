package edu.mum.cs544;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class StudentDAO {

	@PersistenceContext
	EntityManager em;

	public Student load(long studentid) {

		EntityGraph<Student> studentEntityGraph = em.createEntityGraph(Student.class);
		studentEntityGraph.addAttributeNodes("courselist");
		Map<String,Object> map = new HashMap<>();
		map.put("javax.persistence.fetchgraph",studentEntityGraph);
		return em.find(Student.class,studentid,map);
//		return em.find(Student.class,studentid);
	}
}
