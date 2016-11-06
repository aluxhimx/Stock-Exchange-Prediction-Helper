package project;

import java.util.ArrayList;

/**
 * Created by Him on 10/5/2016.
 */
//import math
public class MACD {

    int i = 22;

    //Object stocklist = get_index_stocks('000300.XSHG');
    //stocklist = ['600196.XSHG','601318.XSHG','510300.XSHG','510050.XSHG']
    //security=stocklist[i];

    //benchmarklist=['000300.XSHG','510050.XSHG','000300.XSHG','510050.XSHG'];
    //benchmark=benchmarklist[0];

    //set_universe([security]);

    //set_benchmark(benchmark);

    //set_commission(PerTrade(buy_cost=0.0008, sell_cost=0.0013, min_cost=5);


    //set_slippage(FixedSlippage(0);

    //double init_price=0;


    /*public void f_expma(N, m, EXPMA1, price) {
        a = m / (N + 1)
        EXPMA2 = a * price + (1 - a) * EXPMA1
        return EXPMA2 //2为后一天值
    }*/

    public static double f_expma(int N, double m, double EXPMA1, double price) {
        double a = m / (N + 1);
        double EXPMA2 = a * price + (1 - a) * EXPMA1;
        return EXPMA2;
    }


    public static double[] macd(int N1,int N2,int N3,double m,double EXPMA12_1, double EXPMA26_1, double DEA1, double price) {
        double EXPMA12_2 = f_expma(N1, m, EXPMA12_1, price);
        double EXPMA26_2 = f_expma(N2, m, EXPMA26_1, price);
        double DIF2 = EXPMA12_2 - EXPMA26_2;
        double a = m / (N3 + 1);
        double DEA2 = a * DIF2 + (1 - a) * DEA1;
        double BAR2 = 2 * (DIF2 - DEA2);
        double[] res = {EXPMA12_2,EXPMA26_2, DIF2, DEA2, BAR2};
        return res;
    }
        // 每个单位时间(如果按天回测,则每天调用一次,如果按分钟,则每分钟调用一次)调用一次

    public static void handle_data (ArrayList<Stock> stockList) {
        //MACD

        System.out.println("This function will show a list of stock that can be bought on the date that input by user.");
        System.out.println("Generating buy list..");


        ArrayList<String> buyList = new ArrayList<>();

        for (Stock stock : stockList) {
            double close_price;
            try {
                close_price = stock.getClosePriceFromDate(MyDate.getDate());
            }catch (ExDateNotFound e){
    return;
            }
            //如果是停牌不进行计算
            //if (!math.isnan(close_price[security][0])){
            double init_price = 0, EXPMA12_1 = 0, EXPMA26_1 = 0, EXPMA12_2, EXPMA26_2, DIF1 = 0, DIF2 , DEA1=0, DEA2, BAR2;
            /*if (init_price != 0){
                init_price = close_price[security].mean();//nan和N-1个数，mean为N-1个数的均值
                EXPMA12_1 = init_price;
                EXPMA26_1 = init_price;
                DIF1 = init_price;
                DEA1 = init_price;
            }*/
            // m用于计算平滑系数a=m/(N+1)
            double m = 2.0;
            //设定指数平滑基期数
            int N1 = 12;
            int N2 = 26;
            int N3 = 9;
            double[] res = macd(N1, N2, N3, m, EXPMA12_1, EXPMA26_1, DEA1, close_price);
            EXPMA12_2 = res[0];
            EXPMA26_2 = res[1];
            DIF2 = res[2];
            DEA2 = res[3];
            BAR2 = res[4];
            // 取得当前价格
            //double current_price = data[security].price;
            // 取得当前的现金
            //float cash = context.portfolio.cash;
            // DIF、DEA均为正，DIF向上突破DEA，买入信号参考
            if (DIF2 > 0 && DEA2 > 0 && DIF1 < DEA1 && DIF2 > DEA2) {
                buyList.add(stock.getSecurity());
                // 计算可以买多少只股票
                /*int number_of_shares = (int) (cash / current_price);
                // 购买量大于0时，下单
                if (number_of_shares > 0) {
                    // 以市单价买入股票，日回测时即是开盘价
                    order(security, +number_of_shares);
                    // 记录这次买入
                    log.info("Buying %s" % (security));
                }
                // DIF、DEA均为负，DIF向下突破DEA，卖出信号参考
                else if (DIF2 < 0 && DEA2 < 0 && DIF1 > DEA1 && DIF2 < DEA2 && context.portfolio.positions[security].amount > 0) {
                    // 卖出所有股票,使这只股票的最终持有量为0
                    order_target(security, 0);
                    // 记录这次卖出
                    log.info("Selling %s" % (security));
                }*/
            }
            //将今日的值付给全局变量作为下一次前一日的值
            DEA1 = DEA2;
            DIF1 = DIF2;
            EXPMA12_1 = EXPMA12_2;
            EXPMA26_1 = EXPMA26_2;
            //}
            // 画出当前的价格
        }

        System.out.println("Result:");
        for (String sercurity : buyList) {
            System.out.print(buyList+",");
        }
    }
}

