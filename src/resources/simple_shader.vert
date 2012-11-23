attribute vec2 a_texCoord0;
varying vec2 v_texCoords;

void main(){
    gl_Position = gl_ModelViewProjectionMatrix*gl_Vertex;
    v_texCoords = vec2(gl_MultiTexCoord0);
}
