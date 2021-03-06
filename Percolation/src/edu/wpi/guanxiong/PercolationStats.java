package edu.wpi.guanxiong;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats
{
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	
	public PercolationStats(int N, int T)					// perform T independent computational experiments on an N-by-N grid
	{
		if (N<=0 || T<=0)
		{
			throw new IllegalArgumentException();
		}
		
		mean = 0;
		stddev = 0;
		confidenceLo = 0;
		confidenceHi = 0;
		
		double [] x = new double [T];
		double counter = 0;
		for (int t=0; t<T; t++)
		{
			Percolation test = new Percolation(N);
			while (!test.percolates())
			{
				int i = StdRandom.uniform(N)+1;
				int j = StdRandom.uniform(N)+1;
				
				if (!test.isOpen(i, j))
				{
					test.open(i, j);
					counter++;
				}
			}
			x[t] = counter/(N*N);
			counter = 0;
		}
		mean = StdStats.mean(x);
		stddev = StdStats.stddev(x);
		confidenceLo = mean - 1.96*stddev/Math.sqrt(T);
		confidenceHi = mean + 1.96*stddev/Math.sqrt(T);
	} 

	public double mean()									// sample mean of percolation threshold
	{
		return mean;
	} 

	public double stddev()									// sample standard deviation of percolation threshold
	{
		return stddev;
	} 

	public double confidenceLo()							// returns lower bound of the 95% confidence interval
	{
		return confidenceLo;
	} 

	public double confidenceHi()							// returns upper bound of the 95% confidence interval
	{
		return confidenceHi;
	} 

	public static void main(String[] args)					// test client, described below
	{
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats test = new PercolationStats(N, T);
		
		System.out.println("mean                    =" + test.mean);
		System.out.println("stddev                  =" + test.stddev);
		System.out.println("95% confidence interval =" + test.confidenceLo + ", " + test.confidenceHi);
	} 
}
