package com.afb.HealthyCook.domain.repository;

import com.afb.HealthyCook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
