import java.util.ArrayList;
import java.util.List;

public class GraphMatrix {

    private int v;
    private ArrayList<Integer>[] adjList;

    public GraphMatrix(int vertices) {
        this.v = vertices;

        initAdjList();
    }

    private void initAdjList() {

        adjList = new ArrayList[v];

        for(int i = 0; i < v; i++) {

            adjList[i] = new ArrayList<>();
        }
    }

    public void edge(int u, int v) {

        adjList[u].add(v);
    }

    public void Paths(int s, int d) {

        boolean[] bVisited = new boolean[v];
        ArrayList<Integer> aiPathList = new ArrayList<>();

        aiPathList.add(s);

        PathsUtil(s, d, bVisited, aiPathList);
    }

    private void PathsUtil(Integer u, Integer d, boolean[] bVisited, List<Integer> aiLocalPathList){

        bVisited[u] = true;

        if(u.equals(d)) {

            System.out.println(aiLocalPathList);
            bVisited[u] = false;
            return ;
        }

        for(Integer i : adjList[u]) {

            if(!bVisited[i]) {

                aiLocalPathList.add(i);
                PathsUtil(i, d, bVisited, aiLocalPathList);

                aiLocalPathList.remove(i);
            }
        }

        bVisited[u] = false;
    }

    public void Walk(int s, int d) {

        boolean[] bVisited = new boolean[v];
        ArrayList<Integer> aiPathList = new ArrayList<>();

        aiPathList.add(s);

        walkUtil(s, d, bVisited, aiPathList);
    }

    private void walkUtil(Integer u, Integer d, boolean[] bVisited, List<Integer> aiLocalPathList) {

        bVisited[u] = true;

        if(u.equals(d)) {

            System.out.println(aiLocalPathList);
            bVisited[u] = false;
            return ;
        }

        for(Integer i : adjList[u]) {

            if(!bVisited[i]) {

                aiLocalPathList.add(i);
                PathsUtil(i, d, bVisited, aiLocalPathList);
            }
        }

        bVisited[u] = false;
    }

    public void closedWalk(int s, int d) {

        boolean[] bVisited = new boolean[v];
        ArrayList<Integer> aiPathList = new ArrayList<>();

        aiPathList.add(s);

        ClosedWalkUtil(s, d, bVisited, aiPathList);
    }

    private void ClosedWalkUtil(Integer u, Integer d, boolean[] bVisited, List<Integer> aiLocalPathList) {

        bVisited[u] = true;

        if(u.equals(d)) {

            System.out.println(aiLocalPathList);
            bVisited[u] = false;
            return ;
        }

        for(Integer i : adjList[u]) {

            if(!bVisited[i]) {

                aiLocalPathList.add(i);
                ClosedWalkUtil(i, d, bVisited, aiLocalPathList);
            }
        }

        bVisited[u] = false;
    }



    public static void main(String[] args) {


        GraphMatrix oGraph = new GraphMatrix(4);
        int[][] aiMatrix = {{1, 2, 2, 1}, {2, 1, 2, 1}, {2, 2, 1, 2}, {1, 1, 1, 0}};

        for (int i = 0; i < 4; i++) {

            for(int j = 0; j < 4; j++) {

                if (aiMatrix[i][j] > 0) {

                    oGraph.edge(i, j);
                }
            }
        }

        int s = 2;
        int d = 3;

        System.out.println("The different paths from " + s + " to " + d + ":");
        oGraph.Paths(s, d);

        System.out.println("\nThe different trails from 1 to 2:");
        oGraph.Paths(1, 2);

        System.out.println("\nThe different walks from 1 to 2:");
        oGraph.Walk(1, 2);

        System.out.println("\nClosed Walk");
        oGraph.closedWalk(0, 0);



    }



}
