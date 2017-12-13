/*
 * $RCSfile: RendererStructure.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:17 $
 * $State: Exp $
 */

package javax.media.j3d;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A renderer structure is an object that organizes messages
 * to the renderer thread.
 */
class RendererStructure extends J3dStructure{

    /**
     * This constructor does nothing
     */
    RendererStructure() {
	super(null, J3dThread.RENDER_THREAD);
    }

    /**
     * Returns all messages in the queue. 
     */
    J3dMessage[] getMessages() {
	int sz;

        synchronized (messageList) {
            if ((sz = messageList.size()) > 0) {
		if (msgList.length < sz) {
		    msgList = new J3dMessage[sz];
		}
		messageList.toArrayAndClear(msgList);
            }
        }

	nMessage = sz;
        return msgList;
    }


    void processMessages(long referenceTime) {}

    void removeNodes(J3dMessage m) {}

    void cleanup() {}
}
