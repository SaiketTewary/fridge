package com.example.fridge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item 
{
    public ItemType type;
    public int quantity;
    
    public Item() {}
    
	public String toString()
	{
		return String.format("{ItemType:%s, quantity: %d}", this.type, this.quantity);
	}
}