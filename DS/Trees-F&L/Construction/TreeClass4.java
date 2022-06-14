package Done;
import java.util.*;

public class TreeClass4 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void levelOrderSimple(TreeNode node) {
        LinkedList<TreeNode> que = new LinkedList<>();
        // Queue<TreeNode>que1=new ArrayDeque<>(); remove add fn
        que.addLast(node);
        while (que.size() != 0) {
            TreeNode rn = que.removeFirst(); // remove print add
            System.out.print(rn.val + " ");
    
            if (rn.left != null)
                que.addLast(rn.left);
    
            if (rn.right != null)
                que.addLast(rn.right);
        }
    }
    
    public static void levelOrderLevelWiseM_1(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        LinkedList<TreeNode> childQue = new LinkedList<>();
    
        que.addLast(root);
        int level = 0;
        System.out.print("Level " + level + ":");
        while (que.size() != 0) {
            TreeNode rn = que.removeFirst();
            System.out.print(rn.val + " ");
    
            if (rn.left != null)
                childQue.addLast(rn.left);
    
            if (rn.right != null)
                childQue.addLast(rn.right);
    
            if (que.size() == 0) {
                // level++;
                System.out.println();
                // if(childQue.size()!=0)System.out.print("Level "+level+":");
                if (childQue.size() != 0)
                    System.out.print("Level " + (++level) + ":");
    
                LinkedList<TreeNode> temp = que;
                que = childQue;
                childQue = temp;
            }
        }
    }
    
    public static void levelOrderLevelWiseM_2(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        que.addLast(null);
        int level = 0;
        System.out.print("Level 0" + ":");
        // while(que.size()!=0)  //mere waale mein pehli node kelie 2 baar loop chala but sir mein 1 baar
        while (que.size() != 1) {
            TreeNode rn = que.removeFirst();
            // if(rn!=null){
            System.out.print(rn.val + " ");
    
            if (rn.left != null)
                que.addLast(rn.left);
    
            if (rn.right != null)
                que.addLast(rn.right);
            // }
    
            // else if(rn==null)
            // {
            // System.out.println();
            // if(que.size()!=0){
            // System.out.print("Level "+(++level)+":");
            // que.addLast(null);
            // }
            // }
    
            if (que.getFirst() == null) {
                System.out.println();
                if (que.size() != 1) {
                    System.out.print("Level " + (++level) + ":");
                    que.addLast(que.removeFirst());
                }
            }
        }
    }
    
    public static void levelOrderLevelWiseM_3(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;
        while (que.size() != 0) {
            System.out.print("Level " + level + ":");
            int size = que.size();
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                System.out.print(rn.val + " ");
    
                if (rn.left != null)
                    que.addLast(rn.left);
    
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            level++;
            System.out.println();
        }
    }
    
    // leetcode 102
    public List<List<Integer>> levelOrder_(TreeNode root, List<List<Integer>> result) {
        LinkedList<TreeNode> que = new LinkedList<>();
    
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode rn = que.removeFirst(); // rn : remove Node
                level.add(rn.val);
    
                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            result.add(level);
        }
        return result;
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        levelOrder_(root, result);
        return result;
    }
    
    // leetcode 199
    public void rightSideView_(TreeNode root,List<Integer>result) { 
        LinkedList<TreeNode> que = new LinkedList<>();
    
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();     
            result.add(que.getLast().val);       
            // result.add(que.getFirst().val);      //left side view kelie
            while (size-- > 0) {                 //yhaan size>0 just check krte hi decrement hojaata hai.
                TreeNode rn = que.removeFirst(); // rn : remove Node
                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
        }
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        rightSideView_(root, result);
        return result;
    }
    
    // leetcode 103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {  //tree=>[3,[9,20]] o/p=>[3,[20,9]]
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;
        while (que.size() != 0) {
            List<Integer> res = new ArrayList<>();
            int size = que.size();
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                res.add(rn.val);
    
                if (rn.left != null)
                    que.addLast(rn.left);
    
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            if (level % 2 != 0)
                Collections.reverse(res);
            level++;
            result.add(res);
        }
        return result;
    }
    
    // leetcode 637
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            int s = size;
            double sum = 0;
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                sum += rn.val;
    
                if (rn.left != null)
                    que.addLast(rn.left);
    
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            double average = sum / s;  //agr dono hi int then 3/2 jaise kelie ans 1.0 
            result.add(average);
        }
        return result;
    }
    
    // leetcode 107
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            List<Integer> res = new ArrayList<>();
            int size = que.size();
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                res.add(rn.val);
    
                if (rn.left != null)
                    que.addLast(rn.left);
    
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            result.add(res);
        }
        Collections.reverse(result);
        return result;
    }
    
    //1161
    public int maxLevelSum(TreeNode root) {
        
        LinkedList<TreeNode>que=new LinkedList<>();
        que.add(root);
        int maxSum=-(int)1e9,minLevel=0,sum=0,level=0;
        while(que.size()!=0)
        {
            int size=que.size();
            level++;sum=0;
            while(size --> 0)
            {
                TreeNode rn=que.remove();
                sum+=rn.val;
                if(rn.left!=null)
                    que.add(rn.left);
                
                if(rn.right!=null)
                    que.add(rn.right);
            }
            if(sum>maxSum)
            {
                maxSum=sum;
                minLevel=level;
            }
        }
        return minLevel;
    }
    
    // 513
    public int findBottomLeftValue(TreeNode root) {
        LinkedList<TreeNode>que=new LinkedList<>();
        que.add(root);
        int bottomLeftValue=0;
        while(que.size()!=0)
        {
            int size=que.size();
            bottomLeftValue=que.peek().val;
            while(size --> 0)
            {
                TreeNode rn=que.removeFirst();
    
                if(rn.left!=null)que.add(rn.left);
                if(rn.right!=null)que.add(rn.right);
            }
        }
        return bottomLeftValue;
    }
    
    public class verticalPair {
        TreeNode node = null;
        int hl = 0; // horizontal level
    
        verticalPair(TreeNode node, int hl) {
            this.node = node;
            this.hl = hl;
        }
    }
    
    public void display(List<List<Integer>> result) {
        for (List<Integer> arr : result)
            System.out.println(arr);
    }
    
    // [8,10,11,15,20,30,36,null,47,null,null,50,60]
    public void verticalOrderTraversal(TreeNode root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int maxX = 0, minX = 0;
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, 0));
    
        while (que.size() != 0) {
            verticalPair rp = que.removeFirst();
    
            map.putIfAbsent(rp.hl, new ArrayList<>()); // work if key not present or if key mapped to null
            map.get(rp.hl).add(rp.node.val);
            if ((rp.node).left != null)
                que.addLast(new verticalPair(rp.node.left, rp.hl - 1));
    
            if ((rp.node).right != null)
                que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
    
            maxX = Math.max(maxX, rp.hl);
            minX = Math.min(minX, rp.hl);
        }
    
        List<List<Integer>> result = new ArrayList<>();
        while (minX <= maxX) {
            result.add(map.get(minX));
            minX++;
        }
        display(result);
    }
    
    public void width(int[] arr, TreeNode root, int hl) {
        if (root == null)return;
        width(arr, root.left, hl - 1);  //pre or post
        width(arr, root.right, hl + 1);
    
        arr[0] = Math.min(arr[0], hl);
        arr[1] = Math.max(arr[1], hl);
    }
    
    public void verticalOrderTraversal_(TreeNode root) {
        int arr[] = new int[2];
        width(arr, root, 0);
        int length = arr[1] - arr[0] + 1;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length; i++)
            result.add(new ArrayList<>());
    
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, -arr[0]));
    
        while (que.size() != 0) {
            verticalPair rp = que.removeFirst();
            result.get(rp.hl).add(rp.node.val);
    
            if ((rp.node).left != null)
                que.addLast(new verticalPair(rp.node.left, rp.hl - 1));
    
            if ((rp.node).right != null)
                que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
        }
        display(result);
    }
    
    public int[] verticalSum(TreeNode root) {
        int arr[] = new int[2];
        width(arr, root, 0);
        int length = arr[1] - arr[0] + 1;
        int[] result = new int[length];
    
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, -arr[0]));
    
        while (que.size() != 0) {
            verticalPair rp = que.removeFirst();
            result[rp.hl] += rp.node.val;
    
            if ((rp.node).left != null)
                que.addLast(new verticalPair(rp.node.left, rp.hl - 1));
    
            if ((rp.node).right != null)
                que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
        }
        for (int ele : result)
            System.out.print(ele + " ");
        return result;
    }
    
    public int[] bottomView(TreeNode root) {
        int arr[] = new int[2];
        width(arr, root, 0);
        int length = arr[1] - arr[0] + 1;
        int[] result = new int[length];
    
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, -arr[0]));
    
        while (que.size() != 0) {
            verticalPair rp = que.removeFirst();
            result[rp.hl] = rp.node.val;
    
            if ((rp.node).left != null)
                que.addLast(new verticalPair(rp.node.left, rp.hl - 1));
    
            if ((rp.node).right != null)
                que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
        }
        for (int ele : result)
            System.out.print(ele + " ");
        return result;
    }
    
    public int[] topView(TreeNode root) {
        int arr[] = new int[2];
        width(arr, root, 0);
        int length = arr[1] - arr[0] + 1;
        int[] result = new int[length];
        boolean[] resultVis = new boolean[length];
    
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, -arr[0]));
    
        while (que.size() != 0) {
            verticalPair rp = que.removeFirst();
            // if(result[rp.hl]==0)
            if (resultVis[rp.hl] == false) {
                result[rp.hl] = rp.node.val;
                resultVis[rp.hl] = true;
            }
    
            if ((rp.node).left != null)
                que.addLast(new verticalPair(rp.node.left, rp.hl - 1));
    
            if ((rp.node).right != null)
                que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
        }
        for (int ele : result)
            System.out.print(ele + " ");
        return result;
    }
    
    public void diagonalOrderTraversal_(TreeNode root) {
        int arr[] = new int[2];
        width(arr, root, 0);
        int length = arr[1] - arr[0] + 1;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length; i++)
            result.add(new ArrayList<>());
    
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, -arr[0]));
    
        while (que.size() != 0) {
            verticalPair rp = que.removeFirst();
            result.get(rp.hl).add(rp.node.val);
    
            if ((rp.node).left != null)
                que.addLast(new verticalPair(rp.node.left, rp.hl - 1));
    
            if ((rp.node).right != null)
                que.addLast(new verticalPair(rp.node.right, rp.hl));
        }
        display(result);
    }
    
    // leetcode 987
    //agar hume sorted order mein chahiye then all element of that level should be present at same time.
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<verticalPair> que = new PriorityQueue<>((a, b) -> { //lambda bhi ek fn hi hai jisme do argument jaate hain then compare
            return a.node.val - b.node.val;
        });
        PriorityQueue<verticalPair> childQue = new PriorityQueue<>((a, b) -> { // isme bhi likhna pdega because hum bs pointer change krrhe h na ki child se que mein copy.
            return a.node.val - b.node.val;
        });
        int arr[] = new int[2];
        width(arr, root, 0);
        int length = arr[1] - arr[0] + 1;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length; i++)
            result.add(new ArrayList<>());
    
        que.add(new verticalPair(root, -arr[0]));
        
        //pehle first level then next aese krke hum jaate hain tou y ki dependency aese cover hui and priority queue se choota element nikalte hain aese jab same y vali cheez cover.
        while (que.size() != 0) {
            verticalPair rp = que.poll();
            result.get(rp.hl).add(rp.node.val);
    
            if ((rp.node).left != null)
                childQue.add(new verticalPair(rp.node.left, rp.hl - 1));
    
            if ((rp.node).right != null)
                childQue.add(new verticalPair(rp.node.right, rp.hl + 1));
    
            if (que.size() == 0) {
                PriorityQueue<verticalPair> temp = que;
                que = childQue;
                childQue = temp;
            }
        }
        display(result);
    
        return result;
    }
    
    class verticalPair2 {
        TreeNode node = null;
        int x = 0;
        int y = 0;
    
        verticalPair2(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }
    
    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        PriorityQueue<verticalPair2> que = new PriorityQueue<>((a, b) -> {
            if (a.y != b.y)
                return a.y - b.y;
            else
                return a.node.val - b.node.val;
        });
        int arr[] = new int[2];
        width(arr, root, 0);
        int length = arr[1] - arr[0] + 1;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length; i++)
            result.add(new ArrayList<>());
    
        que.add(new verticalPair2(root, -arr[0], 0));
    
        while (que.size() != 0) {
            verticalPair2 rp = que.remove();
            result.get(rp.x).add(rp.node.val);
    
            if ((rp.node).left != null)
                que.add(new verticalPair2(rp.node.left, rp.x - 1, rp.y + 1));
    
            if ((rp.node).right != null)
                que.add(new verticalPair2(rp.node.right, rp.x + 1, rp.y + 1));
    
        }
        display(result);
        return result;
    }
    
}