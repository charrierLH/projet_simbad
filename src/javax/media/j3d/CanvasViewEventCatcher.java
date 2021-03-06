/*
 * $RCSfile: CanvasViewEventCatcher.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.7 $
 * $Date: 2007/04/12 17:34:04 $
 * $State: Exp $
 */

package javax.media.j3d;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * The CanvasViewEventCatcher class is used to track events on a Canvas3D that
 * may cause view matries to change.
 * 
 */
class CanvasViewEventCatcher extends ComponentAdapter {

    // The canvas associated with this event catcher
    private Canvas3D canvas;
    private static final boolean DEBUG = false;

    CanvasViewEventCatcher(Canvas3D c) {
	canvas = c;
    }
    
    public void componentResized(ComponentEvent e) {
	if (DEBUG) {
	    System.err.println("Component resized " + e);
	}
	
	if(e.getComponent() == canvas ) {
	    if (DEBUG) {
		System.err.println("It is canvas!");
	    }
	    synchronized(canvas) {
                synchronized (canvas.dirtyMaskLock) {
                    canvas.cvDirtyMask[0] |= Canvas3D.MOVED_OR_RESIZED_DIRTY;
                    canvas.cvDirtyMask[1] |= Canvas3D.MOVED_OR_RESIZED_DIRTY;
                }
		canvas.resizeGraphics2D = true;
	    }
	    
	    // see comment below
	    try {
		canvas.newSize = canvas.getSize();
		canvas.newPosition = canvas.getLocationOnScreen();
	    } catch (IllegalComponentStateException ex) {}

	}
    }
    
    public void componentMoved(ComponentEvent e) {
	if (DEBUG) {
	    System.err.println("Component moved " + e);
	}

        synchronized(canvas) {
            synchronized (canvas.dirtyMaskLock) {
                canvas.cvDirtyMask[0] |= Canvas3D.MOVED_OR_RESIZED_DIRTY;
                canvas.cvDirtyMask[1] |= Canvas3D.MOVED_OR_RESIZED_DIRTY;
            }
        }
	// Can't sync. with canvas lock since canvas.getLocationOnScreen()
	// required Component lock. The order is reverse of 
	// removeNotify() lock sequence which required Component lock
	// first, then canvas lock in removeComponentListener()
	
	try {
	    canvas.newSize = canvas.getSize();
	    canvas.newPosition = canvas.getLocationOnScreen();
	} catch (IllegalComponentStateException ex) {}

    }
    
}
