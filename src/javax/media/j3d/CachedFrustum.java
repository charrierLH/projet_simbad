/*
 * $RCSfile: CachedFrustum.java,v $
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

import javax.vecmath.*;

/**
 * The CachedFrustum class is used to pre compute data for a set of view
 * frustum planes which allows more efficient intersection testing.
 *
 * The CachedFrustum caches data for frustums for effecient intersection
 * testing.
 */

// this class assumes the planes are in the following order:
        // left plane =   frustumPlanes[0]
        // right plane =  frustumPlanes[1]
        // top plane =    frustumPlanes[2]
        // bottom plane = frustumPlanes[3]
        // front plane =  frustumPlanes[4]
        // back plane =   frustumPlanes[5]

class CachedFrustum {

    static final double EPSILON = 1.0E-8;
    Vector4d[] clipPlanes;
    Point3d[]  verts;
    Point3d upper,lower;  // bounding box of frustum
    Point3d center;  //  center of frustum


    /**
     * Constructs and initializes a new CachedFrustum using the values
     * provided in the argument.
     * @param planes array specifying the frustum's clip plane position
     */
    CachedFrustum(Vector4d[] planes) {
      int i;
      if( planes.length < 6 ) {
	 throw new IllegalArgumentException(J3dI18N.getString("CachedFrustum0"));
      }
      clipPlanes = new Vector4d[6];
      verts      = new Point3d[8];
      upper      = new Point3d();
      lower      = new Point3d();
      center     = new Point3d();

      for(i=0;i<8;i++){
        verts[i] = new Point3d();
      }

      for(i=0;i<6;i++){
	 clipPlanes[i] = new Vector4d( planes[i] );
      }
      computeValues(clipPlanes);
    }

    /**
     * Constructs and initializes a new default CachedFrustum
     * @param planes array specifying the frustum's clip planes
     */
    CachedFrustum() {
      int i;
      clipPlanes = new Vector4d[6];
      upper      = new Point3d();
      lower      = new Point3d();
      verts      = new Point3d[8];
      center     = new Point3d();

      for(i=0;i<8;i++){
        verts[i] = new Point3d();
      }
      for(i=0;i<6;i++){
	 clipPlanes[i] = new Vector4d();
      }

    }

    /**
     * Returns a string containing the values of the CachedFrustum.
     */
    public String toString() {
	return( clipPlanes[0].toString()+"\n"+
	        clipPlanes[1].toString()+"\n"+
	        clipPlanes[2].toString()+"\n"+
	        clipPlanes[3].toString()+"\n"+
	        clipPlanes[4].toString()+"\n"+
	        clipPlanes[5].toString()+"\n"+
		"corners="+"\n"+
	        verts[0].toString()+"\n"+
	        verts[1].toString()+"\n"+
	        verts[2].toString()+"\n"+
	        verts[3].toString()+"\n"+
	        verts[4].toString()+"\n"+
	        verts[5].toString()+"\n"+
	        verts[6].toString()+"\n"+
	        verts[7].toString()
		);
    }


    /**
     * Sets the values of the CachedFrustum based on a new set of frustum planes.
     * @param planes array specifying the frustum's clip planes
     */
    void  set(Vector4d[] planes) {
      int i;

      if( planes.length != 6 ) {
	 throw new IllegalArgumentException(J3dI18N.getString("CachedFrustum1"));
      }

      for(i=0;i<6;i++){
	 clipPlanes[i].set( planes[i] );
      }

      computeValues(clipPlanes);
    }

