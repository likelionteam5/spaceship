package study.security.Controller;


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

    private final BoardRepository boardRepository;
    private final BoardServiceImpl boardService;

    // 전체 키오스크 목록
    @GetMapping("/kiosk")
    public BoardResponseDto kioskList(BoardRequestDto reqDto){
        return null ;
    };

    // 키오스크 목록 _ 지역 기반_ 페이징 처리는 어떻게 하는건지?
    @GetMapping("/kiosk/list/{location}")
    public List<Board> kioskListLoc() {
        return null;
    }

    // 키오스크 게시물 상세 조회
    @GetMapping("/Kiosk/{id}")
    public Board kioskBoardDetail(Long id) {
        return null;
    }
    //키오스크 게시물 작성
    @PostMapping("/kiosk")
    public void kioskBoardList() {

    }
    // 키오스크 게시물 수정
    @PatchMapping("/kiosk")
    public void kioskBoardModify(){

    }
    // 키오스크 게시물 삭제
    @DeleteMapping("/kiosk")
    public void kioskBoardDelete() {

    }

    // 키오스크 게시물 검색 _ 제목 키워드 기반
    @GetMapping("/kiosk/search")
    public List<Board> kiosBoardSearching(String keyword){
        return null ;
    }





}
