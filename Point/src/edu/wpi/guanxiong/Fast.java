package edu.wpi.guanxiong;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Fast
{
	public static void main(String[] args)
	{
		String FileName = args[0];

		Scanner scan = null;
		try
		{
			scan = new Scanner(new File(FileName));
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = 0;
		if (scan.hasNext())
		{
			size = Integer.parseInt(scan.nextLine());
			Point Points[] = new Point[size];

			for (int i = 0; i < size; i++)
			{
				Points[i] = new Point(scan.nextInt(), scan.nextInt());
			}

			// read in the input file

			for (int i=0; i<size; i++)
			{
				Point temp = Points[i];
				Points[i] = Points[0];
				Points[0] = temp;
				new Fast().FastSort(Points, Points[0], 1, size-1);
			}
			// find the points in line
		}
	}

	private void FastSort(Point[] in, Point origin, int low, int high)
	{
		int mid = low;
		int head = low;
		int tail = high;

		while (head <= tail)
		{
			if (origin.SLOPE_ORDER.compare(in[head], in[mid]) == 0)
			{
				head++;
			}
			else if (origin.SLOPE_ORDER.compare(in[head], in[mid]) < 0)
			{
				Point temp = in[head];
				in[head] = in[mid];
				in[mid] = temp;
				
				head++;
				mid++;
			}
			else if (origin.SLOPE_ORDER.compare(in[head], in[mid]) > 0)
			{
				Point temp = in[head];
				in[head] = in[tail];
				in[tail] = temp;
				
				tail--;
			}
		}
		
		if (head - mid > 3)
		{
			Point[] Result = new Point[head-mid];
			for (int i=mid; i<head; i++)
			{
				Result[i-mid] = in[i];
			}
			Arrays.sort(Result);
			for (int i=0; i<head-mid-1; i++)
			{
				System.out.print(Result[i] + "->");
			}
			System.out.println(Result[head-mid-1]);
		}
		
		if (mid == low && tail == high)
		{
			return;
		}
		else
		{
			Fast.this.FastSort(in, origin, low, mid);
			//left part of unsorted
			Fast.this.FastSort(in, origin, head, high);
			//right part of unsorted
		}
		
	}
}
