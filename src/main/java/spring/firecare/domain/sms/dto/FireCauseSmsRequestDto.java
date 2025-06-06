package spring.firecare.domain.sms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireCauseSmsRequestDto {
    private String cause;
    private double distance;
    private Long userId;
}