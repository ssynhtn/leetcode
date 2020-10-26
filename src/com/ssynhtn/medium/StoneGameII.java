package com.ssynhtn.medium;

public class StoneGameII {
//    2,7,9,4,4
//
//    M = 1
//
//    n <= 100
//
//    i = 0, M = 1
//    i = 1, M = 1
//    i = 2, M <= 2
//    i = 3, M = 1, 2
//    i = 4, M <=2
//    i = 5, M <= 3, 2 + 3
//    i = 6, M <= 4: 2 + 4
//
//            2 + 4
//
//    M = max(takes)
//
//[i, n-1]
//
//            0<=i <=n-1
//            1<=M<=n-1
//
//    dp[][]
//
//            i, M
//
//    剩余 n - i个
//            最多拿2M
//
//    take = 1  to min(n-i, 2M)
//    e = i + 1 to min(n, 2M+i)
//
//    dp[i, M] =
//    max {
//        sum(i, i+1) - dp[i+1, max(M, 1)]
//        sum(i, i+2) - dp(i+2, max(M, 2))
//...
//        sum(i, min(n, 2M+i)) - dp(min(n, 2M+i), max(M, min(n, 2M)))
//    }
//
//
//    dp i relies on dp i+
//
//    as for M
//

}
