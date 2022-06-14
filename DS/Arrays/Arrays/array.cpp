#include <iostream>
#include <cmath>
#include <vector>
#include <string.h>
#include <algorithm>
#include <unordered_map>
#include <queue>
using namespace std;

// 31
public void reverse(int[] nums, int i, int j) {
    while (i < j)
        swap(nums, i++, j--);
}

public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1])
        i--;
    if (i == -1)
        reverse(nums, 0, nums.length - 1); // i=-1 matlab descending
        // return -1;
    else {
        int val = nums[i];
        int j = nums.length - 1; // cant start from aage se
        while (j >= i + 1 && nums[j] <= val)
            j--;

        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);
    }

    // long ans = 0;
    // for (int j = 0; j < nums.length; j++) {
    //     ans = ans * 10 + nums[j];
    //     if (ans > Integer.MAX_VALUE)
    //         return -1;
    // }

    // return (int) ans;
}

// 60
public String getPermutation(int n, int k) {
    int[] nums = new int[n + 1];
    String str = "";
    for (int i = 1; i <= n; i++)
        nums[i] = i;

    for (int i = 1; i < k; i++)
        nextPermutation(nums);

    for (int i = 1; i < nums.length; i++)
        str += nums[i];

    return str;
}

String ans="";
public void getPermutation_helper(List<Integer>factorial,List<Integer>digits,int n,int k)
{
    if(n==1)
    {
        ans+=digits.get(0);
        return;
    }
    
    int index=k/(factorial.get(n-1));
    if(k%factorial.get(n-1)==0)
        index-=1;
    
    ans+=digits.get(index);
    
    digits.remove(index);
    k=k-(factorial.get(n-1)*index);
    getPermutation_helper(factorial,digits,n-1,k);
    
}

public String getPermutation60(int n, int k) {
    
    List<Integer>factorial=new ArrayList<>();
    List<Integer>digits=new ArrayList<>();
    
    for(int i=0;i<n;i++)
        digits.add(i+1);
    
    factorial.add(1);
    for(int i=1;i<=9;i++)
        factorial.add(factorial.get(i-1)*i);
    
    getPermutation_helper(factorial,digits,n,k);
    return ans;
}

//556
int nextGreaterElement(int n)
{
    auto str = to_string(n);
    next_permutation(begin(str), end(str));
    auto res = stoll(str);  //string to long int
    // the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
    if (res > INT_MAX || res <= n)  //= for 1
        return -1; //21 kelie vrna aayga 12
    return res;
}

public int nextGreaterElement(int n) {
    List<Integer> li = new ArrayList<>();
    while (n != 0) {
        int r = n % 10;
        li.add(r);
        n = n / 10;
    }

    Collections.reverse(li);
    int[] nums = new int[li.size()];
    for (int i = 0; i < nums.length; i++)
        nums[i] = li.get(i);
    return nextPermutation(nums);
}

// 1850
public int getMinSwaps(String num, int k) {
    int[] nums = new int[num.length()];
    int[] kthSmallest = new int[num.length()];

    for (int i = 0; i < num.length(); i++) {
        nums[i] = num.charAt(i) - '0';
        kthSmallest[i] = nums[i];
    }

    for (int i = 0; i < k; i++)
        nextPermutation(kthSmallest);

    int swapCount = 0;
    for (int i = 0; i < nums.length; i++) {
        int j = i;
        while (nums[i] != kthSmallest[j])
            j++;

        while (i < j) {
            swap(kthSmallest, j, j - 1);
            j--;
            swapCount++;
        }
    }
    return swapCount;
}


//27
public int removeElement(int[] nums, int val) {
    int len=0;
    for(int i=0;i<nums.length;i++)
    {
        if(nums[i]!=val)
        {
            nums[len]=nums[i];
            len++;
        }
    }
    return len;
}

public int removeElement_(int[] nums, int val) {  //my
    int i=0,n=nums.length,j=n-1,len=0;
    while(i<j)
    {
        while(i<n && nums[i]!=val)i++;
        while(j>=0 && nums[j]==val)j--;
        
        if(i<j)
        {
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }
    } 
    
    for(int value:nums)
    {
        if(value==val)
            return len;
        len++;
    }
    
    return len;
}

//26
public int removeDuplicates(int[] nums) {  //my
    //no need to resize or erase as it doesn't matter what you leave beyond the new length.
    int len=0,i=0;
    while(i<nums.length)
    {
        nums[len++]=nums[i];
        int val=nums[i];
        
        while(i+1<nums.length && nums[i+1]==val)i++;
        i++;
    }
    return len;
}

public int removeDuplicates_(int[] A) {
    int len=1;
    for(int i=1;i<A.length;i++)
    {
        if(A[i]!=A[i-1])
            A[len++]=A[i];
    }
    return len;
}

//80
public int removeDuplicatesII(int[] nums) {
    int len=0,si=0,ei=0,n=nums.length;
    while(ei<n)
    {
        int val=nums[si];
        ei=si+1;
        while(ei<n && nums[ei]==val)ei++;
        if(ei-si==1)
        {
            nums[len++]=nums[si];
        }
        else if(ei-si>=2)
        {
            nums[len++]=nums[si];
            nums[len++]=nums[si];
        }
        
        si=ei;
    }
    
    return len;
}

// 169-Moore voting algo
//Assumes majority element always exist so no need to check if freq greater than n/2;
int majorityElement(vector<int> &nums)
{
    int major = nums[0], count = 1;
    for (int i = 1; i < nums.size(); i++)
    {
        if (count == 0)
        {
            major = nums[i];
            count++;
        }
        else if (major == nums[i])
            count++;
        else
            count--;
    }
    return major;
}

// 229
void isGreaternby3(vector<int> &ans, int val, vector<int> &nums)
{
    int count = 0;
    for (int i = 0; i < nums.size(); i++)
    {
        if (val == nums[i])
            count++;
    }

    if (count > (nums.size() / 3))
        ans.push_back(val);
}

vector<int> majorityElement_(vector<int> &nums)
{
    vector<int> ans;
    int val1 = nums[0], count1 = 1, val2 = nums[0], count2 = 0;

    for (int i = 1; i < nums.size(); i++)
    {
        if (val1 == nums[i])
            count1++;
        else if (val2 == nums[i])
            count2++;
        else
        {
            if (count1 == 0)
            {
                val1 = nums[i];
                count1 = 1;
            }
            else if (count2 == 0)
            {
                val2 = nums[i];
                count2 = 1;
            }
            else
            {
                count1--;
                count2--;
            }
        }
    }

    isGreaternby3(ans, val1, nums);
    if (val1 != val2)
        isGreaternby3(ans, val2, nums); //1111 pr val1=1 val2=1 so added twice

    return ans;
}

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/majority-element-general/ojquestion
public static List<Integer> majorityElement(int[] arr, int k, HashMap<Integer, Integer> map) {

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
        map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }

    int n = arr.length;
    for (int key : map.keySet()) {
        if (map.get(key) > (n / k))
            ans.add(key);
    }

    return ans;
}

//268
// 1 <= n <= 10^4 if you use n=0 and set for each value in array then check which bit will be off so it cant do for more than 32 if int taken 
public int missingNumber(int[] nums) {
    int n=nums.length;
    int sumShouldBe=(n*(n+1))/2;
    int sum=0;
    for(int val:nums)
        sum+=val;
    return sumShouldBe-sum;
}

public int missingNumber_(int[] nums) {
    int result=nums.length;
    for(int i=0;i<nums.length;i++)
        result^=i^nums[i];
    return result;
}

//448
public List<Integer> findDisappearedNumbers(int[] nums) {
    
    List<Integer>ans=new ArrayList<>();
    for(int i=0;i<nums.length;i++)
    {
        int idx=Math.abs(nums[i]);
        if(nums[idx-1]<0)continue;
        else nums[idx-1]=-nums[idx-1];
    }
    
    for(int i=0;i<nums.length;i++)
    {
        if(nums[i]>0)
            ans.add(i+1);
    }
    return ans;
}

