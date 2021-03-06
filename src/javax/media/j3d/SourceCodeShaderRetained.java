/*
 * $RCSfile: SourceCodeShaderRetained.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.6 $
 * $Date: 2007/04/12 17:34:06 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * The SourceCodeShaderRetained object is a shader that is defined using
 * text-based source code. It is used to define the source code for
 * both vertex and fragment shaders. The currently supported shading
 * languages are Cg and GLSL.
 */

class SourceCodeShaderRetained extends ShaderRetained {

    private String shaderSource = null;

    /**
     * Constructs a new shader retained object of the specified shading
     * language and shader type from the specified source string.
     */

    SourceCodeShaderRetained() {
    }

    // This method is similar to setShaderSource(). 
    // To conform to j3d frame in retained creation, we will stick with method 
    // with init name.
    final void initShaderSource(String shaderSource) {
	this.shaderSource = shaderSource;
    }    

    final void set(int shadingLanguage, int shaderType, String shaderSource) {
	this.shadingLanguage = shadingLanguage;
	this.shaderType = shaderType;
	this.shaderSource = shaderSource;
    }

    /**
     * Retrieves the shader source string from this shader object.
     *
     * @return the shader source string.
     */
    final String getShaderSource() {
	return shaderSource;
    }

    final void setShaderSource(String shaderSource) {
	this.shaderSource = shaderSource;
    }    

    synchronized void createMirrorObject() {
	// System.err.println("SourceCodeShaderRetained : createMirrorObject");

	if (mirror == null) {
	    SourceCodeShaderRetained  mirrorSCS = new SourceCodeShaderRetained();
	    mirror = mirrorSCS;
	}

	initMirrorObject();
    }
    
    /**
     * Initializes a mirror object.
     */
    synchronized void initMirrorObject() {
	mirror.source = source;

	((SourceCodeShaderRetained) mirror).set(shadingLanguage, shaderType, shaderSource);
	((SourceCodeShaderRetained) mirror).shaderData = null;	
    }

    synchronized void updateMirrorObject(int component, Object value) {
	System.err.println("SourceCodeShader.updateMirrorObject not implemented yet!");
    }

}
