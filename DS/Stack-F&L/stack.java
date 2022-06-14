import java.util.*;

    // Stack to Queue(remove/add efficient)
    // Queue to Stack(remove/add efficient)
    // TwoStack                                               {full condn same}
    // Stack with increment operation                         (list as stack)
    // Stack(Normal and Dynamic)
    // Queue(Normal and Dynamic)                        //make,copy,change ref and variable
    // Minstack(2){1-initially ms 1e9}
    // Reverse first k queue element

    // Evaluate RPN
    // Infix Evaluation(number single digit(w/o ( ) ) or multiple digit)
    // Infix conversion to prefix postfix              //stack types
    // Postfix/prefix eval and conversion   //no ()

    // Balanced bracket(2)
    // Minimum insertion to balance a par string(2)
    // Min Bracket reversal to make exp balanced
    // Minimum remove to make valid string
    // Redundant braces *
    // Score of Parentheses
    // Longest valid Parentheses *
    // .Remove outer Parentheses(2)
    // Reverse Paren
    // decode string  *
    // Min insertion[ ()) ]

    // NGOR(3),NSOR(2),NSOL(2),NGOL(2)
    // NGR for arr1 using arr2
    // Daily Temperatures
    // Stock Span(2 varin)
    // Maximum abs diff
    // NGOR Circular
    // No of valid subarray  //nsr
    // .Trapping rain water(3) //ngr
    // .Largest Rectangle Area(2)  //nsr
    // .Maximal Rectangle 
    // Validate stack Sequence(2)

    // Find Celebrity
    // Smallest Number following pattern  
    // Asteroid Collision *
    // Merge overlapping interval
    // Remove all adjacent duplicate(2)
    // Make string great
    // Backspace compare(2)
    // .First non repeating char in stream
    // .Remove k digit to make small number //1112 //10 k=2 //903 k=1 *
    // .calculate *
    // .simplify path *
    // Remove duplicate letters to make smallest in lexographical order
    //Build array by stack oprn
    // Remove duplicates size k

public class stack {

// Adapter Set
// 232
class MyQueue {
    Stack<Integer> mainS;
    Stack<Integer> helperS;

    public MyQueue() {
        mainS = new Stack<>();
        helperS = new Stack<>();
    }

    public void push(int val) {
        while (mainS.size() > 0)
            helperS.add(mainS.pop());

        mainS.add(val);

        while (helperS.size() > 0)
            mainS.add(helperS.pop());
    }

    public int pop() {
        return mainS.pop();
    }

    public int peek() {
        return mainS.peek();
    }

    public boolean empty() {
        if (mainS.size() == 0)
            return true;
        return false;
    }
}

public static class StackToQueueAdapter {
    Stack<Integer> mainS;
    Stack<Integer> helperS;

    public StackToQueueAdapter() {
        mainS = new Stack<>();
        helperS = new Stack<>();
    }

    int size() {
        return mainS.size();
    }

    void add(int val) {
        while (mainS.size() > 0)
            helperS.add(mainS.pop());

        mainS.add(val);

        while (helperS.size() > 0)
            mainS.add(helperS.pop());
    }

    int remove() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        return mainS.pop();
    }

    int peek() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        }
        return mainS.peek();
    }

    int remove2() { // push efficient
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            while (mainS.size() != 1)
                helperS.push(mainS.pop());

            int val = mainS.pop();

            while (helperS.size() != 0)
                mainS.push(helperS.pop());

            return val;
        }
    }

    int peek2() { // push efficient
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            while (mainS.size() != 1)
                helperS.push(mainS.pop());

            int val = mainS.peek();
            while (helperS.size() != 0)
                mainS.push(helperS.pop());

            return val;
        }
    }
}

// 225
class MyStack {
    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    /** Initialize your data structure here. */
    public MyStack() {
        mainQ = new ArrayDeque<>();
        helperQ = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (mainQ.size() > 0)
            helperQ.add(mainQ.remove());

        mainQ.add(x);

        while (helperQ.size() > 0)
            mainQ.add(helperQ.remove());
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return mainQ.remove();
    }

    /** Get the top element. */
    public int top() {
        return mainQ.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return mainQ.size() == 0;
    }
}

public static class QueueToStackAdapter {
    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    public QueueToStackAdapter() {
        mainQ = new ArrayDeque<>();
        helperQ = new ArrayDeque<>();
    }

    int size() {
        return mainQ.size();
    }

    void push(int val) { // pop efficient bnane kelie
        while (mainQ.size() > 0)
            helperQ.add(mainQ.remove());

        mainQ.add(val);

        while (helperQ.size() > 0)
            mainQ.add(helperQ.remove());
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return mainQ.remove();
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return mainQ.peek();
    }

    int pop2() { // push efficient
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        while (mainQ.size() != 1)
            helperQ.add(mainQ.remove());

        int val = mainQ.remove();

        while (helperQ.size() != 0)
            mainQ.add(helperQ.remove());

        return val;
    }

    int top2() { // push efficient
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        while (mainQ.size() != 1)
            helperQ.add(mainQ.remove());

        int val = mainQ.remove();   //bcz queue mein ek trf se addn ek trf se deln
        helperQ.add(val);

        while (helperQ.size() != 0)
            mainQ.add(helperQ.remove());

        return val;
    }
}

public static class TwoStack {
    int[] data;
    int tos1;
    int tos2;

    public TwoStack(int cap) {
        data = new int[cap];
        tos1 = -1;
        tos2 = data.length;
    }

    int size1() {
        return tos1 + 1;
    }

    int size2() {
        return data.length - tos2;
    }

    void push1(int val) {
        if (tos1 + 1 == tos2) {
            System.out.println("Stack overflow");
        } else {
            tos1++;
            data[tos1] = val;
        }
    }

    void push2(int val) {
        if (tos1 + 1 == tos2) {
            System.out.println("Stack overflow");
        } else {
            tos2--;
            data[tos2] = val;
        }
    }

    int pop1() {
        if (size1() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos1--];
        }
    }

    int pop2() {
        if (size2() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos2++];
        }
    }

    int top1() {
        if (size1() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else
            return data[tos1];
    }

    int top2() {
        if (size2() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else
            return data[tos2];
    }
}

// 1381-design stack with increment oprn
class CustomStack_ {

    int maxSize = 0;
    List<Integer> st;

    public CustomStack_(int maxSize) {
        st = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (st.size() < maxSize) {
            st.add(x);
        }
    }

    public int pop() {
        if (st.size() == 0)
            return -1;
        return st.remove(st.size() - 1);
    }

    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, st.size()); i++) {
            st.set(i, st.get(i) + val); 
        }
    }
}

public static class CustomStack {
    int[] data;
    int tos;

