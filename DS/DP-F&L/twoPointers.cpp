#include <iostream>
#include <vector>
#include <math.h>
#include <list>
using namespace std;

//copy recursive code as it is in iterative when same result for a state multiple times
void print(vector<int> &arr)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void printlong(vector<long long> &arr)  //long==long int==long long==long long int-16bytes,long double(16 bytes)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void print2d(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
    {
        print(ar);
    }
}

public static void display(int arr[]) {
    for (int ele : arr)
        System.out.print(ele + " ");

    System.out.println();
}

//509
int fib(int N, vector<int> &dp)
{
    if (dp[N] != -1)
        return dp[N];
    if (N <= 1)
        return dp[N] = N;

    int a = fib(N - 1, dp);
    int b = fib(N - 2, dp);
    return dp[N] = a + b;
}

int fibDp(int m, vector<int> &dp)
{
    for (int N = 0; N <= m; N++)
    {
        if (N <= 1)
        {
            dp[N] = N;
            continue;
        }
        int a = dp[N - 1];
        int b = dp[N - 2];
        dp[N] = a + b;
    }
    return dp[m];
}

int fibOPTI(int n)    
{
    if (n <= 1)
        return n;
    int a = 0;
    int b = 1;
    int sum = 0;
    for (int i = 2; i <= n; i++)
    {
        sum = a + b;
        a = b;
        b = sum;
    }
    return sum;
}

void fibo()
{
    int n = 10;
    vector<int> dp(n + 1, -1); //0 se bhi chljaayga but avoid
    cout << fib(n, dp) << endl;
    print(dp);
    cout << fibDp(n, dp) << endl;
    print(dp);
}

//70
int climbingStairs(int n, vector<int> &dp)
{
    if (n <= 1)
        return dp[n] = 1;

    if (dp[n] != -1)
        return dp[n];

    int a = climbingStairs(n - 1, dp);
    int b = climbingStairs(n - 2, dp);

    return dp[n] = a + b;
}

int climbingStairsDP(int N, vector<int> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        int a = dp[n - 1];
        int b = dp[n - 2];

        dp[n] = a + b;
    }
    return dp[N];
}

//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/climb-stairs-official/ojquestion
int climbingStairsPep(int n, vector<int> &dp)
{
    if(n<0)return 0;
    if (n <= 1)
        return dp[n] = 1;

    if (dp[n] != -1)
        return dp[n];

    int a = climbingStairsPep(n - 1, dp);
    int b = climbingStairsPep(n - 2, dp);
    int c = climbingStairsPep(n - 3, dp);

    return dp[n] = a + b + c;
}

void climbingStairsproblem()
{
    // int n = 3;
    // vector<int> dp(n + 1, -1);
    // cout << climbingStairs(n, dp) << endl;
    // print(dp);
    // cout << climbingStairsDP(n, dp) << endl;

    int n;
    cin >> n;
    vector<int> dp(n + 1, -1);
    cout << climbingStairsPep(n, dp) << endl;
    print(dp);
}

public static int climbStairWithVariableJump(int[] jump, int n, int stair, int[] dp) {
    if (stair == n)
        return dp[stair] = 1;

    if (dp[stair] != -1)
        return dp[stair];

    int count = 0;
    for (int j = 1; j <= jump[stair]; j++) {
        if (stair + j <= n)
            count += climbStairWithVariableJump(jump, n, stair + j, dp);
    }
    return dp[stair] = count;
}

public static int climbStairWithVariableJump_DP(int[] jump, int n, int[] dp) {
    dp[n] = 1;
    for (int i = n - 1; i >= 0; i--) {
        int count = 0;
        for (int j = 1; j <= jump[i]; j++) {
            if (i + j <= n)
                count += dp[i + j];
        }
        dp[i] = count;
    }
    return dp[0];
}

public static int climbStairWithVariableJumpMinMoves(int[] jump, int n, int stair, Integer[] dp) {
    if (stair == n)
        return dp[stair] = 0; // chalo hi mat is 1 path with 0 moves

    if (dp[stair] != null)
        return dp[stair];

    int res = (int) 1e9;
    for (int j = 1; j <= jump[stair]; j++) {
        if (stair + j <= n)
        res = Math.min(res, climbStairWithVariableJumpMinMoves(jump, n, stair + j, dp) + 1);
    }
    return dp[stair] = res;
}

public static int climbStairWithVariableJumpMinMoves_DP(int[] jump, int n, int[] dp) {
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int res = (int) 1e9;
            for (int j = 1; j <= jump[i]; j++) {
                if (i + j <= n)
                    // res=Math.min(res,dp[i+j]+j); //moves pucha h isliye + 1 ..j for jumps
                    res = Math.min(res, dp[i + j] + 1);
            }
            dp[i] = res;
        }
        return dp[0];
}

//https://www.geeksforgeeks.org/friends-pairing-problem/
int friendPairing(int n, vector<int> &dp)
{
    if (n <= 1)
        return dp[n] = 1;  //when no person is there to remain single or pair up then 1 tarikka

    if (dp[n] != -1)
        return dp[n];

    int a = friendPairing(n - 1, dp);
    int b = friendPairing(n - 2, dp) * (n - 1);

    return dp[n] = a + b;
}

int friendPairingDP(int N, vector<int> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        int a = dp[n - 1];
        int b = dp[n - 2] * (n - 1);

        dp[n] = a + b;
    }
    return dp[N];
}

