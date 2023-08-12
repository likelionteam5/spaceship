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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if(user.getKioskBadge()){
            boardRepository.save(dto.toEntity()); // request를 통해 Entity 객체 생성ㅎ
        }else{
            throw new Exception("키오스크 게시글을 작성할 권한이 없습니다");
        }
    }



    @Transactional
    @Override
    public List<BoardRequestDto> getBoardList() {
        List<Board> all = boardRepository.findAll();
        List<BoardRequestDto>boardList = new ArrayList<>();
        for(Board board : all){
            BoardRequestDto boardDto = BoardRequestDto.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .build();
            boardList.add(boardDto); //request에서 원하는 보드 정보만을 제공
        }
        return boardList;
    }

    @Transactional
    @Override
    public List<BoardRequestDto> getLocalBoardList(String region) throws Exception {
        List<Board> boardList  = boardRepository.findByRegion(region);
        if(boardList == null || boardList.isEmpty()) throw new Exception("해당 지역에 해당하는 게시들이 없습니다.");
        List<BoardRequestDto> RequestBoards = new ArrayList<>() ;
        for(Board board : boardList ){
            BoardRequestDto Requestboard = BoardRequestDto.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .region(board.getRegion())
                    .build();

            RequestBoards.add(Requestboard);
        }
        return RequestBoards;
    }

    @Transactional
    @Override
    public BoardRequestDto getPost(Long id) {
        /*
        optional : boardWrapper이다. null point error 가 발생하지 않도록 함.
        null point error 가 발생할 수 있는 위치에 사용하여 npe를 막음.
         */
        Optional<Board> boardWapper = boardRepository.findById(id);
        Board board = boardWapper.get();

        return BoardRequestDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .build();
    }
    @Transactional
    @Override
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<BoardRequestDto> searchPosts(String keyword) {
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardRequestDto> boardList = new ArrayList<>();

        for(Board board : boards){
            BoardRequestDto build = BoardRequestDto.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .region(board.getRegion())
                    .build();
            boardList.add(build);
        }
        return boardList;
    }
    @Transactional
    @Override
    public void update(Long id, BoardRequestDto dto) {
        Optional<Board> byId = boardRepository.findById(id);
        Board board = byId.get();

        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setRegion(dto.getRegion());
        //네, save 메서드를 사용할 때 JPA는 해당 엔티티의 ID 값에 따라 업데이트 또는 신규 생성을 결정합니다.
        boardRepository.save(board);
    }
}
