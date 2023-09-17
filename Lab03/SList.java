package Lab03;
/**
 * The {@code SList} class represents a singly linked list that can hold
 * elements of a generic type {@code T}. It provides methods for adding elements
 * to the beginning and end of the list, adding elements at a specified index,
 * removing elements from the beginning and end of the list, removing elements
 * at a specified index, checking if the list is empty, obtaining the size of
 * the list, and searching for elements within the list.
 *
 * @param <T> The type of elements stored in the singly linked list.
 */
public class SList<T> {
	/**
	 * The number of elements currently stored in the singly linked list.
	 */
	int size;
	/**
	 * The reference to the first node in the singly linked list.
	 */
	Node<T> first;
	/**
	 * The reference to the last node in the singly linked list.
	 */
	Node<T> last;

	/**
	 * Constructs an empty singly linked list with size 0.
	 */
	SList() {
		size = 0;
		first = null;
		last = null;
	}

	/**
	 * Adds a new element to the beginning of the singly linked list.
	 *
	 * @param element The element to be added to the list.
	 */
	void addFirst(T element) {

		Node<T> newNode = new Node<T>(element);
		newNode.next = first;
		first = newNode;
		size++;

		if (last == null)
			last = first;
	}

	/**
	 * Adds a new element to the end of the singly linked list.
	 *
	 * @param element The element to be added to the list.
	 */
	void addLast(T element) {

		Node<T> newNode = new Node<T>(element);
		last.next = newNode;
		last = newNode;
		size++ ;

		if (last == null){
			last = first;}
	}

	/**
	 * Adds a new element at the specified index in the singly linked list. If the
	 * index is 0, the element is added to the beginning of the list. If the index
	 * is greater than or equal to the size, the element is added to the end of the
	 * list.
	 *
	 * @param index   The index at which to add the element.
	 * @param element The element to be added to the list.
	 */
	void addAtIndex(int index, T element) {
		
		Node<T> temp = first;
		if(index == 0 ){
			addFirst(element);
		}
		else if (index == size ){
			addLast(element);
		}
		else {
		for ( int i = 0 ; i<index-1 ;i++){
			temp = temp.next;
		}
		Node<T> newNode = new Node<T>(element);
		newNode.next = temp.next;
		temp.next = newNode;
		size++ ;
	}
	}

	/**
	 * Removes and returns the first element from the singly linked list.
	 *
	 * @return The removed element, or {@code null} if the list is empty.
	 */
	T removeFirst() {
		if (size == 0)
			return null;
		else {
			Node<T> tmp = first;
			first = first.next;
			size--;
			if (first == null)
				last = null;
			return tmp.element;
		}

	}

	/**
	 * Removes and returns the last element from the singly linked list.
	 *
	 * @return The removed element, or {@code null} if the list is empty.
	 */
	T removeLast() {
		Node<T> temp = first;
		if (size == 0)
			return null;
		else{
		for ( int i = 0 ; i<size -1;i++){
			temp = temp.next;
		}
		
		
		temp.next = null;
		size-- ;
		}
		return null;
	}

	/**
	 * Removes and returns the element at the specified index in the singly linked
	 * list.
	 *
	 * @param index The index of the element to be removed.
	 * @return The removed element, or {@code null} if the index is out of bounds.
	 */
	T removeAtIndex(int index) {
		Node<T> temp = first;
		
		if(index == 0 ){
			removeFirst();
		}
		else if (index == size ){
			removeLast();
		}
		else {
		for ( int i = 0 ; i<index-1 ;i++){
			temp = temp.next;
		}
		Node<T> toRemove = temp.next;
		temp.next = null;
		temp.next = toRemove.next  ;
		
		size-- ;
	}
		return null;
	}

	/**
	 * Searches for the first occurrence of a specified item in the singly linked
	 * list and returns its index.
	 *
	 * @param item The item to search for.
	 * @return The index of the first occurrence of the item, or {@code -1} if not
	 *         found.
	 */
	int search(T item) {
		
		Node<T> temp = first;
		for ( int i = 0 ; i < size-1 ;i++){
			if (temp.element == item){
				return i ;
			}
			else{
				temp = temp.next;
			}
		}
		return -1;
	}

	/**
	 * Checks whether the singly linked list is empty.
	 *
	 * @return {@code true} if the list is empty, {@code false} otherwise.
	 */
	boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	/**
	 * Returns the current size of the singly linked list.
	 *
	 * @return The number of elements in the list.
	 */
	int getSize() {
		return size;
	}

	/**
	 * Prints the elements of the singly linked list horizontally, followed by a
	 * horizontal line separator. This method is primarily used for debugging and
	 * displaying the contents of the list.
	 */
	void printHorizontal() {
		Node<T> walker = first;
		for (int i = 0; i < size; i++) {
			System.out.print(walker.element);
			System.out.print(" ");
			walker = walker.next;
		}
		System.out.println("\n-----");
	}
}