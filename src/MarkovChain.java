import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MarkovChain<T>
{
    private final int order;
    //private ArrayList<ChainLink> chains;
    private Map<ArrayList<T>, ArrayList<T>> chains;//prefix suffix
    private final Random randGen;

    public MarkovChain(int order, int seed)
    {
        this.order = order;
        //chains = new ArrayList<>();
        chains = new HashMap<>();
        randGen = new Random(seed);
    }

    public MarkovChain(int order)
    {
        this.order = order;
        //chains = new ArrayList<>();
        chains = new HashMap<>();
        randGen = new Random();
    }

    public void processData(ArrayList<T> data)
    {
        for (int i = 0; data.size() > i; i++)
        {
            T suffix = data.get(i);
            ArrayList<T> newPrefix = new ArrayList<T>();
            for (int j = i - order; i > j; j++)
            {
                if (j < 0)
                {
                    newPrefix.add(null);
                    continue;
                }
                newPrefix.add(data.get(j));
            }
            newChainLink(newPrefix, suffix);
        }
        /*
        for (int i = prefixCount; data.size() > i; i++)
        {
            T suffix = data.get(i);
            ArrayList<T> newPrefix = new ArrayList<T>();
            for (int j = i - prefixCount; i > j; j++)
            {
                newPrefix.add(data.get(j));
            }
            //System.out.println(newPrefix + " " + suffix);
            newChainLink(newPrefix, suffix);
        }
         */
    }

    public ArrayList<T> generate(int length)
    {
        if (chains.size() <= 0)
            return new ArrayList<T>();

        ArrayList<T> output = new ArrayList<>();

        //Chooses a random starting prefix from the chain
        //ArrayList<T> prefix = (ArrayList<T>) chains.keySet().toArray()[randGen.nextInt(chains.size())];
        //output.addAll(prefix);

        //Chooses an actual starting point based on the data
        ArrayList<T> prefix = new ArrayList<>(order);
        for (int i = 0; i < order; i++)
        {
            prefix.add(null);
        }
        output.addAll(prefix);

        while (true)
        {
            T suffix = getRandomSuffix(prefix);

            if (output.size() > length || suffix == null)
            {
                break;
            }

            output.add(suffix);

            prefix = new ArrayList<T>();
            for (int j = output.size() - order; output.size() > j; j++)
            {
                prefix.add(output.get(j));
            }
        }

        return output;
    }

    //--------------------Helpers--------------------

    private void newChainLink(ArrayList<T> prefix, T suffix)
    {
        if (chains.containsKey(prefix))
        {
            addSuffix(prefix, suffix);
        } else
        {
            ArrayList<T> suffixs = new ArrayList<>();
            suffixs.add(suffix);
            chains.put(prefix, suffixs);
        }
    }

    private void addSuffix(ArrayList<T> prefix, T suffix)
    {
        chains.get(prefix).add(suffix);
    }

    private T getRandomSuffix(ArrayList<T> prefix)
    {
        if (!chains.containsKey(prefix))
        {
            return null;
        }

        ArrayList<T> suffixs = chains.get(prefix);
        T suffix = suffixs.get(randGen.nextInt(suffixs.size()));
        return suffix;
    }

    //--------------------Output--------------------

    public String makeResultsMoreReadable(ArrayList<T> results)
    {
        String output = "";
        for (T piece : results)
        {
            if (piece != null)
            {
                output += piece.toString() + " ";
            }
        }

        return output;
    }

    @Override
    public String toString()
    {
        return "MarkovChain{" + "prefixCount=" + order + ", chains=" + chains + '}';
    }
}