    public CustomStack(int cap) {
        data = new int[cap];
        tos = -1;
    }

    int size() {
        return tos + 1;
    }

    void display() {
        if (size() == 0) {
            System.out.println();
            return;
        } else {
            int top = tos;
            while (top != -1) {
                System.out.print(data[top--] + " ");
            }
            System.out.println();
        }
    }

    void push(int val) {
        // if(size()==data.length)
        // {
        // System.out.println("Stack overflow");
        // }
        if (size() == data.length) // dynamic stack
        {
            int[] newData = new int[data.length * 2]; 
            tos = -1;
            for (int i = 0; i < data.length; i++) {
                tos++;
                newData[tos] = data[i];
            }
            tos++;
            newData[tos] = val;
            data = newData;
        } else {
            tos++;
            data[tos] = val;
        }
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos--];
        }
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        } else {
            return data[tos];
        }
    }
}

public static class CustomQueue {
    int[] data;
    int front;
    int size;

    public CustomQueue(int cap) {
        data = new int[cap];
        front = 0;
        size = 0;
    }

    int size() {
        return size;
    }

    void display() {
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % data.length;
            System.out.print(data[idx] + " ");
        }
        System.out.println();
    }

    void add(int val) {
        if (size() == data.length) {
            // System.out.println("Queue overflow");
            int newdata[] = new int[2 * data.length];
            int newsize = 0;
            for (int i = 0; i < size; i++) {
                int idx = (front + i) % data.length;
                newdata[newsize++] = data[idx];
            }
            newdata[newsize++] = val;  
            data = newdata;
            size = newsize;
            front = 0;
        } else {
            int rear = (front + size) % data.length;
            data[rear] = val;
            size++;
        }
    }

    int remove() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            int val = data[front];
            front = (front + 1) % data.length;
            size--;
            return val;
        }
    }

    int peek() {
        if (size() == 0) {
            System.out.println("Queue underflow");
            return -1;
        } else {
            return data[front];
        }
    }
}

public static class MinStack {
    Stack<Integer> allData;
    Stack<Integer> minData;

    public MinStack() {
        allData = new Stack<>();
        minData = new Stack<>();
        minData.push((int) 1e9);   //use in push if dont want to use then in push check if minstack size>0 before comparing and also agr minstack size 0 then push
    }

    int size() {
        return allData.size();
    }

    void push(int val) {
        if (val <= minData.peek()) { // = pr bhi daalenge maanlo push 2,2 then agr pop krenge and minstack se bhi htaenge aur agr ek hi baar daala hoga tou neeche hoga (int)1e9 stack underflow though stack has one more 2.
            minData.push(val);
        }

        allData.push(val);
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        int val = allData.pop();
        if (val == minData.peek())
            minData.pop();
        return val;
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return allData.peek();
    }

    int min() {
        if (minData.peek() == (int) 1e9) {  //size()==0
            System.out.println("Stack underflow");
            return -1;
        }
        return minData.peek();
    }
}

public static class MinStack2 {
    Stack<Integer> data;
    int min;

    public MinStack2() {
        data = new Stack<>();
    }

    int size() {
        return data.size();
    }

    void push(int val) {
        if (size() == 0) {
            min = val;
            data.push(val);
        } else if (val < min) {
            data.push(val + val - min); // phle push then min update
            min = val;
        } else
            data.push(val);
    }

    int pop() {
        if (size() == 0) {
            System.out.println("Stack Underflow");
            return -1;
        } else if (data.peek() >= min) {
            return data.pop();
        } else {
            int min_YahanBadlaTha = min;
            min = 2 * min - data.pop();  //2*min-(2*val-min)
            return min_YahanBadlaTha;
        }
    }

    int top() {
        if (size() == 0) {
            System.out.println("Stack Underflow");
            return -1;
        } else if (data.peek() >= min) {
            return data.peek();
        } else {
            return min;
        }
    }

    int min() {
        if (size() == 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return min;
    }
}

//155
class MinStack
{
    public:
        stack<long> st; //-2^31 <= val <= 2^31 - 1
        long minEle = 0;
        MinStack()
        {
        }

        void push(int val)
        {
            if (st.size() == 0)
            {
                st.push(val);
                minEle = val;
                return;
            }

            if (val >= minEle)
            {
                st.push(val);
            }
            else
            {
                //why cant store val-m
                //["MinStack","push","push","push","getMin","pop","top","getMin"]
                //[[],[-2],[0],[-3],[],[],[],[]]
                st.push((val - minEle) + val);
                minEle = val;
            }
        }

        void pop()
        {
            if (st.top() < minEle)
            {
                minEle = 2*minEle - st.top();
            }

            st.pop();
        }

        int top()
        {
            if (st.top() < minEle)
                return (int)minEle;

            return (int)st.top();
        }

        int getMin()
        {
            return (int)minEle;
        }
};

//https://practice.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1#
queue<int> reverseFirstKofQueue(queue<int> que, int k)
{
    stack<int> st;
    queue<int> helper;
    int size = que.size();
    for (int i = 0; i < size; i++)
    {
        if (i < k)
        {
            st.push(que.front());
            que.pop();
        }
        else
        {
            helper.push(que.front());
            que.pop();
        }
    }

    for (int i = 0; i < k; i++)
    {
        que.push(st.top());
        st.pop();
    }

    while (helper.size() != 0)
    {
        que.push(helper.front());
        helper.pop();
    }

    return que;
}

//150
void calculate(stack<int> &st, string str)
{
    int s2 = st.top();
    st.pop();
    int s1 = st.top();
    st.pop();

    if (str == "+")
        st.push(s1 + s2);
    else if (str == "-")
        st.push(s1 - s2);
    else if (str == "*")
        st.push(s1 * s2);
    else
        st.push(s1 / s2);
}

int evaluateRPN(vector<string> &tokens)
{
    stack<int> st;
    for (int i = 0; i < tokens.size(); i++)
    {
        string str = tokens[i];
        if (str == "+" || str == "-" || str == "*" || str == "/")
            calculate(st, str);
        else
        {
            int num = stoi(str); //string to integer(stoi) 
            st.push(num);
        }
    }
    return st.top();
}

public static int precedence(char ch) {
    if (ch == '+')
        return 1;
    else if (ch == '-')
        return 1;
    else if (ch == '/')
        return 2;
    else if (ch == '*')
        return 2;

    return -1;
}

public static int operation(int o1, int o2, char optor) {
    if (optor == '+')
        return o1 + o2;
    else if (optor == '-')
        return o1 - o2;
    else if (optor == '*')
        return o1 * o2;
    else if (optor == '/')
        return o1 / o2;

    return 0;
}

public static void fun(Stack<Integer> operand, Stack<Character> operator) {
    int val2 = operand.pop();
    int val1 = operand.pop();
    char optor = operator.pop();
    int res = operation(val1, val2, optor);
    operand.push(res);
}

public static void infixEvaluation(String str) {
    Stack<Integer> operand = new Stack<>();
    Stack<Character> operator = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == '(') {
            operator.push(ch);
        } else if (ch == ')') {
            //operator.size() > 0 not rqd if string bal
            while (operator.size() > 0 && operator.peek() != '(') {
                fun(operand, operator);
            }
            operator.pop();
        }
        else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            while (operator.size() > 0 && operator.peek() != '(' && precedence(operator.peek()) >= precedence(ch)) {
                fun(operand, operator);
            }
            operator.push(ch);
        }
        else if (Character.isDigit(ch)) { // space bar ki wjh se digit check krna pdega
            operand.push(ch - '0');
        }
    }

    while (operator.size() != 0) {
        fun(operand, operator);
    }

    System.out.println(operand.peek());
}

