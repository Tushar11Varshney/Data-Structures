package Done;

import java.util.LinkedList;
  
public class googleSheet {

    public class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode() {}
             TreeNode(int val) { this.val = val; }
             TreeNode(int val, TreeNode left, TreeNode right) {
                 this.val = val;
                 this.left = left;
                 this.right = right;
             }
    }

    //652
    List<TreeNode>ans=new ArrayList<>();
    Map<String,Integer>map=new HashMap<>();
    public String duplicates(TreeNode root)
    {
        if(root==null)return "X";
        
        String leftSubTree=duplicates(root.left);
        String rightSubTree=duplicates(root.right);
        String res=root.val + "-" + leftSubTree + "-" + rightSubTree;
        
        map.put(res,map.getOrDefault(res,0)+1);
        if(map.get(res)==2)
            ans.add(root);   
        
        return res;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if(root==null)return ans;
        duplicates(root);
        return ans;
    }

    //1110
    private TreeNode removeNodes(Set<Integer>hs,TreeNode root,List<TreeNode>result)
    {
        if(root==null)return null;
        
        root.left=removeNodes(hs,root.left,result);
        root.right=removeNodes(hs,root.right,result);
        if(hs.contains(root.val))
        {
            if(root.left!=null)
            result.add(root.left);
            if(root.right!=null)
            result.add(root.right);
            return  null;
        }
        return root;
    }
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode>result=new ArrayList<>();
        if(root==null)return result;
        Set<Integer>hs=new HashSet<>();  //take set to delet in o(1)
        for(int x:to_delete)
            hs.add(x);
        removeNodes(hs,root,result);
        if(!hs.contains(root.val))
            result.add(root);
        return result;
    }

    //https://www.lintcode.com/problem/count-univalue-subtrees/
    class info_{

        int univalueTree;
        boolean amIuniValue;
        info_(){}
        info_(int univalueTree,boolean amIuniValue)
        {
            this.univalueTree=univalueTree;
            this.amIuniValue=amIuniValue;
        }
    }

    public info_ UnivalSubTree(TreeNode root)
    {
        if(root==null)
        return new info_(0,true);
        if(root.left==null && root.right==null)
        return new info_(1,true);

        info_ res=new info_();
        info_ leftSubTree=UnivalSubTree(root.left);
        info_ rightSubTree=UnivalSubTree(root.right);

        if(root.left!=null || root.right!=null)
        {
            if(root.left!=null && root.right!=null)
            {
                if(leftSubTree.amIuniValue && rightSubTree.amIuniValue && root.val==root.left.val && root.val==root.right.val)
                {
                    res.univalueTree=leftSubTree.univalueTree+rightSubTree.univalueTree+1;
                    res.amIuniValue=true;
                }    
                else 
                {
                    res.univalueTree=leftSubTree.univalueTree+rightSubTree.univalueTree;
                    res.amIuniValue=false;
                }    
            }
            else if(root.left!=null) {
                if(root.val==root.left.val){
                    res.amIuniValue=true;
                    res.univalueTree=leftSubTree.univalueTree+1;
                }
                else{
                    res.amIuniValue=false;
                    res.univalueTree=leftSubTree.univalueTree;
                }    
            }    
            else if(root.right!=null){
                if(root.val==root.right.val){
                    res.amIuniValue=true;
                    res.univalueTree=rightSubTree.univalueTree+1;
                }
                else{
                    res.amIuniValue=false;
                    res.univalueTree=rightSubTree.univalueTree;
                }   
            }    
        }

        return res;    
    }

    public int countUnivalSubtrees(TreeNode root) {
        
        if(root==null)return 0;
        return UnivalSubTree(root).univalueTree;
    }

     //https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence/
     class info{   //stack overflow
        int LCSleft;
        int LCSright;
        int LCSme;

        info(int LCSleft,int LCSright,int LCSme)
        {
            this.LCSleft=LCSleft;
            this.LCSright=LCSright;
            this.LCSme=LCSme;
        }
    };

    public info longestConsecutive_(TreeNode root) {
        if(root==null)return new info(0,0,0);
        if(root.left==null && root.right==null)return new info(0,0,1);

        info result=new info(0,0,1);
        info leftSubTree=longestConsecutive_(root.left);
        info rightSubTree=longestConsecutive_(root.right);
        
        result.LCSleft=Math.max(leftSubTree.LCSleft,leftSubTree.LCSme);

        result.LCSright=Math.max(rightSubTree.LCSright,rightSubTree.LCSme);

        if(root.left!=null && root.val+1==root.left.val)
            result.LCSme=Math.max(result.LCSme,leftSubTree.LCSme+1);
        if(root.right!=null && root.val+1==root.right.val) 
            result.LCSme=Math.max(result.LCSme,rightSubTree.LCSme+1);    

        System.out.println(result.LCSleft+" "+result.LCSright+" "+result.LCSme);
        return result;
    }

    public int longestConsecutive(TreeNode root)
    {
        if(root==null)return 0;
        info result=longestConsecutive_(root);
        return Math.max(result.LCSleft,Math.max(result.LCSright,result.LCSme));
    }

    public void longestConsecutive_(TreeNode root,int count,int target,int []max)
    {
        if(root==null)return;
        else if(root.val==target)count++;
        else count=1;

        max[0]=Math.max(max[0],count);
        longestConsecutive_(root.left,count,root.val+1,max);
        longestConsecutive_(root.right,count,root.val+1,max);

    }

    public int longestConsecutive(TreeNode root) {
        if(root==null)return 0;
        int []max=new int[1];
        longestConsecutive_(root,0,root.val,max);
        return max[0];
    }
  
    //https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence-ii/
    int maxlength=0;
    public int[] longestConsecutive2_(TreeNode root)
    {
        if(root==null)return new int[]{0,0};
        if(root.left==null && root.right==null)return new int[]{1,1};

        int []ans=new int[2];  //increase,decrease
        ans[0]=1;ans[1]=1;
        int []left=longestConsecutive2_(root.left);
        int []right=longestConsecutive2_(root.right);

        if(root.left!=null)
        {
            if(root.left.val+1==root.val)
            ans[1]=left[1]+1;
            else if(root.left.val-1==root.val)
            ans[0]=left[0]+1;
        }
        if(root.right!=null)
        {
            if(root.right.val+1==root.val)
            ans[1]=Math.max(ans[1],right[1]+1);
            else if(root.right.val-1==root.val)
            ans[0]=Math.max(ans[0],right[0]+1);
        }

        maxlength=Math.max(maxlength,ans[0]+ans[1]-1);
        return ans;
    }

    public int longestConsecutive2(TreeNode root) {
        longestConsecutive2_(root);
        return maxlength;
    }
    
    

}
