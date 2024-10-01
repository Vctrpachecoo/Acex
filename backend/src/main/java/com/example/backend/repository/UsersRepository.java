package com.example.backend.repository;

import com.example.backend.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// Interface UsersRepository estende JpaRepository, fornecendo métodos para operações CRUD
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    // JpaRepository<UsersEntity, Long>:
    // - UsersEntity: Tipo da entidade que o repositório gerencia
    // - Long: Tipo da chave primária da entidade
    // Esta interface não precisa de implementações, pois herda todos os métodos necessários do JpaRepository


    // Metodo para buscar um usuário por email
    Optional<UsersEntity> findByEmail(String email);
}