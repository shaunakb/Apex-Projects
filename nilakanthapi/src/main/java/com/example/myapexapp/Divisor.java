package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.common.util.BaseOperator;

public class Divisor extends BaseOperator
{
  public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Integer> input = new DefaultInputPort<Integer>()
	{
		@Override
		public void process(Integer t)
		{
			int c = t.intValue();
			output.emit(4.0 * (c % 2 * 2 - 1) / (2*c) / (2*c+1) / (2*c+2));
		}
	};

}
