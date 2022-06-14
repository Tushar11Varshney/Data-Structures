import java.util.*;

public class strings {   

//1844
string replaceDigits(string s)
{
    for (int i = 1; i < s.length(); i += 2)
    {
        s[i]=(s[i-1]+(s[i]-'0'));  //java mein char mein typecast
    }
    return s;
}

// 1880
public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
    int numFirst = 0;
    // String numFirst="";
    for (int i = 0; i < firstWord.length(); i++) {
        // numFirst+=firstWord.charAt(i)-'a';
        numFirst = numFirst * 10 + (firstWord.charAt(i) - 'a');
    }

    int numSecond = 0;
    for (int i = 0; i < secondWord.length(); i++) 
        numSecond = numSecond * 10 + (secondWord.charAt(i) - 'a');

    int numTarget = 0;
    for (int i = 0; i < targetWord.length(); i++)
        numTarget = numTarget * 10 + (targetWord.charAt(i) - 'a');

    // System.out.println(numFirst+" "+numSecond+" "+numTarget); 
    // if(Integer.parseInt(numFirst)+Integer.parseInt(numSecond)==Integer.parseInt(numTarget))
    if (numFirst + numSecond == numTarget)
        return true;
    return false;
}

// 412
public List<String> fizzBuzz(int n) {
    List<String> ans = new ArrayList<>();  // return a string array answer (1-indexed)
    for (int i = 0; i < n; i++) {
        if ((i + 1) % 3 == 0 && (i + 1) % 5 == 0)
            ans.add("FizzBuzz");
        else if ((i + 1) % 3 == 0)
            ans.add("Fizz");
        else if ((i + 1) % 5 == 0)
            ans.add("Buzz");
        else
            ans.add((i + 1) + "");
    }
    return ans;
}

// string vs stringbuilder(immutable/slow -- mutable/fast)..just like arrayList
public static void StringBuilderVSString() {
    // String str1="abcd tushar huly";
    // String arr[]=str1.split(" ");
    // for(int i=0;i<arr.length;i++) //arr ek normal array h string ki so length not length()
    // System.out.println(arr[i]);

    // StringBuilder sb=new StringBuilder("hello");
    // System.out.println(sb.append(" append"));
    // System.out.println(sb.insert(2," insert")); //2 index par
    // sb.setCharAt(0, '9');
    // System.out.println(sb);  //hello
    // System.out.println(sb.deleteCharAt(4)); //4th index waala


    // String s="";
    // StringBuilder sb=new StringBuilder("");
    // long start=System.currentTimeMillis();
    // for(int i=0;i<1000000;i++)
    // // s+=i;
    // sb.append(i);
    // long end=System.currentTimeMillis();
    // long duration=end-start;
    // System.out.println(duration);
}

public static void allsubstring() {
    String str = "abcd";
    for (int i = 0; i < str.length(); i++)
        for (int j = i + 1; j <= str.length(); j++)
            System.out.println(str.substring(i, j));  
}

public static boolean isPalindromic(String str) {
    int i = 0, j = str.length() - 1;
    while (i < j) {
        char ch1 = str.charAt(i);
        char ch2 = str.charAt(j);
        if (ch1 == ch2) // ye tou character hain inme equals vgra ni hota check directly
        {
            i++;
            j--;
        } else return false;
    }
    return true;
}

public static void palindromicSubstring(String str) {
    for (int i = 0; i <str.length(); i++) {
        for (int j = i + 1; j <= str.length(); j++) {
            if (isPalindromic(str.substring(i, j))) { 
                System.out.println(str.substring(i, j));
            }
        }
    }
}

