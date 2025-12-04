package Week8;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIUWBT {
    public static void main(String[] args) {
        int n = ni();
        long totalWeight = 0;
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            long w = nl();
            vertices[i] = new Vertex(i, w);
            totalWeight += w;
        }
        for (int i = 0; i < n - 1; i++) {
            int u = ni();
            int v = ni();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        dfs(vertices[1]);

        long bestDiff = Long.MAX_VALUE;
        int bestID = -1;
        long bestLeftWeight = -1;
        long bestRightWeight = -1;

        for (int i = 1; i <= n; i++) {
            if (vertices[i].neighbors.size() == 2) {
                Vertex left = vertices[i].neighbors.get(0);
                Vertex right = vertices[i].neighbors.get(1);
                long leftWeight;
                long rightWeight;
                if (left == vertices[i].parent) {
                    leftWeight = totalWeight - vertices[i].totalWeight;
                } else {
                    leftWeight = left.totalWeight;
                }
                if (right == vertices[i].parent) {
                    rightWeight = totalWeight - vertices[i].totalWeight;
                } else {
                    rightWeight = right.totalWeight;
                }
                long diff = Math.abs(leftWeight - rightWeight);
                if (diff < bestDiff) {
                    bestDiff = diff;
                    bestID = i;
                    bestLeftWeight = leftWeight;
                    bestRightWeight = rightWeight;
                }
            }
        }
        if (bestID == -1) {
            System.out.println(-1);
        } else {
            System.out.println(bestID + " " + Math.min(bestLeftWeight, bestRightWeight) + " "
                    + Math.max(bestLeftWeight, bestRightWeight));
        }
    }

    public static void dfs(Vertex v) {
        v.totalWeight = v.weight;
        v.isDiscover = true;
        for (Vertex w : v.neighbors) {
            if (!w.isDiscover) {
                w.parent = v;
                dfs(w);
                v.totalWeight += w.totalWeight;
            }
        }
    }

    static class Vertex {
        int id;
        boolean isDiscover = false;
        long weight;
        long totalWeight;
        Vertex parent;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, long w) {
            this.id = id;
            this.weight = w;
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
