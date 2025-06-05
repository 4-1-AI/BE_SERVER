package spring.firecare.controller;

import org.springframework.web.bind.annotation.*;
import spring.firecare.entity.User;
import spring.firecare.dto.LoginRequest;
import spring.firecare.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "회원가입 성공!";
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable Long id, @RequestBody User updatedUser) {
        userService.updateProfile(id, updatedUser);
        return "프로필 수정 성공!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return "로그인 성공! 사용자: " + user.getNickname();
    }
}