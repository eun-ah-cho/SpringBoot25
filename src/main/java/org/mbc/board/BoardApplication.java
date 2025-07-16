package org.mbc.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //main메서드 실행시 감시작업 동작함!! 얘가 돌면서 
//BaseEntity의 @EntityListeners(value = AuditingEntityListener.class) 와 셋트임
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
