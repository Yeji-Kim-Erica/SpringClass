package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum; // 현재 페이지 번호
	private int amount; // 한 페이지에 출력할 게시글 수
	
	private String type; // 검색 조건 (t, c, w, tc, tw, tcw)
	private String keyword; // 검색어
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public Criteria() {
		this(1, 10);
	}
	
	// ?pageNum=2&amount=10&type=T&keyword=홍길동&...
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
										.queryParam("pageNum", this.pageNum)
										.queryParam("amount", this.amount)
										.queryParam("type", this.type)
										.queryParam("keyword", this.keyword);
		return builder.toUriString();
	}
	
	// 검색 조건(t, c, w, tc, tw, tcw)을 배열로 반환하는 메서드
	// Mybatis -> WHERE 조건절 반복해서 추가
	public String[] getTypeArr() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
}
