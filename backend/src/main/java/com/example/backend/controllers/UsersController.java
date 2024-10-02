    package com.example.backend.controllers;


    import com.example.backend.entities.UsersEntity;
    import com.example.backend.services.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/users")
    public class UsersController {

        @GetMapping("/status")
        public String status() {
            return "ENDPOINT OK";
        }

        @Autowired
        private UserService userService;

        @PostMapping("/registerUsers")
        public ResponseEntity<?> registerUser(@RequestBody UsersEntity user) {

            try {
                UsersEntity newUser = userService.RegisterUser(user);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);

            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }

        }

        @PostMapping("/login")
        public ResponseEntity<?> loginUser(@RequestBody UsersEntity user) {
            try {
                UsersEntity loggedInUser = userService.login(user.getEmail(), user.getSenha());
                return new ResponseEntity<>(loggedInUser, HttpStatus.OK);

            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }
    }
