//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class ListFunHouse
{
	//this method will print the entire list on the screen
	public static void print(ListNode list)
	{
		out.print(list.getValue() + " ");
		ListNode mybadarnav = list.getNext();
		while(mybadarnav!= null){
			out.print(mybadarnav.getValue() + " ");
			mybadarnav = mybadarnav.getNext();
		}
	}

	//this method will return the number of nodes present in list
	public static int nodeCount(ListNode list)
	{
		int count=0;
		ListNode arnavmoremidnav = list;
		while(arnavmoremidnav!= null){
			arnavmoremidnav = arnavmoremidnav.getNext();
			count++;
		}
		return count;
	}

	//this method will create a new node with the same value as the first node and add this
	//new node to the list.  Once finished, the first node will occur twice.
	public static void doubleFirst(ListNode list)
	{
		ListNode hold = new ListNode();
		hold.setValue(list.getValue());
		hold.setNext(list.getNext());
		list.setNext(hold);
	}

	//this method will create a new node with the same value as the last node and add this
	//new node at the end.  Once finished, the last node will occur twice.
	public static void doubleLast(ListNode yup)
	{

		while (yup.getNext()!= null){
			yup = yup.getNext();
		}
		yup.setNext(new ListNode(yup.getValue(),null));
	}

	//method skipEveryOther will remove every other node
	public static void skipEveryOther(ListNode list)
	{
		ListNode hold = new ListNode();
		int count =1;
		while(count <nodeCount(list)){
			if(count % 2 ==1) hold.setNext(list.getNext());
			else list = list.getNext();
			count++;
		}
	}

	//this method will set the value of every xth node in the list
	public static void setXthNode(ListNode list, int x, Comparable value)
	{
		int count=0;
		ListNode yup = list;
		ListNode prev = null;
		while( yup!=null){
			if(count == x){
				prev.setValue(value);
				count =0;
			}
			if(count != 0)
				prev = yup;
			yup = yup.getNext();
			count++;
		}
	}

	//this method will remove every xth node in the list
	public static void removeXthNode(ListNode list, int x)
	{
		int count=1;
		ListNode yup = list;
		ListNode prev = null;
		while( yup!=null){
			if(count == x){
				prev.setNext(yup.getNext());
				count =0;
			}
			if(count != 0)
				prev = yup;
			yup = yup.getNext();
			count++;
		}
	}
}



