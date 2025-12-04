package Week8;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class EIMULEMA {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int n = ni();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            long c = nl();
            vertices[i] = new Vertex(i);
            vertices[i].commission = (long) (c * 0.15);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = ni();
            int v = ni();
            vertices[u].addNeighbor(vertices[v]);
        }
        dfs(vertices[0]);
        for (Vertex vertex : vertices) {
            sb.append(vertex.id).append(" ").append(vertex.commission).append("\n");
        }
        System.out.println(sb);
    }

    static class Vertex {
        int id;
        boolean isDiscover = false;
        long commission;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }
    }

    public static void dfs(Vertex v) {
        v.isDiscover = true;
        for (Vertex w : v.neighbors) {
            if (!w.isDiscover) {
                dfs(w);
                v.commission += (long) (w.commission / 2);
            }
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
