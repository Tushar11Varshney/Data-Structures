 package Done;
import java.util.*;
public class bst {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int size(TreeNode node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    //leetcode 104
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;  //on basis of nodes.
        
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }

    public static int sum(Node node) {
        if(node==null)
        return 0;
        
        return sum(node.left)+sum(node.right)+node.data;
    }

    //leetcode 111
    //The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
    public int minDepth(TreeNode root) {
        if(root==null)
            return 0;  //on basis of no of nodes.
        
        int leftHeight=minDepth(root.left);
        int rightHeight=minDepth(root.right);
        if(leftHeight!=0 && rightHeight!=0)
            return Math.min(leftHeight,rightHeight)+1;
        else if(rightHeight!=0)
            return rightHeight+1;
        else
            return leftHeight+1;
    }

    public int maximumElement(TreeNode node)
    {
        while(node.right!=null)
            node=node.right;
        return node.val;
    }

    public int maximumElement_(TreeNode node)
    {
        if(node.right!=null)
        return maximumElement_(node.right);
        else return node.val;  //else lgana optional
    }

    public int minimumElement(TreeNode node)
    {
        while(node.left!=null)
            node=node.left;
        return node.val;
    }

    public int minimumElement_(TreeNode node)
    {
        if(node.left!=null)
        return minimumElement_(node.left);
        else return node.val;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null)
            return null;
        if(root.val==val)
            return root;
        else if(val<root.val)
             return searchBST(root.left,val);
        else return searchBST(root.right,val);
    }

    public TreeNode searchBST_(TreeNode root, int val) { 
        while(root!=null)
        {
            if(root.val==val)
                return root;
            else if(root.val>val)
                root=root.left;
            else
            root=root.right;
        }
        return null;
    } 

    //leetcode 701
    public TreeNode insertIntoBST(TreeNode root, int val) {   //adding in btw will be a costly operation so we add on leaf.decide for equal node whether they will be on left or right or we can take a hashmap and have a frequency.
        if(root==null)
            return new TreeNode(val);
        
        if(val<=root.val)
        root.left=insertIntoBST(root.left,val);
        else
        root.right=insertIntoBST(root.right,val);
        
        return root;
    }

    public TreeNode insertIntoBST_(TreeNode root, int val) {
        TreeNode curr=root;        
        TreeNode prev=null;        
        TreeNode newNode=new TreeNode(val);
        while(curr!=null)
        {
            prev=curr;
            if(val<=curr.val)
            curr=curr.left;
            else
            curr=curr.right;
        }
        if(prev==null)
        root=newNode;
        else if(prev.val<val)
        prev.right=newNode;
        else prev.left=newNode;

        return root;
    }

    //leetcode 450
    public TreeNode deleteNode(TreeNode root, int key) { 
        if(root==null)return null;    //1st case
        else if(key<root.val)
            root.left=deleteNode(root.left,key);
        else if(key>root.val)
            root.right=deleteNode(root.right,key);
        else
        {
            if(root.left==null||root.right==null)          //2nd 3rd 4th case
                return root.left!=null?root.left:root.right;
            else
                {
                    int max=maximumElement(root.left);    //5th case
                    root.val=max;
                    root.left=deleteNode(root.left, max);  //cant give root because baar then again again ussi node pr pahuchega fir....here can be catched in right also..and yahan return ni likhskte
                }    
        }
        return root;
    }

    //leetcode 235
    //yahan constraint mein given hai ki dono p & q exist krenge if given that both may not exist then find that if present or not if using normal find then O(n) if skew else log(n)+log(n) for both element.
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        if(root->val>p->val && root->val>q->val)
            return lowestCommonAncestor(root->left,p,q);
        else if(root->val<p->val && root->val<q->val)
            return lowestCommonAncestor(root->right,p,q);
        else return root;
    }
 
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {  
        TreeNode curr=root;
        while(curr!=null)
        {
            if(p.val<curr.val && q.val<curr.val)    //= waale leetcode pr consider nhi kree..all value unique
            curr=curr.left;
            else if(p.val>curr.val && q.val>curr.val)
            curr=curr.right;
            else break;
        }
        return curr;
    }

    //leetcode 108
    public TreeNode sortedArrayToBSTHelper(int[] nums,int li,int ri)
    {
        if(li>ri)return null;
        int mid=(li+ri)/2;
        TreeNode midNode=new TreeNode(nums[mid]);
        midNode.left=sortedArrayToBSTHelper(nums,li,mid-1);
        midNode.right=sortedArrayToBSTHelper(nums,mid+1,ri);
        return midNode;
    }
    
    /*public TreeNode sortedArrayToBSTHelper(int[] nums,int li,int ri)
    {
        if(li>ri)return null;
        int mid=(li+ri)/2;
        TreeNode left=sortedArrayToBSTHelper(nums,li,mid-1);
        TreeNode right=sortedArrayToBSTHelper(nums,mid+1,ri);
        
        TreeNode newNode=new TreeNode(nums[mid],left,right);
        return newNode;
    }*/

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums,0,nums.length-1);
    }

    //https://www.pepcoding.com/resources/online-java-foundation/binary-search-tree/replace-with-sum-of-larger-official/ojquestion
    static int sum = 0;
    public static void rwsol(Node node) {   //replace with sum of larger
        if(node==null)return;
        rwsol(node.right);
        
        int originalValue=node.val;
        node.val=sum;
        sum+=originalValue;
        
        rwsol(node.left);
    }

    // https://www.pepcoding.com/resources/online-java-foundation/binary-search-tree/pir-bst-official/ojquestion
    public void printInRange(TreeNode node, int d1, int d2) {   //my soln
        if(node==null)return;
        printInRange(node.left,d1,d2);
        if(node.val>=d1 && node.val<=d2)
        // System.out.println(node.data);
        sum+=node.val;
        printInRange(node.right,d1,d2);
    }

    //leetcode 938
    int sum1=0;   //static bnane se dusre test case kelie bhi vhi purani value ke saath add hota hai.
    public void pir_(TreeNode node, int d1, int d2) {   
        if(node==null)return;
        if(node.val>d1 && node.val>d2)       //= lgane se printing galat
        pir_(node.left,d1,d2);
        else if(node.val<d1 && node.val<d2)
        pir_(node.right,d1,d2);
        else{
            pir_(node.left,d1,d2);
            // System.out.println(node.val);
            sum1+=node.val;
            pir_(node.right,d1,d2);
        }
    }

    public int pir(TreeNode root, int low, int high) {
        pir_(root,low,high);
        return sum1;
    } 

    public static void travelandPrint(Node root,Node node,int targetSum)
    {
        if(node==null)
        return;
        travelandPrint(root,node.left,targetSum);
        
        int complement=targetSum-node.data;
        if(node.data<complement)
        {
            if(find(root,complement))
            System.out.println(node.data+" "+complement);
        }
        travelandPrint(root,node.right,targetSum);
    }

    public static void inorder(Node root,ArrayList<Integer>result)
    {
        if(root==null)return;
        inorder(root.left, result);
        result.add(root.data);
        inorder(root.right, result);
    }

    public static void targetSumPair(Node root,int target)
    {
        ArrayList<Integer>result=new ArrayList<>();
        inorder(root, result);
        int li=0,ri=result.size()-1,sum=0;
        while(li<ri)
        {
            sum=result.get(li)+result.get(ri);
            if(sum<target)
            li++;
            else if(sum>target)
            ri--;
            else{
                System.out.println(result.get(li)+" "+result.get(ri));
                li++;
                ri--;
            }
        }
    }

    static class valStatePair{
        Node node;
        int state;

        valStatePair(Node node,int state)
        {
            this.node=node;
            this.state=state;
        }
    }    

    public static Node leftInorderTraversal(Stack<valStatePair>st)
    {
        while(st.size()>0)
        {
            valStatePair p=st.peek();
            if(p.state==1)
            {
                if(p.node.left!=null)
                st.push(new valStatePair(p.node.left,1));
                p.state++;
            }
            else if(p.state==2)
            {
                if(p.node.right!=null)
                st.push(new valStatePair(p.node.right,1));
                p.state++;
                return p.node;
            }
            else{
                st.pop();
            }
        }

        return null;
    }

    public static Node rightInorderTraversal(Stack<valStatePair>st)
    {
        while(st.size()>0)
        {
            valStatePair p=st.peek();
            if(p.state==1)
            {
                if(p.node.right!=null)
                st.push(new valStatePair(p.node.right,1));
                p.state++;
            }
            else if(p.state==2)
            {
                if(p.node.left!=null)
                st.push(new valStatePair(p.node.left,1));
                p.state++;
                return p.node;
            }
            else{
                st.pop();
            }
        }

        return null;
    }

    public static void targetSumPair2(Node root,int target)
    {
        Stack<valStatePair>Inorder=new Stack<>();
        Stack<valStatePair>ReverseInorder=new Stack<>();

        Inorder.push(new valStatePair(root,1));
        ReverseInorder.push(new valStatePair(root,1));

        Node li=leftInorderTraversal(Inorder);
        Node ri=rightInorderTraversal(ReverseInorder);

        while(li.data<ri.data)  
        {
            if(li.data+ri.data==target)
            {
                System.out.println(li.data+" "+ri.data);
                li=leftInorderTraversal(Inorder);
                ri=rightInorderTraversal(ReverseInorder);
            }
            else if(li.data+ri.data<target)
                li=leftInorderTraversal(Inorder);
            else
                ri=rightInorderTraversal(ReverseInorder);
        }
    }

    // 653-rajneesh sir
    public void insertAllLeft(LinkedList<TreeNode>st,TreeNode node){
        while(node!=null)
        {
            st.addFirst(node);
            node=node.left;
        }
    }

    public void insertAllRight(LinkedList<TreeNode> st, TreeNode node) {
        while (node != null) {
            st.addFirst(node);
            node = node.right;
        }
    }

    public boolean findTarget(TreeNode root, int k) {

        LinkedList<TreeNode> st1 = new LinkedList<>();
        LinkedList<TreeNode> st2 = new LinkedList<>();
    
        insertAllLeft(st1, root);
        insertAllRight(st2, root);
    
        int sum = 0;
        while (st1.getFirst().val < st2.getFirst().val) {
            sum = st1.getFirst().val + st2.getFirst().val;
            if (sum == k)
                return true;
            else if (sum > k) {
                TreeNode np = st2.removeFirst();
                insertAllRight(st2, np.left);
            } else {
                TreeNode np = st1.removeFirst();
                insertAllLeft(st1, np.right);
            }
        }
        return false;
    }


