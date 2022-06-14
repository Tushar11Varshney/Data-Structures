  #include <iostream>
#include <vector>
#include <cmath>
#include <unordered_map>
#include <algorithm>
using namespace std;

// https://practice.geeksforgeeks.org/problems/who-will-win-1587115621/1# return 1 if present else -1
int binarySearch(vector<int> &nums, int target)
{
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

// leetcode 74
bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return false;
    int n = matrix.size(), m = matrix[0].size(), si = 0, ei = n * m - 1, mid = 0, val = 0;
    while (si <= ei) //= reason ye tou normal binary search hai
    {
        mid = (si + ei) / 2;
        val = matrix[mid / m][mid % m];
        if (val == target)
            return true;
        else if (val < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return false;
}

// 374
int guessNumber(int n)
{
    int si = 1, ei = n;
    while (si <= ei)
    {
        int mid = (si + (ei - si) / 2);
        if (guess(mid) == 0)  //0 sahi guess ki
            return mid;
        else if (guess(mid) == -1) //-1 badi guess krli
            ei = mid - 1;
        else
            si = mid + 1;
    }

    return -1;
}

// 278
int firstBadVersion(int n)
{
    int si = 1, ei = n;
    while (si < ei)
    {
        int mid = (si + (ei - si) / 2);
        if (!isBadVersion(mid))
            si = mid + 1;
        else if (isBadVersion(mid))
            ei = mid;
    }
    return si;
}

// leetcode 34
vector<int> searchRange(vector<int>& nums, int target) {
    int mid = 0, si = 0, ei = nums.size() - 1, idx1 = -1, idx2 = -1, midCopy = mid;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
        {
            idx1 = idx2 = midCopy = mid;
            while (--mid >= 0 && nums[mid] == target)
                idx1 = mid;
            while (++midCopy < nums.size() && nums[midCopy] == target)
                idx2 = midCopy;
            return {idx1,idx2};
        }
        else if (nums[mid] > target)
            ei = mid - 1;
        else
            si = mid + 1;
    
    }
    return {-1, -1};  
}

// sir
int binarySearchFirstIndex(vector<int> &nums, int target)
{
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
        {
            if (mid - 1 >= 0 && nums[mid - 1] == target) 
                ei = mid - 1;
            else
                return mid;
        }
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

// https://practice.geeksforgeeks.org/problems/find-transition-point/1#
int transitionPoint(int arr[], int n)
{
    int si = 0, ei = arr.length - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == 1)
        {
            if (mid - 1 >= 0 && arr[mid - 1] == 1)
                ei = mid - 1;
            else
                return mid;
        }
        else if (arr[mid] == 0)
            si = mid + 1;
    }

    return -1;
}

int binarySearchLastIndex(vector<int> &nums, int target)
{
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
        {
            if (mid + 1 < nums.size() && nums[mid + 1] == target)
                si = mid + 1;
            else
                return mid;
        }
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

vector<int> searchRange(vector<int> &nums, int target)
{
    return  {binarySearchFirstIndex(nums, target), binarySearchLastIndex(nums, target)};
}

int binarySearchNearestIndex(vector<int> &nums, int target)
{
    int size = nums.size();
    if (size == 0)
        return 0;
    if (target <= nums[0] || target >= nums[size - 1])
        return target <= nums[0] ? 0 : size - 1; 

    int si = 0, ei = size - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1; // ei = floor, si = ceil.
        else
            ei = mid - 1;
    }
    return target - nums[ei] < nums[si] - target ? ei : si;
}

// leetcode 35
int searchInsert(vector<int> &nums, int target)
{
    int size = nums.size();
    if (size == 0)
        return -1;
    if (target <= nums[0] || target > nums[size - 1]) 
        return target <= nums[0] ? 0 : size;   
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return si; // si answer btata h
}

int searchInsert(vector<int> &nums, int target)
{
    int si = 0, ei = nums.size(), mid = 0; // in binary srch it is not fixed that ei=mid-1..it depend on observation
    while (si < ei)                        //= pr chlne pr loop firse while loop infinite
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid;
    }
    return ei; // ya si dono answer btate hain not mid

    // ArrayList<Integer>al=new ArrayList<>();
    // al.add(10);al.add(18);al.add(13);al.add(19);al.add(20);

    // int idx=Collections.binarySearch(al,11);  //works on both sorted and unsorted
    // System.out.println(idx);

    // vector<int>arr={10,11,12,165,98};  //works on sorted
    // cout<<binary_search(arr.begin(),arr.end(),98);  //return true or false
    // cout<<*lower_bound(arr.begin(),arr.end(),100);  function returns the index of the next smallest number just greater than or equal to that number.
}

