package BinaryTree.TreeConstruction;

public class PreorderAndPostorder {

    static int preorderIndex;


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

    private static void postorderTraversal(Node root){
        if(root!=null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data+" ");
        }
    }

    private static void preorderTraversal(Node root){
        if(root!=null){
            System.out.print(root.data+" ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    private static int search(int postorder[], int key){
        for(int i=0;i<postorder.length;i++){
            if(postorder[i]==key)
                return i;
        }
        return -1;
    }

    private static Node buildTree(int preorder[], int postorder[], int postorderStart, int postorderEnd){
//        if(postorderStart>postorderEnd)
//            return null;

        Node root = new Node(preorder[preorderIndex]);
        preorderIndex++;

        if(postorderStart==postorderEnd){
            return root;
        }

        if(preorderIndex>preorder.length)
            return null;

        int postorderIndex = search(postorder, preorder[preorderIndex]);

        root.left = buildTree(preorder, postorder, postorderStart, postorderIndex);
        root.right = buildTree(preorder, postorder, postorderIndex+1, postorderEnd-1);
        return root;
    }

    public static void main(String[] args) {
        int preorder[] = {1, 2, 4, 8, 9, 5, 3, 6, 7};
        int postorder[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};

        preorderIndex = 0;

        Node root = buildTree(preorder, postorder, 0, postorder.length-1);

        postorderTraversal(root);
        System.out.println();
        preorderTraversal(root);
    }
}
