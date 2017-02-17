package models;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {

  final String API_PATH = "/api";

  @RequestMapping(value = { API_PATH + "/books" }, method = RequestMethod.GET)
  public Iterable<Book> booksGetAll() {
    return Repos.books.findAll();
  }

  @RequestMapping(value = { API_PATH + "/books/count" }, method = RequestMethod.GET)
  public long booksCount() {
    return Repos.books.count();
  }

  @RequestMapping(value = { API_PATH + "/books/{ISBN}" }, method = RequestMethod.GET)
  public Iterable<Book> booksGet(@PathVariable(value = "ISBN") String isbn) {
    return Repos.books.findByIsbn(isbn);
  }

  @RequestMapping(value = { API_PATH + "/books" }, method = RequestMethod.POST)
  public ResponseEntity<String> booksCreate(@AuthenticationPrincipal User user, @RequestParam(value = "isbn", required = false) String isbn,
      @RequestParam(value = "author", required = false) String author, @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "id", required = false, defaultValue = "-1") long id) {
    if (!isAdmin(user))
      return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

    if (id == -1)
      Repos.books.save(new Book(isbn, title, author));
    else {
      Book book = Repos.books.findOne(id);
      if (author != null)
        book.setAuthor(author);
      if (title != null)
        book.setTitle(title);
      if (isbn != null)
        book.setIsbn(isbn);
      Repos.books.save(book);
    }

    return new ResponseEntity<String>(HttpStatus.OK);
  }

  public boolean isAdmin(User user) {
    ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(user.getAuthorities());
    for (GrantedAuthority auth : authorities)
      if (auth.getAuthority().contains("ROLE_ADMIN"))
        return true;
    return false;
  }
}
