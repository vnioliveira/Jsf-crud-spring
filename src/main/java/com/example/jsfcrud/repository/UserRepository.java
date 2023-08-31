package com.example.jsfcrud.repository;

import com.example.jsfcrud.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :partialName, '%'))")
    List<UserEntity> searchUsersByName(@Param("partialName") String partialName);
}
