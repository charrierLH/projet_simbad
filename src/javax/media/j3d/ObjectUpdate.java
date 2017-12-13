/*
 * $RCSfile: ObjectUpdate.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:13 $
 * $State: Exp $
 */

package javax.media.j3d;

/*
 * A Object Update interface.  Any object that can be put in the ObjectUpdate list
 * must implement this interface.
 */

interface ObjectUpdate {

    /**
     * The actual update function.
     */
    abstract void updateObject();
}
