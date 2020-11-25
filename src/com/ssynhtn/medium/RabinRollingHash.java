package com.ssynhtn.medium;

import java.util.HashMap;
import java.util.Map;

class RabinRollingHash {
    static final long P = 1000000007;
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int[] freqs = new int[26];
        Map<Long, Integer> seen = new HashMap<>();
        int max = 0;
        
        int uniqueChars = 0;
        char[] chs = s.toCharArray();
        final int n = chs.length;
        
        int size = minSize;
        
        long base = 26;
        long mul = 1;
            
        long sum = 0;
            for (int i = 0; i < size; i++) {
                char ch = chs[i];
                if (freqs[ch - 'a'] == 0) {
                    uniqueChars++; 
                }
                freqs[ch - 'a']++;

                sum = (sum * base + ch - 'a') % P;
                if (i < size - 1) {
                    mul = (mul * base) % P;
                }
            }


            
            int i = 0; 
            int j = size;
            
            while (true) {
                if (uniqueChars <= maxLetters) {
                    max = Math.max(max, seen.merge(sum, 1, Integer::sum));
                    if (sum == 63914206) {
                        System.out.println("hash " + sum);
                        System.out.println(new String(chs, i, size));
                    }
                }

                if (j < n) {
                    char ch0 = chs[i++];
                    freqs[ch0 - 'a']--;
                    if (freqs[ch0 - 'a'] == 0) uniqueChars--;
                    char ch = chs[j++];
                    if (freqs[ch - 'a'] == 0) uniqueChars++;
                    freqs[ch - 'a']++;

                    sum = ((sum - (ch0 - 'a') * mul) * base + ch - 'a') % P;
                    sum = (sum + P) % P;
                } else {
                    break;
                }
            }

