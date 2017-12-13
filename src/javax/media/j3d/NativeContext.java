/*
 * $RCSfile: NativeContext.java,v $
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
 * Graphics context objects for native rendering pipeline.
 */
class NativeContext implements Context {

    // Native context pointer
    private long nativeCtx;

    NativeContext(long nativeCtx) {
        this.nativeCtx = nativeCtx;
    }

    long getNativeCtx() {
        return nativeCtx;
    }

}
