package org.doit.ik.di2;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.GenericXmlApplicationContext;

@Configuration // application-context.xml처럼 자바 설정과 관련된 파일
// @Import(Config2.class) 자바 설정 파일을 조합 가능
// @Import({Config2.class, Config3.class}) 자바 설정 파일을 조합 가능
// @ImportResource("classpath:org/doit/ik/di/application-context.xml")
public class Config {

	//	<bean id="record" class="org.doit.ik.di.RecordImpl"></bean>
	@Bean
	public RecordImpl record() { // 메서드 이름 == 빈 객체의 이름
		return new RecordImpl();
	}
	
	// String[] resourceLocations = {"classpath:org/doit/ik/di/application-context.xml"};
	// GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
	// RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class);
	
	/* p87 참고
		<bean id="rvi" class="org.doit.ik.di.RecordViewImpl">
		    <property name="record">
		      <ref bean="record">
		    </property>
		</bean>
	*/
	
	// p.87 참고
/*	public RecordViewImpl rvi() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		return rvi;
	}
*/
	@Bean(name = "rvi")
	public RecordViewImpl getRecordViewImpl() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		return rvi;
	}
	
}
