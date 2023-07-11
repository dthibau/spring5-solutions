package org.formation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imp")
public class ImpController {

	@GetMapping("/{pause}")
	String react(@PathVariable long pause) throws InterruptedException {
		Thread.sleep(1000);
		return "Hello";
	}	
}
