import java.util.HashMap;


/**
 * Created by ASUS on 11-Oct-17.
 */
public class vertex implements Comparable<vertex>{

    public int distance;
    public int x;
    public int y;
    public HashMap<vertex,Integer> adj;

    @Override
    public int compareTo(vertex o) {
        return this.distance - o.distance;
    }

    public vertex(int x,int y){
        this.x = x;
        this.y = y;
        adj = new HashMap<vertex,Integer>();
        distance = 9999999;
    }
}
