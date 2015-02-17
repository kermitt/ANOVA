package stats;

/*
 * Numbers for the tests are from: https://www.youtube.com/watch?v=-yQb_ZJnFXw
 * How To Calculate and Understand Analysis of Variance (ANOVA) F Test.
 * User: statisticsfun
 * 
 * The distribution tables are from: http://www.socr.ucla.edu/applets.dir/f_table.html
 * 
 * This is a simple 1 way ANOVA. 
 * If the calculated Fscore is larger than the critial number then reject the null hypothesis.
 * 
 */

public class AnovaMain {

	public static void main(String...strings) { 
		
		Anova anova = new Anova(); 

/*
		double[][] observations = new double[][] {
				{2,3,7,2,6},
				{10,8,7,5,10},
				{10,13,14,13,15},
		};
*/
		double[][] observations = new double[][] {
				{2,3,7,2,6},
				{10,8,7,5,10,10,8,7,5,10},
				{2,3,7,2,6},
		};	
		
		String type = Constants.P_FIVE_PERCENT;
		
		anova.populate_step1(observations, type);
		anova.findWithinGroupMeans_step2();
		anova.setSumOfSquaresOfGroups_step3();
		anova.setTotalSumOfSquares_step4();
		anova.setTotalSumOfSquares_step5();
		anova.divide_by_degrees_of_freedom_step6();
		
		double f_score = anova.fScore_determineIt_step7();
		
		double criticalNumber = anova.getCriticalNumber();

		String result = "The null hypothesis is supported! There is no especial difference in these groups. ";
		
		if ( f_score > criticalNumber) {
			result = "The null hypothesis is rejected! These groups are different."; 
		}
		System.out.println("Groups degrees of freedom: " + anova.getNumenator());
		System.out.println("Observations degrees of freedom: " + anova.getDenomenator());
		System.out.println("SSW_sum_of_squares_within_groups: " + anova.SSW_sum_of_squares_within_groups);
		System.out.println("SSB_sum_of_squares_between_groups: " + anova.SSB_sum_of_squares_between_groups);
		System.out.println("allObservationsMean: " + anova.allObservationsMean);
		System.out.println("Critical number: " + criticalNumber);
		System.out.println("*** F Score: " + f_score );
		System.out.println( result );
	}
}
