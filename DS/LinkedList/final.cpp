#include <iostream>
#include <vector>
#include <math.h>
#include <stack>
using namespace std;
struct ListNode
{
    int val;
    ListNode *next;
    ListNode()
    {
        val = 0;
        next = nullptr;
    }
    ListNode(int x)
    {
        val = x;
        next = nullptr;
    }
    ListNode(int x, ListNode *next)
    {
        val = x;
        this->next = next;
    }
};
/*
agar use krenge midNode tou stack overflow krjaygi because mergeSort mein hum tail aur head pass krenge and in midNode and mergeTwoSortedLists mein hum null se checking krrhe hain
agr null ke respect mein tou ek hi mid baar baar pass.

//agr global allow ni ho then pass array of two node for tt and th.cant do by passing listNode references in fn because they are made on stack.

gyaan-1.big integer jaisa kaam array mein ni hoskta na ki string mein kyunki string bhi character array hi hai...so for big integer itni continuous memory ni hai..then linkedlist ka concept aaya fragmented memory ko use kro...
3.aur recursion se isliye ni krte ll ke qs because stack bhi continuous memory leti hai aur recursion stack pr chlta hai.
always socho basic se addfirst,addlast,removefirst in qs of ll.

//code ko humesha function mein todo..aur agr if else mein same hi kaam hona hai bs value alg alg use so use fn..and in merge two sorted list mein pehli node kelie..alg se handle krne ki zrurat ni hai...addLast fn ka use kro...agr alg se handle krne ki koshish kri then agr ek list bhi null hui check bdjaenge....aur jo fn link bnayga usme null exception aayga kyunki pehla element set hi ni hopaega agr list null hui koi bhi aur check shi se ni lgaye tou
*/

// 1290
int getDecimalValue(ListNode *head)
{
    ListNode *curr = head;
    int size = 0, decimalVal = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    curr = head;
    while (curr != nullptr)
    {
        decimalVal += curr->val * ((int)pow(2, (size - 1))); //size-1=placevalue
        size--;
        curr = curr->next;
    }
    return decimalVal;
}

int kthNodefromMiddle(ListNode *A, int B)
{
    int size = 0;
    ListNode *curr = A;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    int middleNode = (size / 2) + 1;
    int distanceFromBeg = middleNode - B;
    if (distanceFromBeg <= 0)
        return -1;

    curr = A;
    for (int jump = 1; jump < distanceFromBeg; jump++)
        curr = curr->next;
    return curr->val;
}

//61
ListNode *rotateRight(ListNode *head, int k)
{
    if(head==nullptr || head==nullptr)return head;

    ListNode *curr = head;
    int size = 0, jump;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }

    k = (k % size + size) % size;
    if (k == 0)
        return head;

    curr = head;
    jump = size - k - 1;
    while (jump-- > 0)
        curr = curr->next;
    ListNode *forward = curr->next;
    ListNode *newHead = forward;
    curr->next = nullptr;
    while (forward->next != nullptr)
        forward = forward->next;
    forward->next = head;

    return newHead;
}

// display ll reverse-faith ye rkho ki aage ki list pass krdo aur waapsi mein vo print ho kr aajaygi.
public void displayReverseHelper(Node node)
{
    if (node != null)
    {
        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }
}

//leetcode 206
ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *prev = nullptr, *curr = head;
    while (curr != nullptr)
    {
        ListNode *forward = curr->next; // store the address of curr.next before pointing curr.next to previous.
        curr->next = prev;

        prev = curr;
        curr = forward;
    }

    return prev;
}

ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;
    ListNode *temp1 = head->next;
    head->next = nullptr;
    ListNode *reverseListHead = reverseList(temp1);
    //head->next=nullptr;
    temp1->next = head;
    return reverseListHead;
}

//leetcode 876
// for even list mein dusra waala mid
ListNode *middleNode(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *fast = head, *slow = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        fast = fast->next->next;
        slow = slow->next;
        // if(fast->next==nullptr)break; fast=fast->next; //agr ek ek se speed bdayi..tou fast!=null ni lgana pdega 4 node se sochlo
    }
    return slow;
}

ListNode *midNode(ListNode *head) //_IfEvenListmeinPehlaWaalaChahiye
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *fast = head, *slow = head;
    while (fast->next != nullptr && fast->next->next != nullptr)
    {
        fast = fast->next->next;  //agr 1 se then check f->n->n=null and no need of 1st condn 
        slow = slow->next;
    }
    return slow;
}