public static String toggleCase(String str) {
    // String str1 = "";
    // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // if (ch >= 65 && ch <= 90)
        // ch += 32;
        // else if (ch >= 97 && ch <= 122)
        // ch -= 32;
    
        // str1 += ch;
    // }
    // return str1;

    StringBuilder sb = new StringBuilder(str);
    for (int i = 0; i < sb.length(); i++) {
        char ch = sb.charAt(i);
        if (ch >= 'A' && ch <= 'Z') {
            char lc = (char) (ch + 'a' - 'A');
            sb.setCharAt(i, lc);
        } else if (ch >= 'a' && ch <= 'z') {
            char uc = (char) (ch + 'A' - 'a');
            sb.setCharAt(i, uc);
        }
    }
    return sb.toString();
}

// 796
public boolean rotateString_(String A, String B) {
    return A.length() == B.length() && (A + A).contains(B); 
}

public boolean rotateString(String s, String goal) {
    if (s.equals(goal))
        return true;

    StringBuilder sb = new StringBuilder(s);
    int n = s.length();

    for (int i = 1; i < n; i++) {
        char ch = sb.charAt(0);
        sb.deleteCharAt(0);
        sb.append(ch);
        if (goal.equals(sb.toString()))return true;
    }
    return false;
}

// ib
public boolean isVowel(char ch) {
    if (ch == 'A' || ch == 'a' || ch == 'E' || ch == 'e' || ch == 'I' || ch == 'i' || ch == 'O' || ch == 'o'
            || ch == 'U' || ch == 'u')return true;
    return false;
}

public int amazingSubstring(String A) { // substring which start with vowel
    int n = A.length();
    int ans = 0;
    int mod = 10003;
    for (int i = 0; i < n; i++) {
        if (isVowel(A.charAt(i))) {
            ans+=(n-i)%mod;  //just like eg:mod=10 then ans=5+6..then at return time it makes 1
            //(a+b)%mod=(a%mod+b%mod)%mod;
        }
    }

    return ans%mod;
}

public static String stringwithASCIIDiffernce(String str) {
    StringBuilder sb=new StringBuilder();
    for(int i=0;i<str.length()-1;i++)
    {
        int differnce=str.charAt(i+1)-str.charAt(i);
        // sb.append(str.charAt(i)+Integer.toString(differnce));
        sb.append(str.charAt(i));  
        sb.append(differnce);
        //cant do sb.append(str.charAt(i)+differnce) output galat "abc"->9899c
    }
    sb.append(str.charAt(str.length()-1));
    return sb.toString();  //null allowed 
}

// 125
bool isAlphabet(char ch)
{
    if (ch >= 97 && ch <= 122)
        return true;
    if (ch >= 65 && ch <= 90)
        return true;
    return false;
}

bool isNumber(char ch)
{
    if (ch >= 48 && ch <= 57)
        return true;
    return false;
}

bool isPalindrome(string s) // test case:0p
{
    int i = 0, j = s.length() - 1;
    while (i < j)
    {
        char ch1 = s[i];
        char ch2 = s[j];

        if ((!isAlphabet(ch1) && !isNumber(ch1)) && (!isNumber(ch2) && !isAlphabet(ch2)))
        {
            i++;
            j--;
        }
        else if (!isAlphabet(ch1) && !isNumber(ch1))
            i++;
        else if (!isAlphabet(ch2) && !isNumber(ch2))
            j--;
        else
        {
            if (ch1 == ch2 || (isAlphabet(ch1) && isAlphabet(ch2) && abs(ch1 - ch2) == 32))
            {
                i++;
                j--;
            }
            else return false;
        }
    }

    return true;
}

// 680
bool validPalindrome(string s)  
{
    int i = 0, j = s.length() - 1;
    while (i < j)
    {
        if (s[i] == s[j])
        {
            i++;
            j--;
        }
        else
            return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
    }
    return true;
}

bool isPalindrome(string s, int i, int j)
{
    while (i < j)
    {
        if (s[i] == s[j])
        {
            i++;
            j--;
        }
        else return false;
    }
    return true;
}

