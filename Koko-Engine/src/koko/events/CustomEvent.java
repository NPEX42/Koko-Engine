package koko.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CustomEvent<T> {
	public String name;
	public int ID;
	
	public Stack<T> payload = new Stack<T>();
	
	public void Push(T item) {
		payload.push(item);
	}
	
	public T Pop() {
		return payload.pop();
	}
	
	public int GetPayloadStackSize() {
		return payload.size();
	}
}
