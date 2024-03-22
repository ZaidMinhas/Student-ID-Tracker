# Student-ID-Tracker

We built a Student ID Tracking System that maintains and operates on a list of N students, each with a unique 8 digit Student ID Code. By creating an ADT called CleverSIDC to hold this data. CleverSIDC changes it's data structure dynamically when there are too many or too few IDs to keep track of, from a simple Hashtable to a Binary Search Tree.

CleverSIDC includes a number of useful methods:
* SetSIDCThreshold(int n) that defines how many IDs until data structure should be changed from Hashtable to BST
* generate() which generates a random 8 digit code
* allKeys(CleverSIDC) returns a sorted sequence of the IDs
* add(CleverSIDC,key,value) Add a student with his `key` (ID) and `value` (info)
* remove(CleverSIDC,key), Remove a student with a given `key`
* getValues(CleverSIDC,key), get the info of student with given `key`
* nextKey(CleverSIDC,key), get the key after `key`
* prevKey(CleverSIDC,key), get the key before `key`
* rangeKey(key1, key2), get a sorted sequence of IDs from `key1` to `key2`

