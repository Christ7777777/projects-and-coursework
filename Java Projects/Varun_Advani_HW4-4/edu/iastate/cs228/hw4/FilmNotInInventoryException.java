package edu.iastate.cs228.hw4;

/**
 * 
 * @author Yan-Bin Jia
 * 
 */

/**
 * Expression thrown for a video not in the inventory.
 */
public class FilmNotInInventoryException extends Exception
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public FilmNotInInventoryException()
  {
    super();
  }
  
  public FilmNotInInventoryException(String msg)
  {
    super(msg);
  }
}

