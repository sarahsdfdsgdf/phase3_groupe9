/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phase3;

import java.util.ArrayList;
import java.util.List;

public class MaximumFlow {

    /*
    * this method for create new graph
    * @param nodes This is the first paramter to createGraph method
    * <pre>
    * {@code
    *  List<Edge>[] graph = new List[nodes];
    *  for (int i = 1; i < nodes; i++)
    *    graph[i] = new ArrayList<>();
    * }
    * </pre>
    * @return a List
    */
    public static List<Edge>[] createGraph(int nodes) {
        // create new list edge for graph
      List<Edge>[] graph = new List[nodes];
      for (int i = 1; i < nodes; i++)
        graph[i] = new ArrayList<>();
      return graph;
    }
    /*
    * this method for add Edge to graph
    * @param graph This is the first paramter to addEdge method
    * @param src This is the second paramter to addEdge method
    * @param dest This is the third paramter to addEdge method
    * @param capacity This is the forth paramter to addEdge method
    * <pre>
    * {@code
    * graph[src].add(new Edge(src, dest, graph[dest].size(), capacity));
    * graph[dest].add(new Edge(dest, src, graph[src].size() - 1, 0));
    * }
    * </pre>
    * @return Nothing
    */
    public static void addEdge(List<Edge>[] graph, int src, int dest, int capacity) {
       // add edge for s to d and from d to s with capacity and number if edge
      graph[src].add(new Edge(src, dest, graph[dest].size(), capacity));
      graph[dest].add(new Edge(dest, src, graph[src].size() - 1, 0));
      
    }

