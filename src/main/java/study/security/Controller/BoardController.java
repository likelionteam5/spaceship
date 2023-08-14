package study.security.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.security.Entity.Board;
import study.security.Jwt.TokenProvider;
import study.security.Service.BoardServiceImpl;
import study.security.dto.BoardRequestDto;
import study.security.dto.BoardResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kiosk")
public class BoardController {

    private final BoardServiceImpl boardService;
    private final TokenProvider tokenProvider;

    // 전체 키오스크 목록
    @GetMapping("/list")
    public List<BoardResponseDto> kioskList(){
        return boardService.getBoardList();
    };

    // 키오스크 목록 _ 지역 기반_ 페이징 처리 아직 안함.
    @GetMapping("/list/{location}")
    public List<BoardResponseDto> kioskListLoc(@PathVariable(name="location") String location) throws Exception {
        return boardService.getLocalBoardList(location);
    }

    // 키오스크 게시물 상세 조회
    @GetMapping("/{id}")
    public Board kioskBoardDetail(@PathVariable(name = "id") Long id) {
        return null;
    }

    //키오스크 게시물 작성
    //_ 토큰에서 유저 파싱하고, 유저 정보에서 뱃지 여부 확인하는 코드 작성 해야함.
    @PostMapping("")
    public String saveBoard (@RequestBody BoardRequestDto reqDto,@RequestHeader(name="Authorization") String token) throws Exception {
//        String userName =tokenProvider.getUsernameFromToken(token); // 토큰에서 유저 파싱하고
//        System.out.println(userName + "파싱 성공");

        boardService.savePost(reqDto, "yejin1");
        return "redirect:/kiosk/list"; //prg _ 게시물 전체 목록으로 리다이렉트
    }

    // 키오스크 게시물 수정 _ 리다이렉트 생각해보기
//    @PatchMapping("/{id}")
//    public void kioskBoardModify(@PathVariable(name="id") Long id, @RequestBody @Valid BoardRequestDto reqDto){
//        boardService.update(id,reqDto);
//    }

    // 키오스크 게시물 삭제
    @DeleteMapping("/{id}")
    public String kioskBoardDelete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "삭제 완료";
    }

    // 키오스크 게시물 검색 _ 제목 키워드 기반_Board request Dto를 반환하는게 맞는지 생각해보자. 일단 내 생각에는 responseDto를 반환하는게 맞음.
    @GetMapping("/search")
    public List<BoardResponseDto> kioskBoardSearching(@RequestParam("keyword") String keyword){
        return boardService.searchPosts(keyword) ;
    }



}
