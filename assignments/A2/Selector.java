import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Ren McFaden (lam0080@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2017-09-05
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (coll.size() == 1) {
         return coll.iterator().next();
      }
      T low = coll.iterator().next();
      for (T value : coll) {
         if (comp.compare(value, low) < 0) {
            low = value;
         }
      }
      return low;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (coll.size() == 1) {
         return coll.iterator().next();
      }
      T high = coll.iterator().next();
      for (T value : coll) {
         if (comp.compare(value, high) > 0) {
            high = value;
         }
      }
      return high;
   }
      

   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      if (coll.size() == 1 && k == 1) {
         return coll.iterator().next();
      }
      
      ArrayList<T> newArray = new ArrayList<T>(coll);
      java.util.Collections.sort(newArray, comp);
      
      if (k > newArray.size() || k <= 0) {
         throw new NoSuchElementException();
      }
         
      int newCounter = 0;
      int finalCounter = 0;
      int size = newArray.size();
      
      for (int i = 0; i < newArray.size() - 1; i++) {
         while (newArray.size() > 1 && i < newArray.size() - 1
               && newArray.get(i) == newArray.get(i + 1)) {
            newArray.remove(i);
            newCounter++;
         }
         
      }
      finalCounter = size - newCounter;
         
      if ( k > finalCounter) {
         throw new NoSuchElementException();
      }
     
        
      return newArray.get(k - 1);    
   }

   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      if (coll.size() == 1 && k == 1) {
         return coll.iterator().next();
      }
      
      ArrayList<T> newArray = new ArrayList<T>(coll);
      java.util.Collections.sort(newArray, java.util.Collections.reverseOrder(comp));
      
      if (k > newArray.size() || k <= 0) {
         throw new NoSuchElementException();
      }
         
      int newCounter = 0;
      int finalCounter = 0;
      int size = newArray.size();
      
      for (int i = 0; i < newArray.size() - 1; i++) {
         while (newArray.size() > 1 && i < newArray.size() - 1
               && newArray.get(i) == newArray.get(i + 1)) {
            newArray.remove(i);
            newCounter++;
         }
         
      }
      finalCounter = size - newCounter;
         
      if ( k > finalCounter) {
         throw new NoSuchElementException();
      }
     
        
      return newArray.get(k - 1);  
   
   }

   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param   coll the Collection from which the range values are selected
    * @param   low the lower bound of the range
    * @param   high the upper bound of the range
    * @param   comp the Comparator that defines the total order on T
    * @return  a Collection of values between low and high
    * @throws  IllegalArgumentException as per above
    * @throws  oSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      List<T> copy = new ArrayList<T>(coll);
      List<T> newArray = new ArrayList<T>(0);
      if (comp.compare(low, high) <= 0) {
      
         for (T value : copy) {
            if (comp.compare(value, low) >= 0 && comp.compare(value, high) <= 0) {
               newArray.add(value);
            }
         }
      }
      if (newArray.size() == 0) {
         throw new NoSuchElementException();
      }
      return newArray;
   }
   
   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      Collection<T> range = range(coll, key, (max(coll, comp)), comp);
      if (range.isEmpty()) {
         throw new NoSuchElementException();
      }
      T value = min(range, comp);
      return value;
   }
   


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      Collection<T> range = range(coll, (min(coll, comp)), key, comp);
      if (range.isEmpty()) {
         throw new NoSuchElementException();
      }
      T value = max(range, comp);
      return value;
   }
}
