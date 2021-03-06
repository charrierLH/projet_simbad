/*
 * $RCSfile: ShaderAttributeRetained.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.6 $
 * $Date: 2007/03/30 18:40:14 $
 * $State: Exp $
 */

package javax.media.j3d;

import javax.vecmath.*;

/**
 * The ShaderAttributeRetained object encapsulates a uniform attribute for a
 * shader programs.
 */

abstract class ShaderAttributeRetained extends NodeComponentRetained {

    /**
     * Name of the shader attribute (immutable)
     */
    String attrName;

    /**
     * Package scope constructor
     */
    ShaderAttributeRetained() {
    }

    void initializeAttrName(String attrName) {
	this.attrName = attrName;
    }

    /**
     * Retrieves the name of this shader attribute.
     *
     * @return the name of this shader attribute
     */
    String getAttributeName() {
	return attrName;
    }

    void initMirrorObject() {
	((ShaderAttributeObjectRetained)mirror).initializeAttrName(this.attrName);
    }

}
