package koko.rendering;

public abstract class VertexBuffer extends Bindable {
	public abstract void SetFloatData(int index, float[] data, int size);
}
