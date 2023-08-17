package study.security.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.security.Entity.Board;
import study.security.Entity.Likey;
import study.security.Entity.User;
import study.security.Jwt.TokenProvider;
import study.security.Repository.UserRepository;
import study.security.Service.BoardServiceImpl;
import study.security.Service.DislikeService;
import study.security.Service.LikeyService;
import study.security.dto.BoardRequestDto;
import study.security.dto.BoardResponseDto;
import study.security.dto.ResponseDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kiosk")
@CrossOrigin(origins = "*")
public class BoardController {

    private final BoardServiceImpl boardService;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final LikeyService likeyService;
    private final DislikeService dislikeService;

    //키오스크 게시물 작성
    //_ 토큰에서 유저 파싱하고, 유저 정보에서 뱃지 여부 확인하는 코드 작성 해야함.
    @PostMapping("")
    public String saveBoard (@RequestBody BoardRequestDto reqDto,@RequestHeader(name="Authorization") String token) throws Exception {
        String username = tokenProvider.getUsernameFromToken(token.substring(7));
        boardService.savePost(reqDto,username);
        return "redirect:/kiosk/list"; //prg _ 게시물 전체 목록으로 리다이렉트
    }
    //전체 키오스크 목록
    @GetMapping("/list")
    public List<BoardResponseDto> kioskList(){
        return boardService.getBoardList();
    };

    // 키오스크 목록 _ 지역 기반
    @GetMapping("/list/{location}")
    public List<BoardResponseDto> kioskListLoc(@PathVariable(name="location") String location) throws Exception {
         return boardService.getLocalBoardList(location);
    }

    // 키오스크 게시물 상세 조회
    @GetMapping("/list/{id}")
    public BoardResponseDto kioskBoardDetail(@PathVariable(name = "id") Long id) {
        return boardService.getPost(id);
    }

    //  키오스크 게시물 수정 _ 리다이렉트 생각해보기
//    @PatchMapping("/{id}")
//    public void kioskBoardModify(@PathVariable(name="id") Long id, @RequestBody @Valid BoardRequestDto reqDto){
//        boardService.update(id,reqDto);
//    }

    // 키오스크 게시물 삭제
    @DeleteMapping("/{id}")
    public String kioskBoardDelete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/kiosk/list";
    }
    // 키오스크 게시물 검색 _ 제목 키워드 기반_Board request Dto를 반환하는게 맞는지 생각해보자. 일단 내 생각에는 responseDto를 반환하는게 맞음.
    @GetMapping("/search")
    public List<BoardResponseDto> kioskBoardSearching(@RequestParam("keyword") String keyword){
        return boardService.searchPosts(keyword) ;
    }
    @PostMapping("/likely/{boardId}")
    public ResponseDto addlike(@PathVariable("boardId") Long boardId, @RequestHeader("Authorization") String token) throws Exception {
        String username = tokenProvider.getUsernameFromToken(token.substring(7));
        Optional<User> opt = userRepository.findByUsername(username);
        User user = opt.orElseThrow(()->new Exception("일치하는 user가 없습니다."));

        return likeyService.addLike(boardId,user);
    }

    @PostMapping("/dislike/{boardId}")
    public ResponseDto addDislike(@PathVariable("boardId") Long boardId, @RequestHeader("Authorization") String token) throws Exception {
        String username = tokenProvider.getUsernameFromToken(token.substring(7));
        Optional<User> opt = userRepository.findByUsername(username);
        User user = opt.orElseThrow(()->new Exception("일치하는 user가 없습니다."));
        return dislikeService.addDislike(boardId,user);
    }



}
