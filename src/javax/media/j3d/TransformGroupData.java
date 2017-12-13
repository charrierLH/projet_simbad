/*
 * $RCSfile: TransformGroupData.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.5 $
 * $Date: 2007/02/09 17:18:27 $
 * $State: Exp $
 */

package javax.media.j3d;

class TransformGroupData extends NodeData {
    // per path node data
    // XXXX: replace per path mirror objects with node data
    // XXXX: move other TransfromGroup related data here
    boolean switchDirty = false;

    // use for eliminate multiple updates and generate unique targets
    boolean markedDirty = false;
}
