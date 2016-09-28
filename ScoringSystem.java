
public interface ScoringSystem
{
    Score calculate(String key, java.util.List<Score> scores) throws SizeException;
}