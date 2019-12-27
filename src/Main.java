import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        MarkovChain<Character> testChain1 = new MarkovChain<>(2);

        try
        {
            ArrayList<ArrayList<Character>> data = FileToData.CharsToData(new File("assets/droneNames.txt"));
            //System.out.println(data);
            for (ArrayList<Character> line : data)
            {
                testChain1.processData(line);
            }
            System.out.println(testChain1);
            ArrayList<Character> results = testChain1.generate(6);
            System.out.println(testChain1.makeResultsMoreReadable(results));
            results = testChain1.generate(6);
            System.out.println(testChain1.makeResultsMoreReadable(results));
            results = testChain1.generate(6);
            System.out.println(testChain1.makeResultsMoreReadable(results));
            results = testChain1.generate(6);
            System.out.println(testChain1.makeResultsMoreReadable(results));
            results = testChain1.generate(6);
            System.out.println(testChain1.makeResultsMoreReadable(results));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
