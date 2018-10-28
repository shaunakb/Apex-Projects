package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

public class Division extends BaseOperator
{
  public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Double> input = new DefaultInputPort<Double>()
	{
		@Override
		public void process(Double t)
		{
			output.emit(4.0 / t);
		}
	};

}
