import java.util.*;

public class tree {

class Node
{
    Node left, right;
    int data;
    
    Node(int d)
    {
        data = d;
        left = right = null;
    } 
}

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public void display(ArrayList<TreeNode> list) {
    for (TreeNode node : list)
        System.out.print(node.val + " ");
    System.out.println();
}

// leetcode 110
// in term of nodes:node==null? 0
// in term of edges:node==null?-1
public int height(TreeNode node) {
    return node == null ? -1 : (Math.max(height(node.left), height(node.right))) + 1;
}

public int size(TreeNode node) {
    return node == null ? 0 : size(node.left) + size(node.right) + 1;
}

public int maximum(TreeNode node) {
    if (node == null)
        return -(int) 1e9;

    return Math.max(Math.max(maximum(node.left), maximum(node.right)), node.val);
}

public boolean find(TreeNode node, int data) {
    if (node == null)
        return false;

    if (node.val == data)
        return true;

    return find(node.left, data) || find(node.right, data);
}

// void type recursion-yahan void type isliye bolrhe hain kyunki hume chahiye arrayList pr hum vo return ni krwarhe
public boolean NodeToRootPath(TreeNode node, int data, ArrayList<TreeNode> res) {
    if (node == null)
        return false;

    if (node.val == data) {
        res.add(node);
        return true;
    }

    boolean ans = NodeToRootPath(node.left, data, res) || NodeToRootPath(node.right, data, res);
    if (ans)
        res.add(node);

    return ans;
}

public ArrayList<TreeNode> NodeToRootPath2(TreeNode node, TreeNode data) // agar nodes ki value same ho tou answer same aa skte hain..tou can take then address instead of integer data...because address tou unique hai..
{
    if (node == null)
        return new ArrayList<>();

    if (node == data) {
        ArrayList<TreeNode> base = new ArrayList<>();
        base.add(node);
        return base;
    }

    ArrayList<TreeNode> left = NodeToRootPath2(node.left, data);
    if (left.size() > 0) {
        left.add(node);
        return left;
    }
    ArrayList<TreeNode> right = NodeToRootPath2(node.right, data);
    if (right.size() > 0) {
        right.add(node);
        return right;
    }
    return new ArrayList<>();
}

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    // ArrayList<TreeNode>list1=new ArrayList<>();
    // ArrayList<TreeNode>list2=new ArrayList<>();

    // NodeToRootPath(root, p.val, list1); //left skew tree ya right skew tree mein,NodeToRootpath mein space time o(n)
    // NodeToRootPath(root, q.val, list2);
    // display(list1);display(list2);
    ArrayList<TreeNode> list1 = NodeToRootPath2(root, p);
    ArrayList<TreeNode> list2 = NodeToRootPath2(root, q);
    int i = list1.size() - 1;
    int j = list2.size() - 1;

    TreeNode ans = null;
    while (i >= 0 && j >= 0) {
        if (list1.get(i) != list2.get(j))
            break;

        ans = list1.get(i);
        i--;
        j--;
    }
    return ans;
}

public int rootToNodeDistance(TreeNode node, TreeNode data) {
    if (node == null)
        return -1;

    if (node.val == data.val)
        return 0;

    int lans = rootToNodeDistance(node.left, data);
    if (lans != -1) {
        return lans + 1;        
    }
    int rans = rootToNodeDistance(node.right, data);
    if (rans != -1) {
        return rans + 1;        
    }

    return -1;
}

public void printDown(TreeNode node,int depth,TreeNode block,Arraylist<TreeNode>res){ 
    if (node == null || depth < 0 || node == block)
        return;

    if (depth == 0)
        res.add(node.val);

    printDown(node.left, depth - 1, block, res);
    printDown(node.right, depth - 1, block, res);
}

public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

    List<TreeNode> map = new ArrayList<>();
    map = NodeToRootPath2(root, target); // save this space in optimization

    TreeNode prev = null;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < map.size(); i++) {
        printDown(map.get(i), K - i, prev, res);
        prev = map.get(i);
    }
    return res;
}

// optimized Solution
public int distanceK2Optimization(TreeNode node, TreeNode target, int K, List<Integer> res) {
    if (node == null)
        return -1;

    if (node == target) {
        printDown(node, K, null, res);
        return 1;
    }

    int lans = distanceK2Optimization(node.left, target, K, res);
    if (lans != -1) {
        printDown(node, K - lans, node.left, res);
        return lans + 1;
    }

    int rans = distanceK2Optimization(node.right, target, K, res);
    if (rans != -1) {
        printDown(node, K - rans, node.right, res);
        return rans + 1;
    }

    return -1;
}

public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) { 
    List<Integer> res = new ArrayList<>();
    distanceK2Optimization(root, target, K, res);
    return res;
}

// https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
public void BurningTree(TreeNode root, int target) {
    List<TreeNode> path = new ArrayList<>();   //my
    path = NodeToRootPath2(root, target);

    int h = height(root);
    for (int d = 0; d <= h; d++) {
        TreeNode prev=null;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            if(d-i>=0)
            {
                printDown(path.get(i), d - i, prev, res);
                prev = path.get(i);
            }
            else break;
        }
        for (int x : res)
            System.out.print(x + " ");
        System.out.println();
    }
}

