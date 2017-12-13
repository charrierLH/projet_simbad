/*
 * $RCSfile: BHLeafInterface.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:17:51 $
 * $State: Exp $
 */

package javax.media.j3d;

interface BHLeafInterface {
    
    abstract BoundingBox computeBoundingHull();

    abstract boolean isEnable();

    abstract boolean isEnable(int visibilityPolicy);
    
    // Can't use getLocale, it is used by BranchGroupRetained
    abstract Locale getLocale2();
    
}
