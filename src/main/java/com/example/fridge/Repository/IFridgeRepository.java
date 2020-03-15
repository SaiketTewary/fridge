package com.example.fridge.Repository;

import com.example.fridge.model.Fridge;
import com.example.fridge.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFridgeRepository 
{
	Mono<Integer> create();
    
    Mono<Fridge> findById(int id);
 
    Flux<Fridge> findAll();
     
    Mono<Fridge> delete(int id);
    
    Mono<Item> updateItem(int id, Item item);
}
