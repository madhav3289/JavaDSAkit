package dsakit.trie;

import java.util.HashMap;

public class Trie {

    private Node root;

    public Trie() {
        root=new Node('*');
    }

    public void insert(String word) {
        Node curr=root;
        for(int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            if(curr.child.containsKey(ch)){
                curr=curr.child.get(ch);
            }
            else{
                Node nn=new Node(ch);
                curr.child.put(ch,nn);
                curr=nn;
            }
        }
        curr.isTerminal=true;
    }

    public boolean search(String word) {
        Node curr=root;
        for(int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            if(curr.child.containsKey(ch)){
                curr=curr.child.get(ch);
            }
            else{
                return false;
            }
        }
        return curr.isTerminal;
    }
    public boolean startsWith(String prefix) {
        Node curr=root;
        for(int i=0;i<prefix.length();i++){
            char ch=prefix.charAt(i);
            if(curr.child.containsKey(ch)){
                curr=curr.child.get(ch);
            }
            else{
                return false;
            }
        }
        return true;
    }

    private static class Node{
        char ch;
        boolean isTerminal;
        HashMap<Character,Node> child;
        public Node(char ch){
            this.ch=ch;
            child=new HashMap<>();
        }
    }
}
