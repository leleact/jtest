package com.github.leleact.jtest.maven.plugin.checkstyle.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main.
 *
 * @author lele alien
 */
@SpringBootApplication
public class MavenPluginCheckStyleSpringApplication {

	/**
	 * program entry.
	 * @param args main entry arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MavenPluginCheckStyleSpringApplication.class, args);
	}

}
