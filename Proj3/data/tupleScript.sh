#! /bin/bash
 
sbt "run-main cs4370.TestTupleGenerator 100 1" > 100_long_join_1.txt
sbt "run-main cs4370.TestTupleGenerator 100 2" > 100_long_join_2.txt
sbt "run-main cs4370.TestTupleGenerator 100 3" > 100_long_join_3.txt

sbt "run-main cs4370.TestTupleGenerator 200 1" > 200_long_join_1.txt
sbt "run-main cs4370.TestTupleGenerator 200 2" > 200_long_join_2.txt
sbt "run-main cs4370.TestTupleGenerator 200 3" > 200_long_join_3.txt

sbt "run-main cs4370.TestTupleGenerator 500 1" > 500_long_join_1.txt
sbt "run-main cs4370.TestTupleGenerator 500 2" > 500_long_join_2.txt
sbt "run-main cs4370.TestTupleGenerator 500 3" > 500_long_join_3.txt