// 658
vector<int> findClosestElements(vector<int> &arr, int k, int x)
{
    int n = arr.size();
    if (x <= arr[0])
    {
        vector<int> ans(arr.begin(), arr.begin() + k);  
        return ans;
    }
    else if (x >= arr[n - 1])
    {
        vector<int> ans(arr.begin() + n - k, arr.end()); 
        return ans;
    }
    else
    {
        int idx = searchInsert(arr, x); // sir wala
        // int idx = (lower_bound(list.begin(), list.end(), ele) - list.begin());  //the lower bound function gives address so if address is 12 and adrress of begin is 4 so it gives index (12-4)/4 (automatically divides)
        int si = max(0, idx - k);
        int ei = min(idx + k, n - 1); // can be idx+k-1

        while (ei - si >= k) // !=k-1
        {
            if ((x - arr[si]) > (arr[ei] - x))
                si++;
            else
                ei--;
        }
        vector<int> ans(arr.begin() + si, arr.begin() + ei + 1); // not included thats why +1
        return ans;
    }
}

// leetcode 240
bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    int i = matrix.size() - 1, j = 0, n = matrix[0].size();
    while (i >= 0 && j < n)
    {
        if (matrix[i][j] == target)
            return true;
        else if (matrix[i][j] > target)
            i--;
        else
            j++;
    }
    return false;
}

bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return false;
    int n = matrix.size(), m = matrix[0].size(), si = 0, ei = n - 1, mid = 0, val = 0, row;

    while (si <= ei)
    {
        mid = (si + ei) / 2;
        val = matrix[mid][0];

        if (val == target)
            return true;
        else if (val < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    if (ei >= 0 && ei < n)
        row = ei;
    else
        return false;

    si = 0;
    ei = m - 1;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        val = matrix[row][mid];

        if (val == target)
            return true;
        else if (val < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return false;
}

// =======
// https://practice.geeksforgeeks.org/problems/buildings-receiving-sunlight3032/1#
public static int longest(int arr[], int n)
{
    int max = -(int)1e9, count = 0;
    for (int ele : arr)
    {
        if (ele >= max)
        {
            count++;
            max = ele;
        }
    }
    return count;
}

// https://practice.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1#
long long findMinDiff(vector<long long> arr, long long n, long long m)
{
    sort(arr.begin(), arr.end());
    int i = 0, j = m - 1;
    long long ans = 1e9;
    while (j < n)
    {
        ans = min(ans, arr[j] - arr[i]);
        i++;
        j++;
    }
    return ans;
}

// https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/count-the-triplets-official/ojquestion-sum of two ele equal 3rd
public static int countTriplets(int[] arr)
{
    Arrays.sort(arr);
    int count = 0;
    for (int i = arr.length - 1; i >= 2; i--)
    {
        int fixed = arr[i];
        int l = 0, r = i - 1;
        while (l < r)
        {
            int data = arr[l] + arr[r];
            if (data == fixed)
            {
                count++;  //bcz equal chahiey
                l++;
                r--;
            }
            else if (data > fixed)
                r--;
            else
                l++;
        }
    }
    return count;
}

// 611
int triangleNumber(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    int count = 0;
    for (int i = nums.size() - 1; i >= 2; i--)
    {
        int fixed = nums[i];
        int l = 0, r = i - 1;
        while (l < r)
        {
            if (nums[l] + nums[r] > fixed)
            {
                count += r - l;  //bcz gretaer than chahiye
                r--;
            }
            else
                l++;
        }
    }
    return count;
}

// https://practice.geeksforgeeks.org/problems/count-zeros-in-a-sorted-matrix/1
int countZeros(vector<vector<int>> A)
{
    int n = A.size(), i = 0, j = n - 1, count = 0;
    while (i < n && j >= 0)
    {
        if (A[i][j] == 1)
            j--;
        else if (A[i][j] == 0)
        {
            count += j + 1;
            i++;
        }
    }
    return count;
}

// https://practice.geeksforgeeks.org/problems/roof-top-1587115621/1#
static int maxStep(int A[], int N)
{
    int maxConsStep = 0, maxStepTillNow = 0;
    for (int i = 1; i < A.length; i++)
    {
        if (A[i] > A[i - 1])
        {
            maxStepTillNow++;
            maxConsStep = Math.max(maxConsStep, maxStepTillNow);
        }
        else
            maxStepTillNow = 0;
    }

    return maxConsStep;
}

// https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/find_pair_with_given_difference/ojquestion
public static void findPairWithGivenDiffernce(int[] arr, int target)
{
    Arrays.sort(arr);
    int i = 0, j = 1, flag = 0;
    while (j < arr.length)
    {
        int diff = arr[j] - arr[i];
        if (diff == target)
        {
            flag = 1;

            System.out.println(arr[i] + " " + arr[j]);
            //skip duplicates
            int valJ = arr[j++];
            while (j < arr.length && arr[j] == valJ)
                j++;

            if (j < arr.length)
            {
                int valI = arr[i++];
                while (i < arr.length && arr[i] == valI)
                    i++;
            }
        }
        else if (diff < target)
            j++;
        else
            i++;
    }

    if (flag == 0)
        System.out.println(-1);
}

// 69
public int mySqrt(int x)
{
    if (x == 0)
        return 0;
    long ans = 1;
    for (long i = 2; i * i <= x; i++)
        ans = i;

    return (int)ans;
}

int mySqrt_(int x)
{
    int start = 1, end = x, ans = 0;
    while (start <= end)
    {
        int mid = (start + (end - start) / 2);
        if (mid <= (x / mid))
        {
            ans = mid;
            start = mid + 1;
        }
        else
            end = mid - 1;
    }
    return ans;
}

// 50
public double myPow(double x, int n)    
{
    if (n == 0)
        return 1.0;
    else if (n < 0)
    {
        x = 1 / x;
        double ans = myPow(x, -(n / 2));
        // for n=-2147483648 if we write -n/2 then -n=2147483638 which is higher than
        // int_max so it will overflow so phle divide then positive bnao.
        return n % 2 == 0 ? ans * ans : ans * ans * x;
    }
    else
    {
        double ans = myPow(x, n / 2);
        return n % 2 == 0 ? ans * ans : ans * ans * x;
    }
}

// ===========
// 1
vector<int> twoSum(vector<int> &nums, int target)
{
    // vector<int> ans;
    unordered_map<int, int> map;
    for (int i = 0; i < nums.size(); i++)
    {
        if (map.find(target - nums[i]) != map.end())
        {
            // ans.push_back(map[target - nums[i]]);// ans.push_back(i);// return ans;
            return {map[target - nums[i]], i};
        }
        map[nums[i]] = i;
    }
    // ans.push_back(-1);// ans.push_back(-1);// return ans;
    return {-1, -1};
}

// leetcode 167
vector<int> twoSum(vector<int> &numbers, int target) // target sum pair mein sort then yhi technique
{
    int si = 0, ei = numbers.size() - 1, data = 0;
    while (si < ei) // cant use same element twice so here dont use =
    {
        data = numbers[si] + numbers[ei];
        //in qs-Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
        if (data == target)
            return {si + 1, ei + 1};
        if (data < target)
            si++;
        else
            ei--;
    }
    return {-1, -1};
}

public static void targetSumPair(int[] arr, int target)
{
    int i = 0, j = arr.length - 1;
    Arrays.sort(arr);
    while (i < j)
    {
        int sum = arr[i] + arr[j];
        if (sum == target)
        {
            System.out.println(arr[i] + ", " + arr[j]);
            i++;
            j--;
        }
        else if (sum < target)
            i++;
        else
            j--;
    }
}

// 15
vector<vector<int>> threeSum(vector<int> &arr)
{
    vector<vector<int>> ans;
    if (arr.size() < 3)
        return ans;
    sort(arr.begin(), arr.end());
    int target = 0, data = 0, n = arr.size();
    for (int i = 0; i < n; i++)
    {
        while (i != 0 && i < n && arr[i] == arr[i - 1])
            i++;
        int j = i + 1, k = n - 1;
        while (j < k)
        {
            data = arr[i] + arr[j] + arr[k];
            if (data == target) // cant do data+Arr[j]+arr[k] here then if false then how can it check data<target or not in next condn.
            {
                ans.push_back({arr[i], arr[j], arr[k]});
                j++;
                k--;
                while (j < k && arr[j] == arr[j - 1])
                    j++;
                while (j < k && arr[k] == arr[k + 1])
                    k--;
            }
            else if (data < target)
                j++;
            else
                k--;
        }
    }
    return ans;
}

//923
int mod=(int)1e9+7;
public int threeSumMulti(int[] arr, int target) {
    Arrays.sort(arr);
    int ans=0,n=arr.length;
    for(int i=0;i<arr.length;i++)
    {   
        int j=i+1,k=n-1;
        while(j<k)
        {
            int data=arr[i]+arr[j]+arr[k];
            if(data==target)
            {   
                if(arr[j]==arr[k])
                {
                    int r=k-j+1;
                    //for this we calculate the number of all possible combinations we can make to pick 2 elements i.e. nC2
                    ans=(ans%mod+(r*(r-1)/2)%mod)%mod;
                    break;
                }
                int c_j=1,c_k=1;
                while(j+1<k && arr[j]==arr[j+1]){
                    j++;
                    c_j++;
                }
                while(j+1<k && arr[k]==arr[k-1]){
                    k--;
                    c_k++;
                }
                ans=(ans%mod+((c_j)*(c_k))%mod)%mod;
                j++;k--;
            }
            else if(data<target)
                j++;
            else k--;
        }
    }
    return ans%mod;
}
    
// 18
vector<vector<int>> fourSum(vector<int> &nums, int target)
{
    sort(nums.begin(), nums.end());
    int n = nums.size();
    vector<vector<int>> ans;
    for (int i = 0; i < n; i++)
    {
        while (i != 0 && i < n && nums[i] == nums[i - 1])
            i++;
        for (int j = i + 1; j < n; j++)
        {
            while (j != i + 1 && j < n && nums[j] == nums[j - 1])
                j++;
            int k = j + 1, l = n - 1;
            while (k < l)
            {
                int subTarget = target - nums[i] - nums[j];
                //[1e9, 1e9, 1e9, 1e9] 0 isme overflow krjayga so target - use kiya
                int data = nums[k] + nums[l];
                if (data == subTarget)
                {
                    ans.push_back({nums[i], nums[j], nums[k], nums[l]});
                    k++;
                    l--;

                    while (k < l && nums[k] == nums[k - 1])
                        k++;
                    while (k < l && nums[l] == nums[l + 1])
                        l--;
                }
                else if (data < subTarget)
                    k++;
                else
                    l--;
            }
        }
    }
    return ans;
}

vector<vector<int>> twoSum(vector<int> &arr, int si, int ei, int target)
{
    int data, i = si, j = ei;
    vector<vector<int>> smallAns;
    while (i < j)
    {
        data = arr[i] + arr[j];
        if (data == target)
        {
            smallAns.push_back({arr[i], arr[j]});
            i++;
            j--;

            while (i < j && arr[i] == arr[i - 1])
                i++;
            while (i < j && arr[j] == arr[j + 1])
                j--;
        }
        else if (data < target)
            i++;
        else
            j--;
    }
    return smallAns;
}

vector<vector<int>> threeSum(vector<int> &arr, int si, int ei, int target)
{
    vector<vector<int>> res;
    for (int i = si; i <= ei; i++)
    {
        if (i != si && i <= ei && arr[i] == arr[i - 1])
            continue;
        vector<vector<int>> smallAns = twoSum(arr, i + 1, ei, target - arr[i]);
        if (smallAns.size() > 0)
        {
            for (vector<int> ar : smallAns)
            {
                ar.push_back(arr[i]);
                res.push_back(ar);
            }
        }
    }
    return res;
}

vector<vector<int>> fourSumGeneric(vector<int> &arr, int si, int ei, int target)
{
    vector<vector<int>> res;
    for (int i = si; i <= ei; i++)
    {
        if (i != si && i <= ei && arr[i] == arr[i - 1])
            continue; // while lgake overflow hone ke chance rhenge kyunki aage chlega fn but in continue it skips iteration
        vector<vector<int>> smallAns = threeSum(arr, i + 1, ei, target - arr[i]); 
        //yahan overflow [0,0,0,0] 0
        if (smallAns.size() > 0)
        {
            for (vector<int> ar : smallAns)
            {
                ar.push_back(arr[i]);
                res.push_back(ar);
            }
        }
    }
    return res;
}

vector<vector<int>> fourSum(vector<int> &nums, int target)
{
    vector<vector<int>> ans;
    if (nums.size() < 4)
        return ans;
    sort(nums.begin(), nums.end());
    ans = fourSumGeneric(nums, 0, nums.size() - 1, target);
    return ans;
}

// 454
int fourSumCount(vector<int> &A, vector<int> &B, vector<int> &C, vector<int> &D)
{
    unordered_map<int, int> map;
    for (int ele1 : A)
    {
        for (int ele2 : B)
        {
            map[ele1 + ele2]++;
        }
    }

    int count = 0;
    for (int ele1 : C)
    {
        for (int ele2 : D)
        {
            // if(map[-(ele1+ele2)])
            if (map.find(-(ele1 + ele2)) != map.end()) // ye zyada tez chlta hai because agr element nhi milta tou upr waala jo if hai wo us element ke across 0 bhi daalta h
                count += map[-(ele1 + ele2)];
        }
    }
    return count;
}

int twoSumCount(vector<int> &arr1, vector<int> &arr2) // array mein fast
{
    sort(arr1.begin(), arr1.end());
    sort(arr2.begin(), arr2.end());

    int i = 0, n = arr1.size(), j = n - 1, count = 0, ABCount = 0, CDCount = 0;
    while (i < n && j >= 0)
    {
        int data = arr1[i] + arr2[j];
        if (data == 0)
        {
            ABCount = 1;
            CDCount = 1;
            while (++i < n && arr1[i] == arr1[i - 1])
                ABCount++;
            while (--j >= 0 && arr2[j] == arr2[j + 1])
                CDCount++;

            count += ABCount * CDCount;
        }
        else if (data < 0)
            i++;
        else
            j--;
    }
    return count;
}

int fourSumCount(vector<int> &A, vector<int> &B, vector<int> &C, vector<int> &D)
{
    vector<int> ABCombination;
    vector<int> CDCombination;  // all array of same len
    int n = A.size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            ABCombination.push_back(A[i] + B[j]);
            CDCombination.push_back(C[i] + D[j]);
        }
    }
    return twoSumCount(ABCombination, CDCombination);
}

