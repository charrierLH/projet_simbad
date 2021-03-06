/*
 * $RCSfile: J3dClock.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:06 $
 * $State: Exp $
 */

package javax.media.j3d;

/**
 * Utility class to provide a more accurate replacement for
 * System.currentTimeMillis().
 */
class J3dClock {

    private static long deltaTime;
    private static final long nsecPerMsec = 1000000;

    /**
     * Private constructor, since no instance should ever be created.
     */
    private J3dClock() {
    }
    
    /**
     * Returns the current time in milliseconds. This is a more
     * accurate version of System.currentTimeMillis and should be used in
     * its place.
     *
     * @return the current time in milliseconds.
     */
    static long currentTimeMillis() {
        return (System.nanoTime() / nsecPerMsec) + deltaTime;
    }

    static {
        // Call time methods once without using their values to ensure that
        // the methods are "warmed up". We need to make sure that the actual
        // calls that we use take place as close together as possible in time.
        System.currentTimeMillis();
        System.nanoTime();

        // Compute deltaTime between System.currentTimeMillis()
        // and the high-res timer, use a synchronized block to force both calls
        // to be made before the integer divide
        long baseTime, baseTimerValue;
        synchronized (J3dClock.class) {
            baseTime = System.currentTimeMillis();
            baseTimerValue = System.nanoTime();
        }
        deltaTime = baseTime - (baseTimerValue / nsecPerMsec);
    }
}
