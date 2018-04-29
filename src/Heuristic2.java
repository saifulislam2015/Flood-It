/**
 * Created by ASUS on 13-Oct-17.
 */
public class Heuristic2 {
    public int grid[][];
    public int dimension;
    public int visited[][];

    public Heuristic2(int g[][],int d){
        this.grid = g;
        this.dimension = d;
        visited = new int[d][d];
    }

    public int DFS(){
        int h = 0;

        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(visited[i][j]==0){
                    h++;
                    DFS_visit(i,j);
                }
            }
        }
        return h;
    }

    public void DFS_visit(int i,int j){
        visited[i][j] = 1 ;
        visit_neighbors(i,j);
    }

    public void visit_neighbors(int i,int j){
        if(i>0){
            if(visited[i-1][j]==0 && grid[i][j]==grid[i-1][j])
                DFS_visit(i-1,j);
        }
        if(i<dimension-1){
            if(visited[i+1][j]==0 && grid[i][j]==grid[i+1][j])
                DFS_visit(i+1,j);
        }
        if(j>0){
            if(visited[i][j-1]==0 && grid[i][j]==grid[i][j-1])
                DFS_visit(i,j-1);
        }
        if(j<dimension-1){
            if(visited[i][j+1]==0 && grid[i][j]==grid[i][j+1])
                DFS_visit(i,j+1);
        }
    }


}