//leetcode 234
bool isPalindrome(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return true;
    ListNode *mid = midNode(head);
    ListNode *newListHead = mid->next;
    mid->next = nullptr;

    newListHead = reverseList(newListHead);

    ListNode *curr1 = head;
    ListNode *curr2 = newListHead;

    bool res = true;
    while (curr1 != nullptr && curr2 != nullptr)
    {
        if (curr1->val != curr2->val)
        {
            res = false;
            break;
        }

        curr1 = curr1->next;
        curr2 = curr2->next;
    }

    //It is good to unchange the changes we have done with the data.
    newListHead = reverseList(newListHead);
    mid->next = newListHead;
    return res;
}

void dataReverse(ListNode *head)
{
    //li ri pass kro then unka data swap li++ ri--.
    //int li = 0, ri = size - 1;
    //while (li < ri) {
    //    Node leftNode = getNodeAt(li);
    //    Node rightNode = getNodeAt(ri);
    //    int temp = leftNode.data;
    //    leftNode.data = rightNode.data;
    //    rightNode.data = temp;
    //    li++;
    //    ri--;
    //}

    if (head == nullptr || head->next == nullptr)
        return;
    ListNode *mid = midNode(head);
    ListNode *newListHead = mid->next;
    mid->next = nullptr;

    newListHead = reverseList(newListHead);

    ListNode *curr1 = head;
    ListNode *curr2 = newListHead;

    while (curr1 != nullptr && curr2 != nullptr)
    {
        int temp = curr1->val;
        curr1->val = curr2->val;
        curr2->val = temp;

        curr1 = curr1->next;
        curr2 = curr2->next;
    }

    newListHead = reverseList(newListHead);
    mid->next = newListHead;
}

//leetcode 143
void reorderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;
    ListNode *mid = midNode(head);
    ListNode *secondHalfHead = mid->next;
    mid->next = nullptr;

    secondHalfHead = reverseList(secondHalfHead);

    ListNode *c1 = head;
    ListNode *c2 = secondHalfHead;
    // ListNode *forward1 = c1->next;
    // ListNode *forward2 = c2->next;
    while (c1 != nullptr && c2 != nullptr)
    {
        ListNode *forward1 = c1->next; //backup
        ListNode *forward2 = c2->next;

        c1->next = c2; //links
        c2->next = forward1;

        c1 = forward1; //move
        c2 = forward2;

        // if(forward1!=nullptr)forward1=forward1->next;  //ya fir if(c1!=nullptr)forward1=c1->next
        // if(forward2!=nullptr)forward2=forward2->next;
    }
} 

void againReorderlist(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *h1 = head;
    ListNode *h2 = head->next;

    ListNode *c1 = h1;
    ListNode *c2 = h2;

    while (c2 != nullptr && c2->next != nullptr)
    {
        ListNode *f1 = c2->next;

        c1->next = f1;
        c2->next = f1->next;

        c1 = c1->next;
        c2 = c2->next;
    }

    c1->next = nullptr;
    h2 = reverseList(h2);
    c1->next = h2;
}

//leetcode 21
ListNode *mergeTwoLists(ListNode *l1, ListNode *l2)
{
    ListNode *prev = new ListNode(-1);
    ListNode *dummy = prev;

    ListNode *first = l1;
    ListNode *second = l2;

    while (first != nullptr && second != nullptr)
    {
        if (first->val < second->val)
        {
            prev->next = first;
            first = first->next;
        }
        else
        {
            prev->next = second;
            second = second->next;
        }
        prev = prev->next;
    }

    prev->next = (first != nullptr ? first : second);
    return dummy->next;

    // Node first = l1.head;   //original list must stay as they were...else we can use removeFirst
    //         Node second = l2.head;
    //         LinkedList l3 = new LinkedList();
    //         while(first!=null && second!=null)
    //         {
    //             if(first.data<second.data)
    //             {
    //                 l3.addLast(first.data);
    //                 first=first.next;
    //             }
    //             else{
    //                     l3.addLast(second.data);
    //                     second=second.next;
    //             }
    //         }
    //         while(first!=null)
    //         {
    //             l3.addLast(first.data);
    //             first=first.next;
    //         }
    //         while(second!=null)
    //         {
    //             l3.addLast(second.data);
    //             second=second.next;
    //         }
    //         return l3;
}

//nk^2 approach
ListNode *mergeKLists(vector<ListNode *> &lists)
{

    if (lists.size() == 0)
        return nullptr;

    ListNode *refList = nullptr;
    for (int i = 0; i < lists.size(); i++)
        refList = mergeTwoLists(refList, lists[i]);

    return refList;
}