// 227
//Infix Evaluation-space ho tou i++ ho hojaayga but ek digit se bda number then while loop make num and reinitialise num=0
int precedence(char ch)
{
    if (ch == '+')
        return 1;
    else if (ch == '-')
        return 1;
    else if (ch == '/')
        return 2;
    else if (ch == '*')
        return 2;

    return -1;
}

int operation(int o1, int o2, char optor)
{
    if (optor == '+')
        return o1 + o2;
    else if (optor == '-')
        return o1 - o2;
    else if (optor == '*')
        return o1 * o2;
    else if (optor == '/')
        return o1 / o2;

    return 0;
}

void fun(stack<int> &operand, stack<char> &operators)
{
    int val2 = operand.top();
    operand.pop();
    int val1 = operand.top();
    operand.pop();
    char optor = operators.top();
    operators.pop();
    int res = operation(val1, val2, optor);
    operand.push(res);
}

int calculate(string s)
{
    stack<int> operand;
    stack<char> operators;
    int num = 0, i = 0;

    while (i < s.length())
    {

        if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/')
        {
            while (operators.size() > 0 && precedence(operators.top()) >= precedence(s[i]))
                fun(operand, operators);
            operators.push(s[i]);
            i++;
        }

        else if (s[i] != ' ')
        {
            while (i < s.length() && s[i] >= '0' && s[i] <= '9')
            {
                num = num * 10 + (s[i] - '0');
                i++;
            }
            operand.push(num);
            num = 0;
        }
        else
            i++;
    }

    while (operators.size() != 0)
        fun(operand, operators);

    return operand.top();
}

//Infix Conversion
public static void fun2(Stack<String> prefix, Stack<Character> operator, Stack<String> postfix) {
    String val2 = prefix.pop();
    String val1 = prefix.pop();
    char oprtor = operator.pop();
    String pre = oprtor + val1 + val2;

    String val3 = postfix.pop();
    String val4 = postfix.pop();
    String post = val4 + val3 + oprtor;

    prefix.push(pre);
    postfix.push(post);
}

public static void prefixPostfix(String str) {
    Stack<String> prefix = new Stack<>();
    Stack<Character> operator = new Stack<>();
    Stack<String> postfix = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);

        if (ch == '(')
            operator.push(ch);
        else if (ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z') {
            prefix.push(Character.toString(ch));  
            postfix.push(Character.toString(ch));
        } else if (ch == ')') {
            while (operator.size() > 0 && operator.peek() != '(') {
                fun2(prefix, operator, postfix);
            }
            operator.pop();
        } else if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
            while (operator.size() > 0 && operator.peek() != '(' && precedence(operator.peek()) >= precedence(ch)) {
                fun2(prefix, operator, postfix);
            }
            operator.push(ch);
        }
    }
    while (operator.size() != 0) {
        fun2(prefix, operator, postfix);
    }

    System.out.println(postfix.peek());
    System.out.println(prefix.peek());
}

public static void postfixEvalAndConv(String str) {
    Stack<Integer> eval = new Stack<>();
    Stack<String> infix = new Stack<>();
    Stack<String> prefix = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            int val2 = eval.pop();
            int val1 = eval.pop();
            eval.push(operation(val1, val2, ch));

            String is2 = infix.pop();
            String is1 = infix.pop();
            String ires = "(" + is1 + ch + is2 + ")";
            infix.push(ires);

            String ps2 = prefix.pop();
            String ps1 = prefix.pop();
            String pres = ch + ps1 + ps2;
            prefix.push(pres);
        } 
        else {
            eval.push(ch - '0');
            infix.push(ch + "");
            prefix.push(ch + "");
        }
    }

        System.out.println(eval.pop());
        System.out.println(infix.pop());
        System.out.println(prefix.pop());
}

public static void prefixConversionEval(String str) {
    Stack<Integer> eval = new Stack<>();
    Stack<String> infix = new Stack<>();
    Stack<String> postfix = new Stack<>();

    for (int i = str.length() - 1; i >= 0; i--) {
        char ch = str.charAt(i);
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            int val2 = eval.pop();
            int val1 = eval.pop();
            eval.push(operation(val2, val1, ch));

            String is2 = infix.pop();
            String is1 = infix.pop();
            String ires = "(" + is2 + ch + is1 + ")";
            infix.push(ires);

            String ps2 = postfix.pop();
            String ps1 = postfix.pop();
            String pres = ps2 + ps1 + ch;
            postfix.push(pres);
        } else {
            eval.push(ch - '0');
            infix.push(ch + "");
            postfix.push(ch + "");
        }
    }

    System.out.println(eval.pop());
    System.out.println(infix.pop());
    System.out.println(postfix.pop());
}

//20-2(aur ek likhne ka alg tarikka)
bool isValid(string s)
{
    stack<char> st;
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '{' || s[i] == '[' || s[i] == '(')
        {
            st.push(s[i]);
        }
        else
        {
            if (st.size() == 0)
                return false;
            else if (s[i] == '}' && st.top() != '{')
                return false;
            else if (s[i] == ']' && st.top() != '[')
                return false;
            else if (s[i] == ')' && st.top() != '(')
                return false;
            else
                st.pop();
        }
    }
    if (st.size() != 0)
        return false;
    return true;
}

public static boolean handleClosing(Stack<Character> st, char ch) // sumeet sir
{
    if (st.size() == 0)
        return false;
    else if (st.peek() != ch)
        return false;
    else {
        st.pop();
        return true;
    }
}