//==========
// 724
public int pivotIndex(int[] nums)
{
    int n = nums.length;
    int prefixSum[] = new int[n];
    int suffixSum[] = new int[n];

    for (int i = 1; i < n; i++)
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];

    for (int i = n - 2; i >= 0; i--)
        suffixSum[i] = suffixSum[i + 1] + nums[i + 1];

    for (int i = 0; i < n; i++)
    {
        if (prefixSum[i] == suffixSum[i])
            return i;
    }

    return -1;
}

public int pivotIndex_(int[] nums)
{
    int sum = 0, l = 0, r = 0;
    for (int ele : nums)
        sum += ele;

    r = sum;
    for (int i = 0; i < nums.length; i++)
    {
        r -= nums[i];
        if (l == r)
            return i;

        l += nums[i];
    }

    return -1;
}

// 33
int search(vector<int> &arr, int target)
{
    int si = 0, ei = arr.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (arr[mid] == target)
            return mid;
        else if (arr[mid] < arr[ei])
        {
            if (target > arr[mid] && target <= arr[ei])
                si = mid + 1;
            else
                ei = mid - 1;
        }
        else
        {
            if (target >= arr[si] && target < arr[mid])
                ei = mid - 1;
            else
                si = mid + 1;
        }
    }
    return -1;
}

