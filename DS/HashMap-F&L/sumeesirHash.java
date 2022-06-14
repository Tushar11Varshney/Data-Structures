import java.util.Collection;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;

public class sumeesirHash {

    //438
    public boolean compare(int []smap,int []pmap)
    {
        for(int i=0;i<26;i++)
        {
            if(pmap[i]!=smap[i])
            return false;
        }
        
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {  
        ArrayList<Integer>result=new ArrayList<>();   //refrence arraylist ya list
                
        if(s.length()<p.length())return result;
        
        int []pmap=new int[26];
        for(int i=0;i<p.length();i++)
        {
            char ch=p.charAt(i);
            pmap[ch-'a']++;
        }
        
        int []smap=new int[26];
        for(int i=0;i<p.length();i++)
        {
            char ch=s.charAt(i);
            smap[ch-'a']++;
        }
        
        for(int i=p.length();i<s.length();i++)
        {
            boolean compareAns=compare(smap,pmap);
            if(compareAns)
                result.add(i-p.length());
            
            char ch=s.charAt(i-p.length());
            smap[ch-'a']--;
            
            ch=s.charAt(i);
            smap[ch-'a']++;
        }
        
        if(compare(smap,pmap))
            result.add(s.length()-p.length());
        
        return result;
    }

    public static boolean compare(HashMap<Character,Integer>smap,HashMap<Character,Integer>pmap)
    {
        for(char ch:smap.keySet())  //ya p bcz ek tym pr dono mein p jitne hi character hain
        {
            if(smap.get(ch)!=pmap.get(ch))
            return false;
        }
        
        return true;
    }

    public static void findAnagrams(String s, String p) {
        
        HashMap<Character,Integer>pmap=new HashMap<>();
        for(int i=0;i<p.length();i++)
        {
            char ch=p.charAt(i);
            pmap.put(ch,pmap.getOrDefault(ch,0)+1);
        }
        
        HashMap<Character,Integer>smap=new HashMap<>();
        for(int i=0;i<p.length();i++)
        {
            char ch=s.charAt(i);
            smap.put(ch,smap.getOrDefault(ch,0)+1);
        }
        
        int count=0;
        String str="";
        for(int i=p.length();i<s.length();i++)
        {
            if(compare(smap,pmap))
            {
                count++;
                str+=i-p.length()+" ";
            }
            
            char ch=s.charAt(i-p.length());
            if(smap.get(ch)==1)
            smap.remove(ch);
            else
            smap.put(ch,smap.get(ch)-1);
            
            ch=s.charAt(i);
            smap.put(ch,smap.getOrDefault(ch,0)+1);
        }
        
        if(compare(smap,pmap))
        {
            count++;
            str+=s.length()-p.length()+" ";
        }
        
        System.out.println(count);
        System.out.println(str);
    }

    //1347
    public static boolean areKAnagrams(String str1, String str2, int k) {
        if(str1.length()!=str2.length())   //sirf replace krna allowed hai not add insert remove
        return false;
        
        HashMap<Character,Integer>map=new HashMap<>();
        for(int i=0;i<str1.length();i++)
        {
            char ch=str1.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }    
        
        for(int i=0;i<str2.length();i++)
        {
            char ch=str2.charAt(i);
            if(map.containsKey(ch))
            {
                if(map.get(ch)==1)
                map.remove(ch);
                else
                map.put(ch,map.get(ch)-1);
            }
        }
        
        int count=0;
        for(char ch:map.keySet())
        count+=map.get(ch);
        
        if(count<=k)return true;
        else return false;
    }

    public static int[] anagramMappings(int[] arr1, int[] arr2) {
        HashMap<Integer,ArrayList<Integer>>map=new HashMap<>();
        
        for(int i=0;i<arr2.length;i++)
        {
            if(map.containsKey(arr2[i]))
                map.get(arr2[i]).add(i);
            else{
                map.put(arr2[i],new ArrayList<>());
                map.get(arr2[i]).add(i);
            }
        }
        
        int []result=new int[arr1.length];
        for(int i=0;i<arr1.length;i++)
        {
            result[i]=map.get(arr1[i]).get(0);
            map.get(arr1[i]).remove(0);
            if(map.get(arr1[i]).size()==0)
                map.remove(arr1[i]);
        }
        return result;
    }

    public static boolean validAnagrams(String s, String p){
        if(s.length()!=p.length())return false;
        
        HashMap<Character,Integer>pmap=new HashMap<>();
        for(int i=0;i<p.length();i++)
        {
            char ch=p.charAt(i);
            pmap.put(ch,pmap.getOrDefault(ch,0)+1);
        }
        
        // HashMap<Character,Integer>smap=new HashMap<>();
        // for(int i=0;i<s.length();i++)
        // {
        //     char ch=s.charAt(i);
        //     smap.put(ch,smap.getOrDefault(ch,0)+1);
        // }
        
        // return compare(smap,pmap);

        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(pmap.containsKey(ch)==false)
            return false;
            else if(pmap.get(ch)==1)
            pmap.remove(ch);
            else
            pmap.put(ch,pmap.get(ch)-1);
        }    

        return true;
    }

    public static ArrayList<ArrayList<String>> groupAnagrams(String[] strs) {
        HashMap<HashMap<Character,Integer>,ArrayList<String>>bigMap=new HashMap<>();
        
        for(int i=0;i<strs.length;i++)
        {
            String str=strs[i];
            HashMap<Character,Integer>fmap=new HashMap<>();
            for(int j=0;j<str.length();j++)
            {
                char ch=str.charAt(j);
                fmap.put(ch,fmap.getOrDefault(ch,0)+1); 
            }
            
            if(bigMap.containsKey(fmap)==false)
            {
                bigMap.put(fmap,new ArrayList<>());
                bigMap.get(fmap).add(str);
            }
            else{
                bigMap.get(fmap).add(str);
            }
        }
        
        ArrayList<ArrayList<String>>result=new ArrayList<>();
        for(HashMap<Character,Integer>map:bigMap.keySet())
        {
            result.add(bigMap.get(map));
        }
        
        return result;
    }

