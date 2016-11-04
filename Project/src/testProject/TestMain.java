package testProject;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import project.Main;
import project.Stock;
import project.StockData;
import project.Ten;

public class TestMain {

	@Test
	public void test1() throws FileNotFoundException {
		File file=new File("stock_data1.txt");
		ArrayList<Stock> result=Main.inputData(file);
		
		
		ArrayList<Stock> expectedResult=new ArrayList<Stock>();
		Stock stock1=new Stock("600221");
		stock1.addToList("0901","19.9","19.61","19.56","20.06");
		stock1.addToList("0902","19.61","19.68","19.41","19.86");
		stock1.addToList("0905","19.68","19.8","19.6","20.08");
		
		Stock stock2=new Stock("60052");
		stock2.addToList("0901","18.39","18.25","18.22","18.62");
		stock2.addToList("0902","18.25","18.18","18.02","18.38");
		stock2.addToList("0905","18.18","18.27","18.16","18.4");
	
		expectedResult.add(stock1);
		expectedResult.add(stock2);
		
		//System.out.print(result.get(0).getSecurity());
		
		assertEquals(expectedResult,result);
	}	
}
