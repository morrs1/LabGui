package Formal_languages.seventhLab;

import static org.junit.jupiter.api.Assertions.*;

import Programming.FourthSemLab.FifthLab.Pair;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class GrammarTest {

    @Test
  void isRLGTest() {
    ArrayList<Pair<String, String>> rules = new ArrayList<>();
    ArrayList<String> terms = new ArrayList<>(), nonTerms = new ArrayList<>();
    rules.add(new Pair<>("S", "0A"));
    rules.add(new Pair<>("A", "0B"));
    rules.add(new Pair<>("A", "1"));
    rules.add(new Pair<>("B", "0A"));
    terms.add("0");
    terms.add("1");
    nonTerms.add("S");
    nonTerms.add("A");
    nonTerms.add("B");
    assertTrue(new Grammar(rules, terms, nonTerms).isRLG());
  }

  @Test
  void isLLG() {
  }

  @Test
  void isCS() {
  }
}