// 81
int search(vector<int> &arr, int target)
{
    int si = 0, ei = arr.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (arr[mid] == target || arr[si] == target) //[3 1 1/3] [1 3 1 1 1/3] why si  ...agr hi shift hoga tou arr[ei]==trgt
            return true;
        else if (arr[mid] < arr[ei]) //[1 1 3 1] 3 why no equal
        {
            if (target > arr[mid] && target <= arr[ei])
                si = mid + 1;
            else
                ei = mid - 1;
        }
        else if (arr[si] < arr[mid]) //[1 3 1 1 1] 3 why not equal
        {
            if (target >= arr[si] && target < arr[mid])
                ei = mid - 1;
            else
                si = mid + 1;
        }
        else
            si++; // ya ei--;
    }
    return false;
}

// 153
int findMin(vector<int> &arr)
{
    int lo = 0, hi = arr.size() - 1;

    while (lo < hi)
    {
        int mid = (lo + hi) / 2;
        if (arr[mid] < arr[hi])
            hi = mid;
        else if (arr[lo] <= arr[mid])
            lo = mid + 1; // check on 1 0(for = reason)
        else
            lo++; // for duplicate
    }
    return arr[lo]; // hi ya low.
}

int findMin(vector<int> &arr)
{
    int lo = 0, hi = arr.size() - 1;
    if (arr[lo] <= arr[hi]) //[1] =
        return arr[lo];

    while (lo <= hi)
    {
        int mid = (lo + hi) / 2;
        if (arr[mid] > arr[mid + 1])
            return arr[mid + 1];
        else if (arr[mid] < arr[mid - 1])
            return arr[mid];
        else if (arr[lo] <= arr[mid]) // humein sorted region mein kabhi pivot nhi milne waala
            lo = mid + 1;
        else if (arr[mid] <= arr[hi])
            hi = mid - 1;
    }

    return -1;
}

// https://practice.geeksforgeeks.org/problems/rotation4723/1
int findKRotation(int arr[], int n)
{
    int lo = 0, hi = arr.length - 1;
    if (arr[lo] <= arr[hi])
        return 0;

    while (lo <= hi)
    {
        int mid = (lo + hi) / 2;
        if (arr[mid] > arr[mid + 1])
            return mid + 1;
        else if (arr[mid] < arr[mid - 1])
            return mid;
        else if (arr[lo] <= arr[mid])
            lo = mid + 1;
        else if (arr[mid] <= arr[hi])
            hi = mid - 1;
    }
    return -1;
}

// ib
int binarySearch(vector<int> &nums, int target, int si, int ei)
{
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

int reverseBinarySearch(vector<int> &nums, int target, int si, int ei)
{
    while (si >= ei)
    {
        int mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (target < nums[mid])
            ei = mid + 1;
        else
            si = mid - 1;
    }
    return -1;
}

int searchBitonicArray(vector<int> &arr, int target)
{
    int si = 0, ei = arr.size() - 1, bitonicPt = 0;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
        {
            bitonicPt = mid;
            break;
        }
        else if (arr[mid] > arr[mid - 1])
            si = mid + 1;
        else
            ei = mid - 1;
    }
    int ans1 = binarySearch(arr, target, 0, bitonicPt);
    if (ans1 != -1)
        return ans1;
    return reverseBinarySearch(arr, target, arr.size() - 1, bitonicPt + 1);
}

