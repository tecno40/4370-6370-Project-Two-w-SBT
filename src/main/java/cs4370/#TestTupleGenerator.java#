package cs4370;
/*****************************************************************************************
 * @file  TestTupleGenerator.java
 *
 * @author   Sadiq Charaniya, John Miller
 */

import static java.lang.System.out;
import java.util.*;
/*****************************************************************************************
 * This class tests the TupleGenerator on the Student Registration Database defined in the
 * Kifer, Bernstein and Lewis 2006 database textbook (see figure 3.6).  The primary keys
 * (see figure 3.6) and foreign keys (see example 3.2.2) are as given in the textbook.
 */
public class TestTupleGenerator
{
    /*************************************************************************************
     * The main method is the driver for TestGenerator.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
	Random rn = new Random();
	int map = Integer.parseInt(args[1]);
        TupleGenerator test = new TupleGeneratorImpl ();

        test.addRelSchema ("Student",
                           "id name address status",
                           "Integer String String String",
                           "id",
                           null);

	Table Student = new Table("Student",
                           "id name address status",
                           "Integer String String String",
				  "id",map);
        
        test.addRelSchema ("Professor",
                           "id name deptId",
                           "Integer String String",
                           "id",
                           null);

	Table Professor = new Table("Professor",
                           "id name deptId",
                           "Integer String String",
				    "id",map);
        
        test.addRelSchema ("Course",
                           "crsCode deptId crsName descr",
                           "String String String String",
                           "crsCode",
                           null);

	Table Course = new Table("Course",
                           "crsCode deptId crsName descr",
                           "String String String String",
				 "crsCode",map);
        
        test.addRelSchema ("Teaching",
                           "crsCode semester profId",
                           "String String Integer",
                           "crcCode semester",
                           new String [][] {{ "profId", "Professor", "id" },
                                            { "crsCode", "Course", "crsCode" }});

	Table Teaching = new Table("Teaching",
                           "crsCode semester profId",
                           "String String Integer",
				   "crsCode semester",map);
	
        test.addRelSchema ("Transcript",
                           "studId crsCode semester grade",
                           "Integer String String String",
                           "studId crsCode semester",
                           new String [][] {{ "studId", "Student", "id"},
                                            { "crsCode", "Course", "crsCode" },
                                            { "crsCode semester", "Teaching", "crsCode semester" }});

	Table Transcript = new Table("Transcript",
                           "studId crsCode semester grade",
                           "Integer String String String",
				     "studId crsCode semester",map);
	
        String [] tables = { "Student", "Professor", "Course", "Teaching", "Transcript" };
	Table [] tables2 = {Student, Professor, Course, Teaching, Transcript};
        
        //int tups [] = new int [] { 10000, 1000, 2000, 50000, 5000 };
	int num = Integer.parseInt(args[0]);
	int tups [] = new int [] { num, num, num, num, num, };
    
        Comparable [][][] resultTest = test.generate (tups);
        
        for (int i = 0; i < resultTest.length; i++) {
            //out.println (tables [i]);
	    //out.println ( tables2[i].getName() );
            for (int j = 0; j < resultTest [i].length; j++) {
		/*
                for (int k = 0; k < resultTest [i][j].length; k++) {
                    out.print (resultTest [i][j][k] + ",");
                } // for
		*/
		tables2[i].insert( resultTest[i][j] );
                //out.println ();
            } // for
	    //tables2[i].print();
            //out.println ();
	    out.println("Number of tuples in " + tables[i] + " : " + tables2[i].getTuples().size());
        } // for

	List <Comparable []> tuples;
	ArrayList successTimesStudent = new ArrayList();
	ArrayList unsuccessTimesStudent = new ArrayList();
	ArrayList unsuccessTimesTeaching = new ArrayList();
	ArrayList successTimesTeaching = new ArrayList();
	ArrayList successTimesProfessor = new ArrayList();
	ArrayList unsuccessTimesProfessor = new ArrayList();
	ArrayList naturalJoinTimes = new ArrayList();
	ArrayList equiJoinTimes = new ArrayList();
	
	int selector,selector2;
	long something1,something2;
	out.println("Timing selects on primary keys");
	Table tempTable;
	for(int i=0;i<100;i++){
	    selector  = rn.nextInt(1000000);
	    selector2 = rn.nextInt(1000000);
	    //num = selector;
	    KeyType key = new KeyType(selector);

	    /*
	    something1 = System.nanoTime();
	    tempTable = Student.select(key);
	    something2 = System.nanoTime();
	    if( tempTable.getTuples().size() == 0 ) {
		//out.println("adding to unsuccessful");
		unsuccessTimesStudent.add(something2-something1);
	    }
	    else {
		//out.println("adding to successful");
		successTimesStudent.add(something2-something1);		
	    }
	    
	    something1 = System.nanoTime();
	    tempTable = Professor.select(key);
	    something2 = System.nanoTime();

	    if( tempTable.getTuples().size() == 0 ) {
		out.println("adding to unsuccessful professor");
		unsuccessTimesProfessor.add(something2-something1);
	    }
	    else {
		out.println("adding to successful professor");
		successTimesProfessor.add(something2-something1);		
	    }
	    
	    
	    something1 = System.nanoTime();
	    tempTable = Teaching.select(t->t[Teaching.col("profId")].equals(rn.nextInt(100000) ) );
	    something2 = System.nanoTime();
	    if( tempTable.getTuples().size() == 0 ) {
		out.println("adding to unsuccessful teaching");
		unsuccessTimesTeaching.add(something2-something1);
	    }
	    else { 
		out.println("adding to successful teaching");
		successTimesTeaching.add(something2-something1);		
	    }
	    
	    
	    //out.println("Student join Transcript on Student.id == Transcript.studId");
	    something1 = System.nanoTime();
	    Transcript.join("studId","id",Student);
	    something2 = System.nanoTime();
	    equiJoinTimes.add(something2-something1);
	    
	    //out.println("Professor join Teaching on Professor.id == Teaching.profID");
	    something1 = System.nanoTime();
	    Professor.join("id","profId",Teaching);
	    something2 = System.nanoTime();
	    equiJoinTimes.add(something2-something1);

	    
	
	    //out.println("Teaching join Course");
	    something1 = System.nanoTime();
	    Teaching.join(Course);
	    something2 = System.nanoTime();
	    naturalJoinTimes.add(something2-something1);
	
	    //out.println("Teaching join Transcript");
	    something1 = System.nanoTime();
	    Teaching.join(Transcript);
	    something2 = System.nanoTime();
	    naturalJoinTimes.add(something2-something1);
	
	    //out.println("Course join Transcript");
	    something1 = System.nanoTime();
	    Course.join(Transcript);
	    something2 = System.nanoTime();
	    naturalJoinTimes.add(something2-something1);
	    */

	    something1 = System.nanoTime();
	    tempTable = Student.select(t->(t[Student.col("id")].compareTo(rn.nextInt(100000)) >= 0
					   && t[Student.col("id")].compareTo( rn.nextInt(10000) )  <= 0 ) );
	    something2 = System.nanoTime();
	    if( tempTable.getTuples().size() == 0 ) {
		out.println("adding to unsuccessful teaching");
		unsuccessTimesTeaching.add(something2-something1);
	    }
	    else { 
		out.println("adding to successful teaching");
		successTimesTeaching.add(something2-something1);		
	    }
	    
	}
 	out.println("SuccessTimesTeaching: " + successTimesTeaching);
	for(int i=0;i<successTimesTeaching.size();i++) out.println( successTimesTeaching.get(i) );
	out.println("UnSuccessTimesTeaching: ");
	for(int i=0;i<unsuccessTimesTeaching.size();i++) out.println( unsuccessTimesTeaching.get(i) );
	out.println("SuccessTimesProfessor: ");
	for(int i=0;i<successTimesProfessor.size();i++) out.println( successTimesProfessor.get(i) );
	out.println("UnSuccessTimesProfessor: ");
	for(int i=0;i<unsuccessTimesProfessor.size();i++) out.println( unsuccessTimesProfessor.get(i) );
	out.println("SuccessTimesStudent: " );
	for(int i=0;i<successTimesStudent.size();i++) out.println( successTimesStudent.get(i) );
	out.println("UnSuccessTimesStudent: ");
	for(int i=0;i<unsuccessTimesStudent.size();i++) out.println( unsuccessTimesStudent.get(i) );
	out.println("NaturalJoinTimes: ");
	for(int i=0;i<naturalJoinTimes.size();i++) out.println( naturalJoinTimes.get(i) );
	out.println("EquiJoinTimes: ");
	for(int i=0;i<equiJoinTimes.size();i++) out.println( equiJoinTimes.get(i) );
	
    } // main

} // TestTupleGenerator

