package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String author;
  private String title;
  private String isbn;

  protected Book() {
  }

  public Book(String isbn, String title, String author) {
    this.setIsbn(isbn);
    this.setTitle(title);
    this.setAuthor(author);
  }

  @Override
  public String toString() {
    return String.format("Book[id=%d, title='%s', author='%s', ISBN='%s']", id, title, author, isbn);
  }

  public Long getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = Isbn.toIsbn13(isbn);
  }
}
