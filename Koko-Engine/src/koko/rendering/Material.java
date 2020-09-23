package koko.rendering;

import java.awt.Color;

public class Material {
	public float roughness, specular;
	public Color tint;
	
	public Material() {
		tint = Color.MAGENTA;
	}
}
