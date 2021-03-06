/*
 * $RCSfile: ScreenViewCache.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.6 $
 * $Date: 2007/04/12 17:34:06 $
 * $State: Exp $
 */

package javax.media.j3d;

import java.awt.Dimension;
import javax.vecmath.*;

/**
 * The ScreenViewCache class is used to cache all API data
 * from the Screen3D object.
 */
class ScreenViewCache extends Object {
    // The screen associated with this screen view cache
    Screen3D screen;

    //
    // API/INPUT DATA
    //

    // The width and height of the screen in meters.
    double physicalScreenWidth;
    double physicalScreenHeight;

    // The width and height of the screen in pixels.
    int screenWidth;
    int screenHeight;

    // Mask that indicates Screen3D view dependence info. has changed,
    // and CanvasViewCache may need to recompute the final view matries. 
    // Issue 163: Array of dirty bits is used because the Renderer and
    // RenderBin run asynchronously. Now that they each have a separate
    // instance of CanvasViewCache (due to the fix for Issue 109), they
    // need separate dirty bits. Array element 0 is used for the Renderer and
    // element 1 is used for the RenderBin.
    int[] scrvcDirtyMask = new int[2];
    
    //
    // Tracker-base coordinate system to image-plate coordinate
    // system transform.  If head tracking is enabled, this transform
    // is a calibration constant.  If head tracking is not enabled,
    // this transform is not used.
    // This is used only in SCREEN_VIEW mode.
    //
    Transform3D trackerBaseToImagePlate = new Transform3D();

    //
    // Head-tracker coordinate system to left and right image-plate coordinate
    // system transforms.  If head tracking is enabled, these transforms
    // are calibration constants.  If head tracking is not enabled,
    // these transforms are not used.
    // These are used only in HMD_VIEW mode.
    //
    Transform3D headTrackerToLeftImagePlate = new Transform3D();
    Transform3D headTrackerToRightImagePlate = new Transform3D();


    //
    // DERIVED DATA
    //

    // Meters per pixel in the X and Y dimension
    double metersPerPixelX;
    double metersPerPixelY;


    /**
     * Take snapshot of all per-screen API parameters.
     */
    synchronized void snapshot() {

        // accumulate the dirty bits for offscreen because
        // the dirty bits will not be processed until renderOffScreen
        // or triggered by RenderBin at some little time
        if (screen.offScreen) {
            scrvcDirtyMask[0] |= screen.scrDirtyMask;
            scrvcDirtyMask[1] |= screen.scrDirtyMask;
        } else {
            scrvcDirtyMask[0] = screen.scrDirtyMask;
            scrvcDirtyMask[1] = screen.scrDirtyMask;
        }
        screen.scrDirtyMask = 0;

        physicalScreenWidth = screen.physicalScreenWidth;
	physicalScreenHeight = screen.physicalScreenHeight;
	screenWidth = screen.screenSize.width;
	screenHeight = screen.screenSize.height;
	
	screen.trackerBaseToImagePlate.getWithLock(trackerBaseToImagePlate);

	screen.headTrackerToLeftImagePlate.getWithLock
	    (headTrackerToLeftImagePlate);
	screen.headTrackerToRightImagePlate.getWithLock
	    (headTrackerToRightImagePlate);
	
	// This isn't really API data, but since we have no other derived
	// data, and it's a simple calculation, it's easier if we just do
	// it here.
	metersPerPixelX = physicalScreenWidth / (double) screenWidth;
	metersPerPixelY = physicalScreenHeight / (double) screenHeight;
    }


    /**
     * Constructs and initializes a ScreenViewCache object.
     */
    ScreenViewCache(Screen3D screen) {
	this.screen = screen;

	if (false)
	    System.err.println("Constructed a ScreenViewCache");
    }
}
