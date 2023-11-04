package com.codewithtarun.backenedbytarun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtarun.backenedbytarun.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
