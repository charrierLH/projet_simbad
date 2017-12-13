/*
 * $RCSfile: IllegalSceneGraphException.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:02 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Indicates an illegal Java 3D scene graph.
 * For example, the following is illegal:
 * <ul>
 * <li>A ViewPlatform node under a ViewSpecificGroup</li>
 * </ul>
 *
 * @since Java 3D 1.3
 */

public class IllegalSceneGraphException extends RuntimeException {

    /**
     * Create the exception object with default values.
     */
    public IllegalSceneGraphException() {
    }

    /**
     * Create the exception object that outputs message.
     * @param str the message string to be output.
     */
    public IllegalSceneGraphException(String str) {
	super(str);
    }
}
