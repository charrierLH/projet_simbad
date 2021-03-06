/*
 * $RCSfile: Text3DRenderMethod.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.6 $
 * $Date: 2007/02/09 17:18:25 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * The RenderMethod interface is used to create various ways to render
 * different geometries.
 */

class Text3DRenderMethod implements RenderMethod {

    /**
     * The actual rendering code for this RenderMethod
     */
    public boolean render(RenderMolecule rm, Canvas3D cv,
			  RenderAtomListInfo ra, int dirtyBits) {

	boolean isNonUniformScale;
	Transform3D trans = null;
	
        GeometryArrayRetained geo = (GeometryArrayRetained)ra.geometry();
        geo.setVertexFormat((rm.useAlpha && ((geo.vertexFormat & 
					      GeometryArray.COLOR) != 0)), 
			    rm.textureBin.attributeBin.ignoreVertexColors, cv.ctx);

	if (rm.doInfinite) {
	    cv.updateState(dirtyBits);
	    while (ra != null) {
		trans = ra.infLocalToVworld;
		isNonUniformScale = !trans.isCongruent();
		cv.setModelViewMatrix(cv.ctx, cv.vworldToEc.mat, trans);
		
		ra.geometry().execute(cv, ra.renderAtom, isNonUniformScale,
				      (rm.useAlpha && ra.geometry().noAlpha),
				      rm.alpha,
				      cv.screen.screen,
				      rm.textureBin.attributeBin.ignoreVertexColors);
		ra = ra.next;
	    }
	    return true;
	}
	
	boolean isVisible = false; // True if any of the RAs is visible.
	while (ra != null) {
	    if (cv.ra == ra.renderAtom) {
		if (cv.raIsVisible) {
		    cv.updateState(dirtyBits);
		    trans = ra.localToVworld;
		    isNonUniformScale = !trans.isCongruent();
		    
		    cv.setModelViewMatrix(cv.ctx, cv.vworldToEc.mat, trans);
		    ra.geometry().execute(cv, ra.renderAtom, isNonUniformScale,
					  (rm.useAlpha && ra.geometry().noAlpha),
					  rm.alpha,
					  cv.screen.screen,
					  rm.textureBin.attributeBin.
					  ignoreVertexColors);
		    isVisible = true;
		}
	    }
	    else {
		if (!VirtualUniverse.mc.viewFrustumCulling ||
		    ra.renderAtom.localeVwcBounds.intersect(cv.viewFrustum)) {
		    cv.updateState(dirtyBits);
		    cv.raIsVisible = true;
		    trans = ra.localToVworld;
		    isNonUniformScale = !trans.isCongruent();
		    
		    cv.setModelViewMatrix(cv.ctx, cv.vworldToEc.mat, trans);
		    ra.geometry().execute(cv, ra.renderAtom, isNonUniformScale,
					  (rm.useAlpha && ra.geometry().noAlpha),
					  rm.alpha,
					  cv.screen.screen,
					  rm.textureBin.attributeBin.
					  ignoreVertexColors);
		    isVisible = true;
		}
		else {
		    cv.raIsVisible = false;
		}
		cv.ra = ra.renderAtom;
	    }
		
	    ra = ra.next;
	    
	}
	
        geo.disableGlobalAlpha(cv.ctx, 
			       (rm.useAlpha && ((geo.vertexFormat & 
						 GeometryArray.COLOR) != 0)), 
			       rm.textureBin.attributeBin.ignoreVertexColors);
   
	return isVisible;
    }
}
