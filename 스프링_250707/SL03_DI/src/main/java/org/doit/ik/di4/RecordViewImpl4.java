package org.doit.ik.di4;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
// @Component
@Component("rvi")
public class RecordViewImpl4 implements RecordView4 {
	
	// 의존성 주입 (자동)
	@Autowired
	// @Autowired (required = false)
	// @Resource(name = "record2")
	/* @Inject
	   @Named(value = "record2")
	*/
	private RecordImpl4 record = null;
	
	// 디폴트 생성자
	public RecordViewImpl4() {}
	
	public RecordViewImpl4(RecordImpl4 record) {
		this.record = record;
	}

	@Override
	public void input() {
		try (Scanner scanner = new Scanner(System.in)){
			
			System.out.print("> kor, eng, mat input ? ");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
			
		} catch (Exception e) {
			e.printStackTrace();
		} // try~catch
	} // input 메서드

	@Override
	public void output() {
		System.out.printf("> kor=%d, eng=%d, mat=%d, tot=%d, avg=%.2f\n"
								, this.record.getKor()
								, this.record.getEng()
								, this.record.getMat()
								, this.record.total()
								, this.record.avg()
							);
		
	} // output 메서드

}
