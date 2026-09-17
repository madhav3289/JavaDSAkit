package dsakit.cache;

import java.util.*;

public class LRUCache {

    HashMap<Integer,ListNode> map;
    ListNode head;
    ListNode prev;
    int maxCap;
    int cSize;

    public LRUCache(int capacity) {
        map=new HashMap<>();
        head=new ListNode(-1,-1);
        prev=head;
        maxCap=capacity;
        cSize=0;        
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode v=map.get(key);

        ListNode temp=new ListNode(key,v.kValue);
        temp.prev=prev;
        prev.next=temp;
        prev=temp;

        // remove the prev occurence of this same element
        ListNode t1=map.get(key);
        t1.next.prev=t1.prev;
        t1.prev.next=t1.next;
        t1.prev=null;
        t1.next=null;

        map.put(key,temp);
        return v.kValue;
    }
    
    public void put(int key, int val) {
        if(!map.containsKey(key)){
            ListNode temp=new ListNode(key,val);
            temp.prev=prev;
            prev.next=temp;
            prev=temp;
            map.put(key,temp);

            if(cSize+1>maxCap){
                ListNode t1=head.next;
                t1.next.prev=head;
                head.next=t1.next;
                t1.next=null;
                t1.prev=null;
                map.remove(t1.value);
            }
            else{
                cSize++;
            }
        }

        else{
            ListNode temp=new ListNode(key,val);
            temp.prev=prev;
            prev.next=temp;
            prev=temp;

            // remove the prev occurence of this same element
            ListNode t1=map.get(key);
            t1.next.prev=t1.prev;
            t1.prev.next=t1.next;
            t1.prev=null;
            t1.next=null;

            map.put(key,temp);
        }
    }
    
    private static class ListNode {
        int value;
        int kValue;
        ListNode prev;
        ListNode next;
    
        ListNode(int value,int kValue){
            this.value=value;
            this.kValue=kValue;
        }
    };
}

