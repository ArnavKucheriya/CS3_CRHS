
import java.util.AbstractList;

// A class that implements a doubly linked list

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method




	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element )
	{
		// TODO: Implement this method





            return true;
	}

	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		// TODO: Implement this method.






		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element )
	{
		// TODO: Implement this method











	}


	/** Return the size of the list */
	public int size()
	{
		// TODO: Implement this method
		return 0;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 *
	 */
	public E remove(int index)
	{
		// TODO: Implement this method









		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)
	{
		// TODO: Implement this method








		return null;
	}
        public boolean has(E item){
            // TODO: Implement this method
            	
            	
            	
            return false;
        }
        public String toString(){
           	// TODO: Implement this method
           	
           	
           	return "";
        }
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
   public LLNode(LLNode<E> prev, LLNode<E>next, E data){
  		this.prev = prev;
      this.next = next;
      this.data = data;
   }
   public void setData(E e){
      this.data = e;
   }
}
