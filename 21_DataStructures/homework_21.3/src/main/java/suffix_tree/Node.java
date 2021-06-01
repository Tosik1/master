package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private String fragment;
    private ArrayList<Integer> nextNodes;
    private int position;

    public Node(String fragment)
    {
        this.fragment = fragment;
        nextNodes = new ArrayList<>();
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

    public List<Integer> getNextNodes()
    {
        return nextNodes;
    }

    public void addNextNodes(int numberNode) {
        nextNodes.add(numberNode);
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}