        return max;
    }



    public static void main(String[] args) {
        String s = "jkfdkmeighcmefihcfjcbeamcfdcdlglfakbkfceaabcakkigmekibfibmlegfmhfabljdehlbaaihkibbeemfhlfglhhfbegfkafdkimjjadamglgfhigbjbifkgagfalcibibaljdmefbfkhdbcickjbeigllcegjhgbhafmlakhccdlhbalbfgcdalmbjkmbjhbfglfjjjeadgchaechicldiecdmddekmhkijcmhmcldjjhmbmkhhgekggjkeehjmfgklfcemmafaffbeibgebakeeibcelfjeblaecgiefcljjjelkffhkhchjflechcbjlacgiegbflcefejbbmlhhfdgekblblkkbceeejeeideggmdjgickfckacklfmmmafmhmfjhgfmiegdgjeilhkkaiijjgdhlkhilkjgfeahhkleekleaegjadhlkgfeifldbjdkdkdhgmefkggggkcgjjcfkaefdgjjjgkdddjhldljhilldickgabhecelmidhheglmeldbehaifhllickifmiebmfijlicfiagmlmcfaldblbafehmgcgdmeiblidhbkfaehmdglbgeghgddgmhjbcbbefajejbfildmllkbffhkilkefadmfcmljgjklgedamiigecjdfabfblfbiagdhhjeefccclmiecdfalcjcgjfbmaekeamhejhdadegbdcbjigfmlllafhblfjmhmehlkmimggbeaglheajchchjbbicfceffhjckiacdkjhhmceifgbkagdmckdhbieejblaachcajlmekfehbeaadmhebehbelhgflabllfhkkaegbffimldfjegijdlaaflmbelbigacikcjeccmfcddecmfbibggfkdigkebjihllkflalcflmcmlkgmgbfgffiekmcecfbbfemldejcldeehjgjdjcahlkcmedcabjdfhcckkdbickfiibijcgfmhgkcekjgbagfkmkikilaflidliidgjajilmkclkajihdgjhchkjgdjmlcefellimgehkmbkghahbeiimmmidfeabjkbbiadjdjefcbljlilgcfjchijhakhdiimcahjaegchjemjjmdmfkibellichfgjjhfdkihmekmigblggackkkaiegdabmmjceibfiehddblccfjmaceikidkdcfaeeklihaeeemaadaejcdfkcedimgfdacamfmcjccmfcmedbedikkjefefjflgahecahbkhbcklmmhdbdblckcccfemciaglieimjciglmllkefjbdcdlbccedmldmjeijbikdeljijmbeadkajhkmbmhfikdiiklcemalealfggfackmiaahagjdkbjmhmlcgecfbhdialdblfjeigijhmgebgdijmjbaheffjelekcbcgamhbcldiaimedjlggjhedfjdfkeekdkldheelajjddmlfhfkkmdggkdlgjikecimakihkfkbhcfbajijdihkkfgklbjaeeilchadaebdcdkemalfimlblaliadjhejkfckdfgmjmigkiglkmejcdlbafeldlijgkbledehejaimdbbaimbffclbjajdgldhdklhbhkdeeacjiamcfjmfafdamglfhlgmafbigmgdmkhcgaeiajgailabeldadfhkmedahcbajlegceafjhhjfiifibkecaihaicjkcijjgeddibckeedhjmlkdeemfigaflcgjkbbehgdiafebbfmbbbdfmjchcgafgdcgeheblgfdhhilhljaelhckcjjjhgfaiihlclehjmalckmlaeceeifegjmjfgcccmidlkgjkecbmjkkdjkbkgafhldmlfajhgbgheadhglfhdkglldjfaleddgcagjfkhkhmmhamciefmhcdlmdfjeihkimjdihkmjadmhhadgmkmbleafemhebmlaehjmdhhkbdegfdlcamkiemhjjkbfhjdkmhjgcjjlabkdlccmccjgabbbkcbjhdmfhdllkicljieahhgamkljfgmeacflhfcchhmcmijmhgjhggbkhmklgfkdklmclhjdhmifaalhljjcbfjmjlbkajefhbkeifjmffhgljmfifblhaghjcckfdahbjaeicbdecciklkgdciibjbaajcecablghhkahfjlifdadaibimaikikkbbicdccbajmegbidkmgcdagdehmghdegfeliihfbakhcmfbfcfcakfhgleaejckkmbkkfdkgajakkmlecccakmgkcgcmamckkkacgicjaaklbbfhicmgllmbjflebbklamfjjmemlajfebjklccehmlglcaiejajmbdhgeadmfilcedglfbbfddbamabifikclidmigdihkhkfahicicdimidjeefmmacldaifkifeihgjkflfbcmabfcjbmcicdbblfbgdleebjjelfjahdbgkljdbbmmmdhagkddeikmicjlmllfcekkgefbdlhafibeikelggdfmamfafmajhlibaeklkjemjdceacicjifdhdllacmleeligdibdemejfhiccejfchimdcdladdmeifeaidmbaadjkghhfefjmhfhmacmlemaaklfbgghbdiacfmhclmlgkacdakkkbamehldcalemkhbdfebfhkgceeddbkaaakagjdefkbghblkfbifbkkemfdfhbkagdhkdakdjeaailhbhdgefhhkhaiffgajimjmifjdemidmecicbgdijcacecklmbmljdcemlljgfmidmfjaacjcdieifmhiekidcbaldfjegegijbhbkkkdalalaflmmikfagbmcbhageddhljlkjhdghjbbgbchbgfkbcjdkgikeilhfbbjhmmakcekfkhieaambmjbmlcmgmlecjkmiihfghhcgemlkkbklaahjgjjgclgbihjcjebikjjddffmeekgeidjmgadgkfahahfcbckmhblehmcbijlckdbbiicdalacfeammcfegemkmcghgcdbiahgcjaaageehldflclcmakmeggfagkidgbgimfeggjbdmaajfaabihklkcfeemdeiiifahfdeemcdjibjfehciibggkieeelldaeifcmihihifmgaamlhhikmbbfjddmabdhjjfghbkfakfmhckfikfielccbhcjheijjlaclhabkkkbiabccchghkmhfhbfbaejgajkcgfejdckhmmgdjekgikidmjgmhbmliaccbkdghaljdbhbbghhmdgbibclddcfbhljiahfellaidldgffgjlhcjchhdellhgldlfigihhmhfamkheiedhfkcibdilmfhkmjehgmmjcggadmmagekfbikdfjkakklkfadfghgjjkbmbjmakehjclcfhfjjaablfgfdacjcdlmdbiagfhcihfmejjmfmkcfkeihdlkjeiibdfcfaligdeagmgkcakieekaafjihejflhjhacblhaaciklleljkfldefeikfjcjgmidehfkiicglmabbkkmgmacjemijmgekecmkabicbkhjalhfljfmlcgchdjhdgjihklmdbihdmfhalcmbbcdchgifjbdbeicimjajemhfieieclfdekajdgeejijfjlkbbikbeeabgbhcdikmhhliejdkfdjemmlejmljfbabhhjdlghmikakjkeccgfagheijebccfidgcfcfeigemjmkchdgabamfmgmflbbdelcgkkagbfgmibicfjifcebalgkmkbllaakdmbgcilkbgmclkgfccmejmkbkdkbjfjegadbdlkbehdhefbigabjbjiimfbakkmjblgdhjmejmbclmaajffcamegbjcgeaflgfekmijlhmkaclecmdifgdbcbblmfhkklhflkicjdkfhbgkijiecijbhdijhdmfmgcbmfjgjldkkddelgamfmmfldfgijdlcemklgkkkeammfmadkgckmllllkbgbhcjjklclfiideiaichiamgechjjimmmaicefiblmcfaaccimmbdjkccakjdbilgggcfdgddjllllelmklghmfikgfalbddcebagihjmfcmbjdjekhigmbgjdibjfcagjcjbifejcgkkbhcbcmkckkfmakbflkkdaihadfgailkadaeajjajdfekdhmlmghdlgegflmiaabcfjgefhlfemdfkkdmlagmjlfmbdhhbhckglbfkbkefdljelielfmibkdhdffcdjgbfcmhilbehhkladbeljgedjchfdlhfdikeimgdbbbccebmgibgmmcffhjgfikmgiifmgdfbgbfllfilhbjibebljmafklfiecddcfklflbhadimdmlmhifmfikfmiafcjmfeidgmmbkmafacljdajhchhdcmgehlmlaebjmecgejlajcdfllgbgkbidieifbagilbbkiddlmbhaggadflkmdfdhmdlmgkgcmmadbjeggjkimhhbmhkjmebdcmbdbbbmfmbmghjehcgeecfhhiglmjhilkchmcmgdlglkehegjalibmlbkjdhmgjdiebjmcgcmkjjfdlmjmbemdedcajihlhmlkeddbkahhedikldhffjbmahbabgjeeifhfmcelliahhgigdbalikgafjhlhejgihjkfblelmgjimakalcmbihmfmmiahjjfidbghdccjiijhakmedajikhjjfhigddcjhifihhfabdiifidjcibkafkfaeffddbmmjjlmiagkbfcljhldahcimbdjdabdjchkdjhffmgdfmgkllgffhkblelbmjljhjajaafkllbidhhimiamihajakedcdhecffcgkkefbfjgcifiemfbigamialiaehheegecabcfijijiijchlbkjbcigmhdlejidjjhbhjgaedhffhfibccbcjgmifickgkcgcklmehdfmgagcfkjhejcckefadmgcecjkmgajkimmjghdhbkfemfibabkilgfddebgdimfjjljlciggdfjhghlbfmhadfhkkchcihfcbfjfbiejdkddkihgllgjfbmgiigkhhcgfhlggfdbmmjglcmkimjhbdcghegliidijlhkjiblgidfggeehmhiaglhikfcdhgddfcglgmbghkekcaheldkfdgehamfjfekghjcdjjdhhbfcehemilkdhlbgmmkkbbfafdekkajbjidfgmjkfdkckjichmgmkbhgacbihjegdiefdbegmjlblfjkmagllcbgdbheeijflfdkmdgeammlccmbkmddllceghlilhddglgemjalimcekidihbmldffbihihmbajeilceihakfjmiihhcjdmiakfldggcjlgmfklhhlcjkkemdggidjccibfkgklkfflkafhfgelfejkdfcghjkbekfabeiklcgbkcjjiekkfjimdlfmkjejeegkdhhigffjeacgdbhaidhhbbdhkdhkggljchbhihmgalijdbdkcabmmbdmeaemkmfiglmekfchliegfhljecadladgdgjkmleailmiiambiclblaijccdkajgifldgmiakmmcbmlfjccklhciedlldccdihchlafabjadcceljbeeigdefcjeidgfgjllamllkfejlbkbhdfmmgagihbafgljieeldidchkfkgkchbaaaifdieajeglbalfeiakiljfglmjfkkjgcbekjhbmkibfkaejafhkielhhcmgkggdhldhjgiiadcheffdagijlcalbifggclbdgcbkihakfgajmmdegbiiablllldaejmkichaelhgmmgamajkkflkmcihheadcfchifjhfjdcackdmbkilkfimfdlkgfldfmglajifbbmgcdmfmkkgmkhkmdllglcgcelagdgadlmbklghdbgdljjlffmdeaifkbkcllecmmefjfkdlejhgfccfagaacdfikkkfllkglhmfijcajcdhmmcjbkmcmjmbgemmjihachejlkgfbgifbkabidimcdafccbcggdcfemmebdcllhhcjbgbhihhhgjiacjlbhljceflhlcelbjadcfgjgfgljkkdldmfkeefceiajfbccjiihebkegbhljkfjlgimggifiihdmafgdigkkjcfdedhkdlbideaadcbclebleldjaeaifjebceeekfggiiilefgdiahmlaehgcffdfklmmmfklmemdddhemfifcfcldbecbhlbleeihbhbgciamakbmhkebcmcafijhgkadhglgammmfgagcgiefjgbeialbcidicddgamelbjmchdjfbfgbbdjeklhcdimjdhlkdjgcaclcabdhbfhccalfjckgbbemdekdaahckblhgddihdfbjjkdlfifckldmlfkmbilmlclbdmehfhdlicedgcdfljhfmgbclgakcmbkimmmlchlkgdedalcabedhlcgfkdjjmkbdekfdgkabcibbelehmeiledjbhbaajmcgfihfahjmjfcbbcjlmbhfkdamhmlelifaiammllcfecfjhfjhjlhhdahgfacildkfeecihmelefkjagfjcebbjgaiefjhkgakjdhfdcffkldmmidggkjcdejckbblikgfgggigcgiabmdijlflaamcafcilafckembidbgghadmdmjmkjifimgcmbbjddjcaahjgeimlgbehgmgdjhfafkfclcdhklkacfcflhaahcdejhbegibjfcjadcfkflbcebjbhkjhjeeidjehajbaakkmffhedhbglhbfhblgjbjghmfjfbfcmglekkhjhfifdbccfhmdkeklbmdlhlmghdkfbdckjhckgggalihefcjbdefafgcakafafjfaeahbiileagkfagaammicmceadliicmmhchgbiglhhdlliiaebeekegmlhldjielbjadhhdhicakdjcgcflegfhaiibaffchcghlemmiejbbjgcbkabaemgkbdmbfgcfbjlflahdblglhdlhaldekhlkkbafmebjgcmkfgfefmlmjhbgebfiklbmjiacgafkekchfjdejdeibmfchkbbigajhhhlflimhimbdlfccmhggcehmeifdkjbacahchfbakjkcdjfdcligmgcihdeeeehihhfdifgcclimielmemacijbahgclbdhfmdljgihljbccdcacaeglecaedekdkcdihlejljhdbbgfgajdjdfffidbegaedibcecaiefbdgdlehfkahhaebfacejggjfedkgehgcedllcfadiklbdalemgfeafhgihigjmijclhbjegjdgdlemfbfemfbaccjihlmajklalieakaebbhdjafijgddfefijmkmhakjbgfccbjedhljhmbmehbhfcembefaialgighddakkefkmfmibaddmilcldmifdcjikiiagclagegdcggaehkdillilcckeikfidimfmilhlmeecgdbidmibkjdgjbcgmeebmhjekchbhgdabkibcjgmdaammhcbifbjmddekbeekgafmijhmekffdgeacdiieemekegfmgiaicigddmieajdfakejbghkkmegmcdjgegmjdidedhelhieckglmiejeighmgdgejimkijfmgejedadbilchfefkdagmfkciibbaclbcmjgdafdejiblbkiidcbcemifimaembbgbbmakafjmecklljdaehddjfiiflchdcmlfcdgllemhlmibdlkjmmggiekhiafcbglfddeeliidlbilbdiihkmjhfiimalimikcacdkikajfkihhcfabdiegacfgjegemcacjekimjefelgbblggacfdgjebcmcjmbjmemjkghimkmhbkceelmcfkddekeajclgefbkmambhkgaabcdcjgfjdhmfllbfhmdmleimkikekaahdfhkibhkeaeaejhiffmfmdmgifdhgjlmcgegcaagilckbbjakcfafbikbmldcghfehdgllghggdkiaahgmhghjfaghmgibblakieilelhmhkhbfhgcfdeimgeeaciegeghdidhlgbmikblambdefadkmlicjglmbdaifekfhkdjkcajegcbjlgeccmdehmmkdmhiekkjlijfjfflbegjacbiibfgecbamkbggeifmfidibehfjcebabmkhlkgecklecdeekchhhkjehgdclllclgkaidkeeihfckacfdkagiejlmmdaekbkkdlfgdghmbacedkfjhghlfigclclmjmmalagcfcgeehgfdmckkmmhkfidmbiglmjkmfhhagkldcjcjlecdhkghjiadhjjbfihjgcdfgfigjeekjdfkhmchjiammbeglggkckbjhcichacfamhljfljgkfleagjhcehfeejdbaekhjhcgkibhfkmbmbihimfgcjihgiljamajhbhggecaehfmhlhmjekcecgldllhffibhbmecbjcbkcfhjfkffaajfdglbcemgkabmihaldbhidfkhdkjkekaaemghbdjdfijillhaikbgmlffjlhekacaeaccgcfhjdhfaeehilllkbjmlmkmcaifihfjfeckdbjegafkdllcjccdijkcebagbbbmahhmfbemlmcihklhkebibifaegjgbjgkkmhljjiehdjilmijgmimdkmbkiedekcakmcafhbfhkhhkfkfamdlfbmdfeabbijkbbgfehhmbamagjkdkebcdbhfhekjfchclaihjafdmdhghgbdedciihbbichlmikfhgjjdgakhhlcfcgijlaicdjgfdjkemagaffhfmmdhcfkgiiagcbidfkgcefhlbcfejbliieldeddiikkmigceieaaeaeekfbmifgmggchclaeblakjeeiibhkiiihkfcgceaiefdhmdicmdmecgfgfedlcjkhhlmdimmajffgbafikgmkchjcaemcdbfaeajdickdbabdgijgkdceiihakimgfkimllbljkffbdlhdbilaehihhiegadfcmkfljckhamiadchgajmbcehamjeahefgdmjbhidamghgmbfblklihmacmfheldmafjfgkmkjfdmalaabeambmdemkddfehjckjadjbddmijjjlagicagligfjkdkkjlhgkbfkkeiifimcmibbkiicfdfhggcbekmmialilhfjhmhdhkdlamdlajdgdlglklkhjddgdabaadgiijahajbgfegijakcjggfaamihhaalegdcegadfifdhhlacflghjabiffcjiajhhkcggdhkfklhhiffbalagimdfchhamfemgbfikecagebmikbkgfdmjeakleamdkkmemdkhalfhllcgcedlbfidbgmlmbcmmbkicejhbhmejfgieakkccmgkedfbgcafajlkbjgcdmjgmfghjbaeiailkegjghhkgmghahelggjgbhakdebhaehelcdjbgbihhfgldmlfahmkeebgigfcfcehhiahjdmlgajcecjlhlagidgjkdejgichmafjmlecahcjdkjafejjkahkebimjcaaajailkdlgiefjbllejkfmgffkagbdbjijmekdjbfakgfgckhbfagmmafihgmafbhljaadbcabiaiagkkhglcjkcafadligjgeabaaklmfckelfiikakgjdcmgddbimeeefaahmddfbhdjmbblmbifbgmchaehbgdkdcjacfbfadcgcifdefjbiemkbgbchdkcfheaegkgfifmbdllibhmdfbkbceebdkmhjmcabbihafbkkijgjhamamg";
        System.out.println(new RabinRollingHash().maxFreq(s, 6, 8, 26));

    }
}