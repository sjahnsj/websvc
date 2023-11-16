package com.project.websvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.websvc.question.QuestionService;

@SpringBootTest
class WebsvcApplicationTests {

	@Autowired
	private QuestionService questionService;
	// @Test
	// void contextLoads() {
	// }
	@Test
	void testJpa() {
		for(int i=1; i<=300; i++ ){
			String subject = String.format("테스트 질문입니다:[%03d]",i);
			String content = "테스트 질문의 내용입니다";
			// this.questionService.create(subject, content);
		}
	}

}
