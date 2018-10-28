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
public class Combiner extends BaseOperator
{
  private final int numTuples = 2;
  private transient int count = 0;
	private ArrayList<Double> coordinates = new ArrayList<Double>();

  public final transient DefaultOutputPort<Double> out = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Double> in = new DefaultInputPort<Double>()
	{
		@Override
		public void process(Double t)
		{
			coordinates.add(t);
			if(coordinates.size() == numTuples)
			{
				double x = coordinates.get(0);
				double y = coordinates.get(1);
				out.emit(x * x + y * y);
				coordinates.clear();
			}
		}
	};
}
