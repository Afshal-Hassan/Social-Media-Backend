package com.example.social.repo;

import com.example.social.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);

    List<User> findByName(String name);
}
