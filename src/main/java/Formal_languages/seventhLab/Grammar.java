package Formal_languages.seventhLab;

import Programming.FourthSemLab.FifthLab.Pair;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Generated
@Setter
public class Grammar {
    public ArrayList<Pair<String, String>> rules;
    public ArrayList<String> terms;
    public ArrayList<String> nonTerms;

    public boolean isRRG(){
        return true;
    }

    @Override
    public String toString() {
        return "Grammar" +
                "rules=" + rules +
                "\nterms=" + terms +
                "\nnonTerms=" + nonTerms;
    }
}