// 13
public int romanToInt(String s) {
    int number = 0;
    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (ch == 'I')
            number += 1;
        else if (ch == 'V') {
            number += 5;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'I')
                number -= 2;
        } else if (ch == 'X') {
            number += 10;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'I')
                number -= 2;
        } else if (ch == 'L') {
            number += 50;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'X')
                number -= 20;
        } else if (ch == 'C') {
            number += 100;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'X')
                number -= 20;
        } else if (ch == 'D') {
            number += 500;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'C')
                number -= 200;
        } else if (ch == 'M') {
            number += 1000;
            if (i - 1 >= 0 && s.charAt(i - 1) == 'C')
                number -= 200;
        }
    }

    return number;
}

// 13
public int myValue(char ch) {
    if (ch == 'I') 
        return 1;
    else if (ch == 'V')
        return 5;
    else if (ch == 'X')
        return 10;
    else if (ch == 'L')
        return 50;
    else if (ch == 'C')
        return 100;
    else if (ch == 'D')
        return 500;
    else if (ch == 'M')
        return 1000;

    return 0;
}

public int romanToInt2(String s) {
    int number = 0;
    for (int i = 0; i < s.length(); i++) {
        if (i + 1 < s.length() && myValue(s.charAt(i)) < myValue(s.charAt(i + 1))) {
            number += myValue(s.charAt(i + 1)) - myValue(s.charAt(i));
            i = i + 1;
        } else  number += myValue(s.charAt(i));
    }
    return number;
}

//165
int compareVersion(string version1, string version2)
{
    int i = 0, j = 0;
    int v1Length = version1.length(), v2Length = version2.length();

    while (i < v1Length || j < v2Length)
    {
        int temp1 = 0;
        int temp2 = 0;

        while (i < v1Length && version1[i] != '.')
            temp1 = temp1 * 10 + (version1[i++] - '0');

        while (j < v2Length && version2[j] != '.')
            temp2 = temp2 * 10 + (version2[j++] - '0');

        if (temp1 > temp2)
            return 1;
        else if (temp1 < temp2)
            return -1;
        else
        {
            i++;
            j++;
        }
    }

    return 0;
}

// 1881
public String maxValue(String n, int x) {
    if (n == null || n.length() == 0)
        return x + "";
    char ch = n.charAt(0);
    if (ch != '-') {
        for (int i = 0; i < n.length(); i++) {
            if (x > (n.charAt(i) - '0'))
                return n.substring(0, i) + x + n.substring(i);
        }
        return n + x;
    } else {
        for (int i = 1; i < n.length(); i++) {
            if (x < (n.charAt(i) - '0'))
                return n.substring(0, i) + x + n.substring(i);
        }
        return n + x;
    }
}

// 14
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 1)
        return strs[0];

    StringBuilder sb = new StringBuilder(strs[0]);
    // String s=new String(strs[0]);
    for (int i = 1; i < strs.length; i++) {
        String str = strs[i];
        int j = 0, k = 0;
        while (j < sb.length() && k < str.length()) {
            if (sb.charAt(j) != str.charAt(k))
                break;
            else {
                j++;
                k++;
            }
        }
        // s=s.substring(0,j);
        sb.delete(j, sb.length()); 
    }

    return sb.toString();
}

public String longestCommonPrefix2(String[] strs) {
    if (strs.length == 1)
        return strs[0];

    Arrays.sort(strs);  //['flower','flu','flight']  //sorted->['flight','flower','flu']

    String ans = "";

    String s1 = strs[0];
    String s2 = strs[strs.length - 1];

    int i = 0, j = 0;
    while (i < s1.length()) {
        if (s1.charAt(i) == s2.charAt(j)) {
            ans += s1.charAt(i);
            i++;
            j++;
        } else break;
    }
    return ans;
}

public static int factorial(int n) {
    if (n <= 1)
        return 1;
    else
        return n * factorial(n - 1);
}

