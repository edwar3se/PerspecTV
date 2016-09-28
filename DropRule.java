import java.util.ArrayList;
import java.util.Collections;

public class DropRule implements Rule
{
    private boolean shouldDropLowest;
    private boolean shouldDropHighest;
    
    public DropRule()
    {
        shouldDropLowest = true;
        shouldDropHighest = true;
    }
    
    public DropRule(boolean shouldDropLowest, boolean shouldDropHighest)
    {
        this.shouldDropHighest = shouldDropHighest;
        this.shouldDropLowest = shouldDropLowest;
    }
    
    public java.util.List<Score> apply (java.util.List<Score> scores) throws SizeException
    {
        
        if(scores == null)
        {
            throw new SizeException();
        }
        
        if((shouldDropLowest == true || shouldDropHighest == true) && scores.size() <= 1)
        {
            throw new SizeException();
        }
        
        if(shouldDropLowest == true && shouldDropHighest == true && scores.size() <= 2)
        {
            throw new SizeException();
        }
        
        java.util.List<Score> newList = new ArrayList<Score>();
        
        //does this actually make a deep copy or nah?
        newList.addAll(scores);
        
      //  System.out.println("\n" + newList.toString());
        
        double tempValue;
        int tempIndex;
        
        if(shouldDropLowest == true)
        {
            tempValue = newList.get(0).getValue();
            tempIndex = 0;
            
            for(int place = 0; place < newList.size(); place++)
            {
                if(newList.get(place).getValue() < tempValue)
                {
                    tempValue = newList.get(place).getValue();
                    tempIndex = place;
                }
            }
            
            newList.remove(tempIndex);

        }
        
        if(shouldDropHighest == true)
        {
            tempValue = newList.get(0).getValue();
            tempIndex = 0;
            
            for(int place = 0; place < newList.size(); place++)
            {
                if(newList.get(place).getValue() > tempValue)
                {
                    tempValue = newList.get(place).getValue();
                    tempIndex = place;
                    
                }
            }
            
            newList.remove(tempIndex);
        }
        
        return newList;
    }
}
