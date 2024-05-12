package com.laboration2.user;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Integer> {

    Optional<Object> findByFirstName(String username);
}
