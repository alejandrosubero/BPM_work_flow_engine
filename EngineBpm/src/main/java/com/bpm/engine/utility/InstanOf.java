package com.bpm.engine.utility;

public enum InstanOf {
	
     INSTANCE_PROCESS("INSTANCE_PROCESS"),INSTANCE_TASK ("INSTANCE_TASK"),INSTANCE_STAGE("INSTANCE_STAGE"), PROCESS("PROCESS"),TASK ("TASK"),STAGE("INSTANCE_STAGE");

	
	   private final String value;

	   InstanOf(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
	    
}

