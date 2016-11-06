package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Crocodile
{
	
	public static boolean CompareFraction() 
	{
		boolean result = false;
		Scanner stockprice = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter recent 6 days price:");
		double[] fivedaysprice = new double[6];
		for(int i =0;i<6;i++)
		{
			fivedaysprice[i] = stockprice.nextDouble(); 
		}
		
		if(fivedaysprice[0]<fivedaysprice[1] && fivedaysprice[1]<fivedaysprice[2] && fivedaysprice[2]>fivedaysprice[3] &&
		   fivedaysprice[3]>fivedaysprice[4] && fivedaysprice[6]>fivedaysprice[2])
			result = true;
		else
			result = false;
			
		return result;
	}


public static void handle_data(ArrayList<Stock> stockList) 
{

    System.out.println("This function will show a list of stock that can be bought on the date that input by user.");

    System.out.println("Generating buy list..");


    ArrayList<String> buyList = new ArrayList<>();
    for (Stock stock : stockList) {
		double close_price;
	try {
		close_price = stock.getClosePriceFromDate(MyDate.getDate());
	}catch (ExDateNotFound e) {

	}

        boolean effectivefraction = CompareFraction();
        
        if(effectivefraction = true)
            buyList.add(stock.getSecurity());
        
        System.out.println("Result:");
        for (String sercurity : buyList) 
        {
            System.out.print(buyList+",");
        }
    }
}
}
