package edu.wpi.guanxiong;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
	private WeightedQuickUnionUF uf_inst;
	private WeightedQuickUnionUF uf_real;
	private boolean [] grid;
	private int N;
	
	public Percolation(int N)				// create N-by-N grid, with all sites blocked
	{
		if (N<=0) 
		{
			throw new IllegalArgumentException();
		}

		this.N = N;
		uf_inst = new WeightedQuickUnionUF (N*N+2);
		uf_real = new WeightedQuickUnionUF (N*N+1);
		grid = new boolean [N*N];
		
		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
				grid[N*i+j] = false;
			}
			uf_inst.union(i, N*N);
			uf_inst.union(N*(N-1)+i, N*N+1);
			uf_real.union(i, N*N);
		}
	}              
	public void open(int i, int j)			// open site (row i, column j) if it is not already
	{
		if (i<1 || i>N || j<1 || j>N)
		{
			throw new java.lang.IndexOutOfBoundsException();
		}

		if (!this.isOpen(i, j))
		{
			grid[N * (i - 1) + j - 1] = true;

			if (i<N && this.isOpen(i + 1, j))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * i + j - 1);
				uf_real.union(N * (i - 1) + j - 1, N * i + j - 1);
			}
			if (i>1 && this.isOpen(i - 1, j))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * (i - 2) + j - 1);
				uf_real.union(N * (i - 1) + j - 1, N * (i - 2) + j - 1);
			}
			if (j<N && this.isOpen(i, j + 1))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * (i - 1) + j);
				uf_real.union(N * (i - 1) + j - 1, N * (i - 1) + j);
			}
			if (j>1 && this.isOpen(i, j - 1))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * (i - 1) + j - 2);
				uf_real.union(N * (i - 1) + j - 1, N * (i - 1) + j - 2);
			}
		}

	}        
	public boolean isOpen(int i, int j)		// is site (row i, column j) open?
	{
		if (i<1 || i>N || j<1 || j>N)
		{
			throw new java.lang.IndexOutOfBoundsException();
		}

		return grid[N * (i - 1) + j - 1] == true;

	}    
	public boolean isFull(int i, int j)		// is site (row i, column j) full?
	{
		if (i<1 || i>N || j<1 || j>N)
		{
			throw new java.lang.IndexOutOfBoundsException();
		}

		return isOpen(i, j) && uf_real.connected(N * (i - 1) + j - 1, N * N);

	}    
	public boolean percolates()				// does the system percolate?
	{
		if (N == 1)
		{
			return isOpen(1, 1);
		}
		else
		{
			return uf_inst.connected(N*N+1, N*N);
		}
	}            
}
