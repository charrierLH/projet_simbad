/*
 * $RCSfile: RestrictedAccessException.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:18 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Indicates an attempt to access or modify a state variable
 * without permission to do so.  For example, invoking a set
 * method for a state variable that is currently read-only.
 */
public class RestrictedAccessException extends RuntimeException {

/**
 * Create the exception object with default values.
 */
  public RestrictedAccessException(){
  }

/**
 * Create the exception object that outputs a message.
 * @param str the message string to be output.
 */
  public RestrictedAccessException(String str) {

    super(str);
  }

}
