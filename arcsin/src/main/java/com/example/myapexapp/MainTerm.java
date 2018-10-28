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
public class MainTerm extends BaseOperator
{
  private transient int x = 1;

  public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Double> input = new DefaultInputPort<Double>()
	{
		@Override
		public void process(Double t)
		{
			output.emit(Math.pow(t, x) / x);
			x += 2;
		}
	};

}
