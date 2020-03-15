package com.example.fridge.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.fridge.model.Fridge;
import com.example.fridge.model.Item;
import com.example.fridge.model.ItemType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MemoryDBRepo implements IFridgeRepository
{
	
	public static int fridgeId = 0;
	private HashMap<Integer, Fridge> fridges = new HashMap<Integer, Fridge>();  
	
	@Override
	public Mono<Integer> create() 
	{
		Fridge f = new Fridge(0, new HashMap<ItemType, Integer>());
		f.setFridgeId(++fridgeId);
		try
		{
			fridges.put(f.getFridgeId(),f);		
			return Mono.just(f.getFridgeId());
		}
		catch (Exception ex)
		{
			return Mono.empty();
		}
	}

	@Override
	public Mono<Fridge> findById(int id) 
	{
		Fridge f = fridges.get(id);
		return f == null ? Mono.empty() : Mono.just(f);
	}
	
	@Override
	public Flux<Fridge> findAll() 
	{
		List<Fridge> allFridges = fridges.values().stream().collect(Collectors.toList());
		return Flux.fromStream(allFridges.stream());
	}


	@Override
	public Mono<Item> updateItem(int id, Item item) 
	{
		Fridge fridge = fridges.get(id);
		
		if(fridge == null)
			return Mono.empty();
		
		fridge.getItems().put(item.getType(), item.getQuantity());
		
		return Mono.just(item);
	}
	
	@Override
	public Mono<Fridge> delete(int id) 
	{
		Fridge f = fridges.remove(id);
		return f == null ? Mono.empty() : Mono.just(f);
	}

}
