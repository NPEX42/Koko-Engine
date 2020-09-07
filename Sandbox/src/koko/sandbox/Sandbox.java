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
		Background(Color.BLUE);
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
		if(args.length < 1) {
		new Sandbox().Init(1080, 720, "Sandbox - Koko Engine", true);
		} else {
			if(args[0].contentEquals("-SFML")) {
				new Sandbox().InitSFML(1080, 720, "Sandbox - Koko Engine", true);
			} else {
				new Sandbox().InitGLFW(1080, 720, "Sandbox - Koko Engine", true);
			}
		}
	}


}
