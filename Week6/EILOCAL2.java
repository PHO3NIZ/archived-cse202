package Week6;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EILOCAL2 {
    public static void main(String[] args) {
        int n = ni();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = ni();
            int v = ni();
            int w = ni();
            vertices[u].edges.add(new Edge(vertices[v], w));
            vertices[v].edges.add(new Edge(vertices[u], w));
        }
        dfs(vertices[0]);
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (vertices[i].lengthFromRoot > max) {
                max = vertices[i].lengthFromRoot;
            }
        }
        System.out.println(max);
    }

    public static void dfs(Vertex v) {
        v.isDiscover = true;
        for (Edge e : v.edges) {
            if (!e.endVertex.isDiscover) {
                e.endVertex.lengthFromRoot = v.lengthFromRoot + e.weight;
                dfs(e.endVertex);
            }
        }
    }

    static class Vertex {
        int id;
        boolean isDiscover = false;
        int lengthFromRoot = 0;
        ArrayList<Edge> edges = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {
        Vertex endVertex;
        int weight;

        public Edge(Vertex endVertex, int weight) {
            this.endVertex = endVertex;
            this.weight = weight;
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
