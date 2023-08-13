package study.security.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.security.Service.BoardService;
import study.security.dto.BoardDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/train")
public class BoardController {
    private final BoardService boardService;
    // 글 작성
    @PostMapping("/")
    public ResponseEntity<BoardDTO> writeTrain(@RequestBody BoardDTO boardDTO){
        boardService.write(boardDTO);
        return ResponseEntity.ok().build();
    }
    // 글 목록 전체 조회
    @GetMapping("/list/")
    public ResponseEntity<List<BoardDTO>> findAll() {
        List<BoardDTO> boardDTOList = boardService.findAll();
        return ResponseEntity.ok(boardDTOList);
    }

    // 지역별 글 목록 조회
    @GetMapping("/list/{location}")
    public ResponseEntity<?> findByLocation(@PathVariable String location){
        BoardDTO foundBoard = boardService.findByLocation(location);
        if (foundBoard != null){
            return ResponseEntity.ok(foundBoard);
        } else {
            String message = "조회된 게시물이 없습니다.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }


    }

    // 글 상세 조회(수정 필요)
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        BoardDTO foundBoard = boardService.findById(id);
        if (foundBoard != null){
            return ResponseEntity.ok(foundBoard);
        } else {
            String message = "조회된 게시물이 없습니다.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }
    // 글 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<BoardDTO> update(@PathVariable Long id, @RequestBody BoardDTO updatedBoard) {
        BoardDTO updated = boardService.updateBoard(id, updatedBoard);
        return ResponseEntity.ok(updated);
    }
    // 글 삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/list/";
    }

    // /paging?page=1
    @GetMapping("/paging")
    public ResponseEntity<Page<BoardDTO>> paging(@PageableDefault(page = 1) Pageable pageable) {
        int blockLimit = 5; // 한 번에 보여질 페이지 수 제한

        // BoardService의 paging 메서드를 호출하여 페이징 처리된 글 목록을 조회합니다.
        Page<BoardDTO> boardList = boardService.paging(pageable);

        int totalPage = boardList.getTotalPages(); // 총 페이지 수

        int currentPage = pageable.getPageNumber() + 1; // 현재 페이지 번호
        int startPage = ((currentPage - 1) / blockLimit) * blockLimit + 1; // 시작 페이지 번호
        int endPage = Math.min(startPage + blockLimit - 1, totalPage); // 끝 페이지 번호

        // 현재 페이지의 글 목록과 페이징 정보를 포함하여 새로운 Page 객체를 생성합니다.
        Page<BoardDTO> pagedBoardList = new PageImpl<>(boardList.getContent(), pageable, boardList.getTotalElements());

        // HTTP 응답 헤더에 페이징 정보를 추가하여 반환합니다.
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("totalPages", String.valueOf(totalPage)); // 총 페이지 수
        responseHeaders.add("currentPage", String.valueOf(currentPage)); // 현재 페이지 번호
        responseHeaders.add("startPage", String.valueOf(startPage)); // 시작 페이지 번호
        responseHeaders.add("endPage", String.valueOf(endPage)); // 끝 페이지 번호

        // 생성한 Page 객체와 헤더 정보를 포함하는 ResponseEntity를 반환합니다.
        return new ResponseEntity<>(pagedBoardList, responseHeaders, HttpStatus.OK);
    }

}
