package com.freehyun.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //SpringApplication.run 으로 인해 내장 WAS(Web Application Server) 를 실행
    }
}
