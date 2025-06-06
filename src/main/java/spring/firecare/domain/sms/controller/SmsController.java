package spring.firecare.domain.sms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.firecare.domain.sms.dto.FireCauseSmsRequestDto;
import spring.firecare.domain.sms.service.SmsService;

@RestController
@RequestMapping("/alert/fire-cause/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping
    public ResponseEntity<String> sendFireCauseSms(@RequestBody FireCauseSmsRequestDto requestDto) {
        smsService.sendFireCauseSms(requestDto);
        return ResponseEntity.ok("보호자에게 화재 원인 SMS가 전송되었습니다.");
    }
}