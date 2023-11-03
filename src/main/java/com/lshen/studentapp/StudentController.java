package com.lshen.studentapp;

import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/students")
public class StudentController {
	private StudentDAO studentDAO = new StudentDAO();
	
	@GET
	@Path("/totalApiCalls")
	public Response getTotalApiCalls(@Context ServletContext context) {
		Object counterObj = context.getAttribute("apiCallCounter");
		Integer counter;
		if (counterObj == null) {
			counter = 0; // Handle the case where the counter attribute is not set
		} else {
			counter = (Integer) counterObj;
		}
		return Response.ok(counter.toString()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents(){
		return studentDAO.readAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createStudent(Student student) {
		studentDAO.create(student);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("id") int id) {
		return studentDAO.read(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateStudent(@PathParam("id") int id, Student student) {
		studentDAO.update(id, student);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteStudent(@PathParam("id") int id) {
		studentDAO.delete(id);
	}
}
