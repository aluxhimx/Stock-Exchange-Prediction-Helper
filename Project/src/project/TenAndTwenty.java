package project;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Him on 10/1/2016.
 */
/*Context : dummy account
*g: useless
*
*
* */

public class TenAndTwenty {
    
    /*public void initialize(Object context) {
        g.stocks = get_index_stocks('000300.XSHG', date = context.current_dt);
        //g.stocks 就是���们要���纵������������票
        set_universe(g.stocks);
    }
    
    public void  before_trading_start(Context context) {
        //经买���������票
        g.buylist = set();

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
    public static double[] EXPMA(int N1,int N2,int N3,int N4,double m,double EXPMA10_1,double EXPMA20_1,double EXPMA50_1, double EXPMA100_1,double price) {
        double EXPMA10_new = calculatingEXPMA(N1, m, EXPMA10_1, price);
        double EXPMA20_new = calculatingEXPMA(N2, m, EXPMA20_1, price);
        double EXPMA50_new = calculatingEXPMA(N3, m, EXPMA50_1, price);
        double EXPMA100_new = calculatingEXPMA(N4, m, EXPMA100_1, price);
        double[] exmpa = {EXPMA10_new,EXPMA20_new, EXPMA50_new, EXPMA100_new};
        return exmpa;
    }
    // 定���MACD���数
   /* public static void  MACD(float macdprices, int fastperiod,int slowperiod,int signalperiod) {
        macd, signal, hist = talib.MACD(macdprices,
                fastperiod = fastperiod,
                slowperiod = slowperiod,
                signalperiod = signalperiod);
        return macd[-1] - signal[-1];
    }*/
            
    // for each security, determent if buy or not
    public static void  handle_data(ArrayList<Stock> stockList) {
        //EMA(10) > EMA(20) and MACD
        System.out.println("This function will show a list of stock that can be bought on the date that input by user.");
        System.out.println("Generating buy list..");


        ArrayList<String> buyList = new ArrayList<>();

        for (Stock stock : stockList) {
            //EXPMA������
            double close_price = stock.getClosePriceFromDate(MyDate.getDate());

            //double last_close = g.last_df[security][0];
            //double current_price = data[security].close;
            double EXPMA10_old, EXPMA20_old, EXPMA50_old, EXPMA100_old, EXPMA10_new, EXPMA20_new;
            //double EXPMA10_old, EXPMA20_old, EXPMA50_old, EXPMA100_old, EXPMA10_new, EXPMA20_new, EXPMA50_new, EXPMA100_new;
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
            //EXPMA10_new, EXPMA20_new, EXPMA50_new, EXPMA100_new = EXPMA(N1, N2, N3, N4, m, EXPMA10_1, EXPMA50_1, EXPMA100_1, EXPMA20_1, g.last_df[security][-1]);
            double[] exmpa = EXPMA(N1, N2, N3, N4, m, EXPMA10_old, EXPMA50_old, EXPMA100_old, EXPMA20_old, close_price);
            //MACD������

            EXPMA10_new = exmpa[0];
            EXPMA20_new = exmpa[1];
            //EXPMA50_new = exmpa[2];
            //EXPMA100_new = exmpa[3];
            //double macdprices = attribute_history(security, 40, '1d', ('close'));
            //���建MACD买���信号，���������个������fast period, slow period, and the signal
            //注���：MACD使用���price必须���narray
            int fastperiod = 12, slowperiod = 26, signalperiod = 9;
            //double macd = MACD(macdprices['close'].values, fastperiod , slowperiod , signalperiod );

            if (EXPMA10_new > EXPMA20_new) {
                buyList.add(stock.getSecurity());
            }
        }
        System.out.println("Result:");
        for (String sercurity : buyList) {
            System.out.print(buyList+",");
        }
    }
}
