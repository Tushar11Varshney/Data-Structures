import java.util.*;

public class greedy {

    // https://practice.geeksforgeeks.org/problems/choose-and-swap5932/1
    string chooseandswap(string a){    //at most once
        set<char>s;
        for(int i=0;i<a.length();i++)
        s.insert(a[i]);
        
        for(int i=0;i<a.length();i++)
        {
            char ch1=a[i];
            s.erase(a[i]); //remove so that we get smallest 
            if(s.empty())break;
            char ch2=*s.begin();
            if(ch2<ch1)        //string "abac"
            {
                for(int i=0;i<a.length();i++)
                {
                    if(a[i]==ch1)
                    a[i]=ch2;
                    else if(a[i]==ch2)
                    a[i]=ch1;
                }
                break;
            }            
        }
        return a;
    }

    // https://www.spoj.com/submit/CHOCOLA/id=28248929
    public static int mincost(Integer[] hor, Integer[] ver) {
        int minCost = 0;
        Arrays.sort(hor, (a, b) -> {
            return b - a;
        });
        Arrays.sort(ver, (a, b) -> {
            return b - a;
        });

        int vpiece = 1, hpiece = 1, i = 0, j = 0;
        while (i < hor.length || j < ver.length) {
            int v1 = i < hor.length ? hor[i] : -(int) 1e9;
            int v2 = j < ver.length ? ver[j] : -(int) 1e9;

            if (v2 > v1) {
                minCost += v2 * vpiece;
                hpiece++;
                j++;
            } else {
                minCost += v1 * hpiece;
                vpiece++;
                i++;
            }
        }
        return minCost;
    }

