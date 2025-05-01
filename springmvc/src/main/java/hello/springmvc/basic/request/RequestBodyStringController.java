package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream=request.getInputStream();
        String messageBody= StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // Stream 이면 무조건 인코딩 타입 지정해줘야 함

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");

    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {


        String messageBody= StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // Stream 이면 무조건 인코딩 타입 지정해줘야 함

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");

    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException { // 마치 http body 자체를 주고 받는 형식. 이 객체가 String 타입의 데이터를 담는 HttpEntity 임을 명확하게 지정.


        String messageBody=httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok"); //생성자에 "ok"를 파라미터 값으로 넣은 것. HTTP 메시지의 본문(Body)에 "ok"라는 문자열을 담는 HttpEntity 객체를 생성하는 코드

    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException { // 실무에서 제일 많이 쓰는 방식

        log.info("messageBody={}", messageBody);

        return"ok";

    }

}
