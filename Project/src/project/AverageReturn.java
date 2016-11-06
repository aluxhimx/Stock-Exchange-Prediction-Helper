package project;

import java.util.ArrayList;
import java.util.Scanner;

public class AverageReturn 
{
	
	public static double calculate5daysAverage() 
	{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter recent 5 days price:");
		double[] n = new double [5];
		for(int i=0;i<5;i++)
		{
			n[i] = reader.nextInt();
		}
		double average = (n[0]+n[1]+n[2]+n[3]+n[4])/5;
		System.out.println(average);
		return average;		
	}

	public static double calculate10daysAverage() 
	{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter recent 10 days price:");
		double[] n = new double [10];
		for(int i=0;i<10;i++)
		{
			n[i] = reader.nextInt();
		}
		double average = (n[0]+n[1]+n[2]+n[3]+n[4]+n[5]+n[6]+n[7]+n[8]+n[9])/10;
		System.out.println(average);
		return average;		
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
		}catch (ExDateNotFound e){

		}
        
        double fivedays = calculate5daysAverage();
        double tendays = calculate10daysAverage(); 
        
        if (fivedays > tendays)
            //get MyDate's data's security
            buyList.add(stock.getSecurity());
        
        System.out.println("Result:");
        for (String sercurity : buyList) 
        {
            System.out.print(buyList+",");
        }
    }
}
}
