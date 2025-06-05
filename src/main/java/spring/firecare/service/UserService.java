package spring.firecare.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import spring.firecare.dto.UserResponseDTO;
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
        // 이메일 중복 체크
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        return userRepository.save(user);
    }

    public User updateProfile(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            user.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword());
        }

        if (updatedUser.getNickname() != null && !updatedUser.getNickname().isEmpty()) {
            user.setNickname(updatedUser.getNickname());
        }

        if (updatedUser.getGuardians() != null && !updatedUser.getGuardians().isEmpty()) {
            user.setGuardians(updatedUser.getGuardians());
        }

        return user;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    public UserResponseDTO getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getGuardians()
        );
    }
}