    // https://practice.geeksforgeeks.org/problems/maximize-sum-after-k-negations1149/1#
    public static long maximizeSum(long arr[], int n, int k) {
        Arrays.sort(arr);

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 && k > 0) {
                arr[i] = -arr[i];
                k--;
            }
            sum += arr[i];
        }

        if (k > 0) {
            Arrays.sort(arr);
            k = k % 2;
            if (k == 1)
                sum -= 2 * arr[0];
        }
        return sum;
    }

    // https://www.geeksforgeeks.org/smallest-subset-sum-greater-elements/
    public int smallest_SubsetWithSumGreater_ThanAllOtherElement(int[] nums) {
        Arrays.sort(nums);
        int sum = 0, n = nums.length;
        for (int ele : nums)
            sum += ele;

        int currentSum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            currentSum += nums[i];
            sum -= nums[i];
            if (currentSum > sum)
                return n - i;
        }

        return 0;
    }

    // https://www.spoj.com/problems/DEFKIN/
    public static int maxArea(int[] xcoordinates, int[] ycoordinates, int houses, int width, int height) {
        xcoordinates[houses] = 0;
        xcoordinates[houses + 1] = width + 1;
        ycoordinates[houses] = 0;
        ycoordinates[houses + 1] = height + 1;
        Arrays.sort(xcoordinates);
        Arrays.sort(ycoordinates);

        int w = -(int) 1e9;
        for (int i = 1; i < xcoordinates.length; i++) {
            w = Math.max(w, xcoordinates[i] - xcoordinates[i - 1] - 1);
        }

        int h = -(int) 1e9;
        for (int i = 1; i < ycoordinates.length; i++) {
            h = Math.max(h, ycoordinates[i] - ycoordinates[i - 1] - 1);
        }

        return w * h;
    }

    // https://practice.geeksforgeeks.org/problems/smallest-number5829/1#
    static String smallestNumber(int S, int D) {
        if ((9 * D) < S)
            return "-1";
        char[] arr = new char[D];
        while (S > 9) {
            arr[D - 1] = '9';
            D--;
            S -= 9;
        }

        if (D > 1) {
            arr[0] = '1';
            S -= 1;
            for (int i = 1; i < D - 1; i++)
                arr[i] = '0';
            arr[D - 1] = (char) (S + '0');
        } else
            arr[0] = (char) (S + '0');

        return new String(arr);
    }

    // https://www.spoj.com/problems/DIEHARD/
    public static int maximumTimeSurvival(int health, int armor, int[][] dp, int state) {
        // dp.resize(health+4);
        // for (int i = 0; i < health+4; ++i)
        // dp[i].resize(armor+6);
        // dp.resize(health+4, vector<int>(armor+6));
        if (health <= 0 || armor <= 0)
            return 0;

        if (dp[health][armor] != 0)
            return dp[health][armor];

        if (state == 1) {
            dp[health][armor] = Math.max(maximumTimeSurvival(health - 5, armor - 10, dp, 2),
                    maximumTimeSurvival(health - 20, armor + 5, dp, 3)) + 1;
        } else if (state == 2) {
            dp[health][armor] = Math.max(maximumTimeSurvival(health + 3, armor + 2, dp, 1),
                    maximumTimeSurvival(health - 20, armor + 5, dp, 3)) + 1;
        } else {
            dp[health][armor] = Math.max(maximumTimeSurvival(health + 3, armor + 2, dp, 1),
                    maximumTimeSurvival(health - 5, armor - 10, dp, 2)) + 1;
        }

        return dp[health][armor];

        // while(t-->0)
        // {
        // int health=sc.nextInt();
        // int armor=sc.nextInt();
        // int dp[][]=new int[1500][1500];
        // // System.out.println(dp.length+" "+dp[0].length);
        // int ma=maximumTimeSurvival(health+3,armor+2,dp,1);
        // int mw=maximumTimeSurvival(health-5,armor-10,dp,2);
        // int mf=maximumTimeSurvival(health-20,armor+5,dp,3);
        // System.out.println(Math.max(Math.max(ma,mw),mf));
        // }
    }

    // my soln was ek list mein sp of x and number of x daale pair classbnakr.
    // fir hume chhaiye pq jo min bnde nikaalegi max distance pr aur bnde brabar 
    // then zyada dist pr pq mein daale sp,difference of distance,number of people and last waale kelie usse pichle waale ka diff liya fir pq se nikaala jab tak size 1 and then ek naya group banaya ..pr fir pq se jiske saath mila hai vo nikalemge kaise?? 5.calculate jump
    public int seats(String A) {
        int i = 0, j = A.length() - 1, mod = 10000003, l = 0, r = 0, ans = 0;
        while (i <= j) {
            while (i <= j && A.charAt(i) == 'x') {
                i++;
                l++; // l:no of people on left
            }

            while (i <= j && A.charAt(j) == 'x') {
                j--;
                r++; // r:no of people on right
            }
            if (i <= j) {
                if (l <= r) {
                    ans = (ans + l) % mod;
                    i++;
                } else {
                    ans = (ans + r) % mod;
                    j--;
                }
            }
        }
        return ans;
    }

//=======

// https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
int[] JobScheduling(Job arr[], int n) {
    int[] ans = new int[2];
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
        li.add(new int[] { arr[i].id, arr[i].deadline, arr[i].profit });
    }

    Collections.sort(li, (a, b) -> {
        return b[2] - a[2];
    });

    // boolean []time=new boolean[100];
    boolean[] time = new boolean[n];
    for (int i = 0; i < li.size(); i++) {
        int d = Math.min(n, li.get(i)[1]);
        while (d - 1 >= 0 && time[d - 1] != false)
            d--;
        if (d - 1 >= 0) {
            time[d - 1] = true;
            ans[1] += li.get(i)[2];
            ans[0]++;
        }
    }
    return ans;
}