public static void permutation(String str) {
    int n = str.length();
    int totalPermutation = factorial(n);
    for (int i = 0; i < totalPermutation; i++) {
        StringBuilder originalString = new StringBuilder(str);
        StringBuilder sb = new StringBuilder("");
        int m = n, temp = i;
        while (m > 0) {
            int quo = temp / m;
            int index = temp % m;
            sb.append(originalString.charAt(index));
            originalString.deleteCharAt(index);
            m--;
            temp = quo;
        }
        System.out.println(sb);
    }
}

public static String compression1(String str) {
    int n = str.length();
    String str1 = "";
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        while (j < n && str.charAt(i) == str.charAt(j))j++;
        if (j - i > 1) {
            str1 += str.charAt(i);
            str1 += j - i;  //in java chlega
        } else str1 += str.charAt(i);
        i = j - 1;
    }
    return str1;
}

public static String compression2(String str) {
    int n = str.length();
    String str1 = "";
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        while (j < n && str.charAt(i) == str.charAt(j))
            j++;
        str1 += str.charAt(i);

        i = j - 1;
    }
    return str1;
}

// 443
int compress(vector<char> &chars)
{
    if (chars.size() <= 1)
        return chars.size();
    int len = 0;
    for (int i = 0; i < chars.size(); i++)
    {
        int j = i + 1;
        while (j < chars.size() && chars[i] == chars[j])
            j++;
        chars[len++]=chars[i];
        if (j - i > 1)
        {
            string str = to_string(j - i);  //["a","b","b","b","b","b","b","b","b","b","b","b","b"]
            for (int k = 0; k < str.length(); k++)
                chars[len++] = str[k];
        }
        i = j - 1;
    }
    return len;
}

//925
bool isLongPressedName(string name, string typed) {
    int i=0,j=0;
    while(i<name.size())
    {
        char ch=name[i];
        int count_i=0;
        while(i<name.size() && name[i]==ch)
        {
            count_i++;
            i++;
        }
        
        int count_j=0;
        while(j<typed.size() && typed[j]==ch){
            count_j++;
            j++;
        }
        if(count_j<count_i)return false;
    }
    return j==typed.size()?true:false;  //"alex" "aaleexa"
}

// 38
public String countAndSay(int n) {
    if (n == 1)
        return "1";

    StringBuilder sb = new StringBuilder("1");
    for (int i = 2; i <= n; i++) {
        int si = 0, ei = 0;
        StringBuilder ans = new StringBuilder();
        while (ei < sb.length()) {
            char lc = sb.charAt(si);
            while (ei < sb.length() && sb.charAt(ei) == lc)ei++;
            ans.append(Integer.toString(ei - si) + lc); //ya alg alg line mein simple add 
            si = ei;
        }

        sb = ans;
    }
    return sb.toString();
}

// 3
int lengthOfLongestSubstring(string s) {
    if (s.length() <= 1)
    return s.length();
    int si = 0, ei = 0, len = 0, count=0, maxsi, maxei;  //default value 0 in maxsi
    vector<int> arr(128, 0);
    while (ei < s.length())
    {
        char ch=s[ei]; 
        if(arr[s[ei++]]++==1)
            count++;
        while(count>0)    //==1
        {
            if(arr[s[si++]]--==2)count--;
        }    

        if(ei - si > len)
        {
            len = ei - si;
            maxsi = si;
            maxei = ei;
        }
    }
    cout << s.substr(maxsi, len);  // [pos,pos+len) 
    return len;
}

// Longest substring with 2 distinct,change 2 with k for question k distinct(159,340)
// https://www.lintcode.com/problem/928
// https://www.lintcode.com/problem/386/
int lengthOfLongestSubstringTwoDistinct(string s)
{
    vector<int> arr(128, 0);
    int si = 0, ei = 0, len = 0, n = s.length(), distinctCount = 0, head = 0;
    while (ei < n)
    {
        if (arr[s[ei++]]++ == 0)
            distinctCount++;

        while (distinctCount > 2)
        {
            if (arr[s[si++]]-- == 1)
                distinctCount--;
        }

        // len=max(len,ei-si);
        if (len < ei - si)
        {
            len = ei - si;
            head = si;
        }
    }
    cout << s.substr(head, len) << endl;
    return len;
}

