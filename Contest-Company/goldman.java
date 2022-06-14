public class goldman {
public class Main
{   
    static  class pair{
        String str;
        int number;
        
        pair(int number,String str)
        {
            this.number=number;
            this.str=str;
        }
    }
    
    public static int decoding(int []cod,String word)
    {
        int number=0;
        for(int i=0;i<word.length();i++)
        {
            
            int idx=word.charAt(i)-'a';
            number=number*10+cod[idx];
        }
        
        return number;
    }
    
	public static void main(String[] args) {
		String []arr={"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
		int []cod=new int[26];
		int k=0;
		for(int i=0;i<arr.length;i++)
		{
		    String str=arr[i];
		    for(int j=0;j<str.length();j++)
		    {
		        cod[k++]=i+2;
		    }
		}
		
		PriorityQueue<pair>pq=new PriorityQueue<>((a,b)->{
            if(a.number!=b.number)
            return b.number-a.number;   
            
            return a.str.compareTo(b.str);        
        });
		
		String []words=new String[]{"rat","fog","cat","dog","pat","mat"};
		for(int i=0;i<words.length;i++)
		{
		    int decoded=decoding(cod,words[i]);
		    pq.add(new pair(decoded,words[i]));
		}
		
		while(pq.size()!=0)
		{
		    pair p=pq.remove();
		    System.out.println(p.str+" "+p.number);
		}
	}
}

float perimeter(triangle r)
{
	    float res=0;
	    float base=(r.x3-r.x2)/2;
	    float height=r.y1-r.y2;
	    float hypotenuse=sqrt(base*base+height*height);
	    res=hypotenuse*2+base*2;
	    return res;
}

float Area(triangle r)
{
	    float res=0;
	    float base=(r.x3-r.x2)/2;
	    float height=r.y1-r.y2;
	    
	    cout<<base<<" "<<height<<endl;
	    res=(base*height);
	    return res;
}

//cout << fixed << setprecision(2) << r.Area(r)<<endl;
// cout << fixed << setprecision(2) << r.perimeter(r)<<endl;

int lastSetBit=0;
    for(int i=S.length()-1;i>=0;i--)
    {
        if(S[i]=='1')
        lastSetBit=i;
    }
    
    int step=0,idx=S.size()-1;
    while(idx!=lastSetBit)
    {
        if(S[idx]=='0')
            step++;
        else
          step+=2;
        idx--;
    } 
    
    cout<<step+1;
}

//315
void mergeTwoList(vector<pair<int,int>>&qs,int si,int ei,vector<int>&ans,vector<pair<int,int>>&helperArr)
    {
        int k=si,mid=(si+ei)/2,i=si,j=mid+1;
        
        while(i<=mid && j<=ei)
        {
            if(qs[i].first<=qs[j].first)
            {
                helperArr[k++]=qs[j++];
            }    
            else
            {
                ans[qs[i].second]+=ei-j+1;
                helperArr[k++]=qs[i++];   
            } 
        }
        
        while(i<=mid)
            helperArr[k++]=qs[i++];
        
        while(j<=ei)
            helperArr[k++]=qs[j++];
        
        for(int i=si;i<=ei;i++)
            qs[i]=helperArr[i];
    }
    
    void mergeSort(vector<pair<int,int>>&qs,int si,int ei,vector<int>&ans,vector<pair<int,int>>&helperArr)
    {
        if(si==ei)
            return;
        
        int mid=(si+ei)/2;
        mergeSort(qs,si,mid,ans,helperArr);
        mergeSort(qs,mid+1,ei,ans,helperArr);
        
        mergeTwoList(qs,si,ei,ans,helperArr);    
    }
    
    vector<int> countSmaller(vector<int>& nums) {
        int n=nums.size(); 
        vector<int>ans(n,0);
        vector<pair<int,int>>helperArr(n);
        
        vector<pair<int,int>>qs(n);
        for(int i=0;i<n;i++)
        {
            pair<int,int>p;
            p.first=nums[i];
            p.second=i;
            qs[i]=p;
        }
        mergeSort(qs,0,n-1,ans,helperArr);
        return ans;
}

// 1909
public boolean canBeIncreasing(int[] nums) {
    int n = nums.length;

    int count = 0;
    for (int i = 1; i < n; i++) {
        if (nums[i] <= nums[i - 1]) {
            if (count == 1)
                return false;
            count++;
            if (i > 1 && nums[i] <= nums[i - 2]) // [105,924,32,928] //[105,924,106,928]
            {
                nums[i] = nums[i - 1];
            }
        }
    }

    return true;
}

//1404
int numSteps(string s) {
    int steps=0;
    while(s.size()>1)
    {
        if(s.back()=='0')
        {
            s.pop_back();
            steps++;
        }
        else{
            int flag=0,idx=s.length()-1;
            while(idx>=0)
            {
                if(s[idx]!='0')
                    s[idx]='0';
                else 
                {
                    s[idx]='1';
                    flag=1;
                    break;
                }
                idx--;
            }        
            if(flag==0)
                s='1'+s;
            steps++;
        }
    }
    
    return steps;
}

// 1863
public List<List<Integer>> allsubsets(int[] nums, int idx, int n) {
    if (idx == n) {
        List<List<Integer>> base = new ArrayList<>();
        base.add(new ArrayList<>());
        return base;
    }

    List<List<Integer>> ans = allsubsets(nums, idx + 1, n);
    List<List<Integer>> result = new ArrayList<>();

    for (List<Integer> ar : ans) {
        List<Integer> arr = new ArrayList<>(ar);
        result.add(ar);
        arr.add(nums[idx]);
        result.add(arr);
    }
    return result;
}

public int subsetXORSum(int[] nums) {

    int total = 0;
    int n = nums.length;
    List<List<Integer>> result = allsubsets(nums, 0, n);
    for (int i = 0; i < result.size(); i++) {
        if (result.get(i).size() == 0)
            continue;
        else if (result.get(i).size() == 1)
            total += (result.get(i)).get(0);
        else {
            int ans = (result.get(i)).get(0);
            for (int j = 1; j < result.get(i).size(); j++) {
                ans ^= (result.get(i)).get(j);
            }
            total += ans;
        }
    }
    return total;
}

// 1864
public int minSwaps(String s) {

    int n = s.length();
    int cntZero = 0, cntOne = 0;
    for (int i = 0; i < n; i++) {
        char ch = s.charAt(i);
        if (ch == '1')
            cntOne++;
        else
            cntZero++;
    }

    if (Math.abs(cntZero - cntOne) > 1)
        return -1;

    int s1 = 0, s0 = 0; // start with 1 //start with 2
    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (i % 2 == 0 && ch == '0')
            s1++;
        else if (i % 2 != 0 && ch == '1')
            s1++;

        if (i % 2 == 0 && ch == '1')
            s0++;
        else if (i % 2 != 0 && ch == '0')
            s0++;
    }
    if (cntZero == cntOne)
        return Math.min(s1 / 2, s0 / 2);
    else if (cntOne > cntZero)
        return s1 / 2;
    return s0 / 2;
}

