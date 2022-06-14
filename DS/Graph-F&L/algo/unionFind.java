package algo;
import java.util.ArrayList;
public class unionFind {
    
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(int N, ArrayList<Edge>[] graph) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    static int[] par;
    static int[] size;
    
    public static int findPar(int u)
    {
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }

    // O(1)
    public static void merge(int p1,int p2)
    {
        if(size[p1]>size[p2])
        {
            par[p2]=p1;
            size[p1]+=size[p2];
        }
        else{
            par[p1]=p2;
            size[p2]+=size[p1];
        }
    }

    // Edges : {{u,v,w}.....}
    // without Path Compression and size : V + E*V
    // Path Compression: V + E(alpha(n))
    // without Path Compression with size: V + ELog(V)

    public static void unionFind(int [][]edges,int N)
    {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[]graph=new ArrayList[N];  //isme warning isliye aati h kyunki hum right side pr ye bolrhe h ki N size ki arrayList bnani h pr usme koi bhi object aa skta hai..pr left side mein specifically bolrhe hai it is of Edge type so 
        // java give us warning ki bhai kuch gadBad hoskti hai,..then hum is warning ko suppressed krte hain..

        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        par = new int[N];
        size = new int[N];
        for(int i=0;i<N;i++)
        {
            par[i]=i;
            size[i]=1;
        }

        boolean isCycle=false;

        for(int []edge:edges)
        {
            int u=edge[0],v=edge[1],w=edge[2];

            int p1=findPar(u);
            int p2=findPar(v);
 
            if(p1!=p2)
            {
                merge(p1,p2);
                addEdge(graph, u, v, w);
            }
            else isCycle=true;
        }

        System.out.println(isCycle);
        display(N, graph);
    }

    public static void main(String []args)
    {
        int N=9;
        int [][]edges={{7,6,10},{5,6,10},{5,3,10},{6,8,10},{7,8,10},{2,5,10},{0,7,10},{0,1,10},{1,7,10},{1,2,10},{2,3,10},{3,4,10},{4,5,10}};

        unionFind(edges, N);
    }

    //684
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        par = new int[n+1];
        size = new int[n+1];
        for(int i=0;i<n+1;i++)
        {
            par[i]=i;
            size[i]=1;
        }

        for(int []edge:edges)
        {
            int u=edge[0],v=edge[1];

            int p1=findPar(u);
            int p2=findPar(v);

            if(p1!=p2)
                merge(p1,p2);
            else
                return new int[]{u,v};
        }
        
