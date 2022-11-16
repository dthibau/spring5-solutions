package org.formation.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(SpringJunitTest.Config.class)
public class SpringJunitTest {

 
	    @Configuration
	    static class Config {
	    	
	    }

	    @Autowired
	    private ApplicationContext applicationContext;
	 
	    @Test
	    public void givenAppContext_WhenInjected_ThenItShouldNotBeNull() {
	      assertNotNull(applicationContext);
	    }
}
