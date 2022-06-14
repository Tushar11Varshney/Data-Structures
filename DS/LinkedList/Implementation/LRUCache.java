import java.util.*;
// Lru cache-isme agr app ko put krne kelie bole then check if already present or not if present then get from map,update its value and call get fn.if not present if size full removelastnode and remove from map also then addfirst new node then add in map.
// get fn-check value present or not in map if not return -1 else get node from map,call makemost recent and r->val
// make most recent-if head node return else call removeNode and add it to first.
class LRUCache {
    static class ListNode {
        int key=0;
        int val=0;
        ListNode next;
        ListNode prev;

    public ListNode(int key,int val) {
            this.key=key;
            this.val = val;
            this.next = null;
            this.prev=null;
        }
    }

    private int maxSize=0;
    private HashMap<Integer,ListNode>map=new HashMap<>();
    private int currSize=0;
    ListNode head=null,tail=null;

    public LRUCache(int capacity) {
            this.maxSize=capacity;
    }   
    
    public void addFirstNode(ListNode temp)
    {
        if(this.head==null)
        this.head=this.tail=temp;
        else{
            this.head.next=temp;
            temp.prev=head;
            this.head=temp;
        }
        currSize++;
    }

    public void removeLastNode()
    {
        if(this.maxSize==1)
        {
            this.head=null;
            this.tail=null;
        }
        else{
            this.tail=this.tail.next;
            this.tail.prev.next=null;
            this.tail.prev=null;
        }
        currSize--;
    }

    public void removeNode(ListNode node)
    {
        if(node==this.tail)
        {
            removeLastNode();
            return;
        }    
        else{
            ListNode prev=node.prev;
            ListNode forward=node.next;
            node.next=null;node.prev=null;
            prev.next=forward;
            forward.prev=prev;
        }
        currSize--;
    }
    
    public void makeMostRecent(ListNode node)
    {
        if(node==head)return;    //edge case-agr 4 app hain aur pehli app ko get kraa tou uski koi shifting ni krni tou node agr head node h tou return.
        removeNode(node);
        addFirstNode(node);
    }
    
    public int get(int key) { 
        //Return the value of the key if the key exists, otherwise return -1.
        if(!map.containsKey(key))
            return -1;
    
        ListNode node=map.get(key);
        makeMostRecent(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            ListNode node=map.get(key);
            node.val=value;
            get(key);  
        }   
        else{
            if(currSize==maxSize)
            {
                map.remove(tail.key);
                removeLastNode();
            }    
            ListNode temp=new ListNode(key,value);
            addFirstNode(temp);
            map.put(key,temp);
        }     
    }
}
