package com.example.fridge.model;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter 
@Setter
public class FridgeResponse 
{
	private HttpStatus status;
	private String 	   message;
}