long long int friendPairingOPTI(int N)
{
    int mod = 1e9 + 7;       //1e2-100 10e2-1000
    long long a=1,b=1,sum=a;
    for(int n=2;n<=N;n++)  //1 <= N <= 10^6..use in array long long agr overflow krjayga tou test case fail
    {    
        sum=((a*(n-1))%mod+b%mod)%mod;  //(a+b)%mod=(a%mod+b%mod)%mod
        a=b;
        b=sum;
    }
    return sum;
}

void friendsproblem()
{
    // int n = 100000;
    int n = 3;
    vector<int> dp(n + 1, -1);
    // cout << friendPairing(n, dp) << endl;
    // cout << friendPairingDP(n, dp) << endl;
    // cout << friendPairingOPTI(n) << endl;
    print(dp);
}

//746 
int minCostClimbingStairs(int n, vector<int> &cost, vector<int> &dp)
{
    if (n <= 1)  //bcz we can start from 0/1
    {
        return dp[n] = cost[n];
    }

    if (dp[n] != 1e8)
        return dp[n];

    // dp[n - 1] = minCostClimbingStairs(n - 1, cost, dp);
    // dp[n - 2] = minCostClimbingStairs(n - 2, cost, dp); //dp mein hum hr seedi tk pahuchne ki min value hi store krrhe hain tou no need to check if from stair1 or stair0 as starting point cost is minimum

    // if (n < cost.size()) return dp[n] = min(dp[n - 1], dp[n - 2]) + cost[n];
    // else  return dp[n] = min(dp[n - 1], dp[n - 2]);   

    int val = min(minCostClimbingStairs(n - 1, cost, dp), minCostClimbingStairs(n - 2, cost, dp));

    return dp[n] = val + ((n < cost.size()) ? cost[n] : 0);
}

int minCostClimbingStairsDP(int N, vector<int> &cost, vector<int> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = cost[n];
            continue;
        }

        int val = min(dp[n - 1], dp[n - 2]);

        dp[n] = val + ((n < cost.size()) ? cost[n] : 0);
    }
    return dp[N];
}

void minCostClimbingStairsProb()
{
    // vector<int> cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    vector<int> cost = {10, 15, 20};
    int n = cost.size();
    vector<int> dp(n + 1, 1e8);
    // vector<int> dp(n + 1, 0);
    // cout << minCostClimbingStairsDP(n, cost, dp) << endl;
    cout << minCostClimbingStairs(n, cost, dp) << endl;
    print(dp);
}

int power(int n,vector<int>&dp)
{
    if(n==0) return dp[n]= 1;
    if(dp[n]!=0)return dp[n];
    int smallAns=power(n/2,dp);
    return dp[n]=n%2==0?smallAns*smallAns:smallAns*smallAns*2;
}

void power()
{
    int n=10;
    vector<int>dp(n+1);
    cout<<power(n,dp);
}

//62
//yhaan dirn saari positive hain isliye no need to check >=0 agr ek bhi negative then check it.
int mazepath(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    if (sr == er && sc == ec)
    {
        return dp[sr][sc] = 1; //jb bhi last waale cell ko reach krenge tab path count hoga...jb jb recurive call yahn pohchengi return 1
    }

    int count = 0;
    if (sc + 1 <= ec)
        count += mazepath(sr, sc + 1, er, ec, dp); //+ lgao ya na lgao mrzi
    if (sr + 1 <= er)
        count += mazepath(sr + 1, sc, er, ec, dp);
    if (sr + 1 <= er && sc + 1 <= ec)
        count += mazepath(sr + 1, sc + 1, er, ec, dp);

    return dp[sr][sc] = count;
}

int mazepathDIR(int sr, int sc, int er, int ec, vector<vector<int>> &dp, vector<vector<int>> &dir)
{
    if (sr == er && sc == ec)
        return dp[sr][sc] = 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    for (int i = 0; i < dir.size(); i++)
    {
        if (sr + dir[i][0] <= er && sc + dir[i][1] <= ec)
            count += mazepathDIR(sr + dir[i][0], sc + dir[i][1], er, ec, dp, dir);
    }

    return dp[sr][sc] = count;
}

int mazepath_dp(int SR, int SC, int er, int ec, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--) 
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count = 0;
            if (sr + 1 <= er)
                count += dp[sr + 1][sc];
            if (sc + 1 <= ec)
                count += dp[sr][sc + 1];
            if (sr + 1 <= er && sc + 1 <= ec)
                count += dp[sr + 1][sc + 1];
            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}

int mazepath_multiple(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
        return dp[sr][sc] = 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazepath_multiple(sr, sc + jump, er, ec, dp);
    for (int jump = 1; sr + jump <= er; jump++)
        count += mazepath_multiple(sr + jump, sc, er, ec, dp);
    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
        count += mazepath_multiple(sr + jump, sc + jump, er, ec, dp);

    return dp[sr][sc] = count;
}

int mazepath_multipleDIR(int sr, int sc, int er, int ec, vector<vector<int>> &dp, vector<vector<int>> &dir, int totalJump)
{
    if (sr == er && sc == ec)
        return dp[sr][sc] = 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    for (int i = 0; i < dir.size(); i++)
    {
        for (int jump = 1; jump <= totalJump; jump++)
        {
            int xjump = jump * dir[i][1];
            int yjump = jump * dir[i][0];
            if (sr + yjump <= er && sc + xjump <= ec)
                count += mazepath_multipleDIR(sr + yjump, sc + xjump, er, ec, dp, dir, totalJump);
            else break;
        }
    }
    return dp[sr][sc] = count;
}

