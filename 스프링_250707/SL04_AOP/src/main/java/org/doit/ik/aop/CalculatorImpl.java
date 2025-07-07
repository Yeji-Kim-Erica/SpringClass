package org.doit.ik.aop;

public class CalculatorImpl implements Calculator {

	// int result = ... -> 핵심 관심사항 (+, -, *, / 계산)
	// 계산 처리하는 데 걸리는 시간 출력 (공통 관심사항)
	
	@Override
	public int add(int x, int y) {
		long start = System.nanoTime(); // 공통 기능
		int result = x + y; // 핵심 기능
		long end = System.nanoTime(); // 공통 기능
		System.out.printf("> 덧셈 처리 시간 : %d ns\n", (end-start)); // 공통 기능
		return result;
	}

	@Override
	public int sub(int x, int y) {
		long start = System.nanoTime();
		int result = x - y;
		long end = System.nanoTime();
		System.out.printf("> 뺄셈 처리 시간 : %d ns\n", (end-start));
		return result;
	}

	@Override
	public int mul(int x, int y) {
		long start = System.nanoTime();
		int result = x * y;
		long end = System.nanoTime();
		System.out.printf("> 곱셈 처리 시간 : %d ns\n", (end-start));
		return result;
	}

	@Override
	public int div(int x, int y) {
		long start = System.nanoTime();
		int result = x / y;
		long end = System.nanoTime();
		System.out.printf("> 나눗셈 처리 시간 : %d ns\n", (end-start));
		return result;
	}

}
