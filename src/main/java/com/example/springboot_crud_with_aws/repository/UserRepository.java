package com.example.springboot_crud_with_aws.repository;

import com.example.springboot_crud_with_aws.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