// 287 yc
public int findDuplicate(int[] nums) {
    int n = nums.length;
    for (int i = 1; i <= n; i++) {
        int idx = nums[i - 1];
        if (idx < 0)
            idx = -(idx);
        if (nums[idx - 1] < 0)
            return idx;
        else
            nums[idx - 1] = -(nums[idx - 1]);
    }
    return -1;
}

public int findDuplicate2(int[] nums) {
    int fast = nums[0], slow = nums[0];
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (fast != slow);

    slow = nums[0];
    while (fast != slow) {
        slow = nums[slow];
        fast = nums[fast];
    }

    return slow;
}

// 442 yc
public List<Integer> findDuplicates(int[] arr) {
    List<Integer> result = new ArrayList<>();
    for (int i = 1; i <= arr.length; i++) {
        int index = arr[i - 1];
        if (index < 0)
            index = -(index);
        if (arr[index - 1] < 0)
            result.add(index);
        else
            arr[index - 1] = -arr[index - 1];
    }

    return result;
}

//41
public int firstMissingPositive(int[] nums) {
    boolean presentOne=false;
    for(int val:nums)
    {
        if(val==1)
        {
            presentOne=true;
            break;
        }
    }
    if(!presentOne)
        return 1; 
    
    int n=nums.length;
    if(n==1)return 2;
    
    for(int i=0;i<n;i++)
    {
        if(nums[i]<=0 || nums[i]>n)
            nums[i]=1;
    }
    
    for(int i=0;i<n;i++)
    {
        int idx=Math.abs(nums[i]);
        if(nums[idx-1]>0)nums[idx-1]=nums[idx-1]*(-1);
    }
    
    for(int i=0;i<n;i++)
        if(nums[i]>0)return i+1;
    
    return n+1;
}

//56
vector<vector<int>> merge(vector<vector<int>> &intervals)
{
    int n = intervals.size();
    if (n == 1)
        return intervals;

    //[[1,4],[0,0]] sort intervals else answer wrong
    sort(intervals.begin(), intervals.end(), [](vector<int> &a, vector<int> &b)
         { return a[0] < b[0]; });

    vector<vector<int>> ans;
    ans.push_back(intervals[0]);

    for (int i = 1; i < n; i++)
    {
        int li = ans.size();
        if (ans[li - 1][1] >= intervals[i][0])
            ans[li - 1][1] = max(intervals[i][1], ans[li - 1][1]);
        else
            ans.push_back(intervals[i]);
    }
    return ans;
}

//https://www.lintcode.com/problem/920/discuss
bool canAttendMeetings(vector<Interval> &intervals)
{
    int n = intervals.size();
    if (n == 1)
        return true;

    sort(intervals.begin(), intervals.end(), [](Interval &a, Interval &b)
         { return a.start < b.start; });

    for (int i = 1; i < n; i++)
    {
        if (intervals[i - 1].end > intervals[i].start)
            return false;
    }
    return true;
}

//986
void mergeInterval(int a, int b, int c, vector<vector<int>> &ans)
{
    vector<int> smallAns;
    if (a >= b)
        smallAns = {a, c};
    else
        smallAns = {b, c};

    ans.push_back(smallAns);
}

vector<vector<int>> intervalIntersection(vector<vector<int>> &firstList, vector<vector<int>> &secondList)
{
    vector<vector<int>> ans;
    if (firstList.size() == 0 || secondList.size() == 0)
        return ans;

    int i = 0, j = 0, n = firstList.size(), m = secondList.size();
    while (i < n && j < m)
    {
        int iendPt = firstList[i][1];
        int jendPt = secondList[j][1];

        int istPt = firstList[i][0];
        int jstPt = secondList[j][0];

        if (jendPt < istPt)
            j++;
        else if (iendPt < jstPt)
            i++;
        else if (iendPt == jendPt)
        {
            mergeInterval(istPt, jstPt, iendPt, ans);
            i++;
            j++;
        }
        else if (jendPt < iendPt)
        {
            mergeInterval(istPt, jstPt, jendPt, ans);
            j++;
        }
        else
        {
            mergeInterval(istPt, jstPt, iendPt, ans);
            i++;
        }
    }
    return ans;
}

//57
// in place soln mein o(n) ki jaan to delete so it become o(n^2)
vector<vector<int>> insert(vector<vector<int>> &intervals, vector<int> &newInterval)
{
    vector<vector<int>> ans;
    int i = 0;
    if (intervals.size() == 0)
    {
        ans.push_back(newInterval);
        return ans;
    } 

    for (int i = 0; i < intervals.size(); i++)
    {
        if (newInterval[1] < intervals[i][0]) //jaise hi range se bahar add krdo aur change kro apna newInterval
        {
            ans.push_back(newInterval);
            newInterval = intervals[i];
        }
        else if (intervals[i][1] < newInterval[0]) //end point chotta start point se..range ke andar aaya bhi ni new interval ki
        {
            ans.push_back(intervals[i]);
        }
        else
        {
            newInterval[0] = min(intervals[i][0], newInterval[0]);
            newInterval[1] = max(intervals[i][1], newInterval[1]);
        }
    }
    ans.push_back(newInterval);
    return ans;
}

//88
void merge(vector<int> &nums1, int m, vector<int> &nums2, int n)
{
    int i = m - 1, j = n - 1, k = nums1.size() - 1;
    while (i >= 0 && j >= 0)
    {
        if (nums1[i] >= nums2[j])
            nums1[k--] = nums1[i--];
        else
            nums1[k--] = nums2[j--];
    }
    while (j >= 0)
        nums1[k--] = nums2[j--];
}

public void merge(ArrayList<Integer> a, ArrayList<Integer> b) { 
    
    int i=0,j=0,n=a.size(),m=b.size();  
    
    while(j<m)
    {
        int iVal=i >= a.size()?Integer.MAX_VALUE:a.get(i);  //a ka size increase horha h baar baar cant write n
        int jVal=b.get(j);
        
        if(iVal<=jVal)
        i++;
        else{
            a.add(i,jVal);
            i++;
            j++;
        }
    }
}

public static void arrayList() {
    //set,remove,get,size,add 
    // int arr[]=new int[5]; //fixed size
    // ArrayList<Integer>list=new ArrayList<>();
    // System.out.println(list+" "+list.size());
    // list.add(10);
    // list.add(20);
    // list.add(30);
    // list.add(2,300); //indexing-012.. [10,20,300,30]
    // System.out.println(list+" "+list.size()); [10, 20, 300, 30] 4 //to print list can use for loop or forEach loop.

    // System.out.println(list.get(2)); //not list[2]
    // list.set(2, 90); //not list[2]=90
    // list.remove(0);
    // System.out.println(list);

    // ArrayList<String> list1 = new ArrayList<>();
    // list1.add("hello");
    // list1.add(1, "bello");
    // list1.add("mello");
    // list1.remove(0);
    // list1.set(1, "mayank");
    // System.out.println(list1);
}

public static void subArray() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
    }

    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            for (int k = i; k <= j; k++) {
                System.out.printf("%d\t", arr[k]);
            }
            System.out.println("");
        }
    }
}

public static void subSet() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++)
        arr[i] = sc.nextInt();

    int totalSubset = (int) Math.pow(2, n);

    for (int i = 0; i < totalSubset; i++) {
        int temp = i;
        String res = "";
        for (int j = arr.length - 1; j >= 0; j--) {
            int rem = temp % 2;
            temp = temp / 2;

            if (rem == 0) {
                res = "-\t" + res;
            } else {
                res = arr[j] + "\t" + res; // question mein bola h har character ke baad space..
            }
        }
        System.out.println(res);
    }
}

public static int LinearSearch() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++)
        arr[i] = sc.nextInt();
    int ele = sc.nextInt(); // agar print krana ho tou idx=-1 then break and print outside loop(no need to take flag and then check outside loop if flag has changed or not to find
    //value hume mili h loop ke andar ya ni.)
    for (int i = 0; i < n; i++) {
        if (ele == arr[i])
            return i;
    }
    return -1;
}

