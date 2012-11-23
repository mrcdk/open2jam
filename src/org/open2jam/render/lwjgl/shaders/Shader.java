package org.open2jam.render.lwjgl.shaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

/**
 * Main class to load and compile a (vertex or fragment) shader 
 * 
 * Code from: http://lwjgl.org/wiki/index.php?title=GLSL_Shaders_with_LWJGL
 *
 * @author Stephen Jones
 * @author CdK
 */
public class Shader {
    
    int shaderID = 0;
    File shaderFile;
    ShaderType shaderType;
    boolean usable = false;

    public enum ShaderType {

	VERT(ARBVertexShader.GL_VERTEX_SHADER_ARB),
	FRAG(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
	int type = -1;

	ShaderType(int type) {
	    this.type = type;
	}

	public int get() {
	    return type;
	}
    }

    protected Shader(File shader, ShaderType shaderType) throws ShaderException {
	if(!shader.exists()) {
	    throw new ShaderException("The shader "+shader.getAbsolutePath()+" doesn't exists");
	}
	this.shaderFile = shader;
	this.shaderType = shaderType;
	
	createShader();
    }

    protected Shader(String shader, ShaderType shaderType) throws ShaderException {
	File f = new File(shader);
	if (!f.exists()) {
	    usable = false;
	    throw new ShaderException("The shader "+f.getAbsolutePath()+" doesn't exists");
	}

	this.shaderFile = f;
	this.shaderType = shaderType;
	
	createShader();
    }
    
    public ShaderType getType() {
	return shaderType;
    }
    
    private int createShader() throws ShaderException {
	try {
	    shaderID = ARBShaderObjects.glCreateShaderObjectARB(shaderType.get());

	    if (shaderID == 0) {
		return 0;
	    }

	    ARBShaderObjects.glShaderSourceARB(shaderID, readFileAsString());
	    ARBShaderObjects.glCompileShaderARB(shaderID);

	    if (ARBShaderObjects.glGetObjectParameteriARB(shaderID, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
		throw new ShaderException("Error creating shader: " + getLogInfo(shaderID));
		//throw new RuntimeException("Error creating shader: " + getLogInfo(shaderID));
	    }

	    return shaderID;
	} catch (Exception exc) {
	    ARBShaderObjects.glDeleteObjectARB(shaderID);
	    throw new ShaderException("Error creating shader: " + shaderType);
	}
    }

    public static String getLogInfo(int obj) {
	return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    private String readFileAsString() throws FileNotFoundException {
	StringBuilder source = new StringBuilder();

	FileInputStream in = new FileInputStream(shaderFile);

	BufferedReader reader;
	try {
	    reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
	    try {
		String line;
		while ((line = reader.readLine()) != null) {
		    source.append(line).append('\n');
		}
	    } catch (Exception exc) {
		return null;
	    } finally {
		try {
		    reader.close();
		} catch (Exception exc) {
		    return null;
		}
	    }
	} catch (Exception exc) {
	} finally {
	    try {
		in.close();
	    } catch (Exception exc) {
		return null;
	    }
	}

	return source.toString();
    }
}