//=======
// 1011
bool canWeShipWithIn_DDays(int leastWgt, vector<int> &weights, int days)
{
    int count = 0, sum = 0;
    for (int i = 0; i < weights.size(); i++)
    {
        sum += weights[i];
        if (sum > leastWgt)
        {
            count++;
            if (count > days)
                return false;
            sum = 0;
            i = i - 1;
        }
    }
    count++;
    return count > days ? false : true;
}

int shipWithinDays(vector<int> &weights, int days)
{
    int si = 0, ei = 0;
    for (int ele : weights)
        ei += ele;

    while (si < ei)
    {
        int mid = (si + (ei - si) / 2);
        if (canWeShipWithIn_DDays(mid, weights, days))
            ei = mid;
        else
            si = mid + 1;
    }
    return si; // ya ei
}

bool canWeShipWithIn_DDays(int leastWgt, vector<int> &weights, int days)
{
    int count = 1, sum = 0;
    for (int i = 0; i < weights.size(); i++)
    {
        sum += weights[i];
        if (sum > leastWgt)
        {
            count++;
            if (count > days)
                return false;
            sum = weights[i];
        }
    }
    return count <= days;  //true
}

int shipWithinDays(vector<int> &weights, int days)
{
    int si = 0, ei = 0;
    for (int ele : weights)
    {
        si = max(si, ele);
        ei += ele;
    }

    if (weights.size() == days)
        return si; // agr 10 weights hain 10 hi din hai tou koi bhi din khaali ni hoskta tou least weight capacity should be maximum.
    while (si < ei)
    {
        int mid = (si + (ei - si) / 2);
        if (canWeShipWithIn_DDays(mid, weights, days))
            ei = mid;
        else
            si = mid + 1;
    }
    return ei;
}

// https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/allocate_minimum_number_of_pages/ojquestion
public static boolean isPossible(int[] arr, int load, int m)
{
    int student = 1, sum = 0;
    for (int ele : arr)
    {
        sum += ele;
        if (sum > load)
        {
            student++;
            if (student > m)
                return false;
            sum = ele;
        }
    }
    return student <= m;
}

public static int minPages(int[] arr, int m)
{
    int sum = 0, max = 0;
    for (int ele : arr)
    {
        sum += ele;
        max = Math.max(max, ele);
    }

    int lo = max, hi = sum;
    while (lo < hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (isPossible(arr, mid, m))
            hi = mid;
        else
            lo = mid + 1;
    }
    return lo;
}

// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1#
static boolean Possible_ToPaint(int arr[], long paintLength, int noOfPainters)
{
    int painters = 1;
    long sum = 0;
    for (int ele : arr)
    {
        sum += ele;
        if (sum > paintLength)
        {
            painters++;
            if (painters > noOfPainters)
                return false;
            sum = ele;
        }
    }
    return painters <= noOfPainters; //true // yahan return true ni hoga vo tb jab si=max pr set na hoke 0 pr set ho
}

static long minTime(int[] arr, int n, int k)
{
    long sum = 0L;
    int max = 0;
    for (int ele : arr)
    {
        max = Math.max(max, ele);
        sum += ele;
    }

    long si = max, ei = sum; // si ko max set krna pdega vrna aur min chle jaega jo ki galat answer hoga
    while (si < ei)
    {
        long mid = si + (ei - si) / 2;
        if (Possible_ToPaint(arr, mid, k))
            ei = mid;
        else
            si = mid + 1;
    }

    return si;
}

// 410
public static boolean isPossible_ToSplit(int[] arr, int minSum, int m)
{
    int partition = 1, sum = 0;
    for (int ele : arr)
    {
        sum += ele;
        if (sum > minSum)
        {
            partition++;
            if (partition > m)
                return false;
            sum = ele;
        }
    }
    return partition <= m;
}

public int splitArray(int[] arr, int m)
{
    int sum = 0, max = 0;
    for (int ele : arr)
    {
        sum += ele;
        max = Math.max(max, ele);
    }

    int lo = max, hi = sum;
    while (lo < hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (isPossible_ToSplit(arr, mid, m)) // kitne subarray ki zrurat pdegi ki maximum mid ho
            hi = mid;
        else
            lo = mid + 1;
    }
    return lo;
}

// 875
int isPossibletoEat(vector<int> &piles, int eatingSpeed, int H) // array mein we dont write & because pointer passes but in vector & is ram ka hi ek pointer so that copy constructor dont fire.
{
    int totalHour = 0;
    for (int i = piles.size() - 1; i >= 0; i--) // sort krne se ye faeda jab kabhi H exceed hoga tou hum beech mein hi ruk jaenge break lgakr peeche se chalakr
    {
        totalHour += ceil(piles[i] / (eatingSpeed * 1.0)); // ceil returns integer
        if (totalHour > H)
            return false;
    }
    return true;
}

