import java.util.Comparator;

public class FrequencyComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        int freqA = a.freq;
        int freqB = b.freq;
        return freqA-freqB;
    }

}
