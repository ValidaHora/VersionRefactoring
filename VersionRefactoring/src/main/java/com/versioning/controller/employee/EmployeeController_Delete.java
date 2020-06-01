package com.versioning.controller.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.versioning.entity.EmployeeV1;
import com.versioning.entity.ErrorV1;

@RestController
public class EmployeeController_Delete {

  /**
   * DELETE /employees/{employeeId}
   * V1
   * 
   * @param newEmployee
   * @return
   */
  @DeleteMapping(path = "/employees/{employeeId}", produces = "application/nbs.si.v1+json")
  public @ResponseBody EmployeeV1 deleteEmployeeV1(@PathVariable(name = "employeeId") int employeeId) {
    return _deleteEmployeePerIdV1(employeeId);
  }

  /**
   * Method to be refactored when new version is needed.
   * 
   * @param employeeId
   * @return
   */
  private EmployeeV1 _deleteEmployeePerIdV1(int employeeId) {
    return new EmployeeV1(employeeId);
  }

  /**
   * Validates all other Accept parameters as error request.
   * 
   * @param newEmployee
   * @return
   */
  @DeleteMapping(path = "/employees/{employeeId}", produces = {"application/json"})
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public @ResponseBody ErrorV1 getEmployeePerIdNotAccepted(@PathVariable(name = "employeeId") int employeeId) {
    return new ErrorV1(1, "No or wrong Accept header parameter. Or, Error in the URL.");
  }
  
}
