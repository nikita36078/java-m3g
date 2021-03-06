package org.karlsland.m3g.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karlsland.m3g.AnimationTrack;
import org.karlsland.m3g.KeyframeSequence;
import org.karlsland.m3g.Node;

public class TestNode {

    static {
        System.loadLibrary ("jni-opengl");
    }
    
	@Test
	public void testInitialize() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();
		
        assertEquals (1.f      , node.getAlphaFactor(), 0.0001f);
        assertEquals (null     , node.getParent());
        assertEquals (-1       , node.getScope());
        assertEquals (true     , node.isPickingEnabled());
        assertEquals (true     , node.isRenderingEnabled());
        assertEquals (null     , node.getAlignmentReference (Node.Z_AXIS));
        assertEquals (Node.NONE, node.getAlignmentTarget    (Node.Z_AXIS));
        assertEquals (null     , node.getAlignmentReference (Node.Y_AXIS));
        assertEquals (Node.NONE, node.getAlignmentTarget    (Node.Y_AXIS));
	}

	
	@Test
	public void testFinalize() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();
		node = null;
		System.gc();
	}


	@Test
	public void testSetAlignment() {
		ConcreteNode node = new ConcreteNode();
		ConcreteNode zRef = new ConcreteNode();
		ConcreteNode yRef = new ConcreteNode();
		node.initialize();
		zRef.initialize();
		yRef.initialize();
		
		node.setAlignment(zRef, Node.ORIGIN, yRef, Node.ORIGIN);
		
		assertEquals(zRef       , node.getAlignmentReference(Node.Z_AXIS));
		assertEquals(Node.ORIGIN, node.getAlignmentTarget   (Node.Z_AXIS));
		assertEquals(yRef       , node.getAlignmentReference(Node.Y_AXIS));
		assertEquals(Node.ORIGIN, node.getAlignmentTarget   (Node.Y_AXIS));
	}

	@Test
	public void testSetAlphaFactor() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();

		node.setAlphaFactor(0.1f);
		assertEquals(0.1f, node.getAlphaFactor(), 0.0001f);
	}

	@Test
	public void testSetPickingEnable() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();
		
		node.setPickingEnable(false);
		assertEquals(false, node.isPickingEnabled());
	}

	@Test
	public void testSetRenderingEnable() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();
		
		node.setRenderingEnable(false);
		assertEquals(false, node.isRenderingEnabled());
	}

	@Test
	public void testSetScope() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();
		
		node.setScope(0x12345678);
		assertEquals(0x12345678, node.getScope());
	}

	
	@Test
	public void testToString() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();

		node.toString();
	}

	@Test
	public void testAnimate() {
		ConcreteNode node = new ConcreteNode();
		node.initialize();
		KeyframeSequence keyframeSequence = new KeyframeSequence (1, 1, KeyframeSequence.LINEAR);
		AnimationTrack animationTrack = new AnimationTrack (keyframeSequence, AnimationTrack.ALPHA);
		node.addAnimationTrack(animationTrack);	

		assertEquals(1             , node.getAnimationTrackCount());
		assertEquals(animationTrack, node.getAnimationTrack(0));
	}
	
	@Test
	public void testAddAnimationTrack () {
		KeyframeSequence keySeq1 = new KeyframeSequence(2, 1, KeyframeSequence.LINEAR);
		AnimationTrack   anim1   = new AnimationTrack(keySeq1, AnimationTrack.ALPHA);
		KeyframeSequence keySeq2 = new KeyframeSequence(2, 1, KeyframeSequence.LINEAR);
		AnimationTrack   anim2   = new AnimationTrack(keySeq2, AnimationTrack.PICKABILITY);
		KeyframeSequence keySeq3 = new KeyframeSequence(2, 1, KeyframeSequence.LINEAR);
		AnimationTrack   anim3   = new AnimationTrack(keySeq3, AnimationTrack.VISIBILITY);
		ConcreteNode node = new ConcreteNode();
		node.initialize();
	
		node.addAnimationTrack(anim1);
		node.addAnimationTrack(anim2);
		node.addAnimationTrack(anim3);
		
		assertEquals(3    , node.getAnimationTrackCount());
		assertEquals(anim1, node.getAnimationTrack(0));
		assertEquals(anim2, node.getAnimationTrack(1));
		assertEquals(anim3, node.getAnimationTrack(2));
	}

}
