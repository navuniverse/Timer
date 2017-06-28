/**
 * 
 */
package com.rockingengineering.timer;

/**
 * @author naveen
 *
 * @date 28-Jun-2017
 */
public class StackEntry {

	private long time;
	private String message;

	public StackEntry() {
		super();
	}

	public StackEntry(long time, String message) {
		super();
		this.time = time;
		this.message = message;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StackEntry [time=").append(time).append(", message=").append(message).append("]");
		return builder.toString();
	}

}