package com.example.backend.entities;


import com.example.backend.enums.UsersEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome_completo; // Este é o nome do atributo na classe

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "A senha deve conter ao menos um número, uma letra maiúscula, uma letra minúscula e um caractere especial.")
    private String senha;

    @Transient
    private String confirmaSenha; // Campo para confirmar a senha, não é armazenado.

    private String crp; // Campo para o registro profissional, pode ser nulo.

    private String especialidade; // Campo para especialidade do usuário, pode ser nulo.

    @Enumerated(EnumType.STRING) // Armazena o nome do enum no banco de dados
    private UsersEnum tipo; // Tipo de usuário (paciente ou psicologo)
}
