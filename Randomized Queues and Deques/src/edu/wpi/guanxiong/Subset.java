package edu.wpi.guanxiong;

import edu.princeton.cs.introcs.StdIn;

public class Subset
{
	public static void main(String[] args)
	{
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> inData = new RandomizedQueue<>();
		
		while (!StdIn.isEmpty())
		{
			inData.enqueue(StdIn.readString());
		}
		
		if (inData.size() < k)
		{
			throw new IndexOutOfBoundsException();
		}
		
		for (int i=0; i<k; i++)
		{
			System.out.println(inData.dequeue());
		}
	}
}
