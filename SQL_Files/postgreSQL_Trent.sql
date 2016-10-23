\timing

SELECT name
FROM student
WHERE id = 199479;

SELECT name
FROM student
WHERE id >= 100000 AND id <= 900000;

Query 3
SELECT name
FROM student, transcript
WHERE  id = studId AND crsCode = 'crsCode832840';

Query 3A
SELECT name
FROM Student, 
	(SELECT *
	 FROM Transcript
	 WHERE crsCode = 'crsCode85082'
	 ) AS x
WHERE  id = studId;

Query 3B
SELECT name
FROM Student, 
	(SELECT studId
	 FROM Transcript
	 WHERE crsCode = 'crsCode85082'
	 ) AS x
WHERE  id = studId;


SELECT student.name
FROM student, professor, teaching, transcript
WHERE student.id = transcript.studId AND
      teaching.semester = transcript.semester AND teaching.crsCode = transcript.crsCode AND
      teaching.profId = professor.id AND
      professor.name = 'name198080';

SELECT name
FROM student, transcript, course
WHERE student.id = transcript.studId AND
      transcript.crsCode = course.crsCode AND
      deptId = '521238'
EXCEPT
	(
      	SELECT name
	FROM student, transcript, course
	WHERE student.id = transcript.studId AND
      	      transcript.crsCode = course.crsCode AND
      	      deptId = 'deptId382933'
        );


Query 6
SELECT name
FROM student, transcript, course
WHERE student.id = transcript.studId AND
      transcript.crsCode = course.crsCode AND NOT EXISTS
      (
	SELECT crsCode
	FROM course
	WHERE deptId = 'deptId477615' AND
	      course.crsCode NOT IN
	      (
		SELECT crsCode
		FROM transcript
		WHERE student.id = transcript.studId
	      )
      );

Query 6a
SELECT name
  FROM (SELECT *
	  FROM transcript,student
	 WHERE student.id=transcript.studId) AS a
 WHERE a.crsCode NOT IN (SELECT transacript2.crsCode
			   FROM transcript transacript2
			  WHERE transacript2.crsCode NOT IN (SELECT course.crsCode
							       FROM course
							      WHERE course.deptId = 'deptId477615'

							     )
			)

Query 6b
SELECT name
  FROM (SELECT *
	  FROM transcript,student
	 WHERE student.id=transcript.studId) AS a
 WHERE a.crsCode NOT IN (SELECT *
			   FROM (SELECT transcript2.crsCode
				   FROM transcript transcript2
				) AS transcript3
			  WHERE transcript3.crsCode NOT IN (SELECT course.crsCode
							       FROM course
							      WHERE course.deptId = 'deptId477615'

							     )
			)
