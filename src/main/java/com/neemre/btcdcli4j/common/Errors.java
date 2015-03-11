package com.neemre.btcdcli4j.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Errors {

	EXAMPLE(1, ""),	
	ARGS_COUNT_UNEVEN(109001, "Expected argument count to be 'even', but was 'uneven' instead."),
	ARGS_COUNT_UNEQUAL(109002, "Expected argument count to be 'equal', but was 'unequal' instead."),
	ARGS_NULL(109003, "Expected a non-null argument, but got 'null' instead."),
	ARGS_CONTAIN_NULL(109004, "Expected only non-null arguments, but got >0 'null' instead.");
	
	
	@Getter
	@Setter
	private int code;
	@Getter
	@Setter
	private String message;
}