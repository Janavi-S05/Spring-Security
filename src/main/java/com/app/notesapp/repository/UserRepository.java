package com.app.notesapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.notesapp.dto.UserLogin;
import com.app.notesapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByUsername(String username);
}
