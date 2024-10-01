package com.example.backend.services;


import com.example.backend.entities.UsersEntity;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsersEntity RegisterUser(UsersEntity usersEntity) {

        if (usersRepository.findByEmail(usersEntity.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        // Verifica se a senha e confirmaSenha são iguais
        if (!usersEntity.getSenha().equals(usersEntity.getConfirmaSenha())) {
            throw new IllegalArgumentException("As senhas não correspondem.");
        }

        // Criptografa a senha antes de salvar
        usersEntity.setSenha(passwordEncoder.encode(usersEntity.getSenha()));

        return usersRepository.save(usersEntity);

    }
}
