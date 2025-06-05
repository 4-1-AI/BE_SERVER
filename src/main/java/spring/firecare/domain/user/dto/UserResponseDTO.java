package spring.firecare.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import spring.firecare.domain.user.entity.Guardian;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String email;
    private String password; // 추가
    private String nickname;
    private List<Guardian> guardians;

    public UserResponseDTO(Long id, String email, String password, String nickname, List<Guardian> guardians) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.guardians = guardians;
    }
}