package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void testPaging() {
		System.out.println("> BoardMapperTest.testPaging()...");
		
		Criteria criteria = new Criteria(1, 3);
		List<BoardVO> list = this.boardMapper.getListWithPaging(criteria);
		
		list.forEach(boardVO -> System.out.println("> 글 번호 : " + boardVO.getBno() + "\n" + boardVO));
	}

}
