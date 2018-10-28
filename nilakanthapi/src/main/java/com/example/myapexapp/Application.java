/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.lib.io.ConsoleOutputOperator;
import com.datatorrent.lib.math.Sum;

@ApplicationAnnotation(name="NilakanthaPi")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {

    Count count = dag.addOperator("count", new Count());
		Divisor divisor = dag.addOperator("divisor", new Divisor());
		Sum sum = dag.addOperator("sum", new Sum());
		sum.setCumulative(true);
		AddBase addBase = dag.addOperator("addBase", new AddBase());
	
    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());
		
		dag.addStream("countData", count.output, divisor.input);
		dag.addStream("divisorData", divisor.output, sum.data);
		dag.addStream("sumData", sum.sumDouble, addBase.input);
		dag.addStream("piData", addBase.output, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
