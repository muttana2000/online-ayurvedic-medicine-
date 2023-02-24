package com.cg.oam.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class NoUserFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
}
