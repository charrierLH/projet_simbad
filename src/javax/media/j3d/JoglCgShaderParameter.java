/*
 * $RCSfile: JoglCgShaderParameter.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.3 $
 * $Date: 2007/02/09 17:17:48 $
 * $State: Exp $
 */

package javax.media.j3d;

import com.sun.opengl.cg.*;

class JoglCgShaderParameter extends JoglShaderObject {
  private CGparameter vParam;
  private CGparameter fParam;

  JoglCgShaderParameter(CGparameter vParam,
                        CGparameter fParam) {
    super(0);
    this.vParam = vParam;
    this.fParam = fParam;
  }

  CGparameter vParam() {
    return vParam;
  }

  CGparameter fParam() {
    return fParam;
  }
}
