package com.example.fridge;


import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.fridge.Repository.MemoryDBRepo;
import com.example.fridge.model.Fridge;
import com.example.fridge.model.Item;
import com.example.fridge.model.ItemType;
import com.example.fridge.service.FridgeService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class FridgeServiceTests 
{

	@InjectMocks
	FridgeService service;
	
	@Mock
	public MemoryDBRepo repo;
	
    
    @Test
    public void findByIdTest()
    {
    	HashMap<ItemType, Integer>	item = new HashMap<ItemType, Integer>();
    	item.put(ItemType.EGG, 20);
    	Fridge f = new Fridge(1, item);
    	Mockito.when(repo.findById(1)).thenReturn(Mono.just(f));
    	
    	Mono<Fridge> value = service.findById(1);
    	
        StepVerifier.create(value)
        .expectNext(f)
        .verifyComplete();
    }
    
    @Test
    public void addItemTest()
    {
    	HashMap<ItemType, Integer>	item = new HashMap<ItemType, Integer>();
    	Item addItem = new Item();
    	addItem.setType(ItemType.EGG);
    	addItem.setQuantity(20);
    	
    	Fridge f = new Fridge(1, item);
    	Mockito.when(repo.findById(1)).thenReturn(Mono.just(f));
    	
    	Mono<Item> value = service.addItem(1, addItem);
    	
        StepVerifier.create(value)
        .expectNext(addItem)
        .verifyComplete();
    }
    
    @Test
    public void addItemSodaLessthan12Test()
    {
    	HashMap<ItemType, Integer>	item = new HashMap<ItemType, Integer>();
    	Item addItem = new Item();
    	addItem.setType(ItemType.SODA);
    	addItem.setQuantity(10);
    	
    	Fridge f = new Fridge(1, item);
    	Mockito.when(repo.findById(1)).thenReturn(Mono.just(f));
    	
    	Mono<Item> value = service.addItem(1, addItem);
    	
        StepVerifier.create(value)
        .expectNext(addItem)
        .verifyComplete();
    }
    
    @Test
    public void addItemSodaMorethan12Test()
    {
    	HashMap<ItemType, Integer>	item = new HashMap<ItemType, Integer>();
    	Item addItem = new Item();
    	addItem.setType(ItemType.SODA);
    	addItem.setQuantity(20);
    	
    	Fridge f = new Fridge(1, item);
    	Mockito.when(repo.findById(1)).thenReturn(Mono.just(f));
    	
    	Mono<Item> value = service.addItem(1, addItem);
    	
        StepVerifier.create(value)
        .verifyComplete();
    }
}
