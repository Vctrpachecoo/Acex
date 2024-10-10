-- primeira migration
-- criação da tabela de usuários

CREATE TABLE users (
      id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Chave primária que será gerada automaticamente
      nome_completo VARCHAR(255) NOT NULL, -- Nome completo do usuário, não pode ser nulo
      email VARCHAR(255) NOT NULL UNIQUE, -- E-mail do usuário, não pode ser nulo e deve ser único
      senha VARCHAR(255) NOT NULL, -- Senha do usuário, não pode ser nula
      crp VARCHAR(50), -- Campo para registro profissional, pode ser nulo
      especialidade VARCHAR(255), -- Campo para especialidade do usuário, pode ser nulo
      tipo VARCHAR(255) -- Tipo de usuário, pode ser usado para diferenciar papéis (ex: admin, usuário normal)
);