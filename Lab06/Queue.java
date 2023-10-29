package Lab06;

/**
 * A generic queue implementation using a singly linked list.
 *
 * @param <T> the type of elements stored in the queue
 */
public class Queue<T> {
	/** The underlying singly linked list for the queue. */
	SList<T> list = new SList<T>();

	/** Default constructor to initialize an empty queue. */
	Queue() {
	}

	/**
	 * Enqueues an element into the queue.
	 *
	 * @param element the element to enqueue
	 */
	void enqueue(T element) {
		list.addLast(element);
	}

	/**
	 * Dequeues an element from the queue.
	 *
	 * @return the dequeued element
	 */
	T dequeue() {
		return list.removeFirst();
	}

	/**
	 * Gets the element at the front of the queue without removing it.
	 *
	 * @return the element at the front of the queue
	 */
	T queuefront() {
		return list.first.element;
	}

	/**
	 * Gets the element at the rear of the queue without removing it.
	 *
	 * @return the element at the rear of the queue
	 */
	T queuerear() {
		return list.last.element; // Exercise 1d
	}

	/**
	 * Checks if the queue is empty.
	 *
	 * @return true if the queue is empty, false otherwise
	 */
	boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Creates a copy of this queue.
	 *
	 * @return a new queue containing the same elements as this queue
	 */
	Queue<T> copyQueue() { // Exercise 2
		Queue<T> Q2 = new Queue<T>();
		Queue<T> Temp = new Queue<T>();

		int i = 0;
		while (i < list.size) {
			T item = dequeue();
			Temp.enqueue(item);
			Q2.enqueue(item);
		}
		while (!Temp.isEmpty()) {
			enqueue(Temp.dequeue());
		}

		return Q2;
	}

	/**
	 * Checks if this queue is identical to another queue in terms of content and
	 * sequence.
	 *
	 * @param Q2 the queue to compare with
	 * @return true if the queues are identical, false otherwise
	 */
	boolean isIdentical(Queue<T> Q2) {
		int check = 0;
		if ((list.size == Q2.list.size)) {

			Queue<T> temp1 = new Queue<T>();
			Queue<T> temp2 = new Queue<T>();
			while (!isEmpty() && !Q2.isEmpty()) {
				T item1 = dequeue();
				T item2 = Q2.dequeue();

				if (!item1.equals(item2)) {

					check = 1;
				}

				temp1.enqueue(item1);
				temp2.enqueue(item2);
			}

			while (!temp1.isEmpty() && !temp2.isEmpty()) {
				enqueue(temp1.dequeue());
				Q2.enqueue(temp2.dequeue());
			}

		}

		else {
			check = 1;
		}

		if (check == 1) {
			return false;

		} else {
			return true;
		}
	}

	/**
	 * Prints the elements of the queue horizontally. Handles potential
	 * 
	 */
	void printHorizontal() {
		Node<T> walker = list.first;
		while (walker != null) {
			System.out.print(walker.element + " ");
			walker = walker.next;
		}

	}

}