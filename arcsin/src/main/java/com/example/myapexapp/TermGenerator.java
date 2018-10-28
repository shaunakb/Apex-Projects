/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

/**
 * Starts process of getting term of Maclaurin Series
 */
public class TermGenerator extends BaseOperator implements InputOperator
{
	private int pause = 0;
	private double input = 0;
  private transient int count = 0;

  public final transient DefaultOutputPort<Double> out1 = new DefaultOutputPort<Double>();
	public final transient DefaultOutputPort<Double> out2 = new DefaultOutputPort<Double>();

  @Override
  public void beginWindow(long windowId)
  {
    count = 0;
  }

  @Override
  public void emitTuples()
  {
    if (count++ < numTuples) {
			try
			{
				Thread.sleep(pause);
      	out1.emit(input);
				out2.emit(input);
			} 
			catch (InterruptedException ex)
			{
			}
		}
  }

  public int getNumTuples()
  {
    return numTuples;
  }

	public int getPause()
	{
		return pause;
	}

	public double getInput()
	{
		return input;
	}

	public void setPause(int pause)
	{
		this.pause = pause;
	}

	public void setInput(double input)
	{
		this.input = input;
	}
}
