#version 330 core
out vec4 o_PixelColor;
uniform vec4 _Tint;
void main() {
    o_PixelColor = _Tint;
}