package com.ssynhtn.hard;


public class Histogram {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int[] hist = new int[matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    hist[j] = 0;
                } else {
                    hist[j]++;
                }
            }

            max = Math.max(max, largestRectangleArea(hist));
        }

        return max;
    }

    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length);
    }

    private int largestRectangleArea(int[] heights, int start, int end) {
        int n = end - start;
        if (n == 0) return 0;
        if (n == 1) return heights[start];
        int mid = start + (end - start) / 2;
        int maxArea = largestRectangleArea(heights, start, mid);
        maxArea = Math.max(maxArea, largestRectangleArea(heights, mid, end));
        int min = Math.min(heights[mid-1], heights[mid]);
        int i = mid - 2;
        int j = mid + 1;

        maxArea = Math.max(maxArea, min * 2);
        while (i >= start || j < end) {
            while (i >= start && heights[i] >= min) {
                i--;
            }

            while (j < end && heights[j] >= min) {
                j++;
            }

            maxArea = Math.max(maxArea, min * (j-i-1));

            if (i >= start && (j >= end || heights[i] >= heights[j])) {
                min = heights[i--];
                maxArea = Math.max(maxArea, min * (j-i-1));
            } else if (j < end) {
                min = heights[j++];
                maxArea = Math.max(maxArea, min * (j-i-1));
            }
        }
//        while (i >= start && j < end) {
//            if (heights[i] >= heights[j]) {
//                min = Math.min(min, heights[i--]);
//            } else {
//                min = Math.min(min, heights[j++]);
//            }
//            width++;
//            maxArea = Math.max(maxArea, min * width);
//        }
//        while (i >= start) {
//            min = Math.min(min, heights[i--]);
//            width++;
//            maxArea = Math.max(maxArea, min * width);
//        }
//
//        while (j < end) {
//            min = Math.min(min, heights[j++]);
//            width++;
//            maxArea = Math.max(maxArea, min * width);
//        }


        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new Histogram().largestRectangleArea(new int[]{4,2,0,3,2,4,3,4}));
    }

}