public static void ceilfloor() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++)
        arr[i] = sc.nextInt();
    int target = sc.nextInt();
    int si = 0, ei = n - 1;
    while (si <= ei) {
        int mid = (si + ei) / 2;
        if (arr[mid] == target) {
            System.out.println(target);
        } else if (arr[mid] < target) {
            si = mid + 1;
        } else
            ei = mid - 1;
    }
    System.out.println(arr[si]);
    System.out.println(arr[ei]);
}

public static void firstLastIndex() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int nums[] = new int[n];
    for (int i = 0; i < n; i++)
        nums[i] = sc.nextInt();
    int target = sc.nextInt();

    int mid = 0, si = 0, ei = n - 1, idx1 = -1, idx2 = -1, midCopy = mid, flag = 0;
    while (si <= ei) {
        mid = (si + ei) / 2;
        if (nums[mid] == target) {
            idx1 = idx2 = midCopy = mid;
            while (--mid >= 0 && nums[mid] == target)
                idx1 = mid;
            while (++midCopy < n && nums[midCopy] == target)
                idx2 = midCopy;
            System.out.println(idx1);
            System.out.println(idx2);
            flag = 1;
            break;
        } else if (nums[mid] > target) {
            ei = mid - 1;
        } else {
            si = mid + 1;
        }
    }
    if (flag == 0) {
        System.out.println(-1);
        System.out.println(-1);
    }
}

public static void sum2Array() {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt(), arr1[] = new int[m];
    for (int i = 0; i < m; i++)
        arr1[i] = sc.nextInt();
    int n = sc.nextInt(), arr2[] = new int[n];
    for (int i = 0; i < n; i++)
        arr2[i] = sc.nextInt();
    int sum[] = new int[m > n ? m : n];
    int i = m - 1;
    int j = n - 1;
    int k = sum.length - 1, carry = 0, digit = 0;
    while (k >= 0) {
        digit = carry;

        if (i >= 0) // i,j,k place value hai
            digit += arr1[i--];

        if (j >= 0)
            digit += arr2[j--];

        carry = digit / 10;
        digit = digit % 10;

        sum[k--] = digit;
    }
    if (carry != 0)
        System.out.println(carry);
    for (int idx = 0; idx < sum.length; idx++)
        System.out.println(sum[idx]);
}

public static void sub2Array() {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt(), arr1[] = new int[m];
    for (int i = 0; i < m; i++)
        arr1[i] = sc.nextInt();
    int n = sc.nextInt(), arr2[] = new int[n];
    for (int i = 0; i < n; i++)
        arr2[i] = sc.nextInt();
    int diff[] = new int[n]; // given in ques arr2 is big than arr1
    int i = m - 1;
    int j = n - 1;
    int k = n - 1, carry = 0, digit = 0;

    while (k >= 0) {
        digit = 0;
        int val = i >= 0 ? arr1[i] : 0;
        if (arr2[j] + carry - val >= 0) {
            digit = arr2[j] + carry - val;
            carry = 0;
        } else {
            digit = arr2[j] + carry + 10 - val;
            carry = -1;
        }
        diff[k] = digit;
        j--;
        i--;
        k--;
    }

    int idx = 0;
    for (idx = 0; idx < diff.length; idx++) {
        if (diff[idx] != 0)
            break;
    }
    while (idx < diff.length)
        System.out.println(diff[idx++]);
}

public static int[] inverse(int[] a) {
    int n = a.length;
    int res[] = new int[n];
    for (int i = n - 1; i >= 0; i--) {
        int index = a[i];
        res[index] = i;
    }
    return res;
}

public static void removePrimeFromList(ArrayList<Integer> al) {
    for (int i = al.size() - 1; i >= 0; i--) {
        int val = al.get(i), flag = 0;
        for (int j = 2; j * j <= val; j++) {
            if (val % j == 0) {
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            al.remove(i);
    }
}

public static int spanofArray() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++)
        arr[i] = sc.nextInt();
    int max = arr[0], min = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max)
            max = arr[i];
        else if (arr[i] < min)
            min = arr[i];
    }
    return max - min;
}

// 1877
public int minPairSum(int[] nums) {
    Arrays.sort(nums);
    int i = 0, j = nums.length - 1;
    int max = -(int) 1e8;
    while (i < j) {
        max = Math.max(max, nums[i] + nums[j]);
        i++;
        j--;
    }
    return max;
}

// 1848 yc 
public int getMinDistance(int[] nums, int target, int start) {
    int minDistance = (int) 1e9;
    for (int i = 0; i < nums.length; i++) {
        if (target == nums[i])
            minDistance = Math.min(minDistance, Math.abs(i - start));
    }

    return minDistance;
}

// 1846-why arr.len not ans?[2,2,1,2,1]
public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    Arrays.sort(arr);  //rearrange
    int max = 1;
    arr[0] = 1;
    for (int i = 1; i < arr.length; i++) {
        int d = arr[i] - arr[i - 1];
        if (d > 1)
            arr[i] = arr[i - 1] + 1;  //decrease to a smaller value
        max = Math.max(max, arr[i]);
    }

    return max;
}

// 628
public int maximumProduct(int[] nums) {
    if (nums.length == 3)
        return nums[0] * nums[1] * nums[2];

    int mx1 = -1001, mx2 = -1001, mx3 = -1001, mn1 = 1001, mn2 = 1001;
    for (int i = 0; i < nums.length; i++) {
        int val = nums[i];
        if (val >= mx1) {
            mx3 = mx2;
            mx2 = mx1;
            mx1 = val;
        } else if (val >= mx2) {
            mx3 = mx2;
            mx2 = val;
        } else if (val > mx3)
            mx3 = val;

        if (val <= mn1) {
            mn2 = mn1;
            mn1 = val;
        } else if (val < mn2) {
            mn2 = val;
        }
    }
    System.out.println(mx1 + " " + mx2 + " " + mx3 + " " + mn1 + " " + mn2);
    return Math.max(mn1 * mn2 * mx1, mx1 * mx2 * mx3);
}

//https://www.interviewbit.com/problems/array-3-pointers/
public int pointers3(final int[] A, final int[] B, final int[] C) {
    
    int i=0,j=0,k=0;
    
    int min=(int)1e8;
    while(i<A.length && j<B.length && k<C.length)
    {
        int fv=Math.abs(A[i]-B[j]);  //first value
        int sv=Math.abs(B[j]-C[k]);
        int tv=Math.abs(C[k]-A[i]);
        min=Math.min(min,Math.max(Math.max(fv,sv),tv));
        
        if(A[i]<B[j] && A[i] <C[k])i++;
        else if(B[j]<C[k])j++;
        else k++;
    }
    return min;
}

//https://www.interviewbit.com/problems/triplets-with-sum-between-given-range/
public int tripletWithSumbtwGivenRange(ArrayList<String> A) { // ib
    int n = A.size();
    Double[] values = new Double[n];
    for (int i = 0; i < n; i++) {
        values[i] = Double.parseDouble(A.get(i));
    }

    double a = values[0], b = values[1], c = values[2];

    for (int i = 3; i < n; i++) {
        if (a + b + c > 1 && a + b + c < 2)
            return 1;
        else if (a + b + c > 2) {
            if (a > b && a > c)
                a = values[i];
            else if (b > c)
                b = values[i];
            else
                c = values[i];
        } else {
            if (a < b && a < c)
                a = values[i];
            else if (b < c)
                b = values[i];
            else
                c = values[i];
        }
    }

    if (a + b + c > 1 && a + b + c < 2)
        return 1;
    return 0;
}

// (k%length+length)%length 2 tarikke ya tou pehle puri reverse then beech ke part reverse ya vice versa
// 189
void reverse(vector<int> &nums, int i, int j)
{
    while (i < j)
    {
        swap(nums[i++], nums[j--]);
    }
}

void rotate(vector<int> &nums, int k)
{
    int m = nums.size();
    k = (k % m + m) % m;
    reverse(nums, 0, m - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, m - 1);
    for (int ele : nums)
        cout << ele << " ";
}

int calculate(vector<int> &nums)
{
    int sum = 0;
    for (int i = 0; i < nums.size(); i++)
        sum += nums[i] * i;
    return sum;
}

