public class TotalSystem implements ScoringSystem
{
    public TotalSystem()
    {
        
    }
    
    public Score calculate(String key, java.util.List<Score> scores) throws SizeException
    {
        
        Score tempScore;
        double tempNum;
       
        double total = 0;
        
        if(scores == null || scores.isEmpty())
        {
            throw new SizeException();
        }
        for(int place = 0; place < scores.size(); place++)
        {
            tempNum = Missing.doubleValue(scores.get(place).getValue());
            total += tempNum;
            //total += scores.get(place).getValue();
        }
            
        tempScore = new Score(key, total);  
       return tempScore;
        
    }
}
