/*
 * $RCSfile: AssertionFailureException.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:17:50 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Indicates an assertion failure.
 */

class AssertionFailureException extends RuntimeException {

    /**
     * Create the exception object with default values.
     */
    AssertionFailureException() {
    }

    /**
     * Create the exception object that outputs message.
     * @param str the message string to be output.
     */
    AssertionFailureException(String str) {
	super(str);
    }

}
