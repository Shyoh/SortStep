/**
 *  Implements five of five sorts:
 *    1. Bubble sort
 *    2. Selection sort
 *    3. Insertion sort
 *    4. Merge sort (recursive)
 *    5. Quicksort - not implemented
 *
 *   A count is maintained of the number of steps taken by each
 *   sorting algorithm to serve as a method of comparing the order
 *   of each algorithm.
 *
 * @author     G. Peck
 * @created    July 18, 2002
 */
public class Sorts
{
  private long steps;

  /**
   *  Sorts class constructor.
   */
  Sorts()
  {
    steps = 0;
  }

  /**
   *  Sets the stepCount attribute of the Sorts object
   *
   * @param  stepCount  The new stepCount value
   */
  public void setStepCount(int stepCount)
  {
    steps = stepCount;
  }

  /**
   *  Gets the stepCount attribute of the Sorts object
   *
   * @return    The stepCount value
   */
  public long getStepCount()
  {
    return steps;
  }

  /**
   *  Interchanges two elements in an integer array
   *
   * @param  list  reference to an array of integers
   * @param  a     index of integer to be swapped
   * @param  b     index of integer to be swapped
   */
  public void swap(int[] list, int a, int b)
  {
    int c = list[a];
    list[a] = list[a = b];
    list[b] = c;
    steps += 4;// includes count for method call
  }

  /**
   *  Sorts an array in ascending order using a bubble sort algorithm
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void bubbleSort(int[] list)
  {
    steps++;                         // initialize outer=0
    for (int outer = 0; outer < list.length - 1; outer++)
    {
      steps += 3;        // outer loop boundary, outer++, initialize inner=1
      for (int inner = 0; inner < list.length - outer - 1; inner++)
      {
        steps += 3;                  // boundary, inner++, if statement
        if (list[inner] > list[inner + 1])
        {
          swap(list, inner, inner + 1);
        }
      }
    }
  }

  /**
   *  Sorts an array in ascending order using a selection sort
   *  algorithm
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void selectionSort(int[] list)
  {
    int min, temp;
  steps++;
  for (int outer = 0; outer < list.length - 1; outer++)
  {
    steps += 4;
    min = outer;
    for (int inner = outer + 1; inner < list.length; inner++)
    {
      steps += 3;
      if (list[inner] < list[min])
      {
        steps ++;
        min = inner;  // a new smallest item is found
      }
    }
    //swap list[outer] & list[min]
    temp = list[outer];
    list[outer] = list[min];
    list[min] = temp;
    steps += 3;
  }

  }

  /**
   *  Sorts an array in ascending order using an insertion sort
   *  algorithm
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void insertionSort(int[] list)
  {
    steps++;
      for (int outer = 1; outer < list.length; outer++)
  {
    steps += 5;
    int position = outer;
    int key = list[position];

    // Shift larger values to the right
    while (position > 0 && list[position - 1] > key)
    {
      list[position] = list[position - 1];
      position--;
      steps += 2;
    }
    list[position] = key;
    steps++;
  }
  }

  /**
   *  Takes in entire array, but will merge the following sections
   *  together: Left sublist from a[first]..a[mid], right sublist from
   *  a[mid+1]..a[last]. Precondition: each sublist is already in
   *  ascending order
   *
   * @param  a      array to be merged
   * @param  first  start of left sublist
   * @param  mid    end of left sublist
   * @param  last   end of right sublist
   */
  private void merge(int[] a, int first, int mid, int last)
  {
    int aPtr = first;
    int bPtr = mid + 1;
    int cPtr = first;
    int total = last - first + 1;
    int loop;
    boolean doneA = false;
    boolean doneB = false;
    int[] c = new int[a.length];

    steps += 7;                      // 6 initializations above, loop=1
    for (loop = 1; loop <= total; loop++)
    {
      steps += 3;                    // loop boundary, loop++, if
      if (doneA)
      {
        steps += 2;                  // assignment, bPtr++
        c[cPtr] = a[bPtr];
        bPtr++;
      }
      else
          if (doneB)
      {
        steps += 3;                  // if, assignment, aPtr++
        c[cPtr] = a[aPtr];
        aPtr++;
      }
      else if (a[aPtr] < a[bPtr])
      {
        // ok to compare, valid data in each sublist
        steps += 3;                  // if, assignment, aPtr++
        c[cPtr] = a[aPtr];
        aPtr++;
      }
      else
      {
        steps += 2;                  // assignment, bPtr++
        c[cPtr] = a[bPtr];
        bPtr++;
      }
      steps += 3;                    // cPtr++, 2 if's
      cPtr++;
      if (aPtr > mid)
      {
        doneA = true;
      }
      if (bPtr > last)
      {
        doneB = true;
      }
    }
                                     // for loop
    steps++;                         // initialize loop=first
    for (loop = first; loop <= last; loop++)
    {
      a[loop] = c[loop];
      steps += 3;                    // loop boundary, loop++, assignment
    }
  }

  /**
   *  Implements a recursive mergesort
   *
   * @param  a      array containing sublists to be sorted
   * @param  first  start index of range to be sorted
   * @param  last   end index of range to be sorted
   */
  public void mergeSort(int[] a, int first, int last)
 {
   int mid;
   int temp;
   steps += 3;
   if (first == last){   ;     }
   else {
     steps ++;
       if (first + 1 == last)
       {       steps++;
       if (a[first] > a[last])
       {
         swap(a, first, last);
       }
        }
        else
        {steps ++; // The function calls also require steps, but we won't count them.
             mid = (first + last) / 2;
       mergeSort(a, first, mid);
       mergeSort(a, mid + 1, last);
       merge(a, first, mid, last);
        }
    }
 }

  /**
   *  Implements a recursive quicksort algorithm
   *
   * @param  list   referenc to an array of integers to be sorted
   * @param  first  starting index of sort range
   * @param  last   ending index of sort range
   */
  public void quickSort (int[] list, int first, int last)
{
    int g = first, h = last;
    int midIndex, dividingValue;
  steps +=4;
    midIndex = (first + last) / 2;
  steps++;
    dividingValue = list[midIndex];
  steps++;
    do
    {
    steps +=2;
        while (list[g] < dividingValue) {g++;}
        while (list[h] > dividingValue) {h--;}
    steps++;
        if (g <= h)
        {
      steps += 5;
            int temp = list[g];
list[g] = list[h];
list[h] = temp;
            g++;
            h--;
        }
    }
    while (g < h);
  steps += 2;
    if (h > first) quickSort (list, first, h);
    if (g < last) quickSort (list, g, last);


  }
}
