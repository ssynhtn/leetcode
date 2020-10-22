package com.ssynhtn.challenge;

import java.util.ArrayDeque;
import java.util.Arrays;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int x : asteroids) {
            if (x > 0) {
//                System.out.println("appending " + x);
                stack.addLast(x);
            } else {
                boolean xDestroyed = false;
                while (!stack.isEmpty() && stack.peekLast() > 0) {
                    if (stack.peekLast() > -x) {
                        xDestroyed = true;
                        break;
                    } else if (stack.removeLast() == -x) {
                        xDestroyed = true;
                        break;
                    }
                }

                if (!xDestroyed) {
                    stack.addLast(x);
//                    System.out.println("add " + x);
                }


            }
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.removeLast();
        }
        return res;
    }

    public int[] asteroidCollissionSelf(int[] asteroids) {
        int end = 0;
        for (int i = 0; i < asteroids.length; i++) {
            int x = asteroids[i];

            if (x > 0) {
                asteroids[end++] = x;
            } else {
                boolean xDestroyed = false;
                while (end > 0 && asteroids[end - 1] > 0) {
                    if (asteroids[end - 1] > -x) {
                        xDestroyed = true;
                        break;
                    } else if (asteroids[--end] == -x) {
                        xDestroyed = true;
                        break;
                    }
                }

                if (!xDestroyed) {
                    asteroids[end++] = x;
//                    System.out.println("add " + x);
                }


            }
        }

        if (end == asteroids.length) return asteroids;
        return Arrays.copyOf(asteroids, end);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AsteroidCollision().asteroidCollissionSelf(new int[]{-2,-2,1,-2})));
    }
}
