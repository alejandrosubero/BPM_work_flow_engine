package com.bpm.engine.utility;

public enum ApprubeType {
	
	VOTING(0),  UNITARY(1), MULTIPLE(2) , FIRST_TO_PASS(3), AUTO(4);

    private final int value;

    ApprubeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

