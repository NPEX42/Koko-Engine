package koko.events;

public interface ICustomHandler {
	public void Handle(CustomEvent<?> event);
}
