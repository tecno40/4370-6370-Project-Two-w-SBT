Query 3- Original
SELECT name
FROM Student, Transcript
WHERE  id = studId AND crsCode = 'crsCode85082';

Query 3- Modification 1
SELECT name
FROM Student, 
	(SELECT *
	 FROM Transcript
	 WHERE crsCode = 'crsCode85082'
	 ) AS x
WHERE  id = studId;

Query 3- Modifications 2
SELECT name
FROM Student, 
	(SELECT studId
	 FROM Transcript
	 WHERE crsCode = 'crsCode85082'
	 ) AS x
WHERE  id = studId;




query 6-original
SELECT name
FROM Student, Transcript, Course
WHERE Student.id = Transcript.studId AND
      Transcript.crsCode = Course.crsCode AND NOT EXISTS
      (
	SELECT crsCode
	FROM Course
	WHERE deptId = 'deptId344795' AND
	      Course.crsCode NOT IN
	      (
		SELECT crsCode
		FROM Transcript
		WHERE Student.id = Transcript.studId
	      )
      );

query 6-Modification 1
SELECT name
  FROM (SELECT *
	  FROM transcript,student
	 WHERE student.id=transcript.studId) AS a
 WHERE a.crsCode NOT IN (SELECT transacript2.crsCode
			   FROM transcript transacript2
			  WHERE transacript2.crsCode NOT IN (SELECT course.crsCode
							       FROM course
							      WHERE course.deptId = 'deptId344795'

							     )
			)


query 6-modification 2
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
							      WHERE course.deptId = 'deptId344795'

							     )
			)
