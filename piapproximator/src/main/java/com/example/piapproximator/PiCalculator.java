/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;
import java.util.ArrayList;

/**
 * This is a simple operator that emits random number.
 */
public class PiCalculator extends BaseOperator
{
  private int numTuples = 400;
  private transient int count = 0;
	private int sum = 0;

  public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Double> input = new DefaultInputPort<Double>()
	{
		@Override
		public void process(Double t)
		{
			count++;
			if (t <= 1)
			{
				sum++;
			}
			if(count == numTuples)
			{
				output.emit((4.0 * sum) / numTuples);
				count = 0;
				sum = 0;
			}
		}
	};

	public int getNumTuples()
  {
    return numTuples;
  }

  public void setNumTuples(int numTuples)
  {
    this.numTuples = numTuples;
  }

}
