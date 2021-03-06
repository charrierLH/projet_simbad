/*
 * $RCSfile: IntegerFreeList.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:06 $
 * $State: Exp $
 */

package javax.media.j3d;

class IntegerFreeList extends MemoryFreeList {

    int count = 0;

    // default the initial count to 1
    IntegerFreeList() {
	super("java.lang.Integer");
    }

    // sets up an initial count and an initial capacity for the freelist
    IntegerFreeList(int initialCount, int initCapacity) {
	super("java.lang.Integer", initCapacity);
	count = initialCount;
    }

    synchronized Object getObject() {
	if (size > 0) return super.removeLastElement();
	else return new Integer(++count);
    }

    public synchronized void clear() {
	super.clear();
	count = 0;
    }
    
}
