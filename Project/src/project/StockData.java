/**
 * Created by Him on 10/10/2016.
 */
package project; 
 
public class StockData {
    private String date;
    private double open_price;
    private double close_price;
    private double highest_price;
    private double lowest_price;

    public StockData(String date, double open_price, double close_price, double highest_price, double lowest_price) {
        this.date = date;
        this.open_price = open_price;
        this.close_price = close_price;
        this.highest_price = highest_price;
        this.lowest_price = lowest_price;
    }

    public double getClose_price() {
        return close_price;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString(){
        return date +", close_price:"+ close_price;
    }

    @Override
    public boolean equals (Object in) {
        //System.out.println("reached here");
        if(in == null) {
            return false;
        }else if(in instanceof String) {
            String date = (String) in;
            return date.equals(this.date);
        }else {
            return false;
        }
    }
}
