package capstone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.capstone.serviceClasses.GradeCalculationService;

/**
 * This file is testing whether the percentage() method in your GradeCalculationService class correctly calculates a 
 * percentage.
 */
public class PercentageTest{

	GradeCalculationService scoreTenCountTenVar = new GradeCalculationService();
	GradeCalculationService scoreSevenCountTenVar = new GradeCalculationService();
		
		// This checks whether a score of 10 out of 10 produces a percentage of 100%. 
	 @Test
	    public void testPercentage1() {
	        double percentage1 = scoreTenCountTenVar.percentage(10, 10);
	        assertEquals(100.0, percentage1,	0.1 );        
	    }
	 
		// This checks whether a score of 7 out of 10 produces a percentage of 70%.
	 @Test
	    public void testPercentage2() {
	        double percentage2 = scoreSevenCountTenVar.percentage(10, 7);
	        assertEquals(70.0, percentage2, 0.1 );	        
	    }
}
