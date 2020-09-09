package koko.api;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.joml.*;

import np.core.IO;

public class ObjLoader {
	@DoNotUse
	public static MeshData LoadFromStream(InputStream stream) {
		String contents = IO.LoadString(stream);
		if(contents == null) return null;
		String[] source = contents.split("\n");
		
		return ProcessSource(source);
	}
	@DoNotUse
	public static MeshData ProcessSource(String... source) {
		List<Vector3f> positions = new ArrayList<Vector3f>();
		List<Vector2f> texCoords = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		
		List<Vector3f> out_positions = new ArrayList<Vector3f>();
		List<Vector2f> out_texCoords = new ArrayList<Vector2f>();
		List<Vector3f> out_normals = new ArrayList<Vector3f>();
		
		List<Integer> posTris = new ArrayList<Integer>();
		List<Integer> texTris = new ArrayList<Integer>();
		List<Integer> normTris = new ArrayList<Integer>();
		for(String line : source) {
			if(line.startsWith("v ")) { //Vertex Position
				Vector3f vec = ToVec3f(1, line);
				if(vec != null) {
					positions.add(vec);
				} else {
					EngineLogger.coreLogger.fatal("MALFORMATTED .OBJ FILE, '"+line+"', Attempted To Extract A Vec3f...");
				}
			}
			
			if(line.startsWith("vt ")) { //Texture Coord
				Vector2f vec = ToVec2f(1, line);
				if(vec != null) {
					texCoords.add(vec);
				} else {
					EngineLogger.coreLogger.fatal("MALFORMATTED .OBJ FILE, '"+line+"', Attempted To Extract A Vec2f...");
				}
			}
			
			if(line.startsWith("vn ")) { //Normal
				Vector3f vec = ToVec3f(1, line);
				if(vec != null) {
					normals.add(vec);
				} else {
					EngineLogger.coreLogger.fatal("MALFORMATTED .OBJ FILE, '"+line+"', Attempted To Extract A Vec3f...");
				}
			}
			
			if(line.startsWith("f")) { //Face / Triangle
				String[] indices = line.split("[\\s/]+");
				
				int pos1Idx = Integer.parseInt(indices[1]);
				int tex1Idx = Integer.parseInt(indices[2]);
				int nor1Idx = Integer.parseInt(indices[3]);
				
				int pos2Idx = Integer.parseInt(indices[4]);
				int tex2Idx = Integer.parseInt(indices[5]);
				int nor2Idx = Integer.parseInt(indices[6]);
				
				int pos3Idx = Integer.parseInt(indices[7]);
				int tex3Idx = Integer.parseInt(indices[8]);
				int nor3Idx = Integer.parseInt(indices[9]);
				
				posTris.add(pos1Idx);
				posTris.add(pos2Idx);
				posTris.add(pos3Idx);
				
				texTris.add(tex1Idx);
				texTris.add(tex2Idx);
				texTris.add(tex3Idx);
				
				normTris.add(nor1Idx);
				normTris.add(nor2Idx);
				normTris.add(nor3Idx);
			}
		}
		
		for(int index : posTris) {
			Vector3f vec = positions.get(index - 1);
			out_positions.add(vec);
		}
		
		for(int index : texTris) {
			Vector2f vec = texCoords.get(index - 1);
			out_texCoords.add(vec);
		}
		
		for(int index : normTris) {
			Vector3f vec = normals.get(index - 1);
			out_normals.add(vec);
		}
		
		float[] pos, uvs, norms;
		pos = new float[positions.size() * 3];
		uvs = new float[texCoords.size() * 2];
		norms = new float[normals.size() * 3];
		int index = 0;
		for(Vector3f v : positions) {
			pos[index++] = v.x;
			pos[index++] = v.y;
			pos[index++] = v.z;
		}
		
		index = 0;
		for(Vector2f v : texCoords) {
			uvs[index++] = v.x;
			uvs[index++] = v.y;
		}
		
		index = 0;
		for(Vector3f v : normals) {
			norms[index++] = v.x;
			norms[index++] = v.y;
			norms[index++] = v.z;
		}
	
		
		MeshData data = new MeshData();
		data.positions = pos;
		data.texCoords = uvs;
		data.normals = norms;
		
		data.triangles = null;
		
		return data;
	}
	
	public static Vector3f ToVec3f(int offset, String line) {
		String[] parts = line.split("[\\s]+");
		if(offset > parts.length  || (parts.length - offset) < 3) return null;
		float x = Float.parseFloat(parts[offset + 0]);
		float y = Float.parseFloat(parts[offset + 1]);
		float z = Float.parseFloat(parts[offset + 2]);
		return new Vector3f(x,y,z);
	}
	
	public static Vector2f ToVec2f(int offset, String line) {
		String[] parts = line.split("[\\s]+");
		if(offset > parts.length  || (parts.length - offset) < 2) return null;
		float x = Float.parseFloat(parts[offset + 0]);
		float y = Float.parseFloat(parts[offset + 1]);
		return new Vector2f(x,y);
	}
}