public void kdown(TreeNode blockNode,int depth,TreeNode root,List<List<Integer>>result)
{
    if(root==blockNode || root==null)return;

    if(depth==result.size())
    result.add(new ArrayList<>());

    result.get(depth).add(root.val);
    kdown(blockNode, depth+1, root.left, result);
    kdown(blockNode, depth+1, root.right, result);
}

public int burningTree2(TreeNode root,int target,List<List<Integer>>result)
{
    if(root==null)return -1;
    if(root.val==target)
    {
        kdown(null,0,root,result);
        return 1;
    }

    int ldepth=burningTree2(root.left, target,result);
    if(ldepth!=-1)
    {   
        kdown(root.left,ldepth,root,result);
        return ldepth+1;
    }

    int rdepth=burningTree2(root.right, target,result);
    if(rdepth!=-1)
    {
        kdown(root.right,rdepth,root,result);
        return rdepth+1;
    }

    return -1;
}

// leetcode 144
public void preorderTraversal(TreeNode root, List<Integer> result) {
    if (root == null)
        return;
    result.add(root.val);
    preorderTraversal(root.left, result);
    preorderTraversal(root.right, result);
}

public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    preorderTraversal(root, result);
    return result;
}

// leetcode 145
public void postorderTraversal(TreeNode root, List<Integer> result) {
    if (root == null)
        return;
    postorderTraversal(root.left, result);
    postorderTraversal(root.right, result);
    result.add(root.val);
}

public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    postorderTraversal(root, result);
    return result;
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/iterative-pre-post-in-binary-tree-official/ojquestion
public static void iterativePrePostInTraversal(Node node) {
    Stack<Pair> st = new Stack<>();
    Pair root = new Pair(node, 1);
    st.push(root);
    String pre = "";
    String in = "";
    String post = "";
    while (st.size() > 0) {
        Pair p = st.peek();
        if (p.state == 1) {
            pre += p.node.data + " ";
            p.state++;

            if (p.node.left != null) {
                st.push(new Pair(p.node.left, 1));
            }
        } else if (p.state == 2) {
            in += p.node.data + " ";
            p.state++;

            if (p.node.right != null) {
                st.push(new Pair(p.node.right, 1));
            }
        } else {
            post += p.node.data + " ";
            st.pop();
        }
    }
    System.out.println(pre);
    System.out.println(in);
    System.out.println(post);
}

// leetcode 144
static class Pair_ {
    TreeNode node;
    int state;

    Pair_(TreeNode node, int state) {
        this.node = node;
        this.state = state;
    }
}

public List<Integer> preorderTraversalIterative(TreeNode root) {
    List<Integer> preorder = new ArrayList<>();
    if (root == null)
        return preorder;
    Stack<Pair_> st = new Stack<>();
    Pair_ rootNode = new Pair_(root, 1);
    st.push(rootNode);
    while (st.size() > 0) {
        Pair_ p = st.peek();
        if (p.state == 1) {
            preorder.add(p.node.val);
            p.state++;

            if (p.node.left != null) 
                st.push(new Pair_(p.node.left, 1));
        } else if (p.state == 2) {
            p.state++;
            if (p.node.right != null)
                st.push(new Pair_(p.node.right, 1));
        } else st.pop();
    }
    return preorder;
}

// leetcode 543
public int diameterOfBinaryTree_01(TreeNode root) {
    if (root == null)
        return -1;
    int diameterLeftSubTree = diameterOfBinaryTree(root.left);
    int diameterRightSubTree = diameterOfBinaryTree(root.right);

    int heightLeftSubTree = height(root.left);
    int heightRightSubTree = height(root.right);

    return Math.max(Math.max(diameterLeftSubTree, diameterRightSubTree),
            heightLeftSubTree + heightRightSubTree + 2);
}

public int[] diameterOfBinaryTree_02(TreeNode root) {
    if (root == null)
        return new int[] { -1, -1 }; // {dia,height}

    int[] LeftSubTree = diameterOfBinaryTree_02(root.left);
    int[] RightSubTree = diameterOfBinaryTree_02(root.right);

    int[] res = new int[2];
    res[0] = Math.max(Math.max(LeftSubTree[0], RightSubTree[0]), LeftSubTree[1] + RightSubTree[1] + 2); 
    // We are doing LeftSubTree[1] + RightSubTree[1] + 2 this calculation always so we can do this calculation and take diameter as a refrence.
    res[1] = Math.max(LeftSubTree[1], RightSubTree[1]) + 1;
    return res;
}

int maxDia = 0;
public int diameterOfBinaryTree_03(TreeNode root) {
    if (root == null)
        return -1;

    int heightLeftSubTree = diameterOfBinaryTree_03(root.left);
    int heightRightSubTree = diameterOfBinaryTree_03(root.right);

    maxDia = Math.max(maxDia, heightLeftSubTree + heightRightSubTree + 2);

    return Math.max(heightLeftSubTree, heightRightSubTree) + 1;
}

public int diameterOfBinaryTree_04(TreeNode root, int[] arr) {
    if (root == null)
        return -1;

    int heightLeftSubTree = diameterOfBinaryTree_04(root.left, arr);
    int heightRightSubTree = diameterOfBinaryTree_04(root.right, arr);

    arr[0] = Math.max(arr[0], heightLeftSubTree + heightRightSubTree + 2);

    return Math.max(heightLeftSubTree, heightRightSubTree) + 1;
}

