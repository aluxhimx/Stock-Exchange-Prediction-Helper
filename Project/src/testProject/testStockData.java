package testProject;

import static org.junit.Assert.*;
import project.*;

import org.junit.Before;
import org.junit.Test;

public class testStockData {
    /**
     * Sets up the test fixture.
     * 
     * Called before every test case method.
     */
    @Before
    public void setUp() {}

    /**
     * Tears down the test fixture.
     * 
     * Called after every test case method.
     */
    public void tearDown() {}	
    
	@Test
	public void test1() {
		StockData test=new StockData("0901",19.9,19.61,19.56,20.06);
		boolean result=test.equals("0901");
		assertEquals(true,result);
	}

	@Test
	public void test2() {
		StockData test=new StockData("0901",19.9,19.61,19.56,20.06);
		boolean result=test.equals("0902");
		assertEquals(false,result);
	}

	@Test
	public void test3() {
		StockData test=new StockData("0901",19.9,19.61,19.56,20.06);
		boolean result=test.equals(12312);
		assertEquals(false,result);
	}	

	@Test
	public void test4() {
		StockData test=new StockData("0901",19.9,19.61,19.56,20.06);
		boolean result=test.equals(null);
		assertEquals(false,result);
	}		

	@Test
	public void test5() {
		StockData test=new StockData("0901",19.9,19.61,19.56,20.06);
		boolean result=test.equals("");
		assertEquals(false,result);
	}			

	
}
