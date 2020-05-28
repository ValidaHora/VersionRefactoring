package com.versioning.controller.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.versioning.entity.EmployeeV1;
import com.versioning.entity.ErrorV1;

@RestController
public class EmployeeController_Get {

  /**
   * Validates all other Accept parameters.
   * 
   * @param employeeId
   * @return
   */
  @GetMapping(path = "/employees/{employeeId}", produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public @ResponseBody ErrorV1 getEmployeePerIdNotAccepted(@PathVariable(name = "employeeId") int employeeId) {
    return new ErrorV1(1, "No or wrong Accept header parameter. Or, Error in the URL.");
  }

  /**
   * GET /employees/{employeeId}
   * 
   * @param employeeId
   * @return
   */
  @GetMapping(path = "/employees/{employeeId}", produces = "application/nbs.si.v1+json")
  public @ResponseBody EmployeeV1 getEmployeePerIdV1(@PathVariable(name = "employeeId") int employeeId) {
    return _getEmployeePerIdV1(employeeId);
  }

  /**
   * Method to be refactored when new version is needed.
   * 
   * @param employeeId
   * @return
   */
  private EmployeeV1 _getEmployeePerIdV1(int employeeId) {
    return new EmployeeV1(employeeId);
  }

  @GetMapping(path = "/alo")
  public @ResponseBody String aloMundo() {
    System.out.println("Alo Mundo!");
    return "Alo Mundo!";
  }
}
