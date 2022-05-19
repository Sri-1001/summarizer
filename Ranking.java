package com.search.summary;

import java.util.*;

//Comparator to rank people by score in ascending order
//No tiebreaking for equal scores is considered in this question
class PairComparator implements Comparator<List<Object>> {

  public int compare(List<Object> o1, List<Object> o2) {
      return (Integer)o1.get(0) - (Integer)o2.get(0);
  }
}


public class Ranking {
  private void run() {
      Scanner sc = new Scanner(System.in);
      ArrayList<List<Object>> inputPairs = new ArrayList<List<Object>>();
      ArrayList<String> nameIterList = new ArrayList<String>();//To store names in scanned order
      HashMap<String, Integer> dupeCount = new HashMap<String, Integer>();//To consider cases where there are people with same names
      int count = sc.nextInt();
      for (int i=0;i<count;i++) {
          String name = sc.next();
          int score = sc.nextInt();
          name = checkDuplicates(nameIterList,name,dupeCount);//returns a unique name after considering duplicates
          List<Object> pair=null;// = List.of(score,name);//simulates a struct data structure in C with non-homogeneous elements
          inputPairs.add(pair);
          nameIterList.add(name);

      }


      Collections.sort(inputPairs, (new PairComparator()).reversed());//descending order sorting

      HashMap<String,Integer> nameRank = new HashMap<String,Integer>();//name and respective rank in O(1) time
      makeTable(nameRank,inputPairs);
      for (String name: nameIterList) {
          System.out.println(String.format("%s %d",name.trim(),nameRank.get(name)));
      } //for displaying purposes, repeated name is printed


  }

  public static void main(String[] args) {
      Ranking newRanking = new Ranking();
      newRanking.run();
  }

  public static void makeTable(HashMap<String,Integer> nameRank, ArrayList<List<Object>> inputPairs) {
      int lowestRank = 1;
      int previousScore = (Integer)inputPairs.get(0).get(0);
      for (int i=0;i<inputPairs.size();i++) {
          List<Object> pairs = inputPairs.get(i);
          String name = (String) pairs.get(1);
          int score = (Integer) pairs.get(0);
          int currentRank = i+1;//default rank if there are no tiebreakers
          if (score==previousScore) {
              currentRank = lowestRank;//takes the smallest possible rank for a tie-breaker
          } else {
              lowestRank = currentRank;//updates the smallest possible rank as tie-breaker is broken
              previousScore = score;
          }
          nameRank.put(name,currentRank);//updates HashMap
      }
  }


  public static String checkDuplicates(ArrayList<String> nameList, String name, HashMap<String,Integer> dupeCount) {
      if (dupeCount.containsKey(name)) {
          int count = dupeCount.get(name);

          dupeCount.replace(name,count+1); //updates the duplicateTable
          return name+ new String(new char[count]).replace('\0', ' ');//new name is appending with spaces, trimmed later on
      } else {//entry not found, add in as the first one
          dupeCount.put(name,1);
          return name;//no change
      }

  }
}