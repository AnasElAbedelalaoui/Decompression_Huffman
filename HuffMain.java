import java.io.*;
import java.util.*;

public class HuffMain {
    public static PriorityQueue<Node> l;
    public static HashMap<Character, String> chaintocode;
    public static HashMap<String, Character> codetochain;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException {
        // Read all the contents of the file
        String text = new Scanner(new File("alice.txt")).useDelimiter("\\A").next(); 
        // Count the frequency of all the characters
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        for(int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            if(dict.containsKey(a))
                dict.put(a, dict.get(a)+1);
            else
                dict.put(a, 1);
        }

        // Create a forest (group of trees) by adding all the nodes to a priority queue
        l = new PriorityQueue<Node>(100, new FrequencyComparator());
        int n = 0;
        for(Character c : dict.keySet()) {
            l.add(new Node(c, dict.get(c)));
            n++;
        }
        Node root = huffmain(n);
        buildTable(root);

        String compressed = compress(text);
        saveToFile(compressed);

        String decompressed = decompress(compressed);
        writeToFile(decompressed);
    }

    // This method builds the tree based on the frequency of every characters 
    public static Node huffmain(int n) {
        Node x, y;
        for(int i = 1; i <= n-1; i++) {
            Node z = new Node();
            z.gauche = x = l.poll();
            z.droite = y = l.poll();
            z.freq = x.freq + y.freq;
            l.add(z);
        }
        return l.poll();
    }

    // This method builds the table for the compression and decompression
    public static void buildTable(Node root) {
        chaintocode = new HashMap<Character, String>();
        codetochain = new HashMap<String, Character>();
        postorder(root, new String());
    }

    // This method is used to traverse from ROOT-to-LEAVES
    public static void postorder(Node n, String s) {
        if(n == null)
            return;
        postorder(n.gauche, s+"0");
        postorder(n.droite, s+"1");

        // Visit only nodes with keys
        if (!Character.toString(n.alpha).equals("&#092;&#048;")){
            System.out.println("{" + n.alpha + ":" + s + "}");
            chaintocode.put(n.alpha, s);
            codetochain.put(s, n.alpha);
        }
    }

    //assuming tree and dictionary are already built...
    public static String compress(String s) {
        String c = new String();
        for(int i = 0; i < s.length(); i++)
            c = c + chaintocode.get(s.charAt(i));
        return c;
    }

    //assuming tree and dictionary are already built...
    public static String decompress(String s) {
        String temp = new String();
        String result = new String();
        for(int i = 0; i < s.length(); i++) {
            temp = temp + s.charAt(i);
            Character c = codetochain.get(temp);
            if(c!=null && c!=0) {
                result = result + c;
                temp = "";
            }
        }
        return result;
    }
    public static void saveToFile(String l) throws FileNotFoundException {
        PrintWriter fichiercompresse = new PrintWriter("fichiercompressee.txt");
        fichiercompresse.print(chaintocode.size());
        for(char s : chaintocode.keySet())
            fichiercompresse.println("{" +s + ":" + chaintocode.get(s)+ "}");
        fichiercompresse.println(l);
        fichiercompresse.close();
    }

    public static void writeToFile(String i) throws FileNotFoundException {
        PrintWriter fichierdecompresse = new PrintWriter("fichierdecompressee.txt");
        fichierdecompresse.print(codetochain.size());
        for (String s : codetochain.keySet())
            fichierdecompresse.println("{" +s + ":" + codetochain.get(s)+ "}");
        fichierdecompresse.println(i);
        fichierdecompresse.close();
    }
}