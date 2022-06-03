package edu.mum.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentService {

	@Autowired
	private StudentDAO studentdao;

	public Student getStudent(long studentid) {

		Student student = studentdao.load(studentid);
		return student ;
	}
}
