package com.ssynhtn.hard;

public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        char[] chs = str1.toCharArray();
        char[] dhs = str2.toCharArray();
        int n = chs.length;
        int m = dhs.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if (chs[i] == dhs[j]) {
                    dp[i][j] = dp[i+1][j+1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
//        findCommon(chs, dhs, dp, 0, 0);

        int len = m + n - dp[0][0];
        char[] buff = new char[len];
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (chs[i] == dhs[j]) {
                buff[k++]= chs[i];
                i++;
                j++;
            } else {
                if (dp[i][j] == dp[i][j+1]) {
                    // skip dhs[j]
                    buff[k++] = dhs[j++];
                } else {
                    buff[k++] = chs[i++];
                }
            }
        }

        while (i < n) {
            buff[k++] = chs[i++];
        }
        while (j < m) {
            buff[k++] = dhs[j++];
        }


//        System.out.println("used k " + k + ", re use count " + reuseCount);
        return new String(buff, 0, len);
    }

    private int findCommon(char[] chs, char[] dhs, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];
        if (i == chs.length || j == dhs.length) {
            dp[i][j] = 1;
            return 1;
        }

        if (chs[i] == dhs[j]) {
            dp[i][j] = findCommon(chs, dhs, dp, i+1, j+1) + 1;
        } else {
            dp[i][j] = Math.max(findCommon(chs, dhs, dp, i + 1, j), findCommon(chs, dhs, dp, i, j + 1));
        }

        return dp[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new ShortestCommonSupersequence().shortestCommonSupersequence(
                "atdznrqfwlfbcqkezrltzyeqvqemikzgghxkzenhtapwrmrovwtpzzsyiwongllqmvptwammerobtgmkpowndejvbuwbporfyroknrjoekdgqqlgzxiisweeegxajqlradgcciavbpgqjzwtdetmtallzyukdztoxysggrqkliixnagwzmassthjecvfzmyonglocmvjnxkcwqqvgrzpsswnigjthtkuawirecfuzrbifgwolpnhcapzxwmfhvpfmqapdxgmddsdlhteugqoyepbztspgojbrmpjmwmhnldunskpvwprzrudbmtwdvgyghgprqcdgqjjbyfsujnnssfqvjhnvcotynidziswpzhkdszbblustoxwtlhkowpatbypvkmajumsxqqunlxxvfezayrolwezfzfyzmmneepwshpemynwzyunsxgjflnqmfghsvwpknqhclhrlmnrljwabwpxomwhuhffpfinhnairblcayygghzqmotwrywqayvvgohmujneqlzurxcpnwdipldofyvfdurbsoxdurlofkqnrjomszjimrxbqzyazakkizojwkuzcacnbdifesoiesmkbyffcxhqgqyhwyubtsrqarqagogrnaxuzyggknksrfdrmnoxrctntngdxxechxrsbyhtlbmzgmcqopyixdomhnmvnsafphpkdgndcscbwyhueytaeodlhlzczmpqqmnilliydwtxtpedbncvsqauopbvygqdtcwehffagxmyoalogetacehnbfxlqhklvxfzmrjqofaesvuzfczeuqegwpcmahhpzodsmpvrvkzxxtsdsxwixiraphjlqawxinlwfspdlscdswtgjpoiixbvmpzilxrnpdvigpccnngxmlzoentslzyjjpkxemyiemoluhqifyonbnizcjrlmuylezdkkztcphlmwhnkdguhelqzjgvjtrzofmtpuhifoqnokonhqtzxmimp",
                "xjtuwbmvsdeogmnzorndhmjoqnqjnhmfueifqwleggctttilmfokpgotfykyzdhfafiervrsyuiseumzmymtvsdsowmovagekhevyqhifwevpepgmyhnagjtsciaecswebcuvxoavfgejqrxuvnhvkmolclecqsnsrjmxyokbkesaugbydfsupuqanetgunlqmundxvduqmzidatemaqmzzzfjpgmhyoktbdgpgbmjkhmfjtsxjqbfspedhzrxavhngtnuykpapwluameeqlutkyzyeffmqdsjyklmrxtioawcrvmsthbebdqqrpphncthosljfaeidboyekxezqtzlizqcvvxehrcskstshupglzgmbretpyehtavxegmbtznhpbczdjlzibnouxlxkeiedzoohoxhnhzqqaxdwetyudhyqvdhrggrszqeqkqqnunxqyyagyoptfkolieayokryidtctemtesuhbzczzvhlbbhnufjjocporuzuevofbuevuxhgexmckifntngaohfwqdakyobcooubdvypxjjxeugzdmapyamuwqtnqspsznyszhwqdqjxsmhdlkwkvlkdbjngvdmhvbllqqlcemkqxxdlldcfthjdqkyjrrjqqqpnmmelrwhtyugieuppqqtwychtpjmloxsckhzyitomjzypisxzztdwxhddvtvpleqdwamfnhhkszsfgfcdvakyqmmusdvihobdktesudmgmuaoovskvcapucntotdqxkrovzrtrrfvoczkfexwxujizcfiqflpbuuoyfuoovypstrtrxjuuecpjimbutnvqtiqvesaxrvzyxcwslttrgknbdcvvtkfqfzwudspeposxrfkkeqmdvlpazzjnywxjyaquirqpinaennweuobqvxnomuejansapnsrqivcateqngychblywxtdwntancarldwnloqyywrxrganyehkglbdeyshpodpmdchbcc"));

//        System.out.println(new ShortestCommonSupersequence().shortestCommonSupersequence(
//                "djldksjfla",
//                "fkfjlsafj"));
    }
}
