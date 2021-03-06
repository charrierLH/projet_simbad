/*
 * $RCSfile: BHLeafNode.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.5 $
 * $Date: 2007/04/12 17:34:03 $
 * $State: Exp $
 */

package javax.media.j3d;

import javax.vecmath.*;

class BHLeafNode extends BHNode {
    
    BHLeafInterface leafIF;
    
    BHLeafNode() {
	super();
	nodeType = BH_TYPE_LEAF;
	leafIF = null;
    }
    
    BHLeafNode(BHNode parent) {
	super(parent);
	nodeType = BH_TYPE_LEAF;
    }
    
    BHLeafNode(BHLeafInterface lIF) {
	super();
	nodeType = BH_TYPE_LEAF;
	leafIF = lIF;
    }
    
    BHLeafNode(BHNode parent, BHLeafInterface lIF) {
	super(parent);
	leafIF = lIF;
	nodeType = BH_TYPE_LEAF;
    }
    
    BHLeafNode(BHNode parent, BoundingBox bHull) {
	super(parent, bHull);
	nodeType = BH_TYPE_LEAF; 
    }
    
    BHLeafNode(BHNode parent, BHLeafInterface lIF, BoundingBox bHull) {
	super(parent, bHull);
	leafIF = lIF;
	nodeType = BH_TYPE_LEAF;
    }
    
    void computeBoundingHull() {	
	bHull = leafIF.computeBoundingHull();
    }

    void updateMarkedBoundingHull() {
	
	if(mark == false)
	    return;

	computeBoundingHull();
	mark = false;	
    }

    boolean isEnable() {
	return leafIF.isEnable();
    }

    boolean isEnable(int vis) {
	return leafIF.isEnable(vis);
    }
    
    Locale getLocale() {
	return leafIF.getLocale2();
    }

    void destroyTree(BHNode[] bhArr, int[] index) {
	if(bhArr.length <= index[0]) {
	    // System.err.println("BHLeafNode : Problem bhArr overflow!!!");
	    return;
	}

	parent = null;
	bhArr[index[0]] = this;	
	index[0]++;
    }
	    
}
