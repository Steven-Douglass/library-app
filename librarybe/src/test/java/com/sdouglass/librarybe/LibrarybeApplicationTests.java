package com.sdouglass.librarybe;

import com.sdouglass.librarybe.controller.LibraryAPIController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
		locations = "classpath:application-integrationtest.properties")
class LibrarybeApplicationTests {
	private LibraryAPIController libraryAPIController;

	@Autowired
	LibrarybeApplicationTests(LibraryAPIController libraryAPIController) {
		this.libraryAPIController = libraryAPIController;
	}

	@Test
	void contextLoads() {
	}

}
