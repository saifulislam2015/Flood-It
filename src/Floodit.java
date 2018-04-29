/**
 * Created by ASUS on 04-Oct-17.
 */
import java.util.*;
import java.io.*;


public class Floodit {
    public static int turns;
    public static Node start;
    public static Node goal;


    public Floodit(Node n){
        this.start = n;
        turns = 0;
    }
    public static Node Play(){
            ArrayList<Node> pq = new ArrayList<Node>();
            ArrayList<Node> list = new ArrayList<Node>();
            ArrayList<Node> nb = new ArrayList<Node>() ;

            pq.add(start);

            while (!pq.isEmpty()){
                Collections.sort(pq);
                Node n = pq.remove(0);

                if(n.isGoal()) {
                    goal=n;
                    //break;
                    return goal;
                }
                list.add(n);
                nb = n.getNeighbors();
                /*for (Node b: nb){
                    b.parent = n;
                }*/

                for (Node a:nb) {
                    if(list.contains(a)) continue;
                    else if(pq.contains(a)){
                        String str = a.makeString();
                        for(int i=0;i<pq.size();i++){
                            String s = pq.get(i).makeString();
                            if(s.equals(str)){
                                if(a.f<pq.get(i).f)
                                    a.f = pq.get(i).f;
                                    a.parent = n;
                            }
                        }
                    }
                    else pq.add(a);
                    
                }

            }
        return null;
    }


    public static void ShowNode(Node n){
        for(int i=0;i<n.size;i++){
            for (int j=0;j<n.size;j++){
                System.out.print(n.grid[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void printResult(Node n){
        if(n==null) return;
        if(n.flag==false){
            ShowNode(n);
        }
        turns++;
        printResult(n.parent);
        System.out.println("Choose color :"+ n.color);
        ShowNode(n);
    }

    public static void main(String[] args) {
        try{
            //System.out.println(new File("input.txt").getAbsolutePath());

            Scanner input = new Scanner(new File("./input.txt"));
            int n;
            //System.out.println("Here");

            while (input.hasNextInt() && (n = input.nextInt()) > 0) {
                //System.out.println("here");
                    int[][] grid = new int[n][n];

                    for (int row = 0; row < n; row++) {
                        for (int column = 0; column < n && input.hasNextInt(); column++) {
                            grid[row][column] = input.nextInt();
                            //vertex v = new vertex(row,column);
                            //System.out.printf(" %d    ", grid[row][column]);
                        }
                        //System.out.println();
                    }
                    start= new Node(n,grid,grid[0][0],null,0);
                    //start.parent = start;
                    start.f = 0;
                    start.h = 0;
                    start.flag = false;
                    //Heuristic hr = new Heuristic(grid,n);
                    //start.f = start.g+ hr.getHeuristic();
                    Floodit game = new Floodit(start);
                    game.Play();
                    Node t = game.Play();
                    game.printResult(t);
                    System.out.println("turns: "+ game.turns);
                }
                input.close();

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }

    }
}
