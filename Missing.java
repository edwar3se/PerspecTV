public class Missing
{
    private static double DEFAULT_MISSING_VALUE = 0.0;
    
    public static double doubleValue(Double number)
    {
        if(number == null)
        {
            return DEFAULT_MISSING_VALUE;
        }
        
        return number;
        
    }
    
    public static double doubleValue(Double number, double missingValue)
    {
        if(number == null)
        {
            return missingValue;
        }
        
        return number;
    }
    
    public static double doubleValue(Double number, double missingValue, double lowerBound)
    {
        double returnValue;
        if(number == null || number < lowerBound)
        {
            return missingValue;
        }
        
        returnValue = number;
        return returnValue;
    }
}
