package com.example.backend.repository;

import com.example.backend.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// interface que herda o JPA que já possui todos os métodos do crud
// UsersEntity, Long >> declara a entidade e o tipo da chave primária
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    // buscar usuário por email
    Optional<UsersEntity> findByEmail(String email);
}