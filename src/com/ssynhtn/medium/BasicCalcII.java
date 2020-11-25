package com.ssynhtn.medium;

public class BasicCalcII {
    char[] chs;
    int n;
    int i;


    void skipSpaces() {
        while (i < n && chs[i] == ' ') {
            i++;
        }
    }


    public int calculate(String s) {
        chs = s.toCharArray();
        n = chs.length;
        char ch;

        int x = 0;
        char op = '+';

        while (i < n) {
            skipSpaces();
            if (i == n) break;

            int y = chs[i++] - '0';
            while (i < n && (ch = chs[i]) >= '0' && ch <= '9') {
                y = y * 10 + ch - '0';
                i++;
            }

            skipSpaces();
            if (i == n) {
                x = op == '+' ? x + y : x - y;
                break;
            }

            char nextOp = chs[i++];
            if (nextOp == '+' || nextOp == '-') {
                x = op == '+' ? x + y : x - y;
                op = nextOp;
                continue;
            }

            // nextOp == * or /
            while (i < n) {
                skipSpaces();
                // expect a number
                int z = chs[i++] - '0';
                while (i < n && (ch = chs[i]) >= '0' && ch <= '9') {
                    z = z * 10 + ch - '0';
                    i++;
                }

                y = nextOp == '*' ? y * z : y / z;
                skipSpaces();

                if (i < n) {
                    nextOp = chs[i++];
                    if (nextOp == '+' || nextOp == '-') {
                        x = op == '+' ? x + y : x - y;
                        op = nextOp;
                        break;
                    }
                } else {
                    x = op == '+' ? x + y : x - y;
                }
            }

        }


        return x;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalcII().calculate(" 3+5 / 2 "));
    }
}
