/*
 * $RCSfile: NnuId.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:11 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Defines a "not necessarily unique ID"
 */

interface NnuId {
    
    abstract int equal(NnuId obj);

    abstract int getId();
    
}