//23
ListNode *mergeKLists(vector<ListNode *> &lists, int si, int ei)
{
    if (si == ei)
        return lists[si];

    int mid = (si + ei) / 2;
    ListNode *first = mergeKLists(lists, si, mid);
    ListNode *second = mergeKLists(lists, mid + 1, ei);

    return mergeTwoLists(first, second);
}

ListNode *mergeKLists(vector<ListNode *> &lists)
{

    if (lists.size() == 0)
        return nullptr;

    return mergeKLists(lists, 0, lists.size() - 1);
}

//148
ListNode *sortList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;
    ListNode *mid = midNode(head);
    ListNode *newHead = mid->next;
    mid->next = nullptr;

    ListNode *firstList=sortList(head);
    ListNode *secondList=sortList(newHead);
    
    return mergeTwoLists(firstList, secondList);
}

//leetcode 141
bool hasCycle(ListNode *head)
{
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr)
    {
        fast = fast->next->next;
        slow = slow->next;

        if (fast == slow)
            return true;
    }
    return false;
}

//leetcode 142
ListNode *detectCycle(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;
    ListNode *slow = head, *fast = head;

    while (fast != nullptr && fast->next != nullptr)
    {
        fast = fast->next->next;
        slow = slow->next;

        if (fast == slow)
            break;
    }

    if (fast != slow)
        return nullptr;
    slow = head;
    ListNode *ref = fast;
    int cycle = 0, cycleLen = 0, a = 0;
    while (fast != slow)
    {
        slow = slow->next;
        fast = fast->next;

        if (fast == ref)
        {
            cycle++;
            if (cycleLen == 0)
                cycleLen = a;
        }
        a++;
    }
    //tail length=a+1
    //m=cycle+1        //m no of rotation before meeting pt
    //c=a-(cycleLen)*cycle
    //b=((a-c)/cycle)-c
    //cyclenLen=b+c
    return slow;
}

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{
    ListNode *first = headA;
    ListNode *second = headB;
    int size1 = 0, size2 = 0;
    while (first != nullptr)
    {
        size1++;
        first = first->next;
    }
    while (second != nullptr)
    {
        size2++;
        second = second->next;
    }
    first = headA;
    second = headB;
    if (size1 > size2)
    {
        for (int i = 0; i < (size1 - size2); i++)
        {
            first = first->next;
        }
    }
    else if (size1 < size2)
    {
        for (int i = 0; i < (size2 - size1); i++)
        {
            second = second->next;
        }
    }

    while (first != nullptr && second != nullptr)
    {
        if (first == second)
        {
            return first;
        }
        else
        {
            first = first->next;
            second = second->next;
        }
    }
    return nullptr;
}

//leetcode 160
ListNode *getIntersectionNodeRajnesshSir(ListNode *headA, ListNode *headB)
{
    if (headA == nullptr || headB == nullptr)
        return nullptr;

    ListNode *tail = headB;
    while (tail->next != nullptr)
        tail = tail->next;

    tail->next = headA;
    ListNode *ans = detectCycle(headB);
    tail->next = nullptr;
    return ans;
}

// 86
ListNode *partition(ListNode *head, int x)
{
    ListNode *dsmall = new ListNode(-1);
    ListNode *p1 = dsmall;
    ListNode *dgreater = new ListNode(-1);
    ListNode *p2 = dgreater;

    ListNode *curr = head;
    while (curr != nullptr)
    {
        if (curr->val < x)
        {
            p1->next = curr;
            p1 = curr;
        }
        else
        {
            p2->next = curr;
            p2 = curr;
        }

        curr = curr->next;
    }
    p2->next = nullptr;
    p1->next = dgreater->next;

    return dsmall->next;
}

//ib
ListNode *sortBinaryList(ListNode *A)
{
    ListNode *dummyZero = new ListNode(-1);
    ListNode *prevZero = dummyZero;
    ListNode *dummyOne = new ListNode(-1);
    ListNode *prevOne = dummyOne;

    ListNode *curr = A;
    while (curr != nullptr)
    {
        if (curr->val == 0)
        {
            prevZero->next = curr;
            prevZero = curr;
        }
        else
        {
            prevOne->next = curr;
            prevOne = curr;
        }

        curr = curr->next;
    }

    prevZero->next = dummyOne->next;
    prevOne->next = nullptr;

    return dummyZero->next;
}

//leetcode 328
void removeFirst(ListNode *&head, int &size)
{
    if (size == 0)return;
    else
    {
        head = head->next;
        size--;
    }
}

void addLast(int &size, int value, ListNode *&head, ListNode *&tail)
{
    ListNode *temp = new ListNode();
    temp->val = value;
    temp->next = nullptr;
    if (size == 0)
        head = tail = temp;
    else
    {
        tail->next = temp;
        tail = temp;
    }
    size++;
}

