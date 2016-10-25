# DFA-NFA-String-Matcher

###Author: Luke Nitish Kumar	     

##Description:

The tool is capable of handling both Deterministic Finite Automata (DFA) and Non Deterministic Finite Automata (FNA).  
Tool is developed using java 1.6 and net beans IDE and the source code which is produced is a net beans project file. The net beans project file (filename: finiteAutomata) has all the source code and the .jar file (finiteAutomata/dist) which is the executable.
Implantation 
DFA:

The states filled in by the user are taken from the state transition table and using the table transition graph is generated. JGraph library is used to visualize the graph generated, and the string matching is done using the table. It’s done by finding the next states for each character input and the relevant state the machine is in. 
NFA:

The inputs for the NFA are taken from the transition table and the relevant graph is generated. From the input NFA an equal DFA is generated its done using mainly two functions, descriptions of them follows.
1.	Epsilon Closure
Function takes a set of states as a parameter and returns a set of states containing all the states, which can be reached from each individual state of the given set  on Epsilon transition.
2.	Move State 
Move states takes a set of states and an input character and returns all the states that can be reached on given input character form all states given.


Steps of converting a NFA to DFA
1.	The start state of DFA is created by taking the Epsilon closure of the start state of the NFA

2.	For each new DFA state, following is performed for each input character:
A.	Perform move State function to the newly created state
B.	Create new state by taking the Epsilon closure of the result (A).

3.	For each newly created state, perform step 2.

4.	Accepting states/Final states of DFA are all those states, which contain at least one of the final states from the NFA. 

The newly created DFA  is save in a format like the transition table and its used for string matching like a normal DFA when entering the trap state(a states where no transitions are defined for all input characters) the string matching identifies and sends the output which says the string is not accepted.
[All the intermediate steps and created data structures are printed out using System out calls which will be visible when the project is built in the net beans IDE]

Execution Mechanism:

By double clicking the .jar file the tool starts to execute, the First window which gives the user the option to select the Automata (DFA/NFA) to proceed with, and user is requested  to enter  the number of states in the automata and the alphabet  which the automat works. User should not delimit the alphabet with any characters Eg: abcd
By clicking the relevant automata user enters to a new window which has a table (Transition Table) which the user needs to fill, by default the first column and the column names are filled, user only needs to fill in the relevant states in each cell without any errors. Finally after entering all states user must click on of the cells in the first column (this is due to a bud in the JTable component in java the data won’t be collect until the correct event is called). After entering the states user should enter the initial state and the final state/states (all states are case sensitive) to enter multiple states user must enter each state with a comma delimited Eg: s0,s1,s2
Click on the button graph to generate the transition graph and a small window will appear to check the validity of strings (string matcher window)
If it's a NFA then there will be an additional button on the string matching window to generate the converted graph. There are images of windows of the tool in the preceding pages.
