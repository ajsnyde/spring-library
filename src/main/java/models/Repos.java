package models;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

//Yeah, this is gonna be a god-class for a little while. Gotta test out some stuff on a repo, and don't want to work on loading the DB just yet..
public class Repos {
  public static BookRepo books;
  public static LibraryUserRepo users;
  public static AuthenticationManagerBuilder auth;
}