int mazepath_multipleDP(int SR, int SC, int er, int ec, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }
            int count = 0;
            for (int jump = 1; sc + jump <= ec; jump++)
                count += dp[sr][sc + jump];
            for (int jump = 1; sr + jump <= er; jump++)
                count += dp[sr + jump][sc];
            for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                count += dp[sr + jump][sc + jump];
                
            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}

//63-1 obstacle
int mazepath_obstacle(int sr, int sc, int er, int ec, vector<vector<int>> &dp,vector<vector<int>> &obstacleGrid)
{
    if (sr == er && sc == ec)
        return 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    if (sc + 1 <= ec && obstacleGrid[sr][sc+1]!=1)
        count += mazepath_obstacle(sr, sc + 1, er, ec, dp,obstacleGrid);
    if (sr + 1 <= er && obstacleGrid[sr+1][sc]!=1)
        count += mazepath_obstacle(sr + 1, sc, er, ec, dp,obstacleGrid);

    return dp[sr][sc] = count;
}

int mazepath_obstacleDP(int SR, int SC, int er, int ec, vector<vector<long>> &dp, vector<vector<int>> &obstacleGrid)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                    dp[sr][sc] = 1;
                    continue;
            }

            long count = 0;
            if (sc + 1 <= ec && obstacleGrid[sr][sc + 1] != 1)
                count += dp[sr][sc + 1];
            if (sr + 1 <= er && obstacleGrid[sr + 1][sc] != 1)
                count += dp[sr + 1][sc];

            dp[sr][sc] = count;
        }
    }
    return (int)dp[SR][SC];
}

int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
    
    int sr = 0,sc = 0, er = obstacleGrid.size(),ec = obstacleGrid[0].size();
    if(obstacleGrid[er-1][ec-1]==1 || obstacleGrid[0][0]==1 )return 0;
    vector<vector<int>> dp(er, vector<int>(ec, -1));
    return mazepath_obstacle(sr, sc, er - 1, ec - 1, dp, obstacleGrid);
}

//64
int minpathsum(int sr, int sc, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
        return dp[sr][sc] = grid[sr][sc];

    if (dp[sr][sc] != 1e8)
        return dp[sr][sc];

    if (sc + 1 <= ec)
        dp[sr][sc] = min(dp[sr][sc], minpathsum(sr, sc + 1, er, ec, grid, dp));
    if (sr + 1 <= er)
        dp[sr][sc] = min(dp[sr][sc], minpathsum(sr + 1, sc, er, ec, grid, dp));

    return dp[sr][sc] += grid[sr][sc];
}

int minpathsumDP(int SR, int SC, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = grid[sr][sc];
                continue;
            }

            if (sc + 1 <= ec)
                dp[sr][sc] = min(dp[sr][sc], dp[sr][sc + 1]);
            if (sr + 1 <= er)
                dp[sr][sc] = min(dp[sr][sc], dp[sr + 1][sc]);

            dp[sr][sc] += grid[sr][sc];
        }
    }
    return dp[SR][SC];
}

void mp()
{
    // int sr = 0, sc = 0, er = 3, ec = 7;
    // vector<vector<int>> dp(er, vector<int>(ec, -1));
    // vector<vector<int>> dir{{0, 1}, {1, 1}, {1, 0}};
    // cout << mazepath(sr, sc, er - 1, ec - 1, dp) << endl;
    // cout << mazepathDIR(sr, sc, er - 1, ec - 1, dp, dir) << endl;
    // cout << mazepath_dp(sr, sc, er - 1, ec - 1, dp) << endl;
    // cout << mazepath_multiple(sr, sc, er - 1, ec - 1, dp) << endl;
    // int totalJump = er > ec ? er - 1 : ec - 1;
    // cout << mazepath_multipleDIR(sr, sc, er - 1, ec - 1, dp, dir, totalJump) << endl;
    // print2d(dp);
    // cout << mazepath_multipleDP(sr, sc, er - 1, ec - 1, dp) << endl;

    // vector<vector<int>> obstacleGrid = {{0, 0, 0},
    //                                     {0, 1, 0},
    //                                     {0, 0, 0}};
    // vector<vector<int>> obstacleGrid = {{1}};
    // vector<vector<int>> obstacleGrid = {{1, 0}};
    // int sr = 0, sc = 0, er = obstacleGrid.size(), ec = obstacleGrid[0].size();
    // vector<vector<int>> dp(er, vector<int>(ec, -1));
    // cout << mazepath_obstacle(sr, sc, er - 1, ec - 1, dp, obstacleGrid) << endl;
    // cout << mazepath_obstacleDP(sr, sc, er - 1, ec - 1, dp, obstacleGrid) << endl;
    // print2d(dp);

    // vector<vector<int>> grid = {{1, 3, 0},
    //                             {1, 5, 1},
    //                             {4, 2, 1}};
    // int sr = 0, sc = 0, er = grid.size(), ec = grid[0].size();  //if grid.size()==0 || grid[0].size() return 0;
    // vector<vector<int>> dp(er, vector<int>(ec, 1e8));
    // cout << minpathsum(0, 0, er - 1, ec - 1, grid, dp) << endl;
    // print2d(dp);
    // cout << minpathsumDP(0, 0, er - 1, ec - 1, grid, dp) << endl;
}

//https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1#
//agr sr==er && sc==ec tou base case end mein bnega...blki base case bnna whin chahiye jahan col khtm
int goldmine(int sr, int sc, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    if (sc == ec) 
        return dp[sr][sc] = grid[sr][sc];

    if (dp[sr][sc] != 0)
        return dp[sr][sc];

    if (sc + 1 <= ec)
        dp[sr][sc] = max(dp[sr][sc], goldmine(sr, sc + 1, er, ec, grid, dp)); 

    if (sr - 1 <= er && sc + 1 <= ec && sr - 1 >= 0)
        dp[sr][sc] = max(dp[sr][sc], goldmine(sr - 1, sc + 1, er, ec, grid, dp));

    if (sr + 1 <= er && sc + 1 <= ec)
        dp[sr][sc] = max(dp[sr][sc], goldmine(sr + 1, sc + 1, er, ec, grid, dp));

    return dp[sr][sc] += grid[sr][sc];
}

