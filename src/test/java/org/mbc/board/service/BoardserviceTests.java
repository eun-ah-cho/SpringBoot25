package org.mbc.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardserviceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        log.info("등록용 테스트 서비스 실행중.....");
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("서비스에서만든제목")
                .content("서비스에서 만든내용")
                .writer("서비스님")
                .build(); //세터대신 @Builder

        Long bno = boardService.register(boardDTO); //서비스 구현메서드 동작함
        log.info("테스트 결과" + bno); //테스트결과 101

    /*    Hibernate:
        insert
                into
        board
                (content, moddate, regdate, title, writer)
        values
                (?, ?, ?, ?, ?)*/

    }

    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("서비스에서 수정된 내용")
                .build();
        boardService.modify(boardDTO);


       /* Hibernate:
        select
        b1_0.bno,
                b1_0.content,
                b1_0.moddate,
                b1_0.regdate,
                b1_0.title,
                b1_0.writer
        from
        board b1_0
        where
        b1_0.bno=?*/
    }

    @Test
    public void testDelete() {
        Long bno = 101L;
        boardService.remove(bno);


      /*  Hibernate:
        select
        b1_0.bno,
                b1_0.content,
                b1_0.moddate,
                b1_0.regdate,
                b1_0.title,
                b1_0.writer
        from
        board b1_0
        where
        b1_0.bno=?
        Hibernate:
        delete
                from
        board
                where
        bno=?*/

    }
}
