package BinaryTree.InsertDeleteAndTraversals;

import java.util.LinkedList;
import java.util.Queue;

public class binaryTree {
    static Node root;

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
//        BinaryTree.InsertDeleteAndTraversals.binaryTree tree = new BinaryTree.InsertDeleteAndTraversals.binaryTree();

        insert(10);
        insert(20);
        insert(30);
        insert(40);
        insert(50);
        insert(60);

        System.out.println("Level Order Traversal");
        levelOrderTraversal(root);
        System.out.println();
        System.out.println("In Order:");
        inOrderTraversal(root);
        System.out.println();
        System.out.println("Post Order:");
        postOrderTraversal(root);
        System.out.println();
        System.out.println("Pre Order:");
        preOrderTraversal(root);
        System.out.println();

        delete(10, root);
        System.out.println("After deleting 10: ");
        System.out.println("Level Order Traversal");
        levelOrderTraversal(root);



    }

    private static void inOrderTraversal(Node root){
        Node temp = root;
        if(temp!=null){
            inOrderTraversal(temp.left);
            System.out.print(temp.data+" ");
            inOrderTraversal(temp.right);
        }
    }

    private static void postOrderTraversal(Node root){
        Node temp = root;
        if(temp!=null){
            postOrderTraversal(temp.left);
            postOrderTraversal(temp.right);
            System.out.print(temp.data+" ");
        }
    }

    private static void preOrderTraversal(Node root){
        Node temp = root;
        if(temp!=null){
            System.out.print(temp.data+" ");
            preOrderTraversal(temp.left);
            preOrderTraversal(temp.right);
        }
    }

    private static void levelOrderTraversal(Node root){
        if(root== null)
        {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        Node temp = root;
        q.add(temp);
        q.add(null);

        while(!q.isEmpty()){
            temp = q.poll();
            if(temp == null) {
                System.out.println();
            }
            else
                System.out.print(temp.data+" ");

            if(temp!=null && temp.left!= null) {
                q.add(temp.left);
            }

            if(temp!= null && temp.right!=null){
                q.add(temp.right);
            }

            if(!q.isEmpty() && temp==null)
                q.add(null);

        }
    }

    private static void insert(int data){
        Node node = new Node(data);

        if(root==null) {
            root = node;
        }
        else
        {
            Queue<Node> q = new LinkedList<>();
            Node temp = root;
            q.add(temp);

            while(!q.isEmpty()){
                temp = q.poll();

                if(temp.left!=null){
                    q.add(temp.left);
                }
                else{
                    temp.left = node;
                    break;
                }

                if(temp.right!=null){
                    q.add(temp.right);
                }
                else{
                    temp.right = node;
                    break;
                }
            }

        }
    }

    private static void delete(int key, Node root){
        //Empty tree
        if(root==null){
            System.out.println("Tree is Empty. Deletion is impossible.");
            return;
        }
        //tree with one node
        if(root.left==null && root.right==null){
            if(root.data==key){
                root = null;
            }
            return;
        }

        Queue<Node> q = new LinkedList<>();
        Node temp = root;
        q.add(temp);
        Node deleteNode = null;

        while(!q.isEmpty()) {
            temp = q.poll();

            if(temp.data==key){
                deleteNode = temp;
            }

            if(temp.left != null){
                q.add(temp.left);
            }

            if(temp.right != null){
                q.add(temp.right);
            }
        }
        if(deleteNode!=null){
            deleteNode.data = temp.data;
            deleteDeepestRightMostNode(temp,root);
        }
    }

    private static void deleteDeepestRightMostNode(Node deleteNode, Node root){
        Queue<Node> q = new LinkedList<>();
        Node temp = root;
        q.add(temp);

        while(!q.isEmpty()){
            temp = q.poll();

            if(temp.right!=null){
                if(temp.right==deleteNode){
                    temp.right = null;
                    return;
                }
                else{
                    q.add(temp.right);
                }
            }
            if(temp.left!=null){
                if(temp.left == deleteNode){
                    temp.left = null;
                    return;
                }
                else{
                    q.add(temp.left);
                }
            }
        }
    }
}