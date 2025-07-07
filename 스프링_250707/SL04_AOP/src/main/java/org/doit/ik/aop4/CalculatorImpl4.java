package org.doit.ik.aop4;

import org.doit.ik.aop.Calculator;
import org.springframework.stereotype.Component;

@Component("calc4")
public class CalculatorImpl4 implements Calculator {
	
	// 핵심 기능만 메서드 안에
	
	@Override
	public int add(int x, int y) {
		int result = x + y;
		return result;
	}

	@Override
	public int sub(int x, int y) {
		int result = x - y;
		return result;
	}

	@Override
	public int mul(int x, int y) {
		int result = x * y;
		return result;
	}

	@Override
	public int div(int x, int y) {
		int result = x / y;
		return result;
	}

}
