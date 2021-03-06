/*
 * $RCSfile: Leaf.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:08 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * The Leaf node is an abstract class for all scene graph nodes that
 * have no children.
 * Leaf nodes specify lights, geometry, and sounds. They specify special
 * linking and instancing capabilities for sharing scene graphs and
 * provide a view platform for positioning and orienting a view in the
 * virtual world.
 * <p>
 * NOTE: Applications should <i>not</i> extend this class directly.
 */

public abstract class Leaf extends Node {

    /**
     * Construct and initialize the Leaf object.
     */
    public Leaf(){
    }

}