int goldmineDP(int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    for (int sc = ec; sc >= 0; sc--)
    {
        for (int sr = er; sr >= 0; sr--)
        {
            if (sc == ec) 
            {
                dp[sr][sc] = grid[sr][sc];
                continue;
            }

            if (sc + 1 <= ec)
                dp[sr][sc] = max(dp[sr][sc], dp[sr][sc + 1]);

            if (sr - 1 <= er && sc + 1 <= ec && sr - 1 >= 0)
                dp[sr][sc] = max(dp[sr][sc], dp[sr - 1][sc + 1]);

            if (sr + 1 <= er && sc + 1 <= ec)
                dp[sr][sc] = max(dp[sr][sc], dp[sr + 1][sc + 1]);

            dp[sr][sc] += grid[sr][sc];
        }
    }
    
    int maxVal = -1e9;
    for (int r = 0; r <= er; r++)
    {
        maxVal = max(maxVal, dp[r][0]);
    }
    return maxVal;
}

void goldmineproblem()
{
    // vector<vector<int>> grid = {{10, 33, 13, 15},
    //                             {22, 21, 04, 1},
    //                             {5, 0, 2, 3},
    //                             {0, 6, 14, 2}};
    // vector<vector<int>> grid = {{1, 3, 1, 5},
    //                             {2, 2, 4, 1},
    //                             {5, 0, 2, 3},
    //                             {0, 6, 1, 2}};
    vector<vector<int>> grid = {{1,2},{3,4}};
    int sc = 0, er = grid.size(), ec = grid[0].size();
    vector<vector<int>> dp(er, vector<int>(ec, 0));
    // int res=-1e9;
    // for (int sr = 0; sr <= er - 1; sr++)
    // {
    //     res = max(res,goldmine(sr, sc, er - 1, ec - 1, grid, dp));
    // }
    // cout<<res;


    cout<<goldmineDP(er-1,ec-1,grid,dp);cout<<endl; //tabulation mein tou har point kelie calculate hoga but recursion mein agr first row first column kelie calculate horha hoga tou second row first column kbhi pahunch ni skta
    print2d(dp);
}

//T:o(6n) s:o(n)
int boardPath(int sp, int ep, vector<int> &dp)
{
    if (sp == ep)
        return dp[ep] = 1;

    if (dp[sp] != 0)
        return dp[sp];

    int count = 0;
    for (int i = 1; i <= 6; i++)
    {
        if (sp + i <= ep)
            count += boardPath(sp + i, ep, dp);
        else break;
    }
    return dp[sp] = count;
}

int boardPathDP(int sp, int ep, vector<int> &dp)
{
    for (int sp = ep; sp >= 0; sp--)
    {
        if (sp == ep)
        {
            dp[sp] = 1;
            continue;
        }

        int count = 0;
        for (int i = 1; i <= 6; i++)
        {
            if (sp + i <= ep)
                count += dp[sp + i];
            else
                break;
        }
        dp[sp] = count;
    }
    return dp[SP];
}

void showlist(list<int> l)
{
    list<int>::iterator itr = l.begin();
    for (; itr != l.end(); itr++)
    {
        cout << *itr << " ";
    }
    cout << endl;
}

//T:o(n) s:o(n)
int boardPathOpti()
{
    list<int> l;
    l.push_front(1);
    l.push_front(1);
    for (int i = 2; i <= 10; i++)
    {
        if (i >= 2 && i <= 6)
            l.push_front(2 * l.front());
        else
        {
            l.push_front(2 * l.front() - l.back());
            l.pop_back();
        }
    }
    // showlist(l);
    return l.front();
}

int boardPathOptiArr(int target)
{
    int n = 7;
    int arr[n] = {1, 1};
    int index = target % n;
    for (int i = 2; i <= 10; i++)
    {
        if (i >= 2 && i <= 6)
            arr[i] = 2 * arr[i - 1];
        else
            arr[i % n] = 2 * arr[(i - 1) % n] - arr[i % n];
    }
    // for(int ele:arr)
    // cout<<ele<<" ";
    // cout<<endl;
    return arr[index];
}

void boardpathproblem()
{
    int sp = 0, ep = 10;
    vector<int> dp(ep + 1, 0);
    // cout<<boardPathDP(sp,ep,dp)<<endl;
    cout<<boardPath(sp,ep,dp)<<endl;
    // print(dp);
    // cout << boardPathOpti() << endl;
    // cout << boardPathOptiArr(10) << endl;
}

//91
int decodeWays(string &s, int index, vector<int> &dp)  
{
    if (index == s.length())
        return dp[index] = 1;

    if (dp[index] != -1)
        return dp[index];

    if (s[index] == '0')
        return dp[index] = 0;

    int count = 0;
    count = decodeWays(s, index + 1, dp);

    if (index < s.length() - 1)
    {
        int num = (s[index] - '0') * 10 + s[index + 1] - '0';
        if (num <= 26)
            count += decodeWays(s, index + 2, dp);
    }

    return dp[index] = count;
}

