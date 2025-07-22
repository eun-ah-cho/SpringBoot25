package org.mbc.board.repository;


import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.domain.Board;
import org.mbc.board.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired //필드선언
    private ReplyRepository replyRepository;

    @Test
    public  void testInsert(){
    //댓글 등록용 테스트

        Long bno = 100L; //실제 DB에 있는 bno

        //100번 게시물에 댓글을 넣어보자 . 
        Board board = Board.builder().bno(bno).build();
        // Board 게시물에 100번을 가져옴 
        
        Reply reply = Reply.builder()
                .board(board) //bno
                .replyText("리포지토리에서 테스트")
                .replyer("리포지토리")
                .build();
        //100번 게시물에 댓글 객체 생성

        replyRepository.save(reply); //insert into

       /* Hibernate:
        insert
                into
        reply
                (board_bno, moddate, regdate, reply_text, replyer)
        values
                (?, ?, ?, ?, ?)*/
    }

    @Test
    @Transactional  //no session 나오면 이거 넣자~
    public  void testBoardReplies(){

        Long bno = 100L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending() );
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
        //JPQL을 이용한 selecte 처리용 코드
        //Query("select r from Reply r where r.board.bno = :bno " )


        result.getContent().forEach(reply -> { log.info(reply); });
      /*  Hibernate:
        select
        r1_0.rno,
                r1_0.board_bno,
                r1_0.moddate,
                r1_0.regdate,
                r1_0.reply_text,
                r1_0.replyer
        from
        reply r1_0
        where
        r1_0.board_bno=?
                order by
        r1_0.rno desc limit ?*/

        //Reply(rno=5, replyText=리포지토리에서 테스트, replyer=리포지토리)

        /* @ToString  // (exclude = "board")  exclude 빼면
        Could not initialize proxy [org.mbc.board.domain.Board#100] - no session
        org.hibernate.LazyInitializationException: Could not initialize proxy [org.mbc.board.domain.Board#100] - no session
     */
        //실행 메서드 위에 @Transactional 넣ㅇ르면 댓글 안쪽에 게시글의 객체 내용보임
        //Reply(rno=1, board=Board(bno=100, title=제목...100, content=내용...100, writer=user0), replyText=리포지토리에서 테스트, replyer=리포지토리)
            }

}
