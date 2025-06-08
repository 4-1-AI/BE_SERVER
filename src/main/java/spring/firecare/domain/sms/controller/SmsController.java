package spring.firecare.domain.sms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.firecare.domain.sms.dto.FireCauseSmsRequestDto;
import spring.firecare.domain.sms.dto.StopRequestDto;
import spring.firecare.domain.sms.service.SmsService;

@RestController
@RequestMapping("/alert/fire-cause/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    /**
     * 화재 알림 SMS 예약 요청
     * 기존 예약이 없을 때만 3분 후 발송 예약
     */
    @PostMapping
    public ResponseEntity<String> sendFireCauseSms(@RequestBody FireCauseSmsRequestDto requestDto) {
        smsService.scheduleFireCauseSms(requestDto);
        return ResponseEntity.ok("문자 발송 예약 요청이 처리되었습니다.");
    }

    /**
     * 화재 알림 SMS 예약 취소 요청
     * 사용자 반응(3분 안에 STOP)시 문자 발송 중단
     */
    @PostMapping("/stop")
    public ResponseEntity<String> cancelFireCauseSms(@RequestBody StopRequestDto requestDto) {
        smsService.cancelFireCauseSms(requestDto.getUserId());
        return ResponseEntity.ok("문자 발송 예약이 취소되었습니다.");
    }

    /**
     * 화재 알림 SMS 즉시 전송
     */
    @PostMapping("/direct")
    public ResponseEntity<String> sendFireCauseSmsDirect(@RequestBody FireCauseSmsRequestDto requestDto) {
        smsService.sendFireCauseSms(requestDto);
        return ResponseEntity.ok("보호자에게 화재 원인 SMS가 전송되었습니다.");
    }
}