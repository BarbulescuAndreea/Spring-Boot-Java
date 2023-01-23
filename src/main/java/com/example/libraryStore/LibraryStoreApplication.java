package com.example.libraryStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.persistence.repo") // scan the entire packet to find repository with data
// jpa repository - includes the crudrepo and so the operations that came with it
@EntityScan("com.example.persistence.model")
//  This will tells spring boot to where to scan for detecting the entities(tabels from data-bases) for the application.

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// exclude security configurations so it won t ask for login when going on web site
public class LibraryStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryStoreApplication.class, args);
	}

}
