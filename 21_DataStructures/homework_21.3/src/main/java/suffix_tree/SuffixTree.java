package suffix_tree;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuffixTree
{
    private String text;
    private ArrayList<Node> nodes;
    private Node root;
    private int position = 0;

    public SuffixTree(String text)
    {
        this.text = text;
        nodes = new ArrayList<>();
    }

    private void build()
    {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(text.split(" ")));
        root = new Node(" ");

        for (String word : words){
            root.insert(word, position);
            position += word.length() + 1;
        }
        squeezeNodes(root);
    }

    private void squeezeNodes(Node root){
        if (root.getNextNodes().size() == 1){
            for (Node child : root.getNextNodes()){
                root.setFragment(root.getFragment() + child.getFragment());
                root.setNextNodes(child.getNextNodes());
            }
            squeezeNodes(root);
        } else if (root.getNextNodes().size() > 1){
            for (Node node : root.getNextNodes()){
                squeezeNodes(node);
            }
        }
    }

    private void search(String query)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        root.searchStr(query);
    }
}