// 1876-good string if no repeated character
int countGoodSubstrings(string s)
{
    vector<int> arr(26);

    int si = 0, ei = 0, count = 0, repeat=0;
    while (ei < s.length())
    {
        int ch = s[ei] - 'a';
        if (arr[s[ei++] - 'a']++ == 1)
        repeat++;
        while(repeat>0)
        {
            if(arr[s[si++] - 'a']-- == 2)
            repeat--;
        }

        if (ei - si == 3)
        {
            arr[s[si++] - 'a']--;
            count++;
        }
    }
    return count;
}

int countGoodSubstrings(string s)
{
    if (s.length() < 3)
        return 0;
    int total = 0;
    for (int i = 0; i < s.length() - 2; i++)
    {
        if (s[i] != s[i + 1] && s[i + 1] != s[i + 2] && s[i] != s[i + 2])
            total++;
    }

    return total;
}

// leetcode 76
string minWindow(string s, string t)
{
    vector<int> arr(128, 0);
    for (int i = 0; i < t.length(); i++)
        arr[t[i]] += 1;
    int requirement = t.length(), si = 0, ei = 0, len = 1e8, head = 0;  
    while (ei < s.length())
    {
        if (arr[s[ei++]]-- > 0)
            requirement--;

        while (requirement == 0)
        {
            if (len >= ei - si)
            {
                len = ei - si;
                head = si;
            }

            if (arr[s[si++]]++ == 0)
                requirement++;
        }
    }
    return len == 1e8 ? "" : s.substr(head, len);
}

// https://www.geeksforgeeks.org/smallest-window-contains-characters-string/
// target string jo bnegi vo bnegi distinct character of given string and we have to find a smallest string jisme vo sab distinct ho.
string smallestWindow(string s)
{
    vector<int> arr(128, 0);
    for (int i = 0; i < s.length(); i++)
        arr[s[i]] = 1;
    int requirement = 0, si = 0, ei = 0, len = 1e8, head = 0;
    for (int ele : arr)
        requirement += ele;
    while (ei < s.length())
    {
        if (arr[s[ei++]]-- == 1)
            requirement--;

        while (requirement == 0)
        {
            if (len >= ei - si)
            {
                len = ei - si;
                head = si;
            }

            if (arr[s[si++]]++ == 0)
                requirement++;
        }
    }
    return len == 1e8 ? "" : s.substr(head, len);
}

// leetcode 1456
int isVowel(char a)
{
    if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u')
        return 1;
    return 0;
}

int maxVowels(string s, int k)
{
    int vowelCount = 0, maxVowelCount = 0, si = 0, ei = 0, n = s.length();
    while (ei < n)
    {
        if (isVowel(s[ei++]))
            vowelCount++;

        if (ei - si == k)
        {
            maxVowelCount = max(maxVowelCount, vowelCount);
            if (isVowel(s[si++]))
                vowelCount--;
        }
    }
    return maxVowelCount;
}

int maxVowels2(string s, int k)
{
    queue<char> que; 
    int vc = 0, mvc = 0, i = 0;
    while (i < k)
    {
        if (isVowel(s[i]))
            vc++;
        que.push(s[i++]);
    }
    mvc = vc;
    while (i < s.length())
    {
        char ch = que.front();
        que.pop();
        if (isVowel(ch))
            vc--;
        if (isVowel(s[i]))
            vc++;
        que.push(s[i++]);
        mvc = max(mvc, vc);
    }
    return mvc;
}

public String removeConsecutiveCharacter(String A, int B) { // ib

    int si = 0, ei = 0;
    StringBuilder sb = new StringBuilder();
    while (ei < A.length()) {
        char lc = A.charAt(si);
        while (ei < A.length() && A.charAt(ei) == lc)
            ei++;

        if (ei - si == B) {
            si = ei;
        } else {
            int noOftime = ei - si;
            while (noOftime-- > 0) {
                sb.append(lc);
            }
            si = ei;
        }
    }

    return sb.toString();  
}

