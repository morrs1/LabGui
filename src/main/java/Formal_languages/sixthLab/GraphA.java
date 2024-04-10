package Formal_languages.sixthLab;

import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
public class GraphA {

  private final Map<String, Map<String, Set<String>>> transitions;
  private final Map<String, Set<String>> graph;

  public GraphA(Map<String, Map<String, Set<String>>> transitions) {
    this.transitions = new LinkedHashMap<>(transitions);
    this.graph = new HashMap<>();
    convertToGraph();
  }

  private void convertToGraph() {
    for (var entry : transitions.entrySet()) {
      String key = entry.getKey();
      for (Set<String> values : entry.getValue().values()) {
        if (!graph.containsKey(key)) {
          graph.put(key, new HashSet<>());
        }
        graph.get(key).addAll(values);
      }
    }
  }

  public Set<String> bfs() {
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    if (!transitions.isEmpty()) {
      queue.add(transitions.keySet().iterator().next());
    }

    while (!queue.isEmpty()) {
      String vertex = queue.poll();
      if (!visited.contains(vertex)) {
        visited.add(vertex);
        if (graph.containsKey(vertex)) {
          for (String neighbor : graph.get(vertex)) {
            if (!visited.contains(neighbor)) {
              queue.add(neighbor);
            }
          }
        }
      }
    }

    return visited;
  }

}


