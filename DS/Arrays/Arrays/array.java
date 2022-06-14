import java.util.*;

public class array {

    //304
    int [][]mat;
    int [][]sum;
    public NumMatrix(int[][] matrix) {
        mat=matrix;
        int n=matrix.length,m=matrix[0].length;
        sum=new int[n+1][m+1];
        
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                //sum[i][j] contain sum from [0,0,i,j]
                sum[i][j]=sum[i][j-1]+sum[i-1][j]-sum[i-1][j-1]+matrix[i-1][j-1]; 
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;col1++;row2++;col2++;
        return sum[row2][col2]-sum[row2][col1-1]-sum[row1-1][col2]+sum[row1-1][col1-1];
    }

    // https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1#
    public long[] printFirstNegativeInteger(long A[], int N, int K) // space can be O(k) using queue...store only
    //negative number and que first element will be answer if queue empty then no first negative pop when first element on queue out of range.
    {
        int si = 0, ei = K - 1;
        long result[] = new long[N - K + 1];

        for (int i = 0; i < (N - K + 1); i++) {
            while (si <= ei && A[si] >= 0)
                si++;
            if (si > ei) // mila hi ni
            {
                result[i] = 0;
                ei++;
            } else if (ei - si == K - 1) // pehla element window ka
            {
                result[i] = A[si];
                ei++;
                si++; // just shift the window
            } else if (si < ei) {
                result[i] = A[si];
                ei++;
            } else if (ei == si) {
                if (A[si] >= 0) {
                    result[i] = 0;
                    ei++;
                    si++;
                } else {
                    result[i] = A[si];
                    ei = i + K;
                }
            }

        }
        return result;
    }

    int balanceArray(vector<int> &A)
    { //ib
        int sumEven = 0, sumOdd = 0, n = A.size();
        for (int i = 0; i < n; i++)
        {
            if (i % 2 == 0)
                sumEven += A[i];
            else
                sumOdd += A[i];
        }

        int count = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            if (i % 2 == 0)
            {
                sumEven = sumEven - A[i];
                if (sumEven == sumOdd)
                    count++;
                sumOdd = sumOdd + A[i];
            }
            else
            {
                sumOdd = sumOdd - A[i];
                if (sumEven == sumOdd)
                    count++;
                sumEven = sumEven + A[i];
            }
        }
        return count;
    }

    int nobleInteger(vector<int> &A) //ib
    {
        sort(A.begin(), A.end());

        if (A[A.size() - 1] == 0)
            return 1;
        for (int i = 0; i < A.size(); i++)
        {
            if (A[i] < 0)
                continue;
            else
            {
                // int val=A[i];
                // int count=0;  //tle
                // for(int j=i+1;j<A.size();j++)
                // {
                //     if(A[j]==val)A[j]= -val;
                //     else if(A[j]>val)count++;
                // }

                // if(count==val)return 1;

                if (i + 1 < A.size() && A[i + 1] != A[i] && A[i] == (A.size() - (i + 1)))
                    return 1;
            }
        }

        return -1;
    }

    int nobleInteger(vector<int> &A)
    {

        int n = A.size();
        vector<int> countArray(n + 1);

        for (int i = 0; i < n; i++)
        {
            if (A[i] >= n)
                countArray[n]++;

            else if (A[i] >= 0)
                countArray[A[i]]++;
        }

        int totalGreater = countArray[n];

        for (int i = n - 1; i >= 0; i--)
        {

            if (totalGreater == i && countArray[i] > 0)
                return 1;

            if (totalGreater > i)
                return -1;

            totalGreater += countArray[i];
        }

        return -1;
    }

    //73
    public void setZeroes(int[][] matrix) {
        boolean fr=false,fc=false;
        int n=matrix.length,m=matrix[0].length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]==0)
                {
                    if(i==0)fr=true;
                    if(j==0)fc=true;
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }
        
        for(int i=1;i<n;i++)
        {
            for(int j=1;j<m;j++)
            {
                if(matrix[0][j]==0||matrix[i][0]==0)
                    matrix[i][j]=0;
            }
        }
        
        if(fr)
        {
            for(int i=0;i<m;i++)
                matrix[0][i]=0;
        }
        
        if(fc)
        {
            for(int i=0;i<n;i++)
                matrix[i][0]=0;
        }
    }

    }



