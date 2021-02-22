package kr.domo.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 WAS를 실행 - 톰캣 실행 안하고 Jar 파일로 실행하면 끝
        SpringApplication.run(Application.class, args);
    }
}


/*
[Application] - 앞으로 만들 클래스의 메인 클래스
    @SpringBootApplication으로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
    @SpringBootApplication이 있는 위치부터 설정을 읽어가기에 항상 프로젝트 최상단에 위치
*/