package Week5;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIUEASPOST {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = ni();
        Node[] nodes = readTree(n);
        printPostOrder(nodes[0]);
        System.out.println(sb);
    }

    public static void printPostOrder(Node v) {
        if (v.left != null) {
            printPostOrder(v.left);
        }
        if (v.right != null) {
            printPostOrder(v.right);
        }
        sb.append(v.id + " ");
    }

    public static Node[] readTree(int n) {
        Node[] nodes = new Node[n];
        for (var i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (var i = 0; i < n; i++) {
            var leftIndex = ni();
            nodes[i].left = leftIndex > 0 ? nodes[leftIndex - 1] : null;
            var rightIndex = ni();
            nodes[i].right = rightIndex > 0 ? nodes[rightIndex - 1] : null;
        }
        return nodes;
    }

    static class Node {
        public int id;
        public Node left;
        public Node right;

        public Node(int id) {
            this.id = id;
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
