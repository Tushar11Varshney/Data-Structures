package Done;
import java.util.*;

public class constructBT {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer arr[]) {
        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0], null, null);
        st.push(new Pair(root, 1));

        int index = 0;
        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == 1) {
                index++;
                if (arr[index] != null) { 
                    p.node.left = new Node(arr[index], null, null);
                    st.push(new Pair(p.node.left, 1));
                } else p.node.left = null;
                p.state++;
            } else if (p.state == 2) {
                index++;
                if (arr[index] != null) {
                    p.node.right = new Node(arr[index], null, null);
                    st.push(new Pair(p.node.right, 1));
                } else p.node.right = null;
                p.state++;
            } else { 
                st.pop();
            }
        }
        return root;
    }
    
    public static void display(Node node) {
        if (node == null)
            return;
        StringBuilder sb = new StringBuilder();
        sb.append(node.left == null ? "." : node.left.data);
        sb.append(" <- " + node.data + " <- ");
        sb.append(node.right == null ? "." : node.right.data);
        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static void traversal(Node node) {     //euler path
        if (node == null)
            return;
        System.out.println(node.data + " in preorder");
        traversal(node.left);
        System.out.println(node.data + " in inorder");
        traversal(node.right);
        System.out.println(node.data + " in postorder");
    }

    public static void main(String args[]) {
        Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };
        Node root = construct(arr);
        // display(root);
        traversal(root);
    }

    //https://www.pepcoding.com/resources/online-java-foundation/binary-tree/is-balanced-binary-tree-official/ojquestion
    public boolean updateBalandHeight(TreeNode node) {
        int leftHeight = -1;
        int rightHeight = -1;

        if (node.left != null)
            leftHeight = height(node.left);
        if (node.right != null)
            rightHeight = height(node.right);

        int bal = leftHeight - rightHeight;
        if (bal >= -1 && bal <= 1)
            return true;
        else return false;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        // boolean left=isBalanced(root.left);
        // boolean right=isBalanced(root.right);

        // if(left==true && right==true)
        // return updateBalandHeight(root);
        // else
        // return false;
        return isBalanced(root.left) && isBalanced(root.right) && updateBalandHeight(root);
    }

    //https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1#
    public static void fillMap(Tree curr,HashMap<Tree,Tree>map)
    {
        if(curr==null)return;
        
        Tree newNode=new Tree(curr.data);
        map.put(curr,newNode);
        fillMap(curr.left,map);
        fillMap(curr.right,map);
    }
    
    public static void connectClonedNodes(Tree curr,HashMap<Tree,Tree>map)
    {
        if(curr==null)return;
        
        Tree copyNode=map.get(curr);
        copyNode.left=curr.left!=null?map.get(curr.left):null;
        copyNode.right=curr.right!=null?map.get(curr.right):null;
        copyNode.random=curr.random!=null?map.get(curr.random):null;
        
        connectClonedNodes(curr.left,map);
        connectClonedNodes(curr.right,map);
    }
    
    public static Tree cloneTree(Tree tree){
       HashMap<Tree,Tree>map=new HashMap<>();
       Tree curr=tree;
       fillMap(curr,map);
        
       curr=tree;
       connectClonedNodes(curr,map);
       return map.get(tree);
    }

    // 654
    public TreeNode constructMBT_Helper(int []nums,int si,int ei)
    {
            if(si>ei)return null;
            if(si==ei)return new TreeNode(nums[si]);
            int maxIndex=si;
            for(int i=si+1;i<=ei;i++)
            {
                if(nums[i]>nums[maxIndex])
                    maxIndex=i;
            }
        
            TreeNode root=new TreeNode(nums[maxIndex],null,null);
            root.left=constructMBT_Helper(nums,si,maxIndex-1);
            root.right=constructMBT_Helper(nums,maxIndex+1,ei);
                
            return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n=nums.length;
        TreeNode root=constructMBT_Helper(nums,0,n-1);
        return root;
    }

     //951
     private void addToMap(TreeNode root,Map<Integer,Integer>map)
     {
         if(root==null)return;
         if(root.left==null && root.right==null)return;
         
         if(root.left!=null)
         map.put(root.left.val,root.val);
         if(root.right!=null)
         map.put(root.right.val,root.val);
         
         addToMap(root.left,map);
         addToMap(root.right,map);
     }
     
     public boolean flipEquiv(TreeNode root1, TreeNode root2) {
         if(root1==null && root2==null)return true;
         else if(root1==null || root2==null)return false;
         Map<Integer,Integer>map1=new HashMap<>();
         addToMap(root1,map1);
         
         Map<Integer,Integer>map2=new HashMap<>();
         addToMap(root2,map2);
     
         if(map1.size()!=map2.size())return false;
         for(Integer i:map1.keySet())
         {
             int parent=map1.get(i);
             if(parent!=map2.get(i))return false;
         }
         
         return true;
     }
 
     public boolean flipEquiv(TreeNode root1, TreeNode root2) {
         
         if(root1==null && root2==null)return true;
         else if(root1==null || root2==null)return false;
         else if(root1.val!=root2.val)return false;
         else return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
     }
  
    //958
    public boolean isCompleteTree_(TreeNode root,int i,int n) {
        if(root==null)return true;
        else if(i>=n)return false;
        return isCompleteTree_(root.left,2*i+1,n) && isCompleteTree_(root.right,2*i+2,n);
    }
    
    private int size(TreeNode root)
    {
        if(root==null)return 0;
        return 1+size(root.left)+size(root.right);
    } 
    
    public boolean isCompleteTree(TreeNode root) {
        return isCompleteTree_(root,0,size(root));
    }

    //222
    public int countNodes(TreeNode root) {
        if(root==null)return 0;
        
        int lh=leftHeight(root.left);
        int rh=rightHeight(root.right);
        
        if(lh==rh)
            return (1<<lh)-1;
        
        return 1+countNodes(root.left)+countNodes(root.right);
    }
    
    int leftHeight(TreeNode root)
    {
        int lh=1;
        while(root!=null)
        {
            lh++;
            root=root.left;
        }
        return lh;
    }
    
    int rightHeight(TreeNode root)
    {
        int rh=1;
        while(root!=null)
        {
            rh++;
            root=root.right;
        }
        return rh;
    }

}
