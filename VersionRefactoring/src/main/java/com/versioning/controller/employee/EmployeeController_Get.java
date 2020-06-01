package com.versioning.controller.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.versioning.entity.EmployeeV1;
import com.versioning.entity.EmployeeV2;
import com.versioning.entity.ErrorV1;

@RestController
public class EmployeeController_Get {

  /**
   * GET /employees/{employeeId}
   * V1
   * 
   * @param employeeId
   * @return
   */
  @GetMapping(path = "/employees/{employeeId}", produces = "application/nbs.si.v1+json")
  public @ResponseBody EmployeeV1 getEmployeePerIdV1(@PathVariable(name = "employeeId") int employeeId) {
    EmployeeV2 employeeV2 = _getEmployeePerIdV2(employeeId + "");
    EmployeeV1 employeeV1 = new EmployeeV1(employeeId);
    employeeV1.setFullName(employeeV2.getEmail() + " " + employeeV2.getLastName());
    employeeV1.setEmail(employeeV2.getEmail());
    employeeV1.setPhone(employeeV2.getPhone());
    employeeV1.setStatus(employeeV2.getStatus());
    
    return employeeV1;
  }

  /**
   * GET /employees/{employeeId}
   * V2
   * 
   * @param employeeId
   * @return
   */
  @GetMapping(path = "/employees/{employeeId}", produces = "application/nbs.si.v2+json")
  public @ResponseBody EmployeeV2 getEmployeePerIdV2(@PathVariable(name = "employeeId") String employeeId) {
    return _getEmployeePerIdV2(employeeId);
  }

  /**
   * Method to be refactored when new version is needed.
   * 
   * @param employeeId
   * @return
   */
  private EmployeeV2 _getEmployeePerIdV2(String employeeId) {
    return new EmployeeV2(employeeId);
  }

  /**
   * Validates all other Accept parameters as error request.
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
   * Basic test for GET.
   * 
   * @return
   */
  @GetMapping(path = "/alo")
  public @ResponseBody String aloMundo() {
    System.out.println("Alo Mundo!");
    return "Alo Mundo!";
  }
}
