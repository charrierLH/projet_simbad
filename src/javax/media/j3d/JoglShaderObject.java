/*
 * $RCSfile: JoglShaderObject.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.3 $
 * $Date: 2007/02/09 17:17:49 $
 * $State: Exp $
 */

package javax.media.j3d;

class JoglShaderObject implements ShaderProgramId, ShaderId, ShaderAttrLoc {
  private int val;

  JoglShaderObject(int val) {
    this.val = val;
  }

  int getValue() {
    return val;
  }
}
