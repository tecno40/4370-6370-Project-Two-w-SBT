package cs4370;
//STEP 1. Import required packages
import java.sql.*;
import java.util.*;

public class FirstExample {
    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost/EMP";
    //static final String DB_URL = "jdbc:postgresql://localhost/emp";
    //static final String DB_URL = "jdbc:mysql://localhost/university";
    static final String DB_URL = "jdbc:postgresql://localhost/university";

    //  Database credentials
    //static final String USER = "root";
    static final String USER = "user";
    static final String PASS = "user";

    public static void main(String[] args) {
	Connection conn = null;
	Statement stmt = null;
	
	try{
	    System.out.println("HI!");
	    //STEP 2: Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");
	    //Class.forName("org.postgresql.Driver");
	    
	    //STEP 3: Open a connection
	    System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    
	    //STEP 4: Execute a query
	    System.out.println("Creating statement...");
	    stmt = conn.createStatement();
	    String sql;
	    ResultSet rs;

	    Random rn = new Random();
	    TupleGenerator test = new TupleGeneratorImpl ();
	    
	    test.addRelSchema ("Student",
			       "id name address status",
			       "Integer String String String",
			       "id",
			       null);

	    sql = "CREATE TABLE Student "        +
		  "(id INTEGER not NULL, "       +
		  " name VARCHAR(12) not NULL, " +
		  " address VARCHAR(15), "      +
		  " status VARCHAR(15))";
	    System.out.println("Adding table: Student");
	    stmt.executeUpdate(sql);
	    
	    test.addRelSchema ("Professor",
			       "id name deptId",
			       "Integer String String",
			       "id",
			       null);

	    sql = "CREATE TABLE Professor "      +
		  "(id INTEGER not NULL, "       +
		  " name VARCHAR(12) not NULL, " +
		  " deptId VARCHAR(15))";

	    System.out.println("Adding table: Professor");
	    stmt.executeUpdate(sql);        
	    test.addRelSchema ("Course",
			       "crsCode deptId crsName descr",
			       "String String String String",
			       "crsCode",
			       null);
	    
	    sql = "CREATE TABLE Course "            +
		  "(crsCode VARCHAR(15) not NULL, " +
		  " deptId VARCHAR(15) not NULL, "  +
	          " crsName VARCHAR(15), "         +
		  " descr VARCHAR(15))" ;
	    System.out.println("Adding table: course");
	    stmt.executeUpdate(sql);
	    
	    test.addRelSchema ("Teaching",
			       "crsCode semester profId",
			       "String String Integer",
			       "crsCode semester",
			       new String [][] {{ "profId", "Professor", "id" },
						{ "crsCode", "Course", "crsCode" }});

	    sql = "CREATE TABLE Teaching "            +
		  "(crsCode VARCHAR(15) not NULL, "   +
		  " semester VARCHAR(15) not NULL, "  +
		  " profId INTEGER)" ;
	    
	    System.out.println("Adding table: Teaching");
	    stmt.executeUpdate(sql);
	    
	    test.addRelSchema ("Transcript",
			       "studId crsCode semester grade",
			       "Integer String String String",
			       "studId crsCode semester",
			       new String [][] {{ "studId", "Student", "id"},
						{ "crsCode", "Course", "crsCode" },
						{ "crsCode semester", "Teaching", "crsCode semester" }});
	    

	    sql = "CREATE TABLE Transcript " +
		  "(studId INTEGER not NULL, " +
		  " crsCode VARCHAR(15) not NULL, " +
		  " semester VARCHAR(15) not NULL," +
		  " grade VARCHAR(15) )";
	    System.out.println("Adding table: Transcript");
	    stmt.executeUpdate(sql);
	    
	    //String [] tables = { "Student", "Professor", "Course", "Teaching", "Transcript" };
	    //Table [] tables2 = {Student, Professor, Course, Teaching, Transcript};
	    
	    //int tups [] = new int [] { 10000 , 1000 , 2000 , 50000 , 5000 };
	    //int num = Integer.parseInt(args[0]);
	    int tups []   = new int [] { 10000 , 1000 , 2000 , 50000 , 5000 };
	    
	    Comparable [][][] resultTest = test.generate (tups);
	    
	    for (int i = 0; i < resultTest.length; i++) {
		switch(i)
		    {
		    case 0:
			System.out.println("Adding to students.");
			break;
		    case 1:
			System.out.println("Adding to Professors.");
			break;
		    case 2:
			System.out.println("Adding to Course.");
			break;
		    case 3:
			System.out.println("Adding to Teaching.");
			break;
		    case 4:
			System.out.println("Adding to Transcript.");
			break;
		    default:
			break;
		    }
		//out.println (tables [i]);
		//out.println ( tables2[i].getName() );
		for (int j = 0; j < resultTest [i].length; j++) {
		    /*
		      for (int k = 0; k < resultTest [i][j].length; k++) {
		      out.print (resultTest [i][j][k] + ",");
		      } // for
		    */
		    switch(i)
			{
			case 0:
			    sql = "INSERT INTO Student VALUES (" +
				resultTest[i][j][0] + ",'"  +
				resultTest[i][j][1] + "','" +
				resultTest[i][j][2] + "','" +
				resultTest[i][j][3] + "')" ;
			    System.out.println("Adding row into Student");
			    stmt.executeUpdate(sql);
			    break;
			case 1:
			    sql = "INSERT INTO Professor VALUES (" +
				resultTest[i][j][0] + ",'"  +
				resultTest[i][j][1] + "','" +
				resultTest[i][j][2] + "')" ;
			    System.out.println("Adding row into Professor");
			    stmt.executeUpdate(sql);
			    break;
			case 2:
			    sql = "INSERT INTO Course VALUES ('" +
				resultTest[i][j][0] + "','"  +
				resultTest[i][j][1] + "','" +
				resultTest[i][j][2] + "','" +
				resultTest[i][j][3] + "')" ;
			    System.out.println("Adding row into Course");
			    stmt.executeUpdate(sql);
			    break;
			case 3:
			    sql = "INSERT INTO Teaching VALUES ('" +
				resultTest[i][j][0] + "','"  +
				resultTest[i][j][1] + "'," +
				resultTest[i][j][2] + ")" ;
			    
			    System.out.println("Adding row into Teaching");
			    stmt.executeUpdate(sql);
			    break;
			case 4:
			    sql = "INSERT INTO Transcript VALUES (" +
				resultTest[i][j][0] + ",'"  +
				resultTest[i][j][1] + "','" +
				resultTest[i][j][2] + "','" +
				resultTest[i][j][3] + "')" ;
			    System.out.println("Adding row into Transcript");
			    stmt.executeUpdate(sql);
			    break;
			default:
			    System.out.println("missed insertion");
			}
		    //tables2[i].insert( resultTest[i][j] );
		    
		    //out.println ();
		} // for
		//tables2[i].print();
		//out.println ();
		//out.println("Number of tuples in " + tables[i] + " : " + tables2[i].getTuples().size());
	    } // for
	    //sql = "SELECT id, first, last, age FROM Employees";
	    sql = "SELECT * FROM Student";
	    rs = stmt.executeQuery(sql);
	
	    //STEP 5: Extract data from result set
	    while(rs.next()){
		//Retrieve by column name
		int    id      = rs.getInt("id");
		String name    = rs.getString("name");
		String address = rs.getString("address");
		String status  = rs.getString("status");

		//Display values
		System.out.print("ID: "         + id);
		System.out.print(", Name: "     + name);
		System.out.print(", Address: "  + address);
		System.out.println(", Status: " + status);
	    }
	    
	    sql = "SELECT * FROM Professor";
	    rs = stmt.executeQuery(sql);

	    //STEP 5: Extract data from result set
	    while(rs.next()){
		//Retrieve by column name
		int    id     = rs.getInt("id");
		String name   = rs.getString("name");
		String deptId = rs.getString("deptId");

		//Display values
		System.out.print("ID: "       + id);
		System.out.print(", Name: "   + name);
		System.out.println(", Dept: " + deptId);
	    }
	    sql = "SELECT * FROM Course";
	    rs = stmt.executeQuery(sql);

	    //STEP 5: Extract data from result set
	    while(rs.next()){
		//Retrieve by column name
		String crsCode = rs.getString("crsCode");
		String deptId  = rs.getString("deptId");
		String crsName = rs.getString("crsName");
		String descr   = rs.getString("descr");

		//Display values
		System.out.print("Course Code: "     + crsCode);
		System.out.print(", Dept: "          + deptId);
		System.out.print(", Course Name: "   + crsName);
		System.out.println(", Description: " + descr);
	    }
	    sql = "SELECT * FROM Teaching";
	    rs = stmt.executeQuery(sql);

	    //STEP 5: Extract data from result set
	    while(rs.next()){
		//Retrieve by column name
		String    crsCode  = rs.getString("crsCode");
		String    semester = rs.getString("semester");
		int       profId   = rs.getInt("profId");

		//Display values
		System.out.print("Course Code: " + crsCode);
		System.out.print(", Semester: "  + semester);
		System.out.println(", Professor: " + profId);
	    }

	    sql = "SELECT * FROM Transcript";
	    rs = stmt.executeQuery(sql);

	    //STEP 5: Extract data from result set
	    while(rs.next()){
		//Retrieve by column name
		int    studId   = rs.getInt("studId");
		String crsCode  = rs.getString("crsCode");
		String semester = rs.getString("semester");
		String grade    = rs.getString("grade");

		//Display values
		System.out.print("Student ID: " + studId);
		System.out.print(", Course: "   + crsCode);
		System.out.print(", Semester: " + semester);
		System.out.println(", Grade: "  + grade);
	    }
	    //STEP 6: Clean-up environment
	    rs.close();
	    stmt.close();
	    conn.close();
	}catch(SQLException se){
	    //Handle errors for JDBC
	    se.printStackTrace();
	}catch(Exception e){
	    //Handle errors for Class.forName
	    e.printStackTrace();
	}finally{
	    //finally block used to close resources
	    try{
		if(stmt!=null)
		    stmt.close();
	    }catch(SQLException se2){
	    }// nothing we can do
	    try{
		if(conn!=null)
		    conn.close();
	    }catch(SQLException se){
		se.printStackTrace();
	    }//end finally try
	}//end try
	System.out.println("Goodbye!");
    }//end main
}//end FirstExample
