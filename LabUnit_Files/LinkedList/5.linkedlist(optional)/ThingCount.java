//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -   
//Lab  -

public class ThingCount implements Comparable
{
	private int count;
	private Object thing;
	
	public ThingCount()
	{
	}
	
	public ThingCount(Object thang, int cnt)
	{
	}
	
	public int getCount()
	{
		return 0;
	}
	
	public void setCount(int cnt)
	{
	}

	public void setThing(Object obj)
	{
	}	
	
	public Object getThing()
	{
		return null;
	}
	
	public boolean equals(Object obj)
	{
		ThingCount other = (ThingCount)obj;
		return false;
	}
	
	public int compareTo(Object obj)
	{
		ThingCount other = (ThingCount)obj;
		return -1;		
	}
	
	public String toString()
	{
		return ""+ getThing() + " - " + getCount();
	}
}