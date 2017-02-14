package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  @ManyToOne(cascade = CascadeType.ALL)
  private Role role;

  protected User() {
  }

  public User(String firstName, String lastName, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

  @Override
  public String toString() {
    return String.format("User[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
  }

  public Long getId() {
    return id;
  }
}
