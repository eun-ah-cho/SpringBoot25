package org.mbc.board.repository.search;

import org.mbc.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    // 게시물의 검색 조건을 다중화하는 기능을 가진다.

    Page<Board> search1(Pageable pageable);
    // 인터페이스에 구현체로 실제 동작은 구현 클래스에서 작성한다. (조장용)

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
             //          제목, 내용c, 작성자w    like   페이징 처리용(정렬, 검색, 페이지번호)

}
