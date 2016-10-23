#! /bin/bash
 
sbt "run-main cs4370.TestTupleGenerator 100 1" > 100_range.txt
sbt "run-main cs4370.TestTupleGenerator 100 2" > 100_range_2.txt
sbt "run-main cs4370.TestTupleGenerator 100 3" > 100_range_3.txt

sbt "run-main cs4370.TestTupleGenerator 200 1" > 200_range_1.txt
sbt "run-main cs4370.TestTupleGenerator 200 2" > 200_range_2.txt
sbt "run-main cs4370.TestTupleGenerator 200 3" > 200_range_3.txt

sbt "run-main cs4370.TestTupleGenerator 500 1" > 500_range_1.txt
sbt "run-main cs4370.TestTupleGenerator 500 2" > 500_range_2.txt
sbt "run-main cs4370.TestTupleGenerator 500 3" > 500_range_3.txt

sbt "run-main cs4370.TestTupleGenerator 1000 1" > 1000_range_1.txt
sbt "run-main cs4370.TestTupleGenerator 1000 2" > 1000_range_2.txt
sbt "run-main cs4370.TestTupleGenerator 1000 3" > 1000_range_3.txt

sbt "run-main cs4370.TestTupleGenerator 2000 1" > 2000_range_1.txt
sbt "run-main cs4370.TestTupleGenerator 2000 2" > 2000_range_2.txt
sbt "run-main cs4370.TestTupleGenerator 2000 3" > 2000_range_3.txt

sbt "run-main cs4370.TestTupleGenerator 5000 1" > 5000_range_1.txt
sbt "run-main cs4370.TestTupleGenerator 5000 2" > 5000_range_2.txt
sbt "run-main cs4370.TestTupleGenerator 5000 3" > 5000_range_3.txt


