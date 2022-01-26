package org.formation;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FuntionalWebMongoApplicationTests {

	@Autowired
	ApplicationContext context;
	
	@Test
	public void contextLoads() {
		Stream<String> stream = Arrays.stream(context.getBeanDefinitionNames());
        stream.forEach(x -> System.out.println(x));
        
	}

}
