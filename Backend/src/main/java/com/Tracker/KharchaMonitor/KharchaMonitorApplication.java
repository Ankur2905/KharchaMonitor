package com.Tracker.KharchaMonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KharchaMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(KharchaMonitorApplication.class, args);
	}

}
