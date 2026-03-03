import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported. 
 * 
 * @author 
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private T[] array;
	private int rear;
	private int modCount;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = initialCapacity;
		modCount = 0;
	}
	
	/** Double the capacity of array */
	private void expandCapacity() {

		// says this is bad
		// T[] newArray = (T[])(new Object[array.length * 2]);
		// for(int i = 0; i < array.length; i++) {
		// 	newArray[i] = array[i];
		// }
		// array = newArray;

		// this is the same but simpler
		array = Arrays.copyOf(array, array.length*2);
	}

	@Override
	public void addToFront(T element) {
		// TODO 
		if (rear == array.length) {
			expandCapacity();
		}
		for (int i = rear; i > 0; i--) {
			array[i] = array[i-1];
		}
		array[0] = element;
		rear++;
		modCount++;

		// the reason to not just use add(0, element) is because
		// it just gives more practice
		// and because arrrays are great with indices,
		// but ALL other collections are
		// extremely slow comparatively
	}

	@Override
	public void addToRear(T element) {
	
		if (rear == array.length) {
			expandCapacity();
		}
		add(element);
	}

	@Override
	public void add(T element) {
		// TODO 
		if (rear == array.length) {
			expandCapacity();
		}
		array[rear] = element;
		rear++;
		modCount++;
	}

	@Override
	public void addAfter(T element, T target) {
		// TODO 
		if (rear == array.length) {
			expandCapacity();
		}
		for (int i = 0; i < rear; i++) {
			if (array[i].equals(target)) {
				add(i+1, element);
				return;
			}
		}

	}

	@Override
	public void add(int index, T element) {
		// test if index is valid, throw exception if not
		if (index < 0 || index > rear) {
			throw new IndexOutOfBoundsException();
		}

		if (rear == array.length) {
			expandCapacity();
		}
		for (int i = rear; i > index; i--) {
			array[i] = array[i-1];
		}
		array[index] = element;
		rear++;
		modCount++;
		
	}

	@Override
	public T removeFirst() {
		// TODO 
		return null;
	}

	@Override
	public T removeLast() {
		// TODO 
		return null;
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);

		// NOT_FOUND is equal to -1, which indexOf() returns if the element is not found
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[index];
		
		// already given in file, remove(index) works and reduces code-duplication
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		
		return retVal;
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= rear) {
			throw new IndexOutOfBoundsException();
		}

		// had to store the old value to be returned, is object type T
		T oldVal = array[index];

		for(int i = index; i < rear-1; i++) {
			array[i] = array[i+1];
		}
		array[rear - 1] = null;
		rear--;
		modCount++;

		return oldVal;
	}

	@Override
	public void set(int index, T element) {
		// TODO 
		
	}

	@Override
	public T get(int index) {
		// TODO 
		return null;
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		
		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}
		
		return index;
	}

	@Override
	public T first() {
		// TODO 
		return array[0];
	}

	@Override
	public T last() {
		// TODO 
		return array[rear - 1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != NOT_FOUND);
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return rear == 0;
	}

	@Override
	public int size() {
		// TODO 
		return rear;
	}

	@Override
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		
		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			// TODO 
			return false;
		}

		@Override
		public T next() {
			// TODO 
			return null;
		}
		
		@Override
		public void remove() {
			// TODO
			
		}
	}
}
