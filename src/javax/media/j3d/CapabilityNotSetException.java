/*
 * $RCSfile: CapabilityNotSetException.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:17:54 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Indicates an access to a live or
 * compiled Scene Graph object without the required capability
 * set.
 */
public class CapabilityNotSetException extends RestrictedAccessException {

/**
 * Create the exception object with default values.
 */
  public CapabilityNotSetException(){
  }

/**
 * Create the exception object that outputs message.
 * @param str the message string to be output.
 */
  public CapabilityNotSetException(String str){

    super(str);
  }

}
