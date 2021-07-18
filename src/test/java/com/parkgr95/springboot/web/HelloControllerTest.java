package com.parkgr95.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자(SpringRunner)를 실행시킴.
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함.
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중할 수 있는 어노테이션.
// 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음.
// 단, @Service, @Component, @Repository 등은 사용할 수 없음.
// 어노테이션: 실행시(런타임시) 특정 기능을 실행하도록 정보를 제공
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입.
    private MockMvc mvc; // 웹 API를 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해서 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소로 HTTP GET 요청
                // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음.
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증.
                // HTTP Header의 Status를 검증.
                // Ok 즉, 200인지 아닌지를 검증.
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증
                // 응답 본문의 내용을 검증.
                // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) // param: API 테스트할 때 사용될 요청 파라미터를 설정.
                // 값은 String만 허용
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath: jSON 응답값을 필드별로 검증할 수 있는 메소드
                // $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}