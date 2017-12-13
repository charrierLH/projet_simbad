/*
 * $RCSfile: JoglCgShaderInfo.java,v $
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

class JoglCgShaderInfo extends JoglShaderObject {
  private CGprogram cgShader;
  private int j3dShaderType;
  private int shaderProfile;

  JoglCgShaderInfo() {
    super(0);
  }

  public void setCgShader(CGprogram shader)       { cgShader = shader;                  }
  public CGprogram getCgShader()                  { return cgShader;                    }
  public void setJ3DShaderType(int type)          { j3dShaderType = type;               }
  public int  getJ3DShaderType()                  { return j3dShaderType;               }
  public void setShaderProfile(int shaderProfile) { this.shaderProfile = shaderProfile; }
  public int  getShaderProfile()                  { return shaderProfile;               }
}
