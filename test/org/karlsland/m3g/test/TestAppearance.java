package org.karlsland.m3g.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.karlsland.m3g.AnimationTrack;
import org.karlsland.m3g.Appearance;
import org.karlsland.m3g.CompositingMode;
import org.karlsland.m3g.Fog;
import org.karlsland.m3g.Image2D;
import org.karlsland.m3g.KeyframeSequence;
import org.karlsland.m3g.Material;
import org.karlsland.m3g.Object3D;
import org.karlsland.m3g.PolygonMode;
import org.karlsland.m3g.Texture2D;

public class TestAppearance {

    static {
        System.loadLibrary ("jni-opengl");
    }
    
	@Test
	public void testInitialize() {
		Appearance app = new Appearance();
		
		assertEquals (0   , app.getLayer());
		assertEquals (null, app.getPolygonMode());
		assertEquals (null, app.getCompositingMode());
		assertEquals (null, app.getTexture(0));
		assertEquals (null, app.getTexture(1));
		assertEquals (null, app.getMaterial());
		assertEquals (null, app.getFog());
	}


	@Test
	public void testFinalize() {
		@SuppressWarnings("unused")
		Appearance app = new Appearance();
		app = null;
		System.gc();
	}

	@Test
	public void testSetCompositingMode() {
		Appearance      app   = new Appearance();
		CompositingMode cmode = new CompositingMode();

		app.setCompositingMode(cmode);
		
		assertEquals(cmode, app.getCompositingMode());
	}
	

	@Test
	public void testSetFog() {
		Appearance app = new Appearance();
		Fog        fog = new Fog();
		app.setFog(fog);
		
		assertEquals(fog, app.getFog());
	}

	@Test
	public void testSetLayer() {
		Appearance app = new Appearance();
		app.setLayer(10);
		
		assertEquals(10, app.getLayer());
	}

	@Test
	public void testSetMaterial() {
		Appearance app = new Appearance();
		Material   mat = new Material();
		app.setMaterial(mat);
		
		assertEquals(mat, app.getMaterial());
	}

	@Test
	public void testSetPolygonMode() {
		Appearance  app   = new Appearance();
		PolygonMode pmode = new PolygonMode();
		app.setPolygonMode(pmode);

		assertEquals(pmode, app.getPolygonMode());
	}

	@Test
	public void testSetTexture() {
		Appearance app = new Appearance();
		Image2D    img   = new Image2D (Image2D.LUMINANCE, 64, 64);
		Texture2D  tex0  = new Texture2D (img);
		Texture2D  tex1  = new Texture2D (img);
		app.setTexture(0, tex0);
		app.setTexture(1, tex1);

		assertEquals (tex0 , app.getTexture(0));
		assertEquals (tex1 , app.getTexture(1));
	}

	@Test
	public void testToString() {
		Appearance app = new Appearance();
		app.toString();
	}

	@Test
	public void testGetReferences() {
		Appearance      app   = new Appearance();
		CompositingMode cmode = new CompositingMode();
		Fog             fog   = new Fog();
		Material        mat   = new Material();
		PolygonMode     pmode = new PolygonMode();
		Image2D         img   = new Image2D (Image2D.LUMINANCE, 64, 64);
		Texture2D       tex0  = new Texture2D (img);
		Texture2D       tex1  = new Texture2D (img);
		app.setFog(fog);
		app.setCompositingMode(cmode);
		app.setMaterial(mat);
		app.setPolygonMode(pmode);
		app.setTexture(0, tex0);
		app.setTexture(1, tex1);

		Object3D[] references = {null, null, null, null, null, null};  
	   int n = app.getReferences(references);
	     
	 	assertEquals(6   ,  n);
	   assertEquals(pmode, references[0]);
	   assertEquals(cmode, references[1]);
	   assertEquals(mat  , references[2]);
	   assertEquals(tex0 , references[3]);
	   assertEquals(tex1 , references[4]);
	   assertEquals(fog  , references[5]);
	}

}
