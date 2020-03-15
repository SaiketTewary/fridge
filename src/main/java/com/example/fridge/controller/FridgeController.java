package com.example.fridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.fridge.model.Fridge;
import com.example.fridge.model.Item;
import com.example.fridge.service.FridgeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/fridge")
public class FridgeController 
{

	@Autowired
	FridgeService fridgeService;
	
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
	public Mono<String> welcome()
	{
		return Mono.just("Welcome to Fridge Api");
	}
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<Mono<Integer>> createFridge()
	{
    	Mono<Integer> value = fridgeService.create();
        HttpStatus status = value.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.CREATED;
        return new ResponseEntity<Mono<Integer>>(value , status);
	}
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<Mono<Fridge>> getFridgeById(@PathVariable("id") int id)
	{
    	Mono<Fridge> value = fridgeService.findById(id);
        HttpStatus status = value.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<Mono<Fridge>>(value , status);
	}
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
	public Flux<Fridge> getAll()
	{
		return fridgeService.findAll();
	}
    
    
    @RequestMapping(value = "/addItem/{id}", method = RequestMethod.PUT)
    @ResponseBody
	public Mono<ResponseEntity<String>> addItem(@PathVariable("id") int id, @RequestBody Item item)
	{
    	Mono<Item> value = fridgeService.addItem(id, item);
    	return value.flatMap(v ->
    	{
    		return Mono.just(new ResponseEntity<String>( v.toString() , HttpStatus.OK));
    	})
    	.defaultIfEmpty(new ResponseEntity("SODA Quantity can not exced 12!", HttpStatus.BAD_REQUEST));
	}
    
    @RequestMapping(value = "/removeItem/{id}", method = RequestMethod.PUT)
    @ResponseBody
	public Mono<ResponseEntity<String>> removeItem(@PathVariable("id") int id,@RequestBody Item item)
	{
    	Mono<Item> value = fridgeService.removeItem(id, item);
    	return value.flatMap(v ->
    	{
    		return Mono.just(new ResponseEntity<String>( v.toString() , HttpStatus.OK));
    	})
    	.defaultIfEmpty(new ResponseEntity("Remove Item Failed", HttpStatus.BAD_REQUEST));
	}
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
	public ResponseEntity<Mono<String>> removeFridge(@PathVariable("id") int id)
	{
    	Mono<Fridge> value = fridgeService.delete(id);
    	HttpStatus status = value.equals(Mono.empty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    	return new ResponseEntity<Mono<String>>(status);
	}
    
}
