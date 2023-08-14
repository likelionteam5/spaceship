package study.security.Service;

import study.security.dto.BoardRequestDto;
import study.security.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    public void savePost(BoardRequestDto dto, String usrerName) throws Exception;
    public List<BoardResponseDto> getBoardList();
    public BoardRequestDto getPost(Long id);
    public void deletePost(Long id);
    public List<BoardResponseDto> searchPosts(String keyword);
//    public void update(Long id, BoardRequestDto dto);
    public List<BoardResponseDto> getLocalBoardList(String region) throws Exception;

}