// leetcode 98
public class BstPair {
    boolean isBst = true;
    long max = -(long) 1e13;
    long min = (long) 1e13;

    BstPair() {
    }
}

public BstPair isValidBST_(TreeNode root) {
    if (root == null)
        return new BstPair();

    BstPair leftTree = isValidBST_(root.left);
    BstPair rightTree = isValidBST_(root.right);

    BstPair res = new BstPair();
    res.isBst = leftTree.isBst && rightTree.isBst && leftTree.max < root.val && root.val < rightTree.min;
    // leftTree.max < root.val && root.val < rightTree.min node ki trh bst hone kelie
    // leftTree.isBst && rightTree.isBst + node ki trh bst hona pdega tab tree ki trh bst hoga
    if (!res.isBst)
        return res;
    res.max = Math.max(root.val, rightTree.max);
    res.min = Math.min(root.val, leftTree.min);
    return res;
}

long prevNode = -(long) 1e13;
public boolean isValidBST2(TreeNode root) {

    if (root == null)
        return true;
    if (!isValidBST2(root.left))
        return false;

    if (prevNode >= root.val)
        return false;
    prevNode = root.val;

    if (!isValidBST2(root.right))
        return false;

    return true;
}

public boolean isValidBST(TreeNode root) {
    if (root == null)
        return false;
    // return isValidBST_(root).isBst;
    return isValidBST2(root);
}

