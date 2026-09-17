package dsakit.cache;

import java.util.*;

public class LFUCache {

    HashMap<Integer,ListNode> map;
    HashMap<Integer,DLL> freq;
    int maxSize;
    int curSize;
    int minFreq;

    public LFUCache(int capacity) {
        map=new HashMap<>();
        freq=new HashMap<>();
        maxSize=capacity;
        curSize=0;
        minFreq=0;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode node=map.get(key);

        updateFreq(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            // only need to update the existing values
            ListNode node=map.get(key);
            node.value=value;

            updateFreq(node);

            return;
        }
        if(maxSize==curSize){
            DLL list=freq.get(minFreq);

            ListNode temp=list.removeLast();
            map.remove(temp.key);

            curSize--;
        }

        ListNode node=new ListNode(key,value);
        map.put(key,node);

        minFreq=1;

        if(!freq.containsKey(1)){
            freq.put(1,new DLL());
        }
        freq.get(1).addNode(node);

        curSize++;
    }

    public void updateFreq(ListNode node){
        int curFreq=node.freq;

        DLL list=freq.get(curFreq);
        list.removeNode(node);

        if(list.size==0){
            freq.remove(curFreq);
            if(minFreq==curFreq){
                minFreq++;
            }
        }

        node.freq++;
        int newFreq=node.freq;


        if(!freq.containsKey(newFreq)){
            freq.put(newFreq,new DLL());
        }
        freq.get(newFreq).addNode(node);        

    }
    
    private static class DLL{
    
        ListNode head;
        ListNode tail;
        int size;
    
        public DLL(){
            head=new ListNode(-1,-1);
            tail=new ListNode(-1,-1);
            head.next=tail;
            tail.prev=head;
            size=0;
        }
    
        public void addNode(ListNode node){
            ListNode nxt=head.next;
            head.next=node;
            node.next=nxt;
            nxt.prev=node;
            node.prev=head;
            size++;
        }
    
        public void removeNode(ListNode node){
            node.prev.next=node.next;
            node.next.prev=node.prev;
            node.prev=null;
            node.next=null;
            size--;
        }
    
        public ListNode removeLast(){
            if(size==0){
                return null;
            }
            ListNode last=tail.prev;
            removeNode(last);
            return last;
        }
    }
    
    private static class ListNode{
        ListNode prev;
        ListNode next;
        int key;
        int value;
        int freq;
    
        ListNode(int key,int value){
            this.key=key;
            this.value=value;
            this.freq=1;
        }
    }
}
