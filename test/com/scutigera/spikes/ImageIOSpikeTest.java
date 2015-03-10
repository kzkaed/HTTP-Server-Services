package com.scutigera.spikes;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.scutigera.spikes.ImageIOSpike;

public class ImageIOSpikeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ImageIOSpike image = new ImageIOSpike();
		assertTrue(image.checkMime(null));
	}

}
