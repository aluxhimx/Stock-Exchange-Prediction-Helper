package testProject;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import project.Stock;
import project.StockData;
import project.Ten;

public class testTen {

	@Test
	public void test1() {
		double result=Ten.calculatingEXPMA(234,234.2,234.2,234.2);
		
		assertEquals(123,result,0.01);
	}	
}
