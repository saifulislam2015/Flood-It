/**
 * Created by ASUS on 13-Oct-17.
 */
public class Heuristic3 {
    public int grid[][];
    public int dimension;

    public Heuristic3(int g[][],int d){
        this.grid= g;
        this.dimension = d;
    }

    public int getHeuristic(){
        boolean[] visited = new boolean[7];
        int h=0;
        for(int i=0;i<7;i++)
            visited[i]=false;

        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(visited[grid[i][j]]==false) visited[grid[i][j]]=true;
            }
        }

        for(int i=0;i<7;i++){
            if(visited[i]==true) h++;
        }

        return h;
    }
}
