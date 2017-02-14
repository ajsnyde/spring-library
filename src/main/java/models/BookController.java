package models;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  @RequestMapping(value = { "/books" }, method = RequestMethod.GET)
  public Iterable<Book> booksGet(@RequestParam(value = "isbn", required = false) String isbn) {
    if (isbn != null)
      return Repos.books.findByIsbn(isbn);
    else
      return Repos.books.findAll();
  }

  @RequestMapping(value = { "/books" }, method = RequestMethod.POST)
  public void booksCreate(@RequestParam(value = "isbn", required = true) String isbn, @RequestParam(value = "author", required = true) String author,
      @RequestParam(value = "title", required = true) String title) {
    Repos.books.save(new Book(isbn, title, author));
  }

  @RequestMapping(value = { "/books/count" }, method = RequestMethod.GET)
  public long booksCount() {
    return Repos.books.count();
  }
}
