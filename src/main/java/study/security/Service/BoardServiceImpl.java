package study.security.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.Entity.Board;
import study.security.Entity.User;
import study.security.Repository.BoardRepository;
import study.security.Repository.UserRepository;
import study.security.dto.BoardRequestDto;
import study.security.dto.BoardResponseDto;

import java.time.LocalDate;
import java.util.*;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@Service
@RequiredArgsConstructor //생성자 주입을 임의의 annotation 없이 설정해 주는 어노테이션
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public void savePost(BoardRequestDto dto, String userName) throws Exception {
        /* save post가 되기 위한 전제 조건으로는 키오스크 뱃지가 있어야 한다는 것임. */
        /* uninitialized object is attempted to be accessed or modified.
        Essentially, this means the object reference does not
        point anywhere and has a null value. */
        Optional<User>opt = userRepository.findByUsername(userName);
        User user =  opt.orElseThrow(() -> new Exception("userName과 일치하는 유저이름이 없습니다."));
        Board board = Board.builder()
                .username(userName)
                .title(dto.getTitle())
                .content(dto.getContent())
                .location(dto.getLocation())
                .build();
        user.setKioskBadge(true); // 임의로 뱃지 부여
        if(user.getKioskBadge()){
            boardRepository.save(board); // request를 통해 Entity 객체 생성
        }else{
            throw new Exception("키오스크 게시글을 작성할 권한이 없습니다");
        }
    }
    @Transactional
    @Override
    public List<BoardResponseDto> getBoardList() {
        List<Board> all = boardRepository.findAllByOrderByIdDesc(); //ID 내림차순으로 가져옴_최신순
        List<BoardResponseDto>boardList = new ArrayList<>();
        for(Board board : all){
            BoardResponseDto resDto = BoardResponseDto.builder()
                    .content(board.getContent())
                    .title(board.getTitle())
                    .userName(board.getUsername())
                    .location(board.getLocation())
                    .build();
            boardList.add(resDto);
        }
        return boardList;
    }
    @Transactional
    @Override   // 최신순으로 수정
    public List<BoardResponseDto> getLocalBoardList(String location) throws Exception {
        List<Board> boardList = boardRepository.findByLocation(location);
        if(boardList == null || boardList.isEmpty()) throw new Exception("해당 지역에 해당하는 게시들이 없습니다.");
        List<BoardResponseDto> ResponseBoard = new ArrayList<>();
        for(Board board : boardList ){
            BoardResponseDto responseDto = BoardResponseDto.builder()
                    .userName(board.getUsername())
                    .title(board.getTitle())
                    .content(board.getUsername())
                    .location(board.getLocation())
                    .build();

            ResponseBoard.add(responseDto);
        }
        return ResponseBoard;
    }
    @Transactional
    @Override
    public BoardResponseDto getPost(Long id){
        /*
        optional : board Wrapper이다. null point error 가 발생하지 않도록 함.
        null point error 가 발생할 수 있는 위치에 사용하여 npe를 막음.
         */
        Optional<Board> opt = boardRepository.findById(id);
        Board board = opt.get();
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getUsername())
                .userName(board.getUsername())
                .build();
    }
    @Transactional
    @Override
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<BoardResponseDto> searchPosts(String keyword) {
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardResponseDto> boardList = new ArrayList<>();

        for(Board board : boards){
                BoardResponseDto resDto = BoardResponseDto.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .location(board.getLocation())
                        .userName(board.getUsername())
                        .build();

                boardList.add(resDto);
        }
        return boardList;
    }

    

//    @Transactional
//    // 보드 수정
//    public void update(Long id, BoardRequestDto dto) {
//        Optional<Board> byId = boardRepository.findById(id);
//        Board board = byId.get();
//
//        board.setTitle(dto.getTitle());
//        board.set(dto.getContent());
//        board.setBoard_region(dto.getRegion());
//        //네, save 메서드를 사용할 때 JPA는 해당 엔티티의 ID 값에 따라 업데이트 또는 신규 생성을 결정합니다.
//        boardRepository.save(board);
//    }
}

