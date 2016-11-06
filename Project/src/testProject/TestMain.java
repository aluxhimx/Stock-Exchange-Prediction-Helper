package testProject;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.ExDateNotFound;
import project.Main;
import project.Stock;
import project.StockData;
import project.Ten;

public class TestMain {


	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	@Test
	public void testInputData() throws FileNotFoundException {
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
	
	@Test(expected=FileNotFoundException.class)
	public void testFileNotFound() throws FileNotFoundException {
		File file=new File("123.txt");
		ArrayList<Stock> result=Main.inputData(file);
	}	

//	@Test
//	public void testGotoMethod() {
//		class StubTen extends Ten{
//			public static void handle_data(ArrayList<Stock> stockList){
//				
//			}
//		}
//		ArrayList<Stock> stockList=new ArrayList<Stock>();
//		Main.gotoMethod(1, stockList);
//		assertEquals("You selected 1",outContent.toString());
//	}		
}
