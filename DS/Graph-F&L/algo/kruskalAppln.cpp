#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <queue>
using namespace std;

//https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
vector<int> par;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

int mrPresident(int n, long k, vector<vector<int>> &edges)
{
    for (int i = 0; i <= n; i++)
        par.push_back(i);

    vector<int> mstEdgeWeight;
    long overallWeightSum = 0;
    for (vector<int> &edge : edges)
    {
        int p1 = findPar(edge[0]);
        int p2 = findPar(edge[1]);

        if (p1 != p2)
        {
            par[p1] = p2;
            mstEdgeWeight.push_back(edge[2]);
            overallWeightSum += edge[2];
            n--;
        }
    }

    if (n > 1)
        return -1;
    if (overallWeightSum <= k)
        return 0;

    int transform = 0;
    for (int i = mstEdgeWeight.size() - 1; i >= 0; i--)
    {
        transform++;
        overallWeightSum = overallWeightSum - mstEdgeWeight[i] + 1;
        if (overallWeightSum <= k)
            return transform; 
    }

    return -1;  //edge case see in copy
}

void mrPresident()
{
    ios_base::sync_with_stdio(false);
    int n, m;
    long k; //n-cities,m-roads,k-cost of maintenance
    cin >> n >> m >> k;
    vector<vector<int>> edges;
    for (int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        edges.push_back({u, v, w});
    }

    sort(edges.begin(), edges.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    cout << mrPresident(n, k, edges);
}


//https://www.codingninjas.com/codestudio/problem-details/water-supply-in-a-village_1380956
//1168
// vector<int> par;
// int findPar(int u)
// {
//     return par[u] == u ? u : (par[u] = findPar(par[u]));
// }

int unionFind(vector<vector<int>> edges, int N)
{
    par.resize(N + 1);
    for (int i = 0; i <= N; i++)
    {
        par[i] = i;
    }

    int cost = 0;
    for (vector<int> &edge : edges)
    {
        int u = edge[0], v = edge[1], w = edge[2];

        int p1 = findPar(u);
        int p2 = findPar(v);

        if (p1 != p2)
        {
            par[p1] = p2;
            cost += w;
        }
    }

    cout << cost;
    return cost;
}

int minCostTosupplyWater(int n, vector<int> wells, vector<vector<int>> pipes)
{
    for (int i = 0; i < wells.size(); i++)
        pipes.push_back({0, i + 1, wells[i]});

    sort(pipes.begin(), pipes.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    return unionFind(pipes, n);
}

int main()
{
    mrPresident();
    return 0;
}
