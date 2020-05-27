package com.versioning.entity;

/**
 * Id to be returned by POST and PUT methods.
 * 
 * @author Haroldo Macêdo
 *
 */
public class PostId {

  private int id;

  public PostId() {

  }

  public PostId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