void maximizeSum(vector<int> &nums) //o(n^2)
{
    int m = nums.size();
    int maximum = 0;
    maximum = calculate(nums);
    // cout<<maximum<<" ";
    for (int k = 1; k < m; k++)
    {
        reverse(nums, 0, m - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, m - 1);
        // cout<<calculate(nums)<<" ";
        maximum = max(maximum, calculate(nums));
    }
    cout << maximum;
}

void maximizeSum2(vector<int> &nums) //o(n)
{
    int m = nums.size();
    int maximum = 0;
    int rotatedSum = 0;
    int sum = 0;
    for (int ele : nums)
        sum += ele;
    for (int i = 0; i < nums.size(); i++)
        rotatedSum += i * nums[i];
    maximum = rotatedSum;
    for (int i = 0; i < nums.size() - 1; i++) //ye loop rotation ke hisaaab se ni chla formulae ke hisaab se
    {
        maximum = max(maximum, rotatedSum = rotatedSum - sum + nums[i] * m);
    }
    cout << maximum;
}

void posNeg(vector<int> &arr) 
{
    int i = 0, j = arr.size() - 1;
    while (i < j)
    {
        while (arr[i] >= 0)  //use i<j
            i++;
        while (arr[j] < 0)
            j--;
        if(i<j)
        swap(arr[i++], arr[j--]);
    }
}

// Sir-jo left mein lejaani hai uspr swapping
void posNeg2(vector<int> &arr)
{
    int pivot = -1, idx = 0;
    while (idx < arr.size())
    {
        if (arr[idx] >= 0)
            swap(arr[++pivot], arr[idx]);
        idx++;
    }
}

// 283
void moveZeroes(vector<int> &arr)
{
    int pivot = -1, idx = 0; //0 se start kroge pivot tou post increment
    while (idx < arr.size())
    {
        if (arr[idx] != 0)
            swap(arr[++pivot], arr[idx]);
        idx++;
    }
}

// https://practice.geeksforgeeks.org/problems/binary-array-sorting-1587115620/1
void binSort(int arr[], int N)
{
    int pivot = -1, idx = 0;
    while (idx < N)
    {
        if (arr[idx] == 0)
            swap(arr[++pivot], arr[idx]);
        idx++;
    }
    // while (i < n) { if (arr[i] == 0) { swap(arr, i, j); i++; j++; } else { i++; }  //i 0 se initialse then post increment
}

//905
vector<int> sortArrayByParity(vector<int> &arr)
{
    int n = arr.size();
    int i = 0, j = n - 1;
    while (i < j)
    {
        while (i < j && arr[i] % 2 == 0)  //[0,2] use i<j
            i++;
        while (i < j && arr[j] % 2 != 0)
            j--;
        if (i < j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    return arr;
}

// 75
void sortColors(vector<int> &arr)
{
    int p1 = -1, p2 = arr.size() - 1, idx = 0;
    while (idx <= p2)
    {
        if (arr[idx] == 0)
            swap(arr[++p1], arr[idx++]);
        else if (arr[idx] == 2)
            swap(arr[idx], arr[p2--]);
        else
            idx++;
    }
    //  while (i <= k) // = pr chlane ki zrurat ni just bcz of pep portal.upr jaisa hi hai..bus i 0 se hai tou post incr
    //  {
    //   if (arr[i] == 1) i++;
    //   else if (arr[i] == 2) { swap(arr, i, k); k--; }
    //   else { swap(arr, i, j); i++; j++; } }
}

void sortColors(vector<int>& nums) {
    int p1=-1,p2=-1,idx=0;
    while(idx<nums.size())
    {
        if(nums[idx]==0)
        {
            swap(nums[++p1],nums[idx]);
            if(nums[idx]==1)swap(nums[++p2],nums[idx]);
            else ++p2;
            idx++;
        }
        else if(nums[idx]==1)
        {
            swap(nums[++p2],nums[idx++]);   
        }
        else idx++;
    }
}

//119
long nCr(long n, long r)
{
    if (n < r)   //no need here
        return 0L;

    long result = 1L;
    for (long i = 0; i < r; i++)
    {
        result *= (n - i);
        result /= (i + 1);
    }
    return result;
}

vector<int> getRow(int A)  //A-->rowindex
{
    vector<int> result;
    result.push_back(1);
    if (A == 0)
        return result;
    for (int i = 1; i < A; i++)
    {
        result.push_back(nCr(A, i));
    }
    result.push_back(1);
    return result;
}

//118
vector<vector<int>> generate(int A)
{
    vector<vector<int>> result;
    if (A == 0)
        return result;
    result.push_back({1});
    for (int i = 1; i < A; i++)
    {
        vector<int> subRes;
        subRes.push_back(1);
        for (int j = 1; j < i; j++)
        {
            subRes.push_back(result[i - 1][j - 1] + result[i - 1][j]);
        }
        subRes.push_back(1);
        result.push_back(subRes);
    }
    return result;
}

//ib
vector<vector<int>> antiDiagonal(vector<vector<int>> &A)
{
    int n = A.size();
    vector<vector<int>> result;
    for (int s = 0; s < n; s++) //s:sum is always constant for all list element
    {
        vector<int> subres;
        for (int i = 0, j = s; i <= s; i++, j--)
        {
            subres.push_back(A[i][j]);
        }

        result.push_back(subres);
    }

    for (int s = 1; s < n; s++)
    {
        vector<int> subres;
        for (int i = s, j = n - 1; j >= s; i++, j--)
        {
            subres.push_back(A[i][j]);
        }

        result.push_back(subres);
    }
    return result;
} 

//https://www.interviewbit.com/problems/minimum-lights-to-activate/
int minimumLightToActivate(vector<int> &A, int B)
{
    int i = 0, n = A.size(), count = 0;
    while (i < n)
    {
        int bulbFound = -1;
        int j = min(i + B - 1, n - 1);

        while (j >= i - B + 1)
        {
            if (A[j] == 1)
            {
                bulbFound = j;
                i = j + B;
                count++;
                break;
            }
            j--;
        }

        if (bulbFound == -1)
            return -1;
    }
    return count;
}

//179
public String largestNumber(int[] nums) {
    String []arr=new String[nums.length];
    for(int i=0;i<nums.length;i++)
        arr[i]=nums[i]+"";
    
    Arrays.sort(arr,(a,b)->{
        long n1=Long.parseLong(a+b);
        long n2=Long.parseLong(b+a);
        
        if(n2>n1)return -1;
        else if(n1>n2)return 1;
        return 0;
    });
    
    String ans="";
    for(int i=arr.length-1;i>=0;i--)
        ans+=arr[i];
    return ans.charAt(0)=='0'?"0":ans;  [0,0]
}

//11
int maxArea(vector<int> &height)
{
    int i = 0, j = height.size() - 1, maxArea = 0;
    while (i < j)
    {
        int width = j - i;
        int ht = min(height[i], height[j]);

        maxArea = max(maxArea, width * ht);
        if (height[i] <= height[j]) //chotti height waala apna best ans de chuka hai
            i++;
        else
            j--;
    }

    return maxArea;
}

//https://www.lintcode.com/problem/912/description
int minTotalDistance(vector<vector<int>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();

    vector<int> xcord;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
                xcord.push_back(i);
        }
    }

    vector<int> ycord;
    for (int j = 0; j < m; j++)
    {
        for (int i = 0; i < n; i++)
        {
            if (grid[i][j] == 1)
                ycord.push_back(j);
        }
    }

    int xmeetPt = xcord[xcord.size() / 2];
    int ymeetPt = ycord[ycord.size() / 2];

    int distance = 0;
    for (int i = 0; i < xcord.size(); i++)
        distance += abs(xcord[i] - xmeetPt) + abs(ycord[i] - ymeetPt);

    return distance;
}

//754
public int reachNumber(int target) {
    target=Math.abs(target);

    long N=1;
    int parity=0;
    while(!(target<=N*(N+1)/2 && ((target&1)==(N*(N+1)/2 & 1))))
    {
        N++;
        //parity=(parity+1)%2;  //parity se ni krskte because odd even aese alternative ni h
    }
    return (int)N;
}

