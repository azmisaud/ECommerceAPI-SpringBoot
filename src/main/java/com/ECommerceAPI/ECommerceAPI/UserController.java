package com.ECommerceAPI.ECommerceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);

        Map<String, String> response = Map.of(
            "message", "User registered successfully",
            "userId", savedUser.getId().toString()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
            .map(user -> {
                user.setPassword(null); // Hide password in response
                return ResponseEntity.ok(user);
            })
            .orElse(ResponseEntity.notFound().build());
        }

}
