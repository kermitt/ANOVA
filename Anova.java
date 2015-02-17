package stats;

import java.util.ArrayList;
import java.util.List;

public class Anova {
	
	public double SSB;
	public double SSW;
	public double F_score;

	public double SSW_sum_of_squares_within_groups; 
	public double SSB_sum_of_squares_between_groups; 
	public double SS_total_sum_of_squares;
	public double allObservationsMean;
	public List < Group > groups = new ArrayList < Group >(); 
	
	private int numenator_degrees_of_freedom = -1;
	private int denomenator_degrees_of_freedom = -1;
	private String type = "this will be a constant to chose 1 or 5 %"; 
	
	public int getNumenator() {
		// return groups.size() -1...
		// for use with the Distribution table
		return numenator_degrees_of_freedom;
	}
	
	public int getDenomenator() {
		// return number of all observations - groups.size()... 
		// for use with the Distribution table
		return denomenator_degrees_of_freedom;
	}
	
	public String getType() { 
		// return whether this is a 1% or a 5% test
		return type;
	}
	
	public double getCriticalNumber() {
		DistributionTable table = new DistributionTable();
		double critical = table.getCriticalNumber( getDenomenator(),getNumenator(), getType());
		
		return critical;
	}
	
	public double fScore_determineIt_step7() {
		F_score = SSB / SSW;
		
		return F_score;
	}
	
	public void divide_by_degrees_of_freedom_step6() { 
		
		numenator_degrees_of_freedom = groups.size() - 1; 
		
		SSB = SSB_sum_of_squares_between_groups / numenator_degrees_of_freedom;
		int observations = 0;
		for ( Group g : groups ) {
			observations += g.ary.length;
		}
		
		//degrees_of_freedom = observations - groups.size(); 
		denomenator_degrees_of_freedom =  observations - groups.size();
		
		SSW = SSW_sum_of_squares_within_groups / denomenator_degrees_of_freedom;
	}
		
	public void populate_step1( double[][] matrix, String type  ) {	
		
		this.type = type;
				
		for ( int i = 0 ; i < matrix.length; i++ ) { 
			groups.add( new Group( matrix[i]));
		}
	}
	
	public void findWithinGroupMeans_step2() {
		double total = 0;
		int observationsCount = 0;
		for ( int i = 0 ; i < groups.size(); i++ ) { 
			Group g = groups.get(i);
			double groupTotal = 0;
			for ( int j = 0; j < g.ary.length; j++ ) {
				groupTotal += g.ary[j];
			}
			total += groupTotal;
			observationsCount += g.ary.length; 
			g.mean = groupTotal / g.ary.length;
		}
		allObservationsMean = total / observationsCount;
	}

	public void setSumOfSquaresOfGroups_step3() {
	
		for ( int i = 0 ; i < groups.size(); i++ ) { 
			Group g = groups.get(i);
			for ( int j = 0; j < g.ary.length; j++ ) {
				double observation = g.ary[j];
				double result = observation - g.mean; 
				double answer = Math.pow( result, 2);
				g.raisedSum += answer; 
			}
			SSW_sum_of_squares_within_groups += g.raisedSum;
		}
	}
	
	public void setTotalSumOfSquares_step4() {
		SS_total_sum_of_squares = 0;
		for ( int i = 0 ; i < groups.size(); i++ ) { 
			Group g = groups.get(i);
			for ( int j = 0; j < g.ary.length; j++ ) {
				double observation = g.ary[j];
				double result = observation - allObservationsMean; 
				
				SS_total_sum_of_squares += Math.pow( result, 2);
			}
		}	
	}
	
	public void setTotalSumOfSquares_step5() {
		SSB_sum_of_squares_between_groups = SS_total_sum_of_squares - SSW_sum_of_squares_within_groups;
	}
}
