package testProject;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import project.Stock;
import project.StockData;

public class testStock {

	@Test
	public void test1() {
		Stock stock=new Stock("600221");
		stock.addToList("0901","19.9","19.61","19.56","20.06");
		stock.addToList("0902","19.61","19.68","19.41","19.86");
		double result=stock.getClosePriceFromDate("0902");
		System.out.print(result);
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

	@Test
	public void test3() {
		Stock stock=new Stock("600221");
		stock.addToList("0901","19.9","19.61","19.56","20.06");
		stock.addToList("0902","19.9","19.61","19.56","20.06");
		//stock.addToList("0902","19.9","19.61","19.56","20.06");
		ArrayList<StockData> result=stock.getStockDataRange("0901","0920");
		StockData stockData1=new StockData("0901",19.9,19.61,19.56,20.06);
		StockData stockData2=new StockData("0902",19.9,19.61,19.56,20.06);
		ArrayList<StockData> expectedResult=new ArrayList<StockData>();
		expectedResult.add(stockData1);
		expectedResult.add(stockData2);
		assertEquals(expectedResult,result);
	}	
	
	@Test
	public void test4() {
		Stock stock=new Stock("600221");
		stock.addToList("0901","19.9","19.61","19.56","20.06");
		stock.addToList("0902","19.9","19.61","19.56","20.06");
		//stock.addToList("0902","19.9","19.61","19.56","20.06");
		ArrayList<StockData> result=stock.getStockDataRange("0901","920");
		StockData stockData1=new StockData("0901",19.9,19.61,19.56,20.06);
		StockData stockData2=new StockData("0902",19.9,19.61,19.56,20.06);
		ArrayList<StockData> expectedResult=new ArrayList<StockData>();
		expectedResult.add(stockData1);
		expectedResult.add(stockData2);
		assertEquals(expectedResult,result);
	}	
}
