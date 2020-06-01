package com.versioning.entity;

/**
 * Id to be returned by POST and PUT methods.
 * 
 * @author Haroldo Macêdo
 *
 */
public class PostIdV2 {

  private String id;

  public PostIdV2() {
  }

  public PostIdV2(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
