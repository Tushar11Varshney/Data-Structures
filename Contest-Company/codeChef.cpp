
#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

/*bool golf_Problem(int N, int x, int k)
{
	int ballPosn = k;
	while (ballPosn < N + 2)
	{
		if (ballPosn == x)
			return true;
		else if (ballPosn > x)
			break;
		ballPosn += k;
	}

	ballPosn = N + 1;
	while (ballPosn >= 0)
	{
		if (ballPosn == x)
			return true;
		else if (ballPosn < x)
			break;
		ballPosn -= k;
	}

	return false;
}*/

bool golf_Problem(int N, int x, int k)
{
	if (x % k == 0)
		return true;
	else if ((((N + 1) - x) % k) == 0)
		return true;
	else
		return false;
}

void display(vector<vector<char>> board)
{
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			cout << board[i][j] + " ";
		}
		cout << endl;
	}
}

int checkBoard(vector<vector<char>> board,char ch)
{
	if (board[0][0] == ch && board[1][1] == ch && board[2][2] == ch)
		return 1; //diagonal
	if (board[0][2] == ch && board[1][1] == ch && board[2][0] == ch)
		return 1; //antidiagonal
    
    for (int i = 0; i < 3; i++)
	{
		if (board[0][i] == ch && board[1][i] == ch && board[2][i] == ch)
			return 1; //column
		if (board[i][0] == ch && board[i][1] == ch && board[i][2] == ch)
			return 1; //row
	}


	return 0;
}

int answer(vector<vector<char>> board)
{
	int underScore=0;
	int Xcount=0;
	int Ocount=0;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			if (board[i][j] == '_')
				underScore++;
			else if(board[i][j] == 'X')
				Xcount++;
			else Ocount++;
		}
	}

	if(Ocount>Xcount)return 3;
	if(Xcount>Ocount+1)return 3;

	int Xwins=checkBoard(board,'X');
	int Owins=checkBoard(board,'O');

	if(Xwins==1 && Owins==1)return 3;
	if(Xwins==1 && Xcount<=Ocount)return 3;
	if(Owins==1 && Xcount>Ocount)return 3;
	if(Xwins==1 || Owins==1)return 1;
	if(underScore==0)return 1;
	return 2;
}

int maxSugar(int X,int A,int B)
{
    // return ((A/100.0) + ((100-X)*(B/100.0)) )*1000;    //simplify expressions divide mein decimal se zyaada galat aane ke chance
    return (A+ (100-X)*B)* 10 ;    
}

int mod=1e9+7;
long long int XOREquality(int n,vector<long long int>&dp)
{
    if(n==0) return dp[n]= 1;
    if(dp[n]!=0)return dp[n];
    long long int smallAns=XOREquality(n/2,dp)%mod;
    return dp[n]=n%2==0?(smallAns*smallAns)%mod:((smallAns*smallAns)%mod*2)%mod;
}

long long int gcd(long long int a,long long int b)
{
	while(b!=0)
	{
		long long int rem=a%b;
		a=b;
		b=rem;
	}
	return a;
}

long long int interestingSeq(int k)
{
	long long int sum=0;
	for(int i=1;i<=2*k;i++)
	{
		long long int fterm=k+i*i;
		long long int sterm=k+(i+1)*(i+1);

		sum+=gcd(fterm,sterm);
	}

	return sum;
}

void modularEquation(vector<int>&dp,int N,int M)
{
    for(int i=3;i<=N;i++)
	{
	       for(int j=1;j<N;j++)
	       {
	           int b=N,a=j;
        	   if((M-(M%b))%a==0)dp[i]++;
	       }
	       dp[i]+=dp[i-1];
	}
	   
	cout<<dp[N]<<endl;
}

int main()
{

	// ios_base::sync_with_stdio(false);
	// int T,N,x,k;
	// cin>>T;
	// while(T-->0)
	// {
	//     cin>>N>>x>>k;
	//     if(golf_Problem(N,x,k))
	//         cout<<"YES"<<endl;
	//     else cout<<"NO"<<endl;
	// }

	// int t;
	// cin >> t;

	// while (t-- > 0)
	// {
	// 	vector<string> str(3);
	// 	cin >> str[0] >> str[1] >> str[2];

	// 	vector<vector<char>> board(3, (vector<char>(3)));
	// 	for (int i = 0; i < 3; i++)
	// 	{
	// 		for (int j = 0; j < 3; j++)
	// 		{
	// 			board[i][j] = str[i][j];
	// 		}
	// 	}

	// 	cout<<answer(board)<<endl;
	// }

	// int t;
	// cin>>t;
	// while(t-->0)
	// {
	//     int Xdegree=0,initialSolubilty=0,incPerDegree=0;
	//     cin>>Xdegree>>initialSolubilty>>incPerDegree;
	    
	//     cout<<maxSugar(Xdegree,initialSolubilty,incPerDegree)<<endl;
	// }

	// int t;
	// cin>>t;
	// vector<long long int>dp(100000+1);
	// while(t-->0)
	// {
	// 	int N;
	// 	cin>>N;
	// 	cout<<XOREquality(N-1,dp)<<endl;
	// }

	// int t;
	// cin>>t;
	// while(t-->0)
	// {
	// 	int k;
	// 	cin>>k;
	// 	cout<<interestingSeq(k)<<endl;
	// }

	int t;cin>>t;
	while(t-->0)
	{
	   int N,M;
	   vector<int>dp(N+1,0);
	   cin>>N>>M;
	   dp[2]=1;
	   modularEquation(dp,N,M);
	}

	return 0;
}