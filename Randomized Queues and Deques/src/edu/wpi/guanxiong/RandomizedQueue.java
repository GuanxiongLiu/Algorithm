package edu.wpi.guanxiong;

import java.util.Iterator;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[] data;
	private int capacity;
	private int size;
	private int end;

	private void AddCapacity()
	{
		Item[] NewData = (Item[]) new Object[this.capacity*2];
		for (int i=0; i<this.capacity; i++)
		{
			NewData[i] = this.data[i];
		}
		this.data = NewData;
		this.capacity = this.capacity*2;
	}

	private void ReduceCapacity()
	{
		Item[] NewData = (Item[]) new Object[this.capacity/2];
		int pos = -1;
		for (int i=0; i<=this.end; i++)
		{
			if (this.data[i] != null)
			{
				pos++;
				NewData[pos] = this.data[i];
			}
		}
		this.data = NewData;
		this.capacity = this.capacity/2;
		this.end = pos;
	}

	private void ReduceBlank()
	{
		Item[] NewData = (Item[]) new Object[this.capacity];
		int pos = -1;
		for (int i=0; i<=this.end; i++)
		{
			if (this.data[i] != null)
			{
				pos++;
				NewData[pos] = this.data[i];
			}
		}
		this.data = NewData;
		this.end = pos;
	}
	
	public RandomizedQueue() // construct an empty randomized queue
	{
		this.capacity = 1;
		this.data = (Item[]) new Object[this.capacity];
		this.size = 0;
		this.end = -1;
	}

	public boolean isEmpty() // is the queue empty?
	{
		return this.size == 0;
	}

	public int size() // return the number of items on the queue
	{
		return this.size;
	}

	public void enqueue(Item item) // add the item
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		
		if (this.size == this.capacity)
		{
			this.AddCapacity();
		}
		else if (this.end == this.capacity-1)
		{
			this.ReduceBlank();
		}
		
		this.end++;
		this.data [this.end] = item;
		this.size++;
	}

	public Item dequeue() // delete and return a random item
	{
		if (this.isEmpty())
		{
			throw new java.util.NoSuchElementException();
		}
		
		if (this.size <= this.capacity/4)
		{
			this.ReduceCapacity();
		}
		
		int OutPos = StdRandom.uniform(this.end+1);
		while (this.data[OutPos] == null)
		{
			OutPos = StdRandom.uniform(this.end+1);
		}
		Item OutData = this.data[OutPos];
		this.data[OutPos] = null;
		this.size--;
		if (OutPos == this.end)
		{
			this.end--;
		}
		return OutData;
	}

	public Item sample() // return (but do not delete) a random item
	{
		if (this.isEmpty())
		{
			throw new java.util.NoSuchElementException();
		}
		
		int OutPos = StdRandom.uniform(this.end+1);
		while (this.data[OutPos] == null)
		{
			OutPos = StdRandom.uniform(this.end+1);
		}
		return this.data[OutPos];
	}

	public Iterator<Item> iterator() // return an independent iterator over items in random order
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		private int current;
		private int SendOut = 0;

		public ListIterator()
		{
			if (isEmpty())
			{
				this.current = 0;
			}
			else
			{
				this.current = StdRandom.uniform(end+1);
			}
			
		}

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return (size>0 && SendOut<size);
		}

		@Override
		public Item next()
		{
			if (!this.hasNext())
			{
				throw new java.util.NoSuchElementException();
			}
			
			if (current >= capacity)
			{
				current = current - capacity;
			}
			while (data[current] == null)
			{
				current++;
				if (current >= capacity)
				{
					current = current - capacity;
				}
			}
			SendOut++;
			return data[current++];
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
			// TODO Auto-generated method stub

		}
	}

	public static void main(String[] args) // unit testing
	{
	}
}