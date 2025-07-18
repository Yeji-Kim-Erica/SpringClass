package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// @Component
// @Repository
// @Controller
@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService{

	// private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	// DI
	// @Autowired
	// @Setter(onMethod = @__({@Autowired}))
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("> BoardServiceImpl.getList()...");
		return this.boardMapper.getList();
	}

	@Override
	public int register(BoardVO boardVO) {
		log.info("> BoardServiceImpl.register()...");
		// this.boardMapper.insert(boardVO);
		return this.boardMapper.insertSelectKey(boardVO);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("> BoardServiceImpl.get()...");
		return this.boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO boardVO) {
		log.info("> BoardServiceImpl.modify()...");
		return this.boardMapper.update(boardVO) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("> BoardServiceImpl.remove()...");
		return this.boardMapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria criteria) {
		log.info("> BoardServiceImpl.getListWithPaging()...");
		return this.boardMapper.getListWithPaging(criteria);
	}

	@Override
	public int getTotal() {
		log.info("> BoardServiceImpl.getTotal()...");
		return this.boardMapper.getTotalCount();
	}
	
}
