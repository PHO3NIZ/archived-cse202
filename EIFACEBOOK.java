import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIFACEBOOK {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int n = ni();
        int m = ni();
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            String gender = ns();
            vertices[i] = new Vertex(i, gender);
        }
        for (int i = 0; i < m; i++) {
            int u = ni();
            int v = ni();
            if (!vertices[u].neighbors.contains(vertices[v])) {
                vertices[u].addNeighbor(vertices[v]);
                vertices[v].addNeighbor(vertices[u]);
            }
        }
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (Vertex vertex : vertices[i].neighbors) {
                if (!vertices[i].gender.equals(vertex.gender)) {
                    count++;
                }
            }
            sb.append(count + " ");
        }
        System.out.println(sb);
    }

    static class Vertex {
        int id;
        String gender;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
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
