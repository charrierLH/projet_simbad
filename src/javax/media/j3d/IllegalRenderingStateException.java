/*
 * $RCSfile: IllegalRenderingStateException.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.5 $
 * $Date: 2007/02/09 17:18:02 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Indicates an illegal state for rendering. This is typically some sort of
 * resource or graphics device error encountered during rendering.
 */
public class IllegalRenderingStateException extends IllegalStateException {

    /**
     * Create the exception object with default values.
     */
    public IllegalRenderingStateException(){
    }

    /**
     * Create the exception object that outputs message.
     * @param str the message string to be output.
     */
    public IllegalRenderingStateException(String str){
	super(str);
    }

}