public int diameterOfBinaryTree(TreeNode root) {
    if (root == null)
        return 0;
    // return diameterOfBinaryTree_01(root);
    // return diameterOfBinaryTree_02(root)[0];
    // diameterOfBinaryTree_03(root);
    int arr[] = new int[1];
    diameterOfBinaryTree_04(root, arr);
    return arr[0];
    // return maxDia;
}

// 226
public void invertTree_(TreeNode root) {
    // if(root==null)
    // return null;
    if (root.left == null && root.right == null)
        return;
    invertTree(root.left);
    invertTree(root.right);
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
}

public TreeNode invertTree(TreeNode root) {
    if (root == null)
        return null;
    invertTree_(root);
    return root;
}

//100
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null || q == null)
        return p == q;

    if (p.val != q.val)
        return false;

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}

// 617
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null)
        return root2;
    if (root2 == null)
        return root1;
    root1.val += root2.val;
    root1.left = mergeTrees(root1.left, root2.left);
    root1.right = mergeTrees(root1.right, root2.right);
    return root1;
}

// 101
public boolean isSymmetric(TreeNode p1, TreeNode p2) {
    // if(p1==null && p2==null)
    // return true;

    // if(p1==null && p2!=null || p1!=null && p2==null)
    // return false;

    if (p1 == null || p2 == null)
        return p1 == p2;

    if (p1.val != p2.val)
        return false;

    return isSymmetric(p1.left,p2.right) && isSymmetric(p1.right,p2.left);
}

public boolean isSymmetric(TreeNode root) {
    TreeNode p1 = root, p2 = root;
    return isSymmetric(p1, p2);
}

//662
static class pair {
    TreeNode node;
    int number;

    pair(TreeNode node, int number) {
        this.node = node;
        this.number = number;
    }
}

public int widthOfBinaryTree(TreeNode root) {
    if (root == null)
        return 0;
    LinkedList<pair> que = new LinkedList<>();
    int maxWidth = 0;
    que.add(new pair(root, 0));

    while (que.size() != 0) {
        int size = que.size();
        pair first = que.peek();
        pair curr = null;
        while (size-- > 0) {
            curr = que.poll();
            int num = curr.number;
            if (curr.node.left != null)
                que.add(new pair(curr.node.left, 2 * num));
            if (curr.node.right != null)
                que.add(new pair(curr.node.right, 2 * num + 1));
        }
        maxWidth = Math.max(maxWidth, curr.number - first.number + 1);
    }
    return maxWidth;
}

// leetcode 173
class BSTIterator {

    Stack<TreeNode>st=new Stack<>();
    public BSTIterator(TreeNode root) {
        addAllLeft(root);
    }
    
    public void addAllLeft(TreeNode root)
    {
        while(root!=null)
        {
            st.push(root);
            root=root.left;
        }    
    }
    
    public int next() {
        TreeNode rn=st.pop();
        if(rn.right!=null)
            addAllLeft(rn.right);   
        return rn.val;
    }
    
    public boolean hasNext() {
        return st.size()!=0;    
    }
}
    
// leetcode 510 bst-2 locked
static class TreeNode2 {
    int key;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2 parent;
}

public static TreeNode2 inorderSuccessor(TreeNode2 node) {
        TreeNode2 succ = null;
        if (node.right != null) {
            succ = node.right;
            while (succ.left != null)
                succ = succ.left;
        } else {
            while (node.parent != null && node.parent.left != node)
                node = node.parent;

            succ = node.parent;
        }
        return succ;
}

//11 february-https://www.lintcode.com/problem/inorder-successor-in-bst/
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    TreeNode curr=root;
    TreeNode pred=null;
    TreeNode succ=null;
    while(curr!=null)
    {
        if(curr.val==p.val)
        {
            if(curr.left!=null)
            {
                pred=curr.left;
                while(pred.right!=null)
                    pred=pred.right;
            }

            if(curr.right!=null)
            {
                succ=curr.right;
                while(succ.left!=null)
                    succ=succ.left;
            }
            break;
        }
        else if(curr.val<p.val)
        {
            pred=curr;
            curr=curr.right;
        }
        else{
            succ=curr;
            curr=curr.left;
        }
    }
    return succ;
}

//pre succ in binary tree 
public static class allSolPair{
    TreeNode prev=null;
    TreeNode succ=null;
    TreeNode pred=null;

    int ceil=(int)1e9;
    int floor=-(int)1e9;
}

public void getPre_SuccBT(TreeNode root,int key,allSolPair pair)
{
    if(root==null)return;

    if(root.val<key)
    pair.floor=Math.max(root.val,pair.floor);

    if(root.val>key)
    pair.ceil=Math.min(root.val,pair.ceil);

    getPre_SuccBT(root.left, key, pair);  //this code above for preorder successor predecessor

    if(root.val==key && pair.pred==null)
    pair.pred=pair.prev;
    if(pair.prev!=null && pair.prev.val==key && pair.succ==null)  //pair.prev!=null for checking if the soln asked for first value of inorder.
    pair.succ=root;

    pair.prev=root;

    getPre_SuccBT(root.right, key, pair);
}

