package spring.firecare.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CoolSmsConfig {

    @Value("${sms.apikey}")
    private String apiKey;

    @Value("${sms.secretkey}")
    private String apiSecret;

    @Value("${sms.adminphone}")
    private String senderPhone;

}