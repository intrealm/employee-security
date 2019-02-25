package com.sapient.tms.jobs;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LocationTrackingJob {
	@Bean(name = "ETA")
	@Scheduled(fixedDelay = 10000)
	public void scheduleTaskWithFixedDelay() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ex) {

			throw new IllegalStateException(ex);
		}
	}
}
