package spring.firecare.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping("")
    public ResponseEntity<?> hello() {
        Map<String, Object> body = new HashMap<>();
        body.put("data", "서버 정상 작동 중!");

        return ResponseEntity.ok(body);
    }
}
