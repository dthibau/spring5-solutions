package org.formation;

import org.formation.controller.AccountHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

	@Autowired
	AccountHandler accountHandler;

	@Bean
	RouterFunction<ServerResponse> composedRoutes() {

		return RouterFunctions.route(RequestPredicates.GET("/accounts"), accountHandler::listAccounts)
				.and(RouterFunctions.route(RequestPredicates.GET("/accounts/{id}"), accountHandler::getAccount))
				.and(RouterFunctions.route(
						RequestPredicates.POST("/accounts")
								.and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
						accountHandler::createAccount));

	}
}
