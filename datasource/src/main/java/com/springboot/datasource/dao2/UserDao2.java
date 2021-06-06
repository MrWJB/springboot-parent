package com.springboot.datasource.dao2;

import com.springboot.datasource.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao2 extends JpaRepository<User,Integer> {
}
