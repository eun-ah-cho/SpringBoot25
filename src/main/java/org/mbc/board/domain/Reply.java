package org.mbc.board.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity //테이블 관리용 객체
@Getter //게터용
@Builder // 세터대신 빌더패턴 필수로 AllArgsConstructor @NoArgsConstructor
@AllArgsConstructor //모든 필드를 생성자 파라미터로 처리
@NoArgsConstructor //기본 생성자용
@ToString/*(exclude = "board")*/ //board 제외하고 tostring 처리 (객체로 이미 되어있음)*/
@Table(name = "Reply", indexes = { @Index(name = "idx_reply_board_bno", columnList = "board_bno")})
public class Reply extends BaseEntity { //extends BaseEnitiy  등록일, 수정일 처리용 객체

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //자동번호 생성
    private Long rno; //게시물 번호

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    //추천! LAZY 로딩에는 no session이라는 예외가 발생한다. ->  @Teansactional 코드가 필수
    // EAGER 로딩은 연결된 모든 테이블에 값을 가져온다. DB가 힘들어 함
    private Board board; //게시글 fk처리해야함
    //Reply 테이블을 생성하면서 Board에 id값을 확인하여 fk로 선언함

    private String replyText; //댓글내용
    private String replyer; //댓글 작성자

    //등록 날짜와 수정 날짜는 상속받아 처리




}