// [7,-3,12,-1,6,-9,13,0,2,4,null,8,-11,null,-15,null,null,null,null,null,-5,null,null,10,null,19]
// leetcode 99
TreeNode prev = null, a = null, b = null;
public boolean recoverTree_(TreeNode root) {
    if (root == null)
        return true;

    if (!recoverTree_(root.left))  //traversal work like loop
        return false;

    if (prev != null && prev.val > root.val) {
        b = root;
        if (a == null)
            a = prev;
        else
            return false;   //2 jgah galti milgyi ab aage iterate krne ka fayda ni return false
    }
    prev = root;

    if (!recoverTree_(root.right))  //this works like break in loop
        return false;

    return true;
}

public void recoverTree(TreeNode root) {
    if (root == null)
        return;
    recoverTree_(root);
    int temp = a.val;
    a.val = b.val;
    b.val = temp;
}

// leetcode 112
public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null)
        return false;
    if (root.left == null && root.right == null && targetSum - root.val == 0)
        return true;
    boolean res = false;
    res = hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    return res;
}

// leetcode 113
public void pathSumHelper(TreeNode root, List<List<Integer>> res, int targetSum, List<Integer> smallAns) {
    if (root == null)
        return;
    if (root.left == null && root.right == null) {
        if (targetSum - root.val == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            res.add(base);
        }
        return;
    }
    smallAns.add(root.val);
    pathSumHelper(root.left, res, targetSum - root.val, smallAns);
    pathSumHelper(root.right, res, targetSum - root.val, smallAns);
    smallAns.remove(smallAns.size() - 1);
}

public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    pathSumHelper(root, res, targetSum, new ArrayList<>());
    return res;
}

// leetcode 257
public void getAllPaths(List<String> result, TreeNode root, String str) {
    if(root==null)return;
    if(root.left==null && root.right==null)
    {
        str+=root.val;
        result.add(str);
        return;
    }
    str+=root.val+"->";
    getAllPaths(result,root.left,str);
    getAllPaths(result,root.right,str);
}

public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null)
        return result;
    getAllPaths(result, root, "");
    return result;
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/binary-tree-path-to-leaves-from-root-official/ojquestion
public static void pathSumHelper(Node root, int lo, int hi, int sum, String path, List<Integer> smallAns) {
    if (root == null)
        return;
    if (root.left == null && root.right == null) {
        sum += root.data;
        if (sum >= lo && sum <= hi) {
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.data);
            for (int i = 0; i < base.size(); i++) {
                System.out.print(base.get(i) + " ");
            }
            System.out.println();
        }
        return;
    }
    smallAns.add(root.data);
    pathSumHelper(root.left, lo, hi, sum + root.data, smallAns);
    pathSumHelper(root.right, lo, hi, sum + root.data, smallAns);
    smallAns.remove(smallAns.size() - 1);
}

// using string
public static void pathSumHelper(Node root, int lo, int hi, int sum, String path) {
    if (root == null)
        return;
    if (root.left == null && root.right == null) {
        sum += root.data;
        if (sum >= lo && sum <= hi) {
            System.out.println(path + root.data);
        }
        return;
    }
    pathSumHelper(root.left, lo, hi, sum + root.data, path + root.data + " ");
    pathSumHelper(root.right, lo, hi, sum + root.data, path + root.data + " ");
}

// 129 yc
int sum = 0;
public void sumNumbersHelper(List<Integer> path, TreeNode root) {
    if (root == null)
        return;
    if (root.left == null && root.right == null) {
        path.add(root.val);
        List<Integer> base = new ArrayList<>(path);
        int smallSum = 0;
        for (int i = 0; i < base.size(); i++) {
            smallSum = smallSum * 10 + base.get(i);
        }
        sum += smallSum;
        path.remove(path.size() - 1);
        return;
    }
    path.add(root.val);
    sumNumbersHelper(path, root.left);
    sumNumbersHelper(path, root.right);
    path.remove(path.size() - 1);
}

public int sumNumbers(TreeNode root) {
    List<Integer> path = new ArrayList<>();
    sumNumbersHelper(path, root);
    return sum;
}

// leetcode 437
public int pathSumHelper_(TreeNode root, int targetSum) {
    if (root == null)
        return 0;
    int res = 0;
    if (targetSum - root.val==0)
        res++;
    res += pathSumHelper_(root.left, targetSum - root.val);
    res += pathSumHelper_(root.right, targetSum - root.val);
    return res;
}

public int pathSum437(TreeNode root, int sum) {
    if (root == null)
        return 0;
    return pathSumHelper_(root, sum) + pathSum437(root.left, sum) + pathSum437(root.right, sum);
}

// leetcode 437
int ans = 0;
public void pathSum_(HashMap<Integer, Integer> map, TreeNode root, int prefixSum, int tar) {
    if (root == null)
        return;
    prefixSum += root.val;
    ans += map.getOrDefault(prefixSum - tar, 0);
    map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

    pathSum_(map, root.left, prefixSum, tar);
    pathSum_(map, root.right, prefixSum, tar);

    map.put(prefixSum, map.get(prefixSum) - 1);
    if (map.get(prefixSum) == 0)
        map.remove(prefixSum);
}

public int pathSum(TreeNode root, int sum) {
    if (root == null)return 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    pathSum_(map, root, 0, sum);
    return ans;
}

