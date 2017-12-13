/*
 * $RCSfile: OrderedPathElement.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.4 $
 * $Date: 2007/02/09 17:18:13 $
 * $State: Exp $
 */

package javax.media.j3d;

import java.util.ArrayList;

class OrderedPathElement extends Object {
    OrderedGroupRetained orderedGroup;
    Integer childId;

    OrderedPathElement(OrderedGroupRetained og, Integer orderedId) {
        orderedGroup = og;
        childId = orderedId;
    }
}
