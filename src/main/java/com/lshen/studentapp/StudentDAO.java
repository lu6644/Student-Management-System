package com.lshen.studentapp;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	private static List<Student> students = new ArrayList<Student>();
	private static int idCount = 0;
	public Student create(Student student) {
	students.add(student);
	student.setId(++idCount);
	return student;
	}
	public List<Student> readAll() {
	return students;
	}
	public Student read(int id) {
	return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
	}
	public Student update(int id, Student student) {
	students.forEach(s -> {
		if (s.getId() == id) {
			s.setName(student.getName());
			s.setAge(student.getAge());
			s.setMajor(student.getMajor());
		}
	});
	return student;
	}
	public void delete(int id) {
	students.removeIf(s -> s.getId() == id);
	}
}
