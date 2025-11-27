package Week7;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIMAXHTR {

    public static void main(String[] args) {
        int n = ni();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = ni();
            int v = ni();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        dfs(vertices[0], 0);
        int tempFarthestVertex = farthestVertex;
        for (Vertex vertex : vertices) {
            vertex.isDiscover = false;
        }
        maxHeight = -1;
        farthestVertex = Integer.MAX_VALUE;
        dfs(vertices[tempFarthestVertex], 0);
        System.out.println(Math.min(farthestVertex, tempFarthestVertex) + " " + maxHeight);
    }

    static int maxHeight = -1;
    static int farthestVertex = Integer.MAX_VALUE;

    public static void dfs(Vertex v, int h) {
        v.isDiscover = true;
        if (h > maxHeight || (h == maxHeight && v.id < farthestVertex)) {
            maxHeight = h;
            farthestVertex = v.id;
        }
        for (Vertex w : v.neighbors) {
            if (!w.isDiscover) {
                dfs(w, h + 1);
            }
        }
    }

    static class Vertex {
        int id;
        boolean isDiscover = false;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
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
