#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

//lexicographically-smallest-equivalent-string
//https://www.codingninjas.com/codestudio/problem-details/smallest-equivalent-string_1381859
vector<int> parent(26);
int findParent(int u)
{
    return parent[u] == u ? u : parent[u] = findParent(parent[u]);
}

string smallestEqString(string a, string b, string s)
{
    for (int i = 0; i < 26; i++)
        parent[i] = i;

    for (int i = 0; i < a.length(); i++)
    {
        int p1 = findParent(a[i] - 'a');
        int p2 = findParent(b[i] - 'a');

        parent[p1] = min(p1, p2);
        parent[p2] = min(p1, p2);
    }

    string res = "";
    for (int i = 0; i < s.length(); i++)
    {
        res += (char)(findParent(s[i] - 'a') + 'a');
    }

    cout << res << endl;
    // for (int i = 0; i < 26; i++)
    //     cout<<parent[i]<<" ";

    return res;
}

//https://www.hackerrank.com/challenges/journey-to-the-moon/problem
//journey to moon
vector<int>par,size;
int findPar(int u)
{
    return par[u]==u?u:par[u]=findPar(par[u]);
}
long journeyToMoon(int n, vector<vector<int>> astronaut) {  //n-number of astronauts
    
    for(int i=0;i<n;i++)
    {
        par.push_back(i);
        size.push_back(1);
    }
    
    for(vector<int>&ast:astronaut)
    {
        int p1=findPar(ast[0]);
        int p2=findPar(ast[1]);
        
        if(p1!=p2)
        {
            par[p1]=p2;
            size[p2]+=size[p1];
        }
    }
    
    long sum=0,totalPair=0;
    for(int i=0;i<n;i++)
    {
        if(par[i]==i)
        {
            totalPair+=sum*size[i];
            sum+=size[i];
        }
    }     
    return totalPair;
} 

//200
int numIslands(vector<vector<char>> &grid)
{
    int m = grid.size();
    int n = grid[0].size();
    par.resize(m * n);
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
            par[i * n + j] = i * n + j;
    }
    vector<vector<int>> dirs = {{1, 0}, {0, 1}};

    int count = 0;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (grid[i][j] == '1')
            {
                count++;
                for (int d = 0; d < dirs.size(); d++)
                {
                    int x = i + dirs[d][0];
                    int y = j + dirs[d][1];

                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1')
                    {
                        int p1 = findPar(i * n + j);
                        int p2 = findPar(x * n + y);

                        if (p1 != p2)
                        {
                            count--;
                            par[p1] = p2;
                        }
                    }
                }
            }
        }
    }
    return count;
}

//695
int maxAreaOfIsland(vector<vector<int>> &grid)
{
    int m = grid.size();
    int n = grid[0].size();
    par.resize(m * n);
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
            par[i * n + j] = i * n + j;
    }
    vector<vector<int>> dirs = {{1, 0}, {0, 1}};
    vector<int>componentSize(m*n,1);

    int maxArea=0;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (grid[i][j] == 1)
            {
                int p1 = findPar(i * n + j);  //isme hume p1 bahaar hi rkhna pdega for testcase like[[1]] because agr andar rkhenge tou kabhi ye if condn mein jaayga ni tou p1 undefined reh jaayga..
                for (int d = 0; d < dirs.size(); d++)
                {
                    int x = i + dirs[d][0];
                    int y = j + dirs[d][1];

                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                    {
                        int p2 = findPar(x * n + y);
                        if (p1 != p2)
                        {
                            par[p2] = p1;  //dont change p1 parent.
                            componentSize[p1]+=componentSize[p2];
                        }
                    }
                    maxArea=max(maxArea,componentSize[p1]);
                }
            }
            else 
            componentSize[i*n+j]=0;
        }
    }
    
    for(int i=0;i<m*n;i++)
        cout<<componentSize[i]<<" ";
    return maxArea;
}

int main()
{
    // smallestEqString("leetcode","programs","sourcecode");
    // smallestEqString("parker","morris","parser");

    // minCostTosupplyWater(3,{1,2,2},{{1,2,1},{2,3,1}});
    return 0;
}

//305
//https://www.lintcode.com/problem/434/
struct Point
{
    int x;
    int y;
    Point() : x(0), y(0) {}
    Point(int a, int b) : x(a), y(b) {}
};
vector<int> par;
int findPar(int u)
{
    // return par[u]==-1?u:(par[u]=findPar(par[u]));
    return par[u] == u ? u : (par[u] = findPar(par[u]));
}
vector<int> numIslands2(int m, int n, vector<Point> &position)
{ //mrows n column
    par.resize(m * n);
    // par.resize(m*n,-1);
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
            par[i * n + j] = i * n + j;
    }

    vector<vector<int>> grid(m, vector<int>(n, 0));
    int count = 0;
    vector<vector<int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    vector<int> ans;

    for (Point p : position)
    {
        int i = p.x;
        int j = p.y;

        if (grid[i][j] != 1)  //agr repeat hui koi edge tou uska tou parent same hi pichli wali se so count decr nhi hoga
        // if(par[i*n+j]==-1)
        {
            grid[i][j] = 1;
            // par[i*n+j]=(i*n+j);
            count++;

            for (int d = 0; d < dirs.size(); d++)
            {
                int x = i + dirs[d][0];
                int y = j + dirs[d][1];

                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                // if (x >= 0 && x < m && y >= 0 && y < n && par[x*n+y]!=-1)
                {
                    int p1 = findPar(i * n + j);
                    int p2 = findPar(x * n + y);

                    if (p1 != p2)
                    {
                        count--;
                        par[p1] = p2;
                    }
                }
            }
        } 
        ans.push_back(count);
    }

    for (int ele : ans)
        cout << ele << " ";
    return ans;
}

//amazon-count components
int findPar(vector<int>&par,int u)
{
    return par[u]==-1?u:(par[u]=findPar(par,par[u]));    
}

int unionFind(vector<string>&related)
{
    int count=0,n=related.size();
    vector<vector<int>>dirs={{0,1},{1,0},{-1,0},{0,-1}};
    vector<int>par(n,-1);

    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            if(related[i][j]=='1')
            {
                int p1=findPar(par,i),p2=findPar(par,j);
                
                if(p1!=p2)
                {   
                    par[p1]=p2;
                }
            }

        }
    }
    
    for(int i:par)
    {
        if(i==-1)
        count++;
    }
    return count;
}
