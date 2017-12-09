import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArraySet.java.
 *
 * Provides an implementation of the Set interface using an
 * array as the underlying data structure. Values in the array
 * are kept in ascending natural order and, where possible,
 * methods take advantage of this. Many of the methods with parameters
 * of type ArraySet are specifically designed to take advantage
 * of the ordered array implementation.
 *
 * @authorLauren (Ren) McFaden (lam0080@auburn.edu)
 * @authorDean Hendrix (dh@auburn.edu)
 * @version2017-10-11
 *
 */
public class ArraySet<T extends Comparable<? super T>> implements Set<T> {

   ////////////////////////////////////////////
   // DO NOT CHANGE THE FOLLOWING TWO FIELDS //
   ////////////////////////////////////////////
   T[] elements;
   int size;

   ////////////////////////////////////
   // DO NOT CHANGE THIS CONSTRUCTOR //
   ////////////////////////////////////
   /**
    * Instantiates an empty set.
    */
   @SuppressWarnings("unchecked")
   public ArraySet() {
      elements = (T[]) new Comparable[1];
      size = 0;
   }
   
   @SuppressWarnings("unchecked")
     private ArraySet(T[] a, int arraySize) {
      elements = (T[]) new Comparable[arraySize];
      System.arraycopy(a, 0, elements, 0, arraySize);
      size = arraySize;
   }

   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   @Override
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements,
    *               false otherwise.
    */
   @Override
   public boolean isEmpty() {
      return size == 0;
   }

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this ArraySet.
    *
    * @return a string representation of this ArraySet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }
   
   private void resize(int capacity) {
      T[] a = Arrays.copyOf(elements, capacity);
      elements = a;
   }
   
