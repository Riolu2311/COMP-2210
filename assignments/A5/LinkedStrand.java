/**
* LinkedStrand.java
* Provides a linked chunk list implementation of the DnaStrand interface.
* 
* @author   Lauren(Ren) McFaden (lam0080@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-10-17
*
*/
public class LinkedStrand implements DnaStrand {

   /**
    * Container for storing DNA information. A given DNA strand is
    * represented by a chain of nodes.
    *
    * D O   N O T   M A K E   A N Y   C H A N G E S   T O
    *
    * T H E   N O D E   C L A S S 
    *
    */
   class Node {
      String dnaInfo;
      Node next;
   
      public Node() {
         dnaInfo = "";
         next = null;
      }
   
      public Node(String s, Node n) {
         dnaInfo = s;
         next = n;
      }
   }

   /** An empty strand. */
   public static final LinkedStrand EMPTY_STRAND = new LinkedStrand();

   /** The first and last node in this LinkedStrand. */
   // D O   N O T   C H A N G E   T H E S E   F I E L D S 
   Node front;
   Node rear;


   /** The number of nucleotides in this strand. */
   long size;

   /**
    * Create a strand representing an empty DNA strand, length of zero.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    */
   public LinkedStrand() {
      front = null;
      rear = null;
      size = 0;
   }

   /**
    * Create a strand representing s. No error checking is done to see if s represents
    * valid genomic/DNA data.
    *
    * @param s is the source of cgat data for this strand
    */
   public LinkedStrand(String s) {
      if (s.length() > 0) {
         front = new Node(s, null);
         rear = front;
         size = s.length();
      }
      
   }

   /**
    * Appends the parameter to this strand changing this strand to represent
    * the addition of new characters/base-pairs, e.g., changing this strand's
    * size.
    * 
    * If possible implementations should take advantage of optimizations
    * possible if the parameter is of the same type as the strand to which data
    * is appended.
    * 
    * @param dna is the strand being appended
    * @return this strand after the data has been added
    */
   @Override
   public DnaStrand append(DnaStrand dna) {
      if (dna instanceof LinkedStrand) {
         LinkedStrand sStrand = (LinkedStrand) dna;
         if (dna.size() == 0) {
            return this;
         }
         else if (size == 0) {
            front = sStrand.front;
            rear = front;
            size += dna.size();
            return this;
         }
           
         rear.next = sStrand.front;
         rear = sStrand.rear;
         size += dna.size();
         return this;
      }
      else {
         return append(dna.toString());
      }
   }

   /**
    * Similar to append with a DnaStrand parameter in
    * functionality, but fewer optimizations are possible. Typically this
    * method will be called by the append method with an DNAStrand
    * parameter if the DnaStrand passed to that other append method isn't the same 
    * class as the strand being appended to.
    * 
    * @param dna is the string appended to this strand
    * @return this strand after the data has been added
    */
   @Override
   public DnaStrand append(String dna) {
      if (dna.length() < 1) {
         return this;
      }
      else if (size == 0) {
         front = rear = new Node(dna, null);
      } else {
         rear = rear.next = new Node(dna, null);
      }
      size += dna.length();
      return this;
   }

   /**
    * Cut this strand at every occurrence of enzyme, replacing
    * every occurrence of enzyme with splice.
    *
    * @param enzyme is the pattern/strand searched for and replaced
    * @param splice is the pattern/strand replacing each occurrence of enzyme
    * @return the new strand leaving the original strand unchanged.
    */
   @Override
   public DnaStrand cutAndSplice(String enzyme, String splice) {
      DnaStrand output = new LinkedStrand();
      if (!front.dnaInfo.contains(enzyme)) {
         return EMPTY_STRAND;
      }
      int i = 0;
      int j = front.dnaInfo.indexOf(enzyme, i);
      while (j > -1) {
         output = output.append(front.dnaInfo.substring(i, j));
         output = output.append(splice);
         i = j + enzyme.length();
         j = front.dnaInfo.indexOf(enzyme, i);
      }
      output.append(front.dnaInfo.substring(front.dnaInfo.lastIndexOf(enzyme) + enzyme.length()));
      return output;
   }

   /**
    * Simulate a restriction enzyme cut by finding the first occurrence of
    * enzyme in this strand and replacing this strand with what comes before
    * the enzyme while returning a new strand representing what comes after the
    * enzyme. If the enzyme isn't found, this strand is unaffected and an empty
    * strand with size equal to zero is returned.
    * 
    * @param enzyme is the string being searched for
    * @return the part of the strand that comes after the enzyme
    */
   @Override
   public DnaStrand cutWith(String enzyme) {
      if (front.next != null) {
         throw new IllegalStateException();
      }
      DnaStrand newEnzyme = new LinkedStrand();
    
      String strand = front.dnaInfo;
      int loc = strand.indexOf(enzyme);
      if (loc == -1) {
         DnaStrand empty = new LinkedStrand();
         return empty;
      }
      if (strand.equals(enzyme)) {
         initializeFrom(front.dnaInfo.substring(0, 0));
         return newEnzyme;
      }
      DnaStrand ret = new LinkedStrand(strand.substring(loc + enzyme.length()));
      initializeFrom(strand.substring(0, loc));
      return ret;
   }

   /**
    * Initialize by copying DNA data from the string into this strand,
    * replacing any data that was stored. The parameter should contain only
    * valid DNA characters, no error checking is done by the this method.
    * 
    * @param source is the string used to initialize this strand
    */
   @Override
   public void initializeFrom(String source) {
      if (source == null) {
         front = new Node();
         front.dnaInfo = null;
         rear = front;
         size = 0;
      }
      
      else if (source.length() == 0) {
         front = null;
         rear = front;
         size = 0;
      }
      
      else {
         Node sSource = new Node(source, null);
         front = sSource;
         rear = front;
         size = source.length();
      }
      
   }

   /**
    * Returns the number of elements/base-pairs/nucleotides in this strand.
    * @return the number of base-pairs in this strand
    */
   @Override
   public long size() {
      return size;
   }

   /**
    * Returns a string representation of this LinkedStrand.
    */
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder("");
      Node p = front;
      while (p != null) {
         sb.append(p.dnaInfo);
         p = p.next;
      }
      return sb.toString();
   }

}