package cs4370;
/*****************************************************************************************
 * @file  MovieDB.java
 *
 * @author   John Miller
 */

import static java.lang.System.out;

/*****************************************************************************************
 * The MovieDB class makes a Movie Database.  It serves as a template for making other
 * databases.  See "Database Systems: The Complete Book", second edition, page 26 for more
 * information on the Movie Database schema.
 */
class MovieDB
{
    /*************************************************************************************
     * Main method for creating, populating and querying a Movie Database.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
	out.println ();

	/*	Table movie = new Table ("movie", "title year length genre studioName producerNo",
				 "String Integer Integer String String Integer", "title year");

	Table cinema = new Table ("cinema", "title year length genre studioName producerNo",
				  "String Integer Integer String String Integer", "title year");

	Table movieStar = new Table ("movieStar", "name address gender birthdate",
				     "String String Character String", "name");

	Table starsIn = new Table ("starsIn", "movieTitle movieYear starName",
				   "String Integer String", "movieTitle movieYear starName");

	Table movieExec = new Table ("movieExec", "certNo name address fee",
				     "Integer String String Float", "certNo");

	Table studio = new Table ("studio", "name address presNo",
				  "String String Integer", "name");*/

	String [] attributes_movie = {"title", "year", "length", "genre", "studioName", "producerNo"};
	String [] attributes_cinema = {"title","year","length","genre","studioName","producerNo"};	
	String [] attributes_movieStar = {"name","address","gender","birthdate"};
	String [] attributes_starsIn = {"movieTitle","movieYear","starName"};
	String [] attributes_movieExec = {"certNo", "name","address","fee"};
	String [] attributes_studio =  {"name","address","presNo"};
	    
	Class [] domains_movie = {String.class,Integer.class,Integer.class,String.class,String.class, Integer.class};
	Class [] domains_cinema = {String.class, Integer.class, Integer.class, String.class, String.class, Integer.class};
	Class [] domains_movieStar = {String.class, String.class, Character.class, String.class};
	Class [] domains_starsIn = {String.class, Integer.class, String.class};
	Class [] domains_movieExec = {Integer.class, String.class, String.class, Float.class };
	Class [] domains_studio = {String.class, String.class, Integer.class};
	
	String [] key_movie = {"title","year"};
	String [] key_cinema = {"title","year"};
	String [] key_movieStar = {"name"};
	String [] key_starsIn = {"movieTitle","movieYear","starName"};
	String [] key_movieExec = {"certNo"};
	String [] key_studio = {"name"};

	/*Table movie = new Table ("movie", "title year length genre studioName producerNo",
				 "String Integer Integer String String Integer", "title year");

	Table cinema = new Table ("cinema", "title year length genre studioName producerNo",
				  "String Integer Integer String String Integer", "title year");

	Table movieStar = new Table ("movieStar", "name address gender birthdate",
				     "String String Character String", "name");

	Table starsIn = new Table ("starsIn", "movieTitle movieYear starName",
				   "String Integer String", "movieTitle movieYear starName");

	Table movieExec = new Table ("movieExec", "certNo name address fee",
				     "Integer String String Float", "certNo");

	Table studio = new Table ("studio", "name address presNo",
	"String String Integer", "name");*/

        
	Table movie = new Table("movie",attributes_movie,domains_movie,key_movie,1);
	Table cinema = new Table("cinema",attributes_cinema,domains_cinema,key_cinema,1);
	Table movieStar = new Table("movieStar",attributes_movieStar,domains_movieStar,key_movieStar,1);
	Table starsIn = new Table("starsIn",attributes_starsIn,domains_starsIn,key_starsIn,1);
	Table movieExec = new Table("movieExec",attributes_movieExec,domains_movieExec,key_movieExec,1);
	Table studio = new Table("studio",attributes_studio,domains_studio,key_studio,1);

