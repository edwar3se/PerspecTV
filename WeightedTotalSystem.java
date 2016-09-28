import java.util.HashMap;
import java.util.Map;

public class WeightedTotalSystem implements ScoringSystem
{
    
    //difficulties are weights
    private java.util.Map<java.lang.String, java.lang.Double> difficulties;
    
    public WeightedTotalSystem()
    {
        difficulties = null;
    }
    
    public WeightedTotalSystem(java.util.Map<java.lang.String, java.lang.Double> weights)
    {
        difficulties = new HashMap<String, Double>();
        difficulties.putAll(weights);
    }
    
    public Score calculate(String key, java.util.List<Score> scores) throws SizeException
    {
        
        if(scores == null || scores.isEmpty())
        {
            throw new SizeException();
        }
        
        double totalScore = 0;
        double tempValue = 0;
        double tempDifficulty = 0;
        double tempScore = 0;
        String tempString = "";
        
        for(int place = 0; place < scores.size(); place++)
        {
            //sets temp string
            tempString = scores.get(place).getKey();

            //gets the temp difficulty based on the tempScore and if it is missing, set it to 1.0
            
            tempValue = Missing.doubleValue(scores.get(place).getValue());
            tempScore = tempValue;
            tempDifficulty = Missing.doubleValue(difficulties.get(tempString), 1.0, 1.0);

            //adds the number to the total score
            totalScore += tempDifficulty * tempScore;
        }
        
        
     //   System.out.println("total score: " + key + " " + totalScore);
        
        Score weighted = new Score(key, totalScore);
        return weighted;
        
    }
}
