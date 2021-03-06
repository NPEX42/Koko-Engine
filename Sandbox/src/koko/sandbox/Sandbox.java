package koko.sandbox;

import java.awt.Color;

import koko.api.KokoApp;
import koko.api.Material;
import koko.api.MeshData;
import koko.events.EventSystem;

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
//		SetTransform(0, 0, -200f, 0,theta,0, 100, 100, 0);
//		yellow.tint = Color.getHSBColor(theta / 10f, 1, 1);
//		UploadMaterial(yellow);
//		theta += .00314f;
		DrawMeshData(cube);
		
		//LogInfo("Time Step: "+ts);
		
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
		LoadShader("assets/shaders/basic-es.v", "assets/shaders/basic-es.f");
		SetOrthoProj(ScreenWidth(), ScreenHeight(), 1, -1000);
		SetTransform(0, 0, 0f, 10, 10, 0);
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
		SetOrthoProj(ScreenWidth(), ScreenHeight(), 1,-1000);
	} 


}
