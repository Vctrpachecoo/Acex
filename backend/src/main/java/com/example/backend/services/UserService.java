package com.example.backend.services;


import com.example.backend.entities.UsersEntity;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// declara service
@Service
public class UserService {

    // injeção de dependência do repository (chamadas do banco) jpa já tem tudo
    @Autowired
    private UsersRepository usersRepository;

    // cria uma instância da classe BCryptPasswordEncoder que criptografa as senhas
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsersEntity RegisterUser(UsersEntity usersEntity) {

        // verifica se o email já está cadastrado
        if (usersRepository.findByEmail(usersEntity.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        // Verifica se a senha e confirmaSenha são iguais
        if (!usersEntity.getSenha().equals(usersEntity.getConfirmaSenha())) {
            throw new IllegalArgumentException("As senhas não correspondem.");
        }

        // Criptografa a senha antes de salvar
        usersEntity.setSenha(passwordEncoder.encode(usersEntity.getSenha()));

        // salva
        return usersRepository.save(usersEntity);

    }

    // service de login (recebe o email e a senha criptografada como parametros)
    public UsersEntity login(String email, String rawPassword) {

        // Busca o usuário pelo e-mail
        UsersEntity user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Verifica se a senha fornecida (rawPassword) corresponde à senha criptografada no banco
        if (!passwordEncoder.matches(rawPassword, user.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas.");
        }

        return user; // Pode retornar o usuário ou algum token de autenticação, caso implemente JWT
    }
}
