There are six charts included in this submission. Indexed Select Times.png and
Non-Indexed Select Times.png are fairly self explanatory: they chart the
performance times of our implementation of the indexed and non-indexed select
operations as per the database outlined by the professor.

For the join operations things are a little bit more complicated. JoinTime5000A
plots the average time to join two tables for both EquiJoin and Natural Join
under TreeMap, BpTreeMap, and LinHash index data structures. However, this data
is pretty heavily illegible due to the poor performance of the linear hashmap
index data structure. Thus, the JoinTimes5000B chart plots the performance of
the NaturalJoin and EquiJoin implementations for just the BpTreeMap and TreeMap
index data structures in order to more clearly compare their performances. It
is worth mentioning that EquiJoin and NaturalJoin times are significantly
improved through using our BpTreeMap index data structure as compared to the
TreeMap data structure provided by Oracle, at least asymptotically so. From here
you will notice that there are two other charts: JoinTimes2000A and
JoinTimes2000B. These charts plot the performance times of NaturalJoin and
EquiJoin operations and include the performance under nested loop join
implementations. The former of the two clearly shows that the nested loop
implementation is significantly inferior to the performance obtained when
taking advantage of the TreeMap, BpTreeMap and LinHashMap index data structures
as a whole. The latter of the two charts excludes the performance of the nested
loop implementations in order to more clearly compare the performance of our
join operation implementations utilizing the TreeMap, BpTreeMap and LinHashMap
index data structures. It is interesting to note here that the performance of
our join implementation is much improved by the usage of the LinHashMap over
the BpTreeMap index data structure. Both implementations, however, are improved
by utilizing the stock Oracle provided TreeMap index data structure. 

The work on this project was divided as follows: 

Teresa and Jason fixed errors from Project 2 implementation with BpTreeMap. Sharon and Trent fixed errors from Project 2 implementation with LinHashMap. I integrated the Table.java class into the TestTupleImplementation.java class, ran the simulations to measure the performance of our implementations, and charted them through the use of an Excel file and excel charting tools. 