//977
vector<int> sortedSquares(vector<int> &nums)
{
    vector<int> ans(nums.size());
    int i = 0, j = nums.size() - 1, k = nums.size() - 1;
    while (i <= j)
    {
        if (abs(nums[i]) > abs(nums[j]))
        {
            ans[k--] = abs(nums[i]) * abs(nums[i]);
            i++;
        }
        else
        {
            ans[k--] = abs(nums[j]) * abs(nums[j]);
            j--;
        }
    }
    return ans;
}

//66
//Do inplace without making ans array..
vector<int> plusOne(vector<int> &digits)
{
    int carry = 1, n = digits.size();
    for (int i = n - 1; i >= 0; i--)
    {
        int sum = digits[i] + carry;
        digits[i] = sum % 10;
        carry = sum / 10;
    }

    if (carry > 0)
        digits.insert(digits.begin(), 1);
    return digits;
}

//1200
vector<vector<int>> minimumAbsDifference(vector<int> &arr)
{
    sort(arr.begin(), arr.end());
    int minabsDiff = 1e9;
    // int n = arr.size();
    // for (int i = 0; i < n - 1; i++)
    // {
    //     if (abs(arr[i] - arr[i + 1]) < minabsDiff)
    //     {
    //         minabsDiff = abs(arr[i] - arr[i + 1]);
    //     }
    // }

    // vector<vector<int>> ans;
    // for (int i = 0; i < n - 1; i++)
    // {
    //     if (abs(arr[i] - arr[i + 1]) == minabsDiff)
    //     {
    //         // vector<int> subAns;
    //         // subAns.push_back(arr[i]);
    //         // subAns.push_back(arr[i + 1]);
    //         // ans.push_back(subAns);
    //         ans.push_back({arr[i], arr[i + 1]});
    //     }
    // }

    vector<vector<int>> ans;
    for (int i = 0; i < n - 1; i++)
    {
        if (abs(arr[i] - arr[i + 1]) < minabsDiff)
        {
            minabsDiff = abs(arr[i] - arr[i + 1]);
            ans.clear();
            ans.push_back({arr[i], arr[i + 1]});
        }
        else if (abs(arr[i] - arr[i + 1]) == minabsDiff)
            ans.push_back({arr[i], arr[i + 1]});
    }

    return ans;
}

//https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
int minStepinInfiniteGrid(vector<int> &A, vector<int> &B)
{

    int steps = 0;
    int n = A.size();
    for (int i = 0; i < n - 1; i++)
    {
        steps += max(abs(A[i] - A[i + 1]), abs(B[i] - B[i + 1]));
    }
    return steps;
}

//https://www.interviewbit.com/problems/xor-ing-the-subarrays/
int xoringSubArray(vector<int> &A)
{ //ib

    int size = A.size();
    if (size % 2 == 0)
        return 0;

    int res = 0;
    for (int i = 0; i < size; i += 2)
        res = res ^ A[i];

    return res;
}

bool hotelBookingPossible(vector<int> &arrive, vector<int> &depart, int K)
{
    sort(arrive.begin(), arrive.end());
    sort(depart.begin(), depart.end());

    for (int i = K; i < arrive.size(); i++)
    {
        if (arrive[i] < depart[i - K])
            return false;
    }

    return true;
}

bool hotelBookingPossible(vector<int> &arrive, vector<int> &depart, int K)
{
    sort(arrive.begin(), arrive.end());
    sort(depart.begin(), depart.end());

    int i = 0, j = 0, rooms = 0, n = arrive.size();

    while (i < n && j < n)
    {
        if (arrive[i] < depart[j])
        {
            rooms++;
            if (rooms > K)
                return false;
            i++;
        }
        else if (arrive[i] > depart[j])
        {
            rooms--;
            j++;
        }
        else
        { //usdin aaya usdin hi chlagya
            i++;
            j++;
        }
    }

    return true;
}

//https://practice.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1#
vector<int> leaders(int a[], int n)          //2nd approach:right se max print krate jao
{
    vector<int> answer;
    stack<int> st;
    st.push(a[n - 1]);
    answer.push_back(a[n - 1]);
    for (int i = n - 2; i >= 0; i--)
    {
        while (st.size() != 0 && st.top() <= a[i])
            st.pop();

        if (st.size() == 0)
            answer.push_back(a[i]);
        st.push(a[i]);
    }

    reverse(answer.begin(), answer.end());
    return answer;
}

//leetcode 53
int kadaneAlgoSubArray(vector<int> &nums) 
{
    int gmax = -1e9, runningSum = 0, gsi = 0, gei = 0, si = 0, ei = 0, n = nums.size(),maxLength;
    for (; ei < n; ei++)
    {
        runningSum += nums[ei];

        if (runningSum >= gmax)
        {
            gmax = runningSum;
            gsi = si;
            gei = ei;
            maxLength=ei-si+1;  //longest max subarray
        }

        if (runningSum <= 0)
        {
            runningSum = 0;
            si = ei + 1;
        }
    }
    return gmax;
}

int kadaneAlgo2Generic(vector<int> &nums) //vector is passed by refrence to avoid copy constructor from firing.
{
    int gmax = 0, runningSum = 0;
    for (int ei = 0; ei < nums.size(); ei++)
    {
        runningSum = max(nums[ei], runningSum + nums[ei]);
        gmax = max(gmax, runningSum);
    }
    return gmax;
}

//1074
int numSubmatrixSumTarget(vector<vector<int>> &matrix, int target)
{
    int n = matrix.size();
    int m = matrix[0].size();
    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            matrix[i][j] += matrix[i - 1][j];
        }
    }

    int count = 0;
    for (int base = 0; base < n; base++)
    {
        for (int row = base; row < n; row++)
        {
            unordered_map<int, int> map;
            int sum = 0;
            map[0] = 1;
            for (int j = 0; j < m; j++)
            {
                sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
                if (map.find(sum - target) != map.end())
                    count += map[sum - target];
                map[sum]++;
            }
        }
    }
    return count;
}

//ib
public int maximum_SumSquare(int[][] A, int B) {
        int n=A.length,m=A[0].length;
        
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                A[i][j]+=A[i-1][j];
            }
        }
        
        int gsum=-(int)1e8;
        for(int i=B-1;i<n;i++)
        {
            int sum=0;
            for(int j=0;j<B;j++)
                sum+=A[i][j]-(i==B-1?0:A[i-B][j]);
                
            gsum=Math.max(gsum,sum);    
            for(int j=B;j<m;j++)
            {
                //i==B-1?0:A[i-B][j])  ==>upar kelie
                //(i==B-1?0:A[i-B][j-B]) ==>side kelie
                sum=sum+(A[i][j]-(i==B-1?0:A[i-B][j]))-(A[i][j-B]-(i==B-1?0:A[i-B][j-B]));
                gsum=Math.max(gsum,sum);
            }    
        }
        return gsum;
}

//1191
int mod = 1e9 + 7;
int kadaneAlgoKconcat(vector<int> &nums) //for k concat question isme agr saare negative number h tou ans 0 return krne ko bolaa hai islie gmax ko 0 lo
{
    int gmax = 0, runningSum = 0;
    for (int ele : nums)
    {
        runningSum += ele;

        if (runningSum > gmax)
            gmax = runningSum;

        if (runningSum < 0)
            runningSum = 0;
    }
    return gmax;
}

int prefixSum(vector<int> &nums)
{
    int csum = 0;
    int gsum = -1e8;
    for (int ele : nums)
    {
        csum = csum + ele;
        gsum = max(gsum, csum);
    }
    return gsum;
}

int suffixSum(vector<int> &nums)
{
    int csum = 0;
    int gsum = -1e8;
    for (int i = nums.size() - 1; i >= 0; i--)
    {
        csum = csum + nums[i];
        gsum = max(gsum, csum);
    }
    return gsum;
}

int kConcatenationMaxSum(vector<int> &nums, int k) 
{
    long sumofArray = 0;
    int kadaneSum = kadaneAlgoKconcat(nums);
    if (k == 1)
        return (int)kadaneSum;

    for (int ele : nums)
        sumofArray += ele ;

    int pSum = prefixSum(nums);
    int sSum = suffixSum(nums);

    if (sumofArray > 0)
    {
        int apsum = (  (k - 2) * sumofArray) % mod + sSum + pSum) % mod;
        return apsum;
    }
    else
        return max(kadaneSum, (pSum + sSum)%mod);  //10^9+10^9 can be in int
}

