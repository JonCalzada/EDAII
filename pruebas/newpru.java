 /**  By Kristi Thompson and Sean McLaughlin
 *
 *	  April 10 & 11, 1997
 *
 *    CISC 235 - Information Structures (Winter 1997) taught by David Alex Lamb
 *
 *    Dept of Computer Science
 *    Queen's University, Kingston
 *
 *    kristi@Seanster.com , Seanster@Seanster.com
 *
 *    http://www.Seanster.com/BplusTree
 *
 * Feel free to copy and modify this applet, but please give credit
 * to the original author where appropriate
 *
 * This applet was inspired by and partly based upon the BST Search Tree Applet
 * by James Stewart.
 *
 * http://www.dgp.toronto.edu/people/JamesStewart/applets/bst/bst-property.html
 *
 */
 
import java.util.*;
import java.awt.*;


/** A BplusTree

	Uses the following classes:

	INode (Inner nodes, which hold keys and pointers to other nodes
	
	Bucket (leaf nodes that keys and their associated data)
	
	Node (abstract superclass for INode and Bucket)

*/


 public class BplusTree extends java.applet.Applet
 {

// DEFINES
static final int N = 2;
static final int Ndiv2 = 1;

boolean PROMOTION = true;
boolean NO_PROMOTION = false;

final String TREENAME = "BplusTree";

final Color LineColor      = Color.black;
final Color HighlightColor = Color.orange;
final Color BackgroundColor   = Color.lightGray;

static final int BRANCH_SPACING	= (int) (Math.max( Bucket.BUCKET_HEIGHT, INode.INODE_HEIGHT) * 1.5);	// When a new level is added to the tree, move everyone down this much

boolean GetParms = true;


 int	root_X_pos		= 100;
 int	root_Y_pos		= 50;

// DEFINES

  Node root = null;			// root of tree

  Bucket first_Bucket = null;	// For sequential Access (Fully Implemented)

  Insert_T insert;			// thread to handle insertions

  int newKey;
  String newData;



  // Things having to do with appearance:

  Font f = new Font( "TimesRoman", Font.PLAIN, 14 );
  FontMetrics fm = getFontMetrics( f );

  TextField keyField;
  TextField dataField;
  Button    insertB;
  Button    repaintB;
  Button    clearB;
  Label     keyLabel;
  Label     dataLabel;

  Image     buffer_image;
  Graphics  buffer_graphics;
  Dimension buffer_size;

	///////////////////////////////////////////////////////


  /** 
   * Init the applet.  Just insert elements into the tree and
   * compute the tree's initial position.
   */

  public void init() {

   setLayout(null);
 
	root_X_pos		= bounds().x + ((bounds().width - Bucket.BUCKET_WIDTH - 25) / 2);
 	root_Y_pos		= 60;

	// Set the Applett Background

   setBackground(BackgroundColor);

   keyField = new TextField("", 5);
   dataField = new TextField("", 15);

   // Get the applet parameters/build tree

    if ( GetParms != false)
	{
		parse_parameters();
	}
   //  First, set up text fields and buttons

    //setLayout(new FlowLayout(FlowLayout.LEFT));

	insertB = new Button("Insert");
    add(insertB);
	insertB.setForeground(Color.yellow);

 	repaintB = new Button("Repaint");
	add(repaintB);
	repaintB.setForeground(Color.green);

	clearB = new Button("Clear");
	add(clearB);
 	clearB.setForeground(Color.orange);

    add( keyLabel = new Label("Key:", Label.RIGHT));
    add(keyField);
	keyLabel.setForeground(Color.red);

    add( dataLabel = new Label("Data:", Label.RIGHT));
    add(dataField);
	dataLabel.setForeground(Color.black);

 
	insertB.reshape( 20, 20, 50, 25 ); 
	keyLabel.reshape( 70, 20, 40, 25 ); 
	keyField.reshape( 110, 20, 40, 25 ); 
	dataLabel.reshape( 150, 20, 40, 25 ); 
	dataField.reshape( 190, 20, 50, 25 ); 
	repaintB.reshape( 20, 50, 50, 25 ); 
	clearB.reshape( bounds().x + bounds().width - 70, 20, 50, 25 ); 

	Layout_Tree();

   }


  /**
   * Upon starting, start any threads that were previously stopped
   */

  public void start() 
  {

    if (insert != null && insert.isAlive()) insert.resume();
  }


  /**
   * Upon stopping, stop all active threads
   */

  public void stop()
  {


    if (insert != null && insert.isAlive()) insert.suspend();

  }


  /**
   * Upon a `paint', just redraw the tree.
   */

  public void paint( Graphics g )
  {
	// Draw A nice border

		g.setColor(Color.black);
		g.drawRect( 0, 0, bounds().width - 1, bounds().height - 1);
		
		g.setColor(Color.blue);
		g.drawRect( 1, 1, bounds().width - 3, bounds().height - 3);
		g.drawRect( 2, 2, bounds().width - 5, bounds().height - 5);
		
		g.setColor(Color.black);
		g.drawRect( 3, 3, bounds().width - 7, bounds().height - 7);
 
  }


  /**
   * Use update() to do double buffering.
   */


  public synchronized void update( Graphics g ) 
  {
    if (buffer_image == null)
	{
      buffer_size     = size();
      buffer_image    = createImage( buffer_size.width, buffer_size.height );
      buffer_graphics = buffer_image.getGraphics();
      buffer_graphics.setFont( getFont() );
    }

    buffer_graphics.setColor( getBackground() );
    
	buffer_graphics.fillRect( 0, 0, buffer_size.width, buffer_size.height );

    paint( buffer_graphics );

    g.drawImage( buffer_image, 0, 0, null );

  }


  /**
   * Upon a mouseDown, colour the two nodes involved, but
   * don't rotate.
   */

  public boolean mouseDown( Event evt, int x, int y )
  {

/*

    // Ignore mouse if rotations are not allowed

    if (! allow_rotation)
      return false;

    // Ignore mouse if still rotating

    if (rotate_thread != null && rotate_thread.isAlive())
      return false;

    // Find node closest to mouse

    rotation_node = find_closest_node( root, x, y );

      
    // Highlight rotation node and its parent

    if (rotation_node != null) {
      rotation_node.highlight_node = true;
      rotation_node.parent.highlight_node = true;
      repaint();
    }

*/

    return true;
  }

  /**
   * Upon a mouseUp, rotate the two nodes.  This involves spawning
   * a thread that will slowly move the nodes.
   */

  public boolean mouseUp( Event evt, int x, int y ) 
  {
/*
    // Ignore mouse if still rotating

    if (rotate_thread != null && rotate_thread.isAlive())
      return false;

    // Ignore mouse no node selected for rotation

    if (rotation_node == null)
      return true;

    // Start rotating

    rotation_node.highlight_node = false;
    rotation_node.parent.highlight_node = false;

    rotate_thread = new rotate(this);
    rotate_thread.start();
*/
    return true;
  }


  /**
   * Handle an action from one of the UI components.
   */

  public boolean action( Event evt, Object arg ) 
  {
    if (arg.equals("Insert"))
	{
		try
		{
			newKey = Integer.parseInt( keyField.getText().trim() );
			newData = dataField.getText().trim();
		}
		catch (NumberFormatException e)
		{
			play(getCodeBase(), "audio/error.au");
			System.out.println( "ERROR in " + TREENAME + " applet: `Key' value must be an integer" );
			return true;
		}

		// Ignore  if still inserting

		if (insert != null && insert.isAlive())
		  return true;

		// Start rotating

		insert = new Insert_T (this);
		insert.start();
	    play(getCodeBase(), "audio/insert.au");
		return true;
    }
    else if (arg.equals("Clear"))
	{

		// Ignore  if still inserting

		if (insert != null && insert.isAlive())
		  return true;

		// Wipe Tree

	    play(getCodeBase(), "audio/clear.au");
		removeAll(); // Heh heh must implelemt children deleting themselves :)
		root = null;
		GetParms = false;

		init();
		
		GetParms = true;

		return true;
    }
    else if (arg.equals("Repaint"))
	{
		
	    play(getCodeBase(), "audio/repaint.au");
		Layout_Tree();
		return true;
	}
	else
	{
		System.out.println( "ERROR in BST applet: Unrecognized UI event" );
	}

    return true;
  }

  /**
   * Parse the parameters supplied with the applet.  These are listed at the
   * top of this file.
   */


  void parse_parameters() 
  {
 	int tempInt;
	String tempStr;
    String elements;
    String defaults;

    defaults = getParameter( "defaults" );
    if (defaults != null)
	{
      StringTokenizer t = new StringTokenizer( defaults, " \t\n\r," );
      if (t.hasMoreTokens()) keyField.setText( t.nextToken() );
      if (t.hasMoreTokens()) dataField.setText( t.nextToken() );
	}

   // Read elements - int key, string data

    elements = getParameter( "elements" );
    if (elements != null)
	{
      StringTokenizer t = new StringTokenizer( elements, " \t\n\r," );
      while (t.hasMoreTokens())
	  {
		try
		{
			tempInt = Integer.parseInt(t.nextToken());
		}
		catch (NumberFormatException e)
		{
			System.out.println( "ERROR in " + TREENAME + " applet: Invalid Element List: Key value must be an integer." );
			return;
		}

		if ( t.hasMoreTokens() )
		{
			Insert( tempInt, t.nextToken() );
		}
		else
		{
			System.out.println( "Error in " + TREENAME + " applet: Invalid Element List: expected pairs of integers and strings. Some elements could have been added before this." );
			return;
		}
	  }
    }


  }


  /**
   * Layout_Tree
   */

  void Layout_Tree() 
  {
	if (first_Bucket == null || root == null) return; // No tree!!

	Bucket curBucket = first_Bucket;

	int numBuckets = 0;

	int wanted = 0;

	while ( curBucket != null )
	{
		// Get # buckets
		numBuckets++;
		
		// Get Sum of buckets preferred widths
		wanted = wanted + curBucket.preferredSize().width;
		
		curBucket = curBucket.next_Bucket;
	}

	
	// Get screen width

	int screenwidth = bounds().width - 16;

	int leftedge = bounds().x;

	// Get space needed

	int needed = wanted + ( (numBuckets - 1)* Bucket.PREFERRED_BUCKET_SPACING );

	if ( numBuckets == 1) needed = needed + Bucket.PREFERRED_BUCKET_SPACING;


	// Determine slack space

	int slack;

	slack = screenwidth - needed;

	if ( slack < 0 )
	{
		// We don't have room for adequate bucket spacing
		// so shrink the bucket gap
		// (this will cause buckets to overlap on an insertion)
		
			// See if we could shrink the bucket spacing to make
			// all the buckets fit
			// if not we have to chop off the sides of the tree
		
		Bucket.BUCKET_SPACING = Bucket.PREFERRED_BUCKET_SPACING - (Math.abs(slack) / numBuckets);

		if (  Bucket.BUCKET_SPACING < Bucket.MIN_BUCKET_SPACING )
		{
			// there is not enough pixels inbetween buckets for useful display

			Bucket.BUCKET_SPACING = Bucket.MIN_BUCKET_SPACING;
		
			// So instead of really squishing buckets, Chop sides of tree
			// by moving the left edge of the tree over
			
			int nowneed = wanted + ( numBuckets * Bucket.BUCKET_SPACING );

			leftedge = (bounds().x - ( (nowneed - screenwidth) / 2)) + Bucket.MIN_BUCKET_SPACING;

			slack = 0;
		}
		else	// < MAX but > MIN  We at least have some slack space available
		{
			needed = wanted + ( (numBuckets - 1) * Bucket.BUCKET_SPACING );
			
			// Determine NEW slack space

			slack = screenwidth - needed;
		}	
	} // Slack < 0		
	else
	{
		// We have lots of space, and all butkets may be layed out at the preferred sizes

		Bucket.BUCKET_SPACING = Bucket.PREFERRED_BUCKET_SPACING;
	}

	// Now the left edge, and slack space needed are known

	leftedge = leftedge + ( slack / 2 ) + 25;

	root.Reshape_Tree( leftedge );

  }


  /**
   * Insert a Key and it's Data into the Tree
   */

  public void Insert( int k, String d ) 
  {

	NodeInfo promo_d = new NodeInfo();	// Create a class to hold return parameters


	if ( root == null )	// We are a lame kinda tree!
	{
		// Create a Bucket and put our Data into it

		first_Bucket = new Bucket();
		root = first_Bucket;


		// Set it's Position & Attributes!

		add ( first_Bucket );
		
		//first_Bucket.reshape( root_X_pos, root_Y_pos, Bucket.BUCKET_WIDTH, Bucket.BUCKET_HEIGHT );
		first_Bucket.move( root_X_pos - 25, root_Y_pos);

			if ( isShowing() )
			{
				try
				{
					Thread.sleep( 500 );
				}
				catch (InterruptedException e)
				{}
			}
		Layout_Tree(); // Resize, Reposition, and Repaint
	}


	// Now we are sure we have a root, tell it to insert the data

	if ( root.Insert( k, d, promo_d ) == NO_PROMOTION )
	{
		// The data fit into the tree just fine, no hassle :)
		
			if ( isShowing() )
			{
				try
				{
					Thread.sleep( 3000 );
				}
				catch (InterruptedException e)
				{}
			}

		//Layout_Tree(); // Resize, Reposition, and Repaint

		return;

	}
	else	// Damn, the insert caused our root to split
	{
			// Root split, so we need to make a new root and

			// Put the given key into it, and then
			// Make it point to our old root on the left, and the 
			// newly returned pointer on the right
		
		INode rootINode = new INode();

		// Setup the new node


		rootINode.child[0]	= root;		// Set left Child
		rootINode.Keys[0]	= promo_d.key;	// Set Key
		rootINode.child[1]	= promo_d.rc;	// Set right Child

		rootINode.nKeys = 1;


		// Set it's Position & Attributes!

		add ( rootINode );
				

		// Draw root node in exact position it was in the inode

		int Keypos = rootINode.child[0].bounds().x + ((Ndiv2 ) * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH ));

		
		rootINode.movekids = false;
		rootINode.reshape( Keypos, rootINode.child[0].bounds().y, INode.NEW_INODE_WIDTH, INode.INODE_HEIGHT);
		rootINode.resize(); // Paint it	

		root = rootINode;


		// move kids down


		// Move old Root out of the way

		rootINode.child[0].dmove_A( 0, BRANCH_SPACING ); // x,y 

		// Move New Right Child Down one

		rootINode.child[1].dmove_A( 0, BRANCH_SPACING ); // x,y 


		// move root to centre
		
		rootINode.speed = INode.SPLIT_SPEED;

			// We will just move the old tree down a ways, then
			// Draw our new root directly between the old root and the new 
			// node's rightmost edge.

		int left  = rootINode.child[0].bounds().x;
		int right = rootINode.child[1].bounds().x + rootINode.child[1].bounds().width;

		root_X_pos = left + ((right - left - INode.NEW_INODE_WIDTH) / 2);
		
		rootINode.move_A( root_X_pos, root_Y_pos );
		
		rootINode.speed = INode.DEF_SPEED;
		rootINode.movekids = true;

		
		// Give user some time to see what happened before we jump the whole tree
		// to a new position

			if ( isShowing() )
			{
				try
				{
					Thread.sleep( 3000 );
				}
				catch (InterruptedException e)
				{}
			}

		//Layout_Tree(); // Resize, Reposition, and Repaint


	} // Promotion
  
  } // Insert

} // BplusTree