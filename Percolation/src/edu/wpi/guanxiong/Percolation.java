package edu.wpi.guanxiong;

import edu.princeton.cs.algs4.UF;

public class Percolation 
{
	private UF uf_inst;
	private boolean [] grid;
	private int N;
	
	public Percolation(int N)				// create N-by-N grid, with all sites blocked
	{
		this.N = N;
		uf_inst = new UF (N*N+2);
		grid = new boolean [N*N];
		
		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
				grid[N*i+j] = false;
			}
			uf_inst.union(i, N*N);
			uf_inst.union(N*(N-1)+i, N*N+1);
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

			if (this.isOpen(i + 1, j))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * i + j - 1);
			}
			if (this.isOpen(i - 1, j))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * (i - 2) + j - 1);
			}
			if (this.isOpen(i, j + 1))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * (i - 1) + j);
			}
			if (this.isOpen(i, j - 1))
			{
				uf_inst.union(N * (i - 1) + j - 1, N * (i - 1) + j - 2);
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

		return uf_inst.connected(N * (i - 1) + j - 1, N * N);

	}    
	public boolean percolates()				// does the system percolate?
	{
		return uf_inst.connected(N*N+1, N*N);
	}            
}
