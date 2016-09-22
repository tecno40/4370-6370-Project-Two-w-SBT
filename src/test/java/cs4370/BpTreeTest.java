/****************************************************************************************
 *@file BpTreeTest.java
 *
 *@author Nick Klepp
 *
 */

import org.unit.Test;
import static org.junit.Assert.*;
import junit.framework.*;

public class BpTreeTest extends TestCase{
    
    BpTreeMap <Integer, Integer> bpt; 

    protected void setUp(){
	int totalKeys = 20;
	boolean RANDOMLY = false;
	bpt1 = new BpTreeMap <> (Integer.class, Integer.class);
	bpt2 = new BpTreeMap <> (Integer.class, Integer.class);
	bpt
	
	if (RANDOMLY) {
	    Random rng = new Random ();
	    for (int i = 1; i <= totalKeys; i += 2) bpt.put (rng.nextInt (2 * totalKeys), i * i);
	} else {
	    for (int i = 2; i <= totalKeys; i += 2) bpt1.put (i * i, i * i);
	    for (int i = 1; i <= totalKeys; i += 2) bpt1.put (i * i, i * i);
	} // if
    }
    
    
}
