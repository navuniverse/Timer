/**
 * 
 */
package com.rockingengineering.timer;

import java.util.Stack;

import org.apache.log4j.Logger;

/**
 * A thread safe, multi-level time measuring utility class.
 * 
 * To call it say: <code>Timer.timeIt("Time taken in my suspected code area");</code>
 * 
 * Then some where down the code say: <code>Timer.timeUp();</code>
 * 
 * It will print time taken from last start point along with the message.
 * 
 * You can call Timer.timeIt() many times inside another child functions etc.
 * Only corresponding time durations will get printed
 * 
 * Many threads can call these functions, the timing calculations do not clash with each other.
 * 
 * There are more elegant/short cut ways of achieving this for example writing
 * an aspect with around advice to all methods. But this method give quick and fine grained
 * time measurement.
 * 
 * This class itself is taking around 2 milliseconds delay for its internal processing.
 * This is due to storing/retrieving timings in a stack.
 * 
 * @author naveen
 *
 * @date 28-Jun-2017
 */
public class Timer {

	private static final Logger logger = Logger.getLogger(Timer.class);
	private static ThreadLocal<Stack<StackEntry>> threadLocal = new ThreadLocal<Stack<StackEntry>>();

	private static Stack<StackEntry> getStack() {
		if (threadLocal.get() == null) {
			threadLocal.set(new Stack<StackEntry>());
		}

		return threadLocal.get();
	}

	/**
	 * Method to start measuring time
	 */
	public static void timeIt() {
		timeIt("");
	}

	/**
	 * Method to start measuring time along with measuring message that you wish to be logged when measuring is finished
	 * 
	 * @param message
	 *            - message to logged when measuring is finished
	 */
	public static void timeIt(String message) {
		StackEntry entry = new StackEntry(System.currentTimeMillis(), message);
		getStack().push(entry);
	}

	/**
	 * Method to end the time measuring and log the message passed at the time of measuring start
	 */
	public static void timeUp() {
		if (!getStack().isEmpty()) {
			long currentMillis = System.currentTimeMillis();
			StackEntry stackEntry = getStack().pop();

			logger.debug("TIME: " + stackEntry.getMessage() + ": " + (currentMillis - stackEntry.getTime()) + " milliseconds");
		}
	}

	/**
	 * Method to return the time taken from starting measuring and now in milliseconds
	 * 
	 * @return time - time elapsed between starting the measuring and now in milliseconds
	 */
	public static long timeTaken() {
		long timeTaken = 0;

		if (!getStack().isEmpty()) {
			long currentMillis = System.currentTimeMillis();
			StackEntry stackEntry = getStack().pop();
			timeTaken = currentMillis - stackEntry.getTime();
		}

		return timeTaken;
	}

}