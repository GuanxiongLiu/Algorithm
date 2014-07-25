package edu.wpi.guanxiong;

import java.util.Arrays;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

public class Fast
{
	public static void main(String[] args)
	{
		String FileName = args[0];
		In in = new In(FileName);

		int size = in.readInt();
		Point Points[] = new Point[size];

		for (int i = 0; i < size; i++)
		{
			Points[i] = new Point(in.readInt(), in.readInt());
		}

		// read in the input file

		for (int i = 0; i < size-3; i++)
		{
			new Fast().FastSort(Points, Points[i], i+1, size-1);
		}
		// find the points in line
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
			} else if (origin.SLOPE_ORDER.compare(in[head], in[mid]) < 0)
			{
				Point temp = in[head];
				in[head] = in[mid];
				in[mid] = temp;

				head++;
				mid++;
			} else if (origin.SLOPE_ORDER.compare(in[head], in[mid]) > 0)
			{
				Point temp = in[head];
				in[head] = in[tail];
				in[tail] = temp;

				tail--;
			}
		}

		if (head - mid >= 3)
		{
			Point[] Result = new Point[head - mid+1];
			for (int i = mid; i < head; i++)
			{
				Result[i - mid] = in[i];
			}
			Result[head-mid] = origin;
			Arrays.sort(Result);
			
			// rescale coordinates and turn on animation mode
			StdDraw.setXscale(0, 32768);
			StdDraw.setYscale(0, 32768);
			StdDraw.setPenRadius(0.01); // make the points a bit larger
			Result[0].drawTo(Result[head-mid]);
			
			for (int i = 0; i < head - mid; i++)
			{
				System.out.print(Result[i] + "->");
			}
			System.out.println(Result[head - mid]);
		}

		if (mid == low && tail == high)
		{
			return;
		} else
		{
			Fast.this.FastSort(in, origin, low, mid);
			// left part of unsorted
			Fast.this.FastSort(in, origin, head, high);
			// right part of unsorted
		}

	}
}
