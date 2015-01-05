package cs1410;

import static org.junit.Assert.*;

import org.junit.Test;

public class CacheTests {

	
	/**
	 * test to make sure more than 7 attributes
	 * throws an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest1() {
		Cache cache = new Cache("GCRQWK	Old Three Tooth	geocadet	3.5	3	N40 45.850	W111 48.045	3421");
		cache.getTitle();
		
	}
	
	/**
	 * test to make sure less than 7 attributes
	 * throws an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest2() {
		Cache cache = new Cache("GCRQWK	Old Three Tooth	geocadet	3.5	3	N40 45.850");
		cache.getTitle();
	}
	
	/**
	 * test to make sure if the first attribute
	 * doesn't start with "GC" that it throws
	 * an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest3() {
		Cache cache = new Cache("RQWK	Old Three Tooth	geocadet	3.5	3	N40 45.850	W111 48.045");
		cache.getTitle();
	}
	
	/**
	 * test to make sure that the terrain and difficulty
	 * pieces only contain the doubles 1, 1.5, 2, 2.5, 3, 
	 * 3.5, 4, 4.5, or 5 or it throws an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest4() {
		Cache cache = new Cache("GCRQWK	Old Three Tooth	geocadet	3.7	3	N40 45.850	W111 48.045");
		cache.getTitle();
	}
	
	/**
	 * test to make sure that the terrain and difficulty
	 * pieces only contain the doubles 1, 1.5, 2, 2.5, 3, 
	 * 3.5, 4, 4.5, or 5 or it throws an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest5() {
		Cache cache = new Cache("GCRQWK	Old Three Tooth	geocadet	6	3	N40 45.850	W111 48.045");
		cache.getTitle();
	}
	
	/**
	 * test to make sure that the terrain and difficulty
	 * pieces only contain the doubles 1, 1.5, 2, 2.5, 3, 
	 * 3.5, 4, 4.5, or 5 or it throws an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest6() {
		Cache cache = new Cache("GCRQWK	Old Three Tooth	geocadet	3.5	1.2	N40 45.850	W111 48.045");
		cache.getTitle();
	}
	
	/**
	 * test to make sure that the terrain and difficulty
	 * pieces only contain the doubles 1, 1.5, 2, 2.5, 3, 
	 * 3.5, 4, 4.5, or 5 or it throws an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest7() {
		Cache cache = new Cache("GCRQWK	Old Three Tooth	geocadet	3.7	0	N40 45.850	W111 48.045");
		cache.getTitle();
	}
	
	/**
	 * test that if the title, owner, lat or long contains
	 * only whitespace then an IllegalArgumentException should
	 * be thrown
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest8() {
		Cache cache = new Cache("GCRQWK	  	geocadet	3.5	3	N40 45.850	W111 48.045");
		cache.getTitle();
	}
	
	/**
	 * Test to make sure that if difficultyOrTerrain
	 * takes in an illegal number that it throws an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest9() {
		Cache cache = new Cache ("GCRQWK	Old Three Tooth	geocadet	3.5	3	N40 45.850	W111 48.045");
		cache.difficultyOrTerrain(.2, 3.0);
	}
	
	/**
	 * Test to make sure that whitespace only strings
	 * throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void attributesExceptionTest10() {
		Cache cache = new Cache ("GCRQWK	Old Three Tooth	geocadet	3.5	3	N40 45.850	W111 48.045");
		cache.testForWhitespace("         ");
	}
	
	
	/** 
	 * Test that the cache is correctly created
	 *
	 * and implemented by calling the getters
	 * and making sure all the return data is
	 * correct
	 */
	@Test
	public void cacheCreationTest() {
		Cache cache = new Cache ("GCRQWK	Old Three Tooth	geocadet	3.5	3	N40 45.850	W111 48.045");
		assertEquals("GCRQWK", cache.getGcCode());
		assertEquals("Old Three Tooth", cache.getTitle());
		assertEquals("geocadet", cache.getOwner());
		assertEquals("3.5", Double.toString(cache.getDifficulty()));
		assertEquals("3.0", Double.toString(cache.getTerrain()));
		assertEquals("N40 45.850", cache.getLatitude());
		assertEquals("W111 48.045", cache.getLongitude());
		assertEquals("Old Three Tooth by geocadet", cache.toString());
		
		cache = new Cache ("GC2E5QX	Red Butte Garden's Secret	edquest	1.5	1.5	N40 46.133	W111 49.404");
		assertEquals("GC2E5QX", cache.getGcCode());
		assertEquals("Red Butte Garden's Secret", cache.getTitle());
		assertEquals("edquest", cache.getOwner());
		assertEquals("1.5", Double.toString(cache.getDifficulty()));
		assertEquals("1.5", Double.toString(cache.getTerrain()));
		assertEquals("N40 46.133", cache.getLatitude());
		assertEquals("W111 49.404", cache.getLongitude());
		assertEquals("Red Butte Garden's Secret by edquest", cache.toString());
		
		cache = new Cache ("GC28EA	Below Red Butte	Kim Best	3	3.5	N40 45.802	W111 49.025");
		assertEquals("GC28EA", cache.getGcCode());
		assertEquals("Below Red Butte", cache.getTitle());
		assertEquals("Kim Best", cache.getOwner());
		assertEquals("3.0", Double.toString(cache.getDifficulty()));
		assertEquals("3.5", Double.toString(cache.getTerrain()));
		assertEquals("N40 45.802", cache.getLatitude());
		assertEquals("W111 49.025", cache.getLongitude());
		assertEquals("Below Red Butte by Kim Best", cache.toString());
	}
	
//	Sample Data:
//	GCRQWK	Old Three Tooth	geocadet	3.5	3	N40 45.850	W111 48.045
//	GC2E5QX	Red Butte Garden's Secret	edquest	1.5	1.5	N40 46.133	W111 49.404
//	GC28EA	Below Red Butte	Kim Best	3	3.5	N40 45.802	W111 49.025
//	GC2ETN9	Danger Garden	edquest	1.5	1	N40 45.993	W111 49.411

}
