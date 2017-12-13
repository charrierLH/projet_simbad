/*
 * $RCSfile: OrderedChildInfo.java,v $
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

/**
 * List of orderedGroup children that needs to be added/removed for
 * the next frame. Note that the order in which they are removed and
 * added should be maintained after the renderer is done to get the
 * correct order of rendering.
 */
class OrderedChildInfo extends Object {

    static int ADD      = 0x1;
    static int REMOVE   = 0x2;

    
    /**
     * Type of operation, could be add/remove or set
     */
    int               type;

    /**
     * Ordered index at which this operation takes place
     */
    int               orderedId;

    /**
     * Child index at which this operation takes place
     */
    int               childId;

    /**
     * Value of the orderedCollection, only relavent for
     * add and set
     */
    OrderedCollection            value;


    // Maintains the order in which the ordered children
    // were added and removed
    OrderedChildInfo next;
    OrderedChildInfo prev;

    OrderedChildInfo(int t, int cid, int oid, OrderedCollection val) {
	type = t;
	orderedId = oid;
	childId = cid;
	value = val;
	prev = null;
	next = null;
	
    }

}