double fractionalKnapsack(int W, Item arr[], int n) {
    List<double[]> li = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
        double ratio = (arr[i].value * 1.0) / arr[i].weight;
        li.add(new double[] { ratio, arr[i].weight * 1.0 });
    }

    li.sort((a, b) -> Double.compare(a[0], b[0]));

    double profit = 0.0;
    int i = li.size() - 1;
    while (W > 0 && i >= 0) {
        if (W - li.get(i)[1] >= 0) {
            profit += li.get(i)[0] * li.get(i)[1];
            W -= li.get(i)[1];
        } else {
            profit += W * li.get(i)[0];
            W = 0;
        }
        i--;
    }
    return profit;
}

//767
public String reorganizeString(String s) {
    HashMap<Character, Integer> count = new HashMap<>();
    for (int i = 0; i < s.length(); i++)
        count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);

    PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> {
        return count.get(b) - count.get(a);
    });   //instead of using pair

    pq.addAll(count.keySet());
    StringBuilder sb = new StringBuilder();
    while (pq.size() > 1) {
        char ch1 = pq.remove();
        char ch2 = pq.remove();

        sb.append(ch1);
        sb.append(ch2);

        count.put(ch1, count.get(ch1) - 1);
        count.put(ch2, count.get(ch2) - 1);

        if (count.get(ch1) == 0)
            count.remove(ch1);
        else
            pq.add(ch1);

        if (count.get(ch2) == 0)
            count.remove(ch2);
        else
            pq.add(ch2);    
    }

    if (pq.size() == 1) {
        char ch = pq.remove();
        if (count.get(ch) > 1)
            return "";
        sb.append(ch);
    }

    return sb.toString();
}

// 621
public int leastInterval(char[] tasks, int coolTime) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return b - a;
    });

    int n = tasks.length;
    int[] charArr = new int[26];
    for (int i = 0; i < n; i++)
        charArr[tasks[i] - 'A']++;

    for (int i = 0; i < 26; i++)
        if (charArr[i] != 0)
            pq.add(charArr[i]);

    int result = 0;
    while (pq.size() != 0) {
        List<Integer> li = new ArrayList<>();
        int time = 0;
        for (int i = 0; i < coolTime + 1; i++) {
            if (pq.size() != 0) {
                int val = pq.remove();
                if (val - 1 != 0)
                    li.add(val - 1);
                time++;
            }
        }

        while (li.size() != 0)
            pq.add(li.remove(li.size() - 1));

        result += pq.size() != 0 ? coolTime + 1 : time;
    }
    return result;
}

// 1834
class pair {
    int at;
    int pt;
    int id;

    pair(int at, int pt, int id) {
        this.at = at;
        this.pt = pt;
        this.id = id;
    }
}

public int[] getOrder(int[][] tasks) {
    int n = tasks.length;
    int[][] Tasks = new int[n][3];
    for (int i = 0; i < n; i++) {
        Tasks[i][0] = tasks[i][0];
        Tasks[i][1] = tasks[i][1];
        Tasks[i][2] = i;
    }

    Arrays.sort(Tasks, (a, b) -> {
        if (a[0] != b[0])  
            return a[0] - b[0];
        return a[1] - b[1];  //cpu will choose shortest tym wala
    });

    PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
        if (a.pt != b.pt)
            return a.pt - b.pt;
        return a.id - b.id;
    });

    int processTime = 0, i = 0, k = 0;
    int[] ans = new int[n];
    while (i < n || pq.size() != 0) {
        if (pq.size() == 0) {
            pq.add(new pair(Tasks[i][0], Tasks[i][1], Tasks[i][2]));
            processTime+ = Tasks[i][0];
            i++;
        }
        pair p = pq.remove();
        processTime += p.pt;
        ans[k++] = p.id;

        while (i < n) {
            if (Tasks[i][0] <= processTime) {
                pq.add(new pair(Tasks[i][0], Tasks[i][1], Tasks[i][2]));
                i++;
            } else
                break;
        }
    }

    return ans;
}


// https://www.codingninjas.com/codestudio/problems/maximum-equal-stack-sum_1062571?leftPanelTab=0
public static void inputFn(Stack<Integer> stk) {
    Scanner sc = new Scanner(System.in);
    while (true) {
        int val = sc.nextInt();
        if (val == -1)
            break;
        stk.push(val);
    }
}

