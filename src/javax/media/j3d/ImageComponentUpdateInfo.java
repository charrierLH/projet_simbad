/*
 * $RCSfile: ImageComponentUpdateInfo.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:03 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Image Component update information for the users
 */
class ImageComponentUpdateInfo extends Object {

    int x = 0;
    int y = 0;
    int z = 0;
    int width = 0;
    int height = 0;
    int updateMask = 0;		// which resources need to be updated
				// canvas or renderer
    boolean entireImage = false;// true if the entire image needs to be updated
				// then none of the dimension info is to be
				// applied.
}