int decodeWaysDP(string &s, vector<int> &dp)
{
    for (int index = s.length(); index >= 0; index--)
    {
        if (index == s.length())
        {
            dp[index] = 1;
            continue;
        }

        if (s[index] == '0')
        {
            dp[index] = 0;
            continue;
        }

        int count = 0;
        count += dp[index + 1];

        if (index < s.length() - 1)
        {
            int num = (s[index] - '0') * 10 + s[index + 1] - '0';
            if (num <= 26)
                count += dp[index + 2];
        }

        dp[index] = count;
    }
    return dp[0];
}

int decodeWaysOPTI(string &s)   ////o(n) & o(1) space
{
    int a = 1, b = 0,sum;  //a 1 character kelie aur b do jano kelie
    for (int index = s.length() - 1; index >= 0; index--)
    {
        sum = 0;
        if (s[index] == '0')
        {
            sum = 0;
        }
        else
        {
            sum = a;
            // if(index<s.length()-1)
            // {
                int num = (s[index] - '0') * 10 + s[index + 1] - '0'; //null kelie 0-48(0 minus 48)
                if (num <= 26)
                    sum += b; //no need of if condition
            // }
        }
        b = a;
        a = sum;
    }
    return sum;
}

void decodeways()
{
    string s = "1442017";
    int length = s.length();
    vector<int> dp(length + 1, -1);
    // cout << decodeWays(s, 0, dp) << endl;
    // cout << decodeWaysDP(s, dp) << endl;
    cout << decodeWaysOPTI(s) << endl;
    // if (s.length() == 0 || s[0] == '0')
    // cout<<0;
    // print(dp);
}

//639
long long decodewaystwo(string &s, int index, vector<long long> &dp)
{
    int mod = 1e9 + 7;
    if (index == s.length())
        return dp[index] = 1;

    if (dp[index] != -1)
        return dp[index];

    if (s[index] == '0')
        return dp[index] = 0;

    long long count = 0;
    if (s[index] == '*')
    {
        count = (count % mod + 9 * decodewaystwo(s, index + 1, dp) % mod) % mod;
        if (index < s.length() - 1 && s[index + 1] == '*')
            count = (count % mod + 15 * decodewaystwo(s, index + 2, dp) % mod) % mod;
        else if (index < s.length() - 1 && s[index + 1] >= '0' && s[index + 1] <= '6')
            count = (count % mod + 2 * decodewaystwo(s, index + 2, dp) % mod) % mod;
        else if (index < s.length() - 1 && s[index + 1] >= '7')
            count = (count % mod + decodewaystwo(s, index + 2, dp) % mod) % mod;
    }
    else
    {
        count = (count % mod + decodewaystwo(s, index + 1, dp) % mod) % mod;
        if (index < s.length() - 1 && s[index + 1] == '*')
        {
            if (s[index] == '1')
                count = (count % mod + 9 * decodewaystwo(s, index + 2, dp) % mod) % mod;
            else if (s[index] == '2')
                count = (count % mod + 6 * decodewaystwo(s, index + 2, dp) % mod) % mod;
            //nhi tou call hi ni
        }
        else if (index < s.length() - 1)
        {
            int num = (s[index] - '0') * 10 + (s[index + 1] - '0');
            if (num <= 26)
                count = (count % mod + decodewaystwo(s, index + 2, dp) % mod) % mod;
        }
    }

    return dp[index] = count;
}

