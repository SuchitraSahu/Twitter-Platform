package com.h2.oop.twitterplatform.repository;


import com.h2.oop.twitterplatform.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



// Change the return type to Optional<User>

public interface UserRepository extends JpaRepository<User, Long> { Optional<User> findByEmail(String email);
}


