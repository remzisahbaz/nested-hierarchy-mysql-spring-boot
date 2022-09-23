package com.ep.service.user;


import com.ep.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserService {
    public Optional<User> create(User user);
    public Optional<User> getOne(Long id);
    @Transactional
    public Optional<User> oneUpdate(Long userId, User user);

}
