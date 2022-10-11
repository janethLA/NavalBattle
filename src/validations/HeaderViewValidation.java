package validations;

public class HeaderViewValidation {
	
	public static boolean validationForPositionField(String x, String y) {
		boolean result = false;
		if(x.length() == 1) {
			try {
				char coorX = x.charAt(0);
				int coorY = Integer.parseInt(y);
				//System.out.println("coorX"+coorX+"coorY"+coorY);
				if( validationCoorX(coorX) && validationCoorY(coorY) )
					result = true;
			} catch (Exception e) {}
		}
		return result;
	}
	
    private static boolean validationCoorX(char x) {
    	boolean result = false;
    	for(char i = 'A'; i <= 'J' ; i++) {
			 if(x== i)
					result = true;
		}
    	return result;
    }
    
    public static boolean validationCoorY(int y) {
    	boolean result = false;
    	for(int i = 1; i <= 10 ; i++) {
			if(y==i)
				result = true;
		 }
    	return result;
    }

}