	Comparable [] film0 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
	Comparable [] film1 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
	Comparable [] film2 = { "Rocky", 1985, 200, "action", "Universal", 12125 };
	Comparable [] film3 = { "Rambo", 1978, 100, "action", "Universal", 32355 };
	out.println ();
	movie.insert (film0);
	movie.insert (film1);
	movie.insert (film2);
	movie.insert (film3);
	movie.print ();
	System.out.println("movie.printIndex():");
	movie.printIndex();

	Comparable [] film4 = { "Galaxy_Quest", 1999, 104, "comedy", "DreamWorks", 67890 };
	out.println ();
	cinema.insert (film2);
	cinema.insert (film3);
	cinema.insert (film4);
	cinema.print ();
	System.out.println("cinema.printIndex():");
	cinema.printIndex();

	Comparable [] star0 = { "Carrie_Fisher", "Hollywood", 'F', "9/9/99" };
	Comparable [] star1 = { "Mark_Hamill", "Brentwood", 'M', "8/8/88" };
	Comparable [] star2 = { "Harrison_Ford", "Beverly_Hills", 'M', "7/7/77" };
	out.println ();
	movieStar.insert (star0);
	movieStar.insert (star1);
	movieStar.insert (star2);
	movieStar.print ();
	System.out.println("movieStar.printIndex():");
	movieStar.printIndex();

	Comparable [] cast0 = { "Star_Wars", 1977, "Carrie_Fisher" };
	out.println ();
	starsIn.insert (cast0);
	starsIn.print ();
	System.out.println("starsIn.printIndex():");
	starsIn.printIndex();

	Comparable [] exec0 = { 9999, "S_Spielberg", "Hollywood", 10000.00 };
	out.println ();
	movieExec.insert (exec0);
	movieExec.print ();
	System.out.println("movieExec.printIndex():");
	movieExec.printIndex();
	
	Comparable [] studio0 = { "Fox", "Los_Angeles", 7777 };
	Comparable [] studio1 = { "Universal", "Universal_City", 8888 };
	Comparable [] studio2 = { "DreamWorks", "Universal_City", 9999 };
	out.println ();
	studio.insert (studio0);
	studio.insert (studio1);
	studio.insert (studio2);
	studio.print ();
	System.out.println("studio.printIndex():");
	studio.printIndex();
	/*
	System.out.println("Going to start saving.");
	
	movie.save ();
	cinema.save ();
	movieStar.save ();
	starsIn.save ();
	movieExec.save ();
	studio.save ();
	*/

	movieStar.printIndex ();

	//--------------------- project: title year

	out.println ();
	Table t_project = movie.project ("title year");
	t_project.print ();

	//--------------------- select: equals, &&
	/*
	out.println ();
	Table t_select = movie.select (t -> t[movie.col("title")].equals ("Star_Wars") &&
				       t[movie.col("year")].equals (1977));
	t_select.print ();
	*/
	//--------------------- select: <
	/*
	out.println ();
	Table t_select2 = movie.select (t -> (Integer) t[movie.col("year")] < 1980);
	t_select2.print ();
	*/
	//--------------------- indexed select: key

	out.println ();
	Table t_iselect = movieStar.select (new KeyType ("Harrison_Ford"));
	t_iselect.print ();

	//--------------------- union: movie UNION cinema

	out.println ();
	Table t_union = movie.union (cinema);
	t_union.print ();

	//--------------------- minus: movie MINUS cinema

	out.println ();
	Table t_minus = movie.minus (cinema);
	t_minus.print ();

	//--------------------- equi-join: movie JOIN studio ON studioName = name

	out.println ();
	Table t_join = movie.join ("studioName", "name", studio);
	t_join.print ();

	out.println("index print after join");
	t_join.printIndex();
	
	out.println();

	Table t_goodJoin = movie.join("title year","title year",cinema);
	/*
	out.println ();
	Table t_iselectjoin = t_join.select (new KeyType ("7777"));
	t_iselectjoin.print ();
	*/
	//--------------------- natural join: movie JOIN studio

	out.println ();
	Table t_join2 = movie.join (cinema);
	t_join2.print ();

	

    } // main

} // MovieDB class
