package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "users")
public class LibraryUser extends User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @ElementCollection(targetClass = Checkout.class)
  private Set<Checkout> checkouts;

  public LibraryUser() {
    super("asdfasdf", "asdfasdf", new ArrayList<GrantedAuthority>());
    ArrayList<Checkout> checkouts = new ArrayList<Checkout>();
  }

  public LibraryUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    ArrayList<Checkout> checkouts = new ArrayList<Checkout>();
  }

  Set<Checkout> getCheckouts() {
    return checkouts;
  }

}
