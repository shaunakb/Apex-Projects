package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

public class Divisor extends BaseOperator
{
  public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Integer> input = new DefaultInputPort<Integer>()
	{
		@Override
		public void process(Integer t)
		{
			output.emit(4.0 * (t.intValue() % 2 * 2 - 1) / (t.intValue() * 2 - 1));
		}
	};

}
