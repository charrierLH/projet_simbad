/*
 * $RCSfile: RenderingErrorListener.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.3 $
 * $Date: 2007/02/09 17:18:18 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Listener interface for monitoring Java 3D rendering errors.
 *
 * @see VirtualUniverse#addRenderingErrorListener
 *
 * @since Java 3D 1.5
 */
public interface RenderingErrorListener {
    /**
     * Invoked when an error occurs in the Java 3D rendering system.
     *
     * @param error object that contains the details of the error.
     */
    public void errorOccurred(RenderingError error);
}
