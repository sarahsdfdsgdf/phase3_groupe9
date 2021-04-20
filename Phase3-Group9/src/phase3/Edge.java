/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phase3;

 public  class Edge {
    int src, dest, rev, cap, f;
    /*
    * this method is constractor
    * @param s This is the first paramter to Edge method
    * @param t This is the second paramter to Edge method
    * @param rev This is the third paramter to Edge method
    * @param cap This is the forth paramter to Edge method
    * <pre>
    * {@code
    * this.src = s;
    *  this.dest = t;
    *  this.rev = rev;
    *  this.cap = cap;
    * }
    * </pre>
    * @return Nothing
    */
    public Edge(int s, int t, int rev, int cap) {
      this.src = s;
      this.dest = t;
      this.rev = rev;
      this.cap = cap;
    }
  }