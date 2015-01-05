package cs1410;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A CacheList is a collection of Cache objects together with these six
 * constraints:
 * 
 * <ol>
 * <li>A title constraint</li>
 * <li>An owner constraint</li>
 * <li>A minimum difficulty constraint</li>
 * <li>A maximum difficulty constraint</li>
 * <li>A minimum terrain constraint</li>
 * <li>A maximum terrain constraint</li>
 * </ol>
 */
public class CacheList
{
    // The caches being managed by this CacheList. They are arranged in
    // ascending order according to cache title.
    private ArrayList<Cache> allCaches;
    private String titleConstraint;
    private String ownerConstraint;
    private double minDifficultyConstraint;
    private double maxDifficultyConstraint;
    private double minTerrainConstraint;
    private double maxTerrainConstraint;

    /**
     * Creates a CacheList from the specified Scanner. Each line of the Scanner
     * should contain the description of a cache in a format suitable for
     * consumption by the Cache constructor. The resulting CacheList should
     * contain one Cache object corresponding to each line of the Scanner.
     * 
     * Sets the initial value of the title and owner constraints to the empty
     * string, sets the minimum difficulty and terrain constraints to 1.0, and
     * sets the maximum difficulty and terrain constraints to 5.0.
     * 
     * Throws an IOException if the Scanner throws an IOException, or
     * an IllegalArgumentException if any of the individual lines are not
     * appropriate for the Cache constructor.
     * 
     * When an IllegalArgumentException e is thrown, e.getMessage() is the
     * number of the line that was being read when the error that triggered the
     * exception was encountered. Lines are numbered beginning with 1.
     */
    public CacheList (Scanner caches) throws IOException
    {
        allCaches = new ArrayList<Cache>();
        int lineCount = 1;
        while (caches.hasNextLine()) {
        	try {
        		Cache c = new Cache(caches.nextLine());
            	allCaches.add(c);
            	lineCount++;
        	} catch (IllegalArgumentException e) {
        		throw new IllegalArgumentException("" + lineCount);
        	}
        }
        caches.close();
        Collections.sort(allCaches, new CacheComparator());
        titleConstraint = "";
        ownerConstraint = "";
        minDifficultyConstraint = 1.0;
        minTerrainConstraint = 1.0;
        maxDifficultyConstraint = 5.0;
        maxTerrainConstraint = 5.0;
    }

    /**
     * Sets the title constraint to the specified value.
     */
    public void setTitleConstraint (String title)
    {
        titleConstraint = title;
    }

    /**
     * Sets the owner constraint to the specified value.
     */
    public void setOwnerConstraint (String owner)
    {
        ownerConstraint = owner;
    }

    /**
     * Sets the minimum and maximum difficulty constraints to the specified
     * values.
     */
    public void setDifficultyConstraints (double min, double max)
    {
    	minDifficultyConstraint = min;
    	maxDifficultyConstraint = max;
    }

    /**
     * Sets the minimum and maximum terrain constraints to the specified values.
     */
    public void setTerrainConstraints (double min, double max)
    {
    	minTerrainConstraint = min;
    	maxTerrainConstraint = max;
    }

    /**
     * Returns a list that contains each cache c from the CacheList so long as
     * c's title contains the title constraint as a substring, c's owner equals
     * the owner constraint (unless the owner constraint is empty), c's
     * difficulty rating is between the minimum and maximum difficulties
     * (inclusive), and c's terrain rating is between the minimum and maximum
     * terrains (inclusive).
     * 
     * The returned list is arranged in ascending order by cache title.
     */
    public ArrayList<Cache> select ()
    {
        ArrayList<Cache> caches = new ArrayList<Cache>();
        for (Cache c : allCaches) {
        	String owner = c.getOwner();
        	String title = c.getTitle();
        	double terrain = c.getTerrain();
        	double difficulty = c.getDifficulty();
        	if ((owner.equals(ownerConstraint) || ownerConstraint.equals(""))
        			&& difficulty <= maxDifficultyConstraint && difficulty >= minDifficultyConstraint
        			&& terrain <= maxTerrainConstraint && terrain >= minTerrainConstraint
        			&& (title.contains(titleConstraint) || titleConstraint.equals(""))) {
        		caches.add(c);
        	}
        }
        Collections.sort(caches, new CacheComparator());
        return caches;
    }

    /**
     * Returns a list containing all the owners of all of the Cache objects in
     * this CacheList. There are no duplicates. The list is arranged in
     * ascending order.
     */
    public ArrayList<String> getOwners ()
    {
        ArrayList<String> owners = new ArrayList<String>();
        for (Cache c : allCaches) {
        	String owner = c.getOwner();
        	boolean notRepeat = true;
    		for (int i = 0; i < owners.size(); i++) {
    			if (owners.get(i).equals(owner)) {
    				notRepeat = false;
    			}
    		}
        	if (notRepeat) {
        		owners.add(owner);
        	}
        }
        Collections.sort(owners, new StringComparator());
        return owners;
    }

    /**
     * Useful for sorting lists of strings.
     */
    private class StringComparator implements Comparator<String>
    {
        public int compare (String s1, String s2)
        {
            return s1.compareToIgnoreCase(s2);
        }
    }

    /**
     * Useful for sorting lists of caches.
     */
    private class CacheComparator implements Comparator<Cache>
    {
        public int compare (Cache c1, Cache c2)
        {
            return c1.getTitle().compareToIgnoreCase(c2.getTitle());
        }
    }
}