    // 49
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArr = str.toCharArray(); 
            Arrays.sort(charArr);                   
            String s = new String(charArr);
            map.putIfAbsent(s, new ArrayList<>());          
            map.get(s).add(str);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String str : map.keySet()) {
            ans.add(map.get(str));
        }

        return ans;
    }

    public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) {
        HashMap<String,ArrayList<String>>map=new HashMap<>();
        for(int i=0;i<strs.length;i++)
        {
            String str=strs[i];
            String key="";
            for(int j=0;j<str.length()-1;j++)
            {
                int val=str.charAt(j+1)-str.charAt(j);
                if(val<0)val+=26;
                key+=val+"#";
            }
            
            if(map.containsKey(key)==false)
                map.put(key,new ArrayList<>());
            
            map.get(key).add(str);
        }
        
        ArrayList<ArrayList<String>>result=new ArrayList<>();
        for(String str:map.keySet())
        {
            result.add(map.get(str));
        }
        return result;
    }

    //290
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character,String>map=new HashMap<>();
        HashSet<String>hs=new HashSet<>();
        String[] words= str.split(" ");
        
        if(pattern.length()!=words.length)return false;
        for(int i=0;i<pattern.length();i++)
        {
            String word=words[i];
            char letter=pattern.charAt(i);
            if(map.containsKey(letter) && (map.get(letter)).equals(word))continue;
            else if(hs.contains(word))return false;
            else if(map.containsKey(letter))return false;
            else {
                map.put(letter,word);
                hs.add(word);
            }    
        }
        
        return true;
    }

    public static boolean isIsomorphic(String s, String t) {
		HashMap<Character,Character>map=new HashMap<>();
        HashSet<Character>hs=new HashSet<>();
        
        if(s.length()!=t.length())return false;
        for(int i=0;i<s.length();i++)
        {
            char key=s.charAt(i);
            char value=t.charAt(i);
            
            if(map.containsKey(key) && (map.get(key)).equals(value))continue;
            else if(hs.contains(value))return false;
            else if(map.containsKey(key))return false;
            else {
                map.put(key,value);
                hs.add(value);
            }    
        }
        return true;
	}

    //==============
    public static boolean pairsWithEqualSum(int[] arr) { 
        int n = arr.length;
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];
                if (hs.contains(sum))
                    return true;
                hs.add(sum);
            }
        }
        return false;
    }

    public static int lengthOfLongestSubstringwithKDistinct(String s,int k)
    {
        int []arr=new int[128];
        int si = 0, ei = 0, len = 0, n = s.length(), distinctCount = 0;
        while (ei < n)
        {
            char ch=s.charAt(ei);ei++;
            if (arr[ch]++ == 0)
                distinctCount++;

            while (distinctCount > k)
            {
                char ch_=s.charAt(si);si++;
                if (arr[ch_]-- == 1)
                    distinctCount--;
            }

            if(distinctCount==k)
            len=Math.max(len,ei-si);
        }
        return len;
    }

    public static ArrayList<Integer> countDistinctinKLength(int[] arr, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        // int freqArr[]=new int[100000+1];  //1 <= arr[i] <= 10^4 this code works for +ve value
        int si = 0, ei = 0, distinctCount = 0;
        // while (ei - si < k) {
            // if(freqArr[arr[ei++]]++ == 0)
            // distinctCount++;
        // }
        // result.add(distinctCount);
    
        // while (ei < arr.length) {
            // if(freqArr[arr[si++]]-- == 1)
            // distinctCount--;
    
            // if(freqArr[arr[ei++]]++ == 0)
            // distinctCount++;
    
            // result.add(distinctCount);
        // }

        while (ei < arr.length) {    
            int eiVal = arr[ei++];
			if (map.containsKey(eiVal)) {
                map.put(eiVal, map.get(eiVal) + 1);
            } else {
                map.put(eiVal, 1);
                distinctCount++;
            }

			if(ei-si==k)
			{
				result.add(distinctCount);
				int siVal = arr[si++];
				map.put(siVal, map.get(siVal) - 1);
				if (map.get(siVal) == 0)
				{
					distinctCount--;
					map.remove(siVal);
				} 
			}
		}
        return result;
    }

    public static int Longest_Substring_NonRepeatingCharacter(String s) {
        if (s.length() <= 1)
            return s.length();
        int si = 0, ei = 0, count = 0, len = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (ei < s.length()) {
            char ch = s.charAt(ei);
            ei++;
            if (map.containsKey(ch) && map.get(ch) > 0)
                count++;
            map.put(ch, map.getOrDefault(ch, 0) + 1);
    
            while (count > 0) {
                char ch_ = s.charAt(si);
                si++;
                if (map.get(ch_) > 1)
                    count--;
                map.put(ch_, map.get(ch_) - 1);
            }
    
            len = Math.max(len, ei - si);  //count+=ei-si
        }
        return len;
    }

    public static int Count_Substring_Unique_Characters(String str) {
        int si = 0, ei = 0, count = 0, n = str.length();
        HashMap<Character, Integer> map = new HashMap<>();
        while (ei < n) {
            char ch = str.charAt(ei);
            ei++;
            map.put(ch,map.getOrDefault(ch,0)+1);
            while (map.get(ch) > 1) {
                char ch_ = str.charAt(si);
                map.put(ch_, map.get(ch_) - 1);
                si++;
            }
            count += ei - si;
    }

    public static int Longest_At_Most_KUnique_Characters(String s, int k) {
        int si=0,ei=0,n=s.length(),len=0;
        HashMap<Character,Integer>map=new HashMap<>();
    
        while(ei<n)
        {
            char ch=s.charAt(ei);ei++;
            if(map.containsKey(ch))
                map.put(ch,map.get(ch)+1);
            else{
                map.put(ch,1);
                k--;
            }
            
            while(k<0)
            {
                char ch_=s.charAt(si);si++;
                if(map.get(ch_)==1)
                {
                    k++;
                    map.remove(ch_);
                }
                else    
                map.put(ch_,map.get(ch_)-1);
            }
            
            len=Math.max(len,ei-si);
        }
        return len;
    }
    
    public static int Count_At_Most_KUnique_Characters(String s, int k) {
        int si=0,ei=0,n=s.length(),count=0;
        HashMap<Character,Integer>map=new HashMap<>();

        while(ei<n)
        {
            char ch=s.charAt(ei);ei++;
            if(map.containsKey(ch))
                map.put(ch,map.get(ch)+1);
            else{
                map.put(ch,1);
                k--;
            }
            
            while(k<0)
            {
                char ch_=s.charAt(si);si++;
                if(map.get(ch_)==1)
                {
                    k++;
                    map.remove(ch_);
                }
                else    
                map.put(ch_,map.get(ch_)-1);
            }
            count+=ei-si;
        }
        return count;
    }
    
    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
    
        int requirement = t.length(), si = 0, ei = 0, len = (int) 1e8, head = 0;
        while (ei < s.length()) {
            char ch = s.charAt(ei);
            ei++;
    
            if (map.getOrDefault(ch, 0) > 0)
                requirement--;
            map.put(ch, map.getOrDefault(ch, 0) - 1);
    
            while (requirement == 0) {
                if (len > ei - si) {
                    len = ei - si;
                    head = si;
                }
    
                char ch_ = s.charAt(si);
                si++;
                if (map.get(ch_) == 0)
                    requirement++;
                map.put(ch_, map.get(ch_) + 1);
            }
        }
        return len == (int) 1e8 ? "" : s.substring(head, head + len);
    }
    
    public static int smallestWindow(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, 1);
        }
        int requirement = map.size(), si = 0, ei = 0, len = (int) 1e8;  
        while (ei < s.length()) {
            char ch = s.charAt(ei);
            ei++;
            if (map.getOrDefault(ch, 0) > 0)
                requirement--;
            map.put(ch, map.getOrDefault(ch, 0) - 1);
    
            while (requirement == 0) {
                if (len > ei - si) {
                    len = ei - si;
                }
    
                char ch_ = s.charAt(si);
                si++;
                if (map.get(ch_) == 0)
                    requirement++;
                map.put(ch_, map.get(ch_) + 1);
            }
        }
        return len;
    }

    public static int count_and_length_SubArray012(int[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
        int Zcount = 0, Ocount = 0, tCount = 0, count = 0;
    
        String str = (Ocount - Zcount) + "#" + (tCount - Ocount);
        map.put(str, 1);
        //map.put(str,-1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1)
                Ocount++;
            else if (arr[i] == 2)
                tCount++;
            else
                Zcount++;
    
            str = (Ocount - Zcount) + "#" + (tCount - Ocount);
            if (map.containsKey(str))
                count += map.get(str);
            map.put(str, map.getOrDefault(str, 0) + 1);
    
            //if(map.containsKey(str))      //for length
            //len=Math.max(len,i-map.get(str));
            //else map.put(str,i);
        }
    
        return count;
    }

    public static int largestSubArraySumZero(int[] arr) {
        int max = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum))
                max = Math.max(max, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return max;
    }
    
    public static int CountSubArraySumZero(int[] arr) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum))
                count += map.get(sum);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static int Largest_Subarray_Contiguous_Elements(int[] arr) {
        int len = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            int max = arr[i], min = arr[i];
            HashSet<Integer> hs = new HashSet<>();
            hs.add(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                if (hs.contains(arr[j]))
                    break;
                else
                    hs.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                
                if(max-min+1>arr.length)break;  //[7 8 12 6 9] max=12,min=7 6>5
                if (max - min == j - i)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    public static int CountofEqSubArray(int[] arr) {
        // count of unique integers in the subarray = count of unique integers in the given array.
        HashSet<Integer> hs = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int si = 0, ei = 0, count = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            hs.add(arr[i]);
        }
    
        while (ei < n) {
            map.put(arr[ei], map.getOrDefault(arr[ei], 0) + 1);
            ei++;
    
            while (map.size() == hs.size()) {
                count += n - ei + 1;
                if (map.get(arr[si]) == 1)
                    map.remove(arr[si]);
                else
                    map.put(arr[si], map.get(arr[si]) - 1);
                si++;
            }
        }
        return count;
    }
    
    public static void smallestSubArrayWithMostFreqElement(int[] arr) {
        
        HashMap<Integer,Integer>Freqmap=new HashMap<>();
        HashMap<Integer,Integer>indexMap=new HashMap<>();
        
        int hf=1,sp=0,ep=0,len=1,element=arr[0];
        Freqmap.put(arr[0],1);
        indexMap.put(arr[0],0);
        
        for(int i=1;i<arr.length;i++)
        {
            int f=Freqmap.getOrDefault(arr[i],0)+1;
            if(f==1)
            {
                Freqmap.put(arr[i],1);
                indexMap.put(arr[i],i);
            }
            else{
                Freqmap.put(arr[i],f);
                
                if(f>hf)
                {
                    element=arr[i];
                    hf=f;
                    sp=indexMap.get(arr[i]);
                    ep=i;
                    len=ep-sp+1;
                }
                else if(f==hf)
                {
                    if(i-indexMap.get(arr[i])+1<len)
                    {
                        sp=indexMap.get(arr[i]);
                        ep=i;
                        len=ep-sp+1;
                    }
                }
                
            }
        }
        
        System.out.println(element);
        System.out.println(sp);
        System.out.println(ep);
    }

// 380
// in question it is given that atleast one element will be there when getrandom
// will be called so we need not apply any exception when no element will be
// present.we need to store index because random fn generate value btw a range
// or 0to1 and we cannot know value present or not directly so we need to store
// index.
    class RandomizedSet {
        HashMap<Integer, Integer> map;
        ArrayList<Integer> list;
        Random rand;  

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if (!map.containsKey(val)) {
                map.put(val, list.size()); // phle map.put
                list.add(val);

                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int cidx = map.get(val);
                int lastValue = list.get(list.size() - 1);
                list.set(cidx, lastValue);

                list.remove(list.size() - 1);
                map.put(lastValue, cidx); // phle put dry run for given test case.
                map.remove(val);
                return true;
            }
            return false;
        }

        public int getRandom() {
            int randomValue = rand.nextInt(list.size()); // 0-5 mein 5 exclusive 
            return list.get(randomValue);
        }
    }

    class RandomizedSet2 { // without list time increases
        HashMap<Integer, Integer> map;
        int count = 0;
        Random rand;

        public RandomizedSet2() {
            map = new HashMap<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if (!map.containsKey(val)) {
                map.put(val, count);
                count++;
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int cidx = map.get(val);
                int lastIdx = map.size() - 1;
                for (int key : map.keySet()) {
                    if (map.get(key) == lastIdx) {
                        map.put(key, cidx);
                        break;
                    }
                }

                map.remove(val);
                count--;
                return true;
            }

            return false;
        }

        public int getRandom() {
            int randomValue = rand.nextInt(map.size());
            for (int key : map.keySet()) {
                if (map.get(key) == randomValue)
                    return key;
            }
            return -1;
        }
    }

    // 138 with hashmap space
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val);
            map.put(curr, newNode);

            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copyNode = map.get(curr);

            copyNode.next=curr.next!=null?map.get(curr.next):null;
            copyNode.random=curr.random!=null?map.get(curr.random):null;

            curr = curr.next;
        }

        return map.get(head);
    }
   
    //1046
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
            return b-a;
        });
        for(int s:stones)
            pq.add(s);
        
        while(pq.size()>1)
        {
            int s1=pq.poll(),s2=pq.poll();
            if(s1-s2!=0)pq.add(s1-s2);
        }
        return pq.size()==0?0:pq.peek();
    }

    //215
    int findKthLargest(vector<int> &nums, int k)
    {
        priority_queue<int, vector<int>, greater<int>> pq;   //min pq
        for (int ele : nums)
        {
            pq.push(ele);
            if (pq.size() > k)
                pq.pop();
        }
        return pq.top();

        //  PriorityQueue<Integer> pq = new PriorityQueue<>(); // k largest mein aesa sochlo 4 sabse acche batsman chahhie then we want ki humesha min waale log bahar niklte rhein and that is what min pq do.
        //     for (int val : arr) {
        //         pq.add(val);
        //         if (pq.size() > k)
        //             pq.remove();
        //     }
        //     while (pq.size() > 0) {
        //         System.out.println(pq.peek());
        //         pq.remove();
        //     }
    }

    //703
    class KthLargest
    {
    public:
        priority_queue<int, vector<int>, greater<int>> pq;
        int K;
        KthLargest(int k, vector<int> &nums)
        {
            this->K = k;
            for (int ele : nums)
            {
                pq.push(ele);
                if (pq.size() > k)
                    pq.pop();
            }
        }

        int add(int val)
        {
            pq.push(val);
            if (pq.size() > K)
                pq.pop();
            return pq.top();
        }
    };

    // 378
    
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int r1 = a / n;
            int c1 = a % n;
            int r2 = b / n;
            int c2 = b % n;

            return matrix[r1][c1] - matrix[r2][c2];
        });
        for (int i = 0; i < n; i++)
            pq.add(i * n + 0);

        while (k-- > 1) {
            int idx = pq.remove();
            int r = idx / n;
            int c = idx % n;
            if (c + 1 < n)
                pq.add(r * n + (c + 1));
        }

        int kthSmallestIdx = pq.peek();
        return matrix[kthSmallestIdx / n][kthSmallestIdx % n];
    }

    //347
    vector<int> topKFrequent(vector<int> &nums, int k)
    {
        unordered_map<int, int> map;
        for (int ele : nums)
            map[ele]++;

        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; //min pq

        for (pair<int, int> key : map)
        {
            pq.push({key.second, key.first}); //pehle frequency then key
            if (pq.size() > k)
                pq.pop();
        }

        vector<int> ans;
        while (k > 0)
        {
            ans.push_back(pq.top().second);
            pq.pop();
            k--;
        }
        return ans;
    }

    public static void sortKSortedArray(int n, int arr[], int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : arr) {
            pq.add(val);
            if (pq.size() > k) {
                System.out.println(pq.peek());
                pq.remove();
            }
        }
        while (pq.size() > 0) {
            System.out.println(pq.peek());
            pq.remove();
        }
    }

    public static class Pair implements Comparable<Pair> { 
        int li;
        int di;
        int val;

        Pair(int li, int di, int val) {
            this.li = li;
            this.di = di;
            this.val = val;
        }

        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++)
            pq.add(new Pair(i, 0, lists.get(i).get(0)));

        while (pq.size() > 0) {
            Pair p = pq.remove();
            result.add(p.val);
            p.di++;

            if (p.di < lists.get(p.li).size()) {
                Pair r = new Pair(p.li, p.di, lists.get(p.li).get(p.di));
                pq.add(r);
            }
        }
        return result;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0)return null;
        PriorityQueue<ListNode>pq=new PriorityQueue<>((a,b)->{
           return a.val-b.val; 
        });
        
        for(int i=0;i<lists.length;i++)
        {   
            if(lists[i]!=null)
            pq.add(lists[i]);
        }
        
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy;
        
        while(pq.size()!=0)
        {
            ListNode node=pq.poll();
            if(node.next!=null)
                pq.add(node.next);
            
            prev.next=node;
            prev=node;
        }
        return dummy.next;
    }

    //349
    vector<int> intersection(vector<int> &nums1, vector<int> &nums2)
    {
        unordered_map<int, int> map;
        for (int ele : nums1)
        {
            map[ele] = 1;
        }
        vector<int> res;
        for (int ele : nums2)
        {
            if (map.find(ele) != map.end())
            {
                res.push_back(ele);
                map.erase(ele);
            }
        }
        return res;

        // HashMap<Integer, Integer> map = new HashMap<>();
        //     for (int i = 0; i < n1; i++) {
        //         //dont do old frequency+1 because not needed in this qs
        //         map.put(arr1[i], 1);
        //     }

        //     for (int i = 0; i < n2; i++) {
        //         if (map.containsKey(arr2[i])) {
        //             System.out.println(arr2[i]);
        //             map.remove(arr2[i]);
        //         }
        // }
    }

    //350
    vector<int> intersection(vector<int> &nums1, vector<int> &nums2)
    {
        unordered_map<int, int> map;
        for (int ele : nums1)
        {
            map[ele]++;
        }
        vector<int> res;
        for (int ele : nums2)
        {
            if (map.find(ele) != map.end())
            {
                res.push_back(ele);
                map[ele]--;
                if (map[ele] == 0)
                    map.erase(ele);
            }
        }
        return res;

        //         HashMap<Integer, Integer> map = new HashMap<>();
        //         List<Integer>ans=new ArrayList<>();
        //         int n1=arr1.length,n2=arr2.length;
        //         for (int i = 0; i < n1; i++) {
        //             map.put(arr1[i],map.getOrDefault(arr1[i],0)+1);
        //         }

        //         for (int i = 0; i < n2; i++) {
        //             if (map.containsKey(arr2[i])) {
        //                 ans.add(arr2[i]);
        //                 map.put(arr2[i],map.get(arr2[i])-1);
        //                 if(map.get(arr2[i])==0)
        //                     map.remove(arr2[i]);
        //             }
        //         }
                
        //         int []res=new int[ans.size()];
        //         for(int i=0;i<ans.size();i++)
        //         {
        //             res[i]=ans.get(i);
        //         }
                
        //         return res;
    }

    //128
    int longestConsecutive(vector<int> &nums)
    {
        unordered_map<int, int> map;
        for (int ele : nums)
            map[ele] = 1;

        int len = 0;
        for (int ele : nums)
        {
            if (map.find(ele) == map.end())
                continue;

            int num = ele;
            int prev = ele - 1;
            int next = ele + 1;

            while (map.find(prev) != map.end())
            {
                map.erase(prev);
                prev--;
            }

            while (map.find(next) != map.end())
            {
                map.erase(next);
                next++;
            }

            map.erase(num);
            len = max(len, next - prev - 1);
        }

        return len;
    }

    /*public static void longestConsecutiveSeq(int arr[], int n) throws Exception {
            HashMap<Integer, Boolean> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(arr[i], true);
            }

            for (int i = 0; i < n; i++) {
                if (map.containsKey(arr[i] - 1))
                    map.put(arr[i], false);
            }

            int maxLcs = 0, sp = 0, ep = 0;
            for (int i : arr) {
                if (map.get(i) == true) {
                    int j = i + 1;
                    while (map.containsKey(j))
                        j = j + 1;
                    if (j - i > maxLcs) {
                        maxLcs = j - i;
                        sp = i;
                        ep = j - 1;
                    }
                }
            }

            for (int i = sp; i <= ep; i++)
                System.out.println(i);
    } */

    // https://www.pepcoding.com/resources/online-java-foundation/hashmap-and-heap/hfc-official/ojquestion
    public static void maxfreqchar(String str)
    {
        HashMap<Character,Integer>map=new HashMap<>();
        char mfc=str.charAt(0);
        int maxFreq=0;
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
            if(map.get(ch)>maxFreq)
            {
                maxFreq=map.get(ch);
                mfc=ch;
            }
        }

        System.out.println(mfc);
    }

    public static boolean arithmetic_Seq(int[] arr) {
        if(arr.length<=1)return true;
        HashSet<Integer>hs=new HashSet<>();
        int min=arr[0],max=arr[0];
        for(int i=0;i<arr.length;i++)
        {
            min=Math.min(min,arr[i]);
            max=Math.max(max,arr[i]);
            
            hs.add(arr[i]);
        }

        int d=(max-min)/(arr.length-1);
        int term=min;
        for(int i=0;i<arr.length;i++)  //while(min<=max) fail in [0000]
        {
            if(!hs.contains(term))return false;
            term+=d;
        }

        return true;
    }

    //217
    bool containsDuplicate(vector<int> &nums)
    {
        return nums.size() > set(nums.begin(), nums.end()).size();  
    }

    bool containsDuplicate(vector<int> &nums)
    {
        unordered_map<int, int> map;
        for (int ele : nums)
        {
            if (map[ele] == 0)
                map[ele] = 1;
            else
                return true;
        }
        return false;
    }

    //219
    // nums[i] == nums[j] and abs(i - j) <= k.
    bool containsNearbyDuplicate(vector<int>& nums, int k) { 
        unordered_map<int,int>map;
        for(int i=0;i<nums.size();i++)
        {
            int ele=nums[i];
            if(map[ele]==0) //i+1 because 1st element kelie tou 0 hi hoga...[1,2,3,1] 3
                map[ele]=i+1;
            else if( (i+1) - map[ele] <= k)
                return true;
            else 
                map[ele]=i+1;
        }
        return false;
    }

    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        unordered_map<int,int>map;
        for(int i=0;i<nums.size();i++)
        {
            int ele=nums[i];
            if(map.find(ele)==map.end())  //slow because of this O(n)
                map[ele]=i;
            else if( i - map[ele] <= k)
                return true;
            else map[ele]=i;
        }
        return false;
    }

    // 1817
    // beekaar array ki jgah hashset rkho because array mein unique hai ya ni linear search chalan pdega so do it using hashset wo kisi duplicate ko ni aanedega.
    //Return 1 indexed array->0th idx pr kitne logon ka UAM 1 Hai
    public int[] findingUsersActiveMinutes2(int[][] logs, int k) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int[] result = new int[k];
        for (int i = 0; i < logs.length; i++) {
            int userId = logs[i][0];
            int activityMinute = logs[i][1];
            if (!map.containsKey(userId)) {
                map.put(userId, new HashSet<>());
            }
            map.get(userId).add(activityMinute);
        }

        Set<Integer> keyset = map.keySet();
        for (int ele : keyset) {
            int size = map.get(ele).size();
            result[size - 1]++;
        }

        return result;
    }

    // 895
    class FreqStack {

        HashMap<Integer, Integer> map; // number vs frequency
        ArrayList<Stack<Integer>> list;
        int maxFreq = 0;

        public FreqStack() {
            map = new HashMap<>();
            list = new ArrayList<>();

            list.add(new Stack<>()); // dummy stack
        }

        public void push(int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(x));

            if (maxFreq == list.size())
                list.add(new Stack<>());
            list.get(map.get(x)).add(x);
        }

        public int pop() {
            int value = list.get(maxFreq).pop();
            if (list.get(maxFreq).size() == 0) {
                list.remove(maxFreq);
                maxFreq--;
            }
            map.put(value, map.get(value) - 1);
            if (map.get(value) == 0)
                map.remove(value);
            return value;
        }
    }

    public static int firstIndex(int [][]num,int target)
    {
        int n=num.length,idx=-1,si=0,ei=n*n-1;
        while(si<=ei)
        {
            int mid=(si+ei)/2;
            int r=mid/n,c=mid%n;
            if(num[r][c]==target)  
            {
                ei=mid-1;  //2d matrix cant do like mid-1>=0
                idx=mid;
            }
            else if(num[r][c]>target)
            {
                ei=mid-1;
            }
            else{
                si=mid+1;
            }
        }
        return idx;
    }

    public static int lastIndex(int [][]num,int target)
    {
        int n=num.length,idx=-1,si=0,ei=n*n-1;
        while(si<=ei)
        {
            int mid=(si+ei)/2;
            int r=mid/n,c=mid%n;
            if(num[r][c]==target)
            {
                si=mid+1;
                idx=mid;
            }
            else if(num[r][c]>target)
            {
                ei=mid-1;
            }
            else{
                si=mid+1;
            }
        }
        
        return idx;
    }

    public static int pairWithSumIn2SortedMatrix_(int[][] num1, int[][] num2, int target) {
        int count=0;
        int n=num1.length;
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                int fv=firstIndex(num1,target-num2[i][j]);
                if(fv==-1)continue;
                
                int lv=lastIndex(num1,target-num2[i][j]);
                
                count+=lv-fv+1;
            }
        }     
        
        return count;
    }

    public static int pairWithSumIn2SortedMatrix(int[][] num1, int[][] num2, int target) {  //doesnt matter matrix sorted or not o(n^2)
        int count=0;
        int n=num1.length;
        HashMap<Integer,Integer>hmap=new HashMap<>();
        for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
        hmap.put(num1[i][j],hmap.getOrDefault(num1[i][j],0)+1);
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(hmap.containsKey(target-num2[i][j]))
                    count+=hmap.get(target-num2[i][j]);   
            }
        }     
        return count;
    }

    public static void completeTask(int n, int m, int[] arr) {
        HashSet<Integer>hs=new HashSet<>();
        for(int i=0;i<arr.length;i++)
        hs.add(arr[i]);

        int turn=0;
        String s1="",s2="";
        for(int i=1;i<=n;i++) 
        {
            if(hs.contains(i))continue;
            
            if(turn==0)
            s1+=i+" ";
            else
            s2+=i+" ";
            
            turn=(turn+1)%2;
        }

        System.out.println(s1);
        System.out.println(s2);
    }

    public static void findPathfromtickets(HashMap<String, String> map) {
        HashMap<String,Boolean>m=new HashMap<>();  //m:gives source
        for(String str:map.keySet())  //map given in ques
        {
            String d=map.get(str);   //d->dstn
            if(!m.containsKey(str))
            m.put(str,true);
            m.put(d,false);
        }
        
        String src="";
        for(String str:map.keySet())
        {
            if(m.get(str))
            {
                src=str;
                break;
            }
        }
        
        while(true)
        {
            if(map.containsKey(src))
            {
                System.out.print(src+" -> ");
                src=map.get(src);
            }
            else break;
        }
        System.out.print(src+".");
    }

    public static boolean ArrayDivideInPair(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 0; i < arr.length; i++) {
            int rem = arr[i] % k;
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        if (map.get(0) % 2 != 0)
            return false;
        else
            map.remove(0);
        if (k % 2 == 0) {  //k=11 agr 5 h ek rem then agla 6 hona chahiye
            if (map.containsKey(k / 2) && map.get(k / 2) % 2 != 0)
                return false;
            else
                map.remove(k / 2);
        }

        for (Integer rem : map.keySet()) {
            int remainingRem = k - rem;
            if (map.get(rem) != map.get(remainingRem))
                return false;
        }
        return true;
    }

    // 295
    public static class MedianPriorityQueue { // median is middle number is ascending list.
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        public void rebalance() {
            if (maxPq.size() - minPq.size() == 2)
                minPq.add(maxPq.remove());
            else if (maxPq.size() < minPq.size())
                maxPq.add(minPq.remove());
        }

        public void add(int num) {
            if (maxPq.size() == 0 || num <= maxPq.peek())
                maxPq.add(num);
            else
                minPq.add(num);

            rebalance();
        }

        public int remove() {
            int val = maxPq.remove();
            rebalance();
            return val;
        }

        public double findMedian() {

            if ((maxPq.size() + minPq.size()) % 2 == 0)
                return (maxPq.peek() + minPq.peek()) / 2.0;
            else
                return maxPq.peek();
        }

        public int size() {
            return maxPq.size() + minPq.size();
        }
    }

    // 407
    public int trapRainWater(int[][] heightMap) {

        if (heightMap.length == 0 || heightMap[0].length == 0)
            return 0;
        int n = heightMap.length;
        int m = heightMap[0].length;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return heightMap[a / m][a % m] - heightMap[b / m][b % m];
        });
        int[][] visited = new int[n][m];

        for (int i = 0; i < n; i++) { // boundary pr hum water store ni kra skte so we will shrink boundary and if boundary height is greater than beech mein jo building hai then we can store  water
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    pq.add(i * m + j);
                    visited[i][j] = 1;
                }
            }
        }

        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }; // ek building in chaar direction mein hi support deskti hai water fill krane kelei.
        int criticalHeight = 0; // critical height ka matlab mere aas pass jitni boundary hai un sabki min height.agar critical height 1 hai aur element jo pq se nikla hai wo bhi 1 hai then
        // critical height se upar aur brabar paani bhrna start kraa then paani beh jaayga saara.
        int totalwater = 0;
        while (pq.size() > 0) {
            int idx = pq.poll();
            int r = idx / m;
            int c = idx % m;

            if (criticalHeight > heightMap[r][c])
                totalwater += criticalHeight - heightMap[r][c];
            criticalHeight = Math.max(criticalHeight, heightMap[r][c]);
            for (int i = 0; i < dirs.length; i++) {
                int x = r + dirs[i][0];
                int y = c + dirs[i][1];

                if (x >= 0 && y >= 0 && x < n && y < m && visited[x][y] == 0) {
                    pq.add(x * m + y);
                    visited[x][y] = 1;
                }
            }
        }
        return totalwater;
    }

    public static int getSize(HashMap<String, HashSet<String>> tree, HashMap<String, String> map,
                HashMap<String, Integer> result, String key) {
            if (tree.containsKey(key) == false) {
                result.put(key, 0);
                return 1;
            }

            int sz = 0;
            for (String child : tree.get(key)) {
                sz += getSize(tree, map, result, child);
            }
            result.put(key, sz);
            return sz + 1;
    }

    //map is given 
    public static void NumberofEmployeeUnderEveyManager(HashMap<String, HashSet<String>> tree, HashMap<String, String> map) {

            String ceo = "";
            for (String emp : map.keySet()) {
                String man = map.get(emp);
                if (emp.equals(man))
                    ceo = man;
                else {
                    if (tree.containsKey(man)) {
                        tree.get(man).add(emp);
                    } else {
                        tree.put(man, new HashSet<>());
                        tree.get(man).add(emp);
                    }
                }
            }

            HashMap<String, Integer> result = new HashMap<>();
            getSize(tree, map, result, ceo);
            for (String emp : result.keySet()) {
                System.out.println(emp + " " + result.get(emp));
            }
    }

    //387
    public int firstUniqChar(String s) {  //or do using array
        HashMap<Character,Integer>map=new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
            
        for(int i=0;i<s.length();i++) //cant travel on map dont store in ordered way
        {
            char ch=s.charAt(i);
            if(map.get(ch)==1)
                return i;
        }    
        
        return -1;
    }

    //1748
    public int sumOfUnique(int[] nums) {
        HashMap<Integer,Integer>map=new HashMap<>();
        int sum=0;
        for(int i=0;i<nums.length;i++)
        {
            if(map.containsKey(nums[i]) && map.get(nums[i])>1)continue;
            else if(map.containsKey(nums[i]))
            {
                sum-=nums[i];
                map.put(nums[i],map.get(nums[i])+1);
            }
            else{
                sum+=nums[i];
                map.put(nums[i],1);
            }
        }
        return sum;
    }

    //166 leetcode
    //-1,-2147483648(iska abs int se bhaar) take long
    public String fractionToDecimal(int n, int d) {
        long num=Math.abs((long)n);
        long den=Math.abs((long)d);
        
        long q = num / den;
        long r = num % den;  //long lena pdega idhr bhi hashmap bhi else conversion prob
        
        StringBuilder ans = new StringBuilder();
        ans.append(q);
        
        if(n<0 && d>0 || d<0 && n>0)
            ans.insert(0,"-");
        
        if (r == 0) {
            return ans.toString();
        } 
        else {
            HashMap<Long, Integer> map = new HashMap<>(); 
            ans.append(".");
            while (r != 0) {
                if (map.containsKey(r)) {
                    int len = map.get(r);
                    ans.insert(len, "(");
                    ans.append(")");
                    break;
                } else {
                    r *= 10;
                    map.put(r, ans.length());
                    q = r / den;
                    r = r % den;
                    ans.append(q);
                }
            }
        }
        
        return ans.toString();
    }

    //692
    class pair{
        String str;
        int f;
        
        pair(String str,int f)
        {
            this.str=str;
            this.f=f;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        PriorityQueue<pair>pq=new PriorityQueue<>((a,b)->{
        if(a.f==b.f)
        {
            int i=0,j=0;
            String as=a.str,bs=b.str;
            while(i<as.length() && j<bs.length())
            {
                if(as.charAt(i)<bs.charAt(j))return 1;
                else if(as.charAt(i)>bs.charAt(j))return -1;
                else {
                    i++;j++;
                }
            }
            if(as.length()<bs.length())return 1;  //aa aaa
            else return -1;
        }
        return a.f-b.f; 
        });
        
        List<String>ans=new ArrayList<>();
        HashMap<String,Integer>map=new HashMap<>();
        for(int i=0;i<words.length;i++)
            map.put(words[i],map.getOrDefault(words[i],0)+1);
        
        //top k freq acco to lexo order
        for(String str:map.keySet())
        {
            pq.add(new pair(str,map.get(str)));
            if(pq.size()>k)pq.poll();
        }
        
        //arrange freq in desc
        TreeMap<Integer,ArrayList<String>>smap=new TreeMap<>((a,b)->{
        return b-a;  
        });
        
        
        //freq->[str list]
        while(pq.size()!=0)
        {
            pair p=pq.poll();
            int f=p.f;
            if(!smap.containsKey(f))
                smap.put(f,new ArrayList<>());
            smap.get(f).add(p.str); // 1->[i,love]
        }
        
        for(Integer i:smap.keySet())
        {
            ArrayList<String>al=smap.get(i);
            while(al.size()!=0)
            ans.add(al.remove(al.size()-1));
        }

        return ans;
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<PriorityQueue<String>>freq=new ArrayList<>();
        
        for(int i=0;i<words.length+1;i++)
            freq.add(new PriorityQueue<String>());
        
        List<String>ans=new ArrayList<>();
        HashMap<String,Integer>map=new HashMap<>();
        for(String str:words)
            map.put(str,map.getOrDefault(str,0)+1);
        
        for(String str:map.keySet())
        {
            int f=map.get(str);
            freq.get(f).add(str);
        }
        
        for(int i=freq.size()-1;i>=0;i--)
        {
            PriorityQueue<String> pq=freq.get(i);
            if(pq.size()!=0)
            {
                while(pq.size()!=0)
                {
                    if(k==0)return ans;
                    k--;
                    ans.add(pq.poll());
                }
            }
        }
        return ans;
    }

    // leetcode 706-we have to take a fixed size list vrna bucket index change hota rhega
    class MyHashMap {

        private class HMNode {
            int key;
            int value;

            HMNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size;
        private LinkedList<HMNode>[] buckets;

        public MyHashMap() {
            initbuckets(4);
            size = 0;
        }

        private void initbuckets(int N) {
            buckets = new LinkedList[N];
            for (int bi = 0; bi < buckets.length; bi++) {
                buckets[bi] = new LinkedList<>();
            }
        }

        public int size() {
            return size;
        }

        private int hashfn(int key) {
            int hashcode = key % 10;
            return Math.abs(hashcode) % buckets.length;
        }

        public void put(int key, int value) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di);
                node.value = value;
            } else {
                HMNode node = new HMNode(key, value);
                buckets[bi].add(node);
                size++;
            }

            double lambda = size() * 1.0 / buckets.length;
            if (lambda > 2.0)
                rehash();
        }

        private int getIndexWithinBucket(int Key, int bi) {
            int di = 0;
            for (HMNode node : buckets[bi]) {
                if (node.key == Key)
                    return di;
                di++;
            }
            return -1;
        }

        private void rehash() {
            LinkedList<HMNode>[] oba = buckets;
            initbuckets(oba.length * 2);
            size = 0;
            for (int i = 0; i < oba.length; i++) {
                for (HMNode node : oba[i])
                    put(node.key, node.value);
            }
        }

        public int get(int key) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di);
                return node.value;
            } else
                return -1;
        }

        public void remove(int key) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                buckets[bi].remove(di);
                size--;
            }
        }

    }

    public static class HashMap<K, V> {
        private class HMNode {
            K key;
            V value;

            HMNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size; // n
        private LinkedList<HMNode>[] buckets; // N = buckets.length    //Array of LinkedList

        public HashMap() {
            initbuckets(4);
            size = 0;
        }

        private void initbuckets(int N) {
            buckets = new LinkedList[N];
            for (int bi = 0; bi < buckets.length; bi++) {
                buckets[bi] = new LinkedList<>();
            }
        }

        public void put(K key, V value) {
            int bi = hashfn(key); // bucketIndex
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di); // updation
                node.value = value;
            } else {
                HMNode node = new HMNode(key, value); // insertion
                buckets[bi].add(node);
                size++;
            }

            double lambda = size() * 1.0 / buckets.length; // rehashing
            if (lambda > 2.0)
                rehash();
        }

        private int hashfn(K key) { // gives bucketIndex
            int hashcode = key.hashCode();
            return Math.abs(hashcode) % buckets.length; // hashCode can be negative
        }

        private int getIndexWithinBucket(K Key, int bi) { // gives data index
            int di = 0;
            for (HMNode node : buckets[bi]) {
                if (node.key.equals(Key))
                    return di;
                di++;
            }
            return -1;
        }

        private void rehash() {
            LinkedList<HMNode>[] oba = buckets; // oba-old bucket array
            initbuckets(oba.length * 2);
            size = 0;
            for (int i = 0; i < oba.length; i++) {
                for (HMNode node : oba[i])
                    put(node.key, node.value);
            }
        }

        public V get(K key) throws Exception {
            int bi = hashfn(key); // bucketIndex
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di);
                return node.value;
            } else
                return null;
        } 

        public boolean containsKey(K key) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1)
                return true;
            else
                return false;
        }

        public V remove(K key) throws Exception {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].remove(di);
                size--;
                return node.value;
            } else
                return null;
        }

        public ArrayList<K> keyset() throws Exception {
            ArrayList<K> keys = new ArrayList<>();

            for (int i = 0; i < buckets.length; i++) {
                for (HMNode node : buckets[i])
                    keys.add(node.key);
            }

            return keys;
        }

        public int size() {
            return size;
        }
    }

    //705
    class MyHashSet {
        
        List<Integer>[]map;
        int totalValues;
        
        public void initialiseList(int n,List<Integer>[]map)
        {
            for(int i=0;i<n;i++)
                map[i]=new ArrayList<>();
            totalValues=0;
        }
        
        public MyHashSet() {
            map=new ArrayList[4];
            initialiseList(4,map);
        }
        
        public int getBucketIndex(int key,List<Integer>[]map)
        {
            int bi=Integer.hashCode(key);
            return Math.abs(bi)%map.length;
        }
            
        public int getDataIndex(int key,int bi)
        {
            int idx=-1;
            for(int i=0;i<map[bi].size();i++)
            {
                if(key==map[bi].get(i))
                {
                    idx=i;
                    break;
                }
            }
            return idx;
        }
        
        public void add(int key) {
            int bi=getBucketIndex(key,map);
            int di=getDataIndex(key,bi);
            if(di==-1)
            {
                map[bi].add(key);
                totalValues++;
            }
            else
                map[bi].set(di,key);
            
            if(reHash()>2.0)        
                reassign();
        }
        
        public double reHash()
        {
            return (totalValues*1.0)/map.length;    
        }
        
        public void reassign()
        {
            List<Integer>[]newMap=new ArrayList[2*map.length];
            initialiseList(2*map.length,newMap);
            for(int i=0;i<map.length;i++)
            {
                for(int j=0;j<map[i].size();j++)
                {
                    int key=map[i].get(j);
                    int bi=getBucketIndex(key,newMap);
                    newMap[bi].add(key);
                }
            }
            map=newMap;
        }
        
        public void remove(int key) {
            int bi=getBucketIndex(key,map);
            int di=getDataIndex(key,bi);
            if(di!=-1)
                map[bi].remove(di);
        }
        
        public boolean contains(int key) {
            int bi=getBucketIndex(key,map);
            int di=getDataIndex(key,bi);
            if(di==-1)
                return false;
            return true;
        }
    }

