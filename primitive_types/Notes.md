# Primitive Types

## Facts

* A type is a classification of data that spells out possible values for that type and the operations that can be performed on it
* Many languages provide types for Boolean, integer, character and floating point data
* Often, there are multiple integer and floating point types, depending on signedness and precision
* The width of these types is the number of bits of storage a corresponding variable takes in memory. For example, most implementations of C++ use 32 or 64 bits for an int. In Java an int is always 32 bits

## BitWise Shift
An int is 32 bits. If you bitwise shift an int by more than 32 bits, you might expect it to shift the entire number off to the right, returning 0 for positive inputs and -1 for negative inputs, but it doesn't;

Instead, modulo reduction is applied to the right hand operand based on the width of the left hand operand.

E.g. 21990232 decimal is 0001 0100 1111 1000 1011 0101 1000 in binary. If we do ```21990232 >>> 48```, because 48 is greater than 32 bits, you actually shift it by 48 % 32 = 16 bits instead. So the operation ```21990232 >>> 48``` yields 101001111 instead of 0.

## Maths
### Associative
Involving the condition that a group of quantities connected by operators gives the same result whatever their grouping, i.e. in whichever order the operations are performed, as long as the order of the quantities remains the same, e.g. ( a × b ) × c = a × ( b × c )

### Commutative
Involving the condition that a group of quantities connected by operators gives the same result whatever the order of the quantities involved, e.g. a × b = b × a

## References
* Java Primitive Data Types  https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
