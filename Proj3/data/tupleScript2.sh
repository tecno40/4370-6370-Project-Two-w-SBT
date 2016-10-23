#! /bin/bash
 
sbt "run-main cs4370.TestTupleGenerator 1000 1" > 1000_long_join_1.txt
sbt "run-main cs4370.TestTupleGenerator 1000 2" > 1000_long_join_2.txt
sbt "run-main cs4370.TestTupleGenerator 1000 3" > 1000_long_join_3.txt

sbt "run-main cs4370.TestTupleGenerator 2000 1" > 2000_long_join_1.txt
sbt "run-main cs4370.TestTupleGenerator 2000 2" > 2000_long_join_2.txt
sbt "run-main cs4370.TestTupleGenerator 2000 3" > 2000_long_join_3.txt

sbt "run-main cs4370.TestTupleGenerator 5000 1" > 5000_long_join_1.txt
sbt "run-main cs4370.TestTupleGenerator 5000 2" > 5000_long_join_2.txt
sbt "run-main cs4370.TestTupleGenerator 5000 3" > 5000_long_join_3.txt


