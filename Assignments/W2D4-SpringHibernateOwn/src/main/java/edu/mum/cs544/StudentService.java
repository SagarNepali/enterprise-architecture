package edu.mum.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;


@Service
public class StudentService {

	@Autowired
	private StudentDAO studentdao;

	public Student getStudent(long studentid) {

		Student student = studentdao.load(studentid);
		return student ;
	}
}