public static void balancedBracket2(String str) {
    Stack<Character> st = new Stack<>();
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        // if (ch == '{' || ch == '[' || ch == '(')
        // st.push(ch);
        // [->91 ]->93 {->123 }->125 (->40 )->41
        // else if (ch == '}' || ch == ']' || ch == ')') {
        //if (st.size() != 0 && ((ch - st.peek() == 1) || (ch -st.peek() == 2))) {
        // st.pop();
        // } else
        // return false;
        // }
        if (ch == '(' || ch == '{' || ch == '[')
            st.push(ch);
        else if (ch == ')') {
            boolean val = handleClosing(st, '(');
            if (val == false) {
                System.out.println("false");
                return;
            }
        } else if (ch == '}') {
            boolean val = handleClosing(st, '{');
            if (val == false) {
                System.out.println("false");
                return;
            }
        } else if (ch == ']') {
            boolean val = handleClosing(st, '[');
            if (val == false) {
                System.out.println("false");
                return;
            }
        }
    }

    if (st.size() == 0)
        System.out.println("true");
    else
        System.out.println("false");
}

// 921
public int minAddToMakeValid(String str) {
    Stack<Character> st = new Stack<>();
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == '{' || ch == '[' || ch == '(')
            st.push(ch);
        else if (ch == '}' || ch == ']' || ch == ')') {
            if (st.size() != 0 && ((ch - st.peek() == 1) || ch - st.peek() == 2)) {
                st.pop();
            } else
                count += 1;  //ya fir stack mein push and last mein return st size
        }
    }
    if (st.size() != 0)
        count += st.size();
    return count;
}

//https://practice.geeksforgeeks.org/problems/count-the-reversals0401/1
int countRev(string s)
{
    stack<char> st;
    int unBalancedClosing = 0, unBalancedOpening = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '{')
            st.push(s[i]);
        else if (s[i] == '}')
        {
            if (st.size() == 0)
                unBalancedClosing++;
            else if (st.top() == '{')
                st.pop();
        }
    }

    unBalancedOpening = st.size();

    int r1 = unBalancedClosing / 2;
    int re1 = unBalancedClosing % 2; //re:remaining

    int r2 = unBalancedOpening / 2;
    int re2 = unBalancedOpening % 2;

    if (re1 == re2)
        return r1 + r2 + re1 + re2;
    return -1;
}

//1249
string minRemoveValidString(string s)
{
    string res = "";
    stack<int> st; //stack char ki bnadi islie rte
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];

        if (ch == '(')
            st.push(i);

        else if (ch == ')')
        {
            if (st.size() != 0)
                st.pop();
            else
                s[i] = '#';
        }
    }

    while (st.size() != 0)
    {
        s[st.top()] = '#';
        st.pop();
    }

    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] != '#')
            res += s[i];
    }

    return res;
}

//ib and pep(duplicate bracket)
// https://www.interviewbit.com/problems/redundant-braces/
int redundantBraces(string A)
{
    stack<char> st;
    for (int i = 0; i < A.length(); i++)
    {
        if (A[i] == '(' || A[i] == '+' || A[i] == '*' || A[i] == '/' || A[i] == '-')
            //if (ch != ')')  ye operand bhi daaldega  
            st.push(A[i]);
        else if (A[i] == ')')
        {
            if (st.top() == '(')
                return 1;

            while (st.top() != '(')
                st.pop();
            st.pop();
        }
    }
    return 0;
}

//856
int scoreOfParentheses(string s)
{
    stack<char> st;
    int i = 0, score = 0;
    while (i < s.length())
    {
        if (s[i] == '(')
        {
            st.push(s[i]);
            i++;
        }
        else
        {
            st.pop();
            score += (int)(pow(2, st.size())); //acc to gfg pow fn do not work sometime well on integers
            while (++i < s.length() && s[i] == ')')
                st.pop();
        }
    }
    return score;
}

//32
//https://practice.geeksforgeeks.org/problems/valid-substring0624/1
int longestValidParentheses(string s)
{
    stack<int> st;
    st.push(-1);
    int maxLen = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (st.top() != -1 && s[st.top()] == '(' && s[i] == ')')
        {
            st.pop();
            maxLen = max(maxLen, i - st.top());
        }
        else
            st.push(i);
    }

    return maxLen;
}

//1190
string reverseParentheses(string s)
{
    int i = 0;
    stack<char> st;
    string ans = "", inBracket = "";

    for (; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch == '(')
            st.push(ch);
        else if (ch >= 97 && ch <= 122)
        {
            if (st.size() == 0)
                ans += ch;
            else st.push(ch);
        }
        else
        {
            while (st.top() != '(')
            {
                inBracket += st.top();
                st.pop();
            }
            st.pop();
            if (st.size() == 0)
                ans += inBracket;
            else
            {
                for (int j = 0; j < inBracket.length(); j++)
                    st.push(inBracket[j]);
            }
            inBracket = "";
        }
    }

    return ans;
}

//1021
string removeOuterParentheses(string s)
{
    stack<char> st;
    string ans = "";
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '(')
        {
            st.push(s[i]);
            if (st.size() > 1)
                ans += s[i];
        }
        else
        {
            if (st.size() > 1)
                ans += s[i];
            st.pop();
        }
    }
    return ans;
}

string removeOuterParentheses(string s) //do by stack also
{
    string ans = "";
    int count = 0;
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];

        if (ch == '(' && count++ > 0)
            ans += ch;
        else if (ch == ')' && count-- > 1)
            ans += ch;
    }
    return ans;
}

//1541
int minInsertions(string s)
{
    int ans = 0;
    int open = 0;

    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == '(')
            open++;
        else
        {
            if (i + 1 == s.length() || s[i + 1] != ')')
                ans++;
            else
                i++;

            if (open > 0)
                open--;
            else
                ans++;
        }
    }

    return 2 * open + ans;
}

//394
string decodeString(string s) {
    stack<string>st;
    stack<int>numst;
    string res="";
    int i=0;
    while(i<s.length())
    {
        char ch=s[i];
        if(ch>=48 && ch<=57)
        {
            int num=0;
            while(i<s.length() && s[i]>=48 && s[i]<=57)
            {
                num=num*10+(s[i]-'0');
                i++;
            }
            numst.push(num);
        }
        else if(ch=='[')
        {
            string t={ch};
            st.push(t);
            i++;
        }
        else{
            if(ch>=97 && ch<=122 && st.size()==0)
                res+=ch;
            else if(ch>=97 && ch<=122)
            {
                string t={ch};
                st.push(t);
            }
            else{
                string subans1="",subans2="";
                while(st.top()!="[")
                {
                    subans1=st.top()+subans1;
                    st.pop();
                }
                st.pop();
                int repeat=numst.top();numst.pop();
                for(int i=0;i<repeat;i++)
                {
                    for(char ch:subans1)
                        subans2+=ch;
                }
                if(st.size()!=0)
                    st.push(subans2);
                else
                    res+=subans2;
            }
            i++;
        }
    }
    
    return res;
}

