package com.versioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.versioning.entity.EmployeeV1;

@RestController
public class EmployeeController {

	/**
	 * GET /employees/{employeeId}
	 * 
	 * @param employeeId
	 * @return
	 */
	@GetMapping(path = "/employees", produces = "application/nbs.si.v1+json")
	public @ResponseBody EmployeeV1 getEmployeeV1(@PathVariable(name = "employeeId") int employeeId) {
		return _getEmployeeV1(employeeId);
	}
	
	/**
	 * Method to be refactored when new version is needed.
	 * 
	 * @param employeeId
	 * @return
	 */
	private EmployeeV1 _getEmployeeV1(int employeeId) {
		return new EmployeeV1();
	}
}
