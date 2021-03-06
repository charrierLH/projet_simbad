/*
 * $RCSfile: ShaderErrorListener.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:22 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Listener interface for monitoring errors in Shader Programs.
 * Compile and link errors are reported by the shader compiler, as are
 * runtime errors, such as those resulting from shader attributes that
 * aren't found or are of the wrong type.
 *
 * @see VirtualUniverse#addShaderErrorListener
 *
 * @since Java 3D 1.4
 */
public interface ShaderErrorListener {
    /**
     * Invoked when an error occurs while compiling, linking or
     * executing a programmable shader.
     *
     * @param error object that contains the details of the error.
     */
    public void errorOccurred(ShaderError error);
}
