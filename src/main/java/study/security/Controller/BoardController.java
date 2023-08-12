package study.security.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.security.Entity.Board;
import study.security.Repository.BoardRepository;
import study.security.Service.BoardService;
import study.security.Service.BoardServiceImpl;
import study.security.dto.BoardRequestDto;
import study.security.dto.BoardResponseDto;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class BoardController {

    private final BoardServiceImpl boardService;

    // 전체 키오스크 목록
    @GetMapping("/kiosk/list")
    public List<BoardRequestDto> kioskList(){
        return boardService.getBoardList();
    };

    // 키오스크 목록 _ 지역 기반_ 페이징 처리 아직 안함.
    @GetMapping("/kiosk/list/{region}")
    public List<BoardRequestDto> kioskListLoc(@PathVariable(name="region") String region) throws Exception {
        return boardService.getLocalBoardList(region);
    }

    // 키오스크 게시물 상세 조회
    @GetMapping("/Kiosk/{id}")
    public Board kioskBoardDetail(@PathVariable(name = "id") Long id) {
        return null;
    }

    //키오스크 게시물 작성
    //_ 토큰에서 유저 파싱하고, 유저 정보에서 뱃지 여부 확인하는 코드 작성 해야함.
    @PostMapping("/kiosk/board")
    public void kioskBoardList(BoardRequestDto reqDto, String token) throws Exception {
        String userName =""; // 토큰에서 유저 파싱하고
        // 여기서(컨트롤러에서) 인스턴스 뱃지 확인.
        boardService.savePost(reqDto, userName);
    }
    // 키오스크 게시물 수정 _ 리다이렉트 생각해보기
    @PatchMapping("/kiosk/update/{id}")
    public void kioskBoardModify(@PathVariable(name="id") Long id, @RequestBody @Valid BoardRequestDto reqDto){
        boardService.update(id,reqDto);
    }
    // 키오스크 게시물 삭제
    @DeleteMapping("/kiosk")
    public void kioskBoardDelete(@PathVariable(name = "id") Long id) {
        boardService.deletePost(id);
    }

    // 키오스크 게시물 검색 _ 제목 키워드 기반_Board request Dto를 반환하는게 맞는지 생각해보자. 일단 내 생각에는 responseDto를 반환하는게 맞음.
    @GetMapping("/kiosk/search")
    public List<BoardRequestDto> kioskBoardSearching(String keyword){
        return boardService.searchPosts(keyword) ;
    }





}