int minEatingSpeed(vector<int> &piles, int H)
{
    //minspeed cannot be maximum value and ei!=sum bcz koko like to eat slowly.
    // sort(piles.begin(),piles.end());
    int n = piles.size();
    // int minSpeed=1,maxSpeed=piles[n-1],eatingSpeed=0;
    int minSpeed = 1, maxSpeed = 1e9, eatingSpeed = 0;
    while (minSpeed < maxSpeed)
    {
        // 1 <= piles[i] <= 10^9
        eatingSpeed = (minSpeed) + (maxSpeed - minSpeed) / 2; // here though maxSpeed can be 1e9(given) in constraint but if Int_max then eatingspeed overflow krskti hai
        if (isPossibletoEat(piles, eatingSpeed, H))
            maxSpeed = eatingSpeed;
        else
            minSpeed = eatingSpeed + 1;
    }
    return maxSpeed; // ya minspeed ,not eating speed
}

// 1283
public boolean divisor_LessThanThreshold(int[] nums, int threshold, int divisor)
{
    int sum = 0;
    for (int ele : nums)
    {
        // System.out.println(Math.ceil((ele*1.0)/divisor));
        sum += Math.ceil((ele * 1.0) / divisor);
        if (sum > threshold)
            return false;
    }
    return true;
}

public int smallestDivisor(int[] nums, int threshold)
{
    int max = 0;
    for (int ele : nums)
        max = Math.max(max, ele);

    int lo = 1, hi = max;
    while (lo < hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (divisor_LessThanThreshold(nums, threshold, mid))
            hi = mid;
        else
            lo = mid + 1;
    }
    return lo;
}

// ib-wood cutting made easy
int isPossibleTo_Cut(vector<int> &A, int heightBlade, int B)
{
    int sum = 0;
    for (int i = 0; i < A.size(); i++)
    {
        if (A[i] > heightBlade)
            sum += A[i] - heightBlade;

        if (sum >= B)
            return true;
    }
    return false;
}

int woodCutting(vector<int> &A, int B) 
{
    int lo = 0, hi = 1e9, ans = 0;
    while (lo <= hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (isPossibleTo_Cut(A, mid, B))
        {
            ans = mid;
            lo = mid + 1;
        }
        else
            hi = mid - 1;
    }
    return ans;
}

// https://leetcode.com/discuss/interview-question/348510/Google-or-Online-Assessment-or-Maximum-Area-Serving-Cake
bool isPossibletoEat(vector<double> &area, double piecearea, int k)
{
    int count = 0;
    for (double ele : area)
    {
        count += ele / piecearea;  //not taken ceil because cant give bacha hua tukda to someone
        if (count >= k)
            return true;
    }
    return false;
}

double servingAreaCake(vector<int> radii, int k)
{
    vector<double> area;
    double si = 0.0, ei = 0.0, mid = 0.0;
    for (int i = 0; i < radii.size(); i++)
    {
        area.push_back(radii[i] * radii[i] * 2 * acos(0.0));
        ei = max(ei, area[i]);
    }
    while (ei - si > 1e-5) // 4 decimal point tk answer maanga h jaise hi differnce 4 decimal se chhota hua loop stop 
    {
        mid = (ei + si) / 2;
        if (isPossibletoEat(area, mid, k))
            si = mid;
        // else ei=mid-1e-5;  //no farak even if we dont subtract (1e-5 is safe can also use 1e-4)
        else 
            ei = mid; // cant do -1.0 because answer is accepted till 4 decimal place.
    }
    return mid; // si ya ei ya mid(bcz kyunki differnce bahut kam h)
}

bool isValid(vector<int> &stations, int k, double mid) // binary search on difference
{
    int count = 0;
    for (int i = 0; i < stations.size() - 1; i++)
    {
        count += (int)((stations[i + 1] - stations[i]) / mid);
        if (count > k)
            return true;
    }
    return false; // agar 3 pump lgane the aur 2 lga paaye then km kro apna distance also if 3 lga paye then also kam kro your distance
}

// https://www.lintcode.com/problem/minimize-max-distance-to-gas-station/description
double minmaxGasDist(vector<int> &stations, int k)
{
    double si = 0.0, ei = 1e9, mid = 0.0; // agr ei calculate by o(n) ka loop chalakr then it takes more time than log(1e9)
    while ((ei - si) > 1e-7)
    {
        mid = si + (ei - si) / 2.0;
        if (isValid(stations, k, mid))
            si = mid;
        else
            ei = mid - 10e-6; // ya ei = mid and if answer is accepted till 10e-6 then should sub 10e-7 but can also 10e-6
    }
    return mid; // si//ei
}

