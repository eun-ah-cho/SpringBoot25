package org.mbc.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> { //<E> E 엔터티용 변수명 (변할 수 있는 값)
    //페이징 처리 응답용 객체
    //dto의 목로그 시작페이지 끝페이지 여부 등 을처리

    private int page, size, total ; //현재 페이지, 페이지당 게시물수, 총 게시물수, 총 게시물수
    private int start; //시작페이지
    private int end;  //끝페이지 번호

    private boolean prev ; // 이전 페이지 존재 여부
    private boolean next; //  다음 페이지 존재 여부

    private List<E> dtoList ; //게시물의 목록

    //생성자
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        //pageRequestDTO return link; //page=1&size=10&type=???&keyword=???

        if(total <=0) {
            return;

        }

    this.page = pageRequestDTO.getPage();
    this.size = pageRequestDTO.getSize();
    this.total = total;
    this.dtoList = dtoList;

    this.end = (int)(Math.ceil(this.page / 10.0)) * 10; //화면에서의 마지막 번호
        // 1~10, 11~20 이런식으로 게시판 페이지번호 끊어서 보여주려는거
        //this.page : 현재 페이지를 10으로 나눔
        // page=7 이면 7/10.0 = 0.7
        // Math.ceil은 소수점 올림
        // 현재페이지 24 , 나누기 10 = 2.4 , ceil 올림 =3, 곱하기x10 = 30 , 최종은 30

        this.start = this.end - 9;
        int last =(int)(Math.ceil((total/(double)size)));

        this.end = end > last ? last : end; //3항연산자
        //현재 계산한 end가 실제 존재하는 마지막 페이지보다 크면 줄여야함.

        this.prev = this.start > 1;
        this.next = total >= this.end * this.size ;



    }




}