ListNode *oddEvenList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *curr = head;
    int size = 0, i = 1, oddSize = 0, evenSize = 0, osize = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    osize = size;
    ListNode *odd = nullptr, *oddTail = nullptr, *even = nullptr, *evenTail = nullptr;

    while (i <= osize)
    {
        int val = head->val;
        removeFirst(head, size);

        if (i % 2 != 0)
            addLast(oddSize, val, odd, oddTail);
        else
            addLast(evenSize, val, even, evenTail);

        i++;
    }

    oddTail->next = even;
    return odd;
}

// https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list/0
public class questions {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    static Node head;
    static Node tail;

    public static void display(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static Node segregateEvenOdd(Node head) {
        Node dummyEven = new Node(-1);
        Node dummyOdd = new Node(-1);
        Node prevOdd = dummyOdd;
        Node prevEven = dummyEven;
        Node curr = head;
        while (curr != null) {
            if (curr.val % 2 == 0) {
                prevEven.next = curr;
                prevEven = curr;
            } else {
                prevOdd.next = curr;
                prevOdd = curr;
            }
            curr = curr.next;
        }
        prevOdd.next = null;
        prevEven.next = dummyOdd.next;
        return dummyEven.next;
    }

    public static void segregategfg() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            // head=null;
            // tail=null;
            Node dummyNode = new Node(-1);
            Node prev = dummyNode;
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                Node temp = new Node(sc.nextInt());
                prev.next = temp;
                prev = temp;
                // addLast(val);
            }
            Node head = segregateEvenOdd(dummyNode.next);
            // head=segregateEvenOdd(head);
            display(head);
        }

        // LinkedList oddList=new LinkedList();
        //     LinkedList evenList=new LinkedList();
        //     while(this.size()>0)   
        //     {
        //         int val=this.getFirst();
        //         this.removeFirst();
                
        //         if(val%2!=0)
        //             oddList.addLast(val);
        //         else evenList.addLast(val);
        //     }
        //     if(evenList.head!=null && oddList.tail!=null)  //connect
        //     (oddList.tail).next=evenList.head;
    
        //     if(oddList.head!=null)  //set prop
        //     this.head=oddList.head;
        //     else
        //     this.head=evenList.head;
        //     if(evenList.tail!=null)
        //     this.tail=evenList.tail;
        //     else
        //     this.tail=oddList.tail;
            
        //     this.size=oddList.size()+evenList.size();
    }
}

//ib
ListNode *evenReverse(ListNode *A)
// Reverse the order of all nodes at even position
{
    ListNode *dummyEven = new ListNode(-1);
    ListNode *prevEven = dummyEven;
    ListNode *dummyOdd = new ListNode(-1);
    ListNode *prevOdd = dummyOdd;

    ListNode *curr = A;
    int posn = 1;
    while (curr != nullptr)
    {
        if (posn % 2 == 0)
        {
            prevEven->next = curr;
            prevEven = curr;
        }
        else
        {
            prevOdd->next = curr;
            prevOdd = curr;
        }

        posn++;
        curr = curr->next;
    }

    prevEven->next = nullptr;
    prevOdd->next = nullptr;

    //reverse even list
    ListNode *prev = nullptr;
    curr = dummyEven->next;
    while (curr != nullptr)
    {
        ListNode *forward = curr->next;
        curr->next = prev;
        prev = curr;
        curr = forward;
    }
    dummyEven->next = prev;
    ListNode *currOdd = dummyOdd->next;
    ListNode *currEven = dummyEven->next;

    posn = 1;
    A = currOdd;
    while (currOdd != nullptr && currEven != nullptr)
    {
        if (posn % 2 == 0)
        {
            ListNode *f = currEven->next;
            currEven->next = currOdd;
            currEven = f;
        }
        else
        {
            ListNode *f = currOdd->next;
            currOdd->next = currEven;
            currOdd = f;
        }

        posn++;
    }

    return A;
}

//It is guaranteed that the node to be deleted is not a tail node in the list.
void deleteNode4(ListNode *node) //sir
{
    ListNode *temp = node->next;
    node->val = temp->val;
    node->next = temp->next;
    temp->next = nullptr;
}

