import java.util.ArrayList;
import java.util.List;

public class Anagrams{
    List<String> dictionary,prunedDictionary;
    String str;
    public Anagrams(List<String> dictionary){
        this.dictionary = dictionary;
        prunedDictionary = new ArrayList<>();
        str ="";
    }

    private List<String> pruneDictionary(LetterInventory inv){
        List<String> lis = new ArrayList<>();
        for (int i=0 ;i<dictionary.size();i++){
            LetterInventory hold = new LetterInventory(dictionary.get(i));
            if (inv.subtract(hold) != null)
                lis.add(dictionary.get(i));
        }
        return lis;
    }
    public void print(String text, int max){
        if (max < 0) throw new IllegalArgumentException();
        LetterInventory yes = new LetterInventory(text);
        List<String> no = pruneDictionary(yes);
        for (int i=0;i<no.size();i++){
            LetterInventory hold = new LetterInventory(no.get(i));
            if ((max != 0 && str.split(" ").length < max) || max == 0)
                str += no.get(i) + " ";
            if (yes.subtract(hold).isEmpty()){
                System.out.println(str);
                return;
            }
            print(yes.subtract(hold).toString(), max); // recurse into empty string
            if (str.indexOf(no.get(i)) != -1)
                str = str.substring(0,str.indexOf(no.get(i)));
        }
    }
}
