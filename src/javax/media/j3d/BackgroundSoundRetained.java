/*
 * $RCSfile: BackgroundSoundRetained.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:17:51 $
 * $State: Exp $
 */

package javax.media.j3d;
 
/**
 *  BackgroundSound is a class for sounds that are not spatially rendered.
 *  These sounds are simply added to the stereo sound mix without modification.
 *  These could be used to play mono or stereo music, or ambient sound effects.
 */
class BackgroundSoundRetained extends SoundRetained {

    BackgroundSoundRetained() {
        this.nodeType = NodeRetained.BACKGROUNDSOUND;
	localBounds = new BoundingBox();
	((BoundingBox)localBounds).setLower( 1.0, 1.0, 1.0);
	((BoundingBox)localBounds).setUpper(-1.0,-1.0,-1.0);
    }
}
