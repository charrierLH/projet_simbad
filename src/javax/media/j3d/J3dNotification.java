/*
 * $RCSfile: J3dNotification.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.5 $
 * $Date: 2007/02/09 17:18:07 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * J3dNotification is used to hold data for asynchronous error notification.
 */

class J3dNotification extends Object {
    /**
     * The various notification types.
     */
    static final int INVALID_TYPE       = -1;
    static final int SHADER_ERROR       =  0;
    static final int RENDERING_ERROR    =  1;

    /**
     * This holds the type of this message
     */
    int type = INVALID_TYPE;

    /**
     * The universe that this message originated from
     */
    VirtualUniverse universe;

    /**
     * The arguements for a message, 6 for now
     */
    static final int MAX_ARGS = 6;

    Object[] args = new Object[MAX_ARGS];

}
