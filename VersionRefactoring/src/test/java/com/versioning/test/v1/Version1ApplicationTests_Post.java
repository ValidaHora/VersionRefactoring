package com.versioning.test.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.versioning.entity.EmployeeV1;
import com.versioning.entity.PostId;

@SpringBootTest
public class Version1ApplicationTests_Post {
  private static final String BASE_URL = "http://localhost:8080/employees/";
  private HttpHeaders headersContentType = new HttpHeaders();
  private HttpHeaders headersNone = new HttpHeaders();
  private RestTemplate restTemplate = new RestTemplate();

  private Version1ApplicationTests_Post() {
    headersContentType.setContentType(new MediaType("application", "nbs.si.v1+json"));
  }

  /**
   * Test OK
   */
  @Test
  public void postEmployee_Ok() {
    int id = 12345;
    String url = BASE_URL;
    ResponseEntity<PostId> response = null;

    EmployeeV1 employee = new EmployeeV1(id);
    employee.setFullName("Haroldo Macedo");
    employee.setEmail("email@email.com");
    employee.setPhone("123457689");

    // Request with Content Type Header
    response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(employee, headersContentType), PostId.class);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(id, response.getBody().getId());
  }

  /**
   * Test NOK
   */
  @Test
  public void postEmployee_Nok() {
    int id = 12345;
    String url = BASE_URL;

    EmployeeV1 employee = new EmployeeV1(id);
    employee.setFullName("Haroldo Macedo");
    employee.setEmail("email@email.com");
    employee.setPhone("123457689");

    // Request with no Content Type Header
    try {
      restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(employee, headersNone), Object.class);
      assertFalse(true);
    } catch (HttpClientErrorException he) {
      assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, he.getStatusCode());
    }
  }
}
