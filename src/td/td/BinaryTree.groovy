package td.td

/**
 * Created with IntelliJ IDEA.
 * User: mikher
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */

class Node {

    Node left, right
    int data

    Node(data){
        this.data = data
        left = null
        right = null
    }
}

class BTree {

    static def Node insert(Node node, val) {
        if (val < node.data) {
            if (node.left == null) {
                println "inserting $val to the left of $node.data"
                return node.left = new Node(val)
            }

            else insert(node.left, val)
        }

        else if (val > node.data) {
            if (node.right == null) {
                node.right = new Node(val)
                println "inserting $val to the right of $node.data"
                return node.left = new Node(val)
            }

            else insert(node.right, val)
        }

    }

    //left, root, right
    static def printInOrder(Node node) {
        if (node == null) return

        else {
            printInOrder(node.left)
            println node.data
            printInOrder(node.right)
        }
    }

}

class InvertBinary{

    def static invert(int input){

        // println(Integer.toBinaryString(input));

        BitSet bitSet = new BitSet();
        bitSet.set()

        //bitSet.e

    }

}

class Runner {
    //Shift to main if you aren't running this as a groovy script
    public static void main(String[] args) {
        def root = new Node(25)
        BTree.insert(root, 10)
        def node30 = BTree.insert(root, 30)
        BTree.insert(root, 24)
        BTree.insert(root, 299)
        BTree.insert(root, 266)
        BTree.insert(root, 121)
        BTree.insert(root, 920)

        //Inorder traversal of the entered elements
        println("In order from root")
        BTree.printInOrder(root)

        println("node30=" + node30 + "'")
        println("In order from node30")
        BTree.printInOrder(node30)

    }
}


