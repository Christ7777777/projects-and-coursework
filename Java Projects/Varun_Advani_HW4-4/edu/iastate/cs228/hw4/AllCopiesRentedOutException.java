package edu.iastate.cs228.hw4;

/**
 * 
 * @author Yan-Bin Jia
 * 
 */


/**
 * Exception is thrown if a requested movie has no video copy left in the store.
 *
 */
public class AllCopiesRentedOutException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AllCopiesRentedOutException()
	{
		super();
	}
	  
	public AllCopiesRentedOutException(String msg)
	{
	    super(msg);
	}

}
