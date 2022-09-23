package com.ep.service.user;


import com.ep.exception.ResourceNotFoundException;
import com.ep.model.User;
import com.ep.repository.AssigmentRepository;
import com.ep.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class UserImp implements UserService {
    public final UserRepository userRepository;
    private final AssigmentRepository assigmentRepository;


    @Override
    public Optional<User> getOne(Long id) {

        return Optional.of(userRepository.findById(id).get());
    }

    @Override
    public Optional<User> oneUpdate(Long userId, User user) {
     User exist= userRepository.findById(userId)
                .map(user_ -> {
                    user_.setUsername(user.getUsername());
                    return userRepository.save(user_);
                }).orElseThrow(() ->
                        new ResourceNotFoundException("Not found Tutorial with id = " + userId));


        return Optional.ofNullable(exist);
    }

    @Override
    public Optional<User> create(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

}