//1869
bool checkZeroOnes(string s)
{
    int si = 0, ei = 0;
    int zeroMl = 0, oneMl = 0;
    while (ei < s.length())
    {
        while (ei < s.length() && s[ei] == '1')
            ei++;
        oneMl = max(oneMl, ei - si);

        si = ei;
        while (ei < s.length() && s[ei] == '0')
            ei++;
        zeroMl = max(zeroMl, ei - si);
        si = ei;
    }

    return oneMl > zeroMl;
}

// 58
public int lengthOfLastWord(String s) {
    int sz = s.length();

    int lastSpace = -1;
    for (int i = 0; i < sz; i++) {
        if (s.charAt(i) == ' ')
            lastSpace = i;
    }

    int length = 0;
    for (int i = lastSpace + 1; i < sz; i++)
        length++;

    if (length == 0) {
        for (int i = lastSpace - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ')
                continue;
            else {
                while (i >= 0 && s.charAt(i--) != ' ')
                    length++;
                break;
            }
        }
    }
    return length;
}

public int lengthOfLastWord_(String s) {
    int tail = s.length() - 1;
    int len = 0;
    while (tail >= 0 && s.charAt(tail) == ' ')
        tail--;
    while (tail >= 0 && s.charAt(tail) != ' ') {
        len++;
        tail--;
    }

    return len;
}

// 65
public boolean ValidNumber(String s) {
    boolean pointSeen = false;
    boolean numSeen = false;
    boolean eSeen = false;

    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            numSeen = true;
        } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (i != 0 && s.charAt(i - 1) != 'e')
                return false;
        } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
            if (eSeen || !numSeen)
                return false;
            eSeen = true;
            numSeen = false;
        } else if (s.charAt(i) == '.') {
            if (pointSeen || eSeen)
                return false;
            pointSeen = true;
        } else return false; // for any other char other than above
    }
    return numSeen;
}

 // 468
public String validIPAddress(String IP) {
    String[] arrOfStr1 = IP.split("\\.", -1);
    String[] arrOfStr2 = IP.split("\\:", -1);  //-1 se pattern applied as many time as psble,0 se trailing empty string discarded

    if (arrOfStr1.length == 4) { 
        for (int i = 0; i < arrOfStr1.length; i++) {
            String str = arrOfStr1[i];
            int n = arrOfStr1[i].length();  //if n=1 then '0' is allowed
            if (n == 1 && (!(str.charAt(0) >= '0' && str.charAt(0) <= '9'))) return "Neither";
            else if (n >= 2 && n <= 3) {
                if (str.charAt(0) == '0')return "Neither";
                int num = str.charAt(0) - '0';
                for (int j = 1; j < n; j++) {
                    if (!(str.charAt(j) >= '0' && str.charAt(j) <= '9'))
                        return "Neither";
                    num = num * 10 + (str.charAt(j) - '0');
                }
                if (num >= 256)return "Neither";
            } else if (n > 3 || n == 0)return "Neither"; // 1.1.1.
        }
        return "IPv4";
    } 
    else if (arrOfStr2.length == 8) {
        for (int i = 0; i < arrOfStr2.length; i++) {
            String s = arrOfStr2[i];
            int n = arrOfStr2[i].length();
            if (n >= 1 && n <= 4) {
                for (int j = 0; j < n; j++) {
                    if (!((s.charAt(j) >= '0' && s.charAt(j) <= '9') || (s.charAt(j) >= 'a' && s.charAt(j) <= 'f')
                            || (s.charAt(j) >= 'A' && s.charAt(j) <= 'F')))
                        return "Neither";
                }
            } else if (n > 4 || n == 0)return "Neither";
        }
        return "IPv6";
    } else return "Neither";
}

