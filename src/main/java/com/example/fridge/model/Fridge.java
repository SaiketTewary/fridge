package com.example.fridge.model;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Fridge 
{
	private int fridgeId;
	private HashMap<ItemType, Integer> items = new HashMap<ItemType, Integer>();
	
	public Fridge(int id, HashMap<ItemType, Integer> items)
	{
		this.fridgeId = id;
		this.items = items;
	}
}
