//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class BSTreeRunner
{
   public static void main( String args[] )
   {
 		//add test cases here

        BinarySearchTree bst = new BinarySearchTree();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(70);
        bst.add(85);
        bst.add(98);
        bst.add(120);

        // testing inOrder
        System.out.println("IN ORDER");
        bst.inOrder();

        // testing preOrder
        System.out.println("PRE ORDER");
        bst.preOrder();

        // testing postOrder
        System.out.println("POST ORDER");
        bst.postOrder();

        // testing revOrder
        System.out.println("REVERSE ORDER");
        bst.revOrder();

        // testing levelOrder
        System.out.println("LEVEL ORDER");
        bst.levelOrder();

        // testing zigzag/spiral order
        System.out.println("SPIRAL ORDER");
        bst.zigzagOrder();

        // testing height
        System.out.println("Tree height is: " + bst.getHeight());

        // testing width
        System.out.println("Tree width is: " + bst.getWidth());

        // testing number of leaves
        System.out.println("The number of leaves is: " + bst.getNumLeaves());

        // testing the number of nodes
        System.out.println("The number of nodes is: " + bst.getNumNodes());

        // testing the number of levels
        System.out.println("The number of levels is: " + bst.getNumLevels());

        // testing the toString
        System.out.println("The toString: " + bst);

        // testing if the tree is full
        System.out.println(bst.isFull() ? "The tree is full." : "The tree is not full.");

        // testing if the tree contains 100 then 114
        System.out.println(bst.contains(100) ? "The tree contains 100!" : "The tree doesn't contain 100!");
        System.out.println(bst.contains(114) ? "The tree contains 100!" : "The tree doesn't contain 114!");

        // testing getSmallest and getLargest
        System.out.println("The smallest value is: " + bst.getSmallest());
        System.out.println("The largest value is: " + bst.getLargest());

        // first printing tree on level and then testing removing nodes
        System.out.println("\nTree before removing any nodes - using level order traversal");
        bst.levelOrder();
        System.out.println("Tree after removing 90:");
        bst.remove(90);
        bst.levelOrder();
        System.out.println("Tree after removing 70:");
        bst.remove(70);
        bst.levelOrder();
        System.out.println("Tree after removing 85:");
        bst.remove(85);
        bst.levelOrder();
        System.out.println("Tree after removing 98:");
        bst.remove(98);
        bst.levelOrder();
        System.out.println("Tree after removing 98:");
        bst.remove(98);
        bst.levelOrder();
        System.out.println("Tree after removing 80:");
        bst.remove(80);
        bst.levelOrder();
        System.out.println("Tree after removing 120:");
        bst.remove(120);
        bst.levelOrder();
        System.out.println("Tree after removing 100:");
        bst.remove(100);
        bst.levelOrder();

        // test case two
        out.println("\n\n**********NOW CHECKING TEST CASE TWO**********\n");

        bst.clear();
        for (Integer item : new Integer[]{50, 75, 100, 25, 5, 1, 22, 15, 13, 17, 26, 40, 30, 43, 45}) {
            bst.add(item);
        }

        // testing inOrder
        System.out.println("IN ORDER");
        bst.inOrder();

        // testing preOrder
        System.out.println("PRE ORDER");
        bst.preOrder();

        // testing postOrder
        System.out.println("POST ORDER");
        bst.postOrder();

        // testing revOrder
        System.out.println("REVERSE ORDER");
        bst.revOrder();

        // testing levelOrder
        System.out.println("LEVEL ORDER");
        bst.levelOrder();

        // testing zigzag/spiral order
        System.out.println("SPIRAL ORDER");
        bst.zigzagOrder();

        // testing height
        System.out.println("Tree height is: " + bst.getHeight());

        // testing width
        System.out.println("Tree width is: " + bst.getWidth());

        // testing number of leaves
        System.out.println("The number of leaves is: " + bst.getNumLeaves());

        // testing the number of nodes
        System.out.println("The number of nodes is: " + bst.getNumNodes());

        // testing the number of levels
        System.out.println("The number of levels is: " + bst.getNumLevels());

        // testing the toString
        System.out.println("The toString: " + bst);

        // testing if the tree is full
        System.out.println(bst.isFull() ? "The tree is full." : "The tree is not full.");

        // testing if the tree contains 100 then 114
        System.out.println(bst.contains(100) ? "The tree contains 100!" : "The tree doesn't contain 100!");
        System.out.println(bst.contains(114) ? "The tree contains 100!" : "The tree doesn't contain 114!");

        // testing getSmallest and getLargest
        System.out.println("The smallest value is: " + bst.getSmallest());
        System.out.println("The largest value is: " + bst.getLargest());

        // first printing tree on level and then testing removing nodes
        System.out.println("\nTree before removing any nodes - using level order traversal");
        bst.levelOrder();
        System.out.println("Tree after removing 50:");
        bst.remove(50);
        bst.levelOrder();
        System.out.println("Tree after removing 75:");
        bst.remove(75);
        bst.levelOrder();
        System.out.println("Tree after removing 85:");
        bst.remove(85);
        bst.levelOrder();
        System.out.println("Tree after removing 25:");
        bst.remove(25);
        bst.levelOrder();
        System.out.println("Tree after removing 5:");
        bst.remove(5);
        bst.levelOrder();
        System.out.println("Tree after removing 100:");
        bst.remove(100);
        bst.levelOrder();
        System.out.println("Tree after removing 26:");
        bst.remove(26);
        bst.levelOrder();
        System.out.println("Tree after removing 30:");
        bst.remove(30);
        bst.levelOrder();

        // now to print
        bst.clear();
        System.out.println("Testing print function for case 1:");
        for (Integer item : new Integer[]{90, 100, 80, 70, 85, 98, 120}) {
            bst.add(item);
        }
        bst.print();

        bst.clear();
        System.out.println("Testing print function");
        for (Integer item : new Integer[]{50, 75, 90, 25, 12, 18, 10, 40, 30, 45, 65, 70, 60, 95, 80}) {
            bst.add(item);
        }
        bst.print();

        bst.clear();
        System.out.println("Testing print function");
        for (Integer item : new Integer[]{9, 1, 8, 2, 7, 6}) {
            bst.add(item);
        }
        bst.print();

        bst.clear();
        System.out.println("Testing print function");
        for (String item : new String[]{"Z", "h", "P", "R", "S", "Q", "G", "L", "C", "z", "r"}) {
            bst.add(item);
        }
        bst.print();

        bst.clear();
        System.out.println("Testing print function");
        for (String item : new String[]{"Z", "p", "N", "S", "U", "Q", "G", "L", "C", "w", "r",
            "y", "z", "x", "4", "D", "I", "M", "O", "R", "T", "W",
            "t", "q", "f", "d", "j", "l", "i", "e", "c"}) {
            bst.add(item);
        }
        bst.print();

   }
}