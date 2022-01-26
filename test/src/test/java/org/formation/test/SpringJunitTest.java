package org.formation.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
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
