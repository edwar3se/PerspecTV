import java.text.DecimalFormat;

public class Score implements Comparable
{
    private final String key;
    private final Double value;
    
    
    public Score(String key) throws IllegalArgumentException
    {
        if(key == null || key == "") 
        {
            throw new IllegalArgumentException("invalid data entry");
        }
        this.key = key;
        this.value = 0.0;
    }
    
    public Score(String key, double value) throws IllegalArgumentException
    {
        if(key == null || key == "") 
        {
            throw new IllegalArgumentException("invalid data entry");
        }
        this.key = key;
        this.value = value;
        //this.value = Missing.doubleValue(value);
    }
    
    public Score(String key, Double value) throws IllegalArgumentException
    {
        if(key == null || key == "") 
        {
            throw new IllegalArgumentException("invalid data entry");
        }
        this.key = key;
        
        //this.value = Missing.doubleValue(value);
        this.value = value;
    }
    
    public String getKey()
    {
        return key;
    }
    
    public Double getValue()
    {
        return value;
    }
    
    public String toString()
    {
        String temp;
        
        //if(value == null)
        if(value == null)
        {
            String newString = "NA";
            temp = key + ": " + String.format("%5s", newString);
            return temp;
        }

        temp = key + ": " + String.format("%5.1f", value);
        
        return temp;
    }

    public int compareTo(Score other)
    {
        return this.value.compareTo(other.value);
    }
    
}

