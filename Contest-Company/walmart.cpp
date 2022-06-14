#include <iostream>
#include <vector>
using namespace std;

int moves=0;
int fun_helper(vector<int>&arr,int si,int ei,int minimum)
{
    if(si>ei)return  0;
    if(ei==si)return arr[ei];
    if(ei-si==1)return arr[ei]>arr[si]?arr[ei]:arr[si];

    moves+=minimum;
    int nextMin=1e9;
    vector<int>idxArray;
    for(int i=si;i<=ei;i++)
    {
        arr[i]-=minimum;
        if(arr[i]!=0)nextMin=min(nextMin,arr[i]);
        if(arr[i]==0)idxArray.push_back(i);
    }    

    for(int i:idxArray)
    {
        moves+=fun_helper(arr,si,i-1,nextMin);
        moves+=fun_helper(arr,i+1,ei,nextMin);
    }    
    return 0;
}

int main()
{
    // vector<int>ans={5,7,9,4,3,2};

    vector<int>ans={3,5,2,3,4}; 

    // vector<int>ans={8,20};
    int n=ans.size();

    int minimum=1e9;
    for(int ele:ans)
    minimum=min(minimum,ele);

    moves+=fun_helper(ans,0,n-1,minimum);
    cout<<moves<<endl;
}