long long decodewaystwoDP(string &s, vector<long long> &dp)
{
    int mod = 1e9 + 7;
    for (int index = s.length(); index >= 0; index--)
    {
        if (index == s.length())
        {
            dp[index] = 1;
            continue;
        }

        if (s[index] == '0')
        {
            dp[index] = 0;
            continue;
        }

        long long count = 0;
        if (s[index] == '*')
        {
            count = (count % mod + 9 * dp[index + 1] % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
                count = (count % mod + 15 * dp[index + 2] % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '0' && s[index + 1] <= '6')
                count = (count % mod + 2 * dp[index + 2] % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '7')
                count = (count % mod + dp[index + 2] % mod) % mod;
        }
        else
        {
            count = (count % mod + dp[index + 1] % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
            {
                if (s[index] == '1')
                    count = (count % mod + 9 * dp[index + 2] % mod) % mod;
                else if (s[index] == '2')
                    count = (count % mod + 6 * dp[index + 2] % mod) % mod;
            }
            else if (index < s.length() - 1)
            {
                int num = (s[index] - '0') * 10 + (s[index + 1] - '0');
                if (num <= 26)
                    count = (count % mod + dp[index + 2] % mod) % mod;
            }
        }

        dp[index] = count;
    }
    return dp[0];
}

long long decodewaystwoOPTI(string &s)
{
    int mod = 1e9 + 7;
    int a = 1, b = 0;
    long long count = 0;
    for (int index = s.length() - 1; index >= 0; index--)
    {
        count = 0;
        if (s[index] == '0')
            count = 0;

        else if (s[index] == '*')
        {
            count = (count % mod + 9 * a % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
                count = (count % mod + 15 * b % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '0' && s[index + 1] <= '6')
                count = (count % mod + 2 * b % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '7')
                count = (count % mod + b % mod) % mod;
        }
        else
        {
            count = (count % mod + a % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
            {
                if (s[index] == '1')
                    count = (count % mod + 9 * b % mod) % mod;
                else if (s[index] == '2')
                    count = (count % mod + 6 * b % mod) % mod;
            }
            else if (index < s.length() - 1)
            {
                int num = (s[index] - '0') * 10 + (s[index + 1] - '0');
                if (num <= 26)
                    count = (count % mod + b % mod) % mod;
            }
        }
        b = a;
        a = count;
    }
    return count;
}

void decodewaystwoprob()
{
    string s = "*56*3";
    int index = 0;
    int n = s.length();
    vector<long long> dp(n + 1, -1);
    // cout << decodewaystwo(s, index, dp) << endl;
    // cout << decodewaystwoDP(s, dp) << endl;
    // cout << decodewaystwoOPTI(s) << endl;
    // printlong(dp);
}

int main()
{
    // fibo();
    // friendsproblem();
    // climbingStairsproblem();
    // decodeways();
    // boardpathproblem();
    // decodewaystwoprob();
    // mp();
    // minCostClimbingStairsProb();
    // goldmineproblem();
    return 0;
}

public static void countBinaryString(int n) {
    int cendWithZeros = 1;
    int cendWithOne = 1;

    for (int i = 2; i <= n; i++) {
        int nendWithZeros = cendWithOne;
        int nendWithOne = cendWithOne + cendWithZeros;  //new

        cendWithZeros = nendWithZeros;
        cendWithOne = nendWithOne;  //old
    }

    System.out.println(cendWithZeros + cendWithOne);
}

public static void arrangeBuilding(int n) {
    long cBuilding = 1;
    long cSpace = 1;

    for (int i = 2; i <= n; i++) {  //i->plots
        long ncBuilding = cSpace;
        long ncSpace = cBuilding + cSpace;

        cSpace = ncSpace;
        cBuilding = ncBuilding;
    }

    long totalWays = cSpace + cBuilding;
    System.out.println(totalWays * totalWays);
}

public static int countSubsequenceABC(String str) {
    int a = 0, ab = 0, abc = 0;
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);

        if (ch == 'a')
            a = 2 * a + 1;
        else if (ch == 'b')
            ab = 2 * ab + a;
        else if (ch == 'c')
            abc = 2 * abc + ab;
    }
    return abc;
}

public static int maxSumNonAdjacentElement(int[] arr, int n) {
    int inc = arr[0];
    int exc = 0;

    for (int i = 1; i < n; i++) {
        int nexc = Math.max(inc, exc); // newExclude
        int ninc = exc + arr[i];

        inc = ninc;
        exc = nexc;
    }

    return Math.max(inc, exc);
}

public static int paintHouse(int cost[][],int n)
{
    for(int i=1;i<n;i++)
    {
        cost[i][0]=Math.min(cost[i-1][1],cost[i-1][2])+cost[i][0];
        cost[i][1]=Math.min(cost[i-1][0],cost[i-1][2])+cost[i][1];
        cost[i][2]=Math.min(cost[i-1][0],cost[i-1][1])+cost[i][2];
    }
    
    return Math.min(Math.min(cost[n-1][0],cost[n-1][1]),cost[n-1][2]);
}

public static int paintHouse2(int cost[][], int n, int k) {
    int dp[][] = new int[n][k];

    for (int i = 0; i < k; i++)
        dp[0][i] = cost[0][i];

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < k; j++) {
            int min = Integer.MAX_VALUE;

            for (int l = 0; l < k; l++) {
                if (l != j)
                    min = Math.min(min, dp[i][l]);
            }
            dp[i][j] = min + cost[i][j];
        }
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < k; i++)
        ans = Math.min(ans, dp[n - 1][i]);

    return ans;
}

public static int paintHouse2method(int cost[][], int n, int k) {
    int dp[][] = new int[n][k];
    int least = Integer.MAX_VALUE, sleast = Integer.MAX_VALUE;
    for (int i = 0; i < k; i++) {
        dp[0][i] = cost[0][i];
        if (dp[0][i] <= least) {
            sleast = least;
            least = dp[0][i];
        } else if (dp[0][i] < sleast)
            sleast = dp[0][i];
    }

    for (int i = 1; i < n; i++) {
        int nleast = Integer.MAX_VALUE;
        int nsleast = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            if (dp[i - 1][j] != least)
                dp[i][j] = least + cost[i][j];
            else
                dp[i][j] = sleast + cost[i][j];

            if (dp[i][j] <= nleast) {
                nsleast = nleast;
                nleast = dp[i][j];
            } else if (dp[i][j] < nsleast)
                nsleast = dp[i][j];
        }

        least = nleast;
        sleast = nsleast;
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < k; i++)
        ans = Math.min(ans, dp[n - 1][i]);

    return ans;
}

public static void paintFence(int n, int k) {
    int same = k;
    int different = k * (k - 1);
    int total = same + different;

    for (int i = 3; i <= n; i++) {
        same = different;
        different = total * (k - 1);
        total = same + different;
    }

    System.out.println(total);
}

public static int tilingwith2X1(int n, int[] dp) {
    if (n == 0 || n < 0)
        return n == 0 ? 1 : 0;

    if (dp[n] != -1)
        return dp[n];

    int a = tilingwith2X1(n - 1, dp);
    int b = tilingwith2X1(n - 2, dp);

    return dp[n] = a + b;
}

public static int tilingwith2X1_2(int n, int[] dp) {
    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= n; i++)
        dp[i] = dp[i - 1] + dp[i - 2];

    return dp[n];
}

//i did 2d dp..wrong 2dmsn change hi ni horhi
public static int TilingwithMX1_dp(int n, int m, int[] dp) {
    for (int i = 1; i <= n; i++) {
        if (i < m)
            dp[i] = 1;
        else if (i == m)
            dp[i] = 2;
        else
            dp[i] = dp[i - 1] + dp[i - m];
    }

    return dp[n];
}

