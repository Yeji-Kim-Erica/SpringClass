package org.doit.ik.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper { // BoardDAO 인터페이스. Impl 안 만들어도 됨.

	// DB에 있는 서버 시간 가져오는 함수
	String getTime();
	
	@Select(" SELECT SYSDATE + 1 FROM dual")
	String getNextTime();
}
