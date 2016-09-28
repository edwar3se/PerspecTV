import java.util.*;

/**
 * An application that can be used to score the required program for one diver in a
 * sanctioned intra-departmental 1-meter springboard diving competition.
 * 
 * As everyone knows, sanctioned intra-departmental diving competitions currently have two required dives,
 * the forward dive in the pike position (101B) that has a degree of difficulty of 1.3,
 * and a forward somersault in the tuck position (102C) that has a degree of difficulty
 * of 1.4. As everyone also knows, there are five judges in all such competitions
 * (from Albania, Belgium, Canada, Djibouti, and El Salvador) and the lowest and highest scores
 * for each dive are dropped before the raw score is calculated. Then, the raw score 
 * for each dive is multiplied by its degree of difficulty and the two weighted scores are added
 * to get the diver's total points.
 * 
 * When running this application, the judges' 10 scores (one score from each of the five judges
 * for both dives) must be entered as command-line parameters.
 */
public class IndividualScorer 
{
    private static final String[] COUNTRY  = {"ALB","BEL","CAN","DJI","ESA"};
    private static final String[] REQUIRED = {"Forward Dive - Pike", "Forward Somersault - Tuck"};
    
    public static void main(String[] args)
    {
        // Early exit
        if ((args == null) || (args.length != 11))
        {
          System.err.println("You must enter all 10 scores (five scores for each of the two required dives) followed by the diver's name.");
          System.exit(1);
        }         
              
        // Setup the Map containing the degree of difficulty information
        Map<String, Double>      difficulty = setupDODs("1mSpringboard");

        // Print the header
        System.out.printf("Results for %s\n\n", args[10]);
        System.out.printf("%30s", " ");
        for (int judge=0; judge<5; judge++) System.out.printf(" %3s", COUNTRY[judge]);
        System.out.printf(" | %4s\n", "Raw");
        
        
        List<Score> dives = new ArrayList<Score>();
        
        for (int dive=0; dive<2; dive++)
        {
            List<Score> scores = new ArrayList<Score>();
            System.out.printf("%30s", REQUIRED[dive]);
            
            // Create a list containing the judge's scores for the current dive
            for (int judge=0; judge<5; judge++)
            {
                Score score = new Score(COUNTRY[judge], Double.parseDouble(args[dive*5+judge]));
                System.out.printf(" %3.1f", Missing.doubleValue(score.getValue()));
                scores.add(score);
            }

            // Drop the highest and lowest scores and calculate (and display) the raw total
            Rule          dropRule    = new DropRule(true, true);
            ScoringSystem totalSystem = new TotalSystem();
            try
            {
                //not actually dropping high or low score
                List<Score> middle = dropRule.apply(scores);                        // Drop the high and the low
                Score       temp   = totalSystem.calculate(REQUIRED[dive], middle); // Calculate the total of the others
                
                dives.add(temp);
                
                System.out.printf(" | %4.1f\n", temp.getValue());
            }
            catch (SizeException se)
            {
                System.out.printf("There are scores missing for dive %d\n", dive+1);
            }
        }

        // Calculate (and display) the weighted total for this diver
        ScoringSystem dodSystem = new WeightedTotalSystem(difficulty);
        try
        {
            Score total = dodSystem.calculate("Points", dives);
            System.out.printf("%30s", total);
        }
        catch (SizeException se)
        {
            System.out.println("This diver didn't complete either of the two required dives!");
        }
        
    }

    /**
     * Setup the degree of difficulty table.
     * 
     * @param competition  The name of the competition
     * @return A Map containing the degree of difficulty for each dive
     */
    private static Map<String, Double> setupDODs(String competition)
    {
        Map<String, Double>   result;
        
        result = null;
        if (competition.equals("1mSpringboard"))
        {
            result = new HashMap<String, Double>();
            result.put("Forward Dive - Straight", 1.4);
            result.put("Forward Dive - Pike", 1.3);
            result.put("Forward Dive - Tuck", 1.2);
            result.put("Forward Somersault - Straight", 1.6);
            result.put("Forward Somersault - Pike", 1.5);
            result.put("Forward Somersault - Tuck", 1.4);
            result.put("Forward 1 1/2 Somersault - Pike", 1.7);
            result.put("Forward 1 1/2 Somersault - Tuck", 1.6);
            result.put("Forward Double Somersault - Pike", 2.3);
            result.put("Forward Double Somersault - Tuck", 2.2);
            result.put("Forward 2 1/2 Somersault - Pike", 2.6);
            result.put("Forward 2 1/2 Somersault - Tuck", 2.4);
            result.put("Forward Triple Somersault - Tuck", 2.9);
            result.put("Forward 3 1/2 Somersault - Tuck", 3.0);
            // Note: This Map is not complete!
        }
        
        return result;
    }
    
}