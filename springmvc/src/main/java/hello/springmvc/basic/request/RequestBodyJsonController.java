package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper=new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJson1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream=request.getInputStream();
        String messageBody= StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);
        HelloData helloData=objectMapper.readValue(messageBody, HelloData.class);
        log.info("user={}, age={}",helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");

    }

    @ResponseBody // return "ok"; 가능하도록
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}",messageBody);
        HelloData helloData=objectMapper.readValue(messageBody, HelloData.class);
        log.info("user={}, age={}",helloData.getUsername(), helloData.getAge());

        return "ok";

    }

    @ResponseBody // return "ok"; 가능하도록
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData)  {
        log.info("user={}, age={}",helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody // return "ok"; 가능하도록
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data)  { // HelloData data 객체가 http 컨버터에 의해 json 객체로 바뀌고 이렇게 바뀐 json 객체가 return 되어 응답에 박힌다.
        log.info("user={}, age={}",data.getUsername(), data.getAge());
        return data;
    }
}
