import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	Node first, last;
	private int size;
	private class Node{						// inner class for linked list(much easier than using an array)
		Item item;
		Node previous, next;
	}
	public Deque(){							 // construct an empty deque
	}
    public boolean isEmpty(){				  // is the deque empty?
		return false;
    }
    public int size(){                        // return the number of items on the deque
		return 0;
	}
   	public void addFirst(Item item){         // add the item to the front
		if(item==null)
			throw new java.util.NoSuchElementException();








   	}
   	public void addLast(Item item){           // add the item to the end
		if(item==null)
			throw new java.util.NoSuchElementException();









   	}
    public Item removeFirst(){                // remove and return the item from the front
		if(size==0)
            throw new java.util.NoSuchElementException();











		return first.item;	// just here to compile - you'll need to modify
    }
    public Item removeLast(){                // remove and return the item from the end
		if(size==0) {
            throw new java.util.NoSuchElementException();
        }









		return first.item;	// just here to compile - you'll need to modify
    }
    public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
		return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
    	private Node current = first;
    	public boolean hasNext(){ return current!=null; }
		public void remove(){ throw new java.lang.UnsupportedOperationException(); }
		public Item next(){
			if( current==null) {throw new java.util.NoSuchElementException()};
			Item item = current.item;
			current = current.next;
			return item;
		}
    }
    public static void main(String[] args){   // unit testing
		Deque<String> test = new Deque<String>();
		test.addFirst("Love");
		test.addFirst("I");
		test.addLast("Computer");
		test.addLast("Science");
		System.out.println(test.size());
		for(String item: test)
			System.out.println(item);
		test.removeFirst();
		test.removeLast();
		test.removeFirst();

		System.out.println("\n");
		for(String item: test)
			System.out.println(item);
		System.out.println("\n");
		test.removeLast();
		test.addLast("Iterators");
		test.addLast("Rock");
		for(String item: test)
			System.out.println(item);
    }
}
/*
4
I
Love
Computer
Science


Computer


Iterators
Rock
*/