// 1865
class FindSumPairs {

    HashMap<Integer, Integer> map = new HashMap<>();
    int[] nums1, nums2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int ele : nums2) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
    }

    public void add(int index, int val) {
        map.put(nums2[index], map.get(nums2[index]) - 1);
        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int target) {

        int c = 0;
        // 1 <= nums1.length <= 1000
        // 1 <= nums2.length <= 10^5 loop on smaller array will take less time so put
        // nums2 in hashmap.
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(target - nums1[i]))
                c += map.get(target - nums1[i]);
        }

        return c;
    }
}

vector<string>closestColor(vector<string>pixel)
{
    vector<vector<int>>num={{255,255,255},{255,0,0},{0,255,0},{0,0,255},{0,0,0}};
    vector<string>name={"white","red","green","blue","black"};
    
    vector<string>ans;
    for(int i=0;i<pixel.size();i++)
    {
        string s=pixel[i];
        vector<int>clrVal;
        for(int j=0;j<s.length();j+=8)
        {
            int num=0;
            int l=7;
            for(int k=j;k<j+8;k++)
            {
                num+=(s[k]-'0')*(int)(pow(2,l));
                l--;
            }
            clrVal.push_back(num);
        }
        
        // for(int ele:clrVal)
        // cout<<ele<<endl;
        
        double min=1e9,dist=0.0;int idx;
        for(int i=0;i<num.size();i++)
        {
            dist=sqrt(pow((clrVal[0]-num[i][0]),2)+pow((clrVal[1]-num[i][1]),2)+pow((clrVal[2]-num[i][2]),2));
            if(dist<min)
            {
                min=dist;
                idx=i;
            }
            else if(dist==min)
            {
                idx=-1;
            }
        }
        if(idx==-1)
        ans.push_back("Ambiguous");
        else ans.push_back(name[idx]);
    }
    
    return ans;
}

public List<String> subdomainVisits(String[] cpdomains) {
    List<String>res=new ArrayList<>();
    HashMap<String,Integer>map=new HashMap<>();
    for(int i=0;i<cpdomains.length;i++)
    {
        String str=cpdomains[i];
        int j=0,freq=0;
        StringBuilder sb=new StringBuilder();
        for(;j<str.length();j++)
        {
            if(str.charAt(j)!=' ')
                sb.append(str.charAt(j));
            else break;
        }
        
        String num=sb.toString();
        if(num.equals("")==false)
        freq=Integer.parseInt(sb.toString());
        map.put(str.substring(j+1),map.getOrDefault(str.substring(j+1),0)+freq);
        j++;
        for(;j<str.length();j++)
        {
            if(str.charAt(j)!='.')continue;
            String subD=str.substring(j+1);
            map.put(subD,map.getOrDefault(subD,0)+freq);
        }
    }
    
    for(String str:map.keySet())
    {
        String countSd=map.get(str)+" "+str;
        res.add(countSd);
    }
    
    return res;
}