//leetcode 83
// ‘this’ pointer is not available in static member functions as static member functions can be called without any object (with class name).
ListNode *deleteDuplicates(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *first = head;
    ListNode *second = head->next;

    while (second != nullptr)
    {
        if (first->val == second->val)
        {
            first->next = second->next;
            second = first->next;
        }
        else
        {
            first = second;
            second = second->next;
        }
    }
    return head;

    // LinkedList res=new LinkedList();
    //         while(this.size()>0)    //linear time bcz har node ko ekbaar visit kiya
    //         {
    //                 int val=this.getFirst();
    //                 this.removeFirst();  //yahan pr extra space sirf 12 byte(h,t,s) lii joki 1lakh size waali list kelie bhi sirf 12 hi rhegi.yahan space constant because ek tym pr dono node zinda ni hai...purani list mein hum delete krdete hai fir nayi mein daal dete hai tou space balance.
    //             if(res.size()==0 || res.getLast()!=val)
    //             {
    //                 res.addLast(val);
    //             }
    //         }

    //         this.head=res.head;   //yahan pr ye isliye kiya kyunki platform pr vo purani this list hi display krrhe hai.this=res nhi krskte because this is a read only..we can change its properties.
    //         this.tail=res.tail;
    //         this.size=res.size;
}

// deleteDuplicate2-read code..isme waise socho pehle 1122 test case kelie then 2 3 4 4 4
//leetcode 82
ListNode *deleteDuplicates(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *temp = head->next;

    while (head != nullptr && temp != nullptr && head->val == temp->val) //[1122]
    {
        int val = head->val;
        temp = head;
        while (temp != nullptr && temp->val == val)
        {
            head = head->next;
            temp = temp->next;
        }
        if (head != nullptr)
            temp = head->next;
    }

    if (head != nullptr && head->next != nullptr)
    {
        ListNode *first = head;
        ListNode *second = head->next;
        ListNode *prev;
        while (second != nullptr)
        {
            if (first->val == second->val)
            {
                while (second->next != nullptr && (second->next)->val == first->val)
                {
                    second = second->next;
                }

                prev->next = second->next;
                first = prev->next;
                if (first != nullptr)
                    second = first->next;
                else
                    second = nullptr; //122
            }
            else
            {
                prev = first;
                first = second;
                second = second->next;
            }
        }
    }
    return head;
}

//leetcode 203
ListNode *removeFirstNode(ListNode *head)
{
    ListNode *temp = head;
    head = head->next;

    temp->next = nullptr;
    return head;
}

ListNode *removeElements(ListNode *head, int val)
{
    while (head != nullptr && head->val == val)
    {
        head = removeFirstNode(head);
    }
    if (head == nullptr)
        return head;
    ListNode *prev = head;
    ListNode *nxt = prev->next;  //prev next just like first-second
    while (nxt != nullptr)
    {
        if (nxt->val == val)
        {
            nxt = nxt->next;
            prev->next = nxt;
        }
        else
        {
            prev = nxt;
            nxt = nxt->next;
        }
    }
    return head;
}

//leetcode 19
ListNode *getNodeAt(ListNode *head, int idx)
{
    ListNode *temp = head;
    for (int i = 0; i < idx; i++)
        temp = temp->next;
    return temp;
}

ListNode *removeNthFromEnd(ListNode *head, int n)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr; //return nullptr because question mein given hai ki agr ek bhi element hoga tou n 1 hoga
    ListNode *curr = head;
    int size = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    if (size - n - 1 < 0) //testCase [1,2] 2
        head = head->next;
    else
    {
        ListNode *prev = getNodeAt(head, size - n - 1); // n is 1 based index
        ListNode *toDelete = getNodeAt(head, size - n);
        prev->next = toDelete->next;
        toDelete->next = nullptr;
    }
    return head;
}

ListNode *removeNthFromEndRajneeshSir(ListNode *head, int n)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;

    ListNode *c1 = head;
    ListNode *c2 = head;
    ListNode *temp = c1;
    while (n-- > 0)
        c2 = c2->next;

    if (c2 == nullptr)
        head = head->next;
    else
    {
        while (c2->next != nullptr)
        {
            c1 = c1->next;
            c2 = c2->next;
        }
        temp = c1->next;
        c1->next = c1->next->next;
    }
    temp->next = nullptr;
    return head;
}

public int kthFromLast(int k)
{
    // return getAt(size-k-1); //given in constraint size parameter not to use.
    // reverseDI();
    // return getAt(k); //works only 1 time

    Node fast = head, slow = head;
    for (int i = 0; i < k; i++)
        fast = fast.next;

    while (fast != tail)
    {
        fast = fast.next;
        slow = slow.next;
    }
    return slow.data;
}

//1721
ListNode *swapNodes(ListNode *head, int k)
{ //by me
    ListNode *forward = head;
    ListNode *back = head;
    ListNode *curr = head;
    int fjump = 0, bjump = 0, size = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }
    if (k - 1 == size - k)
        return head;
    while (fjump < k - 1)
    {
        forward = forward->next;
        fjump++;
    }
    while (bjump < size - k)
    {
        back = back->next;
        bjump++;
    }
    int temp = forward->val;
    forward->val = back->val;
    back->val = temp;
    return head;
}