void ngor(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ngor(n, n);
    for (int i = 0; i < arr.size(); i++)
    {
        int val = arr[i];
        for (int j = i + 1; j < n; j++)
        {
            if (arr[j] > val)
            {
                ngor[i] = j;
                break;
            }
        }
    }
    for (int i = 0; i < n; i++)
        cout << ngor[i] << endl;
}

void ngorStack(vector<int> &arr) //seedha chalna if stream data
{
    //stack mein vo jo potn ng hoskte hain
    // nge[n - 1] = -1;

    // Stack<Integer> st = new Stack<>();
    // st.push(arr[n - 1]);
    // for (int i = n - 2; i >= 0; i--) {
    //   while (st.size() > 0 && st.peek() <= arr[i])
    //     st.pop();
    //   if (st.size() != 0)
    //     nge[i] = st.peek();
    //   else
    //     nge[i] = -1;

    //   st.push(arr[i]);
    // }
    // return nge;

    //jinka bdA hum dhud rhe hain right mein vo abhi stack mein hai
    stack<int> st;
    int n = arr.size(), i = 1;
    vector<int> ngrArray(n, n);
    st.push(0);    //i=0 and this not needed
    while (i < n)
    {
        if (st.size() != 0 && arr[i] > arr[st.top()])
        {
            ngrArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i++;
        }
    }
    for (int i = 0; i < n; i++)
        cout << ngrArray[i] << endl;
}

// 739(ngor,stack->ll)
public int[] dailyTemperatures(int[] temperatures) {

    int n = temperatures.length;
    LinkedList<Integer> st = new LinkedList<>();
    int result[] = new int[n];

    st.addLast(0);
    for (int i = 1; i < n; i++) {
        while (st.size() != 0 && temperatures[st.getLast()] < temperatures[i]) {
            result[st.getLast()] = i - st.getLast();
            st.removeLast();
        }

        st.addLast(i);
    }

    return result;
}

void nsorStack(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = 1;
    vector<int> nsrArray(n, n);
    st.push(0);
    while (i < n)
    {
        if (st.size() != 0 && arr[i] < arr[st.top()])
        {
            nsrArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i++;
        }
    }
    for (int i = 0; i < n; i++)
        cout << nsrArray[i] << endl;
}

public int[] nearestSmallerRight(int a[], int n) {
    int[] nearestRightSmall = new int[n];
    Stack<Integer> st = new Stack<>();
    st.push(a[n - 1]);
    for (int i = n - 2; i >= 0; i--) {
        while (st.size() > 0 && st.peek()>=a[i])
            st.pop();
        if (st.size() == 0)
            nearestRightSmall[i] = 0;
        else
            nearestRightSmall[i] = st.peek();

        st.push(a[i]);
    }

    return nearestRightSmall;
}

void ngolStack(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = n - 2;
    vector<int> nglArray(n, -1);
    st.push(n - 1);
    while (i >= 0)
    {
        if (st.size() != 0 && arr[i] > arr[st.top()])
        {
            nglArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i--;
        }
    }
    for (int i = 0; i < n; i++)
        cout << nglArray[i] << endl;
}
 
void nsolStack(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = n - 2;
    vector<int> nslArray(n, -1);
    st.push(n - 1);
    while (i >= 0)
    {
        if (st.size() != 0 && arr[i] < arr[st.top()])
        {
            nslArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i--;
        }
    }
    for (int i = 0; i < n; i++)
    {
        cout << nslArray[i] << endl;
    }
}

// https://practice.geeksforgeeks.org/problems/maximum-difference/1#
public int[] nearestSmallerLeft(int a[], int n) {
    int[] nearestleftSmall = new int[n];
    Stack<Integer> st = new Stack<>();
    st.push(a[0]);
    for (int i = 1; i < n; i++) {
        while (st.size() > 0 && a[i] <= st.peek())
            st.pop();
        if (st.size() == 0)
            nearestleftSmall[i] = 0;
        else
            nearestleftSmall[i] = st.peek();

        st.push(a[i]);
    }

    return nearestleftSmall;
}

//Gfg-Maximum abs Difference btw nsol and nsor
int findMaxDiff(int a[], int n) {
    int[] nearestleftSmall = nearestSmallerLeft(a, n);
    int[] nearestRightSmall = nearestSmallerRight(a, n);
    int max = 0;
    for (int i = 0; i < n; i++) {
        max = Math.max(max, Math.abs(nearestleftSmall[i] - nearestRightSmall[i]));
    }
    return max;
}

// 496->find nge for nums2 and give ans for nums1 acc to map
public static HashMap<Integer, Integer> solve(int[] arr) {
    int n = arr.length;
    int[] nge = new int[n];
    nge[n - 1] = -1;

    Stack<Integer> st = new Stack<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    st.push(arr[n - 1]);
    map.put(arr[n - 1], -1);
    for (int i = n - 2; i >= 0; i--) {
        while (st.size() > 0 && st.peek() < arr[i])
            st.pop();
        if (st.size() != 0)
            nge[i] = st.peek();
        else
            nge[i] = -1;
        map.put(arr[i], nge[i]);

        st.push(arr[i]);
    }
    return map;
}

public int[] nextGreaterElement(int[] nums1, int[] nums2) {

    int ans[] = new int[nums1.length];
    int i = 0;
    HashMap<Integer, Integer> map = solve(nums2);
    for (int ele : nums1) {
        ans[i] = map.get(ele);
        i++;
    }

    return ans;
}

//503
void nextGreaterElements2(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = 1;
    vector<int> ngrArray(n, -1);
    //if(n==1)return ngrArray;
    st.push(0);
    while (i < 2 * n)
    {
        if (st.size() != 0 && arr[i % n] > arr[st.top()])
        {
            ngrArray[st.top()] = arr[i % n];
            st.pop();
        }
        else if (i < n)
        {
            st.push(i);
            i = i + 1;
        }
        else
            i = i + 1;
    }
}

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/stacks/number-of-valid-subarrays-official/ojquestion
public static int validSubarrays(int[] nums) {
    int count = 0;
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < nums.length; i++) {
        while (st.size() > 0 && nums[i] < st.peek())  //nsr
            st.pop();
        st.push(nums[i]);

        count += st.size();
    }
    return count;
}

// https://pepcoding.com/resources/online-java-foundation/stacks-and-queues/stock-span-official/ojquestion
public static int[] stockSpan(int[] arr) {
    int[] span = new int[arr.length];
    span[0] = 1;   //span-higher than todays price.  //ngl

    Stack<Integer> st = new Stack<>();
    st.push(0);
    for (int i = 1; i < arr.length; i++) {
        while (st.size() > 0 && arr[st.peek()] < arr[i])
            st.pop();

        if (st.size() == 0)
            span[i] = i + 1;
        else
            span[i] = i - st.peek();

        st.push(i);
    }
    return span;
}

