package org.formation;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class MainErrorTest {

	
	@Test
	public void testRetry() {
		
		StepVerifier.create(MainError.methodeRetry().log())
					.expectNext("tick 0", "tick 1", "tick 2", "tick 0", "tick 1", "tick 2")
					.expectErrorMessage("boom")
					.verify();
	}
}
