package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger; //SL4J 인터페이스 선택하기!!
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //private final Logger log= LoggerFactory.getLogger(getClass()); // 내 클래스 지정해주기 -> 생략 가능
@RestController // http body 에 return 값인 "ok" 바로 박아서 반환. Test 시에 유용.
public class LogTestController {
    //private final Logger log= LoggerFactory.getLogger(getClass()); // 내 클래스 지정해주기

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = "+name); // 이제 이렇게 쓰면 X

        // log 레벨을 설정
        log.trace("info log={}",name);
        log.debug("info log={}",name);
        log.info("info log={}",name);
        log.warn("info log={}",name);
        log.error("info log={}",name);

        return "ok";
    }
}
