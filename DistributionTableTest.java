package stats;

public class DistributionTableTest {
	public static void main(String...strings ) {
		findLookupRow_forNumerator();
		findLookupColor_forDenominator();
	}

	private static void findLookupRow_forNumerator() {
		
		DistributionTable table = new DistributionTable(); 
	
		int[] numerators = new int[]         { 1 ,	2,	3,	4,	5,	6,	7,	8,	9,	10,	11,	14,	20,	21,	29,	31,	59,	119,1000 };
		String result = "";
		for ( int i = 0 ; i < numerators.length; i++ ) { 
			int choosen = table.getRowIndex( numerators[i] ); 
			result += choosen + ","; 
		}
		String expected = "1,2,3,4,5,6,7,8,9,10,12,15,20,24,30,40,60,120,121,";
		boolean isOk = expected.equals(result);
		
		log( isOk );
	}

	private static void findLookupColor_forDenominator() {
		DistributionTable table = new DistributionTable(); 
	
		int[] denominators = new int[] { 1, 2, 3, 4, 5, 6, 28, 30, 31, 42, 62, 121, 1210 };

		String result = "";
		for ( int i = 0 ; i < denominators.length; i++ ) { 
			int choosen = table.getColIndex( denominators[i] ); 
			result += choosen + ","; 
		}
		String expected = "1,2,3,4,5,6,28,30,40,60,120,121,121,";
		boolean isOk = expected.equals(result);
		log( isOk );
	}
	
    private static void log( boolean result ){
        StackTraceElement[] ste = new Throwable().getStackTrace();

        String line = " ln: " + ste[1].getLineNumber();
        String clazz = " c: " + ste[1].getClassName();
        String method = " m: " + ste[1].getMethodName();
        
        String msg = result ? "PASS" : "FAIL";
        String out = msg + " |\t" + line + clazz + method;

        System.out.println( out );
    }
}