public static int sum(Stack<Integer> stk) {
    int sz = stk.size();
    Stack<Integer> st = new Stack();
    int sum = 0;
    while (sz-- > 0) {
        int val = stk.pop();
        sum += val;
        st.push(val);
    }
    sz = st.size();
    while (sz-- > 0) {
        stk.push(st.pop());
    }
    return sum;
}

public static int maxSum(Stack<Integer> stk1, Stack<Integer> stk2, Stack<Integer> stk3) {
    int sum1 = sum(stk1), sum2 = sum(stk2), sum3 = sum(stk3);
    int ans = 0;
    while (true) {
        if (stk1.size() == 0 || stk2.size() == 0 || stk3.size() == 0) {
            ans = 0;
            break;
        } else if (sum1 == sum2 && sum2 == sum3) {
            ans = sum1;
            break;
        } else if (sum1 >= sum2 && sum1 >= sum3)
            sum1 -= stk1.pop();
        else if (sum2 >= sum3 && sum2 >= sum1)
            sum2 -= stk2.pop();
        else if (sum3 >= sum1 && sum3 >= sum2)
            sum3 -= stk3.pop();
    }
    return ans;
}

// https://practice.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1#
long minCost(long arr[], int n) {
    //multiset giving tle
    PriorityQueue<Long> pq = new PriorityQueue<>();

    for (int i = 0; i < n; i++)
        pq.add(arr[i]);
    long sum = 0;
    while (pq.size() != 1) {
        Long v1 = pq.remove();
        Long v2 = pq.remove();
        sum += v1 + v2;
        pq.add(v1 + v2);
    }

    return sum;
}

// 1710
public int maximumUnits(int[][] boxTypes, int truckSize) {

    Arrays.sort(boxTypes, (a, b) -> {
        return b[1] - a[1];
    });

    int unit = 0;
    for (int i = 0; i < boxTypes.length; i++) {
        if (truckSize >= boxTypes[i][0]) {
            unit += boxTypes[i][0] * boxTypes[i][1];
            truckSize -= boxTypes[i][0];
        } else {
            unit += truckSize * boxTypes[i][1];
            break;
        }
    }
    return unit;
}

// https://www.codingninjas.com/codestudio/problems/buy-maximum-stocks-if-i-stocks-can-be-bought-on-i-th-day_1169470?leftPanelTab=0
public static int maxStock(int[] prices, int n, int amount) {
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < prices.length; i++)
        li.add(new int[] { prices[i], i + 1 });

    Collections.sort(li, (a, b) -> {
        return a[0] - b[0];
    });

    int totalStock = 0;
    for (int i = 0; i < n; i++) {
        int weCanBuy = amount / li.get(i)[0];
        if (weCanBuy > li.get(i)[1]) {
            amount -= li.get(i)[0] * li.get(i)[1];
            totalStock += li.get(i)[1];
        } else {
            totalStock += weCanBuy;
            break;
        }
    }
    return totalStock;
}

// https://practice.geeksforgeeks.org/problems/check-if-it-is-possible-to-survive-on-island4922/1#
static int minimumDays(int S, int N, int M) {
    int sunday = S / 7;
    int daySheCanBuy = S - sunday;

    int totalReqmt = S * M;
    int reqddays = (int) Math.ceil(totalReqmt * 1.0 / N);

    if (reqddays <= daySheCanBuy)
        return reqddays;

    return -1;
}

// 135
public int candy(int[] rating) {
    int n = rating.length;
    int[] candyArr = new int[n];

    candyArr[0] = 1;
    for (int i = 1; i < n; i++) {
        if (rating[i] > rating[i - 1])
            candyArr[i] = candyArr[i - 1] + 1;
        else
            candyArr[i] = 1;
    }

    for (int i = n - 2; i >= 0; i--) {
        if (rating[i] > rating[i + 1])
            if (candyArr[i] <= candyArr[i + 1])
                candyArr[i] = candyArr[i + 1] + 1;
    }

    int reqd = 0;
    for (int i = 0; i < n; i++)
        reqd += candyArr[i];

    return reqd;
}

