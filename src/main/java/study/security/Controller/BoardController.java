package study.security.Controller;

import lombok.RequiredArgsConstructor;
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
    @PostMapping("/")
    public ResponseEntity<BoardDTO> writeTrain(@RequestBody BoardDTO boardDTO){
        boardService.write(boardDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<BoardDTO>> findAll() {
        List<BoardDTO> boardDTOList = boardService.findAll();
        return ResponseEntity.ok(boardDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> findById(@PathVariable Long id) {
        boardService.updateHits(id);
        boardService.findById(id);
        return ResponseEntity.ok().build();
    }
}
