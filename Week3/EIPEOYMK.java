package Week3;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIPEOYMK {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = ni();
        int m = ni();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = ni();
            int v = ni();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        for (int i = 0; i < n; i++) {
            Collections.sort(vertices[i].neighbors, (v1, v2) -> v1.id - v2.id);
        }
        int u = ni();
        bfs(vertices[u]);
        int q = ni();
        for (int i = 0; i < q; i++) {
            int k = ni();
            boolean flag = false;
            for (int j = 0; j < vertices.length; j++) {
                if (vertices[j].distance == k) {
                    sb.append(vertices[j].id + " ");
                    flag = true;
                }
            }
            if (!flag) {
                sb.append("-1");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<Vertex>();
        q.add(v);
        v.isDiscover = true;
        while (!q.isEmpty()) {
            Vertex w = q.poll();
            for (Vertex x : w.neighbors) {
                if (!x.isDiscover) {
                    x.isDiscover = true;
                    x.distance = w.distance + 1;
                    q.add(x);
                }
            }
        }
    }

    static class Vertex {
        int id;
        boolean isDiscover = false;
        int distance = 0;
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
