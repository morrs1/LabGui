package Programming.FourthSemLab.FifthLab;

import java.util.ArrayList;
import java.util.Arrays;

public class SupaCoin {

  public static ArrayList<Integer> minCoins(int[] denominations, int total) {
    Arrays.sort(denominations);

    ArrayList<Integer> resp = new ArrayList<>();
    for (int i = denominations.length - 1; i >= 0 && total > 0; i--) {
      while (total >= denominations[i]) {
        total -= denominations[i];
        resp.add(denominations[i]);
      }
    }
    return resp;
  }

}
