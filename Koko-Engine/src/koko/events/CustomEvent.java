package koko.events;

import java.util.Stack;

public class CustomEvent<T> {
	protected String name;
	private int ID;
	
	public CustomEvent(String name) {
		ID = name.hashCode();
		this.name = name;
	}
	
	public CustomEvent(int ID) {
		name = "UNAMED_EVENT";
		this.ID = ID;
	}
	
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
