package org.formation;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class FuntionalWebMongoApplicationTests {

	@Autowired
	ApplicationContext context;
	
	@Test
	public void contextLoads() {
		Stream<String> stream = Arrays.stream(context.getBeanDefinitionNames());
        stream.forEach(x -> System.out.println(x));
        
	}

}
