package BinaryTree.TreeConstruction;

public class treeConstructionInorderAndPreorder {
    // Inorder and Preorder

    static class Node{
        char data;
        Node left;
        Node right;

        Node(char data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

//    private static void inorderTraversal(Node root){
//        Node temp = root;
//        if(temp==null){
//            return;
//        }
//        inorderTraversal(temp.left);
//        System.out.print(temp.data+" ");
//        inorderTraversal(temp.right);
//    }

    private static void preOrderTraversal(Node root){
        Node temp = root;
        if(temp==null){
            return;
        }
        System.out.print(temp.data+" ");
        preOrderTraversal(temp.left);
        preOrderTraversal(temp.right);
    }

    private static int search(char key, char[] inorder, int inStart, int inEnd){
        for(int i=inStart;i<=inEnd;i++){
            if(key == inorder[i]){
                return i;
            }
        }
        return -1;
    }
    static int preStart = 0;
    private static Node buildtree(char[] inorder, char[] preorder, int inStart, int inEnd){
        if(inStart>inEnd){
            return null;
        }

        Node root = new Node(preorder[preStart]);
        int inorderIdx = search(preorder[preStart], inorder,inStart,inEnd);
        preStart++;
        if(inStart==inEnd){
            return root;
        }

        root.left = buildtree(inorder, preorder, inStart, inorderIdx-1);
        root.right = buildtree(inorder, preorder, inorderIdx+1, inEnd);
        return root;

    }

    public static void main(String[] args) {
        char inorder[] = {'D', 'B', 'E', 'A', 'F', 'C'};
        char preorder[] = {'A', 'B', 'D', 'E', 'C','F'};

        Node root = buildtree(inorder, preorder, 0, inorder.length-1);

        preOrderTraversal(root);
    }
}
