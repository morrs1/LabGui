package expOperation.firstLab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TableTransportTask {
    private Map<Integer, Map<Integer, Node>> table;
    private static final Scanner scanner = new Scanner(System.in);

    public Map<HeadNode, Map<HeadNode, Node>> setTable() {
        var mainTable = new LinkedHashMap<Map<String, Integer>, Map<Map<String, Integer>, Node>>();
        System.out.println("Введите ячейки таблицы: ");
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("exit")) {
                break;
            }
            var arrStr = str.split(" ");
            for(var s: arrStr){
                
            }
        }

        return null;
    }


}
