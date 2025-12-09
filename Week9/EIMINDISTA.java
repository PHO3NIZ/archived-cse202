package Week9;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIMINDISTA {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int n = ni();
        int m = ni();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = ni();
            int v = ni();
            int l = ni();
            vertices[u].addEdge(new Edge(vertices[v], l));
            vertices[v].addEdge(new Edge(vertices[u], l));
        }
        dijkstra(vertices);
        for (int i = 1; i < n; i++) {
            sb.append(((vertices[i].distance != Integer.MAX_VALUE) ? vertices[i].distance : "-1")  + " ");
        }
        System.out.println(sb);
    }

    private static void dijkstra(Vertex[] vertices) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.distance, v2.distance));
        vertices[0].distance = 0;
        queue.add(vertices[0]);

        while (!queue.isEmpty()) {
            Vertex minVertex = queue.remove();
            if (minVertex.isDiscover)
                continue;
            minVertex.isDiscover = true;

            Vertex orginVertex = vertices[minVertex.id];
            for (Edge e : orginVertex.edges) {
                Vertex v = e.endPoint;
                int w = e.weight;
                if (v.isDiscover == false && v.distance > orginVertex.distance + w) {
                    v.distance = orginVertex.distance + w;
                    v.previous = orginVertex;

                    Vertex clone = new Vertex(v.id);
                    clone.distance = v.distance;

                    queue.add(clone);
                }
            }
        }

    }

    static class Vertex {
        int id;
        boolean isDiscover = false;
        int distance = Integer.MAX_VALUE;
        Vertex previous = null;
        ArrayList<Edge> edges = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }
    }

    static class Edge {
        int weight;
        Vertex endPoint;

        public Edge(Vertex v, int w) {
            this.endPoint = v;
            this.weight = w;
        }
    }

    static InputStream is = System.in;
    static byte[] inbuf = new byte[1 << 24];
    static int lenbuf = 0, ptrbuf = 0;

    static int readByte() {
        if (lenbuf == -1)
            throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return -1;
        }
        return inbuf[ptrbuf++];
    }

    static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b))
            ;
        return b;
    }

    static double nd() {
        return Double.parseDouble(ns());
    }

    static char nc() {
        return (char) skip();
    }

    static String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    static char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    static int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    static long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
}
