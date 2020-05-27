package com.versioning.controller.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.versioning.entity.EmployeeV1;
import com.versioning.entity.ErrorV1;
import com.versioning.entity.PostId;

@RestController
public class EmployeeController_Post {

  @PostMapping(path = "/employees")
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public @ResponseBody ErrorV1 postemployeeNotAccepted(@RequestBody EmployeeV1 newEmployee) {
    return new ErrorV1(1, "No or wrong Accept header parameter. Or, Error in the URL.");
  }
  

  /**
   * Test V1 OK
   * 
   * @param newEmployee
   * @return
   */
  @PostMapping(path = "/employees", consumes = "application/nbs.si.v1+json")
  @ResponseStatus(HttpStatus.CREATED) // 201
  public @ResponseBody PostId postemployeeV1(@RequestBody EmployeeV1 newEmployee) {
    return new PostId(_postemployeeV1(newEmployee));
  }

  private int _postemployeeV1(@RequestBody EmployeeV1 newEmployee) {
    return newEmployee.getId();
  }
  
  @PostMapping(path = "/alo")
  public @ResponseBody String aloMundo() {
    System.out.println("Alo Mundo!");
    return "Alo Mundo!";
  }
}
