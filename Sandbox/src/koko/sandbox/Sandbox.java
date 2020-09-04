package koko.sandbox;

import java.awt.Color;

import koko.api.KokoApp;

public class Sandbox extends KokoApp {
	private float[] vertices = {
			-0.5f,-0.5f,0,
			 0.5f,-0.5f,0,
			 0.5f, 0.5f,0,
			-0.5f, 0.5f,0
	};
	
	private int[] tris = {
			0,1,2,
			2,3,0
	};
	@Override
	public boolean OnUpdate(float ts) {
		DrawMeshData(vertices, tris);
		return true;
	}

	@Override
	public boolean OnDestroy() {
		return true;
	}

	@Override
	public boolean OnCreate() {
		return true;
	}

	public static void main(String[] args) {
		new Sandbox().Init(1080, 720, "Sandbox - Koko Engine", true);
	}


}
