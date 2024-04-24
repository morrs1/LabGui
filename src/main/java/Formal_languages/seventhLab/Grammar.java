package Formal_languages.seventhLab;

import Programming.FourthSemLab.FifthLab.Pair;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Grammar {
  private ArrayList<Pair<String, String>> rules;
  private ArrayList<String> terms;
  private ArrayList<String> nonTerms;

  public boolean isRLG() {
    for (var rule : rules) {
      if (!(rule.getFirst().length() == 1 && nonTerms.contains(rule.getFirst()))) {
        return false;
      }
      if (Objects.equals(rule.getSecond(), "eps")) {
        continue;
      }

      var counterNonTerms = 0;
      var indNonTerms = -1;
      for (var el : rule.getSecond().toCharArray()) {
        var el1 = String.valueOf(el);
        if (nonTerms.contains(el1)) {
          counterNonTerms++;
          indNonTerms = rule.getSecond().indexOf(el1);
        }
      }
      if (counterNonTerms > 1) {
        return false;
      }
      if (counterNonTerms == 0) {
        continue;
      }
      if (counterNonTerms == 1) {
        if (indNonTerms != rule.getSecond().length() - 1) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isLLG() {
    for (var rule : rules) {
      if (!(rule.getFirst().length() == 1 && nonTerms.contains(rule.getFirst()))) {
        return false;
      }
      if (Objects.equals(rule.getSecond(), "eps")) {
        continue;
      }

      var counterNonTerms = 0;
      var indNonTerms = -1;
      for (var el : rule.getSecond().toCharArray()) {
        var el1 = String.valueOf(el);
        if (nonTerms.contains(el1)) {
          counterNonTerms++;
          indNonTerms = rule.getSecond().indexOf(el1);
        }
      }
      if (counterNonTerms > 1) {
        return false;
      }
      if (counterNonTerms == 0) {
        continue;
      }
      if (counterNonTerms == 1) {
        if (indNonTerms != 0) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isCS() {
    for (var rule : rules) {
      if (!(rule.getFirst().length() == 1 && nonTerms.contains(rule.getFirst()))) {
        return false;
      }
      if (Objects.equals(rule.getSecond(), "eps")) {
        continue;
      }
    }
    return true;
  }

  public boolean isCZ(){
    //Проверка на то, если ли нетерминалы в левой части
    var c = 0;
    for(var rule : rules) {
      for (var el : nonTerms) {
        if (rule.getFirst().contains(el)){
          c++;
        }
      }
      if (c == 0){
        return false;
      }
    }
    //



    return true;
  }

  @Override
  public String toString() {
    return
        "rules=" + rules +
            "\nterms=" + terms +
            "\nnonTerms=" + nonTerms;
  }
}