// 948
public int bagOfTokensScore(int[] tokens, int P) {
    Arrays.sort(tokens);

    int score = 0, maxScore = 0;
    int i = 0, j = tokens.length - 1;
    while (i <= j) {
        if (P >= tokens[i]) {
            score++;
            P -= tokens[i++];
            maxScore = Math.max(maxScore, score);
        } else if (score > 0) {
            score--;
            P += tokens[j--];
        } else
            break; // cant do anything
    }
    return maxScore;
}

//https://practice.geeksforgeeks.org/problems/assign-mice-holes3053/1
static int assignMiceHoles(int N, int[] M, int[] H) {  //read explanation of qs
    Arrays.sort(M);
    Arrays.sort(H);

    int max = 0;
    for (int i = 0; i < N; i++) {
        max = Math.max(max, Math.abs(H[i] - M[i]));
    }

    return max;
}

// 881
public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);
    int n = people.length, boats = 0, i = 0, j = n - 1;
    while (i <= j) {
        if (people[i] + people[j] <= limit) {
            boats++;
            i++;
            j--;
        } else {
            boats++;
            j--;
        }
    }
    return boats;
}

// 628
public int maximumProduct(int[] nums) {
    if (nums.length == 3)
        return nums[0] * nums[1] * nums[2];
    int n = nums.length;
    Integer[] num = new Integer[n];
    for (int i = 0; i < n; i++)
        num[i] = nums[i];
    Arrays.sort(num, (a, b) -> {
        return b - a;
        //return a-b;
    });

    if (num[0] > 0) {
        // [98,4,3,2,-1,-100]
        return Math.max(num[0] * num[n - 1] * num[n - 2], num[0] * num[1] * num[2]);
        // return num[n-1]*num[n-2]*num[n-3];
    }else if(num[0]<=0)
    //[0,-1,-2,-3]
    return num[0]*num[1]*num[2];
    // return Math.max(num[0]*num[1]*num[n-1],num[n-1]*num[n-2]*num[n-3]);

    return 0;
}

// https://practice.geeksforgeeks.org/problems/shop-in-candy-store1145/1
static ArrayList<Integer> candyStore(int candies[], int N, int K) {
    Arrays.sort(candies);
    int min = 0, max = 0, i = 0, j = N - 1;

    while (i <= j) {
        min += candies[i++];
        j -= K;
    }

    i = 0;
    j = N - 1;
    while (i <= j) {
        max += candies[j--];
        i += K;
    }

    ArrayList<Integer> li = new ArrayList<>();
    li.add(min);
    li.add(max);

    return li;
}

// 1383
public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    int[][] engi = new int[n][2];
    for (int i = 0; i < n; i++) {
        engi[i][0] = speed[i];
        engi[i][1] = efficiency[i];
    }

    Arrays.sort(engi, (a, b) -> {
        return b[1] - a[1];  //efficiency
    });

    long ans = 0, s = 0, mod = (int) 1e9 + 7;
    PriorityQueue<Integer> pq = new PriorityQueue<>();  //speed dalenge
    for (int i = 0; i < n; i++) {
        s += engi[i][0];
        pq.add(engi[i][0]);
        if (pq.size() > k)
            s -= pq.remove();
        ans = Math.max(ans, (s * engi[i][1]));
    }
    return (int) (ans % mod);
}  


// https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1#
public static int maxMeetings(int start[], int end[], int n) {
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < n; i++)
        li.add(new int[] { start[i], end[i] });

    Collections.sort(li, (a, b) -> {
        if (a[1] == b[1])
            return a[0] - b[0];
        else
            return a[1] - b[1];
    });

    int count = 1, i = 0, j = 1;
    while (j < li.size()) {
        if (li.get(i)[1] < li.get(j)[0]) {
            count++;
            i = j;
            j++;
        } else
            j++;
    }
    return count;
}

