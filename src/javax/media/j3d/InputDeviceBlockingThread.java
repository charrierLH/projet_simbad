/*
 * $RCSfile: InputDeviceBlockingThread.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.5 $
 * $Date: 2007/02/09 17:18:06 $
 * $State: Exp $
 */

package javax.media.j3d;

class InputDeviceBlockingThread extends Thread {

    // action flag for runMonitor
    private static final int WAIT   = 0;
    private static final int NOTIFY = 1;
    private static final int STOP   = 2;

    // blocking device that this thread manages
    private InputDevice device;
    private volatile boolean running = true;
    private volatile boolean stop = false;
    private boolean waiting = false;
    private boolean ready = false;
    private static int numInstances = 0;
    private int instanceNum = -1;

    InputDeviceBlockingThread(ThreadGroup threadGroup, InputDevice device) {
	super(threadGroup, "");
	setName("J3D-InputDeviceBlockingThread-" + getInstanceNum());
	this.device = device;
    }

    private synchronized int newInstanceNum() {
	return (++numInstances);
    }

    private int getInstanceNum() {
	if (instanceNum == -1)
	    instanceNum = newInstanceNum();
	return instanceNum;
    }


    public void run() {
	// Since this thread is blocking, this thread should not be
	// taking an inordinate amount of CPU time.  Note that the
	// yield() call should not be necessary (and may be ineffective),
	// but we can't call MasterControl.threadYield() because it will
	// sleep for at least a millisecond.
	while (running) {	
	    while (!stop) {
		device.pollAndProcessInput();
		Thread.yield();
	    }
	    runMonitor(WAIT);
	}
    }

    void sleep() {
	stop = true;
    }

    void restart() {
	stop = false;
	runMonitor(NOTIFY);
    }

    void finish() {
        stop = true;
        runMonitor(STOP);
    }

    synchronized void runMonitor(int action) {

	switch (action) {
	case WAIT:
            // Issue 279 - loop until ready
            while (running && !ready) {
                waiting = true;
                try {
                    wait();
                } catch (InterruptedException e) {}
                waiting = false;
            }
            ready = false;
	    break;
	case NOTIFY:
            ready = true;
            if (waiting) {
                notify();
            }
	    break;
	case STOP:
	    running = false;
            if (waiting) {
                notify();
            }
	    break;
	}
    }
}
