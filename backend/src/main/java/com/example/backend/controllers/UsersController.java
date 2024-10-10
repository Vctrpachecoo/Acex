    package com.example.backend.controllers;


    import com.example.backend.entities.UsersEntity;
    import com.example.backend.services.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    // Criação do controller
    @RestController
    @RequestMapping("/users")
    public class UsersController {

        // requisição get para verificação da api
        @GetMapping("/status")
        public String status() {
            return "ENDPOINT OK";
        }

        // injeção de dependência da service
        @Autowired
        private UserService userService;

        // metodo de cadastro de usuários
        @PostMapping("/registerUsers")
        public ResponseEntity<?> registerUser(@RequestBody UsersEntity user) {

            // Inicia um bloco try-catch para lidar com exceções durante o registro do usuário
            try {
                // Chama o serviço de registro para salvar o novo usuário
                UsersEntity newUser = userService.RegisterUser(user);

                // Retorna uma resposta HTTP com o status "201 Created" e o usuário recém-criado
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);

            } catch (IllegalArgumentException e) {
                // Se uma exceção de argumento ilegal for lançada, retorna uma resposta HTTP com o status "400 Bad Request"
                // e a mensagem de erro
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }

        }

        // metodo de login do usuário
        @PostMapping("/login")
        public ResponseEntity<?> loginUser(@RequestBody UsersEntity user) {

            // Inicia um bloco try-catch para tentar realizar o login do usuário
            try {
                // Chama o serviço de login, passando o email e a senha do usuário fornecido no corpo da requisição
                UsersEntity loggedInUser = userService.login(user.getEmail(), user.getSenha());

                // Se o login for bem-sucedido, retorna o usuário logado com status "200 OK"
                return new ResponseEntity<>(loggedInUser, HttpStatus.OK);

            } catch (IllegalArgumentException e) {
                // Se ocorrer uma exceção (como credenciais inválidas), retorna uma resposta com status "401 Unauthorized"
                // e a mensagem de erro
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }
    }
