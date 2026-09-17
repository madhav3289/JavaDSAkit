package dsakit.algorithms;

public class KMPAlgorithm {

    public int KMP(String s1,String s2){
        int n=s1.length();
        int m=s2.length();

        if(m==0) return 0;

        int [] lps=longestPrefixSuffix(s2,m);

        int i=0;
        int j=0;
        while(i<n && j<m){
            if(s1.charAt(i)==s2.charAt(j)){
                i++;
                j++;
                if(j==m){
                    return i-m;
                }
            }
            else{
                if(j==0){
                    i++;
                }
                else{
                    j=lps[j-1];
                }
            }
        }
        return -1;
    }

    public int[] longestPrefixSuffix(String s,int n){
        int [] lps=new int[n];

        int i=0;
        int j=1;

        while(j<n){
            if(s.charAt(i)==s.charAt(j)){
                lps[j]=i+1;
                i++;
                j++;
            }
            else{
                if(i==0){
                    lps[j]=0;
                    j++;
                }
                else{
                    i=lps[i-1];
                }
            }
        }

        return lps;
    }
}