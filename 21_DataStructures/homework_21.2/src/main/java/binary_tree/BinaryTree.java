package binary_tree;

public class BinaryTree {
    private Node root;

    public void addNode(String data) {
        Node nodeNew = new Node(data);
        if (root == null){
            root = nodeNew;
            root.setLeft(null);
            root.setRight(null);
            root.setParent(null);
        }
        else {
            compare(nodeNew, root);
        }
    }

    //Сравниваем значения 2х элементов
    public void compare(Node nodeNew, Node nodeOld){
        int intDataNew = Integer.parseInt(nodeNew.getData().replaceAll("[^0-9.]", ""));
        int intDataOld = Integer.parseInt(nodeOld.getData().replaceAll("[^0-9.]", ""));

        //Выбираем в какую ветку вставлять
        if (intDataNew > intDataOld){
            //проверяем занятость ветки
            if (nodeOld.getRight() == null) {
                nodeOld.setRight(nodeNew);
                nodeNew.setParent(nodeOld);
                nodeNew.setRight(null);
                nodeNew.setLeft(null);
            }
            else {
                compare(nodeNew, nodeOld.getRight());
            }
            nodeNew.setParent(nodeOld);
        }
        else if (intDataNew < intDataOld){
            if (nodeOld.getLeft() == null) {
                nodeOld.setLeft(nodeNew);
                nodeNew.setParent(nodeOld);
                nodeNew.setLeft(null);
                nodeNew.setLeft(null);
            }
            else {
                compare(nodeNew, nodeOld.getLeft());
            }
            nodeNew.setParent(nodeOld);
        }
    }

    public boolean isContains(String data) {
        if (root != null){
            Node nodeNew = new Node(data);
            if (equals(nodeNew, root)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Node nodeNew, Node nodeOld){

        if (nodeNew.getData().equals(nodeOld.getData())){
            return true;
        }
        else {
            int intDataNew = Integer.parseInt(nodeNew.getData().replaceAll("[^0-9.]", ""));
            int intDataOld = Integer.parseInt(nodeOld.getData().replaceAll("[^0-9.]", ""));

            if (intDataNew > intDataOld){
                if (nodeOld.getRight() != null){
                    if (equals(nodeNew, nodeOld.getRight())) {
                        return true;
                    }
                }
                else return false;
            }
            else {
                if (nodeOld.getLeft() != null){
                    if (equals(nodeNew, nodeOld.getLeft())) {
                        return true;
                    }
                }
                else return false;
            }
            return false;
        }
    }

    public Node getRoot() {
        return root;
    }
}
