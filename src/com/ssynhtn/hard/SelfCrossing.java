package com.ssynhtn.hard;

public class SelfCrossing {
    
// a b c d, e, f, g

// (0,0)
// a:(0,a)
// b:(-b,a)
// c:(-b,a-c)
// d:(-b+d,a-c)
// e:(-b+d,a-c+e)
// f:(-b+d-f,a-c+e)
// g:(-b+d-f,a-c+e-g)


// (0,0), (0,a)


// (a,d):
// (-b, a-c), (-b+d, a-c)
// b <= d
// c <= a

// (a, e)
// (-b+d, a-c), (-b+d, a-c+e)
// b == d
// c<=a+e

// (a, f)
// (-b+d,a-c+e), (-b+d-f,a-c+e)
// b<=d<=b+f
// e<=c<=a+e

// (a, g)
// (-b+d-f,a-c+e), (-b+d-f,a-c+e-g)
// d==b+f
// e<=c+g
// c<=a+e
    public boolean isSelfCrossing(int[] x) {
        int n = x.length;
        for (int j = 0; j < n; j++) {
            int i = j;
            if (n - i < 4) continue;
            
            int a = x[i++];
            int b = x[i++];
            int c = x[i++];
            int d = x[i++];

            if (b <= d && c <= a) return true;
            if (i == n) continue;
            int e = x[i++];
            if (b == d && c <= a + e) return true;
            if (i == n) continue;
            int f = x[i++];

            if (b <= d && d <= b+f && e <= c && c <= a+e) return true;
            // if (i == n) continue;
            // int g = x[i++];
            // if (d == b + f && e <= c + g && c <= a + e) return true;
            
        }
        
        return false;
        
    }
}