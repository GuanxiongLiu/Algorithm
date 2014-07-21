package edu.wpi.guanxiong;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Brute
{
	public static void main(String[] args)
	{
		String FileName = args[0];

		Scanner scan = null;
		try
		{
			scan = new Scanner(new File(FileName));
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = 0;
		if (scan.hasNext())
		{
			size = Integer.parseInt(scan.nextLine());
			Point Points[] = new Point[size];
			
			for (int i=0; i<size; i++)
			{
				Points[i] = new Point(scan.nextInt(), scan.nextInt());
			}
			
			// read in the input file
			
			
			for (int i=0; i<size-3; i++)
			{
				for (int j=i+1; j<size-2; j++)
				{
					for (int m=j+1; m<size-1; m++)
					{
						for (int n=m+1; n<size; n++)
						{
							if (Points[i].slopeTo(Points[j]) == Points[j].slopeTo(Points[m]) && Points[j].slopeTo(Points[m]) == Points[m].slopeTo(Points[n]))
							{
								Point Result[] = {Points[i], Points[j], Points[m], Points[n]};
								Arrays.sort(Result);
								System.out.println(Result[0] + "->" + Result[1] + "->" + Result[2] + "->" + Result[3]);
							}
						}
					}
				}
			}
			// find the points in line
		}
		
		
		
		
	}
}