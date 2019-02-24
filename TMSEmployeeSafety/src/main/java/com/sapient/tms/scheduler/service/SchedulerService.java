package com.sapient.tms.scheduler.service;

public interface SchedulerService {

	void scheduleJobIfCabIsLate();

	void scheduleJobIfEmployeeIsLate();

}
