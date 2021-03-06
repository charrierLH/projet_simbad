/*
 * $RCSfile: PickRay.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:15 $
 * $State: Exp $
 */

package javax.media.j3d;

import javax.vecmath.*;

/**
 * PickRay is an infinite ray pick shape.  It can
 * be used as an argument to the picking methods in BranchGroup and Locale.
 *
 * @see BranchGroup#pickAll
 * @see Locale#pickAll
 */
public final class PickRay extends PickShape {

    Point3d origin;
    Vector3d direction;

    /**
     * Constructs an empty PickRay.  The origin and direction of the
     * ray are initialized to (0,0,0).
     */
    public PickRay() {
	origin = new Point3d();
	direction = new Vector3d();
    }

    /**
     * Constructs an infinite ray pick shape from the specified
     * parameters.
     * @param origin the origin of the ray.
     * @param direction the direction of the ray.
     */
    public PickRay(Point3d origin, Vector3d direction) {
	this.origin = new Point3d(origin);
	this.direction = new Vector3d(direction);
    }


    /**
     * Sets the parameters of this PickRay to the specified values.
     * @param origin the origin of the ray.
     * @param direction the direction of the ray.
     */
    public void set(Point3d origin, Vector3d direction) {
	this.origin.x = origin.x;
	this.origin.y = origin.y;
	this.origin.z = origin.z;
	this.direction.x = direction.x;
	this.direction.y = direction.y;
	this.direction.z = direction.z;
    }

    /**
     * Retrieves the parameters from this PickRay.
     * @param origin the Point3d object into which the origin will be copied.
     * @param direction the Vector3d object into which the direction
     * will be copied.
     */
    public void get(Point3d origin, Vector3d direction) {
	origin.x = this.origin.x;
	origin.y = this.origin.y;
	origin.z = this.origin.z;
	direction.x = this.direction.x;
	direction.y = this.direction.y;
	direction.z = this.direction.z;
    }

    /**
     * Return true if shape intersect with bounds.
     * The point of intersection is stored in pickPos.
     */
    final boolean intersect(Bounds bounds, Point4d pickPos) {
	return bounds.intersect(origin, direction,  pickPos);
    }


    // Only use within J3D.
    // Return a new PickRay that is the transformed (t3d) of this pickRay.  
    PickShape transform(Transform3D t3d) {

	Point3d end = new Point3d();
    
	PickRay newPR = new PickRay(origin, direction);
	
	end.x = origin.x + direction.x;
	end.y = origin.y + direction.y;
	end.z = origin.z + direction.z;
	
	t3d.transform(newPR.origin);
	t3d.transform(end);
	
	newPR.direction.x = end.x - newPR.origin.x;
	newPR.direction.y = end.y - newPR.origin.y;
	newPR.direction.z = end.z - newPR.origin.z;
	newPR.direction.normalize();
	
	return newPR;
    }


    Point3d getStartPoint() {
	return origin;
    }

    int getPickType() {
	return PICKRAY;
    }
}
