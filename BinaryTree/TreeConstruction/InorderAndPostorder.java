package BinaryTree.TreeConstruction;

public class InorderAndPostorder {
    static int postorderIndex;
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

    private static void inorderTraversal(Node root){
        if(root!=null){
            inorderTraversal(root.left);
            System.out.print(root.data+" ");
            inorderTraversal(root.right);
        }
    }

    private static void postorderTraversal(Node root){
        if(root!=null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data+" ");
        }
    }

    private static int search(int inorder[], int key){
        for(int i = 0;i<inorder.length; i++){
            if(inorder[i]==key)
                return i;
        }
        return -1;
    }

    private static Node buildTree(int inorder[], int postorder[], int inorderStart, int inorderEnd){
        if(inorderStart>inorderEnd)
            return null;

        if(postorderIndex<0)
            return null;

        Node root = new Node(postorder[postorderIndex]);
        int inorderIndex = search(inorder,postorder[postorderIndex]);
        postorderIndex--;

        if(inorderStart==inorderEnd)
            return root;

        root.right = buildTree(inorder, postorder, inorderIndex+1, inorderEnd);
        root.left = buildTree(inorder, postorder, inorderStart, inorderIndex-1);

        return root;
    }

    public static void main(String[] args) {
        int inorder[] = {4, 8, 2, 5, 1, 6, 3, 7};
        int postorder[] = {8, 4, 5, 2, 6, 7, 3, 1};
        postorderIndex = postorder.length-1;

        Node root = buildTree(inorder, postorder, 0, inorder.length-1);
        inorderTraversal(root);
        System.out.println();
        postorderTraversal(root);
    }
}