//116
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

//116,117
public Node connect(Node root) {
    if(root==null)return root;
    LinkedList<Node>que=new LinkedList<>();
    que.add(root);
    while(que.size()!=0)
    {
        int size=que.size();
        while(size>0)
        {
            Node rn=que.remove();
            if(size==1)
            rn.next=null;
            else  rn.next=que.peek();

            if(rn.left!=null)
            que.add(rn.left);

            if(rn.right!=null)
            que.add(rn.right);

            size--;
        }
    }
    return root;
}

//116 a perfect binary tree where all leaves are on the same level, and every parent has two children.
public void populatingNextRight(Node root)
{
    // if(root==null)return;  //no need
    
    if(root.left==null && root.right==null)return;
    
    root.left.next=root.right;
    if(root.next!=null)root.right.next=root.next.left;
    
    populatingNextRight(root.left);
    populatingNextRight(root.right);
}

//117
public Node fun(Node node)
{
    if(node==null)return null;
    else if(node.left!=null)return node.left;    
    else if(node.right!=null)return node.right;  
    else return fun(node.next);
}

public void populatingNextRight2(Node root)
{
    if(root==null)return;
    if(root.left==null && root.right==null)return;
    
    if(root.left!=null)
    {
        if(root.right!=null)root.left.next=root.right;  
        else root.left.next=fun(root.next);   
    }
    
    if(root.right!=null)root.right.next=fun(root.next); 
    
    populatingNextRight2(root.right);
    populatingNextRight2(root.left);
}

public Node connect(Node root) {
    if(root==null)return null;
    populatingNextRight2(root);
    return root;
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/transform-to-left-cloned-tree-official/ojquestion
public static Node createLeftCloneTree(Node node) {
    if (node == null)
        return null;

    Node newNode = new Node(node.data, node.left, null);
    node.left = newNode;
    createLeftCloneTree(node.left.left);
    createLeftCloneTree(node.right);

    return node;
}

public static Node createLeftCloneTree_(Node node) {
    if (node == null)
        return null;

    Node lcr = createLeftCloneTree_(node.left); // left cloned root
    Node rcr = createLeftCloneTree_(node.right);

    Node newNode = new Node(node.data, lcr, null);
    node.left = newNode;
    node.right = rcr;

    return node;
}

public static Node transBackFromLeftClonedTree(Node node){
    if (node == null)
       return null;
   
   node.left=node.left.left;
   transBackFromLeftClonedTree(node.left);
   transBackFromLeftClonedTree(node.right);
   return node;
}

public static Node transBackFromLeftClonedTree_(Node node) {
    if (node == null)
        return null;

    Node lcr = transBackFromLeftClonedTree_(node.left.left);
    Node rcr = transBackFromLeftClonedTree_(node.right);

    node.left = lcr;
    node.right = rcr;
    return node;
}

public static void printSingleChildNodes(Node node) {
    if (node == null)
        return;
    if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
        if (node.right != null)
            System.out.println(node.right.data);
        else
            System.out.println(node.left.data);  
        return;
    }

    printSingleChildNodes(node.left);
    printSingleChildNodes(node.right);
}

// sir
public static void printSingleChildNodes(Node node, Node parent) {
    if (node == null)
        return;
    if (parent != null && parent.left == node && parent.right == null)
    {
        System.out.println(node.data);
        return;
    }
    else if (parent != null && parent.right == node && parent.left == null)
        {
            System.out.println(node.data);
            return;
        }
    printSingleChildNodes(node.left, node);
    printSingleChildNodes(node.right, node);

    // printSingleChildNodes(root, null);
}

// https://www.pepcoding.com/resources/online-java-foundation/binary-tree/remove-leaves-binary-tree-official/ojquestion
public static Node removeLeaves(Node node) {
    if (node == null)
        return null;
    if (node.left == null && node.right == null)
        return null;

    node.left = removeLeaves(node.left);
    node.right = removeLeaves(node.right);

    return node;
}

// leetcode 404
int leftSum = 0;   //ye bhi heap pr hi h
public boolean isNodeLeftleaf(TreeNode node) {
    if (node == null)
        return false;
    if (node.left == null && node.right == null)
        return true;

    return false;
}

public int sumOfLeftLeaves(TreeNode root) {
    if (root == null)
        return 0;
    if (isNodeLeftleaf(root.left))
        leftSum += root.left.val;
    sumOfLeftLeaves(root.left);
    sumOfLeftLeaves(root.right);
    return leftSum;
}

// https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
int maxleafToleaf = -(int) 1e9;   //-100
public int maxPathSumHelper(Node root) {
    if (root == null)
        return -(int) 1e9;  //-100
    if (root.left == null && root.right == null)
        return root.data;

    int leftNodetoleafmax = maxPathSumHelper(root.left); // isme humne leftsubTree se bola apne se lekar apni kisi bhi leaf tak ka max path sum.
    int rightNodetoleafmax = maxPathSumHelper(root.right);

    if (root.left != null && root.right != null) // ek akela leaf..leaf to leaf nhi hota....but agr ye check na lagaye aur -100 bhi lele -(int)1e9 ki jgh then tab bhi chlega
    //because question mein unhone badi values ni li hai..but recommended use -(int)1e9.
    maxleafToleaf = Math.max(maxleafToleaf, leftNodetoleafmax + root.data + rightNodetoleafmax);

    return Math.max(leftNodetoleafmax, rightNodetoleafmax) + root.data;
}

