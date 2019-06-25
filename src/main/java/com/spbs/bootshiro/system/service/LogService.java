package com.spbs.bootshiro.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spbs.bootshiro.common.service.IService;
import com.spbs.bootshiro.system.domain.TLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface LogService extends IService<TLog> {
	
	List<TLog> findAllLogs(TLog log);
	
	void deleteLogs(String logIds);

	@Async
	void saveLog(ProceedingJoinPoint point, TLog log) throws JsonProcessingException;
}
