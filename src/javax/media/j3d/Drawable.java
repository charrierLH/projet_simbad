/*
 * $RCSfile: Drawable.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.3 $
 * $Date: 2007/02/09 17:17:58 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Tagging interface for drawable (window) objects. The rendering pipelines
 * will define concrete classes that implement this interface. All code that
 * uses the tagged objects will be in the pipelines.
 */
interface Drawable {
    // No methods or constants defined at this time
}
