package pandey.ujjwal.javaConcepts.threads;

class Counter {
	private volatile int countSimple = 0;
	private int count = 0;

	public void incrementSimple() {
		countSimple++;
	}

	public synchronized void increment() {
		count++;
	}

	public synchronized void printCount() {
		System.out.println("Final simple count: " + countSimple);
		System.out.println("Final count: " + count);
	}
}

/**
 * Usecases:
 * 
 * Shared Resource Management
 * 
 * Thread-safe Collections
 * 
 * Singleton Pattern
 * 
 * Producer-Consumer Problem
 * 
 * Bank Account Management
 * 
 * Logging
 * 
 * Blocking Queue Implementation
 * 
 * Cannot be fixed with the volatine keyword.
 * 
 * Method witout sync will give the different outputs.
 * 
 * Cons: Time taking - instead of method sync use code sysc
 */
public class Synchronized_KW {
	public static void main(String[] args) {
		Counter counter = new Counter();

		// Creating threads to increment the counter
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 20000; i++) {
				counter.increment();
				counter.incrementSimple();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 20000; i++) {
				counter.increment();
				counter.incrementSimple();
			}
		});

		t1.start();
		t2.start();

		// Wait for both threads to finish
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Print final counts
		counter.printCount();

	}
}