// stock span--fixed array
//901
class StockSpanner
{
public:
    stack<pair<int, int>> st;    //nsl
    //Stack<int[]>st=new Stack<>();  //price lower/equal todays price
    int idx = 0;
    StockSpanner()
    {
        st.push({-1, -1}); //{idx,value}
        //st.push(new int[]{-1,-1});
    }

    int next(int price)
    {
        while (st.top().second != -1 && st.top().second <= price)
            st.pop();
        int span = idx - st.top().first;
        st.push({idx, price});
        idx++;

        //while (st.peek()[0]!=-1 && st.peek()[1] <= price)
        //        st.pop();

        //int span=idx-st.peek()[0];
        //st.push(new int[]{idx++,price});
        return span;
    }
};

//84-1st method in java
public int largestRectangleArea(int[] arr) {
    int[] nsLeft = nseLeft(arr);
    int[] nsRight = nseRight(arr);

    int maxArea = 0;
    for (int i = 0; i < arr.length; i++) {
        int width = nsRight[i] - nsLeft[i] - 1;
        int area = arr[i] * width;
        System.out.println("Area " + i + "can make-->" + area);
        maxArea = Math.max(maxArea, area);
    }

    return maxArea;
}

int largestRectangleArea(vector<int> &heights)
{
    int n = heights.size();
    stack<int> st;
    st.push(-1);

    int area = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && heights[i] <= heights[st.top()])  //nsr
        {
            int height = heights[st.top()];
            st.pop();

            int w = i - st.top() - 1;
            area = max(area, height * w);
        }
        st.push(i);
    }

    while (st.size() != 1)
    {
        int height = heights[st.top()];
        st.pop();

        int w = n - st.top() - 1;
        area = max(area, height * w);
    }
    return area;
}

//85
int maximalRectangle(vector<vector<char>> &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;

    int n = matrix.size();
    int m = matrix[0].size();
    vector<int> arr(m, 0);
    int area = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            arr[j] = matrix[i][j] == '1' ? arr[j] + 1 : 0;
        }
        area = max(area, largestRectangleArea(arr));
    }
    return area;
}

//42
int trap(vector<int> &height)
{
    int n = height.size();
    vector<int> left(n, 0);
    vector<int> right(n, 0);

    int prev = 0;
    for (int i = 0; i < n; i++)
    {
        left[i] = max(height[i], prev);
        prev = left[i];
    }

    prev = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        right[i] = max(height[i], prev);
        prev = right[i];
    }

    int water = 0;
    for (int i = 0; i < n; i++)
    {
        water += min(left[i], right[i]) - height[i];
    }

    return water;
}

int trap2(vector<int> &height)
{ 
    stack<int> st;
    int n = height.size();

    int water = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && height[st.top()] <= height[i])  //ngr
        {
            int h = st.top();
            st.pop();

            if (st.size() == 0)
                break;
            int w = i - st.top() - 1;
            water += w * (min(height[i], height[st.top()]) - height[h]);
        }

        st.push(i);
    }
    return water;
}

int trap3(vector<int> &height)
{
    int water = 0, lmax = 0, rmax = 0;
    int i = 0, j = height.size() - 1;
    while (i < j)
    {
        lmax = max(height[i], lmax);
        rmax = max(height[j], rmax);

        if (lmax <= rmax)
            water += lmax - height[i++];
        else
            water += rmax - height[j--];
    }

    return water;
}

//946
bool validateSequence(vector<int> &pushed, vector<int> &popped)
{
    int i = 0, j = 0;
    stack<int> st;
    while (i < pushed.size())
    {
        if (pushed[i] == popped[j])
        {
            i++;
            j++;
        }
        else if (st.size() != 0 && st.top() == popped[j])
        {
            st.pop();
            j++;
        }
        else
        {
            st.push(pushed[i]);
            i++;
        }
    }

    while (st.size() != 0)
    {
        if (st.top() == popped[j])
        {
            j++;
            st.pop();
        }
        else
            return false;
    }

    return true;
}

bool validateStackSequences2(vector<int> &pushed, vector<int> &popped) //sir
{
    stack<int> st;
    int i = 0;

    for (int ele : pushed)
    {
        st.push(ele);
        while (st.size() != 0 && st.top() == popped[i])
        {
            st.pop();
            i++;
        }
    }

    return st.size() == 0;
}

public static void findCelebrity(int[][] arr) {
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < arr.length; i++)
        st.push(i);

    while (st.size() >= 2) {
        int i = st.pop();
        int j = st.pop();

        if (arr[i][j] == 1) {
            // i knows j so i cant be a celebrity
            st.push(j);
        } else {
            // i doest not know j so j cant be a celebrity
            st.push(i);
        }
    }

    int potnCeleb = st.pop();
    for (int i = 0; i < arr.length; i++) {
        if (i != potnCeleb) {
            if (arr[i][potnCeleb] != 1 || arr[potnCeleb][i] != 0) {
                System.out.println("none");
                return;
            }
        }
    }
    System.out.println(potnCeleb);
}

public static void smallestNumfollowingPattern(String str) {
    Stack<Integer> st = new Stack<>();
    int count = 1;
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch == 'd') {
            st.push(count);
        } else if (ch == 'i') {
            st.push(count);
            while (st.size() != 0) {
                System.out.print(st.pop() + "");
            }
        }
        count++;
    }
    System.out.print(count);
    while (st.size() != 0)
        System.out.print(st.pop() + "");
}

//735-Fatega(pop) only when jab stack mein +ve/for storing purpose only we push -ve num
vector<int> asteroidCollision(vector<int> &asteroids)
{
    stack<int> st;
    for (int ele : asteroids)
    {
        if (ele > 0)
            st.push(ele);
        else
        {
            while (st.size() != 0 && st.top() > 0 && st.top() < -ele) //yahan = lgakr andar fir pop krke continue krke ni chlega //[1,3,2,1,-3]  on equal it will pop 1 also which is wrong
                st.pop();    

            if (st.size() != 0 && st.top() == -ele) // [ 8, -8 ]
                st.pop();
            else if (st.size() == 0 || st.top() < 0)
                st.push(ele);
            else
            { //negative asteroid will destroy  [3,-2]
            }
        }
    }

    vector<int> ans(st.size());
    for (int i = st.size() - 1; i >= 0; i--)
    {
        ans[i] = st.top();
        st.pop();
    }
    return ans;
}

// 56
static class Pair implements Comparable<Pair> {
    int st;
    int et;

