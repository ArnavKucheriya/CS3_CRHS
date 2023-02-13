
import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//implements a double linked list

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
	}

	/**
	 * Appends an element to the end of the list
	 *
	 * @param element The element to add
	 */
	public boolean add(E element) {
		if (element == null)
			throw new NullPointerException();
		size++;
		LLNode<E> newNode = new LLNode<E>(element);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		return true;
	}

	/**
	 * Get the element at position index
	 *
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		Iterator<E> iter = listIterator(index);
		return iter.next();
	}


	/**
	 * Add an element to the list at the specified index
	 *
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (element == null)
			throw new NullPointerException();
		ListIterator<E> iter = listIterator(index);
		iter.add(element);
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 *
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 *
	 */
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		ListIterator<E> iter = listIterator(index);
		E element = iter.next();
		iter.remove();
		return element;
	}

	/**
	 * Set an index position in the list to a new element
	 *
	 * @param index   The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		if (element == null)
			throw new NullPointerException();
		ListIterator<E> iter = listIterator(index);
		E oldElement = iter.next();
		iter.set(element);
		return oldElement;
	}

	public boolean has(E item) {
		for (E element : this) {
			if (element.equals(item))
				return true;
		}
		return false;
	}

	public String toString() {
		if (size == 0)
			return "[]";
		String out = "[";
		ListIterator<E> iter = listIterator();
		while (iter.hasNext()) {
			out += iter.next();
			if (iter.hasNext())
				out += ", ";
		}
		return out + "]";
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterable();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterable(index);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new LinkedListIterable();
	}

	private class LinkedListIterable implements ListIterator<E> {
		LLNode<E> current;
		private int index;

		public LinkedListIterable(int index) {
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException();
			current = null;
			this.index = -1;
			while (index-- > 0) {
				next();
			}
		}

		public LinkedListIterable() {
			current = null;
			index = -1;
		}

		public boolean hasNext() {
			return firstTime() || (current != null && current.next != null);
		}

		private boolean firstTime() {
			return index == -1 && head != null;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			else if (firstTime()) {
				if (size == 0)
					throw new NoSuchElementException();
				current = head;
			} else
				current = current.next;
			index++;
			return current.data;
		}

		@Override
		public boolean hasPrevious() {
			return current != null && current.prev != null;
		}

		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			current = current.prev;
			index--;
			return current.data;
		}

		@Override
		public int nextIndex() {
			return index + 1;
		}

		@Override
		public int previousIndex() {
			return firstTime() ? -1 : index - 1;
		}

		@Override
		public void remove() {
			size--;
			//System.out.println("Removing " + current.data);
			if (size == 0)
				current = head = tail = null;
			else if (current.prev == null) {
				current = current.next;
				head = current;
				current.prev = null;
			} else if (current.next == null)  {
				current = current.prev;
				tail = current;
				current.next = null;
				//System.out.println("Setting tail to " + tail.data);
			} else {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				current = current.prev;
			}
		}

		@Override
		public void set(E e) {
			if (firstTime())
				throw new IllegalStateException();
			current.data = e;
		}

		@Override
		public void add(E e) {
			size++;
			if (e == null)
				throw new NullPointerException();
			if (head == null) {
				head = new LLNode<E>(e);
				tail = head;
			} else if (firstTime()) {
				LLNode<E> newNode = new LLNode<E>(e);
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
			} else {
				LLNode<E> newNode = new LLNode<E>(e);
				newNode.next = current.next;
				newNode.prev = current;
				current.next = newNode;
				if (newNode.next != null)
					newNode.next.prev = newNode;
				else
					tail = newNode;
			}
		}
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(LLNode<E> prev, LLNode<E> next, E data) {
		this.prev = prev;
		this.next = next;
		this.data = data;
	}

	public void setData(E e) {
		this.data = e;
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(1, 5);

		for (int i : list) {
			System.out.println(i);
		}
	}
}