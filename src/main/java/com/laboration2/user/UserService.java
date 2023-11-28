package com.laboration2.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> getUserById(int id){
        return repository.findById(id);
    }
    static Optional<UserDto> map(Optional<User> user) {
        if(user.isEmpty()){
            return Optional.empty();
        }
        var user1 = user.get();
        return Optional.of(
                new UserDto(user1.getId(), user1.getFirstName(), user1.getLastName())
        );
    }
}
