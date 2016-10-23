USE university;
SET profiling = 1;

SELECT name
FROM Student
WHERE id = 199479;

SELECT name
FROM Student
WHERE id >= 100000 AND id <= 900000;

SELECT name
FROM Student, Transcript
WHERE  id = studId AND crsCode = 'crsCode968614';

SELECT Student.name
FROM Student, Professor, Teaching, Transcript
WHERE Student.id = Transcript.studId AND
      Teaching.semester = Transcript.semester AND Teaching.crsCode = Transcript.crsCode AND
      Teaching.profId = Professor.id AND
      Professor.name = 'name642723';

SELECT name
FROM Student, Transcript, Course
WHERE Student.id = Transcript.studId AND
      Transcript.crsCode = Course.crsCode AND
      deptId = 'deptId551560' AND
      Student.name NOT IN
      (
	SELECT name
	FROM Student, Transcript, Course
	WHERE Student.id = Transcript.studId AND
      	      Transcript.crsCode = Course.crsCode AND
      	      deptId = 'deptId615497'
      );

SELECT name
FROM Student, Transcript, Course
WHERE Student.id = Transcript.studId AND
      Transcript.crsCode = Course.crsCode AND NOT EXISTS
      (
	SELECT crsCode
	FROM Course
	WHERE deptId = 'deptId615497' AND
	      Course.crsCode NOT IN
	      (
		SELECT crsCode
		FROM Transcript
		WHERE Student.id = Transcript.studId
	      )
      );

SHOW PROFILES;
