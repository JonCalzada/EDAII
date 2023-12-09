import java.awt.*;

/** A BplusTree Inner Node (INode) */


class INode extends Node
{
final Color INodeColor      = Color.cyan;

static int	INODE_SPACE		= 10;	// When a new node is created, move over this much
static int	INODE_SPACING	= ((Key.KEY_WIDTH + Pointer.POINTER_WIDTH ) * (BplusTree.N + 1)) + (INODE_SPACE * 2);	// When the tree redraws to respace eveything
static int	INODE_HEIGHT	= 30;

// This is not definable! final int	INODE_WIDTH		= KEY_WIDTH + 2 * POINTER_WIDTH;

static int	NEW_INODE_WIDTH	= Key.KEY_WIDTH + Pointer.POINTER_WIDTH + Pointer.POINTER_WIDTH;

//((Key.KEY_WIDTH + Pointer.POINTER_WIDTH) * (BplusTree.Ndiv2+1)) + Pointer.POINTER_WIDTH;

boolean movekids = true;

  Node[] child = new Node[ BplusTree.N + 2 ];		// N to N/2 children
  
  int[] Keys = new int[BplusTree.N + 1];
  
  int nKeys = 0;

final Font keyFont= new Font( "TimesRoman", Font.BOLD, 18 );
final FontMetrics keyfm = getFontMetrics( keyFont );



/** /////////////////////////////////////
	//
	// paint
	//
*/

  public void paint( Graphics g )
  {
  int i;

    // Draw the INode

	for(i=0; i < nKeys; i++)
	{
		g.setColor( Pointer.PointerColor );
    
		g.fillRect( i * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH) , 0, Pointer.POINTER_WIDTH - 1, INODE_HEIGHT - 1);

		g.setColor(LineColor);

		g.drawRect( i * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH) , 0, Pointer.POINTER_WIDTH - 1, INODE_HEIGHT - 1);



		g.setColor( INodeColor );
    
		g.fillRect( i * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH) + Pointer.POINTER_WIDTH, 0, Key.KEY_WIDTH - 1, INODE_HEIGHT - 1);

		g.setColor(LineColor);

		g.drawRect( i * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH) + Pointer.POINTER_WIDTH, 0, Key.KEY_WIDTH - 1, INODE_HEIGHT - 1);


		g.setColor( Key.KeyColor);
		g.setFont( keyFont );
		g.drawString( String.valueOf( Keys[i] ),5 + (i * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH)) + Pointer.POINTER_WIDTH, keyfm.getHeight());

	}

	g.setColor( Pointer.PointerColor );
    
	g.fillRect( i * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH) , 0, Pointer.POINTER_WIDTH - 1, INODE_HEIGHT - 1);

	g.setColor(LineColor);

	g.drawRect( i * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH) , 0, Pointer.POINTER_WIDTH - 1, INODE_HEIGHT - 1);
  
  }

/////////////////////////////////////

	//
	// dmove_A
	//

  public void dmove_A( int dx, int dy )
  {
  int i;
 
	if ( movekids == true )
	{

		// Tell Children to Move then call superclass to move us

		if ( nKeys > 0 )
		{
			for(i=0; i <= nKeys; i++)
			{
				if ( child[i] != null )
				{
					child[i].dmove_A( dx, dy);
				}
				else
				{
					// This is 'likely' a bad situation :)
				}
			}
		}
	}
    super.dmove_A( dx, dy);

  }

/////////////////////////////////////
	//
	// dmove
	//

  public void dmove( int dx, int dy )
  {
  int i;

 	if ( movekids == true )
	{

		// Tell Children to Move

		for(i=0; i <= nKeys; i++)
		{
			if ( child[i] != null )
			{
				child[i].dmove( dx, dy);
			}
			else
			{
				// This is 'likely' a bad situation :)
			}
		
		}
	}

	move( bounds().x + dx, bounds().y + dy );

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

	reshape( bounds().x, bounds().y, ((Key.KEY_WIDTH + Pointer.POINTER_WIDTH) * nKeys) + Pointer.POINTER_WIDTH, INODE_HEIGHT );
  
	repaint();
  }

	//////////////////////////////////////////////////////////
	//
	// preferredSize
	//

  public Dimension preferredSize()
  {

	Dimension d = new Dimension();

	d.width =  ((Key.KEY_WIDTH + Pointer.POINTER_WIDTH) * (nKeys+1)) + Pointer.POINTER_WIDTH;

	d.height = INODE_HEIGHT;

	return (d);

  }


/**	//////////////////////////////////////////////////////////
	//
	// Move our bottom-leftmost child to the left edge, and all bottommost
	// children against the right edge returned.
	// and then centre ourselves
	// between the immediate left child's leftmost edge, and the
	// immediate right child's rightmost edge.
	//
	// If we don't have children, position ourselves at the left edge
	//
	// This of course constructs a tree shape.
*/

	public int Reshape_Tree( int left) // Returns right edge
	{
	int myleft; // my eventual left edge
	int curright;

		if ( nKeys < 1 )
		{
			// We are an INode, and as such, should NEVER, EVER
			// be a leaf node. But, in the interest of safety and odd implementation
			// vs 'correct' code, I'll let it lie.

			// Basic Step
			
			myleft = left;
			curright = myleft + bounds().width;
		}
		else
		{
				// Recursive Step
			
			curright = left;// curright is technicaly the right edge of
								// the last object
			
			int nChild = 1 + nKeys;

			for(int j = 0; j < nChild; j++)
			{
				curright = child[j].Reshape_Tree( curright );
			}
			
			// Centre ourself between our left child's left edge and our right child's right edge


			int myright = child[nKeys].bounds().x + child[nKeys].bounds().width;

			int cleft = child[0].bounds().x;

			int centrepos = cleft + ( (myright -  cleft) / 2);

			myleft = centrepos - ((bounds().width) / 2 );

		}
		
			// Just position ourself
			// Bottleneck code here in case I want to change to animate this

		move( myleft, bounds().y);					
		return ( curright );
			
	}
	
	
