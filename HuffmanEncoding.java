import java.util.*;

class Node {

    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node left, Node right) {
        this.ch = '\0'; // internal node
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class HuffmanEncoding {

    // Generate Huffman codes
    static void getCodes(Node root, String code, Map<Character, String> map) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            map.put(root.ch, code);
            return;
        }

        getCodes(root.left, code + "0", map);
        getCodes(root.right, code + "1", map);
    }

    // Build Huffman tree and return codes
    static Map<Character, String> huffman(char[] chars, int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (int i = 0; i < chars.length; i++) pq.add(
            new Node(chars[i], freq[i])
        );

        while (pq.size() > 1) {
            Node a = pq.poll(),
                b = pq.poll();
            pq.add(new Node(a.freq + b.freq, a, b));
        }

        Node root = pq.poll();
        Map<Character, String> map = new HashMap<>();
        getCodes(root, "", map);
        return map;
    }

    public static void main(String[] args) {
        char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] freq = { 5, 9, 12, 13, 16, 45 };

        long s = System.nanoTime();
        Map<Character, String> codes = huffman(chars, freq);

        System.out.println("Huffman Codes:");
        codes.forEach((k, v) -> System.out.println(k + ": " + v));

        long e = System.nanoTime();
        System.out.println("Execution Time: " + (e - s) + " ns");
        System.out.println("Execution Time: " + ((e - s) / 1e6) + " ms");
    }
}
