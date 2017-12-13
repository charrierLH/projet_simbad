/*
 * $RCSfile: NodeComponentUpdate.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:12 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * A Node Component Update interface.  Any object that can be put in the 
 * node component updateCheck list must implement this interface.
 */

interface NodeComponentUpdate {

    /**
     * The actual update function.
     */
    abstract void updateNodeComponentCheck();
}
