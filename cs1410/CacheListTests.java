package cs1410;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class CacheListTests
{
    private final static String badCaches =
            "GC2E5QX\tRed Butte Garden's Secret\tedquest\t1.5\t1.5\tN40 46.133\tW111 49.404\n" +
            "GC28EA\tBelow Red Butte\tKim Best\t3\t6\tN40 45.802\tW111 49.025\n" +
            "GC2ETN9\tDanger Garden\tedquest\t1.5\t1\tN40 45.993\tW111 49.411\n";
    
    private final static String goodCaches =
            "GC2E5QX\tRed Butte Garden's Secret\tedquest\t1.5\t1.5\tN40 46.133\tW111 49.404\n" +
            "GC28EA\tBelow Red Butte\tKim Best\t3\t1.5\tN40 45.802\tW111 49.025\n" +
            "GC2ETN9\tDanger Garden\tedquest\t1.5\t1\tN40 45.993\tW111 49.411\n";
    
    private final static String goodCaches2 =
    		"GC1QTHX\tWe Remember..... D.W.H.\tMooseman70\t2.5\t3\tN40 43.656\tW111 45.938\n" +
    		"GC39838\tKid's Challenge Cache\tJAC0B\t1.5\t1.5\tN40 46.271\tW111 50.397\n" +
    		"GC39836\tHealthy Geocacher's Challenge Cache\tJAC0B\t1.5\t1.5\tN40 46.103\tW111 50.183\n" +
    		"GCPR5D\tBar Coded\tFire Elemental\t4.5\t2\tN40 46.200\tW111 50.460\n";

    /**
     * Tests whether the constructor detects the error (terrain rating of 6) on line 2 of badCaches.
     */
    @Test
    public void testCacheList1 () throws IOException
    {
        try
        {
            CacheList list = new CacheList(new Scanner(badCaches));
            fail("No exception thrown");
            list.getOwners();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("2", e.getMessage());
        }       
    }
    
    /**
     * Tests whether select returns all three caches when all the constraints are set
     * to their defaults.
     */
    @Test
    public void testCacheList2 () throws IOException
    {
        CacheList list = new CacheList(new Scanner(goodCaches));
        ArrayList<Cache> caches = list.select();
        assertEquals(3, caches.size());
        assertEquals("Below Red Butte", caches.get(0).getTitle());
        assertEquals("Danger Garden", caches.get(1).getTitle());
        assertEquals("Red Butte Garden\'s Secret", caches.get(2).getTitle());
        // Note that we could be making sure that the other attributes of the caches are
        // as expected, but this is good enough for our purposes.
    }
    
    /**
     * Tests whether the select method works using the data from
     * goodCaches and calling the setTitleContraint and setDifficultyContraint
     * methods
     * @throws IOException
     */
    @Test
    public void testSelectMethod() throws IOException {
    	CacheList list = new CacheList(new Scanner(goodCaches));
    	list.setTitleConstraint("B");
    	list.setDifficultyConstraints(2.5, 5.0);
        ArrayList<Cache> caches = list.select();
        assertEquals(1, caches.size());
        assertEquals("Below Red Butte", caches.get(0).getTitle());
    }
    
    /**
     * Tests whether the select method works using the data from
     * goodCaches2 and calling the setOwnerConstraint method
     * @throws IOException
     */
    @Test
    public void testSelectMethod1() throws IOException {
    	CacheList list = new CacheList(new Scanner(goodCaches2));
    	list.setOwnerConstraint("JAC0B");
    	ArrayList<Cache> caches = list.select();
    	assertEquals(2, caches.size());
    	assertEquals("Healthy Geocacher's Challenge Cache", caches.get(0).getTitle());
    	assertEquals("Kid's Challenge Cache", caches.get(1).getTitle());
    }
}