    Pair(int st, int et) {
        this.st = st;
        this.et = et;
    }

    public int compareTo(Pair other) {
        return this.st - other.st;
    }
}

public static void mergeOverlappingIntervals(int[][] arr) {
    Pair[] pr = new Pair[arr.length];
    for (int i = 0; i < arr.length; i++) {
        pr[i] = new Pair(arr[i][0], arr[i][1]);
    }

    Arrays.sort(pr);

    Stack<Pair> st = new Stack<>();
    st.push(pr[0]);

    for (int i = 1; i < arr.length; i++) {
        Pair top = st.peek();

        if (top.et >= pr[i].st) {
            Pair rp = st.pop();
            Pair np = new Pair(rp.st, Math.max(top.et, pr[i].et));
            st.push(np);
        } else {
            st.push(pr[i]);
        }
    }
    Stack<Pair> newSt = new Stack<>();
    while (st.size() != 0) {
        newSt.push(st.pop());
    }

    // pep
    while (newSt.size() != 0) {
        Pair rp = newSt.pop();
        System.out.println(rp.st + " " + rp.et);
    }

    // leetcode
    // int n = newSt.size();
    // int[][] ans = new int[n][2];
    // int i = 0;
    // while (newSt.size() != 0) {
    // Pair rp = newSt.pop();
    // ans[i][0] = rp.st;
    // ans[i][1] = rp.et;
    // i++;
    // }

    // return ans;
}

//1047
string removeDuplicates(string s)
{
    int i = 0, j = 0;
    for (; j < s.length(); j++, i++)
    {
        s[i] = s[j];

        if (i > 0 && s[i] == s[i - 1])
            i -= 2;
    }
    return s.substr(0, i);
}

public String removeDuplicates(String s) {
    List<Character> l = new ArrayList<>();
    l.add(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
        l.add(s.charAt(i));
        if (l.size() > 1 && l.get(l.size() - 1) == l.get(l.size() - 2)) {
            l.remove(l.size() - 2);
            l.remove(l.size() - 1);
        }
    }

    String ans = "";
    for (int i = 0; i < l.size(); i++)
        ans += l.get(i);

    return ans;
}

// 1544
public String makeGood(String s) {
    LinkedList<Character> ll = new LinkedList<>();
    String res = "";
    ll.add(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
        if (ll.size() > 0 && (Math.abs(s.charAt(i) - ll.peekLast()) == 32)) {
            ll.pollLast();
        } else
            ll.add(s.charAt(i));
    }

    while (ll.size() > 0) {
        res += ll.peekFirst();
        ll.pollFirst();
    }

    return res;
}

//https://www.interviewbit.com/problems/first-non-repeating-character-in-a-stream-of-characters/
string firstNonRepeatingCharacterInAStream(string A)
{
    unordered_map<char,int>map;
    queue<char>que;
    string ans="";
    for(int i=0;i<A.length();i++)
    {
        map[A[i]]++;
        if(map[A[i]]==1)
        que.push(A[i]);

        while(que.size()>0 &&map[que.front()]>1)
        que.pop();
        
        if(que.size()>0)ans+=que.front();
        else ans+='#';
    }
    
    return ans;
}

//402
string removeKdigits(string num, int k)
{
    int n = num.size();
    vector<int> st; //instead of ll use vector 
 
    for (int i = 0; i < n; i++)   //cannot run till k!=0
    {
        char ch = num[i];
        while (st.size() != 0 && k > 0 && st.back() > ch)
        {
            k--;
            st.pop_back();
        }

        if (st.size() == 0 && ch == '0')   //90301 when k=1//so in this case st size if 0 then only continue...nhi tou last mein answer bnate tym aayga 0301 we want 301
            continue;

        st.push_back(ch);
    }

    while (st.size() != 0 && k-- > 0)
        st.pop_back();

    string ans = "";
    for (int i = 0; i < st.size(); i++)
        ans += st[i];

    return ans.length() == 0 ? "0" : ans;
}

// 844
string simplify(string s)
{
    stack<char>st;
    for(int i=0;i<s.length();i++)
    {
        char ch=s[i];
        if(ch=='#')
        {
            if(st.size()!=0)
                st.pop();
        }
        else
            st.push(ch);
    }
    
    string ans="";
    while(st.size()!=0)
    {
        ans+=st.top();
        st.pop();
    }
    return ans;
}

string simplify(string s)
{
    int bs=0;
    string ans="";
    for(int i=s.length()-1;i>=0;i--)
    {
        if(s[i]=='#')
            bs++;
        else{
            if(bs>0){
                bs--;
            }
            else 
                ans+=s[i];
        }
    }
    return ans;
}

bool backspaceCompare(string s, string t) {
    return simplify(s)==simplify(t);    
}

//71
public String simplifyPath(String path) {
    String strArray[] = path.split("/");
    StringBuilder sb = new StringBuilder("/");
    LinkedList<String> st = new LinkedList<>();

    for (String str : strArray) {
        if (str.equals("..")) {
            if (st.size() != 0)
                st.pollLast();
        } else if (!str.equals("") && !str.equals(".")) {
            st.addLast(str);
        }
    }
    for (String str : st)  
        sb.append(str + "/");

    // if(flag==1) //cant do by stack ko empty krke and flag rkhkr
    if (!st.isEmpty())
        sb.deleteCharAt(sb.length() - 1);

    // flag se kaam ni chelga flag /../ kelie istmaal kraa tha but..yahan flag fail
    // "/home/../../.."
    return sb.toString();
}

// 224
// basic method fails because "-2+1" yahan jaise hi usko + milega tou wo
// calcualtion krega pr operand stack size only 1 so stackEmpty
// exception...
// 2147484647 mein 7 dega output aese hi agar check lgalia ki koi
// operator nhi h tou parse krke output return
// then..." 30" yahan dega output 0 wo
public int calculate(String s) {
    Stack<Integer> st = new Stack<>();
    int result = 0, num = 0, sign = 1;
    st.push(sign);

    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (ch >= '0' && ch <= '9') {
            num = num * 10 + (ch - '0');
        } else if (ch == '+' || ch == '-') {
            result += sign * num;
            sign = st.peek() * (ch == '+' ? 1 : -1);
            num = 0;
        } else if (ch == '(') {
            st.push(sign);
        } else if (ch == ')') {
            st.pop();
        }
    }

    result += sign * num;
    return result;
}