/**	//////////////////////////////////////////////////////////
	//
	// Insert
	//
	// performs a search on the way into the recursion and fixes the tree (if
	// necessary) on the way out
	//
	// if key fits in bucket, then returns NO_PROMOTION
	//
	// if key does not fit and a new bucket is created, then returns PROMOTION
	//
	// insert smallest (left) key of new bucket, plus bucket address, into parent (us)
	// if we (the parent) are full, split, but middle key goes up (promotes) to parent
	// instead of either half. (Repeats until parent need not split)
	//
	// k	- key to search for
	// d	- data record to insert
	// Promo_d contains:
	// key		- the new key to be inserted (unless NO_PROMOTION)
	// rc		- pointer to be inserted as the right child of the key
*/

	boolean	Insert( int k, String d, NodeInfo promo_d )
	{
		
	boolean retval;   /* Value returned from recursive call to 
						  BTinsert. (PROMOTION or NO_PROMOTION)*/
	int i,j;		// loop counters, etc 


		// cycle thru our keys to see if the key is smaller

		for(i=0; i < nKeys; i++)
		{
			if (k < Keys[i])
			{
					// it's smaller than one of our keys, insert here
				break;
					// else Key is larger or equal to our largest key
					// gets to end of for loop, increments i to nKeys
					// i = nKeys happens to be the child we'd want
			}
		}
		
			// i is the CHILD index

		retval = child[i].Insert( k, d, promo_d ); 
			
		
		// now the fun part

		// if the subnode had to promote a key, we must then deal with it
		
	/*
	 insert promoted key, plus address
  
	 if full 
		split, but promote middle key to parent instead of either half. 
    
	*/


		if (retval == PROMOTION)
		{
			// Okay, we have to add the damn key

			// Find out where to insert the key

			// i is the CHILD index
			// the new key is to be inserted at KEY position i
			// the key's child inserts ay CHILD position i+1
			// Child i is left alone
			// child i+i and key i are shifted up to make space
			

			for(j = nKeys; j > i; j--)
			{
				 // make a space, shift keys up one
				
				Keys[j] = Keys[j - 1];			
				child[j+1] = child[j];
			}

			Keys[i] = promo_d.key;
			child[i + 1] = promo_d.rc;
			nKeys++;


			// We are now LONGER

			// Ideally we animate ourselves stretching and inserting the
			// new key :) :)

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

			if ( nKeys <= BplusTree.N )	// Are we too big?
			{
				return(NO_PROMOTION); // Not Overstuffed
			}
			else
			{
				// We're Full Up, so Split in two

				// middle key Ndiv2 goes up to parent
				// The new node goes up as the key's right child

				promo_d.key = Keys[BplusTree.Ndiv2];

				// Make a new node, and stuff it with Key Ndiv2+1 on

				INode newnode = new INode();

				promo_d.rc = newnode;

				
				for(j = BplusTree.Ndiv2 + 1, k = 0; j <= BplusTree.N; j++, k++)
				{
					 // stuff the upper half keys into the new node
					newnode.Keys[k] = Keys[j];			
					newnode.child[k] = child[j];
				}
				
				newnode.child[k] = child[j];
				newnode.nKeys = k;


				// We cut off our array BEFORE Key Ndiv2

				nKeys = BplusTree.Ndiv2+1;	// That 0-1 base thingy goes on here				

				// Tell our parent to insert the new Inode to it's container

				getParent().add ( newnode );		

				// SET THE Physical LOCATION OF THE NEW Inode!!!
				
				// To exactly where the keys would have been if still in us
				// taking into account that the new inode has a pointer on the left

				int Keypos = bounds().x + ((BplusTree.Ndiv2 + 1) * ( Pointer.POINTER_WIDTH + Key.KEY_WIDTH ));

				newnode.move( Keypos, bounds().y);

				newnode.resize(); // Get the node to autosize based on the # of keys it has
								  // This will cause a repaint, but that's okayt

				resize(); // Since we are now shorter
						  // repaint ourselves, this may leave our new node
						  // erased briefly because we covered it
				
						  // Animate an amoeba split
						  // The two node's pointers overlap
						  // and we want a INODE_SPACE inbetween new and old nodes


				newnode.speed = SPLIT_SPEED;
				newnode.movekids = false;
				newnode.dmove_A( Pointer.POINTER_WIDTH + INODE_SPACE, 0); 
				newnode.movekids = true;
				newnode.speed = DEF_SPEED;

								// We cut off our array BEFORE Key Ndiv2


				if ( isShowing() )
				{
					try
					{
					Thread.sleep( 500 );	// Pause to maintain speed and give time to redraw
					}
					catch (InterruptedException e)
					{}
				}
			
				nKeys = BplusTree.Ndiv2;	// That 0-1 base thingy goes on here				

				resize();

				return(PROMOTION);
			
			} // Too Full?
		}
		else	// Not Promoted
		{
				// Just Quit
				return ( NO_PROMOTION );

		} // Promoting?	

	// Unreachable Code

	// ASSERT( FALSE )
	 
	} // Insert

} // INode