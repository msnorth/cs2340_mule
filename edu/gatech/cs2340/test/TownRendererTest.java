package edu.gatech.cs2340.test;
import static org.junit.Assert.fail;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.gatech.cs2340.ui.TownRendererUpdated;

/**
 * 
 */

/**
 * @author maddy
 *
 */
public class TownRendererTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		JFrame jf = new JFrame();
		TownRendererUpdated tr = new TownRendererUpdated(null);
		jf.add(tr);
		jf.setVisible(true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
	}

}
