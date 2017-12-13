/*
 * $RCSfile: SoundException.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:23 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Indicates a problem in loading or playing a sound sample.
 */
public class SoundException extends RuntimeException{

/**
 * Create the exception object with default values.
 */
  public SoundException(){
  }

/**
 * Create the exception object that outputs message.
 * @param str the message string to be output.
 */
  public SoundException(String str){

    super(str);
  }

}
