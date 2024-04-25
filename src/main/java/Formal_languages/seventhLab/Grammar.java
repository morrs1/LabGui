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
    for (Pair<String,String> rule: rules){
      if (rule.getFirst().equals("S") && rule.getSecond().equals("eps")) {
        for (Pair<String,String> r: rules){
          if (r.getSecond().contains("S")) return false;
        }
      }
      String leftPart = rule.getFirst();
      boolean N = false;
      for (String nonTerm: nonTerms){
        if (leftPart.contains(nonTerm)) {
          N = true;
          break;
        }
      }
      if (!N) return false;
      String rightPart = rule.getSecond();
      if (rightPart.equals("eps")) return false;
      String psi1,psi2;
      int i = 0;
      while(!nonTerms.contains(String.valueOf(leftPart.charAt(i)))){
        i++;
      }
      if (i > 0) psi1 = leftPart.substring(0, i);
      else psi1 = "";
      int j = leftPart.length() - 1;
      while(!nonTerms.contains(String.valueOf(leftPart.charAt(j)))){
        j--;
      }
      if (j < leftPart.length() - 1) psi2 = leftPart.substring(j);
      else psi2 = "";
      if (leftPart.substring(i,j).length() > 1) return false;
      if (!rightPart.contains(psi1) && !rightPart.contains(psi2)) return false;
      if (i == j && !psi1.isEmpty() && !psi2.isEmpty()) return false;
    }
    return true;
  }
  public boolean isNU(){
    for (Pair<String,String> rule: rules){
      if (rule.getFirst().equals("S") && rule.getSecond().equals("E")) {
        for (Pair<String,String> r: rules){
          if (r.getSecond().contains("S")) return false;
        }
      }
      if (rule.getFirst().length() > rule.getSecond().length()) return false;
    }
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
