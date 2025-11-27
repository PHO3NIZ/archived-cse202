package Week7;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIUSEFI2 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = ni();
        HashMap<String, Vertex> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String u = ns();
            if (!map.containsKey(u)) {
                map.put(u, new Vertex(u));
            }
            String v = ns();
            if (!map.containsKey(v)) {
                map.put(v, new Vertex(v));
            }
            map.get(u).addNeighbor(map.get(v));
            map.get(v).addNeighbor(map.get(u));
        }
        ArrayList<String> list = new ArrayList<>(map.keySet());
        for (int i = 0; i < n; i++) {
            Collections.sort(map.get(list.get(i)).neighbors, (v1, v2) -> v1.id.compareToIgnoreCase(v2.id));
        }
        for (String s : list) {
            Vertex v = map.get(s);
            if (v.neighbors.size() == 1) {
                v.isFile = true;
            } else {
                v.isFolder = true;
            }
        }
        String root = ns();
        String keyword = ns();
        dfs(map.get(root), keyword);
        System.out.println(sb);
    }

    public static void dfs(Vertex v, String keyword) {
        v.isDiscover = true;
        for (Vertex w : v.neighbors) {
            if (!w.isDiscover) {
                dfs(w, keyword);
                if (w.isFile && w.id.contains(keyword)) {
                    v.keywordCount++;
                } else if (w.isFolder) {
                    v.keywordCount += w.keywordCount;
                }
            }
        }
        if (v.keywordCount > 0) {
            sb.append(v.id).append(" ").append(v.keywordCount).append("\n");
        }
    }

    static class Vertex {
        String id;
        boolean isDiscover = false;
        boolean isFile = false;
        boolean isFolder = false;
        int keywordCount = 0;
        ArrayList<Vertex> neighbors = new ArrayList<>();

        public Vertex(String id) {
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
