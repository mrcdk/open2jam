/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.open2jam.render.lwjgl.shaders;

/**
 *
 * @author CdK
 */
public class ShaderException extends Exception {

    /**
     * Creates a new instance of
     * <code>ShaderException</code> without detail message.
     */
    public ShaderException() {
    }

    /**
     * Constructs an instance of
     * <code>ShaderException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ShaderException(String msg) {
	super(msg);
    }
}
