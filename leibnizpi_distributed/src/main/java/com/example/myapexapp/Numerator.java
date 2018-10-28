
package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

public class Numerator extends BaseOperator implements InputOperator
{
	private double num = 4;
	
  public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  @Override
  public void emitTuples()
  {
		output.emit(num);
  }

}
