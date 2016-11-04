package project;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Created by Him on 10/9/2016.
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Stock> stockList;

        String file_path = "stock_data.txt";
        System.out.println("Using file path: " + file_path);
        File file = new File(file_path);
        stockList = inputData(file);

        Scanner input = new Scanner(System.in);
        System.out.println("Please input a date for prediction (0901 - 0930, exclude holidays):");
        String date = input.next();
        while (date.length() != 4) {
            System.out.println("Input Invalid. Date format: MMdd from 0901 to");
            date= input.next();
        }
        MyDate.setDate(date);
        System.out.println("Provided prediction:");
        System.out.printf("1. EMA(10) > EMA(ALL)\n2. EMA(10) > EMA(20) and MACD > 0\n3. MACD\n");
        System.out.println("Please select a prediction:");
        int method = input.nextInt();

        while (method < 1 || method > 3) {
            System.out.println("Input Invalid. Please select from the range provided:");
            method= input.nextInt();
        }
        input.close();
        gotoMethod(method,stockList);

    }

    public static ArrayList<Stock> inputData(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<Stock> stocks = new ArrayList<>();
        //stock array
        String security = "dummy";
        Stock stock = new Stock(security);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            char lastChar = line.charAt(line.length() - 1);
            if (lastChar == ':') {
                //System.out.println("this is a new stock");
                security = line.substring(0, line.length() - 1);
                stock = new Stock(security);
            } else {
                //System.out.println("this is data in "+ security);
                String[] data = line.substring(0, line.length() - 1).split(",");
                stock.addToList(data[0], data[1], data[2], data[3], data[4]);
            }

            if (lastChar != ':' && lastChar != ';') {
                System.out.println("" + stock);
                stocks.add(stock);
            }

        }
        input.close();

        System.out.println("Read in file success.");
        return stocks;
    }

    public static void gotoMethod(int method,ArrayList<Stock> stockList){
        switch (method) {
            case 1:
                System.out.println("You selected " + method);
                Ten.handle_data(stockList);
                break;
            case 2:
                System.out.println("You selected " + method);
                TenAndTwenty.handle_data(stockList);
                break;
            case 3:
                System.out.println("You selected " + method);
                Ten.handle_data(stockList);
                break;
            default:
                break;
        }
    }
}


