package org.open2jam.render.lwjgl.shaders;

import java.util.HashMap;

/**
 * A ShaderProgram manager to keep track of every shader program we use
 * @author CdK
 */
public class ShaderManager extends HashMap<String, ShaderProgram> {
    
    private ShaderManager() {
    }
    
    public ShaderProgram put(ShaderProgram program) {
	return super.put(program.getName(), program);
    }
    
    public static ShaderManager getInstance() {
	return ShaderProgramManagerHolder.INSTANCE;
    }
    
    private static class ShaderProgramManagerHolder {

	private static final ShaderManager INSTANCE = new ShaderManager();
    }
}
