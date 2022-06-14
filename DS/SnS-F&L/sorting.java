import java.util.*;

/*
Binary Search
bs on sorted matrix
guess Number
first bad version
Search Range(2)    //for 2dmatrix also
Transition pt
Binary search nearest index
Search Insert (2)
Find k closest
bs on row/colm sorted(2) 

Two sum unsorted/sorted   //pair with sumin sorted matrix
Target sum pair
Three sum
Three sum multi *
four sum(2)
four sum count(2)

Building receiving sunlight
chocolate distrbn
.Count triplets
.Triangle num
count 0 in matrix
roof top
.sqr root (2)
.Pow
Find pair with given diff

leftmost pivot index
search in rotated array 1/2
find min in rotated array(2)/kth rotation
bitonic search

Can ship within d days(2)
minimum #of page
psble to paint
split array
Koko eating banana 
smallest divisor threshold 
===(all qs above minimise)
--wood cutting 
Serving area cake
--Minimize max dist to station

LIS
Radius Heater
Complete ckt
.Median 2 sorted array
.Inversion count
*/

// https://practice.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1#
int countSmallerthanMid(vector<int> &A, int val)
{
    int si = 0, ei = A.size() - 1, mid = 0;
    while (si <= ei)
    {
        int mid = (si + (ei - si) / 2);
        if (A[mid] <= val)
        {
            si = mid + 1;
        }
        else
            ei = mid - 1;
    }
    return si;
}

// The median is the middle number in a sorted, ascending or descending
int median(vector<vector<int>> &A, int r, int c)
{
    int low = 0, high = 1e9;
    while (low <= high)
    {
        int mid = (low + (high - low) / 2);
        int count = 0;
        for (int i = 0; i < r; i++)
            count += countSmallerthanMid(A[i], mid);

        if (count <= (r * c) / 2)
            low = mid + 1;
        else
            high = mid - 1;
    }

    return low;
}

void pattern()
{
    vector<int> arr = {2, 5, 7, -8, 3, -2};
    // vector<int>arr={2,5,7,8,3,2};
    int n = arr.size(), maximum = 0;
    for (int ele : arr)
        maximum = max(ele, maximum);

    for (int i = 0; i < maximum; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int val = arr[j];
            if (val > 0 && (maximum - val) <= i)
                cout << "*\t";
            else
                cout << "\t";
        }
        cout << endl;
    }

    for (int ele : arr)
    {
        if (ele < 0)
            maximum = max(maximum, -ele);
    }

    for (int i = 0; i < maximum; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (arr[j] < 0)
            {
                int val = -arr[j];

                if (val > i)
                    cout << "*\t";
                else
                    cout << "\t";
            }
            else
                cout << "\t";
        }
        cout << endl;
    }
}

public class sorting {

