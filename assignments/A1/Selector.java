import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Ren McFaden (lam0080@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-08-22
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
    
    
   public static int min(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      if (a.length == 1) {
         return a[0];
      }
      int value = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] < value) {
            value = a[i];
         }
      }
      return value;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      if (a.length == 1) {
         return a[0];
      }
      int value = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > value) {
            value = a[i];
         }
      }
      return value;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      
      int[] newArray = a.clone();
      Arrays.sort(newArray);
         
      int counter = 0;
      
      for (int i = 1; i < a.length; i++) {
         if ( newArray[i] == newArray[i - 1]) {
            newArray[i - 1] = 0;
         }
      }
       
      for (int i = 0; i < newArray.length; i++) {
         if ( newArray[i] != 0) {
            counter++;
         }
      }
     
      int[] finalArray = new int[counter];
      int j = 0;
     
      for (int i = 0; i < newArray.length; i++) {
         if (newArray[i] != 0) {
            finalArray[j] = newArray[i]; 
            j++;
         }
      }
     
      if (k < 1 || k > finalArray.length) {
         throw new IllegalArgumentException();
      }
     
      return finalArray[k - 1];
       
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      
      int[] newArray = a.clone();
      Arrays.sort(newArray);
         
      int counter = 0;
      
      for (int i = 1; i < a.length; i++) {
         if ( newArray[i] == newArray[i - 1]) {
            newArray[i - 1] = 0;
         }
      }
       
      for (int i = 0; i < newArray.length; i++) {
         if ( newArray[i] != 0) {
            counter++;
         }
      }
     
      int[] finalArray = new int[counter];
      int j = 0;
     
      for (int i = 0; i < newArray.length; i++) {
         if (newArray[i] != 0) {
            finalArray[j] = newArray[i]; 
            j++;
         }
      }
     
      if (k < 1 || k > finalArray.length) {
         throw new IllegalArgumentException();
      }
     
      return finalArray[finalArray.length - k];
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException();
      }
      int num = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            num++;
         }
      }
      int[] newArray = new int[num];
      int k = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            newArray[k] = a[i];
            k++;
         }
      }
      return newArray;
   }
   

   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if ((a == null) || (a.length == 0) || key > max(a)) {
         throw new IllegalArgumentException();
      }
      int [] ro = range(a, key, max(a));
      return min(ro);
   
   }



   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if ((a == null) || (a.length == 0) || key < min(a)) {
         throw new IllegalArgumentException();
      }
      
      int [] ab = range(a, min(a), key);
      int final1 = max(ab);
      return final1;
   }
}
