import java.util.*;

public class EICON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        Vertex[] vertices = new Vertex[n + 1];
        
        // Init vertices
        for(int i = 1; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        // Add adjacent vertex into a vertex
        for(int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[v].addNeighbor(vertices[u]);
        }

        // Query and output
        for(int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (vertices[a].neighbors.contains(vertices[b])) {
                sb.append("Y\n");
            }
            else {
                sb.append("N\n");
            }
        }
        System.out.println(sb);
    }

    static class Vertex {
        int id;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }
        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }
    }
}