ListNode *swapNodes(ListNode *head, int k)
{
    ListNode *p1 = head, *p2 = head, *kth = nullptr;
    while (--k > 0)
        p1 = p1->next;

    kth = p1;
    p1 = p1->next;

    while (p1 != nullptr)
    {
        p1 = p1->next;
        p2 = p2->next; //now p2 reaches the kth node from end
    }

    swap(kth->val, p2->val);
    return head;
}

//leetcode 92
void addFirst(ListNode *node)
{
    if (th == nullptr)
        th = tt = node;
    else
    {
        node->next = th;
        th = node;
    }
}

ListNode *th=nullptr;  
ListNode *tt=nullptr;
ListNode *reverseBetween(ListNode *head, int m, int n)
{
    if (head == nullptr || head->next == nullptr || m == n)
        return head;
    int idx = 1;
    ListNode *prev = nullptr;
    ListNode *curr = head;

    while (idx < n)
    {
        while (idx >= m && idx <= n)
        {
            ListNode *forward = curr->next;
            // curr->next = nullptr; no need
            addFirstNode(curr);
            curr = forward;
            idx++;
        }
        if (idx > n)
        {
            if (prev != nullptr)   //[3,6]  handles mid and last
            {
                prev->next = th;
                tt->next = curr;
                return head;
            }
            else
            {
                tt->next = curr; //12345 mein 1,4 reverse...handles beginning
                return th;
            }
        }
        else
        {
            prev = curr;
            curr = curr->next;
            idx++;
        }
    }
    return head;
}

//leetcode 25
ListNode *th = nullptr;
ListNode *tt = nullptr;
void addFirstNode(ListNode *node)
{
    if (th == nullptr)
    {
        th = node;
        tt = node;
    }
    else
    {
        node->next = th; //node->next=tt; it can be only for second node.
        th = node;
    }
}

int length(ListNode *node)
{
    if (node == nullptr)
        return 0;
    int size = 0;
    while (node != nullptr)
    {
        size++;
        node = node->next;
    }
    return size;
}

ListNode *reverseKGroup(ListNode *head, int k)
{
    if (head == nullptr || k == 1 || head->next == nullptr)
        return head;

    int size = length(head);
    ListNode *originalHead = nullptr, *originalTail = nullptr, *curr = head;
    // ListNode *forward = head->next; 
    while (size >= k)
    {
        int tempK = k;
        while (tempK-- > 0)
        {
            ListNode *forward=curr->next;
            // curr->next = nullptr; //pehle null krenge then add agr pehle add kra then usko null kra tou wo akeli node hi reh jaygi..can do without doing null too
            addFirstNode(curr);
            curr = forward;
            // if (forward != nullptr)
            //     forward = forward->next;
        }

        if (originalHead == nullptr)
        {
            originalHead = th;
            originalTail = tt;
        }
        else
        {
            originalTail->next = th;
            originalTail = tt;
        }
        th = nullptr;
        tt = nullptr;
        size -= k;
    }
    originalTail->next = curr;
    return originalHead;
}

public void kReverse(int k)
{
    int li = 0;
    while (li < this.size())
    {
        if (li + k - 1 < this.size())
            reverseDIKreverse(li, li + k - 1);
        li = li + k;
    }
}

public void kReverse2(int k)
{
    LinkedList prev = null;
    while (this.size() > 0)
    {
        LinkedList curr = new LinkedList();
        if (this.size() >= k)
        {
            for (int i = 0; i < k; i++)
            {
                int val = this.getFirst();
                this.removeFirst(); //yahan removeFirst mein this.size() kam horha hai.
                curr.addFirst(val);
            }
        }
        else
        {
            int s = this.size();
            for (int i = 0; i < s; i++) //cant write this.size()because i bhi bdrha hai aur size ghtrha h removeFirst ki wjh se
            {
                int val = this.getFirst();
                this.removeFirst();
                curr.addLast(val);
            }
        }

        if (prev == null)
            prev = curr;
        else
        {
            prev.tail.next = curr.head;
            prev.tail = curr.tail;
            prev.size += curr.size;
        } 
    }

    this.head = prev.head;
    this.tail = prev.tail;
    this.size = prev.size;
}

//24
ListNode *swapPairs(ListNode *head)
{
    return reverseKGroup(head, 2);
}

