package com.ssynhtn.contest;

public class SplitTwoStringstoMakePalindrome {
    public boolean checkPalindromeFormation(String a, String b) {
        return checkPalindrome(a, b) || checkPalindrome(b, a);
    }

    private boolean checkPalindrome(String a, String b) {
        int i = 0;
        int j = b.length() - 1;

        while (i < j) {
            if (isPalindrome(a, i, j)) {
                System.out.println(a + " splitting at " + j + " forms " + a.substring(0, j + 1) + b.substring(j + 1));
                return true;
            }
            if (isPalindrome(b, i, j)) {
                System.out.println(a + " split at " + i + " forms " + a.substring(0, i) + b.substring(i));
                return true;
            }
            if (a.charAt(i) != b.charAt(j)) return false;
            i++;
            j--;
        }

        System.out.println(a + " splits at " + i + " to form " + a.substring(0, i + 1) + b.substring(i + 1));
        return true;
    }

    private boolean isPalindrome(String a, int i, int j) {
        while (i < j) {
            if (a.charAt(i) != a.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SplitTwoStringstoMakePalindrome().checkPalindromeFormation("abdef", "fecab"));
    }
}
