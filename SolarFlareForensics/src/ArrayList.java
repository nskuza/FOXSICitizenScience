/**
 * Created by skuza004
 * Project2 submission (ArrayList)
 */
public class ArrayList<T extends Comparable<T>> implements List<T> {
    private int size, sizeOfList;
    private T[] a;
    private boolean isSorted;

    // sizeOfList is the full length of the list and size is the number of elements.
    public ArrayList(){
        sizeOfList = 2;
        size = 0;
        isSorted = true;
        a = (T[]) new Comparable[sizeOfList];
    }

    // Created as an extra function to double and Copy the array when it fills.
    private void doubleAndCopy(){
        sizeOfList = 2*sizeOfList;
        T[] temp = (T[]) new Comparable[sizeOfList];
        for(int i = 0; i < size; i++){
            temp[i] = a[i];
        }
        a = temp;
    }


    /*
  * Add an element to end of the list. If element is null,
  * it will NOT add it and return false.  Otherwise, it
  * will add it and return true. Updates isSorted to false.
 */
     public boolean add(T element){
         if(element == null){
             return false;
         }
         else if (size < sizeOfList){
             a[size] = element;
             isSorted = false;
             size++;
             return true;
         }
         else {
             doubleAndCopy();
             a[size] = element;
             isSorted = false;
             size++;
             return true;
         }

     }

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
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= sizeOfList) {
            return false;
        } else if (size < sizeOfList) {
            for(int i = size; i > index; i--){
                a[i] = a[i-1];
            }
            a[index] = element;
            isSorted = false;
            size++;
            return true;
        } else {
            doubleAndCopy();
            for(int i = size; i > index; i--){
                a[i] = a[i-1];
            }
            a[index] = element;
            isSorted = false;
            size++;
            return true;
        }
    }

    /*
     * Remove all elements from the list.
     */
    public void clear(){
        for(int i = 0; i < size; i++){
            a[i] = null;
        }
        size = 0;
        sizeOfList = 2;
    }

    /*
     * Return true if element is in the list and false
     * otherwise. If isSorted is true, uses the ordering
     * of the list to increase the efficiency of the search.
     */
    public boolean contains(T element){
        for(int i = 0; i < size; i++ ){
            if(a[i] == element){
                return true;
            }
        }
        return false;
    }

    /*
     *  Return the element at given index. If index is
     * out-of-bounds, it will return null.
     *
     */
    public T get(int index){
        if(index < 0 || index >=sizeOfList){
            return null;
        }
        else {
            return a[index];
        }
    }

    /*
     * Return the first index of element in the list. If element
     * is null or not found in the list, return -1. If isSorted is
     * true, uses the ordering of the list to increase the efficiency
     * of the search.
     */
    public int indexOf(T element) {
        if (element == null){
            return -1;
        }
        else{
            for(int i = 0; i < size; i++){
                if(a[i] == element){
                    return i;
                }
            }
        }
        return -1;
    }
    /*
     * Return true if the list is empty and false otherwise.
     */
    public boolean isEmpty(){
        for(int i = 0; i < size; i++){
            if(a[i] != null){
                return false;
            }
        }
        return true;
    }

    /*
     * Same as indexOf(), except it will return the last index
     * of element. If isSorted is true, uses the ordering
     * of the list to increase the efficiency of the search.
     */
    public int lastIndexOf(T element){
        if (element == null){
            return -1;
        }
        else{
            for(int i = size; i >= 0; i--){
                if(a[i] == element){
                    return i;
                }
            }
        }
        return -1;
    }

    /*
     * Replaces the element at index with element and return the
     * element that was previously at index. If index is
     * out-of-bounds or element is null, does nothing with element
     * and return null.
     */
    public T set(int index, T element){
        if(index < 0 || index > size){
            return null;
        }
        else {
            T temp;
            temp = a[index];
            a[index] = element;
            return temp;
        }
    }

    /*
     * Returns the number of elements in the list. For example, if
     * 4 elements added to a list, size will return 4, while the
     * last index in the list will be 3. Updates isSorted.
     */
    public int size(){
        isSorted = false;
        return size;
    }

    /*
     * Sorts the elements of the list. If order is true, sort the
     * list in increasing (alphabeticaly) order. If order is
     * false, sort the list in decreasing (reverse alphabetical)
     * order. Note: only set isSorted to true if sorted in ASCENDING
     * order.
     * If isSorted is true, and the order is true, do NOT resort.
     * Hint: Since T extends Comparable, you will find it useful
     * to use the public int compareTo(T o) method.
     */

    // Uses bubble sort and compareTo to sort alphabetically or reverse alphabetically.
    public void sort(boolean order){
        if (order && !isSorted){
            for(int j = 0; j < size ; j++){
                for(int i = 0; i < size-1; i++ ){
                    int checker = a[i].compareTo(a[i+1]);
                    if(checker > 0){
                        T temp;
                        temp = a[i];
                        a[i] = a[i+1];
                        a[i+1] = temp;
                    }
                }
            }
            isSorted = false;
        }
        else if (order && isSorted){
            isSorted = true;
        }
        else {
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size-1; i++) {
                    int checker = a[i].compareTo(a[i + 1]);
                    if (checker < 0) {
                        T temp;
                        temp = a[i];
                        a[i] = a[i + 1];
                        a[i + 1] = temp;
                    }
                }
            }
        }
    }

    /*
     * Removes the first instance of element from the list. This
     * method should also shifts any subsequent elements to the
     * left (subtracts one from their indices). If successful,
     * return true. If element is not found in the list, return
     * false.
     */
    public boolean remove(T element){
        for(int i = 0; i < size; i++){
            if(a[i] == element){
                for(int j = i; j < size; j++){
                    a[j] = a[j+1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    /*
     * Remove whatever is at index index in the list and return
     * it. If index is out-of-bounds, return null. Shift the
     * indices as in the other remove.
     */
    public T remove(int index){
        if (index < 0 || index >= size){
            return null;
        }
        else{
            T temp;
            temp = a[index];
            a[index] = null;
            for(int i = index; i < size; i++){
                a[i] = a[i+1];
            }
            size--;
            return temp;
        }
    }

    // This prints the elements of a[] in a vertical column.
    public String toString(){
        String verticalList = "";
        for(int i = 0; i < size; i++){
            verticalList = verticalList + a[i] + "\n";
        }
        return verticalList;
    }
}
