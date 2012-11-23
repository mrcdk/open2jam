package org.open2jam.render.lwjgl.shaders;

import java.io.File;

/**
 * A vertex shader
 * 
 * @author CdK
 */
public class VertShader extends Shader {
    
    public VertShader(File file) throws ShaderException {
	super(file, ShaderType.VERT);
    }
    
    public VertShader(String file) throws ShaderException {
	super(file, ShaderType.VERT);
    }
}
