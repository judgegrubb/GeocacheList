package cs1410;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;

/**
 * Represents a variety of information about a geocache. A geocache has a title,
 * an owner, a difficulty rating, a terrain rating, a GC code, a latitude, and a
 * longitude.
 */
public class Cache
{
    private String GcCode = "";
	private String title = "";
	private String owner = "";
	private double difficulty = 0;
	private double terrain = 0;
	private String latitude = "";
	private String longitude = "";

    /**
     * Creates a Cache from a string that consists of these seven cache
     * attributes: the GC code, the title, the owner, the difficulty rating, the
     * terrain rating, the latitude, and the longitude, in that order, separated
     * by single TAB ('\t') characters.
     * 
     * If any of the following problems are present, throws an
     * IllegalArgumentException:
     * <ul>
     * <li>Fewer than seven attributes</li> ++
     * <li>More than seven attributes</li> ++
     * <li>A GC code that is anything other than "GC" followed by one or more
     * upper-case letters and/or digits</li> ++
     * <li>A difficulty or terrain rating that parses to anything other than the
     * doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, or 5.</li>
     * <li>A title, owner, latitude, or longitude that consists only of white
     * space</li>
     */
    public Cache (String attributes)
    {
    	
    	// take the attributes and turn them
    	// into an ArrayList based on being split
    	// but \t into it
    	String[] splitArray = {};
    	try {
    	    splitArray = attributes.split("\\t+");
    	} catch (PatternSyntaxException ex) {
    	    throw new IllegalArgumentException();
    	}
    	ArrayList<String> attributesArray = new ArrayList<String>(Arrays.asList(splitArray));
    	
    	// check for different cases and if
    	// the data violates it then throw
    	// an IllegalArgumentException
    	if (attributesArray.size() > 7 || attributesArray.size() < 7) {
    		throw new IllegalArgumentException();
    	} else if(!attributesArray.get(0).substring(0, 2).equals("GC")) {
    		throw new IllegalArgumentException();
    	}
    	
    	this.GcCode = attributesArray.get(0);
    	this.title = attributesArray.get(1);
    	this.owner = attributesArray.get(2);
    	this.difficulty = Double.parseDouble(attributesArray.get(3));
    	this.terrain = Double.parseDouble(attributesArray.get(4));
    	difficultyOrTerrain(difficulty, terrain);
    	this.latitude = attributesArray.get(5);
    	this.longitude = attributesArray.get(6);
    	testForWhitespace(GcCode);
    	testForWhitespace(title);
    	testForWhitespace(owner);
    	testForWhitespace(latitude);
    	testForWhitespace(longitude);
    }
    
    /**
     * 
     * @param s
     */
    public void testForWhitespace(String s) {
		if(s.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
	}

	/**
     * checks to make sure that difficulty and terrain
     * are one of the doubles 1, 1.5, 2, 2.5, 3, 3.5, 
     * 4, 4.5, or 5 and returns false if otherwise.
     * @param d
     * @param t
     * @return
     */
    public void difficultyOrTerrain(double d, double t) {
    	if (d > 5 || d < 1 || t > 5 || t < 1 || (d != 1.0 && d != 1.5 && d != 2.0 && d != 2.5 && d != 3.0 && d != 3.5 && d != 4.0 && d != 4.5 && d != 5.0) || (t != 1.0 && t != 1.5 && t != 2.0 && t != 2.5 && t != 3.0 && t != 3.5 && t != 4.0 && t != 4.5 && t != 5.0)) {
    		throw new IllegalArgumentException();
    	}
    }

    /**
     * Converts this cache to a string
     */
    public String toString ()
    {
        return getTitle() + " by " + getOwner();
    }

    /**
     * Returns the owner of this cache
     */
    public String getOwner ()
    {
        return owner;
    }

    /**
     * Returns the title of this cache
     */
    public String getTitle ()
    {
        return title;
    }

    /**
     * Returns the difficulty rating of this cache
     */
    public double getDifficulty ()
    {
        return difficulty;
    }

    /**
     * Returns the terrain rating of this cache
     */
    public double getTerrain ()
    {
        return terrain;
    }

    /**
     * Returns the GC code of this cache
     */
    public String getGcCode ()
    {
        return GcCode;
    }

    /**
     * Returns the latitude of this cache
     */
    public String getLatitude ()
    {
        return latitude;
    }

    /**
     * Returns the longitude of this cache
     */
    public String getLongitude ()
    {
        return longitude;
    }
}
