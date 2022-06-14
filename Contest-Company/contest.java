public class contest {

    // 1845
    class SeatManager {

        TreeSet<Integer> ts;
        int n;

        public SeatManager(int n) {
            this.n = n;
            this.ts = new TreeSet<>();

            for (int i = 1; i <= n; i++)
                ts.add(i);
        }

        public int reserve() {
            int seatReserved = ts.first();
            ts.remove(seatReserved);
            return seatReserved;
        }

        public void unreserve(int seatNumber) {
            ts.add(seatNumber);
        }
    }

    class SeatManager {

        PriorityQueue<Integer> pq;
        int n;
        int count;

        public SeatManager(int n) {
            pq = new PriorityQueue<>();
            this.n = n;
            this.count = 0;
        }

        public int reserve() {
            if (pq.size() == 0)
                return ++count;

            return pq.poll();
        }

        public void unreserve(int seatNumber) {
            pq.add(seatNumber);
        }
    }

    // 1851
    // public void putAnswer(HashMap<Integer,List<Integer>>map,int val,int q,int
    // []result)
    // {
    // List<Integer>li=map.get(q);
    // int index=li.get(li.size()-1);
    // result[index]=val;
    // map.get(q).remove(li.size()-1);
    // }

    public int[] minInterval(int[][] intervals, int[] queries) {
        // HashMap<Integer,List<Integer>>map=new HashMap<>();
        // for(int i=0;i<queries.length;i++)
        // {
        // if(!map.containsKey(queries[i]))
        // {
        // map.put(queries[i],new ArrayList<>());
        // }
        // map.get(queries[i]).add(i);
        // }

        int[] result = new int[queries.length];
        int[] Q = queries.clone();
        Arrays.sort(Q);
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        int j = 0;
        for (int i = 0; i < Q.length; i++) {
            while (j < intervals.length && intervals[j][0] <= Q[i]) {
                int[] p = { intervals[j][1] - intervals[j][0] + 1, intervals[j++][1] };
                pq.add(p);
            }

            while (pq.size() > 0 && pq.peek()[1] < Q[i])
                pq.poll();

            if (pq.size() == 0)
                map.put(Q[i], -1);
            else
                map.put(Q[i], pq.peek()[0]);
        }

        for (int i = 0; i < queries.length; i++)
            result[i] = map.get(queries[i]);
        return result;
    }

    // 1847
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int k = queries.length;
        Integer indexes[] = new Integer[k];
        for (int i = 0; i < k; i++)
            indexes[i] = i;

        int result[] = new int[k];
        Arrays.sort(indexes, (a, b) -> {
            return queries[b][1] - queries[a][1];
        });
        Arrays.sort(rooms, (a, b) -> {
            return b[1] - a[1];
        });

        int j = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int index : indexes) {
            while (j < rooms.length && rooms[j][1] >= queries[index][1])
                ts.add(rooms[j++][0]);

            result[index] = closestRoom_helper(ts, queries[index][0]);
        }

        return result;
    }

    public int closestRoom_helper(TreeSet<Integer> ts, int preffered) {
        int ans = -1, absAns = (int) 1e8;
        Integer fl = ts.floor(preffered);
        Integer cl = ts.ceiling(preffered);

        if (fl != null) {
            ans = fl;
            absAns = Math.abs(fl - preffered);
        }
        if (cl != null && Math.abs(cl - preffered) < absAns)
            ans = cl;

        return ans;
    }

    // 1306
    public boolean canReach_helper(int[] arr, Boolean[] dp, int idx) {
        if (idx < 0 || idx >= arr.length)
            // if(idx<0 || idx>=arr.length || arr[idx]>arr.length)
            return false;

        if (arr[idx] == 0)
            return dp[idx] = true;

        if (dp[idx] != null)
            return dp[idx];

        // dp[idx]=false; //or use visited or same array
        // int jump=arr[idx];
        // arr[idx]+=arr.length;
        // res=canReach_helper(arr,dp,idx+jump)||canReach_helper(arr,dp,idx-jump);
        boolean res = false;
        res = canReach_helper(arr, dp, idx + arr[idx]) || canReach_helper(arr, dp, idx - arr[idx]);
        dp[idx] = res;

        return dp[idx];
    }

    public boolean canReach(int[] arr, int start) {
        Boolean[] dp = new Boolean[arr.length];
        return canReach_helper(arr, dp, start);
    }

    // 1696
    int maxResult(vector<int>& nums, int k) {
        int n=nums.size();
        vector<int>dp(n,-(int)1e8);
        multiset<int>ms;
        dp[n-1]=nums[n-1];
        ms.insert(dp[n-1]);
        for(int i=n-2;i>=0;i--)
        {
            // for(int j=1;j<=k;j++)
            // {
            //     if(i+j<n)
            //      dp[i]=max(dp[i],dp[i+j]+nums[i]);
            // }
            
            if(ms.size()>k)
            {
                auto it=ms.find(dp[i+k+1]);
                ms.erase(it);
            }
            // cout<<*ms.rbegin()<<endl;
            dp[i]=nums[i]+*ms.rbegin();
            ms.insert(dp[i]);
        }
        return dp[0];
    }



}