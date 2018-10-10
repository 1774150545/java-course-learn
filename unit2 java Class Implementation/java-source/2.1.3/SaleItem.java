public class SaleItem {	
	private String isbn;
	private int unitsSold;
	private double revenue;    
	public SaleItem(String initialIsbn, 
			int initialUnitsSold, double initialRevenue ){		
		isbn = initialIsbn;
		unitsSold = initialUnitsSold;
		revenue = initialRevenue;		
	}	
	public String getIsbn(){		
		return isbn;
	}	
	public int getUnitsSold(){		
		return unitsSold;
	}
    public double getRevenue(){
		return revenue;
	}    
	public double avg_price() 	{
	    if (unitsSold!=0) 
	        return revenue/unitsSold;
	    else
	        return 0;
	}
}
//////////////////////////////////////////////////////
import java.util.*;
public class Generator {	
	public static SaleItem createSaleItem(String str, String deli){
	//²¹³ä´úÂë
		
		
	
	}		
	    public static void  main(String[]  args) {
	        String data = "A001_5_2013.54";
	        SaleItem saleItem = createSaleItem(data,"_");	        
	        System.out.println("isbn: " + saleItem.getIsbn());
	        System.out.println("unitSold: " + saleItem.getUnitsSold());
	        System.out.println("revenue: " + saleItem.getRevenue());
	        System.out.println("avgPrice: "+ saleItem.avgPrice());
	    }
}