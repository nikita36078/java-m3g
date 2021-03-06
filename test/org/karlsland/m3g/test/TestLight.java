package org.karlsland.m3g.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karlsland.m3g.AnimationTrack;
import org.karlsland.m3g.KeyframeSequence;
import org.karlsland.m3g.Light;

public class TestLight {

    static {
        System.loadLibrary ("jni-opengl");
    }
    
	@Test
	public void testInitialize() {
		Light lgh = new Light();
		assertEquals (Light.DIRECTIONAL, lgh.getMode());
		assertEquals (0x00ffffff, lgh.getColor());
		assertEquals (1.f,  lgh.getIntensity()           , 0.00001f);
		assertEquals (1.f,  lgh.getConstantAttenuation() , 0.00001f);
		assertEquals (0.f,  lgh.getLinearAttenuation()   , 0.00001f);
		assertEquals (0.f,  lgh.getQuadraticAttenuation(), 0.00001f);
		assertEquals (45.f, lgh.getSpotAngle()           , 0.00001f);
		assertEquals (0.f,  lgh.getSpotExponent()        , 0.00001f);
	}

	@Test
	public void testFinalize() {
		@SuppressWarnings("unused")
		Light lgh = new Light();
		lgh = null;
		System.gc();
	}

	@Test
	public void testSetAttenuation() {
		Light lgh = new Light();
		lgh.setAttenuation(1, 2, 3);
		
		assertEquals (1.f,  lgh.getConstantAttenuation() , 0.00001f);
		assertEquals (2.f,  lgh.getLinearAttenuation()   , 0.00001f);
		assertEquals (3.f,  lgh.getQuadraticAttenuation(), 0.00001f);
	}

	@Test
	public void testSetColor() {
		Light lgh = new Light();
		lgh.setColor(0x12345678);
		
		assertEquals(0x00345678, lgh.getColor());
	}

	@Test
	public void testSetIntensity() {
		Light lgh = new Light();
		lgh.setIntensity(2.f);
		
		assertEquals(2.f, lgh.getIntensity(), 0.00001f);
	}

	@Test
	public void testSetMode() {
		Light lgh = new Light();
		lgh.setMode(Light.SPOT);
		
		assertEquals(Light.SPOT, lgh.getMode());
	}

	@Test
	public void testSetSpotAngle() {
		Light lgh = new Light();
		lgh.setSpotAngle(90.f);
		
		assertEquals(90.f, lgh.getSpotAngle(), 0.0001f);
	}

	@Test
	public void testSetSpotExponent() {
		Light lgh = new Light();
		lgh.setSpotExponent(2.f);
		
		assertEquals(2.f, lgh.getSpotExponent(), 0.0001f);
	}

	@Test
	public void testToString() {
		Light lgh = new Light();
		lgh.toString();
	}

	@Test
	public void testAddAnimationTrack () {
		KeyframeSequence keySeq1 = new KeyframeSequence(2, 3, KeyframeSequence.LINEAR);
		AnimationTrack   anim1   = new AnimationTrack(keySeq1, AnimationTrack.COLOR);
		KeyframeSequence keySeq2 = new KeyframeSequence(2, 1, KeyframeSequence.LINEAR);
		AnimationTrack   anim2   = new AnimationTrack(keySeq2, AnimationTrack.INTENSITY);
		KeyframeSequence keySeq3 = new KeyframeSequence(2, 1, KeyframeSequence.LINEAR);
		AnimationTrack   anim3   = new AnimationTrack(keySeq3, AnimationTrack.SPOT_ANGLE);
		KeyframeSequence keySeq4 = new KeyframeSequence(2, 1, KeyframeSequence.LINEAR);
		AnimationTrack   anim4   = new AnimationTrack(keySeq4, AnimationTrack.SPOT_EXPONENT);
		Light lgh = new Light();
	
		lgh.addAnimationTrack(anim1);
		lgh.addAnimationTrack(anim2);
		lgh.addAnimationTrack(anim3);
		lgh.addAnimationTrack(anim4);
		
		assertEquals(4    , lgh.getAnimationTrackCount());
		assertEquals(anim1, lgh.getAnimationTrack(0));
		assertEquals(anim2, lgh.getAnimationTrack(1));
		assertEquals(anim3, lgh.getAnimationTrack(2));
		assertEquals(anim4, lgh.getAnimationTrack(3));
	}

}