public int maxPathSum(Node root) {
    if (root == null)
        return Integer.MIN_VALUE;
    maxPathSumHelper(root);
    return maxleafToleaf;
}

// leetcode 124
int maxNTN = -(int) 1e9; // Max sum from node to node
public int maxPathSum_(TreeNode root) {
    if (root == null)
        return 0; // ya -(int)1e9

    //if (root.left == null && root.right == null)  //[0]->ans 0 but hoga -1e9 agr ye lgaya
        //return root.val;

    int leftNTN = maxPathSum_(root.left);
    int rightNTN = maxPathSum_(root.right);

    int maxNodetoRoot = Math.max(leftNTN, rightNTN) + root.val;
    maxNTN = Math.max(Math.max(maxNTN, maxNodetoRoot),
            Math.max(root.val, leftNTN + root.val + rightNTN));

    return Math.max(maxNodetoRoot, root.val);
} 

// {nodeTonode,rootTonode}
class pair {

    int nodeTonode = -(int) 1e9;
    int rootTonode = -(int) 1e9;

    pair() {
    }
}

int max_(int... arr) {
    int max = arr[0];
    for (int ele : arr) {
        max = Math.max(max, ele);
    }
    return max;
}

public pair maxPathSum_class(TreeNode root) {
    if (root == null)
        return new pair();

    pair leftSubtreeMax = maxPathSum_class(root.left);
    pair rightSubtreeMax = maxPathSum_class(root.right);

    pair result = new pair();

    result.rootTonode = max_(max_(leftSubtreeMax.rootTonode, rightSubtreeMax.rootTonode) + root.val, root.val);
    
    result.nodeTonode=max_(leftSubtreeMax.nodeTonode,rightSubtreeMax.nodeTonode,result.rootTonode,leftSubtreeMax.rootTonode+root.val+rightSubtreeMax.rootTonode);

    return result;
}

public int maxPathSum(TreeNode root) {
    if (root == null)
        return 0;
    // maxPathSum_(root);
    // return maxNTN;
    return maxPathSum_class(root).nodeTonode;
}

// leetcode 105
public TreeNode construct(int[] preorder, int[] inorder, int isi, int iei, int psi, int pei) {
    if (psi > pei)
        return null; // ya isi>iei
    int idx = isi;
    while (preorder[psi] != inorder[idx])idx++;

    TreeNode node = new TreeNode(preorder[psi]);

    int tnel = idx - isi;
    node.left = construct(preorder, inorder, isi, idx - 1, psi + 1, psi + tnel);
    node.right = construct(preorder, inorder, idx + 1, iei, psi + tnel + 1, pei);

    return node;
}

public TreeNode buildTree(int[] preorder, int[] inorder) {
    return construct(preorder, inorder, 0, inorder.length - 1, 0, preorder.length - 1);
}

// 106
public TreeNode construct_(int[] postorder, int[] inorder, int isi, int iei, int psi, int pei) {
    if (psi > pei)
        return null;
    int idx = isi;
    while (postorder[pei] != inorder[idx])
        idx++;

    TreeNode node = new TreeNode(inorder[idx]);
    //TreeNode node=new TreeNode(postorder[pei]);

    int tnel = idx - isi;
    node.left = construct_(postorder, inorder, isi, idx - 1, psi, psi + tnel - 1);
    node.right = construct_(postorder, inorder, idx + 1, iei, psi + tnel, pei - 1);

    return node;
}

public TreeNode buildTree_(int[] inorder, int[] postorder) {
    return construct_(postorder, inorder, 0, inorder.length - 1, 0, postorder.length - 1);
}

// leetcode 889
public TreeNode construct2(int[] postorder, int[] preorder, int psi, int pei, int posi, int poei) {
    if (psi > pei)
        return null;

    TreeNode node = new TreeNode(preorder[psi]);

    if (psi == pei)  //ya posi,poei
        return node;

    int idx = posi;
    while (preorder[psi + 1] != postorder[idx])idx++;

    int tnel = idx - posi + 1;
    node.left = construct_(postorder, preorder, psi + 1, psi + tnel, posi, idx);
    node.right = construct_(postorder, preorder, psi + tnel + 1, pei, idx + 1, poei - 1);

    return node;
}

public TreeNode constructFromPrePost(int[] pre, int[] post) {
    return construct2(post, pre, 0, pre.length - 1, 0, post.length - 1);
}

// leetcode 337
public int[] rob_(TreeNode root) {
    if (root == null) {
        int base[] = { 0, 0 };
        return base;
    }

    int lans[] = rob_(root.left);
    int rans[] = rob_(root.right);

    int myAns[] = new int[2];
    myAns[0] = lans[1] + root.val + rans[1];
    myAns[1] = Math.max(lans[0], lans[1]) + Math.max(rans[0], rans[1]);

    return myAns;
}

public int rob(TreeNode root) {
    if (root == null)return 0;
    int ans[] = rob_(root);
    return Math.max(ans[0], ans[1]);
}   

