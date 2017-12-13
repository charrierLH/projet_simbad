/*
 * $RCSfile: RotationInterpolator.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:19 $
 * $State: Exp $
 */

package javax.media.j3d;


/**
 * Rotation interpolator behavior.  This class defines a behavior
 * that modifies the rotational component of its target TransformGroup
 * by linearly interpolating between a pair of specified angles
 * (using the value generated by the specified Alpha object).
 * The interpolated angle is used to generate a rotation transform
 * about the local Y-axis of this interpolator.
 */

public class RotationInterpolator extends TransformInterpolator {

    float minimumAngle;
    float maximumAngle;
    private Transform3D rotation = new Transform3D();

    // We can't use a boolean flag since it is possible 
    // that after alpha change, this procedure only run
    // once at alpha.finish(). So the best way is to
    // detect alpha value change.
    private float prevAlphaValue = Float.NaN;
    private WakeupCriterion passiveWakeupCriterion = 
    (WakeupCriterion) new WakeupOnElapsedFrames(0, true);
    
    // non-public, default constructor used by cloneNode
    RotationInterpolator() {
    }

    /**
      * Constructs a trivial rotation interpolator with a specified target,
      * an default axisOfTranform set to identity, a minimum angle of 0.0f, and
      * a maximum angle of 2*pi radians.
      * @param alpha The alpha object for this Interpolator
      * @param target The target for this rotation Interpolator
      */
    public RotationInterpolator(Alpha alpha, TransformGroup target) {
	super(alpha, target);
	this.minimumAngle = 0.0f;
	this.maximumAngle = 2.0f*(float)Math.PI;
    }


    /**
     * Constructs a new rotation interpolator that varies the target
     * transform node's rotational component.
     * @param alpha the alpha generator to use in the rotation computation
     * @param target the TransformGroup node affected by this interpolator
     * @param axisOfTransform the transform that defines the local coordinate
     * system in which this interpolator operates.  The rotation is done
     * about the Y-axis of this local coordinate system.
     * @param minimumAngle the starting angle in radians
     * @param maximumAngle the ending angle in radians
     */
    public RotationInterpolator(Alpha alpha,
				TransformGroup target,
				Transform3D axisOfTransform,
				float minimumAngle,
				float maximumAngle) {
	super(alpha, target, axisOfTransform);
	this.minimumAngle = minimumAngle;
	this.maximumAngle = maximumAngle;
    }

    /**
      * This method sets the minimumAngle for this interpolator, in
      * radians.
      * @param angle the new minimal angle
      */
    public void setMinimumAngle(float angle) {
	this.minimumAngle = angle;
    }

    /**
      * This method retrieves this interpolator's minimumAngle, in 
      * radians.
      * @return the interpolator's minimal angle value
      */
    public float getMinimumAngle() {
	return this.minimumAngle;
    }

    /**
      * This method sets the maximumAngle for this interpolator, in
      * radians.
      * @param angle the new maximal angle value
      */
    public void setMaximumAngle(float angle) {
	this.maximumAngle = angle;
    }

    /**
      * This method retrieves this interpolator's maximumAngle, in 
      * radians.
      * @return the interpolator's maximal angle value
      */
    public float getMaximumAngle() {
	return this.maximumAngle;
    }

    /**
     * @deprecated As of Java 3D version 1.3, replaced by
     * <code>TransformInterpolator.setTransformAxis(Transform3D)</code>
     */
     public void setAxisOfRotation(Transform3D axisOfRotation) {
        setTransformAxis(axisOfRotation);
    }
       
    /**
     * @deprecated As of Java 3D version 1.3, replaced by
     * <code>TransformInterpolator.getTransformAxis()</code>
     */
    public Transform3D getAxisOfRotation() {
        return getTransformAxis();
    }
    
    /**
     * Computes the new transform for this interpolator for a given
     * alpha value.
     *
     * @param alphaValue alpha value between 0.0 and 1.0
     * @param transform object that receives the computed transform for
     * the specified alpha value
     *
     * @since Java 3D 1.3
     */
    public void computeTransform(float alphaValue, Transform3D transform) {
	double val = (1.0-alphaValue)*minimumAngle + alphaValue*maximumAngle;

	// construct a Transform3D from:  axis * rotation * axisInverse
	rotation.rotY(val);
	transform.mul(axis, rotation);
	transform.mul(transform, axisInverse);
    }

    /**
     * Used to create a new instance of the node.  This routine is called
     * by <code>cloneTree</code> to duplicate the current node.
     * @param forceDuplicate when set to <code>true</code>, causes the
     *  <code>duplicateOnCloneTree</code> flag to be ignored.  When
     *  <code>false</code>, the value of each node's
     *  <code>duplicateOnCloneTree</code> variable determines whether
     *  NodeComponent data is duplicated or copied.
     *
     * @see Node#cloneTree
     * @see Node#cloneNode
     * @see Node#duplicateNode
     * @see NodeComponent#setDuplicateOnCloneTree
     */
    public Node cloneNode(boolean forceDuplicate) {
        RotationInterpolator ri = new RotationInterpolator();
        ri.duplicateNode(this, forceDuplicate);
        return ri;
    }


    /**
     * Copies all RotationInterpolator information from
     * <code>originalNode</code> into
     * the current node.  This method is called from the
     * <code>cloneNode</code> method which is, in turn, called by the
     * <code>cloneTree</code> method.<P> 
     *
     * @param originalNode the original node to duplicate.
     * @param forceDuplicate when set to <code>true</code>, causes the
     *  <code>duplicateOnCloneTree</code> flag to be ignored.  When
     *  <code>false</code>, the value of each node's
     *  <code>duplicateOnCloneTree</code> variable determines whether
     *  NodeComponent data is duplicated or copied.
     *
     * @exception RestrictedAccessException if this object is part of a live
     *  or compiled scenegraph.
     *
     * @see Node#duplicateNode
     * @see Node#cloneTree
     * @see NodeComponent#setDuplicateOnCloneTree
     */
    void duplicateAttributes(Node originalNode, boolean forceDuplicate) {
        super.duplicateAttributes(originalNode, forceDuplicate);

	RotationInterpolator ri = (RotationInterpolator) originalNode;
	    
	setMinimumAngle(ri.getMinimumAngle());
        setMaximumAngle(ri.getMaximumAngle());

    }


}
