package hello.springmvc.basic;

import lombok.Data;

@Data // getter setter 등 다 자동으로 만들어줌 (롬복)
public class HelloData {
    private String username;
    private int age;
}