//best method
int mod=1e9+7;
long kadanesSum(vector<int> &arr, int k)
{
    long gmax = 0, rsum = 0, n = arr.size();
    for (int i = 0; i < n * k; i++)
    {
        rsum = rsum + arr[i % n];
        if (rsum > gmax)
            gmax = rsum;
        if (rsum <= 0)
            rsum = 0;
    }
    return gmax;
}

int kConcatenationMaxSum(vector<int> &arr, int k)
{
    long prevSum = 0;
    for (int i = 1; i <= 3; i++)
    {
        long KSum = kadanesSum(arr, i)%mod;
        if (i == k)
            return (int)KSum;

        if (i == 3)
        {

            //a+(n-1)d
            return (int)((prevSum + ((k - 2)*(KSum - prevSum))%mod)%mod);
            //secondTerm+(k-2)*(thirdTerm-secondTerm) need to take 3 terms because just by 2 terms we cant say it is ap.
        }
        prevSum = KSum;
    }
    return 0;
}

// 363
public int maxSumSubmatrix(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++)
                matrix[i][j] += matrix[i - 1][j];
        }

        int maxsum = -(int) 1e9;
        for (int base = 0; base < n; base++) {
            for (int row = base; row < n; row++) {
                // yahan kadane lgake gsum calculate krskte for =k return anf for < k store in maxsum
                TreeSet<Integer> map = new TreeSet<>();
                map.add(0);
                int sum = 0;
                for (int j = 0; j < m; j++) {                    
                    sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
                    Integer val1 = map.ceiling(sum - k);
                    if (val1 != null) {
                        maxsum = Math.max(maxsum, sum - val1);
                    }
                    map.add(sum);
                }                
            }
        }

        return maxsum == -(int) 1e9 ? -1 : maxsum;
    }
}

//https://www.interviewbit.com/problems/flip/
vector<int> flip(string A)
{
    int gsum = 0, gsi = -1, gei = -1, runningSum = 0, si = 0;
    for (int ei = 0; ei < A.length(); ei++)
    {
        if (A[ei] == '0')
            runningSum++;
        else
            runningSum--;

        if (runningSum > gsum)
        {
            gsum = runningSum;
            gsi = si + 1;
            gei = ei + 1; //1 indexed base
        }

        if (runningSum < 0)  //need of equal but for ib dont apply "1101010001"
        {
            runningSum = 0;
            si = ei + 1;
        }
    }

    if (gsi == -1)
        return {};
    return {gsi, gei};
}

// 485
public int findMaxConsecutiveOnes(int[] arr) {

    // int si=0,ei=0,n=arr.length,len=0;
    // while(ei<n)
    // {
    // while(ei<n && arr[ei] == 1)ei++;

    // len=Math.max(len,ei-si);

    // si=ei+1;
    // ei++;

    // while(ei<n && arr[ei]==0)
    // {
    // si++;ei++;
    // }

    // }

    // return len;

    int len = 0, count = 0;
    for (int i = 0; i < arr.length; i++) // slow code
    {
        if (arr[i] == 1)
            count++;
        else
            count = 0;

        len = Math.max(len, count);
    }

    return len;
}

//239
vector<int> maxSlidingWindow(vector<int> &nums, int k)
{
    if (nums.size() == 1 || k == 1)
        return nums;
    priority_queue<pair<int, int>> pq;
    int i = 0, n = nums.size();
    vector<int> ans; //n-k+1 size ki array bnadi aur pushback kiya then 0,0,0,ans aayga
    while (i < n)
    {
        while (pq.size() != 0 && pq.top().second <= i - k) //out of range waale bnde nhi htaye yahan jo top pr the pr range se bahaar
            pq.pop();
        pq.push({nums[i], i});

        if (i >= k - 1)
            ans.push_back(pq.top().first);
        i++;
    }
    return ans;
}

//560
int subarraySum(vector<int> &nums, int k)
{
    unordered_map<int, int> map;
    map[0] = 1;
    int sum = 0, count = 0;
    for (int i = 0; i < nums.size(); i++)
    {
        sum += nums[i];
        if (map.find(sum - k) != map.end())
            count += map[sum - k];
        map[sum]++;
    }
    return count;
}

//leetcode 930-Binary Subarrays With Sum
//Constraints:
//1 <= nums.length <= 3 * 10^4
//0 <= goal <= nums.length
int numSubarraysWithSum(vector<int> &A, int S)
{
    int n = A.size(), sum = 0, count = 0, val = 0;
    unordered_map<int, int> map; //sum vs freq
    map[0] = 1;
    for (int i = 0; i < n; i++)
    {
        sum += A[i];
        val = sum - S; //sum bhi kabhi negative ni hoga and S also always positive because 0<=S<=A.length.aur hashmap mein hum daal rhe hain sum tou val can be negative but if we search -ve value in hashmap then ans always false
        if (val >= 0 && map.find(val) != map.end())
            count += map[val];
        map[sum]++;
    }
    return count;
}

int numSubarraysWithSum(vector<int> &A, int S) //by array
{
    int n = A.size(), sum = 0, count = 0, val = 0;
    vector<int> map(30000 + 1, 0); //agar -1 se initialise then when value not available in map then it will add -1.Test case:0000001000 and S=0
    map[0] = 1;
    for (int i = 0; i < n; i++)
    {
        sum += A[i];
        val = sum - S;
        if (val >= 0)
            count += map[val];
        map[sum]++;
    }
    return count;
}

// LongestSubArray with equal no 0&1 leetcode-525
int findMaxLength(vector<int> &nums)
{
    unordered_map<int, int> map;
    int sum = 0, n = nums.size(), len = 0;
    map[0] = -1;
    for (int i = 0; i < n; i++)
    {
        int val = nums[i];
        if (val == 0)
            val = -1;
        sum += val;

        if (map.find(sum) != map.end())
            len = max(len, i - map[sum]);
        else
            map[sum] = i;
    }
    return len;
}

//https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
long long int CountSubArray(vector<int> &nums, int N)
{
    unordered_map<int, int> map;
    int sum = 0;
    long long int count = 0;
    map[0] = 1;
    for (int i = 0; i < N; i++)
    {
        int val = nums[i];
        if (val == 0)
            val = -1;
        sum += val;

        if (map.find(sum) != map.end())
        {
            count += map[sum];
            map[sum]++;
        }
        else
            map[sum] = 1;
    }
    // for (pair<int, int> key : map)
    //     count += (key.second) * (key.second - 1) / 2;
    return count;
}

int longSubarrWthSumDivByK(int arr[], int n, int k)
{
    int sum = 0, len = 0,rem=0;
    unordered_map<int, int> map;   //rem vs frequency
    map[0] = -1;
    for (int i = 0; i < n; i++)
    {
        sum += arr[i];
        rem = (sum % k + k) % k; //sum can be negative

        if (map.find(rem) != map.end())
            len = max(len, i - map[rem]);
        else
            map[rem] = i;
    }
    return len;
}

//974
int subarraysDivByK(vector<int> &A, int k)
{
    int sum = 0, count = 0, rem = 0;
    unordered_map<int, int> map;
    map[0] = 1;
    for (int i = 0; i < A.size(); i++)
    {
        sum += A[i];
        rem = (sum % k + k) % k;
        if (map.find(rem) != map.end())
        {
            count += map[rem];
            map[rem]++;
        }
        else
            map[rem] = 1;
    }
    return count;
}

int subarraysDivByK(vector<int> &A, int k) //by array faster
{
    int sum = 0, count = 0;
    vector<int> map(10000, -1); //2 <= K <= 10000
    map[0] = 1;
    for (int i = 0; i < A.size(); i++)
    {
        sum += A[i];
        sum = (sum % k + k) % k;
        if (map[sum] != -1)
        {
            count += map[sum];
            map[sum]++;
        }
        else
            map[sum] = 1;
    }
    return count;
}

