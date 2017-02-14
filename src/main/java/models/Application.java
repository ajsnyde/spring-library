package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  public CommandLineRunner demo(BookRepo repository) {
    return (args) -> {
      repository.save(new Book("1234567890", "Title1", "Author1"));
      repository.save(new Book("1234567891", "Title2", "Author2"));
      repository.save(new Book("1234567892", "Title3", "Author3"));
      // fetch all books
      log.info("Books found with findAll():");
      log.info("-------------------------------");
      for (Book book : repository.findAll()) {
        log.info(book.toString());
      }

      // fetch an individual book by ID
      Book book = repository.findOne(1L);
      log.info("Book found with findOne(1L):");
      log.info("--------------------------------");
      log.info(book.toString());
      log.info("");

      // fetch books by author
      log.info("Book found with findByAuthor('Author2'):");
      log.info("--------------------------------------------");
      for (Book author : repository.findByAuthor("Author2")) {
        log.info(author.toString());
      }
      log.info("");
    };
  }
}
