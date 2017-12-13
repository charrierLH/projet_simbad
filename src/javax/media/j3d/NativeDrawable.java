/*
 * $RCSfile: NativeDrawable.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.3 $
 * $Date: 2007/02/09 17:18:11 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Graphics Drawable objects for native rendering pipeline.
 */
class NativeDrawable implements Drawable {

    // Native drawable pointer. On Windows it is the native HDC.
    // On X11 it is the handle to the native X11 drawable.
    private long nativeDrawable;

    NativeDrawable(long nativeDrawable) {
        this.nativeDrawable = nativeDrawable;
    }

    long getNativeDrawable() {
        return nativeDrawable;
    }

}
