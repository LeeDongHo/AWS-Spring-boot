package kr.domo.book.springboot.web;

import kr.domo.book.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

//lombok
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Junit4 에서는 @RunWith, import org.junit.runner.RunWith 이었다.
// 테스트를 진행할 때 JUnit에 내장된 실행자 외 다른 실행자를 실행시킨다. -> 여기서는 SpringRunner 실행자 실행
// 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@ExtendWith(SpringExtension.class)

// 여러 스프링 테스트 어노테이션 중에서 Web(Srping MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
// @Service, @Component, @Repository 등은 사용할 수 없다.
// 여기서는 컨트롤러만 사용하기에 사용
@WebMvcTest(controllers = HelloController.class,
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {
    // 스프링이 관리하는 빈(Bean)을 주입 받는다.
    @Autowired
    // 웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 할 수 있다.
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET요청을 합니다.
        // 체이닝이 지원돼 아래와 같이 여러 검증 기능을 이어서 선언할 수 있다.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())             // Status 가 200인지 검증
                .andExpect(content().string(hello));    // Content 부분의 내용 검증
                                                        // Controller에서 "hello"를 리턴하기에 검증 가능
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
        .param("name",name)
        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
