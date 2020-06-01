package com.versioning.controller.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.versioning.entity.EmployeeV1;
import com.versioning.entity.EmployeeV2;
import com.versioning.entity.ErrorV1;
import com.versioning.entity.PostId;
import com.versioning.entity.PostIdV2;

@RestController
public class EmployeeController_Post {

  //
  //  Version 1
  //
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * POST /employees
   * V1
   * 
   * @param newEmployee
   * @return
   */
  @PostMapping(path = "/employees", consumes = "application/nbs.si.v1+json")
  @ResponseStatus(HttpStatus.CREATED) // 201
  public @ResponseBody PostId postEmployeeV1(@RequestBody EmployeeV1 newEmployee) {
    //  Map to V2.
    EmployeeV2 employeeV2 = new EmployeeV2();
    employeeV2.setId(newEmployee.getId() + "");
    employeeV2.setFirstName(firstName(newEmployee.getFullName()));
    employeeV2.setLastName(lastName(newEmployee.getFullName()));
    employeeV2.setPhone(newEmployee.getPhone());
    employeeV2.setEmail(newEmployee.getEmail());
    employeeV2.setStatus(newEmployee.getStatus());
    
    //  Transform the String to an Integer.
    return new PostId(Integer.parseInt(_postEmployeeV2(employeeV2)));
  }

  /**
   * Implements the approved way to transform a full name into a first name.
   * @param fullName
   * @return
   */
  private String firstName(String fullName) {
    return fullName.substring(0, 5);
  }

  /**
   * Implements the approved way to transform a full name into a last name.
   * @param fullName
   * @return
   */
  private String lastName(String fullName) {
    return fullName.substring(5);
  }

  //
  //  Version 2
  //
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * POST /employees
   * V2
   * 
   * @param newEmployee
   * @return
   */
  @PostMapping(path = "/employees", consumes = "application/nbs.si.v2+json")
  @ResponseStatus(HttpStatus.CREATED) // 201
  public @ResponseBody PostIdV2 postEmployeeV2(@RequestBody EmployeeV2 newEmployee) {
    return new PostIdV2(_postEmployeeV2(newEmployee));
  }

  /**
   * Method to be refactored when new version is needed.
   * 
   * @param employeeId
   * @return
   */
  private String _postEmployeeV2(@RequestBody EmployeeV2 newEmployee) {
    return newEmployee.getId();
  }

  /**
   * Validates all other Accept parameters as error request.
   * 
   * @param newEmployee
   * @return
   */
  @PostMapping(path = "/employees")
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public @ResponseBody ErrorV1 postEmployeeNotAccepted(@RequestBody EmployeeV1 newEmployee) {
    return new ErrorV1(1, "No or wrong Accept header parameter. Or, Error in the URL.");
  }
  
  /**
   * Basic test for POST.
   * 
   * @return
   */
  @PostMapping(path = "/alo")
  public @ResponseBody String aloMundo() {
    System.out.println("Alo Mundo!");
    return "Alo Mundo!";
  }
}