//1002
public List<String> commonChars(String[] A) {

    List<String> result = new ArrayList<>();
    int minArray[] = new int[26];
    String str = A[0];

    for (int i = 0; i < str.length(); i++) {
        minArray[str.charAt(i) - 'a']++;
    }

    for (int i = 1; i < A.length; i++) {
        str = A[i];
        int freqArr[] = new int[26];
        for (int j = 0; j < str.length(); j++) {
            freqArr[str.charAt(j) - 'a']++;
        }

        for (int j = 0; j < 26; j++) {
            minArray[j] = Math.min(minArray[j], freqArr[j]);
        }
    }

    for (int i = 0; i < 26; i++) {
        int val = minArray[i];
        if (val > 0) {
            for (int j = 0; j < val; j++)
                result.add((char) (i + 'a') + "");
        }
    }
    return result;
}

//151
public String reverseWords(String s) {
    StringBuilder ans = new StringBuilder();
    int i = s.length() - 1;
    while (i >= 0) {
        StringBuilder word = new StringBuilder();
        while (i >= 0 && s.charAt(i) == ' ')
            i--;
        while (i >= 0 && s.charAt(i) != ' ') {
            word.insert(0, s.charAt(i));
            i--;
        }
        word.append(' ');
        ans.append(word);
    }
    ans.deleteCharAt(ans.length() - 1);
    if (ans.charAt(ans.length() - 1) == ' ') 
        ans.deleteCharAt(ans.length() - 1); // at max yahan pr do space rhengi tou pehle meine loop chalay tha wo bekar ...do space tab jab shuruat mein bhot sarii space ho..
    return ans.toString();
}

//151
string reverseWords(string s) {
    reverse(s.begin(),s.end());  //end index not included
    int nextLetter=0;
    for(int i=0;i<s.length();i++)
    {
        if(s[i]!=' ')
        {
            int j=i;
            while(j<s.length() && s[j]!=' ')j++;
            reverse(s.begin()+nextLetter,s.begin()+j);
            nextLetter+=j-i+1;
            i=j;
        }
    }
    s.erase(s.begin()+nextLetter-1,s.end());
    return s;
}

//8
int myAtoi(string s)
{
    long number = 0;
    int size = s.length(), sign = 0, i=0;
    while(i < s.length())
    {
        if (s[i] == ' ')  //ignore leading whitespace
        {
            i++;
            continue;
        }
        else if (s[i] == '-' || s[i] == '+')
        {
            if (sign != 0)
                break; //+-12 answer 0
            sign = s[i] == '-' ? -1 : 1;
            if (i + 1 < s.length() && !(s[i + 1] >= '0' && s[i + 1] <= '9'))
                break; //"  +  413" ans->0
            i++;
        }
        else if(s[i]>='0' && s[i]<='9')
        {
            while (i < s.length() && (s[i] >= '0' && s[i] <= '9'))
            {
                number = number * 10 + (s[i] - '0');
                if ((sign == -1 && number < INT_MIN) || (number > INT_MAX))
                {
                    if (sign == -1)
                        number = INT_MIN;
                    else
                        number = INT_MAX;
                    break;
                }
                i++;
            }
            break;
        }
        else break;
    }
    if (sign == 0)
        sign = 1; //42
    return number * sign;
}

//1312
int minInsertions(string s)
{
    int n = s.length();
    vector<vector<int>> dp(n, vector<int>(n, 0)); //initialse with -1 chalta hai recursion mein

    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; i++, j++)
        {
            if(i==j)
            {
                dp[i][j]=0;
                continue;
            }

            if (s[i] == s[j])
                dp[i][j] = dp[i + 1][j - 1];
            else
                dp[i][j] = min(dp[i][j - 1], dp[i + 1][j]) + 1;
        }
    }

    return dp[0][n - 1];
}

//======================
//https://practice.geeksforgeeks.org/problems/longest-prefix-suffix2527/1#
int lps(string s)
{
    int i = 1, len = 0;
    vector<int> LPS(s.length());
    while (i < s.length())
    {
        if (s[i] == s[len])
        {
            len++;
            LPS[i] = len;
            i++;
        }
        else
        {
            if (len > 0)
                len = LPS[len - 1];
            else
            {
                LPS[i] = 0;
                i++;
            }
        }
    }
    return len;
}

