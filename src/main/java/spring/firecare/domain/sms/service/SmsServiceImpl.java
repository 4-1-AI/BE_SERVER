package spring.firecare.domain.sms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;
import spring.firecare.common.config.CoolSmsConfig;
import spring.firecare.domain.sms.dto.FireCauseSmsRequestDto;
import spring.firecare.domain.user.entity.Guardian;
import spring.firecare.domain.user.entity.User;
import spring.firecare.domain.user.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsServiceImpl implements SmsService {

    private final CoolSmsConfig coolSmsConfig;
    private final UserRepository userRepository;
    private DefaultMessageService messageService;

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(
                coolSmsConfig.getApiKey(),
                coolSmsConfig.getApiSecret(),
                "https://api.coolsms.co.kr");
    }

    @Override
    public void sendFireCauseSms(FireCauseSmsRequestDto requestDto) {
        String cause = requestDto.getCause();
        double distance = requestDto.getDistance();
        Long userId = requestDto.getUserId();

        // 1️⃣ 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        List<Guardian> guardians = user.getGuardians();
        if (guardians.isEmpty()) {
            throw new IllegalArgumentException("보호자 번호가 등록되어 있지 않습니다.");
        }

        // 2️⃣ 메시지 작성
        String smsContent = String.format("[화재 원인 알림]\n원인: %s\n거리: %.2f m", cause, distance);

        // 3️⃣ 보호자에게 모두 발송
        for (Guardian guardian : guardians) {
            try {
                Message message = new Message();
                message.setFrom(coolSmsConfig.getSenderPhone());
                message.setTo(guardian.getPhoneNumber());
                message.setText(smsContent);

                SingleMessageSentResponse response =
                        messageService.sendOne(new SingleMessageSendingRequest(message));

                log.info("SMS 발송 성공 [수신자: {}]: {}", guardian.getPhoneNumber(), response);
            } catch (Exception e) {
                log.error("SMS 발송 실패 [수신자: {}]", guardian.getPhoneNumber(), e);
            }
        }
    }
}