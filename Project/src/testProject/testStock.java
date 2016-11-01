package testProject;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Stock;

public class testStock {

	@Test
	public void test1() {
		Stock stock=new Stock("600221");
		stock.addToList("0901","19.9","19.61","19.56","20.06");
		//stock.addToList("0902","19.9","19.61","19.56","20.06");
		double result=stock.getClosePriceFromDate("0901");
		assertEquals(19.61,result,0.01);
	}

	@Test
	public void test2() {
		Stock stock=new Stock("600221");
		stock.addToList("0901","19.9","19.61","19.56","20.06");
		//stock.addToList("0902","19.9","19.61","19.56","20.06");
		double result=stock.getClosePriceFromDate("0902");
		assertEquals(null,result);
	}	
	
}
