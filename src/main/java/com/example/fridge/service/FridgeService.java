package com.example.fridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.fridge.Repository.MemoryDBRepo;
import com.example.fridge.model.Fridge;
import com.example.fridge.model.Item;
import com.example.fridge.model.ItemType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FridgeService 
{
	@Autowired
	MemoryDBRepo repo;

	static Mono<ServerResponse> notFound = ServerResponse.badRequest().build();
	
	public Mono<Integer> create() 
	{
		return repo.create();
	}

	
	public Mono<Fridge> findById(int value) 
	{
		return repo.findById(value);
	}

	
	public Flux<Fridge> findAll() 
	{
		return repo.findAll();
	}

	public Mono<Item> addItem (int id, Item item)
	{

		return Mono.just(item)
				.log()
                .flatMap((i) -> {

                    Mono<Item> itemMono = repo.findById(id)
                            .flatMap(currentItem -> {
                            	
                            	if(currentItem.getItems().containsKey(i.getType()))
                            	{
                            		int currQuantity = currentItem.getItems().get(i.getType());
                            		int quantity =  currQuantity + item.getQuantity();                            		
                            		item.setQuantity(quantity);
                            		repo.updateItem(id, item);
                                    return Mono.just(item);                            		
                            	}
                            	
                            	if(item.getType() == ItemType.SODA && item.getQuantity() > 12)
                            		return Mono.empty();
                            	
                            	repo.updateItem(id, item);
                            	return Mono.just(item);
                            	
                            });
                    return itemMono;
                });
	}
	
	public Mono<Item> removeItem (int id, Item item)
	{

		return Mono.just(item)
				.log()
                .flatMap((i) -> {

                    Mono<Item> itemMono = repo.findById(id)
                            .flatMap(currentItem -> {
                            	
                            	if(currentItem.getItems().containsKey(i.getType()))
                            	{
                            		int currQuantity = currentItem.getItems().get(i.getType());
                            		int quantity =  currQuantity - item.getQuantity();
                            		
                            		if(quantity < 0)
                            			return Mono.empty();
                            		
                            		item.setQuantity(quantity);
                            		repo.updateItem(id, item);
                                    return Mono.just(item);                            		
                            	}
                            	return Mono.just(item);                            	
                            });
                    return itemMono;
                });
	}
	
	public Mono<Fridge> delete(int id) 
	{
		return repo.delete(id);
	}
}
