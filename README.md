# README
___
## Introduction
Provides a Threadsafe implementation of `SnapshotList` interface  that offers the possibility to create snapshots of the 
elements currently stored in it. 
SnapshotList can not remove existing elements, and it can only grow by appending elements,
not inserting.

## Implementation
The `SynchronizedUnbalancedSnapshotList` extends `CopyOnWriteArrayList` which is a threadsafe implementation of ArrayList.
In `SynchronizedUnbalancedSnapshotList`, a concurrent hashmap (thread-safe implementation of hashmap) has been used to store the
version number of the list as key and the pointer to the last element of that version.
Whenever `snapshot` method is invoked, version number is incremented and the last index of array is stored in map.
Whenever we need the list at a particular versionwe need to get the sublist from 0 till end index (i.e the value part in map for that version).

PRO: Works in multithreaded environment, uses less space by only storing pointers (delta) in the map.
CON: If there are huge number of queries a lot of  new sublists would be returns hence memory usage would be increased in a heavy query based scenarios.


## Steps to Run Project through IDE
* Import the project as maven project in your favourite IDE (this project is developed in Intellij Idea)
* Open the maven tabs to get the list of all maven goals
* Execute `clean` goal
* Execute `compile` goal
* Execute `package` goal
* Execute `tests` goal to run the test cases (optional)
* A jar `assignment-1.0.jar` file would be generated in `target` folder in the root of project
* The jar can be executed by following ways
  * Right-click on the jar and click on `run assignment-1.0.jar` option
  * or, if you have java available in commandline then simply run `java -jar assignment-1.0.jar`
  * Other method can be to simply right-click on the `Driver.java` file and click on `run Driver.main()`

NOTE: Project can also be run through command-line if maven is installed on system.
      Installation and executing the commands depend on which OS the commands are being run but the goal names remain the same i.e
      clean,compile,test and package.