        return new int[]{};
    }
    
    //547
    public int findParent(int u,int []par)
    {
        return par[u]==u?u:(par[u]=findParent(par[u],par));
    }
    
    public int findCircleNum(int[][] isConnected) {
        int n=isConnected.length;
        int []par=new int[n];
        for(int i=0;i<n;i++)
            par[i]=i;
        
        int component=n;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=j && isConnected[i][j]==1)
                {
                    int u=i;
                    int v=j;
                    
                    int p1=findParent(u,par);
                    int p2=findParent(v,par);
                    
                    if(p1!=p2)
                    {
                        par[p1]=p2;
                        component--;
                    }
                }
            }
        }
        return component;
    }
    
    //1319
    public int makeConnected(int n, int[][] connections) {
        int []par=new int[n];
        for(int i=0;i<n;i++)
            par[i]=i;
        
            int extrawire=0,component=n;
            for(int []c:connections)
            {
                int u=c[0];
                int v=c[1];
    
                int p1=findParent(u,par);
                int p2=findParent(v,par);
    
                if(p1!=p2)
                {
                    par[p1]=p2;
                    component--;
                }
                else
                    extrawire++;
            }
                   
            if(extrawire>=component-1)
                return component-1;
            else if(component==1)
                return 0;
    }

    //990
    public boolean equationsPossible(String[] equations) {
        int n=26;
        int []par=new int[n];
        for(int i=0;i<n;i++)
            par[i]=i;
        
        for(int i=0;i<equations.length;i++)
        {
            if(equations[i].charAt(1)=='=')
            {
                int u=equations[i].charAt(0)-'a';
                int v=equations[i].charAt(3)-'a';
                
                int p1=findParent(u,par);
                int p2=findParent(v,par);
                
                if(p1!=p2)
                    par[p1]=p2;
            }
        }
        
        for(int i=0;i<equations.length;i++)
        {
            if(equations[i].charAt(1)=='!')
            {
                int u=equations[i].charAt(0)-'a';
                int v=equations[i].charAt(3)-'a';
                
                int p1=findParent(u,par);
                int p2=findParent(v,par);
                
                if(p1==p2)
                    return false;
            }
        }
        
        return true;
    }
    
    //lexicographically-smallest-equivalent-string

    //839
    int []parentArray;
    public int findParent(int u)
    {
        return parentArray[u]==u?u:(parentArray[u]=findParent(parentArray[u]));
    }

    public boolean isSimilar(String str1,String str2)
    {
        int count=0;
        for(int i=0;i<str1.length();i++)
        {
            if(str1.charAt(i)!=str2.charAt(i) && ++count>2)return false;
        }
        return true;
    }
    
    public int numSimilarGroups(String[] strs) {
        parentArray=new int[strs.length];
        for(int i=0;i<strs.length;i++)parentArray[i]=i;
        int group=strs.length;
        for(int i=0;i<strs.length-1;i++)
        {
            for(int j=i+1;j<strs.length;j++)
            {
                if(isSimilar(strs[i],strs[j]))
                {
                    int par1=findParent(i);
                    int par2=findParent(j);

                    if(par1!=par2)
                    {
                        parentArray[par1]=par2;
                        group--;
                    }
                }
            }
        }
        return group;
    }

    //924
    //https://leetcode.com/problems/minimize-malware-spread/discuss/614031/C%2B%2B-%3A-Union-Find-reframe-the-question-on-%22CORONA%22-with-relatable-explanation
    int []par;
    int []size;
    //[[1,1,1,0,0,0],[1,1,1,0,0,0],[1,1,1,0,0,0],[0,0,0,1,1,1],[0,0,0,1,1,1],[0,0,0,1,1,1]] [3,1]
    public int findPar(int u)
    {
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n=graph.length;
        par=new int[n];
        size=new int[n];
        
        for(int i=0;i<n;i++)
        {
            par[i]=i;
            size[i]=1;
        }
        
        Arrays.sort(initial); //if multiple node can be removed return with smallest index
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=j && graph[i][j]==1)
                {
                    int p1=findPar(i);
                    int p2=findPar(j);
                    
                    if(p1!=p2)
                    {
                        par[p1]=p2;
                        size[p2]+=size[p1];  //size of country
                    }
                }
            }
        }
        
        int []infectedNodesinCity=new int[n];  //initially
        for(int i:initial)
        {
            int leader=findPar(i);
            infectedNodesinCity[leader]++;  //patient of each country
        }
        
        int ans=initial[0];
        int maxPopulatedCity=0;
        for(int i:initial)
        {
            int noofInfectedPeople=infectedNodesinCity[findPar(i)];
            if(noofInfectedPeople==1 && size[findPar(i)]>maxPopulatedCity)
            {
                maxPopulatedCity=size[findPar(i)];
                ans=i;
            }
        }
        return ans;    
    }

    //959
    int []par;
    int count=1;
    public int findPar(int u)
    {
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }
    public void merge(int idx1,int idx2)
    {
        int p1=findPar(idx1);
        int p2=findPar(idx2);
        
        if(p1!=p2)
        {
            par[p1]=p2;
        }
        else count++;
    }
    public int regionsBySlashes(String[] grid) {
        int n=grid.length;
        par=new int[(n+1)*(n+1)];
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=n;j++)
            {   
                int idx=i*(n+1)+j;
                if(i==0||j==0||i==n||j==n)
                {
                    par[idx]=0;
                }
                else 
                par[idx]=idx;
            }
        }

        for(int i=0;i<n;i++)
        {
            char[] ch = grid[i].toCharArray(); 
            for(int j=0;j<n;j++)
            {
                if(ch[j]==' ')continue;
                else if(ch[j]=='/'){
                    int idx1=i*(n+1)+(j+1);
                    int idx2=(i+1)*(n+1)+j;
                    
                    merge(idx1,idx2);
                }
                else if(ch[j]=='\\'){
                    int idx1=i*(n+1)+j;
                    int idx2=(i+1)*(n+1)+(j+1);
                    
                    merge(idx1,idx2);
                }
            }
        }
        return count;
    }

    //redundant connection 2
    int n;
    int []par;
    public int findParent(int u)
    {
        return par[u]=(par[u]==u)?u:findParent(par[u]);
    }
    
    public boolean union(int u,int v) {
        int p1=findParent(u);
        int p2=findParent(v);

        if(p1!=p2)
        {
           par[p1]=p2;
           return false;
        }  
        return true;
    }
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        n=edges.length;
        int []indegree=new int[n+1];
        par=new int[n+1];
        
        for(int i=0;i<n+1;i++)
            par[i]=i;
        
        Arrays.fill(indegree,-1);
        int idx1=-1,idx2=-1;
        for(int i=0;i<n;i++)
        {
            int v=edges[i][1];
            
            if(indegree[v]==-1)
            indegree[v]=i;
            else
            {
                idx1=i;
                idx2=indegree[v];
                break;
            }
        }
        
        for(int i=0;i<n;i++)
        {
            if(i==idx1)continue;
            
            int u=edges[i][0];
            int v=edges[i][1];
            
            boolean flag=union(u,v);  //ek ek krke dkhna pdega direct union find on directed graph cant apply.
            if(flag==true)  //cycle present
            {
                if(idx1==-1) //cycle present but not 2 parent
                    return edges[i]; 
                else  //cycle present + 2 parent 
                    return edges[idx2]; //but galat blacklisted
            }
        }
        
        return edges[idx1]; 
    } 

}
