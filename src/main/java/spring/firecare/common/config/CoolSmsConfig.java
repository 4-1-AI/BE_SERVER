package spring.firecare.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CoolSmsConfig {

    @Value("${SMS_APIKEY}")
    private String apiKey;

    @Value("${SMS_SECRETKEY}")
    private String apiSecret;

    @Value("${SMS_ADMINPHONE}")
    private String senderPhone;
}