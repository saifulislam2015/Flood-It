import java.util.ArrayList;

/**
 * Created by ASUS on 05-Oct-17.
 */
public class Node implements Comparable<Node>{
    public int size;
    public int grid[][];
    public int color;
    public  Node parent;
    public  int f,g,h;
    public boolean flag;
    public int first;
    //public String s;
    //public ArrayList


    @Override
    public int compareTo(Node o) {
        return this.f-o.f;
    }

    public Node(int size){
        this.size = size;
        flag = false;
        first = 0;
    }

    public Node(int n, int mat[][], int c, Node p, int g){
        this.size = n;
        this.grid = mat;
        this.color = c;
        this.parent = p;
        this.g = g;

    }

    public Node(int n,int mat[][],int c,Node p,int g,int h){
        this.size = n;
        this.grid = mat;
        this.color = c;
        this.parent = p;
        this.g = g;
        this.h = h;
        this.f = g+h;
    }

    public String  makeString(){
        String s = null;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                s+=Integer.toString(grid[i][j]);
            }
        }
        return s;
    }

    public boolean isGoal(){
        int col = grid[0][0];

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(grid[i][j]!=col) return false;
            }
        }
        return true;
    }

    public int[][] ColorRegion(int x,int y,int nc,int oc){
        if(x<0 || x>=size || y<0 || y>=size)
            //return 0;
            System.out.println("No such place");
        if(grid[x][y] != oc)
            //return 0;
            System.out.println("Color mismatch");

        grid[x][y] = nc;

        return grid;
    }

    public ArrayList getNeighbors(){
        ArrayList<Node> nb=new ArrayList<Node>();
        boolean[] found = new boolean[7];

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
                found[grid[i][j]] = true;
        }

        for(int k=1;k<7;k++){
            if(!found[k]) continue;
            if(k==grid[0][0]) continue;

            Node t = new Node(this.size);
            int src = grid[0][0];
            t.grid = ColorRegion(0,0,k,src);
            t.color = k;
            t.parent = this;
            t.g = this.g +1;
            //Heuristic hr = new Heuristic(grid,size);
            //t.h = hr.getHeuristic();
            //Heuristic2 hr2 = new Heuristic2(grid,size);
            //t.h = hr2.DFS();
            Heuristic3 hr3 = new Heuristic3(grid,size);
            t.h = hr3.getHeuristic();
            t.f = t.g+t.h;
            t.flag = true;
            //t.h
            //t.f
            nb.add(t);
        }
        return nb;
    }
}
