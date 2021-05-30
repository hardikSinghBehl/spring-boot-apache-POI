package com.hardik.kofta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.kofta.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
