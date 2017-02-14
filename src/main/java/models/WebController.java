package models;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

  @RequestMapping("/bookView")
  public String greeting(@RequestParam(value = "id", required = true) Long id, Model model) {

    Book book = Repos.books.findOne(id);
    if (book != null) {
      model.addAttribute("id", id);
      model.addAttribute("author", book.getAuthor());
      model.addAttribute("isbn", book.getIsbn());
      model.addAttribute("title", book.getTitle());
    }
    return "bookView";
  }
}
