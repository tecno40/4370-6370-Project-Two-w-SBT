\timing

SELECT name
FROM student
WHERE id = 199479;

SELECT name
FROM student
WHERE id >= 100000 AND id <= 900000;

SELECT name
FROM student, transcript
WHERE  id = studId AND crsCode = 'crsCode152954';

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

SELECT name
FROM student, transcript, course
WHERE student.id = transcript.studId AND
      transcript.crsCode = course.crsCode AND NOT EXISTS
      (
	SELECT crsCode
	FROM course
	WHERE deptId = 'deptId382933' AND
	      course.crsCode NOT IN
	      (
		SELECT crsCode
		FROM transcript
		WHERE student.id = transcript.studId
	      )
      );
