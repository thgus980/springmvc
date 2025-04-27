package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        int age=Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // return 에 들어간 String 값을 바로 화면에 박아서 응답해준다. -> RestController 와 같은 효과
    @RequestMapping("/request-param-v2")
    public String requestParamV2 (
            @RequestParam("username") String memberName, // http 요청에 해당하는 키 값이랑 따옴표 안에 입력 값이 같아야 함
            @RequestParam("age") int memberAge ) {

        log.info("username={}, age={}",memberName, memberAge);
        return "ok";

    }

    @ResponseBody // return 에 들어간 String 값을 바로 화면에 박아서 응답해준다. -> RestController 와 같은 효과
    @RequestMapping("/request-param-v3")
    public String requestParamV3 (
            @RequestParam String username, // HTTP 파라미터 이름이 변수 이름과 같으면 `@RequestParam(name="xx")` 생략 가능
            @RequestParam int age ) {

        log.info("username={}, age={}",username, age);
        return "ok";

    }

    @ResponseBody // return 에 들어간 String 값을 바로 화면에 박아서 응답해준다. -> RestController 와 같은 효과
    @RequestMapping("/request-param-v4")
    public String requestParamV4 (String username, int age ) { // v3에서 String` , `int` , `Integer` 등의 단순 타입이면 `@RequestParam` 도 생략 가능

        log.info("username={}, age={}",username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // 꼭 있어야 하는 값
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //값이 안 들어오면 defaultValue 값으로 하겠다
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map") // 모든 요청 파라미터를 다 받고 싶다
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttribute1(@RequestParam String username, @RequestParam int age) {
//        HelloData helloData=new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
//
//        return "ok";
//    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1") //위의 메소드 modelAttribute1 과 같음
    public String modelAttribute1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2") // 위의 메소드 modelAttribute1 과 같음
    public String modelAttribute2(HelloData helloData){
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

}
