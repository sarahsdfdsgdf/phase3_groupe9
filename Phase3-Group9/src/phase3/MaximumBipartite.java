/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phase3;


import java.util.*;
import java.lang.*;
import java.io.*;
  
class MaximumBipartite
{ 
    String applicants[]={"Ahme","Mahmoud","Ema","Fatimah","Kamel","Nojoo"};
    String hospitals[]={"King Abdelaziz University","King Fahd","East Jeddah","King Fahad Armed Forces","King Faisal Specialist","Ministry of National Guard"};
    // number of applicants
    static final int M = 6;
    // number of hospitals
    static final int N = 6;
    int countMatching;
    /*
    * this method for Bipartite matching
    * @param bpGraph This is the first paramter to bpm method
    * @param u This is the second paramter to bpm method
    * @param seen This is the third paramter to bpm method
    * @param matchR This is the forth paramter to bpm method
    * <pre>
    * {@code
    *  for (int v = 0; v < N; v++)
    *    {
    *        if (bpGraph[u][v] && !seen[v])
    *        {
    *            seen[v] = true; 
    *            if (matchR[v] < 0 || bpm(bpGraph, matchR[v],seen, matchR))
    *            {
    *                System.out.println(applicants[u]+" take "+hospitals[v]);
    *                matchR[v] = u;
    *                this.countMatching++;
    *                return true;
    *            }
    *        }
    *    }
    *    return false;
    * }
    * </pre>
    * @return boolean value if the applicants take the job or not
    */
    boolean bpm(boolean bpGraph[][], int u,boolean seen[], int matchR[])
    {
        // for on hospitals
        for (int v = 0; v < N; v++)
        {   //if this jobs not seen and from applicant graph
            if (bpGraph[u][v] && !seen[v])
            {
                seen[v] = true; 
                // if no other take it
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v],seen, matchR))
                {
                    System.out.println(applicants[u]+" take "+hospitals[v]);
                    matchR[v] = u;
                    this.countMatching++;
                    return true;
                }
            }
        }
        return false;
    }
      /*
    * this method for  Maximum Bipartite algorithm
    * @param bpGraph This is the first paramter to maxBPM method
    * <pre>
    * {@code
    *  int matchR[] = new int[N];
    *    for(int i = 0; i < N; ++i)
    *        matchR[i] = -1;
    *    int result = 0; 
    *    for (int applicants = 0; applicants < M; applicants++)
    *    {
    *        boolean seen[] =new boolean[N] ;
    *        for(int i = 0; i < N; ++i)
    *            seen[i] = false;
    *        if (bpm(bpGraph, applicants, seen, matchR))
    *        {
    *            result++;
    *            System.out.println("Update maximum number of applicants: "+result);
    *        }
    *    }
    *    return result;
    * }
    * </pre>
    * @return result of method 
    */
    int maxBPM(boolean bpGraph[][])
    {
        int matchR[] = new int[N];
        // matching array
        for(int i = 0; i < N; ++i)
            matchR[i] = -1;
  
        int result = 0; 
        for (int applicants = 0; applicants < M; applicants++)
        {
            boolean seen[] =new boolean[N] ;
            for(int i = 0; i < N; ++i)
                seen[i] = false;
            // if the applicants find job
            if (bpm(bpGraph, applicants, seen, matchR))
            {
                result++;
                System.out.println("------------------------------------------");
                System.out.println("Update maximum number of applicants: "+result);
            }
            
        }
        return result;
    }
      /*
    * this method main  
    * @param args Unused.
    * <pre>
    * {@code
    *  boolean bpGraph[][] = new boolean[][]{
    *                          {true, true, false, false, false, false},
    *                          {false, false, false, false, false, true},
    *                          {true, false, false, true, false, false},
    *                          {false, false, true, false, false, false},
    *                          {false, false, false, true, true, false},
    *                          {false, false, false, false, false, true}
    *                        };
    *    MaximumBipartite m = new MaximumBipartite();
    *    m.countMatching = 0;
    *    System.out.println("maximum number of application: 0");
    *    System.out.println( "Maximum number of applicants that can be assigned to hospitals : "+m.maxBPM(bpGraph));
    *    System.out.println( "Maximum bipartite matching : "+m.countMatching);
    * }
    * </pre>
    * @return Nothing 
    */
    public static void main (String[] args) throws java.lang.Exception
    {
        //define graph
        boolean bpGraph[][] = new boolean[][]{
                              {true, true, false, false, false, false},
                              {false, false, false, false, false, true},
                              {true, false, false, true, false, false},
                              {false, false, true, false, false, false},
                              {false, false, false, true, true, false},
                              {false, false, false, false, false, true}
                            };
        
        MaximumBipartite m = new MaximumBipartite();
        // total for matching
        m.countMatching = 0;
        System.out.println("maximum number of application: 0");
        System.out.println( "Maximum number of applicants that can be assigned to hospitals : "+m.maxBPM(bpGraph));
        System.out.println( "Maximum bipartite matching : "+m.countMatching);
    }
}
