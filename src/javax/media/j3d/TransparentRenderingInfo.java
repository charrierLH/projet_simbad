/*
 * $RCSfile: TransparentRenderingInfo.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.6 $
 * $Date: 2007/04/26 21:51:17 $
 * $State: Exp $
 */

package javax.media.j3d;


class TransparentRenderingInfo extends Object implements com.sun.j3d.utils.scenegraph.transparency.TransparencySortGeom {
    // For DepthSortedTransparency, rm is the rendermolecule
    // that this rInfo is part of
    // For non depth sorted transparency, rm is one of the rendermolecules
    // in the textureBin that is rendered, all renderMolecules under
    // rm.textureBin's will be rendered
    RenderMolecule rm;
    RenderAtomListInfo rInfo;
    TransparentRenderingInfo prev;
    TransparentRenderingInfo next;
    GeometryAtom geometryAtom;
    double zVal; // Used in DepthSorted Transparency
    // XXXX: Add Dirty info

    /**
     * update state before rendering transparent objects 
     */
    boolean updateState(Canvas3D cv) {

	TextureBin textureBin = rm.textureBin;
	AttributeBin attributeBin = textureBin.attributeBin;
        ShaderBin shaderBin = textureBin.shaderBin;
        
	// Get a collection to check if switch is on

	RenderMolecule rm = textureBin.transparentRMList ;

	// Optimization to skip updating Attributes if
	// all switch are off. Note that switch condition
	// is check again in rm.render().
	while (rm != null) {
	    if (rm.isSwitchOn()) {
		break;
	    }
	    if (rm.next != null) {
		rm = rm.next;
	    } else {
		rm = rm.nextMap;
	    }
	}
	
	if (rm == null) {
	    return false;
	}

        // XXXX : Code cleanup needed : The following code segment should simply test
        //        each bin independently and update it if necessary.
        if (cv.environmentSet != attributeBin.environmentSet) {
            
            boolean visible = (attributeBin.definingRenderingAttributes == null ||
                    attributeBin.definingRenderingAttributes.visible);
            
            if ( (attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy
                    == View.VISIBILITY_DRAW_VISIBLE && !visible) ||
                    (attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy
                    == View.VISIBILITY_DRAW_INVISIBLE && visible)) {
                return false;
            }
            
            // Fix to issue 314. Set the appropriate bits for the dirty bins 
            // and call the update state method.
            cv.setStateToUpdate(Canvas3D.LIGHTBIN_BIT, attributeBin.environmentSet.lightBin);
            cv.setStateToUpdate(Canvas3D.ENVIRONMENTSET_BIT, attributeBin.environmentSet);
            cv.setStateToUpdate(Canvas3D.ATTRIBUTEBIN_BIT, attributeBin);
            cv.setStateToUpdate(Canvas3D.SHADERBIN_BIT, shaderBin);
            cv.updateEnvState();

        } else if (cv.attributeBin != attributeBin) {
            boolean visible = (attributeBin.definingRenderingAttributes == null ||
                    attributeBin.definingRenderingAttributes.visible);
            
            if ( (attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy
                    == View.VISIBILITY_DRAW_VISIBLE && !visible) ||
                    (attributeBin.environmentSet.renderBin.view.viewCache.visibilityPolicy
                    == View.VISIBILITY_DRAW_INVISIBLE && visible)) {
                return false;
            }
            
            // Fix to issue 314. Set the appropriate bits for the dirty bins 
            // and call the update state method.
            cv.setStateToUpdate(Canvas3D.ATTRIBUTEBIN_BIT, attributeBin);
            cv.setStateToUpdate(Canvas3D.SHADERBIN_BIT, shaderBin);
            cv.updateEnvState();
            
        } else if (cv.shaderBin != shaderBin) {

            // Fix to issue 314. Set the appropriate bits for the dirty bins 
            // and call the update state method.
            cv.setStateToUpdate(Canvas3D.SHADERBIN_BIT, shaderBin);
            cv.updateEnvState();
 
        } 

        return true;
    }

    void render(Canvas3D cv) {
	if (updateState(cv)) {      
	    rm.textureBin.render(cv, rm.textureBin.transparentRMList);
	}
    }


    void sortRender(Canvas3D cv) {
	if (updateState(cv)) {
	    rm.textureBin.render(cv, this);
	}
    }

    public double getDistanceSquared() {
        return zVal;
    }

    public Geometry getGeometry() {
        // XXXX: verify 0 is always the correct index. Assumption is that for 
        // Shape3D with multiple geometry each geometry is put in it's 
        // own geometryAtom.
        if (geometryAtom.geometryArray[0]==null)
            return null;
        return (Geometry)geometryAtom.geometryArray[0].source;
    }

    public void getLocalToVWorld(Transform3D localToVW) {
        localToVW.set(rm.localToVworld[NodeRetained.LAST_LOCAL_TO_VWORLD]);
    }

    public Shape3D getShape3D() {
        return (Shape3D)geometryAtom.source.source;
    }
}
