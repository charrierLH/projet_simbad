/*
 * $RCSfile: TriangleStripArray.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * $Revision: 1.5 $
 * $Date: 2007/02/09 17:18:28 $
 * $State: Exp $
 */

package javax.media.j3d;


/**
 * The TriangleStripArray object draws an array of vertices as a set of
 * connected triangle strips.  An array of per-strip vertex counts specifies
 * where the separate strips appear in the vertex array.
 * For every strip in the set,
 * each vertex, beginning with the third vertex in the array,
 * defines a triangle to be drawn using the current vertex and
 * the two previous vertices.
 */

public class TriangleStripArray extends GeometryStripArray {

    // non-public, no parameter constructor
    TriangleStripArray() {}

    /**
     * Constructs an empty TriangleStripArray object using the
     * specified parameters.
     *
     * @param vertexCount
     * see {@link GeometryArray#GeometryArray(int,int)}
     * for a description of this parameter.
     *
     * @param vertexFormat
     * see {@link GeometryArray#GeometryArray(int,int)}
     * for a description of this parameter.
     *
     * @param stripVertexCounts
     * see {@link GeometryStripArray#GeometryStripArray(int,int,int[])}
     * for a description of this parameter.
     *
     * @exception IllegalArgumentException if vertexCount is less than 3
     * or any element in the stripVertexCounts array is less than 3
     * ;<br>
     * See {@link GeometryStripArray#GeometryStripArray(int,int,int[])}
     * for more exceptions that can be thrown
     */
    public TriangleStripArray(int vertexCount,
			      int vertexFormat,
			      int stripVertexCounts[]) {

	super(vertexCount, vertexFormat, stripVertexCounts);

        if (vertexCount < 3 )
	    throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArray0"));
    }

    /**
     * Constructs an empty TriangleStripArray object using the
     * specified parameters.
     *
     * @param vertexCount
     * see {@link GeometryArray#GeometryArray(int,int,int,int[])}
     * for a description of this parameter.
     *
     * @param vertexFormat
     * see {@link GeometryArray#GeometryArray(int,int,int,int[])}
     * for a description of this parameter.
     *
     * @param texCoordSetCount
     * see {@link GeometryArray#GeometryArray(int,int,int,int[])}
     * for a description of this parameter.
     *
     * @param texCoordSetMap
     * see {@link GeometryArray#GeometryArray(int,int,int,int[])}
     * for a description of this parameter.
     *
     * @param stripVertexCounts
     * see {@link GeometryStripArray#GeometryStripArray(int,int,int,int[],int[])}
     * for a description of this parameter.
     *
     * @exception IllegalArgumentException if vertexCount is less than 3
     * or any element in the stripVertexCounts array is less than 3
     * ;<br>
     * See {@link GeometryStripArray#GeometryStripArray(int,int,int,int[],int[])}
     * for more exceptions that can be thrown
     *
     * @since Java 3D 1.2
     */
    public TriangleStripArray(int vertexCount,
			      int vertexFormat,
			      int texCoordSetCount,
			      int[] texCoordSetMap,
			      int stripVertexCounts[]) {

	super(vertexCount, vertexFormat,
	      texCoordSetCount, texCoordSetMap,
	      stripVertexCounts);

        if (vertexCount < 3 )
	    throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArray0"));
    }

    /**
     * Constructs an empty TriangleStripArray object using the
     * specified parameters.
     *
     * @param vertexCount
     * see {@link GeometryArray#GeometryArray(int,int,int,int[],int,int[])}
     * for a description of this parameter.
     *
     * @param vertexFormat
     * see {@link GeometryArray#GeometryArray(int,int,int,int[],int,int[])}
     * for a description of this parameter.
     *
     * @param texCoordSetMap
     * see {@link GeometryArray#GeometryArray(int,int,int,int[],int,int[])}
     * for a description of this parameter.
     *
     * @param vertexAttrCount
     * see {@link GeometryArray#GeometryArray(int,int,int,int[],int,int[])}
     * for a description of this parameter.
     *
     * @param vertexAttrSizes
     * see {@link GeometryArray#GeometryArray(int,int,int,int[],int,int[])}
     * for a description of this parameter.
     *
     * @param stripVertexCounts
     * see {@link GeometryStripArray#GeometryStripArray(int,int,int,int[],int,int[],int[])}
     * for a description of this parameter.
     *
     * @exception IllegalArgumentException if vertexCount is less than 3
     * or any element in the stripVertexCounts array is less than 3
     * ;<br>
     * See {@link GeometryStripArray#GeometryStripArray(int,int,int,int[],int,int[],int[])}
     * for more exceptions that can be thrown
     *
     * @since Java 3D 1.4
     */
    public TriangleStripArray(int vertexCount,
			      int vertexFormat,
			      int texCoordSetCount,
			      int[] texCoordSetMap,
			      int vertexAttrCount,
			      int[] vertexAttrSizes,
			      int[] stripVertexCounts) {

	super(vertexCount, vertexFormat,
	      texCoordSetCount, texCoordSetMap,
	      vertexAttrCount, vertexAttrSizes,
	      stripVertexCounts);

        if (vertexCount < 3 )
	    throw new IllegalArgumentException(J3dI18N.getString("TriangleStripArray0"));
    }

    /**
     * Creates the retained mode TriangleStripArrayRetained object that this
     * TriangleStripArray object will point to.
     */
    void createRetained() {
	this.retained = new TriangleStripArrayRetained();
	this.retained.setSource(this);
    }


    /**
     * @deprecated replaced with cloneNodeComponent(boolean forceDuplicate)
     */
    public NodeComponent cloneNodeComponent() {
        TriangleStripArrayRetained rt = (TriangleStripArrayRetained) retained;
        int stripcounts[] = new int[rt.getNumStrips()];
        rt.getStripVertexCounts(stripcounts);
        int texSetCount = rt.getTexCoordSetCount();
        int[] texMap = null;
        int vertexAttrCount = rt.getVertexAttrCount();
        int[] vertexAttrSizes = null;
        if (texSetCount > 0) {
            texMap = new int[rt.getTexCoordSetMapLength()];
            rt.getTexCoordSetMap(texMap);
        }
        if (vertexAttrCount > 0) {
            vertexAttrSizes = new int[vertexAttrCount];
            rt.getVertexAttrSizes(vertexAttrSizes);
        }
        TriangleStripArray t = new TriangleStripArray(rt.getVertexCount(),
                rt.getVertexFormat(),
                texSetCount,
                texMap,
                vertexAttrCount,
                vertexAttrSizes,
                stripcounts);
        t.duplicateNodeComponent(this);
        return t;
     }
}
