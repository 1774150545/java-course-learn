import java.util.*;
public class Generator {
	
	/*
	public static SaleItem createSaleItem(String str, String deli){
		
		
	}
    */
	
	
	public static SaleItem createSaleItem(String str, String deli){
			
			StringTokenizer tokenizer = new StringTokenizer(str, deli);
			if (tokenizer.countTokens() == 3){
				
				String isbn = tokenizer.nextToken();
		        int unitSold = Integer.parseInt(tokenizer.nextToken());
		        double revenue = Double.parseDouble(tokenizer.nextToken());
				return new SaleItem(isbn,unitSold,revenue);
			} else {
				
				return null;
			}
	        
		}	/**/
		
	    public static void  main(String[]  args) {

	        String data = "A001_5_2013.54";
	        SaleItem saleItem = createSaleItem(data,"_");
	        
	        System.out.println("isbn: " + saleItem.getIsbn());
	        System.out.println("unitSold: " + saleItem.getUnitsSold());
	        System.out.println("revenue: " + saleItem.getRevenue());
	        System.out.println("avgPrice: "+ saleItem.avgPrice());
	    }
	



}