public static void optimalStrategy(int[] arr) { 
    int n = arr.length;
    int[][] dp = new int[n][n];

    for (int gap = 0; gap < n; gap++) {
        for (int i = 0, j = gap; j < n; i++, j++) {
            if (gap == 0)
                dp[i][j] = arr[j];
            else if (gap == 1)
                dp[i][j] = Math.max(arr[i], arr[j]);
            else
                dp[i][j] = Math.max(arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]),
                        arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]));
        }
    }
    System.out.println(dp[0][n - 1]);
}

// 887
public static int eggDrop(int e, int f) {
    int[][] dp = new int[e + 1][f + 1];
    for (int i = 1; i <= e; i++) {
        for (int j = 1; j <= f; j++) {
            if (i == 1)
                dp[i][j] = j;
            else if (j == 1) {
                dp[i][j] = 1;
            } else {
                int min = (int) 1e8, k = 0;
                for (int pr = i - 1, pc = j - 1; pc >= 0; pc--) {  //pr:prev row,pc:prev col
                    int val = Math.max(dp[pr][k], dp[i][pc]);
                    min = Math.min(min, val);
                    k++;
                }
                dp[i][j] = min + 1;
            }
        }
    }
    return dp[e][f];
}

public static int rodcutting(int[] prices) {
    int np[] = new int[prices.length + 1];  //new price

    for (int i = 0; i < prices.length; i++)
        np[i + 1] = prices[i];

    int[] dp = new int[prices.length + 1];
    dp[0] = 0;
    dp[1] = np[1];

    for (int i = 2; i < dp.length; i++) {
        dp[i] = np[i];
        for (int j = 1, k = i - 1; j <= i / 2; j++, k--)
            dp[i] = Math.max(dp[i], dp[j] + dp[k]);
    }

    return dp[prices.length];
}

public static long partitionintoSubset(int n, int k, long[][] dp) { 

    if (n == 0 || k == 0 || n < k)
        return 0;

    for (int t = 1; t <= k; t++) {
        for (int p = 1; p <= n; p++) {
            if (p < t)
                dp[t][p] = 0;
            else if (t == p)
                dp[t][p] = 1;
            else {
                dp[t][p] = t * dp[t][p - 1] + dp[t - 1][p - 1];
            }
        }
    }
    return dp[k][n];
}

public static int templeoffering(int[] height) {

    int n = height.length;
    int[] forward = new int[n];

    forward[0] = 1;   //detect upar jaate hue pahad ki wall
    for (int i = 1; i < n; i++) {
        if (height[i] > height[i - 1])
            forward[i] = forward[i - 1] + 1;
        else
            forward[i] = 1;  //taaki girte hue right se ans aaye
    }

    int[] backward = new int[n];   //detect niche jaate hue ki wall

    backward[n - 1] = 1;
    for (int j = n - 2; j >= 0; j--) {
        if (height[j] > height[j + 1])
            backward[j] = backward[j + 1] + 1;
        else
            backward[j] = 1;
    }

    int result = 0;
    for (int i = 0; i < n; i++)
        result += Math.max(forward[i], backward[i]);

    return result;
}

// 279
public int numSquares(int n) {

    int[] dp = new int[n + 1];
    Arrays.fill(dp, (int) 1e9);
    dp[0] = 0;  //0 cannot be represented by any number square so dp[0]=0;
    dp[1] = 1; 

    for (int i = 2; i <= n; i++) {
        for (int j = 1; j * j <= i; j++) {
            int sum = i - j * j;
            dp[i] = Math.min(dp[i], dp[sum] + 1);
        }
    }
    return dp[n];
}

//403 
public int canCross_helper(int []stones,int jump,int src,int [][]dp,HashMap<Integer,Integer>hs)
{
    if(src==stones[stones.length-1]){
        return 1;
    }
    
    int idx=hs.get(src); //we cant use stone[i] for dp size but can use hashmap
    if(dp[idx][jump]!=-1)return dp[idx][jump];
    
    boolean res=false;
    if(jump-1>0 && hs.containsKey(src+jump-1))
        res=res||(canCross_helper(stones,jump-1,src+jump-1,dp,hs)==1); 
    
    if(hs.containsKey(src+jump))
    res=res||(canCross_helper(stones,jump,src+jump,dp,hs)==1); 
    
    if(hs.containsKey(src+jump+1))
    res=res||(canCross_helper(stones,jump+1,src+jump+1,dp,hs)==1);
    
    return dp[idx][jump]=res==true?1:0;
}

public boolean canCross(int[] stones) {
    if(stones[1]!=1)return false;
    int n=stones.length;
    int [][]dp=new int[n][1000];
    
    for(int []a:dp)
        Arrays.fill(a,-1);
    
    HashMap<Integer,Integer>hs=new HashMap<>();
    for(int i=0;i<stones.length;i++)
        hs.put(stones[i],i);
    return canCross_helper(stones,1,1,dp,hs)==1;
}

