#version 330 core
in vec4 a_Position;
uniform mat4 _Proj;
uniform mat4 _Transform;
void main() {
	gl_Position = _Proj * _Transform * a_Position;
}