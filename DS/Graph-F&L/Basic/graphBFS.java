package Basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class graph{

    public static class Edge{
        int v;
        int w;

        Edge(int v,int w)
        {
            this.v=v;
            this.w=w;
        }
    }

    static int N=9;
    @SuppressWarnings("unchecked")
    static ArrayList<Edge>[]graph=new ArrayList[N];

    public static void addEdge(int u,int v,int w)
    {
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static int findEdge(int u,int v)
    {
        int idx=-1;
        for(int i=0;i<graph[u].size();i++)
        {
            if(graph[u].get(i).v==v)
            {
                idx=i;
                break;
            }
        }
        return idx;
    }

    public static void removeEdge(int u,int v)
    {
        int idx1=findEdge(u, v);
        int idx2=findEdge(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVertex(int u)
    {
        // for(Edge e:graph[u]) //dont use forEach loop for modification
        // for(int i=0;i<graph[u].size();i++) //remove from piche because of change in indexing
        for(int i=graph[u].size()-1;i>=0;i--) 
        {
            removeEdge(u, graph[u].get(i).v);
        }
        // graph[u]=null;  //null pointer exception aayga fir display krte tym
    }

    public static void display()
    {
        for(int i=0;i<N;i++)
        {
            System.out.print(i+" -> ");
            for(Edge e:graph[i])
            {
                System.out.print(e.v+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void constructGraph()
    {
        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        // addEdge(3, 4, 2);
        addEdge(4, 5, 3);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);
        addEdge(2, 7, 3);
        addEdge(2, 8, 13);
        addEdge(7, 8, 14);

        display();

        // removeEdge(0, 1);
        // display();

        // removeVertex(3);
        // display();

        // addEdge(0, 6, 20);

        // addEdge(7, 7, 10);  // 7 do baar add
    }

    //====================
    //1.cycle detection
    //2.shortest path
    //3.shortest path print kelie 
    public static void BFS_Cycle(int src,boolean []visited)
    {
        int dest=6;
        int level=0;
        int atLevel=-1;
        boolean isCycle=false;

        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);

        while(que.size()>0)
        {
            int size=que.size();
            System.out.print("Level "+level+" -> ");
            while(size-- > 0)
            {
                int rvtx=que.removeFirst();
                if(!visited[rvtx])System.out.print(rvtx+" ");

                if(visited[rvtx])
                {
                    isCycle=true;
                    continue;
                }
                
                visited[rvtx]=true;
                if(rvtx==dest)
                    atLevel=level;
                
                for(Edge e:graph[rvtx])
                {
                    if(!visited[e.v])
                    que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }

        System.out.println("isCycle: "+isCycle);
        System.out.println("level: "+ atLevel);
    }

    public static void BFS_ShortestPath(int src,boolean []visited)
    {
        int dest=6;
        int level=0;
        int atLevel=-1;

        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        visited[src]=true;
        while(que.size()>0)
        {
            int size=que.size();
            System.out.print("Level "+level+" -> ");
            while(size-- > 0)
            {
                int rvtx=que.removeFirst();
                System.out.print(rvtx+" ");

                if(rvtx==dest)
                    atLevel=level;

                for(Edge e:graph[rvtx])
                {
                    if(!visited[e.v])
                    {
                        visited[e.v]=true;
                        que.addLast(e.v);;
                    }
                }
            }
            System.out.println();
            level++;
        }
        System.out.println("level: "+ atLevel);
    }
    
    public static void printShortestPath(int src,boolean []visited)
    {
        int dest=6;
        int level=0;
        int atLevel=-1;
        int parent[]=new int[N];
        parent[src]=-1;

        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        visited[src]=true;
        while(que.size()>0)
        {
            int size=que.size();
            while(size-- > 0)
            {
                int rvtx=que.removeFirst();

                if(rvtx==dest)
                    atLevel=level;

                for(Edge e:graph[rvtx])
                {
                    if(!visited[e.v])
                    {
                        visited[e.v]=true;
                        que.addLast(e.v);
                        parent[e.v]=rvtx;
                    }
                }

            }
            System.out.println();
            level++;
        }

        int idx=dest;
        while(idx!=-1)
        {
            System.out.print(idx+" -> ");
            idx=parent[idx];
        }
    }

    public static int BFS_gcc()
    {
        boolean visited[]=new boolean[N];
        int components=0;
        for(int i=0;i<N;i++)
        {
            if(!visited[i])
            {   
                BFS_Cycle(i,visited);
                components++;
            }
        }
        return components;
    }

    static class Pair_ {
        int vertex;
        int time;

        Pair_(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }
    }

    public static int SpreadofInfection(ArrayList<Edge>[] graph, int visited[], int t, ArrayDeque<Pair_> dq) {
        int count = 0;
        while (dq.size() > 0) {
            Pair_ p = dq.pop();
            if (visited[p.vertex] == 1)
                continue;
            // count yahan mt bdaio nhi tou jab time 4 hoga aur condn andar check hogi usse
            // phle count increment ho chukega
            if (p.time == t + 1) {
                break;
            } else {
                visited[p.vertex] = 1;
                count++;
                for (Edge e : graph[p.vertex]) {
                    if (visited[e.nbr] == 0) {
                        dq.add(new Pair_(e.nbr, p.time + 1));
                    }
                }
            }
        }
        return count;

        // ArrayDeque<Pair>dq=new ArrayDeque<>();
        // int []visited=new int[vtces];
        // int infectedPerson=0;
        // dq.add(new Pair(src,1));
        // totalinfectedPersons=SpreadofInfection(graph,visited,t,dq);
    }

     // 994
    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int freshOranges = 0, time = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2)
                que.addLast(i * m + j);

                if (grid[i][j] == 1)
                freshOranges++;
            }
        }

        if (freshOranges == 0)
        return 0;
        int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;

                for (int i = 0; i < dirs.length; i++) {
                    int x = r + dirs[i][0];
                    int y = c + dirs[i][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        freshOranges--;
                        grid[x][y] = 2;
                        que.addLast(x * m + y);
                        if (freshOranges == 0)
                        return time + 1;
                    }
                }
            }
            time++;
        }

        return -1;
    }

    // 286
    //https://www.lintcode.com/problem/663/
    public void wallsAndGates(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int emptyRooms = 0, distance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)
                que.addLast(i * m + j);

                if (grid[i][j] == 2147483647)
                emptyRooms++;
            } 
            //-1 - A wall or an obstacle.
            // 0 - A gate.
            // INF - Infinity means an empty room
        }

        if (emptyRooms == 0)
        return;
        int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;

                for (int i = 0; i < dirs.length; i++) {
                    int x = r + dirs[i][0];
                    int y = c + dirs[i][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 2147483647) {
                        emptyRooms--;
                        grid[x][y] = distance + 1;
                        que.addLast(x * m + y);
                        if (emptyRooms == 0)
                        return;
                    }
                }
            }
            distance++;
        }
    }

    //1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid.length==0 || grid[0].length==0)return -1;
        
        int n=grid.length;
        if(grid[0][0]==1 || grid[n-1][n-1]==1)return -1;
              
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(0*n+0);
        grid[0][0]=1;
        int level=1;
        int[][]dirs={{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
    
        while(que.size()>0)
        {
            int size=que.size();
            while(size-- > 0)
            {
                int idx=que.removeFirst();
                int r=idx/n;
                int c=idx%n; 

                if(r==n-1 && c==n-1)
                    return level;  //counting on basis of nodes

                for(int i=0;i<dirs.length;i++)
                {
                    int x=r+dirs[i][0];
                    int y=c+dirs[i][1];

                    if(x>=0 && y>=0 && x<n && y<n && grid[x][y]==0)
                    {
                        grid[x][y]=1;
                        que.addLast(x*n+y);
                    }
                }
            }
            level++;
        }

        return -1;
    }

    //785
    public boolean isBipartite(int[][] graph,int []visited,int src) {
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        int color=0;
        boolean isCycle=false;

        while(que.size()>0)
        {
            int size=que.size();
            while(size-- > 0)
            {
                int rvtx=que.removeFirst();

                if(visited[rvtx]!=-1)
                {
                     isCycle=true;
                     if(visited[rvtx]!=color)
                     return false;

                     continue;
                }

                visited[rvtx]=color;
                for(int v:graph[rvtx])
                {
                    if(visited[v]==-1)
                        que.addLast(v);
                }
            }
            color=(color+1)%2;
        }
        return true;
    }    

    public boolean isBipartite(int[][] graph) {
     
        int n=graph.length;
        int []visited=new int[n];
        Arrays.fill(visited, -1);

        // boolean res=true;
        for(int i=0;i<n;i++)
        {
            // if(visited[i]==-1)
            // res=res&&isBipartite(graph,visited,i);
            if(visited[i]==-1 && !isBipartite(graph,visited,i))
                return false;
        }
        // return res;
        return true;
    }

    //jis bus mein hum  phele baith chuke hain tou uske path explore krne ka koi fayda ni hai nhi tou cycle ban jaygi...islye make a array for all buses to check hum uspr phle baithe h ya ni....

    //also check the stations where we already visited before so no need to enter it again in queue.

    //815
    /*
    int numBusesToDestination(vector<vector<int>> &routes, int src, int dest)
    {
        if(src==dest)return 0;
        int n=routes.size();

        int busNumber=0;
        unordered_map<int,vector<int>>busMapping;  //busStand vs bus number going to the busStand
        for(vector<int>&route:routes)
        {
            for(int busStand:route)
            busMapping[busStand].push_back(busNumber);

            busNumber++;
        }

        vector<bool>isBusSeen(n,false);
        unordered_set<int>isBusStandSeen;

        queue<int>que;
        que.push(src);
        isBusStandSeen.insert(src);

        int level=0;
        while(que.size()>0)
        {
            int size=que.size();

            while(size-->0)
            {
                int bs=que.front();que.pop();

                vector<int>allbus=busMapping[bs];
                for(int busNo:allbus)
                {
                    if(isBusSeen[busNo])
                        continue;

                    for(int b:routes[busNo])
                    {
                        if(isBusStandSeen.find(b)==isBusStandSeen.end())
                        {
                            que.push(b);
                            isBusStandSeen.insert(b);

                            if(b==dest)
                            return level+1;
                        }
                    }
                    isBusSeen[busNo]=true;
                }
            }
            level++;
        }

        return -1;
    }
    */

}