//1392
int lps(string s)
{
    vector<int>ans(s.length());
    int i=1,len=0;
    while(i<s.length())
    {
        if(s[len]==s[i])
        {
            len++;
            ans[i]=len;
            i++;
        }
        else{
            if(len>0)
                len=ans[len-1];
            else{
                ans[i]=0;
                i++;
            }
        }
    }
    return ans[s.length()-1];
}

string longestPrefix(string s) {
    return s.substr(0,lps(s));
}

int minimumAppendForPalindrome(string A)
{
    // 1 <= string len <= 10^5 so memory limit exceed agar dp bnayi then check kra function se ki palindrome hai ya ni aaage ki string(in both case iterarive/recursive mle)

    //if without dp we check for each character that palindromic/not then worst case n^2
    string rev = A;
    reverse(rev.begin(), rev.end());
    return A.length() - lps(rev + "$" + A);  //agr pata lgjaaye piche se string kitni palindromic hai tou length-lps[length()-1] is answer;
}

// 28
int strStr(string str, string find) {
    if(find.length()==0)return 0;
    vector<int>flps(find.length());
    
    int i=0,j=0;
    lps(find,flps);
    while(i<str.length())
    {
        if(str[i]==find[j])
        {
            i++;
            j++;
        }
        else if(j==find.length())break; //c++ aage wale address krleta hai even if dont belong
        else {
            if(j>0)
                j=flps[j-1];
            else
                i++;
        }
    }
    return j==find.length()?i-j:-1;
}

//686
public int repeatedStringMatch(String a, String b) {
    StringBuilder res=new StringBuilder(a);
    int nt=(int)Math.ceil(b.length()/a.length())+1;
    int count=1;
    while(nt-->0)
    {
        if(res.indexOf(b)!=-1)
            return count;
        res.append(a);
        count++;
    }
    if(res.indexOf(b)!=-1)
            return count;
    return -1;
}

//459
vector<int>lps(string s)
{
    vector<int>ans(s.length());
    int len=0,i=1;
    while(i<s.length())
    {
        if(s[i]==s[len])
        {
            len++;
            ans[i]=len;
            i++;
        }
        else{
            if(len>0)len=ans[len-1];
            else{
                ans[i]=0;
                i++;
            }
        }
    }
    return ans;
}

bool repeatedSubstringPattern(string s) {
    vector<int>res=lps(s);
    
    // for(int i=0;i<res.size()/2;i++)
    // {
    //     int j=i,jump=i+1,count=1;
    //     while(j<res.size())
    //     {
    //         if(j+jump<s.length() && res[j+jump]==count*jump)
    //         {
    //             j=j+jump;
    //             count++;
    //         }
    //         else break;
    //     }
    //     if(j==res.size()-1)return true;
    // }
    // return false;
    
    int n=s.length();
    int patlen=n-res[n-1];
    if(res[n-1]>0 && n%patlen==0)return true;
    return false;
}

//1332
int removePalindromeSub(string s) {
    int j=s.length()-1,i=0;
    
    while(i<j)
    {
        if(s[i]==s[j])
        {
            i++;j--;
        }
        else return 2;
    }
    return 1;
}

//1658
int minOperations(vector<int>& nums, int x) {
        
    unordered_map<int,int>map;
    int sum=0,target,len=-1;
    for(int ele:nums)
        sum+=ele;
    target=sum-x;
    
    if(target<0)return len;
    if(target==0)return nums.size();
    map[0]=-1,sum=0;
    for(int i=0;i<nums.size();i++)
    {
        sum+=nums[i];
        if(map.find(sum-target)!=map.end())
            len=max(len,i-map[sum-target]);
        map[sum]=i;
    }
    
    return len==-1?-1:nums.size()-len; 
}

