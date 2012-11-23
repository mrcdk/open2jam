package org.open2jam.render.lwjgl.shaders;

import java.io.File;

/**
 * A fragment shader
 * 
 * @author CdK
 */
public class FragShader extends Shader {
    
    public FragShader(File file) throws ShaderException {
	super(file, Shader.ShaderType.FRAG);
    }
    
    public FragShader(String file) throws ShaderException {
	super(file, Shader.ShaderType.FRAG);
    }
}