    // pep pr isSmaller ,swap,isGreater fn derkhe the because portal pr inke hisaab se printing krai hai

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr) {
        // 8 7 6
        // ans-> 7 8 6 => 7 6 8
        // 6 7 8
        for (int i = 0; i < arr.length-1; i++) {   //loop for no of iteration
            int flag=0;
            for (int j = 1; j <= arr.length - i -1; j++) {   //us iteration mein kitne parts
                if (arr[j]<arr[j-1]) {                  
                    swap(arr, j, j - 1);
                    flag=1;
                }
            }
            if(flag==0){
                System.out.println("break in btw");
                break;
            }
        }
    }

    public static void selectionSort(int[] arr) {   
        for (int i = 0; i < arr.length - 1; i++) {       //loop for no of iteration
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {       //loop min dhundne kelie us posn pr jo aana chahiye
                if (arr[j]<arr[min])
                    min = j;
            }
            swap(arr, i, min); 
        }
    }

    public static void insertionSort(int[] arr) {
        // 9 8 7 6
        // 8 9 7 6
        // 8 7 9 6 => 7 8 9 6
        // 7 8 6 9 => 7 6 8 9 => 6 7 8 9 
        for (int i = 1; i < arr.length; i++) {        //loop for no of iteration
            for (int j = i; j >0; j--) {
                if (arr[j]<arr[j-1])
                    swap(arr, j, j - 1);
                else
                    break; // kyunki hum apne element sorted order mein maintain krke chlrhe the..tou agr koi element apne piche waale ko kisi tym pr beat nhi krpaaya tou usse piche waale kyunki usse chhote hain tou unko bhi kbhi beat nhi krpaayaga.
            }
        }
    }

    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {

        int res[] = new int[a.length + b.length];
        int k = 0, i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j])
                res[k++] = a[i++];
            // else if(b[j]<a[i]) //jab element dono array mein same ho tou dono array se copy krna h
            else
                res[k++] = b[j++];
            // else{
            // res[k++]=a[i++]; 
            // j++; //ye ni chlega bcz j incremented also

            // res[k++]=a[i++];
            // res[k++]=b[j++]; //ye chlega
            // }
        }
        while (i < a.length)
            res[k++] = a[i++];
        while (j < b.length)
            res[k++] = b[j++];

        return res;
    }

    public static int[] mergeSort(int[] arr, int lo, int hi) {
        if (lo == hi) {
            int bcarray[] = new int[1];
            bcarray[0] = arr[lo];
            return bcarray;
        }
        // else if(lo<hi){
        int mid = (lo + hi) / 2;
        int fp[] = mergeSort(arr, lo, mid);
        int sp[] = mergeSort(arr, mid + 1, hi);
        int res[] = mergeTwoSortedArrays(fp, sp);
        return res;
        // }
        // return new int[0]; //dmsn zruri   //cant do {} ya {0}
        // return null;
    }

    public static void partition(int[] arr, int pivot) {
        int i = 0, j = 0;
        // i to n unexplored
        // 0 to j-1 choote element ya brabar swap and i++ j++
        // j to i-1 bde elements i++
        while (i < arr.length) {
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else i++;
        }
    }

    public static int partition(int[] arr, int pivot, int lo, int hi) {
        System.out.println("pivot -> " + pivot);
        int i = lo, j = lo;
        while (i <= hi) {
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else i++;
        }
        System.out.println("pivot index -> " + (j - 1));
        return (j - 1);
    }

    public static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        // if(lo<=hi){
        int pivot = arr[hi];
        int pivotIndex = partition(arr, pivot, lo, hi);
        quickSort(arr, lo, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, hi);
        // }
    }

    //*
    public static int quickSelect(int[] arr, int lo, int hi, int k) { // k 0 based 
        int pivot = arr[hi];
        int pivotIndex = partition(arr, pivot, lo, hi);

        if (pivotIndex < k)
            return quickSelect(arr, pivotIndex + 1, hi, k);
        else if (pivotIndex > k)
            return quickSelect(arr, lo, pivotIndex - 1, k);
        else
            return arr[k];
    }

    public static void countSort(int[] arr, int min, int max) {

        int size = max - min + 1;
        int psum[] = new int[size];
        int sortedArray[] = new int[arr.length];
        for (int ele : arr) {
            psum[ele - min]++; // create frequency array first then psum array
        }
        psum[0]--;
        for (int i = 1; i < size; i++) {
            psum[i] = psum[i] + psum[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {  //frequency ke hisaab se krenge tou relative order is not maintained
            int val = arr[i];
            int index = psum[val - min];
            sortedArray[index] = val;
            psum[val - min]--;
        }
        
        for (int i = 0; i < arr.length; i++) // copy to arr
            arr[i] = sortedArray[i];
    }

    public static void countSortRadix(int[] arr, int exp) {
        int psum[] = new int[10];
        int sortedArray[] = new int[arr.length];
        for (int ele : arr) {
            psum[(ele / exp) % 10]++;
        }
        psum[0]--;
        for (int i = 1; i < 10; i++) {
            psum[i] = psum[i] + psum[i - 1];
        }
        
        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            int index = psum[(val / exp) % 10];
            sortedArray[index] = val;
            psum[(val / exp) % 10]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArray[i];
        }
    }

    public static void radixSort(int[] arr) {
        int max = -(int) 1e9;
        for (int ele : arr) {
            max = Math.max(max, ele);
        }
        int exp = 1;  //exponent
        while (exp <= max) {
            countSortRadix(arr, exp);
            exp = exp * 10;
        }
    }

    public static void countSortDate(String[] arr, int div, int mod, int range) {
        int psum[] = new int[range];
        String sortedArray[] = new String[arr.length];
        // for(int i=0;i<arr.length;i++)
        for (String ele : arr) {
            psum[(Integer.parseInt(ele, 10) / div) % mod]++;
        }
        psum[0]--;
        for (int i = 1; i < range; i++) {
            psum[i] = psum[i] + psum[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            String val = arr[i];
            int index = psum[(Integer.parseInt(val, 10) / div) % mod];
            sortedArray[index] = val;
            psum[(Integer.parseInt(val, 10) / div) % mod]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArray[i];
        }
    }

    public static void sortDates(String[] arr) {

        countSortDate(arr, 1000000, 100, 32); // day
        countSortDate(arr, 10000, 100, 13); // month
        countSortDate(arr, 1, 10000, 2501); // year

        // int i=057;
        // System.out.println(i);  //print 47
    }

    public static void main(String[] args) {
        // int arr[]={5,9,8,1,2};
        // int arr1[]={2,9,5,1,3};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr1);

        // int arr[] = { 7, 9, 4, 8, 3, 6, 2, 1 };
        // partition(arr, 5);
        // for (int ele : arr)
        //     System.out.println(ele);
    }

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/counting-elements-in-two-arrays-official/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/count-zeros-xor-pairs-official/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/distinct-absolute-array-elements-official/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/find-the-element-that-appears-once-in-sorted-array--offcial/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/maximum-number-of-1s-row-official/ojquestion

}
