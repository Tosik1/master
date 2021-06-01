package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree
{
    private String text;
    private ArrayList<Node> nodes;
    private Node root;
    private int number = 0;

    public SuffixTree(String text)
    {
        this.text = text;
        nodes = new ArrayList<>();
    }

    private void build()
    {
        String[] arr = text.split(" ");

        root = new Node("");
        root.addNextNodes(++number);
        addTree(arr, root);
    }

    private void addTree(String[] arr, Node node){

        for (int i = 0; i < arr.length; i++){
            //если у узла нет детей
            if (node.getNextNodes() == null){
                node.addNextNodes(i);
                Node newNode = new Node(arr[i]);
                newNode.setPosition(i);
                nodes.add(newNode);
            }
            else {
                for (int a : node.getNextNodes()){
                    int childLength = arr[a].length();
                    int wordLength = arr[i].length();
                    //находим самое короткое слово
                    if (wordLength <= childLength){
                        //ищем максимально длинные одинаковые префиксы
                        for (int lengthPref = wordLength; lengthPref > 0; lengthPref--){
                            //если совпадение найдено
                            if (arr[i].substring(0, lengthPref).equals(arr[a].substring(0, lengthPref))){
                                //перебираем список нодов на нахождение нода у которого есть такой же префикс
                                for (Node node1 : nodes){
                                    if (node1.getPosition() == a){
                                        addTree(arr[i].substring(lengthPref), node1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private List<Integer> search(String query)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        //TODO
        return positions;
    }
}