//ib
ListNode *reverseAlternateKnodes(ListNode *A, int B)   
{
    ListNode *curr = A;
    int size = 0;
    while (curr != nullptr)
    {
        size++;
        curr = curr->next;
    }

    if (size < B)
        return A;
    curr = A;
    bool skip = false;
    ListNode *ot = nullptr, *oh = nullptr;
    while (size >= B)
    {
        if (!skip)
        {
            int noOfTime = B;
            while (noOfTime-- > 0)
            {
                ListNode *f = curr->next;
                addFirst(curr);
                curr = f;
            }
            skip = true;

            if (oh == nullptr && ot == nullptr)
            {
                oh = th;
                ot = tt;
            }
            else
            {
                ot->next = th;
                ot = tt;
            }
            th = nullptr;
            tt = nullptr;
        }
        else
        {
            ot->next = curr;
            int jump = 0;
            while (jump < B - 1)
            {
                curr = curr->next;
                jump++;
            }

            ot = curr;
            curr = curr->next;
            skip = false;
        }
        size -= B;
    }
    ot->next = curr;
    return oh;
}

//445
void addFirst(int val, ListNode *&head)
{
    ListNode *temp = new ListNode();
    temp->val = val;
    if (head == nullptr)
        head = temp;
    else
    {
        temp->next = head;
        head = temp;
    }
}

int addListHelper(ListNode *&one, int pv1, ListNode *&two, int pv2, ListNode *&res)
{
    if (one == nullptr && two == nullptr)
        return 0;
    int carry = 0, data = 0;
    if (pv1 > pv2)
    {
        carry = addListHelper(one->next, pv1 - 1, two, pv2, res);
        data = one->val + carry;
    }
    else if (pv2 > pv1)
    {
        carry = addListHelper(one, pv1, two->next, pv2 - 1, res);
        data = two->val + carry;
    }
    else
    {
        carry = addListHelper(one->next, pv1 - 1, two->next, pv2 - 1, res);
        data = one->val + two->val + carry;
    }
    int digit = data % 10;
    addFirst(digit, res);
    int digitCarry = data / 10;

    return digitCarry;
}

ListNode *addTwoNumbers2(ListNode *l1, ListNode *l2)
{
    ListNode *first = l1;
    ListNode *second = l2;
    ListNode *res = nullptr;
    int size1 = 0, size2 = 0;
    while (first != nullptr)
    {
        size1++;
        first = first->next;
    }
    while (second != nullptr)
    {
        size2++;
        second = second->next;
    }
    first = l1, second = l2;
    int carry = addListHelper(first, size1, second, size2, res);
    if (carry > 0)
        addFirst(carry, res);
    return res;
}

//leetcode 2-Here linked list stored in reverse order so reverse first then normal..then reverse result
int size(ListNode *l1)
{
    ListNode* curr=l1;
    int s=0;
    while(curr!=nullptr)
    {
        s++;
        curr=curr->next;
    }
    return s;
}

ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    if(size(l2)>size(l1))return addTwoNumbers(l2,l1);
    // ListNode* dummy=new ListNode(-1);
    // ListNode* prev=dummy;
    
    int carry=0;
    ListNode *first=l1,*prev=nullptr;
    while(first!=nullptr || carry!=0)
    {
        int sum=carry+ (first!=nullptr?first->val:0) + (l2!=nullptr?l2->val:0);
        int digit=sum%10;
        carry=sum/10;
        
        if(first!=nullptr)
        {
            first->val=digit;
            prev=first;
        }
        else{
            ListNode* temp=new ListNode(digit);
            prev->next=temp;
        }
        
        if(first!=nullptr)first=first->next;
        if(l2!=nullptr)l2=l2->next;
    }
    return l1;
}

//SubtractTwoNum
public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2)
{
    if (l2 == null)
        return l1;
    else if (l1 == null)
    {
        l2.val = -l2.val;
        return l2;
    }

    l1 = reverseList(l1);
    l2 = reverseList(l2);
    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    while (l1 != null)
    {
        int difference = l1.val - (l2 != null ? l2.val : 0);
        ListNode newNode = new ListNode(difference);
        if (difference < 0)
            newNode.val += 10;
        prev.next = newNode;
        prev = prev.next;

        l1 = l1.next;
        if (l1 != null && difference < 0)  //for this question subtraction result always positive
            l1.val -= 1;
        if (l2 != null)
            l2 = l2.next;
    }
    return (reverseList(dummy.next));
}

//Multiply
//Sum 2 list
public static void sumTwoList(ListNode l1, ListNode l2)
{
    int carry = 0;
    while (l1 != null || carry != 0)
    {
        int sum = carry + (l1 != null ? l1.val : 0) + (l2.next != null ? l2.next.val : 0);

        int digit = sum % 10;
        carry = sum / 10;

        if (l2.next != null)
            l2.next.val = digit;
        else l2.next = new ListNode(digit);

        if (l1 != null)
            l1 = l1.next;
        l2 = l2.next;
    }
}

