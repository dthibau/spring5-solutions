package org.formation.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.formation.controller.AccountHandler;
import org.formation.model.AccountCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(SpringJunitTest.Config.class)
class SpringJunitTest2 {

	 @Configuration
	    static class Config {
	    
		 @MockBean
		 AccountCrudRepository accountCrudRepository;
		 
		 @Bean
		 AccountHandler accountHandler() {
			 return new AccountHandler(accountCrudRepository);
		 }
	    }

	    @Autowired
	    private ApplicationContext applicationContext;
	 
	    @Test
	    public void givenAppContext_WhenInjected_ThenItShouldNotBeNull() {
	      assertNotNull(applicationContext);
	      
	      for ( String beanName : applicationContext.getBeanDefinitionNames() )
	    	  System.out.println(beanName);
	    }
}
