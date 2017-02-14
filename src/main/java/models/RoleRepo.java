package models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
  List<Role> findByName(String name);
}
