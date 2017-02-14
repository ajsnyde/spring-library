package models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
  List<User> findByLastNameAndFirstName(String lastName, String firstName);

  List<User> findByRole(Role role);
}
