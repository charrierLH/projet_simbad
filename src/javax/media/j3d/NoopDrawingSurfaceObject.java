/*
 * $RCSfile: NoopDrawingSurfaceObject.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:12 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * The DrawingSurfaceObject class is used to manage native drawing surface 
 */
class NoopDrawingSurfaceObject extends DrawingSurfaceObject {
    
    NoopDrawingSurfaceObject(Canvas3D cv) {
        super(cv);
        
        System.err.println("NoopDrawingSurfaceObject constructed");
    }

    synchronized boolean renderLock() {
//        System.err.println("NoopDrawingSurfaceObject.renderLock()");
        gotDsiLock = true;
        return true;
    }

    synchronized void unLock() {
//        System.err.println("NoopDrawingSurfaceObject.unLock()");
        gotDsiLock = false;
    }

    synchronized void getDrawingSurfaceObjectInfo() {
        if (canvas.drawable == null) {
            System.err.println(
                    "NoopDrawingSurfaceObject.getDrawingSurfaceObjectInfo: window = "
                    + canvas.drawable);

            canvas.drawable = new NoopDrawable();
        }
    }

    synchronized void invalidate() {
        System.err.println("NoopDrawingSurfaceObject.invalidate()");
    }

    /**
     * Dummy drawable for noop pipeline
     */
    static class NoopDrawable implements Drawable {
    }

}
