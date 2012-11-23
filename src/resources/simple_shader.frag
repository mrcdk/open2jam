uniform sampler2D u_texture;
varying vec2 v_texCoords;

varying vec4 vertColor;

void main(){
    vec4 color =texture2D(u_texture, v_texCoords);
    gl_FragColor = vec4(color.x+5f, color.y, color.z, color.a);
}