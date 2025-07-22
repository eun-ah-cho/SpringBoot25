package org.mbc.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
        //rest 방식의 객체 처리용

    private Long rno; //댓글용 번호

    @NotNull //필수 값 (NULL 허용 하지않음) -> "" , " "허용
    private Long bno; //게시글의 fk용

    @NotEmpty //Null, ""까지 허용하지 않음. -? " "허용됨 -> @NotBlank  " "차단
    private String replyText; //댓글 내용

    @NotEmpty
    private String replyer; //댓글작성자

    private LocalDateTime regDate, modDate; //등록일, 수정일



}
