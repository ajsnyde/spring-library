package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

  @Id
  private String name;

  boolean readAccess;
  boolean writeAccess;
  boolean adminAccess;

  protected Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