int main()
{
    // vector<int> nums = {-2, 0, 2, 3, 5, 15, 17, 19, 29, 48, 68, 78, 98};
    // int target = 67;
    // cout << binarySearchNearestIndex(nums, target) << endl;
    vector<int> radii = {1, 1, 1, 2, 2, 3};
    // vector<int> radii = {4,3,3};
    int k = 6;
    cout << servingAreaCake(radii, k);
    // vector<int> stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // int k = 10;
    // cout << minmaxGasDist(stations, k) << endl;
    return 0;
}

//=====
// 4
double findMedianSortedArrays(vector<int> &a, vector<int> &b)
{                                                                                           
    int n = a.size(), m = b.size(), l = 0, h = n, te = n + m;
    if (n > m)
        return findMedianSortedArrays(b, a);
    // te:total elements
    while (l <= h)
    {
        int aleft = (l + h) / 2;
        int bleft = (te + 1) / 2 - aleft;

        int alm1 = (aleft == 0) ? INT_MIN : a[aleft - 1]; // aleft minus1
        int al = (aleft == n) ? INT_MAX : a[aleft];

        int blm1 = (bleft == 0) ? INT_MIN : b[bleft - 1];
        int bl = (bleft == m) ? INT_MAX : b[bleft];

        if (alm1 <= bl && blm1 <= al)
        {
            if (te % 2 == 0)
            {
                int leftValue = max(alm1, blm1);
                int rightValue = min(al, bl);

                return (leftValue + rightValue) / 2.0;
            }
            else
            {
                return max(alm1, blm1);
            }
        }
        else if (!(alm1 <= bl))
            h = aleft - 1;
        else if (!(blm1 <= al))
            l = aleft + 1;
    }

    return 0.0;
}

// https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
//For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. 
// Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
long long inversionCount2(long long arr[], int si, int ei, vector<long long> &sortedArray)
{
    long long count = 0;
    long long i = si, mid = (si + ei) / 2, j = mid + 1, k = si;
    while (i <= mid && j <= ei)
    {
        if (arr[i] <= arr[j])
        {
            sortedArray[k++] = arr[i];
            i++;
            // arr[i] < arr[j] and i > j; count += ei - j + 1;
        }
        else
        {
            sortedArray[k++] = arr[j];
            count += mid - i + 1;
            j++;
        }
    }
    while (i <= mid)
        sortedArray[k++] = arr[i++];
    while (j <= ei)
        sortedArray[k++] = arr[j++];

    while (si <= ei)
        arr[si] = sortedArray[si++];
    return count;
}

long long inversionCount1(long long arr[], int si, int ei, vector<long long> &sortedArray)
{
    if (si == ei)
        return 0;
    long long mid = (si + ei) / 2, count = 0;
    count += inversionCount1(arr, si, mid, sortedArray);
    count += inversionCount1(arr, mid + 1, ei, sortedArray);

    count += inversionCount2(arr, si, ei, sortedArray);
    return count;
}

long long int inversionCount(long long arr[], long long N) // long long int=long long=long int=long
{
    if (N == 0)
        return 0;
    vector<long long> sortedArray(N, 0);
    return inversionCount1(arr, 0, N - 1, sortedArray);
}

// 475
public int findRadius(int[] houses, int[] heaters)  
{
    TreeSet<Integer> ts = new TreeSet<>();
    for (int heater : heaters)
        ts.add(heater); // mlogm

    int ans = 0;
    for (int i = 0; i < houses.length; i++) // n+nlogm
    {
        int RightmeinSabse_Pass = ts.ceiling(houses[i]) != null ? ts.ceiling(houses[i]) - houses[i]
                                                                : Integer.MAX_VALUE;
        int leftmeinSabse_Pass = ts.floor(houses[i]) != null ? houses[i] - ts.floor(houses[i]) : Integer.MAX_VALUE;

        ans = Math.max(ans, Math.min(leftmeinSabse_Pass, RightmeinSabse_Pass));
    }
    return ans;
}

// 300
int lengthOfLIS(vector<int> &nums)
{
    int n = nums.size();
    if (n <= 1)
        return n;
    vector<int> ans;
    for (int ele : nums)
    {
        int idx = searchInsert(ans, ele);
        if (idx == ans.size())
            ans.push_back(ele);
        else
            ans[idx] = ele;
    }
    return ans.size();
}

// 134
int canCompleteCircuit(vector<int> &gas, vector<int> &cost)
{
    int deficit = 0, extraGas = 0, sp = 0;
    for (int i = 0; i < gas.size(); i++)
    {
        extraGas += gas[i] - cost[i];
        if (extraGas < 0)
        {
            deficit += extraGas;
            extraGas = 0;
            sp = i + 1;
        }
    }

    return (deficit + extraGas < 0) ? -1 : sp;
}
