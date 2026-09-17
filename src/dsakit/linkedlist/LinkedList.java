package dsakit.linkedlist;

public class LinkedList {
    
    ListNode head;
    ListNode tail;
    int size;

    public LinkedList(){
        size=0;
        head=null;
        tail=null;
    }

    public void addFirst(int key){
        ListNode node=new ListNode(key);
        if(head==null){
            head=node;
            tail=node;
        }
        else{
            node.next=head;
            head=node;
        }
        size++;
    }

    public void addLast(int key){
        ListNode node=new ListNode(key);
        if(tail==null){
            head=node;
            tail=node;
        }
        else{
            tail.next=node;
            tail=node;
        }
        size++;
    }

    public int removeFirst(){
        if(size==0) return -1;

        int val=head.value;
        if(size==1){
            head=null;
            tail=null;
        }
        else{
            ListNode next=head.next;
            head.next=null;
            head=next;
        }
        size--;
        return val;
    }

    public int removeLast(){
        if(size==0) return -1;

        int val=tail.value;
        if(size==1){
            head=null;
            tail=null;
        }
        else{
            ListNode prev=null;
            ListNode curr=head;

            while(curr.next!=null){
                prev=curr;
                curr=curr.next;
            }
            prev.next=null;
            tail=prev;
        }
        size--;
        return val;
    }

    public int getFirst(){
        if(size==0) return -1;
        return head.value;
    }

    public int getLast(){
        if(size==0) return -1;
        return tail.value;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return (size==0)?true:false;
    }

    private static class ListNode{
        ListNode next;
        int value;
    
        ListNode(int value){
            this.value=value;
        }
    }
}

