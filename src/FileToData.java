import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileToData
{
    public static ArrayList<ArrayList<String>> StringsToData(File file) throws IOException
    {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        while (bufferedReader.ready())
        {
            String[] newLine = bufferedReader.readLine().split("\\s+");
            data.add(new ArrayList<String>(Arrays.asList(newLine)));
            //System.out.println(newLine);
        }

        return data;
    }

    public static ArrayList<ArrayList<Character>> CharsToData(File file) throws IOException
    {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<ArrayList<Character>> data = new ArrayList<>();
        while (bufferedReader.ready())
        {
            Character[] newLine = bufferedReader.readLine().chars().mapToObj(c -> (char)c).toArray(Character[]::new);
            data.add(new ArrayList<Character>(Arrays.asList(newLine)));
            //System.out.println(newLine);
        }

        return data;
    }
}
