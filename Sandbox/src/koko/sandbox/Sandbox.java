package koko.sandbox;

import java.awt.Color;

import koko.api.KokoApp;
import koko.rendering.Material;
import koko.rendering.MeshData;

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
	
	float theta = 0.0f;
	
	@Override
	public boolean OnUpdate(float ts) {
		Background(Color.getHSBColor((float) (theta - 3.14) / 10f, 1, 1));
		DrawMeshData(cube);
		
		return true;
	}

	@Override
	public boolean OnDestroy() {
		return true;
	}
	Material yellow = new Material();
	MeshData cube;
	@Override
	public boolean OnCreate() {
		LoadShader("assets/shaders/basic.v", "assets/shaders/basic.f");
		UseIdentityProj();
		SetTransform(0, 0, 0f, 0, 0, 0);
		UploadMaterial(yellow);
		RegisterResizeEventHandler(this::OnWindowResize);
		cube = new MeshData();
		
		cube.positions = vertices;
		cube.triangles = tris;
		
		return true;
	}

	public static void main(String[] args) {
		if(args.length < 1) {
		new Sandbox().Init(1080, 720, "Sandbox - Koko Engine", true);
		} else {
			if(args[0].contentEquals("-SFML")) {
				new Sandbox().InitSFML(1080, 720, "Sandbox - Koko Engine", true);
			} else if(args[0].contentEquals("-GLFW")) {
				new Sandbox().InitGLFW(1080, 720, "Sandbox - Koko Engine", true);
			}
		}
	}
	
	public void OnWindowResize(int w, int h) {
		//SetOrthoProj(ScreenWidth(), ScreenHeight(), 1,-1000);
	} 


}
