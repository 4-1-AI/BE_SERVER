package spring.firecare.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;       // 아이디
    private String password;    // 비밀번호
    private String nickname;    // 닉네임

    @ElementCollection
    @CollectionTable(name = "guardian", joinColumns = @JoinColumn(name = "user_id"))
    private List<Guardian> guardians = new ArrayList<>();
}