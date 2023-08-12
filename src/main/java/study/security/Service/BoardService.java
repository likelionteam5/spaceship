package study.security.Service;

import study.security.dto.BoardRequestDto;
import study.security.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    public void savePost(BoardRequestDto dto, String usrerName) throws Exception;
    public List<BoardRequestDto> getBoardList();
    public BoardRequestDto getPost(Long id);
    public void deletePost(Long id);
    public List<BoardRequestDto> searchPosts(String keyword);
    public void update(Long id, BoardRequestDto dto);
    public List<BoardRequestDto> getLocalBoardList(String region) throws Exception;

}
