package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private String fragment;
    private ArrayList<Node> nextNodes;
    private int position;

    public Node(String fragment)
    {
        this.fragment = fragment;
        nextNodes = new ArrayList<>();
    }

    public void insert(String string, int position){
        char c = string.charAt(0);
        Node child = findChar(c);
        if (child == null){
            child = new Node(String.valueOf(c));
            nextNodes.add(child);
            child.setPosition(position);
        }
        child.insert(string.substring(1), position);
    }

    public boolean searchStr(String query){
        for (Node node : nextNodes){
            if (node.getFragment().equals(query.substring(0, node.getFragment().length()))){
                if (query.substring(node.getFragment().length()).length() != 0){
                    node.searchStr(query.substring(node.getFragment().length()));
                }
                return true;
            }
        }
        return false;
    }

    private Node findChar(char c){
        if (nextNodes != null){
            for (Node node : nextNodes){
                if (node.getFragment().equals(String.valueOf(c))){
                    return node;
                }
            }
        }
        return null;
    }

    public String getFragment()
    {
        return fragment;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Node> getNextNodes()
    {
        return nextNodes;
    }

    public void setNextNodes(ArrayList<Node> nextNodes){
        this.nextNodes = nextNodes;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}