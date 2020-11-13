package com.ssynhtn.medium;

import com.ssynhtn.hard.RemoveMinParen;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {
    public static void main(String[] args) {
        List<String> res = new RemoveComments().removeComments(new String[]{
                "main() { ", "  int a = 1; /* Its comments here ", "", "  ", "  */ return 0;", "} "
        });

        for (String s : res) {
            System.out.print("\"" + s + "\",");
        }
    }

    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        final int n = source.length;
        if (n == 0) return res;

        int i = 0;
        int j = 0;
        char[] buffer = source[0].toCharArray();
        int m = buffer.length;
        StringBuilder sb = new StringBuilder();

        while (i < n) {
            if (j == m) {
                i++;
                if (i < n) {
                    buffer = source[i].toCharArray();
                    m = buffer.length;
                    j = 0;
                }
                continue;
            }

            if (j == m - 1) {
                sb.append(buffer[j]);
                res.add(sb.toString());
                sb.delete(0, sb.length());
                i++;
                if (i < n) {
                    buffer = source[i].toCharArray();
                    m = buffer.length;
                    j = 0;
                }
                continue;
            }

            if (buffer[j] == '/' && buffer[j+1] == '/') {
                if (sb.length() > 0) {
                    res.add(sb.toString());
                    sb.delete(0, sb.length());
                }
                i++;
                if (i < n) {
                    buffer = source[i].toCharArray();
                    m = buffer.length;
                    j = 0;
                }
                continue;
            }

            if (buffer[j] == '/' && buffer[j+1] == '*') {
                j+=2;
                if (j == m) {
                    i++;
                    if (i < n) {
                        buffer = source[i].toCharArray();
                        m = buffer.length;
                        j = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }

                while (i < n) {
                    if (j == m) {
                        i++;
                        if (i < n) {
                            buffer = source[i].toCharArray();
                            m = buffer.length;
                            j = 0;
                        }
                        continue;
                    }

                    if (j == m - 1) {
                        i++;
                        if (i < n) {
                            buffer = source[i].toCharArray();
                            m = buffer.length;
                            j = 0;
                        }
                        continue;
                    }

//                    System.out.println("j " + j + ", m " + m);
                    if (buffer[j] == '*' && buffer[j+1] == '/') {
                        if (j == m - 2) {
                            if (sb.length() > 0) {
                                res.add(sb.toString());
                                sb.delete(0, sb.length());
                            }

                            i++;
                            if (i<n) {
                                buffer = source[i].toCharArray();
                                m = buffer.length;
                                j = 0;
                            }
                        } else {
                            j+=2;
                        }

                        break;

                    } else {
                        j++;
                    }
                }

            } else {
                sb.append(buffer[j++]);
            }
        }

        return res;
    }
}
