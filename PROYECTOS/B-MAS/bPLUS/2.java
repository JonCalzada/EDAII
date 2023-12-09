import java.awt.*;


/** A BplusTree's Leaf node */


class Bucket extends Node
{
final Color BucketColor      = Color.gray;

static int	BUCKET_HEIGHT	= 45;
static final int	BUCKET_WIDTH	= 40;	// Size of an individual bucket element ie. new bucket

static final int BUCKET_SPACE = 5;	// Not much since we are overextended and will shrink in a sec

static int	NEW_BUCKET_WIDTH	= BUCKET_WIDTH * (BplusTree.Ndiv2+1);	// Size of an individual bucket element ie. new bucket

static final int PREFERRED_BUCKET_SPACING = BUCKET_WIDTH + BUCKET_SPACE + BUCKET_SPACE;
static final int MIN_BUCKET_SPACING = 5;

static int BUCKET_SPACING = PREFERRED_BUCKET_SPACING;

//	= (BUCKET_WIDTH * (N + 1)) + (BUCKET_SPACE * 2);// This is the preferred amt of space, regardless of existing widths


  Bucket next_Bucket = null;	// For sequential Access (Fully Implemented)
  
  int[]		Keys = new int[BplusTree.N + 1];
  String[]	myData = new String[BplusTree.N + 1];
  
  int nKeys = 0;

final Font keyFont = new Font( "TimesRoman", Font.BOLD, 18 );
final FontMetrics keyfm = getFontMetrics( keyFont );

final Font dataFont = new Font( "TimesRoman", Font.PLAIN, 14 );
final FontMetrics datafm = getFontMetrics( dataFont );
  
  //setBackground(BackgroundColor);

	public Bucket()
	{
		setBackground(BucketColor);
	}


	//////////////////////////////////////////////////////////
	//
	// paint
	//

  public void paint( Graphics g )
  {
  int j = 0;
  int i;

    // Draw the Bucket Keys

		//g.drawRect( i * BUCKET_WIDTH, 0, BUCKET_WIDTH , BUCKET_HEIGHT );


	for(i=0; i < nKeys; i++)
	{
		g.setColor(LineColor);

		g.drawRect( i * BUCKET_WIDTH, 0, BUCKET_WIDTH - 1, BUCKET_HEIGHT - 1);

		g.setColor( Key.KeyColor );
		g.setFont( keyFont );
		g.drawString( String.valueOf( Keys[i] ), i * BUCKET_WIDTH +5, keyfm.getHeight());


		g.setColor( Data.DataColor );
		g.setFont( dataFont );
		g.drawString( myData[i], i * BUCKET_WIDTH +5, keyfm.getHeight() + datafm.getHeight() );
			
	}
	
  }

/**	/////////////////////////////////////
	//
	// dmove_A - move a certain distance - in an animated manner though
	//
*/

	/////////////////////////////////////
	//
	// move
	//

  public void dmove( int dx, int dy )
  {
 
    Rectangle bounds = bounds();
	
	reshape( bounds.x + dx, bounds.y + dy, bounds.width , bounds.height );

	repaint();

  }

	//////////////////////////////////////////////////////////
	//
	// resize
	//

  public void resize()
  {
  int j = 0;

    // Draw any stored edges

	reshape( bounds().x, bounds().y, BUCKET_WIDTH * nKeys, BUCKET_HEIGHT );

	repaint();
  }

	//////////////////////////////////////////////////////////
	//
	// preferredSize
	//

  public Dimension preferredSize()
  {

	Dimension d = new Dimension();

	d.width = ( BUCKET_WIDTH * nKeys ) + BUCKET_SPACE;

	d.height = BUCKET_HEIGHT;

	return (d);
  }


/**	//////////////////////////////////////////////////////////
	//
	// We are always a leaf, so just move to the left edge given
	// and return our right edge
	//
	// This of course constructs a tree shape.
*/

	public int Reshape_Tree( int left) // Returns right edge
	{
			// Basic Step
			
			// Just position ourself
	
		move( left, bounds().y);
						
		return ( left +  bounds().width +  BUCKET_SPACING);
			
	}
	/////////////////////////////////////////////////////////
	//
	// insert
	//

/* performs a search, if key found, just pretend we did insert and result NO_PROMOTION

   fits key into bucket where appropriate

   if not overstuffed, return NO_PROMOTION

   if overstuffed, a new bucket is created and we put the right half into it,
   and return PROMOTION with the Key of the far left of the new bucket, and the new bucket 

*/

