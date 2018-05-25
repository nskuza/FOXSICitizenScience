public interface List<T extends Comparable<T>> {
  /*
   * Add an element to end of the list. If element is null,
   * it will NOT add it and return false.  Otherwise, it
   * will add it and return true. Updates isSorted to false.
  */
  boolean add(T element);

  /*
   *  Add an element at specific index. This method should
   * also shift the element currently at that position (if
   * any) and any subsequent elements to the right (adds
   * one to their indices). If element is null, or if index
   * index is out-of-bounds (index < 0 or index >= size_of_list),
   * it will NOT add it and return false. Otherwise it will
   * add it and return true. See size() for the definition
   * of size of list. Also updates isSorted variable to false.
   */
  boolean add(int index, T element);

  /*
   * Remove all elements from the list.
   */
  void clear();

  /*
   * Return true if element is in the list and false
   * otherwise. If isSorted is true, uses the ordering
   * of the list to increase the efficiency of the search.
   */
  boolean contains(T element);

  /*
   *  Return the element at given index. If index is
   * out-of-bounds, it will return null.
   *
   */
  T get(int index);

  /*
   * Return the first index of element in the list. If element
   * is null or not found in the list, return -1. If isSorted is
   * true, uses the ordering of the list to increase the efficiency
   * of the search.
   */
  int indexOf(T element);

  /*
   * Return true if the list is empty and false otherwise.
   */
  boolean isEmpty();

  /*
   * Same as indexOf(), except it will return the last index
   * of element. If isSorted is true, uses the ordering
   * of the list to increase the efficiency of the search.
   */
  int lastIndexOf(T element);

  /*
   * Replace the element at index with element and return the
   * element that was previously at index. If index is
   * out-of-bounds or element is null, do nothing with element
   * and return null.
   */
  T set(int index, T element);

  /*
   * Return the number of elements in the list. For example, if
   * 4 elements added to a list, size will return 4, while the
   * last index in the list will be 3. Updates isSorted.
   */
  int size();

  /*
   * Sort the elements of the list. If order is true, sort the
   * list in increasing (alphabeticaly) order. If order is
   * false, sort the list in decreasing (reverse alphabetical)
   * order. Note: only set isSorted to true if sorted in ASCENDING
   * order.
   * If isSorted is true, and the order is true, do NOT resort.
   * Hint: Since T extends Comparable, you will find it useful
   * to use the public int compareTo(T o) method.
   */
  void sort(boolean order);

  /*
   * Remove the first instance of element from the list. This
   * method should also shifts any subsequent elements to the
   * left (subtracts one from their indices). If successful,
   * return true. If element is not found in the list, return
   * false.
   */
  boolean remove(T element);

  /*
   * Remove whatever is at index index in the list and return
   * it. If index is out-of-bounds, return null. Shift the
   * indices as in the other remove.
   */
  T remove(int index);

  /*
   * Note that this method exists for debugging purposes.
   * It calls the toString method of the underlying elements in
   * the list in order to create a String representation of the list.
   * The format of the toString should appear as follows:
   * Element1
   * Element2
   * .
   * .
   * .
   * Elementn
   * Watch out for extra spaces or carriage returns. Each element
   * should be printed on its own line.
   */
  String toString();
}  // End of interface definition.