/**
    * Ensures the collection contains the specified element. Elements are
    * maintained in ascending natural order at all times. Neither duplicate nor
    * null values are allowed.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */

   public boolean add(T element) {
      if (this.contains(element)) {
         return false;
      }
   
      if (isFull()) {
         resize(elements.length * 2);
      }
   
      int index = 0;
      int i = 0;
   
      while (i < size && element.compareTo(elements[i]) > 0) {
         i++;
      }
      for (int j = size; j > i; j--) {
         elements[j] = elements[j - 1];
      }
      elements[i] = element;
      size++;
      return true;
   }

   private boolean isFull() {
      if (size == elements.length) {
         return true;
      }
      return false;
   }

   private int locate(T element) {
   
      int low = 0;
      int high = size - 1;
      int mid = 0;
      while (low <= high) {
         mid = (high + low) / 2;
         int compareElements =
            elements[mid].compareTo(element);
         if (compareElements > 0) {
            high = mid - 1;
         }
      
         if (compareElements < 0) {
            low = mid + 1;
         }
      
         if (compareElements == 0) {
            return mid;
         }
      }
      return -1;
   
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. Elements are maintained in ascending natural
    * order at all times.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */

   public boolean remove(T element) {
   
      int i = locate(element);
      if (i >= size || i == -1) {
         return false;
      }
      size--;
      for (int j = i; j < size; j++) {
         elements[j] = elements[j + 1];
      }
     
      if (size > 0 && size < (elements.length / 4)) {
         resize(elements.length / 2);
      }
      return true;
   }

   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection
    *                   is to be tested.
    * @return  true if this collection contains the specified element,
    *               false otherwise.
    */

   public boolean contains(T element) {
      int i = 0;
      while (this.size > i) {
         if (!elements[i].equals(element)) {
            i++;
         }
      
         if (i == size) {
            break;
         }
      
         if (elements[i].equals(element)) {
            return true;
         }
      }
      return false;
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */

   public boolean equals(Set<T> s) {
   
      if (s.size() != this.size() && complement(s).size() != 0) {
         return false;
      }
      return true;
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
     
   public boolean equals(ArraySet<T> s) {
      if (s.size() != this.size() && complement(s).size() != 0) {
         return false;
      }
      return true;
   }

  /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */

   public Set<T> union(Set<T> s) {
      ArraySet<T> as = new ArraySet<T>();
      Iterator<T> itr = s.iterator();
   
      for (int i = 0; i < size; i++) {
         as.add(elements[i]);
      }
      while (itr.hasNext()) {
         as.add(itr.next());
      }
      return as;
   }
   
 /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */

   public Set<T> union(ArraySet<T> s) {
      ArraySet<T> as = new ArraySet<T>();
      Iterator<T> itr = s.iterator();
   
      for (int i = 0; i < size; i++) {
         as.add(elements[i]);
      }
      while (itr.hasNext()) {
         as.add(itr.next());
      }
      return as;
   }

   /**
    * Returns a set that is the intersection of this set
    * and the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */

   @SuppressWarnings("unchecked")
     public Set<T> intersection(Set<T> s) {
      ArraySet<T> as = new ArraySet<T>();
      for (T value : elements) {
         if (s.contains(value)) {
            as.add(value);
         }
      }
      return as;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */

   public Set<T> intersection(ArraySet<T> s) {
      ArraySet<T> as = new ArraySet<T>();
      for (T value : elements) {
         if (s.contains(value)) {
            as.add(value);
         }
      }
      return as;
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */

   public Set<T> complement(Set<T> s) {
      if (s == null || s.isEmpty()) {
         return this;
      }
      
      if (isEmpty()) {
         return this;
      }
   
      if (this == null) {
         return this;
      }
   
      ArraySet<T> as = new ArraySet<T>();
      for (T value : this) {
         if (!s.contains(value)) {
            as.add(value);
         }
      }
      return as;
   }

/**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */

   @SuppressWarnings("unchecked")
   public Set<T> complement(ArraySet<T> s) {
      if (s == null || s.isEmpty()) {
         return this;
      }
   
      if (isEmpty()) {
         return this;
      }
   
      if (this == null) {
         return this;
      }
   
      ArraySet<T> as = new ArraySet<T>();
   
      for (T value : this) {
         if (!s.contains(value)) {
            as.add(value);
         }
      }
      return as;
   }

/**
    * Returns an iterator over the elements in this ArraySet.
    * No specific order can be assumed.
    *
    * @return  an iterator over the elements in this ArraySet
    */

   public Iterator<T> iterator() {
      return new ArrayIterator(elements, size);
   }
  /**
    * Returns an iterator over the elements in this ArraySet.
    * The elements are returned in descending order.
    *
    * @return  an iterator over the elements in this ArraySet
    */
    
   public Iterator<T> descendingIterator() {
      return new DescendingIterator(elements, size);
   }

   /**
    * Returns an iterator over the members of the power set
    * of this ArraySet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */

   public Iterator<Set<T>> powerSetIterator() {
      return new MyPowerSetIterator(elements, size);
   }

   private class ArrayIterator implements Iterator<T> {
   
      private T[] items;
      private int count;
      private int current;
   
      public ArrayIterator(T[] elements, int size) {
      
         items = elements;
         count = size;
         current = 0;
      }
   
      @Override
          public boolean hasNext() {
         return (current < count);
      }
   
      @Override
          public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
      
         T result = items[current];
         current++;
         return result;
      }
   
      @Override
          public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private class DescendingIterator implements Iterator<T> {
   
      private T[] items;
      private int count;
      private int current;
   
      public DescendingIterator(T[] elements, int size) {
      
         items = elements;
         count = 0;
         current = size - 1;
      }
   
      @Override
          public boolean hasNext() {
         return (current >= count);
      }
   
      @Override
          public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         return items[current--];
      }
   
      @Override
          public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private class MyPowerSetIterator implements Iterator<Set<T>> {
   
      private T[] items;
      private int count;
      private int current;
      private int bit;
   
      public MyPowerSetIterator(T[] elements, int size) {
      
         items = elements;
         count = size;
         current = 0;
         bit = 0;
      }
   
      public boolean hasNext() {
         return (current < (int) Math.pow(2, count));
      }
   
      public Set<T> next() {
         Set<T> output = new ArraySet<T>();
         int mask = 1;
         for (int i = 0; i < count; i++) {
            if ((bit & mask) != 0) {
               output.add(items[i]);
            }
         }
         current++;
         bit = 0;
         return output;
      }
   
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}