package models;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

  @RequestMapping("/bookView")
  public String greeting(@AuthenticationPrincipal User user, @RequestParam(value = "id", required = true) Long id, Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      Book book = Repos.books.findOne(id);
      if (book != null) {
        model.addAttribute("id", id);
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("isbn", book.getIsbn());
        model.addAttribute("title", book.getTitle());
      }
      return "bookView";
    }
    return "redirect:/login";
  }
}
