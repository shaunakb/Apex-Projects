/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This is a simple operator that reads a file.
 */
public class FileReader extends BaseOperator implements InputOperator
{
  private int numTuples = 100;
  private transient int count = 0;
	private Scanner scanner = null;


  public final transient DefaultOutputPort<String> out = new DefaultOutputPort<String>();

  @Override
  public void beginWindow(long windowId)
  {
    count = 0;
		try
		{
			scanner = new Scanner(new File("input/input.txt"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
  }

  @Override
  public void emitTuples()
  {
		if (count++ < numTuples && scanner.hasNext()) 
		{
     	out.emit(scanner.nextLine());
    }
  }

  public int getNumTuples()
  {
    return numTuples;
  }

  /**
   * Sets the number of tuples to be emitted every window.
   * @param numTuples number of tuples
   */
  public void setNumTuples(int numTuples)
  {
    this.numTuples = numTuples;
  }
}