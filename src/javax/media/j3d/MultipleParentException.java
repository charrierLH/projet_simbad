/*
 * $RCSfile: MultipleParentException.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:11 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Indicates
 * an attempt to add a node that is already a child of one
 * group node, into another group node.
 */
public class MultipleParentException extends IllegalSharingException {

/**
 * Create the exception object with default values.
 */
  public MultipleParentException(){
  }

/**
 * Create the exception object that outputs message.
 * @param str the message string to be output.
 */
  public MultipleParentException(String str){

    super(str);
  }

}
