package edu.wpi.guanxiong;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
	private class LinkNode
	{
		private Item data;
		private LinkNode next;
		private LinkNode previous;
	}
	
	private LinkNode first = new LinkNode();
	private LinkNode end   = new LinkNode();
	private int size;
	
	
	
	public Deque()// construct an empty deque
	{
		this.first.next 	= this.end;
		this.end.previous   = this.first;
		this.size  			= 0;
	} 

	public boolean isEmpty()// is the deque empty?
	{
		return this.size == 0;
	} 

	public int size()// return the number of items on the deque
	{
		return this.size;
	} 

	public void addFirst(Item item)// insert the item at the front
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		
		LinkNode inNode = new LinkNode();
		inNode.data = item;
		
		inNode.next = this.first.next;
		inNode.previous = this.first;
		
		this.first.next.previous = inNode;
		this.first.next = inNode;
		
		this.size++;
	} 

	public void addLast(Item item)// insert the item at the end
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		
		LinkNode inNode = new LinkNode();
		inNode.data = item;
		
		inNode.next = this.end;
		inNode.previous = this.end.previous;
		
		this.end.previous.next = inNode;
		this.end.previous = inNode;
		
		this.size++;
	} 

	public Item removeFirst()// delete and return the item at the front
	{
		if (this.isEmpty())
		{
			throw new java.util.NoSuchElementException();
		}
		
		LinkNode outNode = new LinkNode();
		outNode = this.first.next;
		
		this.first.next = outNode.next;
		outNode.next.previous = this.first;
		
		outNode.previous = null;
		outNode.next = null;
		
		this.size--;
		return outNode.data;
	} 

	public Item removeLast()// delete and return the item at the end
	{
		if (this.isEmpty())
		{
			throw new java.util.NoSuchElementException();
		}
		
		LinkNode outNode = new LinkNode();
		outNode = this.end.previous;
		
		outNode.previous.next = this.end;
		this.end.previous = outNode.previous;
		
		outNode.previous = null;
		outNode.next = null;
		
		this.size--;
		return outNode.data;
	} 

	public Iterator<Item> iterator()// return an iterator over items in order from front to end
	{
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>
	{
		private LinkNode current = first.next;

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return current != end;
		}

		@Override
		public Item next()
		{
			// TODO Auto-generated method stub
			if (!this.hasNext())
			{
				throw new java.util.NoSuchElementException();
			}
			
			Item outData = this.current.data;
			this.current = this.current.next;
			return outData;
		}

		@Override
		public void remove()
		{
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}

	public static void main(String[] args)// unit testing
	{
	}
}
