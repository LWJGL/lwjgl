/* 
 * Copyright (c) 2003 Shaven Puppy Ltd
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'Shaven Puppy' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.util.model.loader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lwjgl.util.model.*;
import org.lwjgl.util.model.BoneFrame;
import org.lwjgl.util.model.BonedModel;
import org.lwjgl.util.model.BonedVertex;
import org.lwjgl.util.model.MeshedModel;
import org.lwjgl.util.model.Triangle;
import org.lwjgl.util.model.Vertex;
import org.lwjgl.util.model.Weight;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * $Id$
 * 
 * Loads a Model from an XML document. Construct with an XML Document as the argument,
 * and then retrieve the Model by calling load().
 * 
 * @author $Author$
 * @version $Revision$
 */
public class Loader {
	
	/** The source document */
	private final Document src;
	
	/** Expected number of vertices */
	private int numVertices;
	
	/** Expected number of bones */
	private int numBones;
	
	/**
	 * C'tor
	 */
	public Loader(Document src) {
		this.src = src;
	}
	
	/**
	 * Load the model from the XML document and return it.
	 * @return Model
	 * @throws Exception
	 */
	public Model load() throws Exception {
		String material = XMLUtil.getString(src.getDocumentElement(), "material");
		numVertices = XMLUtil.getInt(src.getDocumentElement(), "vertices");
		if (XMLUtil.getString(src.getDocumentElement(), "type").equals("boned")) {
			// It's a boned model
			numBones = XMLUtil.getInt(src.getDocumentElement(), "bones", 0);
			return new BonedModel(
					material,
					loadTriangles(),
					loadSkin(),
					loadBoneAnimations(), 
					loadBonedVertices()
				);
		} else if (XMLUtil.getString(src.getDocumentElement(), "type").equals("meshed")) {
			// It's a mesh keyframe model
			return new MeshedModel(
					material,
					loadTriangles(),
					loadSkin(),
					loadMeshAnimations()
				);
		} else {
			throw new Exception("Unsupported model type.");
		}
	}
	
	/**
	 * Load all the BonedVertices
	 * @return Vertex[]
	 * @throws Exception
	 */
	private BonedVertex[] loadBonedVertices() throws Exception {
		List vertexElements = XMLUtil.getChildren(src.getDocumentElement(), "vertex");
		if (vertexElements.size() != numVertices) {
			throw new Exception("Vertex count incorrect, got "+vertexElements.size()+", expected "+numVertices);
		}
		BonedVertex[] vertices = new BonedVertex[vertexElements.size()];
		int vertexCount = 0;
		for (Iterator i = vertexElements.iterator(); i.hasNext(); ) {
			Element vertexElement = (Element) i.next();
			vertices[vertexCount++] = loadBonedVertex(vertexElement);
		}
		return vertices;
	}
	
	/**
	 * Load the skin
	 * @return Vector2f[]
	 * @throws Exception
	 */
	private Vector2f[] loadSkin() throws Exception {
		List skinElements = XMLUtil.getChildren(src.getDocumentElement(), "skin");
		if (skinElements.size() == 0) {
			return null;
		}
		if (skinElements.size() != numVertices) {
			throw new Exception("Skin count incorrect, got "+skinElements.size()+", expected "+numVertices);
		}
		Vector2f[] skins = new Vector2f[skinElements.size()];
		int skinCount = 0;
		for (Iterator i = skinElements.iterator(); i.hasNext(); ) {
			Element skinElement = (Element) i.next();
			skins[skinCount++] = loadTexCoord(skinElement);
		}
		return skins;
	}
	
	/**
	 * Load all the Triangles
	 * @return Triangle[]
	 * @throws Exception
	 */
	private Triangle[] loadTriangles() throws Exception {
		List triangleElements = XMLUtil.getChildren(src.getDocumentElement(), "triangle");
		Triangle[] triangles = new Triangle[triangleElements.size()];
		int triangleCount = 0;
		for (Iterator i = triangleElements.iterator(); i.hasNext(); ) {
			Element triangleElement = (Element) i.next();
			triangles[triangleCount++] = loadTriangle(triangleElement);
		}
		return triangles;
	}
	