// leetcode 968
int cameras = 0;
public int minCameraCover_(TreeNode root) {
    if (root == null)
        return 0;

    int lans = minCameraCover_(root.left);
    int rans = minCameraCover_(root.right);

    if (lans == -1 || rans == -1) {
        cameras++;
        return 1;
    }

    if (lans == 1 || rans == 1)
        return 0;

    return -1;
}

public int minCameraCover(TreeNode root) {
    int answer = minCameraCover_(root);
    if (answer == -1)
        return cameras + 1; // not cameras++
    return cameras;
}

//leetcode 114
public TreeNode returnTail(TreeNode node)   //T->(O(n^2))
{   
    if(node==null)return null;
    while(node.right!=null)
        node=node.right;
    return node;
}

public void flatten(TreeNode root) {
    if(root==null || (root.left==null && root.right==null))return;
    
    flatten(root.left);
    flatten(root.right);
    
    TreeNode tail=returnTail(root.left);
    if(tail!=null)
    {   tail.right=root.right;
        root.right=root.left;
        root.left=null;
    } 
}

public TreeNode flatten_(TreeNode root) {
    if(root==null || (root.left==null && root.right==null))
    return root;

    TreeNode leftTail=flatten_(root.left);
    TreeNode rightTail=flatten_(root.right);

    if(leftTail!=null)
    {
        leftTail.right=root.right;
        root.right=root.left;
        root.left=null;
    }

    return rightTail!=null?rightTail:leftTail;
}

public void flatten1(TreeNode root) {
    if(root==null)return;
    flatten_(root);
}

//leetcode 297
public void serialize_(TreeNode root,StringBuilder sb)
{
    if(root==null)
    {
        sb.append("-1001 ");         //-1000 <= Node.val <= 1000
        return;
    }

    sb.append(root.val+" ");
    serialize_(root.left,sb);
    serialize_(root.right,sb);
}

public String serialize(TreeNode root) {
    StringBuilder sb=new StringBuilder();
    serialize_(root,sb);
    return sb.toString();
}

int idx=0;
public TreeNode deserialize(int arr[])
{   
    if(arr[idx]==-1001)
    {
        idx++;
        return null;
    }

    TreeNode node=new TreeNode(arr[idx++]);
    node.left=deserialize(arr);
    node.right=deserialize(arr);

    return node;
}

public TreeNode deserialize(String data) {
    if(data=="")return null;
    String arr[]=data.split(" ");
    int num[]=new int[arr.length];
    int i=0;
    for(String str:arr)
    {
        int x=Integer.parseInt(str);
        num[i++]=x;
    }
    return deserialize(num);
}

//https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1#
Node dummy=new Node(-1);
Node prev=dummy;
Node bToDLL_(Node root)
{
    if(root==null)return null;
    
    bToDLL_(root.left);
    prev.right=root;
    root.left=prev;
    
    prev=root;
    bToDLL_(root.right);
    
    return root;
}

Node bToDLL(Node root)
{
    if(root==null)return null;
    bToDLL_(root);
    Node head=dummy.right;
    dummy.right=null;
    head.left=null;
    return head;
}

//897
TreeNode current=null;
public void increasingBST_(TreeNode root)
{
    if(root==null)return;
    
    increasingBST_(root.left);
    root.left=null;
    current.right=root;
    current=root;
    increasingBST_(root.right);
}

public TreeNode increasingBST(TreeNode root) {
    TreeNode dummy=new TreeNode(-1);
    current=dummy;
    increasingBST_(root);
    return dummy.right;
}

//leetcode 965
public boolean isUniValTree_helper(TreeNode root,int data)
{
    if(root==null)return true;
    
    if(root.val!=data)return false;
    return isUniValTree_helper(root.left,data) && isUniValTree_helper(root.right,data);
}

public boolean isUnivalTree(TreeNode root) {
    return isUniValTree_helper(root,root.val);
}

// leetcode 1372
//Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0)
public int[] longestZigZagPath(TreeNode root)
{
    if(root==null)
    {
        int []base={-1,-1,-1};   //forward,backward,maxLen
        return base;
    }

    int leftans[]=longestZigZagPath(root.left);
    int rightans[]=longestZigZagPath(root.right);

    int []myAns=new int[3];
    myAns[0]=leftans[1]+1;
    myAns[1]=rightans[0]+1;
    myAns[2]=Math.max(Math.max(leftans[2],rightans[2]),Math.max(myAns[0],myAns[1]));  //path thru root not considered

    return myAns;
}

public int longestZigZag(TreeNode root) {
    if(root==null)return 0;
    int []ans=longestZigZagPath(root);
    return ans[2];
}

//1302
// or by level order traversal
public void dls_helper(TreeNode root,int []sum,int level,int dl)
{
    if(root==null)return;
    if(level==dl)
    {
        sum[0]+=root.val;  
        return;
    }
    
    dls_helper(root.left,sum,level+1,dl);
    dls_helper(root.right,sum,level+1,dl);
}

public int deepestLeavesSum(TreeNode root) {
    int dl=height(root);
    int []sum=new int[1];
    dls_helper(root,sum,1,dl);
    return sum[0];
}