// 316,1081
/*string removeDuplicateLetters(string s){   
    int n = s.size();
    vector<int> freq(26, 0);
    vector<bool> visited(26, 0);
    
    for (int i = 0; i < n; i++)
    {
        char ch = s[i];
        freq[ch - 'a']++;
    }

    string str = ""; //string is array of characters
    for (int i = 0; i < n; i++)
    {
        char ch = s[i];
        freq[ch - 'a']--;

        if (visited[ch - 'a'])
            continue;

        while (str.size() != 0 && freq[str.back() - 'a'] > 0 && str.back() > ch)
        {
            visited[str.back() - 'a'] = false;
            str.pop_back();
        }

        visited[ch - 'a'] = true;
        str.push_back(ch);
    }
    return str;
}*/

//1441
public List<String> buildArrayWithStackOprn(int[] target, int n) {

    List<String>result=new ArrayList<>();
    int i=0,ele=1;
    while(i<target.length)
    {
        if(ele>target[i])break;  // target = [1,2], n = 4 ["push","push"]
        else if(target[i]==ele)
        {
            result.add("Push");
            i++;
        }
        else
        {
            result.add("Push");
            result.add("Pop");
        }
        ele++;
    }
    
    return result;
}

// 1209
class pair_ {
    char ch;
    int count;

    pair_(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }
}

public String removeDuplicates(String s, int k) {

    Stack<pair_> st = new Stack<>();
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        if (st.size() > 0 && st.peek().ch == s.charAt(i)) {
            if (st.peek().count + 1 == k)
                st.pop();
            else {
                pair_ p = st.pop();
                p.count++;
                st.push(p);
            }
        } else {
            pair_ p = new pair_(s.charAt(i), 1);
            st.push(p);
        }
    }

    while (st.size() != 0) {
        pair_ p = st.pop();
        char ch = p.ch;
        int freq = p.count;
        {
            for (int i = 0; i < freq; i++)
                res.append(ch);
        }
    }

    return (res.reverse()).toString();
}

    
// Application

// 1598 crawler log folder
public int minOperations(String[] logs) {
    Stack<Character> st = new Stack<>();
    for (int i = 0; i < logs.length; i++) {
        String str = logs[i];
        char ch1 = str.charAt(0);
        char ch2 = str.charAt(1);
        if ((ch1 >= 97 && ch1 <= 122) || (ch1 >= 48 && ch1 <= 57))
            st.push('f');
        else if (ch1 == '.') {
            if (ch2 == '.' && st.size() > 0)
                st.pop();
        }
    }
    return st.size();
}
// putting data member and fn in a class is called encapsulation
// person p1=new Person(); //p1 is refrence
}

// 636
class pair1 {
    int id;
    int st; // start time
    int ct;
}

public int[] exclusiveTime(int n, List<String> logs) {

    int[] ans = new int[n];
    Stack<pair1> st = new Stack<>();
    for (int i = 0; i < logs.size(); i++) {
        String[] data = logs.get(i).split(":");
        if (data[1].equals("start")) {
            pair1 p = new pair1();
            p.id = Integer.parseInt(data[0]);
            p.st = Integer.parseInt(data[2]);
            st.push(p);
        } else {
            pair1 p = st.pop();
            int interval = Integer.parseInt(data[2]) - p.st + 1;
            ans[p.id] += interval - p.ct;

            if (st.size() > 0) {
                pair1 t = st.pop();
                t.ct += interval;
                st.push(t);
            }
        }
    }

    return ans;
}

// k stack in single array
static class kStack {

    int[] data;
    int[] top;
    int[] next;
    int free;

    kStack(int n, int k) {
        data = new int[n];
        top = new int[k];
        next = new int[n];
        free = 0;
    }

    public void initialise() {
        for (int i = 0; i < next.length - 1; i++) {
            next[i] = i + 1;
        }
        Arrays.fill(top, -1);
    }

    public void push(int stnum, int val) {
        if (free == -1) {
            System.out.println("Stack Overflow");
            return;
        }
        int idx = free;
        free = next[idx];
        data[idx] = val;
        next[idx] = top[stnum];
        top[stnum] = idx;
    }

    public int pop(int stnum) {
        if (top[stnum] == -1) {
            return -1;
        }
        int idx = top[stnum];
        top[stnum] = next[idx];
        next[idx] = free;
        free = idx;
        return data[idx];
    }

    public void display() {
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }
}

/*
    * public static void kStackFunction() { kStack ks = new kStack(12, 3);
    * ks.initialise(); ks.push(2, 15); ks.push(2, 45); ks.push(2, 25); ks.push(1,
    * 5); ks.push(1, 7); ks.push(1, 19);
    * 
    * System.out.println(ks.pop(2)); ks.push(0, 50); System.out.println(ks.pop(0));
    * System.out.println(ks.pop(0)); System.out.println(ks.pop(1)); ks.push(1, 90);
    * ks.pop(2); ks.pop(2); ks.push(1, 100); ks.push(2, 200); ks.push(2, 400);
    * ks.push(1, 1200); ks.push(0, 2200); ks.push(2, 2300); ks.push(0, 2020);
    * ks.push(2, 1230); ks.push(0, 8920); ks.push(1, 82920); ks.push(1, 900);
    * ks.push(1, 20); ks.push(1, 9200); ks.push(1, 9270); ks.display(); }
    */

// https://www.geeksforgeeks.org/efficiently-implement-k-queues-single-array/
static class kQueue {

    int[] data;
    int[] front;
    int[] rear;
    int[] next;
    int free;

    kQueue(int n, int k) {
        data = new int[n];
        front = new int[k];
        rear = new int[k];
        next = new int[n];
        free = 0;
    }

    public void initialise() {
        for (int i = 0; i < next.length - 1; i++) {
            next[i] = i + 1;
        }
        Arrays.fill(front, -1);
        Arrays.fill(rear, -1);
        free = 0;
    }

    public boolean isEmpty(int quenum) {
        return front[quenum] == -1;
    }

    public void push(int quenum, int val) {
        if (free == -1) {
            System.out.println("Queue Overflow");
            return;
        }
        int idx = free;
        free = next[idx];
        if (isEmpty(quenum)) {
            front[quenum] = rear[quenum] = idx;
        } else {
            next[rear[quenum]] = idx;
            rear[quenum] = idx;
        }
        next[idx] = -1;
        data[idx] = val;
    }

    public int pop(int quenum) {
        if (isEmpty(quenum)) {
            return -1;
        }
        int idx = front[quenum];
        front[quenum] = next[idx];
        next[idx] = free;
        free = idx;
        return data[idx];
    }

    public void display() {
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }
    // kQueue kq=new kQueue(10,3);
    // kq.initialise();
    // kq.push(2,15);kq.push(2,45);kq.push(2,50);kq.push(1,17);kq.push(1,49);kq.push(1,39);
    // kq.display();
    // System.out.println(kq.pop(1));
    // System.out.println(kq.pop(1));
    // kq.push(2,99);
    // kq.push(2,900);
/    // kq.display();
}