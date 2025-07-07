package org.doit.ik.aop;

import org.springframework.stereotype.Component;

public interface Calculator {

	// 덧셈 : addition
	int add(int x, int y);
	
	// 뺄셈 : subtraction
	int sub(int x, int y);
	
	// 곱셈 : multiplication
	int mul(int x, int y);
	
	// 나눗셈 : division
	int div(int x, int y);
	
}
