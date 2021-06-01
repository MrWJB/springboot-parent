package com.springboot.datasource.dao1;

import com.springboot.datasource.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