	/**
	 * Load all the bone animations
	 * @return Map of animation names to BoneFrame[] animations
	 * @throws Exception
	 */
	private Map loadBoneAnimations() throws Exception {
		List animationElements = XMLUtil.getChildren(src.getDocumentElement(), "animation");
		Map animations = new HashMap(animationElements.size());
		for (Iterator i = animationElements.iterator(); i.hasNext(); ) {
			Element animationElement = (Element) i.next();
			animations.put(XMLUtil.getString(animationElement, "name"), loadBonedAnimation(animationElement));
		}
		return animations;
	}
	
	/**
	 * Load all the mesh keyframe animations
	 * @return Map of animation names to MeshFrame[] animations
	 * @throws Exception
	 */
	private Map loadMeshAnimations() throws Exception {
		List animationElements = XMLUtil.getChildren(src.getDocumentElement(), "animation");
		Map animations = new HashMap(animationElements.size());
		for (Iterator i = animationElements.iterator(); i.hasNext(); ) {
			Element animationElement = (Element) i.next();
			animations.put(XMLUtil.getString(animationElement, "name"), loadMeshAnimation(animationElement));
		}
		return animations;
	}
	
	/**
	 * Load a Vertex from XML
	 * @param vertexElement
	 * @return a Vertex
	 * @throws Exception
	 */
	private BonedVertex loadBonedVertex(Element vertexElement) throws Exception {
		List weightElements = XMLUtil.getChildren(vertexElement, "weight");
		Weight[] weights;
		if (weightElements.size() == 0) {
			weights = null;
		} else {
			weights = new Weight[weightElements.size()];
			int weightCount = 0;
			for (Iterator i = weightElements.iterator(); i.hasNext(); ) {
				Element weightElement = (Element) i.next();
				weights[weightCount++] = loadWeight(weightElement);
			}
		}
			
		return new BonedVertex(
				new Vector3f(
					XMLUtil.getFloat(vertexElement, "x"),
					XMLUtil.getFloat(vertexElement, "y"),
					XMLUtil.getFloat(vertexElement, "z")
				),
				XMLUtil.hasAttribute(vertexElement, "nx") ?
					new Vector3f(
							XMLUtil.getFloat(vertexElement, "nx"),
							XMLUtil.getFloat(vertexElement, "ny"),
							XMLUtil.getFloat(vertexElement, "nz")
						)
					: null,
				weights
			);
	}

	/**
	 * Load a Vertex from XML
	 * @param vertexElement
	 * @return a Vertex
	 * @throws Exception
	 */
	private Vertex loadMeshVertex(Element vertexElement) throws Exception {
		return new Vertex(
				new Vector3f(
					XMLUtil.getFloat(vertexElement, "x"),
					XMLUtil.getFloat(vertexElement, "y"),
					XMLUtil.getFloat(vertexElement, "z")
				),
				XMLUtil.hasAttribute(vertexElement, "nx") ?
					new Vector3f(
							XMLUtil.getFloat(vertexElement, "nx"),
							XMLUtil.getFloat(vertexElement, "ny"),
							XMLUtil.getFloat(vertexElement, "nz")
						)
					: null
			);
	}

	/**
	 * Load a Weight from XML
	 * @param element
	 * @return a Skin
	 * @throws Exception
	 */
	private Weight loadWeight(Element element) throws Exception {
		int bone = XMLUtil.getInt(element, "bone");
		if (bone < 0 || bone >= numBones) {
			throw new Exception("Bone index out of range");
		}
		return new Weight(
				bone,
				XMLUtil.getFloat(element, "weight")
			);
	}
	
	/**
	 * Load a Triangle from XML
	 * @param element
	 * @param numVertices
	 * @return a Triangle
	 * @throws Exception
	 */
	private Triangle loadTriangle(Element element) throws Exception {
		// Perform sanity checks
		int a = XMLUtil.getInt(element, "a");
		if (a < 0 || a >= numVertices) {
			throw new Exception("'a' is out of range");
		}
		int b = XMLUtil.getInt(element, "b");
		if (b < 0 || b >= numVertices) {
			throw new Exception("'b' is out of range");
		}
		int c = XMLUtil.getInt(element, "c");
		if (c < 0 || c >= numVertices) {
			throw new Exception("'c' is out of range");
		}
		if (a == b || a == c || b == c) {
			throw new Exception("Degenerate triangle");
		}
		return new Triangle(
				a,
				b,
				c,
				XMLUtil.getInt(element, "adjacency", 0)
			);
	}
	
