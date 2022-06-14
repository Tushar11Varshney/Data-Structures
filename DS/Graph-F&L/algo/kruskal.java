package algo;
import java.util.ArrayList;
import java.util.Arrays;

public class kruskal {
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

    public static void unionFind(int [][]edges,int N)
    {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[]graph=new ArrayList[N];  

        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        par = new int[N];
        size = new int[N];
        for(int i=0;i<N;i++)
        {
            par[i]=i;
            size[i]=1;
        }

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
        }
        display(N, graph);
    }

    public static void solve()
    {
        int N=9;
        int [][]edges={{0,7,8},{7,6,1},{6,5,2},{5,4,10},{4,3,9},{3,2,7},{2,1,8},{1,0,4},{7,1,11},{7,8,7},{6,8,6},{8,2,2},{5,2,4},
        {5,3,14}};

        Arrays.sort(edges,(a,b)->{
            return a[2]-b[2]; //this-other  //default behavious increasing.
        });

        unionFind(edges, N);
    }

    public static void main(String []args)
    {
        solve();
    }
}
