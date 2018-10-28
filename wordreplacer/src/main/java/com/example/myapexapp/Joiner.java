/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

/**
 * Emits a word created from a random number of random characters.
 */
public class Joiner extends BaseOperator
{
	private String replacement = ".";

	public String getReplacement()
	{
		return replacement;
	}

	public void setReplacement(String i)
	{
		replacement = i;
	}

	private String join(String[] array, String connector)
	{
		String result = "";
		for (int i = 0; i < array.length - 1; i++)
		{
			result += array[i] + connector;
		}
		result += array[array.length - 1];
		if (result.charAt(result.length() - 1) == ' ')
		{
			return result.substring(0, result.length() - 1);
		}
		return result;
	}

  public final transient DefaultOutputPort<String> output = new DefaultOutputPort<String>();

	public final transient DefaultInputPort<String[]> input = new DefaultInputPort<String[]>()
	{
		@Override
		public void process(String[] s)
		{
			try 
			{
				Thread.sleep(3);
				output.emit(join(s, replacement));
			}
			catch (InterruptedException ex)
			{
				//do nothing
			}
		}
	};

}
