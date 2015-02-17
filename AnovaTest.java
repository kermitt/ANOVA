package stats;


/*
 *  test data taken from a youtube video anova excel tutorial
 */
public class AnovaTest {
	private static Anova anova = new Anova(); 
	public static void main(String[] args) { 
		populate_step1();
		findWithinGroupMeans_step2();
		setSumOfSquaresOfGroups_step3();
		setTotalSumOfSquares_step4();
		setTotalSumOfSquares_step5();
		divide_by_degrees_of_freedom_step6();
		fScore_determineIt_step7();
		getCriticalNumber_stepFinal();
		
		log(" The end ");
	}
	
	private static void getCriticalNumber_stepFinal() {
		int d = anova.getDenomenator();
		int n = anova.getNumenator();
		String type = anova.getType();
	
		boolean isOk = d == 12 && n == 2 && type.equals(Constants.P_FIVE_PERCENT);
		//log( isOk );
		
		double criticalNumber = anova.getCriticalNumber();
		double f_score = anova.F_score; 
		
		//log( isOk );
		
		//isOk = f_score > criticalNumber then Reject null hypothesis
		// log( isOk );
		
		isOk = f_score == 22.592592592592595 && criticalNumber == 3.8853;
		
		log( isOk );
	}
	
	private static void fScore_determineIt_step7() {
		
		// In the test the F score could be written as:
		// F( 2, 12 ) = 22.5,p<.05 
		// # 2 is degrees freedom numerator and 12 is degrees freedom denomiator 
		
		anova.fScore_determineIt_step7();
		
		double expected_F = 22.592592592592595;
		
		boolean isOk = expected_F == anova.F_score;
		log( isOk ); 
	}
	
	private static void divide_by_degrees_of_freedom_step6() { 
		anova.divide_by_degrees_of_freedom_step6();
		
		double expected_SSB = 101.66666666666669;
		double expected_SSW = 4.5;
		
		boolean isOk = expected_SSB == anova.SSB && expected_SSW == anova.SSW;
		
		log( isOk );
	}
	
	private static void setTotalSumOfSquares_step5() {
		anova.setTotalSumOfSquares_step5(); 
		
		double expected_SSB = 203.33333333333337; // between groups sum of squares
		boolean isOk = expected_SSB == anova.SSB_sum_of_squares_between_groups;
		log( isOk );
	}
	
    private static void log( boolean result ){
        StackTraceElement[] ste = new Throwable().getStackTrace();

        String line = " ln: " + ste[1].getLineNumber();
        String clazz = " c: " + ste[1].getClassName();
        String method = " m: " + ste[1].getMethodName();
        
        String msg = result ? "PASS" : "FAIL";
        String out = msg + " |\t" + line + clazz + method;

        log( out );
    }
    
	private static void log( String s ) { 
    	System.out.println( s );	
    }
    
	private static void setTotalSumOfSquares_step4() {
		anova.setTotalSumOfSquares_step4();

		double expected = 257.33333333333337;
		double actual = anova.SS_total_sum_of_squares;
		boolean isOk = expected == actual; 
		
		log( isOk );	
	}
	
	private static void setSumOfSquaresOfGroups_step3() { 

		double[] expected = new double[] { 22, 18, 14 };
		boolean isOk = true; 
		anova.setSumOfSquaresOfGroups_step3();
		for ( int i = 0 ; i < anova.groups.size(); i++ ) { 
			if ( expected[i] != anova.groups.get( i ).raisedSum ) {
				isOk = false; 
			}	
		}
		if ( anova.SSW_sum_of_squares_within_groups != 54 ) {
			isOk = false;
		}
		log( isOk ); 
	}
	
	private static void findWithinGroupMeans_step2() { 
		boolean isOk = true; 
		anova.findWithinGroupMeans_step2();
		
		double[]  expected = new double[] { 4.0,8.0,13.0 }; 
		for ( int i = 0 ; i < anova.groups.size(); i++ ) {
			if ( expected[i] != anova.groups.get( i ).mean ) { 
				isOk = false;
			}
		}
		
		if ( anova.allObservationsMean != 8.333333333333334 ) {
			isOk = false;
		}
		
		log( isOk );
	}
	
	private static void populate_step1() { 
		
		double[][] observations = new double[][] {
				{2,3,7,2,6},
				{10,8,7,5,10},
				{10,13,14,13,15},
		};
		anova.populate_step1(observations, Constants.P_FIVE_PERCENT);

		int expected = 3;
		int actual = anova.groups.size();
		
		boolean isOk = expected == actual; 
		
		log( isOk );
	}
	
}
