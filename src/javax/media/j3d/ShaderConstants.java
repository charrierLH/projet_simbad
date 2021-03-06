/*
 * $RCSfile: ShaderConstants.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.2 $
 * $Date: 2007/04/27 15:27:24 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * The ShaderConstants class contains internal constants used by other
 * Shader classes.
 */
class ShaderConstants extends Object {

    //
    // The following bits are used in the messages for various Shader objects.
    //

    // ShaderAttributeSet bits -- indicates which attribute
    // operation in this ShaderAttributeSet object is needed.
    static final int ATTRIBUTE_SET_PUT       = 0x0001;
    static final int ATTRIBUTE_SET_REMOVE    = 0x0002;
    static final int ATTRIBUTE_SET_CLEAR     = 0x0004;

    // ShaderAttribute bits
    static final int ATTRIBUTE_VALUE_UPDATE  = 0x0008;

}