//https://www.interviewbit.com/old/problems/disjoint-intervals/
public int disjointInterval(int[][] A) {
    Arrays.sort(A,(a,b)->{
        if(a[0]!=b[0])return a[0]-b[0];
        return a[1]-b[1];
    });
    
    int i=0,j=1,n=A.length,size=1;
    while(j<n)
    {
        if(A[i][1]<A[j][0])
        {
            size++;
            i=j;
            j++;
        }
        else j++;
    }
    return size;
}

// o(n) space
// https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
static int findPlatform(int arr[], int dep[], int n) { // treeset mein lower bhot saare chotton mein se dikhayga value sabse closest to element tou jab treeSet use kra tou zyada count aaya aana chahiye tha kam testcase pr
    List<int[]> li = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        li.add(new int[] { arr[i], dep[i] });
    }

    Collections.sort(li, (a, b) -> {
        return a[0] - b[0];
    });

    int platforms = 1;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    pq.add(li.get(0)[1]);
    for (int i = 1; i < n; i++) {
        Integer val = pq.peek();
        if (val < li.get(i)[0]) {
            pq.remove();
            pq.add(li.get(i)[1]);
        } else {
            platforms++;
            pq.add(li.get(i)[1]);
        }
    }
    return platforms;
}

// o(1)space
static int findPlatform(int arr[], int dep[], int n) { 
    Arrays.sort(arr);
    Arrays.sort(dep);

    int platforms = 1, i = 1, j = 0; // i moves always i is for next train and j is for train currently running
    while (i < n) {
        if (dep[j] < arr[i]) // same platform can not be used for both departure of a train and arrival of another train.
        {
            i++;
            j++;
        } else {
            platforms++;
            i++;
        }
    }
    return platforms;
}

// 452 
int findMinArrowShots(vector<vector<int>>& points) {
    int n = points.size();
    sort(points.begin(),points.end());
    int ans = 1, end = points[0][1], i = 1;
    while (i < n) {
        // non overlapping region
        if (end < points[i][0]) {
            ans++;
            end = points[i][1];
        }
        else 
        // overlapping region
        end = min(end, points[i][1]);

        i++;
    }
    return ans;
}

// 1094
public boolean carPooling(int[][] trips, int capacity) {
    int n = trips.length;
    int[][] tripStart = new int[n][2];

    for (int i = 0; i < n; i++) {
        tripStart[i][0] = trips[i][1]; //start
        tripStart[i][1] = trips[i][0]; //psngr
    }

    Arrays.sort(tripStart, (a, b) -> {
        if (a[0] != b[0])
            return a[0] - b[0];
        return a[1] - b[1];
    });

    int[][] tripEnd = new int[n][2];

    for (int i = 0; i < n; i++) {
        tripEnd[i][0] = trips[i][2];   //end
        tripEnd[i][1] = trips[i][0];   //psng
    }

    Arrays.sort(tripEnd, (a, b) -> {
        if (a[0] != b[0])
            return a[0] - b[0];
        return b[1] - a[1];
    });

    int i = 0, j = 1, currentSitting = tripStart[0][1];
    while (j < n) {
        while (i < j && tripEnd[i][0] <= tripStart[j][0]) {
            currentSitting -= tripEnd[i][1];
            i++;
        }
        currentSitting += tripStart[j][1];
        j++;
        if (currentSitting > capacity)
            return false;
    }
    return currentSitting > capacity ? false : true;
}

public boolean carPooling2(int[][] trips, int capacity) {
    int time[] = new int[1001];
    for (int[] t : trips) {
        time[t[1]] += t[0];
        time[t[2]] -= t[0];
    }

    for (int num : time) {
        capacity -= num;
        if (capacity < 0)
            return false;
    }
    return true;
}

