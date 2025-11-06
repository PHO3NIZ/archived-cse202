package Week4;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIBIRTHDAY {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = ni();
        int m = ni();
        int d = ni();
        int k = ni();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            int dob = ni();
            vertices[i] = new Vertex(i, dob);
            vertices[i].hasGift = hasGift(vertices[i], d, k);
        }
        for (int i = 0; i < m; i++) {
            int u = ni();
            int v = ni();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (Vertex vertex : vertices[i].neighbors) {
                if (vertex.hasGift) {
                    count++;
                }
            }
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }

    public static boolean hasGift(Vertex v, int d, int k) {
        if (d + k <= 365 && (d <= v.dayOfBirth && v.dayOfBirth <= d + k)) {
            return true;
        } else if (d + k > 365 && (d <= v.dayOfBirth || v.dayOfBirth <= d + k - 365)) {
            return true;
        }
        return false;
    }

    static class Vertex {
        int id;
        boolean hasGift = false;
        int dayOfBirth;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, int dob) {
            this.id = id;
            this.dayOfBirth = dob;
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
