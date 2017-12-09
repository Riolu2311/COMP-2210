import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Lauren (Ren) McFaden (lam0080@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2017-11-06
 */
public class Doublets implements WordLadderGame {

   ////////////////////////////////////////////
   // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
   ////////////////////////////////////////////

   // A word ladder with no words. Used as the return value for the ladder methods
   // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

   ///////////////////////////////////////////////////////////////////////////////
   // Fill in implementations of all the WordLadderGame interface methods here. //
   ///////////////////////////////////////////////////////////////////////////////

   public int getHammingDistance(String string1, String string2) {
      int result = 0;
   
      if (string1.length() != string2.length()) {
         return -1; 
      }
   
      for (int i = 0; i < string1.length(); i++) {
         if (string1.charAt(i) != string2.charAt(i)) {
            result++;
         }
      }
      return result;
   }
   
   public List<String> getLadder(String start, String end) {
      List<String> result = new ArrayList<String>();
        
      if (start.equals(end)) {
         result.add(start);
         return result;
      }
      else if (start.length() != end.length()) {
         return EMPTY_LADDER;
      }
      else if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
          
      TreeSet<String> one = new TreeSet<>();
      Deque<String> stack = new ArrayDeque<>();
       
      stack.addLast(start);
      one.add(start);
      while (!stack.isEmpty()) {
       
         String current = stack.peekLast();
         if (current.equals(end)) {
            break;
         }
         List<String> spot1 = getNeighbors(current);
         List<String> spot = new ArrayList<>();
          
         for (String word : spot1) {
            if (!one.contains(word)) {
               spot.add(word);
            }
         }
         if (!spot.isEmpty()) {
            stack.addLast(spot.get(0));
            one.add(spot.get(0));
         }
         else {
            stack.removeLast();
         }
      }
      result.addAll(stack);
      return result;
   }

   public List<String> getMinLadder(String start, String end) {
      List<String> ladder = new ArrayList<String>();
      if (start.equals(end)) {
         ladder.add(start);
         return ladder;
      }
      else if (start.length() != end.length()) {
         return EMPTY_LADDER;
      }
      else if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
    
      Deque<Node> x = new ArrayDeque<>();
      TreeSet<String> place = new TreeSet<>();
    
      place.add(start);
      x.addLast(new Node(start, null));
      while (!x.isEmpty()) {
       
         Node j = x.removeFirst();
         String position = j.position;
          
         for (String spots : getNeighbors(position)) {
            if (!place.contains(spots)) {
               place.add(spots);
               x.addLast(new Node(spots, j));
            }
            if (spots.equals(end)) {
             
               Node i = x.removeLast();
               
               while (i != null) {
                  ladder.add(0, i.position);
                  i = i.predecessor;
               }
               return ladder;
            }
         }
      }      
      return EMPTY_LADDER;
   }

   public List<String> getNeighbors(String word) {
      List<String> neighbor = new ArrayList<String>();
      TreeSet<String> list = new TreeSet<String>();
       
      if (word == null)
         return EMPTY_LADDER;
      
      for (String full : lexicon) {
         if (getHammingDistance(word, full) == 1)
            neighbor.add(full);
      }
      
      return neighbor;
   }

   public int getWordCount() {
      int size = lexicon.size();
      return size;
   }

   public boolean isWord(String string) {
      if (lexicon.contains(string)) {
         return true;
      }
      return false;
   }

   public boolean isWordLadder(List<String> order) {
      int count = 0;
      if ((order == null) || (order.isEmpty())) {
         return false;
      }
      
      for (int i = 0; i < order.size() - 1; i++) {
         if (isWord(order.get(i)) != true || isWord(order.get(i + 1)) != true 
            || (getHammingDistance(order.get(i), order.get(i + 1)) != 1))
            count++;  
      }
      boolean y = (count == 0);
      return y;
   }
   
   private class Node {
      String position;
      Node predecessor;
   
      public Node(String b, Node pred) {
         position = b;
         predecessor = pred;
      }
   }
}