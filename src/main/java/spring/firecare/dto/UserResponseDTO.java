package spring.firecare.dto;

import lombok.Getter;
import lombok.Setter;
import spring.firecare.entity.Guardian;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String email;
    private String nickname;
    private List<Guardian> guardians;

    public UserResponseDTO(Long id, String email, String nickname, List<Guardian> guardians) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.guardians = guardians;
    }
}