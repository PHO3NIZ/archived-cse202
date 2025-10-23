package Week2;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIFOLTRE {
    static StringBuilder sb = new StringBuilder();
    static int level = 0;

    public static void main(String[] args) {
        int n = ni();
        HashMap<String, Vertex> vertices = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String u = ns();
            if (!vertices.containsKey(u)) {
                vertices.put(u, new Vertex(u));
            }
            String v = ns();
            if (!vertices.containsKey(v)) {
                vertices.put(v, new Vertex(v));
            }
            vertices.get(u).addNeighbor(vertices.get(v));
            vertices.get(v).addNeighbor(vertices.get(u));
        }
        ArrayList<Vertex> verticesList = new ArrayList<>(vertices.values());
        for (int i = 0; i < n; i++) {
            Collections.sort(verticesList.get(i).neighbors, (v1, v2) -> v1.name.compareToIgnoreCase(v2.name));
        }
        String start = ns();
        dfs(vertices.get(start), level);
        System.out.println(sb);
    }

    public static void dfs(Vertex v, int level) {
        v.isDiscover = true;
        sb.append("-");
        for (int i = 0; i < level; i++) {
            sb.append("---");
        }
        sb.append(v.name).append("\n");
        for (Vertex w : v.neighbors) {
            if (!w.isDiscover) {
                dfs(w, level + 1);
            }
        }
    }

    static class Vertex {
        String name;
        boolean isDiscover = false;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(String name) {
            this.name = name;
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