//multiplication in ll
public static ListNode multiplyWithoneDigit(ListNode l1, int digit)
{
    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    int carry = 0;
    while (l1 != null || carry != 0)
    {
        int sum = carry + (l1 != null ? l1.val : 0) * digit;
        carry = sum / 10;
        int dig = sum % 10;

        ListNode newNode = new ListNode(dig);
        prev.next = newNode;
        prev = prev.next;

        if (l1 != null)
            l1 = l1.next;
    }

    return dummy.next;
}

public static ListNode multiplyTwoLL(ListNode l1, ListNode l2)  
{
    l1 = reverseList(l1);
    l2 = reverseList(l2);

    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    while (l2 != null)
    {
        ListNode product = multiplyWithoneDigit(l1, l2.val);
        l2 = l2.next;
        sumTwoList(product, prev);
        prev= prev.next;
    }

    return reverseList(dummy.next);
}

//leetcode 138
class Node
{
public:
    int val;
    Node *next;
    Node *random;

    Node(int _val)
    {
        val = _val;
        next = NULL;
        random = NULL;
    }
};

void copyNode(Node *head)
{
    Node *curr = head;
    while (curr != nullptr)
    {
        Node *forward = curr->next;
        Node *newNode = new Node(curr->val);
        curr->next = newNode;
        newNode->next = forward;
        curr = forward;
    }
}

void setRandom(Node *head)
{
    Node *curr = head;
    while (curr != nullptr)
    {
        if (curr->random != nullptr)
            curr->next->random = curr->random->next;
        curr = curr->next->next;
    }
}

Node *extractLL(Node *head)
{
    Node *dummy = new Node(-1);
    Node *copycurr = dummy;
    Node *curr = head;

    while (curr != nullptr)
    {
        copycurr->next = curr->next;
        curr->next = curr->next->next;
        copycurr = copycurr->next;
        curr = curr->next;
    }

    return dummy->next;
}

Node *copyRandomList(Node *head)
{
    if (head == nullptr)
        return head;

    copyNode(head);
    setRandom(head);
    return extractLL(head);
}

//1019
vector<int> nextLargerNodes(ListNode *head)
{
    ListNode *curr = head;
    int size = 0, count = 0;
    while (curr != nullptr)
    {
        curr = curr->next;
        size++;
    }
    vector<int> ans(size, 0);
    stack<pair<int, int>> st;
    curr = head;
    while (curr != nullptr)
    {
        while (st.size() != 0 && st.top().first < curr->val)
        {
            pair<int, int> p = st.top();
            st.pop();

            ans[p.second] = curr->val;
        }
        st.push({curr->val, count});
        count++;
        curr = curr->next;
    }

    return ans;
}

// 817
public int numComponents(ListNode head, int[] G)
{
    Set<Integer> hs = new HashSet<>();
    for (int ele : G)
        hs.add(ele);

    ListNode prev = null, curr = head;
    int newComponents = 0;

    while (curr != null)
    {
        if (prev == null)
        {
            if (hs.contains(curr.val))
                newComponents++;
        }
        else if (!hs.contains(prev.val) && hs.contains(curr.val))
            newComponents++;

        prev = curr;
        curr = curr.next;
    }

    return newComponents;
}

// 147
ListNode *insertionSortList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *dummy = new ListNode(-5001);
    ListNode *curr = head;
    while (curr != nullptr)
    {
        ListNode *f = curr->next;
        ListNode *p = dummy;
        while (p->next != nullptr && p->next->val < curr->val)
            p = p->next;

        curr->next = p->next;
        p->next = curr;
        curr = f;
    }

    return dummy->next; 
}

//725
vector<ListNode*> splitListToParts(ListNode* head, int k) {
    vector<ListNode*>ans(k);
    if(head==nullptr)return ans;
    
    int size=0;
    ListNode *curr=head;
    while(curr!=nullptr)
    {
        size++;curr=curr->next;
    }
    int exp=size%k,i=0;  //extra parts
    int ep=size/k;  //equal parts
    while(k-->0)
    {
        int tt=ep;
        if(exp>0){
            tt++;exp--;
        }
        
        ListNode *node1=head,*node2=head;
        while(tt-->1)
            node2=node2->next;
        
        ListNode *f=node2->next;
        node2->next=nullptr;
        ans[i++]=node1;
        head=f;
        if(head==nullptr)return ans;
    }
    
    return ans;
}
