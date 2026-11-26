//someone who bought Microsoft stock in 2000 would be negative; their $39.81 stock is now worth $28.80 climbing from its lowest of $15.81
//the global financial crisis of 2008, the housing market bubble popped, plumitting the rest of the market as well. Decrease market, decrease stock price.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("sp500_1925_1945.txt"));
        String symbol = in.next();
        int n = in.nextInt();
	

        int[] years = new int[n];
	int[] months = new int[n];
	double[] prices = new double[n];

	System.out.printf("Ticker: %s%n", symbol);
	System.out.printf("Months: %d%n", n);
	loadData(in, years, months, prices);
	printData(years, months, prices);
	System.out.printf("%s lowest:  $%.2f in %s %d%n", symbol, prices[indexOfLowest(prices)], monthName(months[indexOfLowest(prices)]), years[indexOfLowest(prices)]);
	System.out.printf("%s highest: $%.2f in %s %d%n", symbol, prices[indexOfHighest(prices)], monthName(months[indexOfHighest(prices)]), years[indexOfHighest(prices)]);
	System.out.printf("%s bigDrop: %.2f%% in %s %d%n", symbol, percentDif(prices, indexOfBiggestDrop(prices)), monthName(months[indexOfBiggestDrop(prices)]), years[indexOfBiggestDrop(prices)]);
	
	 
	double result = simulate(years, months, prices);
	System.out.printf("Final value: $%,.2f%n", result);

    }
    
    public static void loadData(Scanner in, int[] years, int[] months, double[] prices) {
	for(int i = 0; i < years.length; i++) {
	    years[i] = in.nextInt();
	    months[i] = in.nextInt();
	    prices[i] = in.nextDouble();
	}
    }
    
    public static String monthName(int month) {
	String[] letMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	return letMonths[month-1];
    }
    
    public static void printData(int[] years, int[] months, double[] prices) {
	for(int i = 0; i < years.length; i++) {
	    System.out.printf("%s %d: $%.2f%n", monthName(months[i]), years[i], prices[i]);
	}
    }
    
    public static int indexOfLowest(double[] prices) {
	int index = 0;
	for(int i = 0; i < prices.length; i++) {
	    if(prices[i] < prices[index]) index = i;
	}
	return index;
    }
    
    public static int indexOfHighest(double[] prices) {
	int index = 0;
	for(int i = 0; i < prices.length; i++) {
	    if(prices[i] > prices[index]) index = i;
	}
	return index;
    }
    
    public static int indexOfBiggestDrop(double[] prices) {
	int index = 1;
	for(int i = 2; i < prices.length; i++) {
	    if((prices[i] - prices[i-1])/prices[i-1] < (prices[index] - prices[index-1])/prices[index-1]) index = i;
	}
	return index;
    }
    
    public static double percentDif(double[] prices, int i) {
	return (prices[i] - prices[i - 1]) / prices[i - 1] * 100;
    }
    
    public static final double START_CASH = 10000.0;
    public static final double FEE = 10.0;
    
    public static double simulate(int[] years, int[] months, double[] prices) {
	double cash = START_CASH;
	int shares = 0;
	double boughtAt = 0.0;

	for (int i = 0; i < prices.length; i++) {
	    double[] history = Arrays.copyOf(prices, i + 1);
	    String action = decide(history, shares > 0, boughtAt);
    
	    if (action.equals("BUY") && shares == 0) {
		int canBuy = (int) ((cash - FEE) / prices[i]);
		if (canBuy > 0) {
		    shares = canBuy;
		    cash = cash - shares * prices[i] - FEE;
		    boughtAt = prices[i];
		    System.out.printf("%s %d: BUY %d shares at $%.2f%n", monthName(months[i]), years[i], shares, prices[i]);
		}
	    } else if (action.equals("SELL") && shares > 0) {
		cash = cash + shares * prices[i] - FEE;
		System.out.printf("%s %d: SELL %d shares at $%.2f%n", monthName(months[i]), years[i], shares, prices[i]);
		shares = 0;
		boughtAt = 0.0;
	    }
	}
	return cash + shares * prices[prices.length - 1];
    }
    
    public static String decide(double[] history, boolean holding, double boughtAt) {
	return bigBoyPants(history, holding, boughtAt);
    }
    
    public static String buyAndHold(double[] history, boolean holding, double boughtAt) {
	return "BUY";
    }
    
    public static String bigBoyPants(double[] history, boolean holding, double boughtAt) {
	if(history.length > 1 && percentDif(history, history.length-1) < 0 && percentDif(history, history.length-1) > -2) return "HOLD";
	if(history.length > 1 && history[history.length-1] < history[history.length-2]) return "SELL";
	return "BUY";
    }

    public static String dipBuyer(double[] history, boolean holding, double boughtAt) {
	int today = history.length-1;
	if(!holding && history.length > 2 && history[today] < history[today-1] && history[today-1] < history[today-2]) return "BUY";
	if(holding && history[today] >= boughtAt * 1.10) return "SELL";
	return "HOLD";
    }

    public static String movingAverage(double[] history, boolean holding, double boughtAt) {
	if(history.length < 12) return "HOLD";
	double sum = 0;
	for(int i = history.length-12; i < history.length; i++) {
	    sum += history[i];
	}
	double average = sum / 12;
	double today = history[history.length-1];
	if(!holding && today > average) return "BUY";
	if(holding && today < average) return "SELL";
	return "HOLD";
    }
}
