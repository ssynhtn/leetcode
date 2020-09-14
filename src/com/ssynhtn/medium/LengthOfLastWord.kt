package com.ssynhtn.medium

class LengthOfLastWord {
    fun lengthOfLastWord(s: String): Int {
        var end = s.length - 1
        while (end >= 0 && s[end] == ' ') {
            end--
        }

        if (end < 0) {
            return 0
        }

        var start = end
        while (start >= 0 && s[start] != ' ') {
            start--
        }

        return end - start
//
//        if (start < 0) {
//            return end + 1
//        } else {
//            end - start
//        }


    }
}