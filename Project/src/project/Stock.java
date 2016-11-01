package project;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Him on 10/9/2016.
 */
public class Stock {


    private String security;
    private ArrayList<StockData> dataList;

    public Stock(String security) {
        this.security = security;
        this.dataList = new ArrayList<>() ;
    }

    public void addToList(String s, String s1, String s2, String s3, String s4){
        StockData data = new StockData(s,Double.parseDouble(s1),Double.parseDouble(s2),Double.parseDouble(s3),Double.parseDouble(s4));
        dataList.add(data);
    }

    @Override
    public String toString(){
        return "Security: "+ security + ", Data: " + dataList;
    }

    public double getClosePriceFromDate(String date) {
        StockData res = searchFromListByDate(date);
        return res.getClose_price();
    }

    public String getSecurity() {
        return security;
    }

    private StockData searchFromListByDate(String date) {
        StockData res = null;
        for (StockData x : this.dataList) {
            if(x.equals(date))
                res = x;
            else{
                System.out.println("The input date is not a valid date. (date:"+MyDate.getDate()+")");
                return null;
            }
        }
        return res;
    }

    //return arrayList of data which ranged from two date inclusively
    public ArrayList<StockData> getStockDataRange(String fromDate, String toDate){
        ArrayList<StockData> res= new ArrayList<>();
        int _fromDate = Integer.parseInt(fromDate);
        int _toDate = Integer.parseInt(toDate);

        for (int i = _fromDate; i < _toDate+1; i++) {
            String searchDate = Integer.toString(i);

            if(searchDate.length()!=4)
                searchDate = new StringBuilder(searchDate).insert(0, "0").toString();

            res.add(this.searchFromListByDate(searchDate));
        }

        return res;
    }
}
