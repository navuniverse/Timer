# Timer
Project timer - Utility class to measure the time of your java code execution in milliseconds. Works well in multi-threaded environment


# Usage Summary
A thread safe, multi-level time measuring utility class. To call it say: 

# Timer.timeIt("Time taken in my code area");
 
Then some where down the code say: 
# Timer.timeUp();
 
It will print time taken from last start point along with the message. You can call Timer.timeIt() many times inside another child functions etc. Only corresponding time durations will get printed
 
Many threads can call these functions, the timing calculations do not clash with each other.
 
There are more elegant/short cut ways of achieving this for example writing an aspect with around advice to all methods. But this method give quick and fine grained time measurement.