	/**
	 * Load a texture coordinate from XML
	 * @param element
	 * @return a Vector2f
	 * @throws Exception
	 */
	private Vector2f loadTexCoord(Element element) throws Exception {
		return new Vector2f(
				XMLUtil.getFloat(element, "u"),
				XMLUtil.getFloat(element, "v")
			);
	}
	
	/**
	 * Load a boned Animation from XML
	 * @param element
	 * @return BoneFrame[] 
	 * @throws Exception
	 */
	private BoneFrame[] loadBonedAnimation(Element element) throws Exception {
		List frameElements = XMLUtil.getChildren(element, "frame");
		BoneFrame[] frames = new BoneFrame[frameElements.size()];
		int frameCount = 0;
		for (Iterator i = frameElements.iterator(); i.hasNext(); ) {
			Element frameElement = (Element) i.next();
			frames[frameCount++] = loadBoneFrame(frameElement);
		}
		return frames;
	}
	
	/**
	 * Load a mesh Animation from XML
	 * @param element
	 * @return MeshFrame[] 
	 * @throws Exception
	 */
	private MeshFrame[] loadMeshAnimation(Element element) throws Exception {
		List frameElements = XMLUtil.getChildren(element, "frame");
		MeshFrame[] frames = new MeshFrame[frameElements.size()];
		int frameCount = 0;
		for (Iterator i = frameElements.iterator(); i.hasNext(); ) {
			Element frameElement = (Element) i.next();
			frames[frameCount++] = loadMeshFrame(frameElement);
		}
		return frames;
	}
	
	/**
	 * Load a Frame from XML
	 * @param element
	 * @return BoneFrame 
	 * @throws Exception
	 */
	private BoneFrame loadBoneFrame(Element element) throws Exception {
		List boneElements = XMLUtil.getChildren(element, "bone");
		if (boneElements.size() != numBones) {
			throw new Exception("Expected "+numBones+" bones in frame, only got "+boneElements.size());
		}
		Matrix4f[] bones = new Matrix4f[boneElements.size()];
		int boneCount = 0;
		for (Iterator i = boneElements.iterator(); i.hasNext(); ) {
			Element boneElement = (Element) i.next();
			bones[boneCount++] = loadBone(boneElement);
		}
		return new BoneFrame(
				XMLUtil.getFloat(element, "time", 0.0f),
				bones
			);
	}

	/**
	 * Load a Frame from XML
	 * @param element
	 * @return MeshFrame 
	 * @throws Exception
	 */
	private MeshFrame loadMeshFrame(Element element) throws Exception {
		List vertexElements = XMLUtil.getChildren(element, "vertex");
		Vertex[] vertices = new Vertex[vertexElements.size()];
		if (vertices.length != numVertices) {
			throw new Exception("Vertex count incorrect");
		}
		int vertexCount = 0;
		for (Iterator i = vertexElements.iterator(); i.hasNext(); ) {
			Element vertexElement = (Element) i.next();
			vertices[vertexCount++] = loadMeshVertex(vertexElement);
		}
		return new MeshFrame(
				XMLUtil.getFloat(element, "time", 0.0f),
				vertices
			);
	}

	/**
	 * Load a Bone from XML
	 * @param element
	 * @return a Matrix4f
	 * @throws Exception
	 */
	private Matrix4f loadBone(Element element) throws Exception {
		Matrix4f ret = new Matrix4f();
		ret.m00 = XMLUtil.getFloat(element, "m00");
		ret.m01 = XMLUtil.getFloat(element, "m01");
		ret.m02 = XMLUtil.getFloat(element, "m02");
		ret.m03 = XMLUtil.getFloat(element, "m03");
		ret.m10 = XMLUtil.getFloat(element, "m10");
		ret.m11 = XMLUtil.getFloat(element, "m11");
		ret.m12 = XMLUtil.getFloat(element, "m12");
		ret.m13 = XMLUtil.getFloat(element, "m13");
		ret.m20 = XMLUtil.getFloat(element, "m20");
		ret.m21 = XMLUtil.getFloat(element, "m21");
		ret.m22 = XMLUtil.getFloat(element, "m22");
		ret.m23 = XMLUtil.getFloat(element, "m23");
		ret.m30 = XMLUtil.getFloat(element, "m30", 0.0f);
		ret.m31 = XMLUtil.getFloat(element, "m31", 0.0f);
		ret.m32 = XMLUtil.getFloat(element, "m32", 0.0f);
		ret.m33 = XMLUtil.getFloat(element, "m33", 1.0f);
		return ret;
	}

}
