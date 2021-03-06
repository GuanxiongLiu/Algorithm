package edu.wpi.guanxiong;

import java.util.Arrays;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

public class Brute
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

		for (int i = 0; i < size - 3; i++)
		{
			for (int j = i + 1; j < size - 2; j++)
			{
				for (int m = j + 1; m < size - 1; m++)
				{
					for (int n = m + 1; n < size; n++)
					{
						if (Points[i].slopeTo(Points[j]) == Points[j].slopeTo(Points[m])
							&& Points[j].slopeTo(Points[m]) == Points[m].slopeTo(Points[n]))
						{
							Point Result[] = { Points[i], Points[j], Points[m], Points[n] };
							Arrays.sort(Result);
							System.out.println(Result[0] + "->" + Result[1] + "->" + Result[2] + "->" + Result[3]);
							
							// rescale coordinates and turn on animation mode
							StdDraw.setXscale(0, 32768);
							StdDraw.setYscale(0, 32768);
							StdDraw.setPenRadius(0.01); // make the points a bit larger
							Result[0].drawTo(Result[3]);
						}
					}
				}
			}
		}
		// find the points in line
	}

}
