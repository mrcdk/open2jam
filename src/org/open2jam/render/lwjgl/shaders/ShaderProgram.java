package org.open2jam.render.lwjgl.shaders;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

/**
 * A shader program
 * Code from: http://lwjgl.org/wiki/index.php?title=GLSL_Shaders_with_LWJGL
 * 
 * @author Stephen Jones
 * @author CdK
 */
public class ShaderProgram {

    int program = 0;
    final String name;
    final VertShader vertShader;
    final FragShader fragShader;

    public ShaderProgram(String name, VertShader vert, FragShader frag) throws ShaderException {
	this.name = name;
	this.vertShader = vert;
	this.fragShader = frag;
	
	compileProgram();
    }

    public int getProgram() {
	return program;
    }

    public String getName() {
	return name;
    }
    
    public void use() {
	ARBShaderObjects.glUseProgramObjectARB(program);
    }
    
    public void stop() {
	ARBShaderObjects.glUseProgramObjectARB(0);
    }

    private void compileProgram() throws ShaderException {
	program = ARBShaderObjects.glCreateProgramObjectARB();

	if (program == 0) {
	    throw new ShaderException("The program can't be created: "+program);
	}

	/*
	 * if the vertex and fragment shaders setup sucessfully,
	 * attach them to the shader program, link the sahder program
	 * (into the GL context I suppose), and validate
	 */
	ARBShaderObjects.glAttachObjectARB(program, vertShader.shaderID);
	ARBShaderObjects.glAttachObjectARB(program, fragShader.shaderID);

	ARBShaderObjects.glLinkProgramARB(program);
	if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
	    throw new ShaderException("There was an error linking the program: \n"+Shader.getLogInfo(program));
	    
	}

	ARBShaderObjects.glValidateProgramARB(program);
	if (ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
	    throw new ShaderException("There was an error validating the program: \n"+Shader.getLogInfo(program));
	}
    }
}