public static class BstPair {
    boolean isBst = true;
    long max = -(long) 1e13;
    long min = (long) 1e13;

    Node root = null;
    int size = 0;

    BstPair() {
    }
}

public static BstPair largestBst(Node root) {
    if (root == null)
        return new BstPair();

    BstPair leftTree = largestBst(root.left);
    BstPair rightTree = largestBst(root.right);

    BstPair res = new BstPair();
    res.isBst = leftTree.isBst && rightTree.isBst && leftTree.max < root.data && root.data < rightTree.min;
    res.max = Math.max(root.data, max(leftTree.max,rightTree.max));
    res.min = Math.min(root.data, min(leftTree.min,rightTree.min));

    if (res.isBst) {
        res.root = root;
        res.size = leftTree.size + rightTree.size + 1;
    } else if (leftTree.size > rightTree.size) {
        res.root = leftTree.root;
        res.size = leftTree.size;
    } else {
        res.root = rightTree.root;
        res.size = rightTree.size;
    }
    return res;
}

// 96 & catalan number
public int numTrees(int n) {  //bst
    int totalWays = 0;
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        for (int j = 0; j <i; j++) {
            dp[i] += dp[j] * dp[i - j - 1];
        }
    }
    return dp[n];
}

// 95
public List<TreeNode> generateTrees(int n) {
    return generateTrees(1, n);
}

public List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> list = new ArrayList<>();
    if (start > end) {
        list.add(null);
        return list;
    }

    for (int i = start; i <= end; i++) {
        List<TreeNode> leftTreeList = generateTrees(start, i - 1);
        List<TreeNode> rightTreeList = generateTrees(i + 1, end);

        for (TreeNode l : leftTreeList) {
            for (TreeNode r : rightTreeList) {
                TreeNode root=new TreeNode(i,l,r);

                list.add(root);
            }
        }
    }
    return list;
}

// 894
public List<TreeNode> allPossibleFBT_(int si,int ei)
{
    List<TreeNode>res=new ArrayList<>();
    if(si==ei)
    {
        res.add(new TreeNode(0));
        return res;
    }
    
    for(int i=si+1;i<ei;i+=2)
    {
        List<TreeNode>leftSubTree=allPossibleFBT_(si,i-1);
        List<TreeNode>rightSubTree=allPossibleFBT_(i+1,ei);
        for(TreeNode l:leftSubTree)
        {
            for(TreeNode r:rightSubTree)
            {
                TreeNode root=new TreeNode(0);
                root.left=l;
                root.right=r;
                res.add(root);
            }
        }
    }
    return res;
}

public List<TreeNode> allPossibleFBT(int N) {
    if(N == 0 || N % 2 == 0) return new ArrayList<>();
    return allPossibleFBT_(0,N-1);
}
 
//109
public TreeNode sortedListToBST(ListNode head) {
    if(head==null)return null;
    if(head.next==null)return new TreeNode(head.val);
    
    ListNode slow=head,fast=head,mid=null;
    while(fast!=null && fast.next!=null)
    {
        mid=slow;
        slow=slow.next;
        fast=fast.next.next;
    }
    
    mid.next=null;
    TreeNode root=new TreeNode(slow.val);
    root.left=sortedListToBST(head);
    root.right=sortedListToBST(slow.next);
    
    return root;
}

