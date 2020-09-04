package koko.rendering;

public abstract class Bindable {
	protected int ID;
	
	public abstract void Bind();
	public abstract void Unbind();
	public abstract void Delete();
	
	public abstract void Regenerate();
}
