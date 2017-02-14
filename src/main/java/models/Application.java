package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demo(BookRepo repository) {
    return (args) -> {
      Repos.books = repository;
      loadBooks("src/main/resources/sampleData/tgb_1.csv");
      loadBooks("src/main/resources/sampleData/tgb_2.csv");
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

  public void loadBooks(String file) {

    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    try {
      br = new BufferedReader(new FileReader(file));
      while ((line = br.readLine()) != null) {
        // use comma as separator
        String[] book = line.split(cvsSplitBy);
        Repos.books.save(new Book(((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L) + "", book[1], book[2]));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
