package com.lshen.studentapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	// private static List<Student> students = new ArrayList<Student>();

	public void create(Student student) {
		// we use prepared statements, Q: why?
		String sql = "INSERT INTO students(name, age, major) VALUES(?,?,?)";
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setString(3, student.getMajor());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Student> readAll() {
		String sql = "SELECT id, name, age, major FROM students";
		List<Student> students = new ArrayList<>();
		try (Connection conn = DatabaseConnection.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setMajor(rs.getString("major"));
				students.add(student);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return students;
	}

	public Student read(int id) {
		String sql = "SELECT id, name, age, major FROM students WHERE id = ?";
		Student student = new Student();
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					student.setId(rs.getInt("id"));
					student.setName(rs.getString("name"));
					student.setAge(rs.getInt("age"));
					student.setMajor(rs.getString("major"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return student;
	}

	public void update(int id, Student student) {
		String sql = "UPDATE students SET name = ?, age = ?, major = ? WHERE id = ?";
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set the corresponding parameters
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setString(3, student.getMajor());
			pstmt.setInt(4, id);
			// Update the student record
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM students WHERE id = ?";
		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set the corresponding parameters
			pstmt.setInt(1, id);
			// Update the student record
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
