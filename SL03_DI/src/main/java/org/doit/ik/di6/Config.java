package org.doit.ik.di6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
/* Config.java 로 설정 빼기.

 	<bean id="user1" class="org.doit.ik.di5.User">
      <constructor-arg value="bkchoi"></constructor-arg>
      <constructor-arg value="1234"></constructor-arg>
    </bean>
*/
	
	@Bean
	public User user1() {
		return new User("bkchoi", "1234");
	}
	
	@Bean
	public User user2() {
		return new User("madvirous", "qwsr");
	}
}