// https://www.geeksforgeeks.org/maximum-trains-stoppage-can-provided/
static int maximumTrainsStoppage(int[][] matrix, int platform, int length) {
    HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
    for (int i = 0; i < length; i++) {
        int ptnum = matrix[i][2];
        map.putIfAbsent(ptnum, new ArrayList<>());
        map.get(ptnum).add(new int[] { matrix[i][0], matrix[i][1] });
    }

    int stoppage = 0;
    for (int key : map.keySet()) {
        ArrayList<int[]> temp = map.get(key);
        Collections.sort(temp, (a, b) -> {
            return a[0] - b[0];
        });
        Collections.sort(temp, (a, b) -> {
            return a[1] - b[1];
        });

        int j = 0, i = 1, stop = 1;
        while (i < temp.size()) {
            int endtime = temp.get(j)[1];
            int starttime = temp.get(i)[0];

            if (endtime < starttime) {
                i++;
                j++;
                stop++;
            } else
                i++;
        }
        stoppage += stop;
    }
    return stoppage;
}

// 502
public int findMaximizedCapital(int k, int ic, int[] profits, int[] capital) {
    PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> {
        return profits[b] - profits[a];  //no need to makr array for profit & capital
    });

    PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> {
        return capital[a] - capital[b];
    });

    int n = profits.length;
    for (int i = 0; i < n; i++)
        pq2.add(i);

    while (k-- > 0) {
        while (pq2.size() != 0 && capital[pq2.peek()] <= ic) {
            pq1.add(pq2.remove());
        }

        if (pq1.size() != 0)
            ic += profits[pq1.remove()];
    }

    return ic;
}


// 857
static class pair {
    double ratio;
    int quality;

    pair(double ratio, int quality) {
        this.ratio = ratio;
        this.quality = quality;
    }
}

public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    int n=quality.length;
    double ans=Double.MAX_VALUE;
    
    for(int i=0;i<n;i++)
    {
        double ratio=(wage[i]*1.0)/quality[i];
        double leastMoney=0;
        PriorityQueue<Double>pq = new PriorityQueue<>((a, b) -> {
            if(a>b)return -1;
            else if(b>a)return 1;
            return 0;
        });
        for(int j=0;j<n;j++)
        {
            double salary=ratio*quality[j];
            if(salary>=wage[j])
              pq.add(salary);
            if(pq.size()>k)
                pq.remove();
        }
        
        if(pq.size()==k)
        {
            while(pq.size()!=0)
            leastMoney+=pq.remove();

            ans=Math.min(ans,leastMoney);
        }
    }
    
    return ans;
}

//2nd approach put all ratio in array sort the array and check for captain from k-1
public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    int n=quality.length;
    List<pair>li=new ArrayList<>();
    for(int i=0;i<n;i++)
    {
        double ratio=(wage[i]*1.0)/quality[i];
        li.add(new pair(ratio,quality[i]));    
    }
    
    Collections.sort(li,(a,b)->{
        if(a.ratio>b.ratio)return 1;
        else if(b.ratio>a.ratio)return -1;
        return 0;
    });
    
    PriorityQueue<Integer>pq = new PriorityQueue<>((a, b) -> {
            return b-a;
    });
    
    int sum=0;
    for(int i=0;i<k;i++)
    {
        pq.add(li.get(i).quality);
        sum+=li.get(i).quality;
    }
    
    double ratio=li.get(k-1).ratio;
    double minCost=ratio*sum,ans=minCost;
    for(int i=k;i<n;i++)
    {        
        if(li.get(i).quality<pq.peek())
        {
            int q=pq.remove();
            sum-=q;
            pq.add(li.get(i).quality);
            sum+=li.get(i).quality;
        }
        
        ratio=li.get(i).ratio;
        minCost=ratio*sum;
        ans=Math.min(ans,minCost);
    }
    
    return ans;
}

