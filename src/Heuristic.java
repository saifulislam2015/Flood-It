import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by ASUS on 11-Oct-17.
 */
public class Heuristic{

    int grid[][];
    int dimension;
    ArrayList<vertex> g = new ArrayList<vertex>();


    public  Heuristic(int g[][],int d){
            this.grid = g;
            this.dimension = d;
    }

    public vertex isValid(int i,int j){
            if(i<0 || j<0 || i>=dimension || j>=dimension)
                return null;
            for(vertex u:g){
                if(u.x==i && u.y==j) {
                    //System.out.println(i);
                    return u;
                }
            }
            return null;
    }

    public void addEdge(int x,int y,vertex u){

        vertex v = isValid(x,y);
        if(grid[u.x][u.y]==grid[v.x][v.y]){
            u.adj.put(v,0);
        }
        else u.adj.put(v,1);
    }

    public void GraphCreation(){
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                vertex v;
                v = new vertex(i,j);
                g.add(v);
            }

            for (vertex u:g){

                if(u.x>0){
                    addEdge(u.x-1,u.y,u);
                }
                if (u.y>0){
                    addEdge(u.x,u.y-1,u);
                }
                if(u.x<dimension-1){
                    vertex v2 = isValid(u.x+1,u.y);
                    if(v2!=null){
                        if(grid[u.x][u.y]==grid[v2.x][v2.y]){
                            u.adj.put(v2,0);
                        }
                        else u.adj.put(v2,1);
                    }
                }
                if(u.y<dimension-1){
                    addEdge(u.x,u.y+1,u);
                }


            }
        }
    }

    public void dijkstra(){
        GraphCreation();
        ArrayList<vertex> pq = new ArrayList<vertex>();
        vertex source = isValid(0,0);
        source.distance = 0;

        pq.addAll(g);

        while (!g.isEmpty()){
            Collections.sort(g);
            vertex v = pq.remove(0);

            for(HashMap.Entry<vertex,Integer> t:v.adj.entrySet()){
                if(t.getKey().distance > t.getKey().distance+v.distance){
                    t.getKey().distance = t.getKey().distance + v.distance;
                }
            }

        }

    }



    public int getHeuristic(){
        int h= 0;

        dijkstra();
        for(vertex v:g){
            if(h<v.distance) h=v.distance;
        }
        return h;
    }
}