    /*
    * this method for max flow algorithm
    * @param graph This is the first paramter to maxFlow method
    * @param s This is the second paramter to maxFlow method
    * @param d This is the third paramter to maxFlow method
    * <pre>
    * {@code
    * int flow = 0;
    *  int[] q = new int[graph.length];
    *  while (true) {
    *    int firstqt = 0;
    *    q[firstqt++] = s;
    *    Edge[] pred = new Edge[graph.length];
    *    for (int qh = 0; qh < firstqt && pred[d] == null; qh++) {
    *      int cur = q[qh];// == s 1
    *      for (Edge e : graph[cur]) {
    *        if (pred[e.dest] == null && e.cap > e.f) {
    *          pred[e.dest] = e;
    *          q[firstqt++] = e.dest;
    *        }
    *      }
    *    }
    * System.out.println();
    * if (pred[d] == null)
    *   break;
    * int df = Integer.MAX_VALUE;
    * ArrayList<String> authPath = new ArrayList<>();
    * for (int u = d; u != s; u = pred[u].src)
    *   df = Math.min(df, pred[u].cap - pred[u].f);
    * for (int u = d; u != s; u = pred[u].src) {
    *   pred[u].f += df;
    *   graph[pred[u].dest].get(pred[u].rev).f -= df;
    *   authPath.add(u+"");
    * }
    * authPath.add(s+"");
    * System.out.println("Augmenting path");
    * for(int i=authPath.size()-1;i>=0;i--){
    *   System.out.print(" "+authPath.get(i)+" "); 
    *   if(i>0)
    *         System.out.print("->");
    * }
    * System.out.println("flow "+flow);
    * flow += df;
    * System.out.println("Update flow "+flow);
    *}
    *   System.out.println("----------End----------");
    *   System.out.println("Max Flow : " +flow);
    * boolean[] isVisited = new boolean[graph.length];     
    * dfs(graph,s,d, isVisited);
    *    System.out.println("Minimun Cuts :");
    * for (int i = 1; i < graph.length; i++) {
    *    Edge[] pred = new Edge[graph.length];
    *    for (Edge e : graph[i]) {
    *       if ((e.cap > 0) && (e.cap-e.f) >= 0 && isVisited[i] && !isVisited[e.dest]) {
    *            System.out.println(i + " - " + e.dest);
    *        }
    *     }
    * }
    * </pre>
    * @return Nothing
    */
    public static void maxFlow(List<Edge>[] graph, int s, int d) {
      int flow = 0;
      // array for node on each path
      int[] q = new int[graph.length];
      while (true) {
     
        int firstqt = 0;
        q[firstqt++] = s;
        // for on current path from s to d
        Edge[] pred = new Edge[graph.length];
        for (int qh = 0; qh < firstqt && pred[d] == null; qh++) {
          int cur = q[qh];// == s 1
          for (Edge e : graph[cur]) {
            if (pred[e.dest] == null && e.cap > e.f) {
              pred[e.dest] = e;
              q[firstqt++] = e.dest;
            }
          }
        }
        // if we finish all paths
        System.out.println();
        if (pred[d] == null)
          break;
        int df = Integer.MAX_VALUE;
        // get min value on the current path
        ArrayList<String> authPath = new ArrayList<>();
        for (int u = d; u != s; u = pred[u].src)
          df = Math.min(df, pred[u].cap - pred[u].f);
        // update the flow for each node from d to s
        for (int u = d; u != s; u = pred[u].src) {
          pred[u].f += df;
          graph[pred[u].dest].get(pred[u].rev).f -= df;
          authPath.add(u+"");
        }
        authPath.add(s+"");
        // print current path
        System.out.println("Augmenting path");
        for(int i=authPath.size()-1;i>=0;i--){
          System.out.print(" "+authPath.get(i)+" "); 
          if(i>0)
                System.out.print("->");
        }
        // add to flow min value for s to d
        System.out.println("flow "+flow);
        flow += df;
        System.out.println("Update flow "+flow);
        System.out.println("--------------------");
      }
      System.out.println("----------End----------");
      // print max flow
      System.out.println("The maximum flow of the network " +flow);
    // define visits for nodes
    boolean[] isVisited = new boolean[graph.length];     
    dfs(graph,s,d, isVisited);
        System.out.println("The corresponding min-cut :  ");
    for (int i = 1; i < graph.length; i++) {
        Edge[] pred = new Edge[graph.length];
        for (Edge e : graph[i]) {
            // get the min cut edges
           if ((e.cap > 0) && (e.cap-e.f) >= 0 && isVisited[i] && !isVisited[e.dest]) {
                System.out.println(i + " - " + e.dest);
            }
         }
    }
      
  }
    /*
    * this method for dfs algorithm to get min cut
    * @param rGraph This is the first paramter to dfs method
    * @param s This is the second paramter to dfs method
    * @param d This is the third paramter to dfs method
    * @param visited This is the forth paramter to addEdge method
    * <pre>
    * {@code
    * visited[s] = true;
    *    Edge[] pred = new Edge[rGraph.length];
    *    for (Edge e : rGraph[s]) {
    *        //System.out.println(s+" "+e.dest+" "+e.cap+" "+e.f);
    *            if ((e.cap-e.f) > 0 && !visited[e.dest]) {
    *                dfs(rGraph, e.dest,d, visited);
    *            }
    *    }
    * }
    * </pre>
    * @return Nothing
    */
    private static void dfs(List<Edge>[] rGraph, int s,int d,
                                boolean[] visited) {
        // visit the node
        visited[s] = true;
        Edge[] pred = new Edge[rGraph.length];
        for (Edge e : rGraph[s]) {
            // after flow set the path to distination true by dfs
                if ((e.cap-e.f) > 0 && !visited[e.dest]) {
                    dfs(rGraph, e.dest,d, visited);
                }
        }
    }
    /*
    * this method main
    * @param args Unused.
    * <pre>
    * {@code
    * List<Edge>[] graph = createGraph(7);
    *    addEdge(graph, 1, 2, 2);
    *    addEdge(graph, 1, 3, 7);
    *    addEdge(graph, 2, 4, 3);
    *    addEdge(graph, 2, 5, 4);
    *    addEdge(graph, 3, 4, 4);
    *    addEdge(graph, 3, 5, 2);
    *    addEdge(graph, 4, 6, 1);
    *    addEdge(graph, 5, 6, 5);
    *    System.out.println("start with flow zero");
    *    maxFlow(graph, 1,6 );
    * }
    * </pre>
    * @return Nothing
    */
    public static void main(String[] args) {
        // create graph with edges
        List<Edge>[] graph = createGraph(7);
        addEdge(graph, 1, 2, 2);
        addEdge(graph, 1, 3, 7);
        addEdge(graph, 2, 4, 3);
        addEdge(graph, 2, 5, 4);
        addEdge(graph, 3, 4, 4);
        addEdge(graph, 3, 5, 2);
        addEdge(graph, 4, 6, 1);
        addEdge(graph, 5, 6, 5);
                
        
        System.out.println("start with flow '0'");
        maxFlow(graph, 1,6 );
       
    }
    
}