//leetcode 1004 
int longestOnes(vector<int> &A, int K)  //atmost k
{
    int si = 0, ei = 0, requirement = K, n = A.size(), len = 0, gsi = 0;
    while (ei < n)
    {
        if (A[ei++] == 0)
            requirement--;

        while (requirement < 0)
        {
            if (A[si++] == 0) //iske andar length ni update krskte because if requirement never become 0 so length will never update.
                requirement++;
        }
        if (ei - si > len)
        {
            len = ei - si;
            gsi = si;
        }
    }

    // for(int i=gsi;i<gsi+len;i++)
    //     ans.push_back(i);
    return len;
}

//904
int totalFruit(vector<int> &tree)
{
    int si = 0, ei = 0, fruitCount = 0, distinctCount = 0, n = tree.size();
    unordered_map<int, int> map; //fruit type vs freq
    while (ei < n)
    {
        if (map[tree[ei++]]++ == 0)
            distinctCount++;

        while (distinctCount > 2)
        {
            if (map[tree[si++]]-- == 1)
                distinctCount--;
        }

        fruitCount = max(fruitCount, ei - si);
    }
    return fruitCount;
}

//781
int numRabbits(vector<int> &answers)
{
    unordered_map<int, int> map;
    int count = 0, i = 0;
    while (i < answers.size())
    {
        // if(map[answers[i]]==0)
        if (map.find(answers[i]) == map.end())
        {
            map[answers[i]] = 1;
            count += answers[i] + 1;
        }
        else
            map[answers[i]]++;

        if (map[answers[i]] == answers[i] + 1)
            // map[answers[i]]=0;
            map.erase(answers[i]);
        i++;
    }
    return count;
} 

int allSubarrayAtmostK(vector<int> &A, int S) //also on IB there they want strictly less than S so while(sum>=s)
{
    int si = 0, ei = 0, count = 0, sum = 0;
    while (ei < A.size())
    {
        sum += A[ei++];

        while (sum > S)
            sum -= A[si++];

        count += ei - si;
    }
    return count;
}

int numSubarraysWithSum2(vector<int> &A, int S) //method 2
{
    if (A.size() == 0)
        return 0;
    return allSubarrayAtmostK(A, S) - (S != 0 ? allSubarrayAtmostK(A, S - 1) : 0);
}

//1248
int numberOfSubarraysWithAtmostKOdd(vector<int> &nums, int k) //1 <= k <= nums.length
{
    int si = 0, ei = 0, count = 0, n = nums.size(), oddcount = 0, res = 0;
    while (ei < n)
    {
        if ((nums[ei++] & 1) != 0)
            oddcount++;

        while (oddcount > k)
        {
            if ((nums[si++] & 1) != 0)
                oddcount--;
        }
        res += ei - si;
    }
    return res;
}

int numberOfSubarrays(vector<int> &nums, int k)
{
    return numberOfSubarraysWithAtmostKOdd(nums, k) - numberOfSubarraysWithAtmostKOdd(nums, k - 1);
}

//https://www.interviewbit.com/problems/subarrays-with-distinct-integers/
/*public int subArraysWithAtMostK(int []A,int k)
{
    int []map=new int[A.length+1];
    int si=0,ei=0,disCount=0,count=0,n=A.length;
    while(ei<n)
    {
        if(map[A[ei++]]++ == 0)
            disCount++;
            
        while(disCount>k)
        {
            if(map[A[si++]]-- == 1)
            disCount--;
        }
        
        count+=ei-si;
    }
    
    return count;

    // return subArraysWithAtMostK(A,B)-(B-1!=0?subArraysWithAtMostK(A,B-1):0);
}*/

//https://www.interviewbit.com/problems/maximum-unsorted-subarray/
vector<int> maximumUnsortedSubArray(vector<int> &A)
{ //memory limit exceeded

    int n = A.size();
    vector<int> leftMax(n);
    vector<int> rightMin(n);

    rightMin[n - 1] = A[n - 1];
    for (int i = n - 2; i >= 0; i--)
        rightMin[i] = min(rightMin[i + 1], A[i]);

    leftMax[0] = A[0];
    for (int i = 1; i < n; i++)
        leftMax[i] = max(leftMax[i - 1], A[i]);

    int j = n - 1;
    for (; j >= 0; j--)
    {
        if (leftMax[j] != rightMin[j])
            break;
    }

    int i = 0;
    for (; i < n; i++)
    {
        if (leftMax[i] != rightMin[i])
            break;
    }

    if (i == n && j == -1)
        return {-1};

    return {i, j};
}

vector<int> maximumUnsortedSubArray(vector<int> &A)
{
    int n = A.size();
    vector<int> sortedA;

    for (int ele : A)
        sortedA.push_back(ele);

    sort(sortedA.begin(), sortedA.end());
    if (sortedA == A)
        return {-1};

    int i = 0, j = n - 1;
    while (i < n && j >= 0)
    {
        if (sortedA[j] != A[j] && sortedA[i] != A[i])
            break;

        if (sortedA[i] == A[i])
            i++;

        if (sortedA[j] == A[j])
            j--;
    }

    return {i, j};
}

.//https://www.interviewbit.com/problems/max-distance/
int maximumDistance(const vector<int> &A)
{
    int n = A.size();
    vector<int> maxSuffix(n);
    maxSuffix[n - 1] = A[n - 1];

    for (int i = n - 2; i >= 0; i--)
    {
        maxSuffix[i] = max(maxSuffix[i + 1], A[i]);
    }

    int ans = 0, i = 0, j = 0;
    while (i < n && j < n)
    {
        if (maxSuffix[j] >= A[i])
        {
            ans = max(ans, j - i); //length ni j-i btana h
            j++;
        }
        else
            i++;
    }
    return ans;
}

int partition(int A, vector<int> &B)
{ //1013 & on ib
    int sum = 0;
    vector<int> prefixSum(A);
    vector<int> suffixSum(A);

    for (int i = 0; i < A; i++)
    {
        sum += B[i];
        prefixSum[i] = i > 0 ? prefixSum[i - 1] + B[i] : B[0];
    }

    if (sum % 3 != 0)
        return 0;

    suffixSum[A - 1] = B[A - 1];
    for (int i = A - 2; i >= 0; i--)
    {
        suffixSum[i] = suffixSum[i + 1] + B[i];
    }

    int ans = 0;
    for (int i = 0; i < A; i++)
    {
        if (prefixSum[i] == sum / 3)
        {
            for (int j = i + 2; j < A; j++)
            {
                if (suffixSum[j] == sum / 3)
                    ans++;
            }
        }
    }
    return ans;
}

. //ib https://www.interviewbit.com/problems/perfect-peak-of-array
int perfectPeak(vector<int> &A)
{
    int maximum = A[0], n = A.size();
    for (int i = 1; i < n - 1; i++)
    {
        if (A[i] > maximum)
        {
            maximum = A[i];
            A[i] = A[i] * (-1);
        }
    }

    int minimum = A[n - 1];
    for (int i = n - 2; i >= 1; i--)
    {
        if (A[i] < 0 && abs(A[i]) < minimum)
            return 1;
        minimum = min(abs(A[i]), minimum);
    }

    return 0;
}

.//https://www.interviewbit.com/problems/maximum-sum-triplet/
public static int maximumSumTriplet(int[] A) { 
    if (A.length < 3)
        return 0;
    int n = A.length;
    int maxSuffixArray[] = new int[n];
    maxSuffixArray[n - 1] = A[n - 1];
    int tripletSum = Integer.MIN_VALUE;
    TreeSet<Integer> set = new TreeSet<Integer>();
    set.add(A[0]);
    for (int i = n - 2; i >= 0; i--) {
        maxSuffixArray[i] = Math.max(maxSuffixArray[i + 1], A[i]);
    }
    for (int j = 1; j < n - 1; j++) {
        Integer firstValue = set.lower(A[j]); // lower,higher,ceiling,floor
        int lastValue = maxSuffixArray[j + 1];
        if (firstValue != null && lastValue > A[j])
            tripletSum = Math.max(tripletSum, firstValue + A[j] + lastValue);
        set.add(A[j]);
    }
    return tripletSum;
}