/*
1.(3)Fibonacci(O(n) & O(1) space)-1.Make array of n+1 size,initialise -1/0
                            2.if(n<=1)return dp[n]=n,memo
                            3.calc dp[n-1],dp[n-2]
                            4.r->dp[n]=dp[n-1]+dp[n-2]...Dirn->left to right
            
            M2-1.take a=0,b=1
            2.traverse from 2<=n and calc sum=a+b and shift a and b

2.(2+1+1+1+1)climbing stairs-1.base case n<=1 r->dp[n]=1 ...dirn left to right
            in pep qs can climb 1,2,3 stairs so if(n<0)r->0

            Climb Stair with Variable Moves-1.dp[n+1] bc:stair==n r->dp[n]=1(path 6->6)..memo
                                           2.c=0,for loop[1,jump[stairs]](stairs+j<=n)c+=call(s+j)
                                           3.dp[stairs]=c.....dirn right to left
                            
                            Minimum Moves-bc:dp[n]=0(chalo mat)
                                          2.res=min(res,call())
                                          3.dp[stairs]=res....right to left
            
            min cost climbing stairs-1.Initialise with 1e8.if n<=1 dp[n]=cost[n];
                        2.min(call(n-1),call(n-2))
                        3.dp[n]=min+(n<size?cost[n]:0)...dirn left to right

3.(3)Friends Pairing-1.base case n<=1 r->1.
                  2.call for(n-1) and call((n-2))*(n-1)
                  3.r->dp[n]=a+b.....loop left to right
                M3-a=1,b=1,sum=a loop 2 to <=n sum=a*(n-1)+b change a to b and b to sum

4.Power(log2(n))-1.dp[n+1]...initialise 0
                 2.if(n==0)r->dp[n]=1;memo
                 3.sAns=call(n/2);
                 4.if dp[n]=n%2==0?sAns*sAns:sAns*sAns*x

5.(3)Count Mazepath-M1-memoization-1.base condn:sr==er&&sc==ec return dp[er][ec]=1,
                        2.take count=0..check in if not out of matrix the call for each side(count+=call())
                        3.return dp[sr][sc]=count
            M2-dirnArray-Loop on dirn array
                    1.2.if(sr+d[i][0]<=er && sc+d[i][0]<=ec)
                            count+=call(sr+d[i][0],sc+d[i][0])
                    1.3 r->dp[sr][sc]=count
            M3-dirn from bottom right to left or bottom right to top.r->dp[SR][SC]

6.(3)Mazepath Multiple jump-memoization-same but loop
                                1.for(int j=1;sc+j<=ec;j++)call(sr,sc+j)
                    M2-dirnArray-same but inner loop of jump(1tomaxJump)
                        1.2 make x any y coord,and check if both exist then call
                        1.3 dp[sr][sc]=count
                    M3-dirns same

7*.(1)Mazepath obstacle-EC:check if at 0/0 obstacle value not present or at (endC/endR) if present r->0
                2.check if coordinates valid and the at those coordinates obstacle value not present.

8.(1)minimum path sum-initialise with 1e8
                   1.Base Case-dp[sr]][sc]=grid[sr][sc];
                   2.at call time dp[sr][sc]=min(dp[sr][sc],callls)
                   3.r->dp[sr][sc]+=grid[sr][sc]

9.(2)Gold Mine Problem-1.return case sc==ec return dp[sr][sc]=grid[sr][sc]
                    2.initialse max with -1e9 and update at each call.In main fn call from left row each time so sr will vary 

                    M2-Dirn will now be from bottom right to top  or from top right to down.same initialise and update for each row first column

11.(4)BoardPath:
        M1-Initialise with 0.Base case:if sp==ep r->dp[ep]=1;
            memo,for(1 to 1<=6)if sp+i<=ep c+=call(sp+i) else break
            r->dp[sp]=count
        M2-dirn from right to left(o(6n) time o(n)space)
        M3-1.take a list
           2.pushfront 2 time 1 value
           3.loop from 2 to <=10 if i[2,6] pushfront 2*front() else pushfront 2*front()-2*back() and popBack(T:o(n) s:o(n))
        M4:1.take array of size 7
           2.find idx(target%size(7))
           3.first two idx 1,1 and then loop[2,10] if[2,6] then a[i]=2*a[i-1] else a[i%sz]=2*a[i-1)%sz]-a[i%sz]       

12*.(3)decode ways(o(n),o(n) space)-EC:1.str length=0||first char 0 r->0
                                2.pass idx,dp(length+1)
                                3.bc:if idx==length r->dp[idx]=1;
                                4.memo,if s[idx]==0 r->0
                                5.count(c)=0,make call for idx+1,and if idx<l()-1 make num((s[idx]-'0)*10+s[idx+1]-'0') annd num<=26 c+=call(idx+2)..r->dp[n]=c
                M2-dirn loop right to left
                M3 O(1)space-Take a=1(single character call),b=0(double character call),sum=0 ..Iterate from last and if s[i]='0' sum=0
                else make num and if num<=26 then sum=a+b else sum=a...shift b=a and a=sum

            13.(3)decode ways-M1-same base case...if(s[idx]=='*)then c+=9*call(idx+1),check if next idx is * or digit
                                1.2 if(*) then 15*call(idx+2) (**)
                                1.3 if(digit>=0 & <=6)2*call(idx+2) (*d)
                                1.4 if(digit>=7)1*call(idx+2) (*d)
                            2.else c+=call(idx+1) check if next idx(*) 
                                2.1 if(s[idx] was 1)c+=9*call(idx+2) (d*)
                                    if(2)c+=6*call(idx+2)
                                2.2 (dd) call same as indecode way1 [check if next idx ava each time]
                            M2-iterate from last
                            M3-same as decodeways1

54.Rod Cutting-1.store cost at respective index in new array
               2.make dp[n+1]...dp[0=0]/1=arr[i]
               3.loop [2,n]..dp[i]=cost[i]..run j=1,k=i-1,j<=i/2
                    3.1 dp[i]=max(,dp[j]+dp[k])
               4.r->dp[n]

55.Perfect Square-1.src->dstn ..(dp[n+1],1e9) dp[0/1]=0/1
                  2.loop i=[2..n]...inner loop j=1;j*j<=i;j++
                    2.1 dp[i]=min(,dp[remSum]+1)
                  3.r->dp[n]

                  
*/