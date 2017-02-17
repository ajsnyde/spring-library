package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkouts")
public class Checkout {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "checkouts")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "users")
  private LibraryUser user;

  public Book getBook() {
    return book;
  }
}
