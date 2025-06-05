package spring.firecare.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import spring.firecare.entity.User;
import spring.firecare.repository.UserRepository;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 가입된 이메일입니다.");
                });
        return userRepository.save(user);
    }

    public User updateProfile(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getNickname() != null) {
            user.setNickname(updatedUser.getNickname());
        }

        if (updatedUser.getGuardians() != null && !updatedUser.getGuardians().isEmpty()) {
            user.setGuardians(updatedUser.getGuardians());
        }

        return user;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다."));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}