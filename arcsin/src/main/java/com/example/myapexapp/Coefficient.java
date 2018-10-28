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
public class Coefficient extends BaseOperator
{
  private transient int count = 0;
	private double last = 1;
	private double current = 0;
	private double multiplyFactor = 1.0;

	public double getMultiplyFactor()
	{
		return multiplyFactor;
	}

	public void setMultiplyFactor(double multiplyFactor)
	{
		this.multiplyFactor = multiplyFactor;
	}

  public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Double> input = new DefaultInputPort<Double>()
	{
		@Override
		public void process(Double t)
		{
			count++;
			output.emit(multiplyFactor * last);
			current = last * (2.0 * count - 1) / (2 * count);
			last = current;
		}
	};

}
