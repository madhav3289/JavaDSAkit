package dsakit.segmenttree;

public class SegmentTree {

    public void solve(int [] nums,int [][] q,int n,int m){
        int [] segT=new int[4*n];
        // build segment tree
        buildTree(segT,nums,0,n-1,0);

        int [] lazyP=new int[4*n];

        for(int [] temp:q){
            int l=temp[0];
            int r=temp[1];
            int val=temp[2];
            // update query
            updateQuery(segT,lazyP,l,r,val,0,n-1,0);

            // return range sum
            System.out.println(rangeSum(segT,lazyP,l,r,0,n-1,0));
        }
    }

    public int rangeSum(int [] segT,int [] lazyP,int l,int r,int left,int right,int idx){
        if(lazyP[idx]!=0){
            segT[idx]+=(right-left+1)*lazyP[idx];
            if(left!=right){
                lazyP[idx*2+1]+=lazyP[idx];
                lazyP[idx*2+2]+=lazyP[idx];
            }
            lazyP[idx]=0;
        }

        if(l>right || r<left){
            return 0;
        }
        if(l<=left && r>=right){
            return segT[idx];
        }
        int mid=left+(right-left)/2;
        int lSum=rangeSum(segT,lazyP,l,r,left,mid,idx*2+1);
        int rSum=rangeSum(segT,lazyP,l,r,mid+1,right,idx*2+2);
        return lSum+rSum;
    }

    public void updateQuery(int [] segT,int [] lazyP,int l,int r,int val,int left,int right,int idx){
        if(lazyP[idx]!=0){
            segT[idx]+=(right-left+1)*lazyP[idx];
            if(left!=right){
                lazyP[idx*2+1]+=lazyP[idx];
                lazyP[idx*2+2]+=lazyP[idx];
            }
            lazyP[idx]=0;
        }
        if(l>right || r<left){
            return;
        }
        if(l<=left && r>=right){
            segT[idx]+=(right-left+1)*val;
            if(left!=right){
                lazyP[idx*2+1]+=val;
                lazyP[idx*2+2]+=val;
            }
            return;
        }
        int mid=left+(right-left)/2;
        updateQuery(segT,lazyP,l,r,val,left,mid,idx*2+1);
        updateQuery(segT,lazyP,l,r,val,mid+1,right,idx*2+2);

        segT[idx]=segT[idx*2+1]+segT[idx*2+2];
    }

    public void buildTree(int [] segT,int [] nums,int left,int right,int idx){
        if(left==right){
            segT[idx]=nums[left];
            return;
        }
        int mid=left+(right-left)/2;
        buildTree(segT,nums,left,mid,idx*2+1);
        buildTree(segT,nums,mid+1,right,idx*2+2);

        segT[idx]=segT[idx*2+1]+segT[idx*2+2];
    }
}
