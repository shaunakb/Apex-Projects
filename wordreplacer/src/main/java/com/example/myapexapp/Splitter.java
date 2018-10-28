/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

/**
 * This is a simple operator that emits random number.
 */
public class Splitter extends BaseOperator
{
	private String base = "";
	private String target = "";

	public String getBase()
	{
		return base;
	}

	public String getTarget()
	{
		return target;
	}

	public void setBase(String s)
	{
		base = s;
	}

	public void setTarget(String s)
	{
		target = s;
	}

  public final transient DefaultOutputPort<String[]> output = new DefaultOutputPort<String[]>();

	public final transient DefaultInputPort<String> input = new DefaultInputPort<String>()
	{
		@Override
		public void process(String s)
		{
			output.emit((s + " ").split(target));
		}
	};

}