//1008
int idx=0;
public TreeNode bst_preorder_helper(int []preorder,int n,int lr,int rr)
{
    if(idx>=n || !(preorder[idx]>=lr && preorder[idx]<rr))
        return null;
    
    TreeNode node=new TreeNode(preorder[idx++]);
    node.left=bst_preorder_helper(preorder,n,lr,node.val);
    node.right=bst_preorder_helper(preorder,n,node.val,rr);
    return node;
}

public TreeNode bstFromPreorder(int[] preorder) {
    int n=preorder.length;
    
    return bst_preorder_helper(preorder,n,(int)-1e8,(int)1e8);
}

public class pair{
    int lr;
    int rr;
    Node node;
    
    pair(int lr,int rr,Node node)
    {
        this.lr=lr;
        this.rr=rr;
        this.node=node;
    }
}

//https://practice.geeksforgeeks.org/problems/convert-level-order-traversal-to-bst/1#
public Node constructBST(int[] arr)
{
    LinkedList<pair>queue=new LinkedList<>();
    int idx=0,n=arr.length;
    Node root=null;
    queue.add(new pair(-(int)1e8,(int)1e8,null));
    while(idx<n)
    {
        pair p=queue.pollFirst();
        if(!(arr[idx]>=p.lr && arr[idx]<p.rr))continue;
        Node node=new Node(arr[idx]);
        if(p.node==null)
        {
            root=node;
        }
        else{
            Node par=p.node;
            if(arr[idx]<=par.data)
            par.left=node;
            else par.right=node;
        }
        queue.add(new pair(p.lr,arr[idx],node));
        queue.add(new pair(arr[idx],p.rr,node));
        idx++;
    }
    return root;
}

// 16 feb
public static TreeNode rightMostNode(TreeNode next, TreeNode curr) {
    while (next.right != null && next.right != curr)
        next = next.right;
    return next;
}

public static void morrisInorderTraversal(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
        TreeNode next = curr.left;
        if (next == null) {
            System.out.println(curr.val + " ");
            curr = curr.right;
        } else {
            TreeNode rightMost = rightMostNode(next, curr);
            if (rightMost.right == null) {  //thread create
                rightMost.right = curr;
                curr = curr.left;
            } else {
                rightMost.right = null; //break thread it mean we have traversed whole left subtree
                System.out.println(curr.val + " ");
                curr = curr.right;
            }
        }
    }
}

public static void morrisPreorderTraversal(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
        TreeNode next = curr.left;
        if (next == null) {
            System.out.println(curr.val + " ");
            curr = curr.right;
        } else {
            TreeNode rightMost = rightMostNode(next, curr);
            if (rightMost.right == null) {
                System.out.println(curr.val + " ");
                rightMost.right = curr;
                curr = curr.left;
            } else {
                rightMost.right = null;
                curr = curr.right;
            }
        }
    }
}

// leetcode 230
public void insertAllLeft(LinkedList<TreeNode> st, TreeNode node) {
    while (node != null) {
        st.addFirst(node);
        node = node.left;
    }
}

public int kthSmallest(TreeNode root, int k) {
    LinkedList<TreeNode> st = new LinkedList<>();
    insertAllLeft(st, root);

    while (k-- > 1) {
        TreeNode rn = st.getFirst();
        st.removeFirst();
        insertAllLeft(st, rn.right);
    }

    return st.getFirst().val;
}

public static TreeNode rightMostNode(TreeNode next, TreeNode curr) {
    while (next.right != null && next.right != curr)
        next = next.right;

    return next;
}

public static int kthSmallest2(TreeNode root, int k) {
    TreeNode curr = root;
    while (curr != null) {
        TreeNode next = curr.left;
        if (next == null) {
            if (k == 1)
                return curr.val;
            k--;
            curr = curr.right;
        } else {
            TreeNode rightMost = rightMostNode(next, curr);
            if (rightMost.right == null) {
                rightMost.right = curr;
                curr = curr.left;
            } else {
                rightMost.right = null;
                if (k == 1)
                    return curr.val;
                k--;
                curr = curr.right;
            }
        }
    }
    return -1;
}

//https://practice.geeksforgeeks.org/problems/median-of-bst/1#
public static Node rightMostNode(Node node,Node curr)
{
    while(node.right!=null && node.right!=curr)
    {
        node=node.right;
    }
    return node;
}

public static int inorder(Node root,int k)
{
    Node curr=root;
    int ans=-1;
    while(curr!=null)
    {
        Node left=curr.left;
        if(left==null)
        {
            if(k==1)
                ans=curr.data;  //cant return poora chlana pdega vrna agli baar jab chlaynge galat answer
            k--;
            curr=curr.right;
        }
        else{
            Node rightnode=rightMostNode(left,curr);
            if(rightnode.right==null) //thread create
            {
                rightnode.right=curr;
                curr=curr.left;
            }
            else{
                rightnode.right=null;
                if(k==1)
                    ans=curr.data;
                k--;
                curr=curr.right;
            }
        }
    } 
    return ans;
}

public static int size(Node root)
{
    return root==null?0:size(root.left)+size(root.right)+1;    
}

public static float findMedian(Node root)
{
    int sz=size(root);
    float v1,v2;
    if(sz%2!=0)
    {
        v1=inorder(root,sz/2+1);
        return v1;
    }
    else{
        v2=inorder(root,sz/2+1);
        v1=inorder(root,sz/2);
        return (v1+v2)/2;
    }
    
}
