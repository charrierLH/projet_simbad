/*
 * $RCSfile: DepthComponentNative.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:17:57 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * A 2D array of depth (Z) values stored in the most efficient format for a
 * particular device.  Values are not accessible by the user and may only be
 * used to read the Z values and subsequently write them back.
 */

public class DepthComponentNative extends DepthComponent {
    /**
     * Package scope defualt constructor for use by cloneNodeComponent
     */
    DepthComponentNative() {
    }

    /**
     * Constructs a new native depth (z-buffer) component object with the
     * specified width and height.
     * @param width the width of the array of depth values
     * @param height the height of the array of depth values
     */
    public DepthComponentNative(int width, int height) {
	((DepthComponentNativeRetained)this.retained).initialize(width, height);
    }

    /**
     * Copies the depth data from this object to the specified array.
     * @param depthData array of ints that will receive a copy of
     * the depth data
     */
    void getDepthData(int[] depthData) {
	((DepthComponentNativeRetained)this.retained).getDepthData(depthData);
    }

    /**
     * Creates a retained mode DepthComponentIntRetained object that this
     * DepthComponentInt component object will point to.
     */
    void createRetained() {
	this.retained = new DepthComponentNativeRetained();
	this.retained.setSource(this);
    }

   /**
     * Creates a new DepthComponentNative object.  Called from a Leaf node's
     * <code>duplicateNode</code> method.
     *
     * @return a duplicate of the DepthComponentNative object.
     *
     * @see Node#duplicateNode
     * @see Node#cloneTree
     * @see NodeComponent#setDuplicateOnCloneTree
     */
    public NodeComponent cloneNodeComponent() {
	DepthComponentNativeRetained rt = (DepthComponentNativeRetained) retained;
        DepthComponentNative d = new DepthComponentNative(rt.width,
							  rt.height);
        d.duplicateNodeComponent(this);
        return d;
    }

  
   /**
     * Copies all node information from <code>originalNodeComponent</code> into
     * the current node.  This method is called from the
     * <code>duplicateNode</code> method. This routine does
     * the actual duplication of all "local data" (any data defined in
     * this object). 
     *
     * @param originalNodeComponent the original node to duplicate.
     * @param forceDuplicate when set to <code>true</code>, causes the
     *  <code>duplicateOnCloneTree</code> flag to be ignored.  When
     *  <code>false</code>, the value of each node's
     *  <code>duplicateOnCloneTree</code> variable determines whether
     *  NodeComponent data is duplicated or copied.
     *
     *
     * @see Node#cloneTree
     * @see NodeComponent#setDuplicateOnCloneTree
     */
    void duplicateAttributes(NodeComponent originalNodeComponent,
			     boolean forceDuplicate) { 
	super.duplicateAttributes(originalNodeComponent, forceDuplicate);
      
	int originalData[] = ((DepthComponentNativeRetained)
			      originalNodeComponent.retained).depthData;

	int currentData[] =  ((DepthComponentNativeRetained) retained).depthData;

	if (originalData != null) {
	    for (int i=0; i < originalData.length; i++)
		currentData[i] = originalData[i];
	}
    }
}
