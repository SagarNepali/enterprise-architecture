package edu.mum.cs544;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

public class StudentDAO {

    public Student load(long studentid) {
        EntityManager em = EntityManagerHelper.getCurrent();
        EntityGraph<Student> graph = em.createEntityGraph(Student.class);
        graph.addAttributeNodes("courselist");
        Map<String, Object> hints = new HashMap<String,Object>();
        hints.put("javax.persistence.fetchgraph", graph); 
        return em.find(Student.class, studentid, hints);
    }
}