	// k	- key to search for
	// d	- data record to insert
	// promo_key	- the new key to be inserted (unless NO_PROMOTION)
	// promo_rc		- pointer to be inserted as the right child of the key

	boolean	Insert( int k, String d, NodeInfo promo_d )
	{
		
	int i,j;		// loop counters, etc 


		if ( nKeys > 0 )
		{
			// cycle thru our keys to see if the key is smaller

			for(i=0; i < nKeys; i++)
			{
				if (k < Keys[i])
				{
						// it's smaller than one of our keys, insert here
					break;
						// else Key is larger or equal to our largest key
						// gets to end of for loop, increments i to nKeys
				}
				else if ( k == Keys[i] ) // Check for Dupe Key
				{
						//It's a Dupe, We don't want dupe keys
				
						//But we will happily update it's data value
					
					myData[i] = d;

					repaint();
					
					return(NO_PROMOTION);

				}
			}

		}
		else	// We have NO keys
		{
			i = 0; // Insert Key at position Zero
		}
		
			// Key is not in the list, insert it at position i
			// Shove over keys from pos i on 		

		for (j = nKeys; j > i; j--)
		{
			 // make a space, shift keys up one
			
			Keys[j] = Keys[j - 1];			
			myData[j] = myData[j - 1];
		}

		Keys[i] = k;
		myData[i] = d;
		nKeys++;


			// We are now LONGER

			// Ideally we animate ourselves stretching and inserting the
			// new record :) :)

			resize();

			if ( isShowing() )
			{
				try
				{
				Thread.sleep( 500 );	// Pause to maintain speed and give time to redraw
				}
				catch (InterruptedException e)
				{}
			}


		// now the fun part

		// Did we overstuff ourselves by Adding that Key?
	
		if ( nKeys <= BplusTree.N )	// Are we too big?
		{
			return(NO_PROMOTION); // Not Overstuffed
		}
		else
		{
			// We're Full Up, so Split in two

			// middle key Ndiv2 goes up to parent
			// The new bucket goes up as the key's right child

			// Ideally we move the key object up, out of us, sitting slightly above the top
			// and then shrink

			promo_d.key = Keys[BplusTree.Ndiv2];

			// Make a new bucket, and stuff it with Keys & Data Ndiv2 on

			Bucket newnode = new Bucket();
			
			// We keep track of the next bucket, so it is possible
			// To rapidly cruise on directly across the Buckets

			newnode.next_Bucket = next_Bucket;

			next_Bucket = newnode;
			
			promo_d.rc = newnode;
			
			for(j = BplusTree.Ndiv2, k = 0; j <= BplusTree.N; j++, k++)
			{
				 // stuff the upper half keys into the new node
				newnode.Keys[k] = Keys[j];			
				newnode.myData[k] = myData[j];
			}
			
			newnode.nKeys = k;


			// We cut off our array BEFORE Key Ndiv2

			nKeys = BplusTree.Ndiv2;	// That 0-1 base thingy goes on here


				// Tell our parent to insert the new node in it's container

			getParent().add ( newnode );


			// SET THE Physical LOCATION OF THE NEW BUCKET!!!
				
			// To exactly where the records would have been if still in us
			
			int Recordpos = bounds().x + (nKeys * BUCKET_WIDTH);

			newnode.move( Recordpos, bounds().y);

			newnode.resize(); // Get the node to autosize based on the # of keys it has
							  // This will cause a repaint, but that's okayt

			resize(); // Since we are now shorter
					  // repaint ourselves, this may leave our new node
					  // erased briefly because we covered it
			
					  // Animate a separation
					  // and we want a BUCKET_SPACE inbetween new and old nodes

			newnode.speed = SPLIT_SPEED;
			newnode.dmove_A( BUCKET_SPACE, 0); 
			newnode.speed = DEF_SPEED;

			if ( isShowing() )
			{
				try
				{
				Thread.sleep( 500 );	// Pause to maintain speed and give time to redraw
				}
				catch (InterruptedException e)
				{}
			}

			// Tell our parent to insert the new Key & Bucket

			return(PROMOTION);
		
		} // Too Full?

	// Unreachable Code

	// ASSERT( FALSE )
	 
	} /* insert. **********************/
}