package com.bpm.engine.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemRequest {
	private String codeEmployee;
	private String processCode;
	private String codeTask;
	private Map<String, List<String>> assigned = new HashMap<String, List<String>>();

	public SystemRequest() {
	}

	public Boolean checkAssigned(String codeTask) {
		return 
			(this.assigned != null && this.assigned.containsKey(codeTask) && !this.assigned.get(codeTask).isEmpty()) ? true: false;

	}

	public String getCodeEmployee() {
		return codeEmployee;
	}

	public void setCodeEmployee(String codeEmployee) {
		this.codeEmployee = codeEmployee;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getCodeTask() {
		return codeTask;
	}

	public void setCodeTask(String codeTask) {
		this.codeTask = codeTask;
	}

	public Map<String, List<String>> getAssigned() {
		return assigned;
	}

	public void setAssigned(Map<String, List<String>> assigned) {
		this.assigned = assigned;
	}
}
