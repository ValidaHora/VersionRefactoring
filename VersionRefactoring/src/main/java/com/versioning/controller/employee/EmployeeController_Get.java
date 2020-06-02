package com.versioning.controller.employee;

import org.springframework.beans.BeanUtils;
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
   * 
   * @apiNote <b>Use case D</b> - Fields in a Response – Include<BR>
   *          New output fields firstName, lastName, salaryStatement and relationShip.<BR>
   *          Solution 2 - Ignore new field and don’t return it
   *          
   * @apiNote Use <B>case L</B> - Fields in a Response – Change Type<BR>
   *          Id was integer, now it is string.<BR>
   *          Solution 1 - Copy transformed data, if transformed OK
   */
  @GetMapping(path = "/employees/{employeeId}", produces = "application/nbs.si.v1+json")
  public @ResponseBody EmployeeV1 getEmployeePerIdV1(@PathVariable(name = "employeeId") int employeeId) {
    EmployeeV2 employeeV2 = _getEmployeePerIdV2(employeeId + "");
    EmployeeV1 employeeV1 = new EmployeeV1(employeeId);
    BeanUtils.copyProperties(employeeV2, employeeV1);
    employeeV1.setFullName(employeeV2.getFirstName() + " " + employeeV2.getLastName());

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
    EmployeeV2 employee = new EmployeeV2(employeeId);
    employee.setFirstName("Haroldo");
    employee.setLastName("Macedo");
    employee.setEmail("email@email.com");
    employee.setPhone("123457689");
    employee.setSalaryStatement("/salaryStatement/654");
    
    return employee;
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
}
