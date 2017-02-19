package me.tyler.carousel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a spinning carousel of words loaded from a MySQL database.
 * 
 * Currently designed for use with the Connector/J JDBC Driver.
 * */
public class WordCarousel extends JPanel
{
	private static final long serialVersionUID = 7315190309727032755L;
	
	private static final String DB_URL = "jdbc:mysql://localhost/db";
	
	private static final String DB_USERNAME = "uname";
	private static final String DB_PASSWORD = "pass";
	
	private ArrayList<String> words;
	
	/**The angle (in radians) between each word (2pi divided by total words in the carousel.)*/
	private float wordAngle;
	
	/**Radius of the carousel.*/
	private float radius;
	
	/**Diameter of the carousel.*/
	private float diameter;
	
	/**Current Offset from default carousel position.*/
	private float offset;
	
	/**Speed at which words spin around the carousel*/
	private float spinSpeed = 1f;
	
	/**Delay (in milliseconds) between subsequent repaint calls. FPS is approx. 1000/frameDelay*/
	private static int timerDelay = 16;
	
	/**Timestamps (in nanoseconds) for calls to the carousel's paint() function.*/
	private long currentFrameTime, lastFrameTime;
	
	/**The difference between current and last frame timestamp.*/
	private float frameDelay;

	public static void main(String[] args)
	{
		ArrayList<String> words;
		try 
		{
			words = getWordListFromDB();
		} catch (Exception e) 
		{
			e.printStackTrace();
			
			words = new ArrayList<String>();
			
			words.add("Something");
			words.add("Went");
			words.add("Wrong");
		}
		
		JFrame hwFrame = new JFrame("Hello 18B");
		WordCarousel carousel = new WordCarousel(words, 100);
		
		/**Create a timer to continuously repaint the carousel.*/
		Timer carouselTimer = new Timer(timerDelay, new CarouselUpdater(carousel));
		carouselTimer.setRepeats(true);
		carouselTimer.start();
		
		hwFrame.add(carousel);
		hwFrame.pack();
		hwFrame.setVisible(true);
		hwFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Retrieves all words from a table named "words" on the pre-specified database.
	 * 
	 * Throws a million different exceptions because I'm lazy.
	 *
	 * @return	an ArrayList of all words retrieved from the database
	 **/
	public final static ArrayList<String> getWordListFromDB() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
        //Class.forName("com.mysql.jdbc.Driver").newInstance();
		
        Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		
		//Select all words in descending order.
		PreparedStatement wordListStatement = dbConnection.prepareStatement("SELECT word FROM words ORDER BY word DESC");
		
		ResultSet result = wordListStatement.executeQuery();
		
		ArrayList<String> words = new ArrayList<String>();
		
		while(result.next())
		{
			words.add(result.getString(1));
		}
		
		dbConnection.close();
		
		return words;
	}
	
	/**
	 * @param	words	the words to spin around the carousel.
	 * @param	radius	the radius (in pixels) to spin the words around.
	 * 
	 * */
	public WordCarousel(ArrayList<String> words, float radius)
	{
		super();
		
		this.words = words;
		
		this.radius = radius;
		
		this.diameter = 2*radius;
		
		this.wordAngle = (float) Math.toRadians(360/words.size());
		
		this.lastFrameTime = System.nanoTime();
		
		/**The width and height of the carousel panel.*/
		final int panelSize = 450;
		
		this.setPreferredSize(new Dimension(panelSize, panelSize));
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		//Current word angle
		float angle = 0;
		
		//Calculate the frame delta
		currentFrameTime = System.nanoTime();
		
		frameDelay = (float)(currentFrameTime-lastFrameTime)/1000000000f;

		lastFrameTime = currentFrameTime;
		
		//Turn on antialiasing.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i = 0; i < words.size(); i++)
		{
			angle = ((offset*spinSpeed) + (wordAngle*i));
			g2d.drawString(words.get(i), (int)(diameter+radius*Math.cos(angle)), (int)(diameter+radius*Math.sin(angle)));
		}
		
		//Change offset relative to the time since last call to paint().
		offset += frameDelay;
	}
}
