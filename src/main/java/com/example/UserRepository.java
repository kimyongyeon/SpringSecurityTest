package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yongyeon on 2016-08-07.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);
}
