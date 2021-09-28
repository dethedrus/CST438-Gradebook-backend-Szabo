package com.cst438.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;

@RestController
public class EnrollmentController {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	/*
	 * endpoint used by registration service to add an enrollment to an existing
	 * course.
	 */
	@PostMapping("/enrollment")
	@Transactional
	public EnrollmentDTO addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
		
		// Make a new enrollment to be saved
		Enrollment enrollment = new Enrollment();
		enrollment.setStudentEmail(enrollmentDTO.studentEmail);
		enrollment.setStudentName(enrollmentDTO.studentName);

		// If course exists, add enrollment for that course
		if (courseRepository.findByCourse_id(enrollmentDTO.course_id) != null)
		{
		enrollment.setCourse(courseRepository.findByCourse_id(enrollmentDTO.course_id));
		enrollmentRepository.save(enrollment);
		}
		return null;
		
	}

}