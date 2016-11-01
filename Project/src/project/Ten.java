package project;
/**
 * Created by Him on 10/3/2016.
 */
//import math;

import java.util.ArrayList;

public class Ten {

    /*public void initialize() {

        g.stocks = list(get_all_securities(['stock'], date = context.current_dt).index);
        //g.stocks 就是���们要���纵������������票
        //set_universe(g.stocks);
    }*/

    /*public void before_trading_start(Context context) {
        //经买���������票
        g.buyList = set();

        //得到���������票���日��������� 每天������要��������, ���������在 before_trading_start ���
        g.last_df = history(1, '1d', 'close');
    }*/

    //计���EXPMA
    public static double calculatingEXPMA(int N, double m, double oldEXPMA, double price) {
        double a = m / (N + 1);
        double newEXPMA = a * price + (1 - a) * oldEXPMA;
        return newEXPMA;
    }

    //计���EXPMA10,20,50,100,150����������
    public static double[] EXPMA(int N1, int N2, int N3, int N4, double m, double EXPMA10_old, double EXPMA20_old, double EXPMA50_old, double EXPMA100_old, double price) {
        double EXPMA10_new = calculatingEXPMA(N1, m, EXPMA10_old, price);
        double EXPMA20_new = calculatingEXPMA(N2, m, EXPMA20_old, price);
        double EXPMA50_new = calculatingEXPMA(N3, m, EXPMA50_old, price);
        double EXPMA100_new = calculatingEXPMA(N4, m, EXPMA100_old, price);
        double[] exmpa = {EXPMA10_new, EXPMA20_new, EXPMA50_new, EXPMA100_new};
        return exmpa;
    }

    // ���������������第�����运���, data ��������������������������据
    public static void handle_data(ArrayList<Stock> stockList) {
        //EMA(10) > EMA(ALL)
        System.out.println("This function will show a list of stock that can be bought on the date that input by user.");

        System.out.println("Generating buy list..");


        ArrayList<String> buyList = new ArrayList<>();

        for (Stock stock : stockList) {

            double close_price = stock.getClosePriceFromDate(MyDate.getDate());
            //last_close = g.last_df[security][0];
            //currnt_price = data[security].close;
            double EXPMA10_old, EXPMA20_old, EXPMA50_old, EXPMA100_old, EXPMA10_new, EXPMA20_new, EXPMA50_new, EXPMA100_new;

            EXPMA10_old = close_price;
            EXPMA20_old = close_price;
            EXPMA50_old = close_price;
            EXPMA100_old = close_price;

            //m������计���平���系数a = m / (N + 1)
            double m = 2.0;
            //设������数平�������������
            int N1 = 10;
            int N2 = 20;
            int N3 = 50;
            int N4 = 100;
            //EXPMA10_new, EXPMA20_new, EXPMA50_new, EXPMA100_new = EXPMA(N1, N2, N3, N4, m, EXPMA10_old, EXPMA50_old, EXPMA100_old, EXPMA20_old, g.last_df[security][-1])
            double[] exmpa = EXPMA(N1, N2, N3, N4, m, EXPMA10_old, EXPMA50_old, EXPMA100_old, EXPMA20_old, close_price);
            EXPMA10_new = exmpa[0];
            EXPMA20_new = exmpa[1];
            EXPMA50_new = exmpa[2];
            EXPMA100_new = exmpa[3];

            if (EXPMA10_new > EXPMA20_new && EXPMA10_new > EXPMA50_new && EXPMA10_new > EXPMA100_new)
                //get MyDate's data's security
                buyList.add(stock.getSecurity());
        }
        System.out.println("Result:");
        for (String sercurity : buyList) {
            System.out.print(buyList+",");
        }

    }
}
