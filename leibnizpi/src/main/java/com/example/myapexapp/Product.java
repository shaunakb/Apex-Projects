
package com.example.myapexapp;

import java.util.ArrayList;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.annotation.OutputPortFieldAnnotation;
import com.datatorrent.common.util.BaseOperator;

public class Product extends BaseOperator
{
  /**
   * Array to store numerator inputs during window.
   */
  private ArrayList<Double> n1 = new ArrayList<Double>();

  /**
   * Array to store denominator input during window.
   */
  private ArrayList<Double> n2 = new ArrayList<Double>();

  /**
   * Number of pair processed in current window.
   */
  private int index = 0;

	public final transient DefaultOutputPort<Double> output = new DefaultOutputPort<Double>();

  public final transient DefaultInputPort<Double> num1 = new DefaultInputPort<Double>()
  {
    @Override
    public void process(Double tuple)
    {
      n1.add(tuple);
      if (n2.size() > index) {
        int loc = n2.size();
        if (loc > n1.size()) {
          loc = n1.size();
        }
        output.emit(n1.get(loc - 1) * n2.get(loc - 1));
        index++;
      }
    }
  };

  public final transient DefaultInputPort<Double> num2 = new DefaultInputPort<Double>()
  {
    @Override
    public void process(Double tuple)
    {
      n2.add(tuple);
      if (n1.size() > index) {
        int loc = n2.size();
        if (loc > n1.size()) {
          loc = n1.size();
        }
        output.emit(n1.get(loc - 1) * n2.get(loc - 1));
        index++;
      }
    }
  };

  @Override
  public void endWindow()
  {
    n1.clear();
    n2.clear();
    index = 0;
  }
}