//334
 public boolean increasingTriplet(int[] nums) {
    int n=nums.length;
    if(n<3)return false;
    int i=Integer.MAX_VALUE,j=Integer.MAX_VALUE;
    for(int ele:nums)
    {
        if(ele<=i) //= for [1,1,1]
            i=ele;
        else if(ele<=j)
            j=ele;
        else return true;
    }
    return false;
}

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/range_addition/ojquestion
public static int[] range_addition(int length, int[][] queries) {
    int[] arr = new int[length];

    for (int i = 0; i < queries.length; i++) {
        int startIndex = queries[i][0];
        int endIndex = queries[i][1] + 1;

        arr[startIndex] += queries[i][2];
        if (endIndex < length)
            arr[endIndex] += -(queries[i][2]);
    }

    int sum = 0;
    for (int i = 0; i < length; i++) {
        sum += arr[i];
        arr[i] = sum;
    }
    return arr;
}

// 598
int maxCount(int m, int n, vector<vector<int>> &ops)
{
    if (ops.size() == 0)
        return m * n; //0 ke hi saare

    int to = ops.size();  //total oprn
    int noOfElement = ops[0][0] * ops[0][1];
    for (int i = 1; i < ops.size(); i++)
    {

        ops[i][0] = min(ops[i][0], ops[i - 1][0]);
        ops[i][1] = min(ops[i][1], ops[i - 1][1]);
    }

    noOfElement = ops[to - 1][0] * ops[to - 1][1];
    return noOfElement;
}

// 769
int maxChunksToSorted(vector<int> &arr)
{
    int reach = 0, chunk = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        reach = max(reach, arr[i]);
        if (i == reach)
            chunk++;
    }
    return chunk;
}

.// 768
public int maxChunksToSorted(int[] arr) {
    int chunk = 0;
    int n = arr.length;
    int[] leftMax = new int[n];
    int[] rightMin = new int[n + 1];

    leftMax[0] = arr[0];
    for (int i = 1; i < n; i++)
        leftMax[i] = Math.max(leftMax[i - 1], arr[i]);

    rightMin[n] = Integer.MAX_VALUE;
    for (int i = n - 1; i >= 0; i--)
        rightMin[i] = Math.min(rightMin[i + 1], arr[i]);

    for (int i = 0; i < n; i++) {
        if (leftMax[i] <= rightMin[i + 1])
            chunk++;
    }
    return chunk;
}

.//532
public int findPairs(int[] nums, int k) {
    HashMap<Integer,Integer>map=new HashMap<>();
    
    for(int val:nums)
        map.put(val,map.getOrDefault(val,0)+1);
    
    int count=0;
    for(int key:map.keySet())
    {
        if((map.containsKey(key+k) && k>0  || (k==0 && map.get(key)>1) )
            count++;
    }
    return count;
}

public int findPairs_(int[] nums, int k) {
    Arrays.sort(nums);
    int i=0,j=1,count=0;
    while(j<nums.length)
    {
        int diff=Math.abs(nums[j]-nums[i]);
        if(diff==k)
        {
            count++;
            while(++j<nums.length && nums[j]==nums[j-1]);
        }
        else if(diff<k)
            j++;
        else{
            while(++i<nums.length && nums[i]==nums[i-1]);
            if(i==j)j++;
        }
    }
    
    return count;
}

// https://www.lintcode.com/problem/wiggle-sort/
void wiggleSort(vector<int> &a) {
    int n=a.size();
    for(int i=0;i<n;i++)
    {
        if(i%2==0 && i+1<n && a[i]>a[i+1])
        swap(a[i],a[i+1]);

        else if(i%2!=0 && i+1<n && a[i]<a[i+1])
        swap(a[i],a[i+1]);
    }
}

//324
void wiggleSort(vector<int>& nums) {
    sort(nums.begin(),nums.end());
    int i=1,j=nums.size()-1,k=0,n=nums.size();
    vector<int>ans(n);
    while(i<n)
    {
        ans[i]=nums[j--];
        i+=2;
    }
    i=0;
    while(i<n)
    {
        ans[i]=nums[j--];
        i+=2;
    }

    for(int i=0;i<nums.size();i++)
    nums[i]=ans[i];
}

// 238
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = nums[0];
    for (int i = 1; i < n; i++)
        left[i] = nums[i] * left[i - 1];

    right[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--)
        right[i] = nums[i] * right[i + 1];

    int[] ans = new int[n];

    for (int i = 0; i < n; i++) {
        int lprod = i == 0 ? 1 : left[i - 1];
        int rprod = i == n - 1 ? 1 : right[i + 1];
        ans[i] = lprod * rprod;
    }

    return ans;
}

public int[] productExceptSelf2(int[] nums) { // o(1) space

    int n = nums.length;
    int[] ans = new int[n];

    ans[0] = 1;
    for (int i = 1; i < n; i++) {
        ans[i] = nums[i - 1] * ans[i - 1];
    }

    int right = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
        ans[i] = ans[i] * right;
        right *= nums[i];
    }

    return ans;
}

//https://www.interviewbit.com/problems/maximum-absolute-difference/
int maxAbsoluteDiff(vector<int> &arr)
{

    int maximum1 = -1e9;
    int maximum2 = -1e9;
    int minimum1 = 1e9;
    int minimum2 = 1e9;

    for (int i = 0; i < arr.size(); i++)
    {
        maximum1 = max(maximum1, arr[i] + i);
        maximum2 = max(maximum2, arr[i] - i);

        minimum1 = min(minimum1, arr[i] + i);
        minimum2 = min(minimum2, arr[i] - i);
    }

    return max(maximum1 - minimum1, maximum2 - minimum2);
}                

//min xor value --ib
//https://www.interviewbit.com/problems/min-xor-value/
int findMinXor(vector<int> &arr)
{
    sort(arr.begin(), arr.end());

    int min = (int)1e9;
    for (int i = 0; i < arr.size() - 1; i++)
    {
        int val = arr[i] ^ arr[i + 1];
        if (val < min)
        {
            min = val;
        }
    }
    return min;
}

//https://www.interviewbit.com/problems/max-min-05542f2f-69aa-4253-9cc7-84eb7bf739c4/
int maximumMinimum(vector<int> &A)
{

    int n = A.size();
    int max = A[0], min = A[0];

    for (int i = 1; i < n; i++)
    {
        if (A[i] > max)
            max = A[i];
        else if (A[i] < min)
            min = A[i];
    }

    //return Math.max(…A) + Math.min(…A);
    return max + min;
}


//152
int maxProduct(vector<int> &nums)
{
    int n = nums.size();
    int res = -1e8, left = 0, right = 0;  //left right ya 1
    for (int i = 0; i < n; i++)
    {
        left = (left == 0 ? 1 : left) * nums[i]; //left=(left==0?1:left*nums[i]); cant write like this 
        right = (right == 0 ? 1 : right) * nums[n - 1 - i];
        res = max(res, max(left, right));
    }
    return res;
}

//1855
 public int maxDistance(int[] num1, int[] num2) {
        int i=0,j=0,n1=num1.length,n2=num2.length,ans=0;
        while(i<n1 && j<n2)
        {
            if(num1[i]<=num2[j])
                j++;
            else i++;
            
            ans=Math.max(ans,j-i);
        }
        return ans==0?ans:ans-1;
    }

void merge(long long arr1[], long long arr2[], int n, int m) 
    { 
        int gap=(int)(ceil(float(n+m)/2)),total=n+m;
        while(gap!=0)
        {
            int i=0,j=gap;
            while(j<total)
            {
                if(i>=n && j>=n && arr2[i-n]>arr2[j-n])
                {
                    swap(arr2[i-n],arr2[j-n]);
                }
                else if(j>=n && i<n && arr1[i]>arr2[j-n])
                {
                    swap(arr1[i],arr2[j-n]);
                }
                else if(j<n && arr1[i]>arr1[j]){
                     swap(arr1[i],arr1[j]);
                }
                i++;j++;
            }
            if(gap==1)
            gap=0;
            else
            gap=(int)(ceil(float(gap)/2));
        }
    } 

    