    /**
     * Computes cached values.
     * @param planes array specifying the frustum's clip planes
     */
    private void computeValues(Vector4d[] planes) {

        int i;

	// compute verts

	computeVertex( 0, 3, 4, verts[0]);  // front-bottom-left
	computeVertex( 0, 2, 4, verts[1]);  // front-top-left
	computeVertex( 1, 2, 4, verts[2]);  // front-top-right
	computeVertex( 1, 3, 4, verts[3]);  // front-bottom-right
	computeVertex( 0, 3, 5, verts[4]);  // back-bottom-left
	computeVertex( 0, 2, 5, verts[5]);  // back-top-left
	computeVertex( 1, 2, 5, verts[6]);  // back-top-right
	computeVertex( 1, 3, 5, verts[7]);  // back-bottom-right

	// compute bounding box

	upper.x = verts[0].x;
	upper.y = verts[0].y;
	upper.z = verts[0].z;
	lower.x = verts[0].x;
	lower.y = verts[0].y;
	lower.z = verts[0].z;

	center.x = verts[0].x;
	center.y = verts[0].y;
	center.z = verts[0].z;

	// find min and max in x-y-z directions

	for(i=1;i<8;i++) {
	    if( verts[i].x > upper.x) upper.x = verts[i].x; // xmax
	    if( verts[i].x < lower.x) lower.x = verts[i].x; // xmin

	    if( verts[i].y > upper.y) upper.y = verts[i].y; // ymay
	    if( verts[i].y < lower.y) lower.y = verts[i].y; // ymin

	    if( verts[i].z > upper.z) upper.z = verts[i].z; // zmaz
	    if( verts[i].z < lower.z) lower.z = verts[i].z; // zmin

	    center.x += verts[i].x;
	    center.y += verts[i].y;
	    center.z += verts[i].z;
	}

	center.x = center.x*0.125;
	center.y = center.y*0.125;
	center.z = center.z*0.125;

    }

    private void computeVertex( int a, int b, int c, Point3d vert) {
	double det,x,y,z;

	det = clipPlanes[a].x*clipPlanes[b].y*clipPlanes[c].z + clipPlanes[a].y*clipPlanes[b].z*clipPlanes[c].x +
	    clipPlanes[a].z*clipPlanes[b].x*clipPlanes[c].y - clipPlanes[a].z*clipPlanes[b].y*clipPlanes[c].x -
	    clipPlanes[a].y*clipPlanes[b].x*clipPlanes[c].z - clipPlanes[a].x*clipPlanes[b].z*clipPlanes[c].y;


	if( det*det < EPSILON ){
	    // System.err.println("************** Two planes are parallel : det = " + det);
	    return;       // two planes are parallel
	}

	det = 1.0/det;

	vert.x = (clipPlanes[b].y*clipPlanes[c].z - clipPlanes[b].z*clipPlanes[c].y) * -clipPlanes[a].w;
	vert.y = (clipPlanes[b].z*clipPlanes[c].x - clipPlanes[b].x*clipPlanes[c].z) * -clipPlanes[a].w;
	vert.z = (clipPlanes[b].x*clipPlanes[c].y - clipPlanes[b].y*clipPlanes[c].x) * -clipPlanes[a].w;

   vert.x += (clipPlanes[c].y*clipPlanes[a].z - clipPlanes[c].z*clipPlanes[a].y) * -clipPlanes[b].w;
   vert.y += (clipPlanes[c].z*clipPlanes[a].x - clipPlanes[c].x*clipPlanes[a].z) * -clipPlanes[b].w;
   vert.z += (clipPlanes[c].x*clipPlanes[a].y - clipPlanes[c].y*clipPlanes[a].x) * -clipPlanes[b].w;

   vert.x += (clipPlanes[a].y*clipPlanes[b].z - clipPlanes[a].z*clipPlanes[b].y) * -clipPlanes[c].w;
   vert.y += (clipPlanes[a].z*clipPlanes[b].x - clipPlanes[a].x*clipPlanes[b].z) * -clipPlanes[c].w;
   vert.z += (clipPlanes[a].x*clipPlanes[b].y - clipPlanes[a].y*clipPlanes[b].x) * -clipPlanes[c].w;

   vert.x = vert.x*det;
   vert.y = vert.y*det;
   vert.z = vert.z*det;

  }

}
