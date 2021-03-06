package org.karlsland.m3g.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karlsland.m3g.AnimationController;
import org.karlsland.m3g.AnimationTrack;
import org.karlsland.m3g.KeyframeSequence;
import org.karlsland.m3g.Object3D;

public class TestAnimationTrack {

    static {
        System.loadLibrary ("jni-opengl");
    }
    
	@Test
	public void testInitialize() {
	     KeyframeSequence keySeq    = new KeyframeSequence (4, 3, KeyframeSequence.LINEAR);
	     AnimationTrack   animTrack = new AnimationTrack (keySeq, AnimationTrack.SCALE);
	        
	     assertEquals (null  , animTrack.getController());
	     assertEquals (keySeq, animTrack.getKeyframeSequence());
	     assertEquals (AnimationTrack.SCALE, animTrack.getTargetProperty());
	}

	@Test
	public void testFinalize() {
	     KeyframeSequence keySeq    = new KeyframeSequence (4, 3, KeyframeSequence.LINEAR);
	     @SuppressWarnings("unused")
		AnimationTrack   animTrack = new AnimationTrack (keySeq, AnimationTrack.SCALE);
	     animTrack = null;
	     System.gc();
	}

	@Test
	public void testSetController() {
	     KeyframeSequence    keySeq    = new KeyframeSequence (4, 3, KeyframeSequence.LINEAR);
	     AnimationTrack      animTrack = new AnimationTrack (keySeq, AnimationTrack.SCALE);
	     AnimationController ctrl      = new AnimationController();
	     
	     animTrack.setController(ctrl);
	     assertEquals (ctrl, animTrack.getController());
	}

	@Test
	public void testToString() {
	     KeyframeSequence    keySeq    = new KeyframeSequence (4, 3, KeyframeSequence.LINEAR);
	     AnimationTrack      animTrack = new AnimationTrack (keySeq, AnimationTrack.SCALE);
	     animTrack.toString();
	}

	@Test
	public void testGetReferences() {
	     KeyframeSequence    keySeq     = new KeyframeSequence (4, 3, KeyframeSequence.LINEAR);
	     AnimationTrack      animTrack  = new AnimationTrack (keySeq, AnimationTrack.SCALE);
	     AnimationController ctrl       = new AnimationController();
	     animTrack.setController(ctrl);
	     
	     Object3D[]          references = {null, null};
	     int n = animTrack.getReferences(references);
	     
	     assertEquals(2     , n);
	     assertEquals(keySeq, references[0]);
	     assertEquals(ctrl  , references[1]);
	}


}