//========
/*
static class pair{
    int a=0;
    String s="";
    
    pair(int a,String s)
    {
        this.a=a;
        this.s=s;
    }
    
    @Override
    public int hashCode()   //taaki equal waale same bucket mein jaaye..iske bina sb agl alg bucket mein jaaynge
    {
        return this.a%29;    
    }
    
    @Override
    public boolean equals(Object o)  //this will tell it how to compare two pair..agr hashCode ni hoga pr ye fn hoga then aesa hoga ki kuch same or kuch diff bucket mein jaaynge
    {
        if(o==null)return false;  //no need found
        pair p=(pair)o;
        
        if(this.a!=p.a)return false;
        if(!this.s.equals(p.s))return false;
        
        return true;
    }
    
    @Override
    public String toString()  //ye ni denge tou address print krega
    {
        return "("+this.a+" "+this.s+")";
    }
}

public static void main(String[] args) {
    HashMap<pair,Integer>map=new HashMap<>();
    for(int i=0;i<5;i++){
        pair p=new pair(10,"tushar");
        map.put(p,map.getOrDefault(p,0)+1);
    //   System.out.println(p);
    }
    map.put(null,1);
    pair p=new pair(10,"tushar");
    map.put(p,1);
    System.out.println(map.size());
    System.out.println(map);
}

 public boolean doublePairArray(int[] arr) {
        
        if(arr.length==0)return true;
        
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int ele:arr)map.put(ele,map.getOrDefault(ele,0)+1);
        
        Integer ar[]=new Integer[arr.length];
        for(int i=0;i<arr.length;i++)ar[i]=arr[i];
        
        Arrays.sort(ar,(a,b)->{   // lambda fn used with capital INTEGER
            return Math.abs(a)-Math.abs(b);
        });
        
        for(int ele:ar)
        {
            if(map.get(ele)==0)continue;
            
            if(map.getOrDefault(2*ele,0)==0)return false;
            
            
            map.put(ele,map.get(ele)-1);
            map.put(2*ele,map.get(2*ele)-1);
        }
        
        return true;
} */


    public static void main(String args[]) throws Exception {
        // fun1();
        // int arr[]={10,5,9,1,11,8,6,15,3,12,2};
        // longestConsecutiveSeq(arr, 11);

        // fun2();

        // HashMap<String, String> map = new HashMap<>(); // employee,manager
        // for (int i = 0; i < n; i++) {
        // String employee = sc.next(), manager = sc.next();
        // map.put(employee, manager);
        // }

        // HashMap<String, HashSet<String>> tree = new HashMap<>(); // manager,employee
        // fun(tree, map);

        Scanner scn = new Scanner(System.in);
        int noofpairs_src_des = scn.nextInt();
        HashMap<String, String> map = new HashMap<>();
        map.put("chennai", "bangalore");
        map.put("bombay", "delhi");
        map.put("goa", "chennai");
        map.put("delhi", "goa");

        findPathfromtickets(map);
    }
}


static class ListComparator implements Comparator<List<String>> {   //to  make answer unique
    @Override
    public int compare(List<String> l1, List<String> l2) {
        if (l1.size() != l2.size()) {   //descending mein krne kelie
            return l2.size() - l1.size();
        }

        String l1str = l1.get(0);
        String l2str = l2.get(0);
        return l1str.compareTo(l2str);  //sort on basis of 1st string if same then second

    }
}
	
public static void main(String[] args) {
    ArrayList<ArrayList<String>>ans=new ArrayList<>();
    ans.add(new ArrayList<>());ans.add(new ArrayList<>());ans.add(new ArrayList<>());
    ans.get(2).add("a");
    ans.get(2).add("z");
    ans.get(2).add("y");
    ans.get(1).add("m");
    ans.get(1).add("e");
    ans.get(0).add("a");
    ans.get(0).add("q");
    ans.get(0).add("j");
    for (ArrayList<String> a : ans) {  //within the list sorting kelie
        Collections.sort(a);
    }
    display(ans);	
    ans.sort(new ListComparator());
    System.out.println();
    display(ans);

    //a j q //em //ayz
    //ajq //ayz //em
}
