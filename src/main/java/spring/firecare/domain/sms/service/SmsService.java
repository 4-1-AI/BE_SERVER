package spring.firecare.domain.sms.service;


import spring.firecare.domain.sms.dto.FireCauseSmsRequestDto;

public interface SmsService {
    void sendFireCauseSms(FireCauseSmsRequestDto requestDto);
    void scheduleFireCauseSms(FireCauseSmsRequestDto requestDto);
    void cancelFireCauseSms(Long userId);
}