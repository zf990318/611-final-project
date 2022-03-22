package LoanStockCurrency;

public class Currency {
    static Float USDToCNY = 6.39f;
    static Float USDToCAD = 1.27f;
    static Float CADToCNY = 5.01f;
    static Float CADToUSD = 0.79f;
    static Float CNYToCAD = 0.20f;
    static Float CNYToUSD = 0.16f;

    public static Float CurrencyRate(String source, String target){
        if (source.equals("USD") && target.equals("CNY")){return USDToCNY;}
        if (source.equals("USD") && target.equals("CAD")){return USDToCAD;}
        if (source.equals("CNY") && target.equals("CAD")){return CNYToCAD;}
        if (source.equals("CNY") && target.equals("USD")){return CNYToUSD;}
        if (source.equals("CAD") && target.equals("CNY")){return CADToCNY;}
        if (source.equals("CAD") && target.equals("USD")){return CADToUSD;}
        return null;
    }
}

