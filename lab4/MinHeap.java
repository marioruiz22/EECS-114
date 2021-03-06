/**
 * Course: EECS 114 Fall 2015
 *
 * First Name: Mario
 * Last Name:  Ruiz
 * Lab Section: 1A
 * email address: mdruiz@uci.edu
 *
 *
 * Assignment: lab4
 * Filename : MinHeap.java
 *
 * I hereby certify that the contents of this file represent
 * my own original individual work. Nowhere herein is there 
 * code from any outside resources such as another individual,
 * a website, or publishings unless specifically designated as
 * permissible by the instructor or TA.
 */

public class MinHeap {
	
	private int h[];
	private int heapSize; // the number of items stored in h
	public static final int CAPACITY = 100;
	
	public MinHeap(){//� Default constructor - Constructs an empty binary min heap.
		this.h = new int[CAPACITY];
		this.heapSize = 0;
	}
	
	public MinHeap(int[] A){// � Constructor - Constructs a binary min heap, via buildMinHeap().
		//You can use the built-in length property to determine the size of the array.
		this.h = new int[CAPACITY]; //Initialize h of size CAPACITY
		//Copy A into h
		for (int i=0; i < A.length; i++){
			this.h[i]= A[i];
		}
		this.heapSize = A.length;
		buildMinHeap();
	}
	
	public void buildMinHeap(){
		for (int i = (heapSize/2)-1; i >= 0; i--){
			trickleDown(i);
		}
	}
	
	public int heapMin(){//� Returns the minimum key in the heap. If empty, throw exception.
		if (this.heapSize == 0){
			throw new IndexOutOfBoundsException("heap is empty!");
		}
		return this.h[0];
	}
	
	public void heapExtractMin(){//� Removes minimum key from heap. If empty, throw exception.
		if(this.heapSize == 0){
			throw new IndexOutOfBoundsException("heap is empty!");
		}
		this.h[0] = this.h[this.heapSize-1];
		this.heapSize--;
		trickleDown(0);
	}
	
	public void minHeapInsert(int key){//� Insert key into heap. If full, throw exception.
		if(this.heapSize == this.h.length){
			throw new IndexOutOfBoundsException("heap is full!");
		}
		this.h[this.heapSize]=key;
		this.heapSize++;
		trickleUp(this.heapSize - 1);
	}
	
	private void trickleDown(int index){
		int current = index;
		int leftChild = 2*index + 1;
		int rightChild = 2*index + 2;
		int temp;
		// while the parent violates the Min Heap properties, trickle down
		while( this.h[current] > this.h[leftChild] || (rightChild <= this.heapSize && this.h[current] > this.h[rightChild])){
			leftChild = 2*current + 1;
			rightChild = 2*current + 2;
			// if parent has two children
			if ((rightChild) <= this.heapSize){
				if( this.h[current] > this.h[leftChild] || this.h[current] > this.h[rightChild]){
					// if left child is smaller
					if(this.h[leftChild] < this.h[rightChild]){
						temp = this.h[leftChild];
						this.h[leftChild] = this.h[current];
						this.h[current] = temp;
						current = leftChild;
					}
					//if right child is smaller
					else{
						temp = this.h[rightChild];
						this.h[rightChild] = this.h[current];
						this.h[current] = temp;
						current = rightChild;
					}
				}
			}
			//if parent only has one child
			else if(leftChild <= this.heapSize){
				if( this.h[current] > this.h[leftChild]){
					temp = this.h[leftChild];
					this.h[leftChild] = this.h[current];
					this.h[current] = temp;		
					current = leftChild;
				}
			}
			else{
				break;
			}
		}
	}
	
	private void trickleUp(int index){
		int current = index;
		int parent = (index - 1)/2;
		int temp;
		while (this.h[parent] > this.h[current] ){
			temp = this.h[parent];
			this.h[parent] = this.h[current];
			this.h[current] = temp;
			current = parent;
			parent = (current - 1)/2;
		}
	}
	
	public void print(){
		System.out.println("Printing Min Heap:");
		int current=0;
		int total = 1;
		for(int i=0; i < this.heapSize;i++){
			if(i == ((current*2)+total)){
				System.out.println();
				if (current == 0){
					current++;
				}
				else{
					current = current *2;
				}
				total = i;
			}
			
			System.out.print(this.h[i]+ " ");
			
		}
		System.out.println(" ");
	}
	
	
}
