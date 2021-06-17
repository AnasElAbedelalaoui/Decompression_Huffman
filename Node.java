
public class Node {
    public char alpha;
    public int freq;
    public Node gauche, droite;

    public Node(char a, int f) {
        alpha = a;
        freq = f;
    }  
    public Node() {
    } 
    public String toString() {
        return alpha + " " + freq;
    }    
}   