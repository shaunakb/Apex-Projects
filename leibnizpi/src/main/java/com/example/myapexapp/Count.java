
package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

public class Count extends BaseOperator implements InputOperator
{
	private int count = 0;
	
  public final transient DefaultOutputPort<Integer> output = new DefaultOutputPort<Integer>();
	//public final transient DefaultOutputPort<Integer> output2 = new DefaultOutputPort<Integer>();

  @Override
  public void emitTuples()
  {
    output.emit(++count);
		//output2.emit(count);
  }
}
