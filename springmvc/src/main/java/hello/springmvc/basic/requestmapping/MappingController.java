package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

    private Logger log= LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic") // url 가지고 mapping
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }
}
