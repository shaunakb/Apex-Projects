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

@ApplicationAnnotation(name="LeibnizPi")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {

    Count count = dag.addOperator("count", new Count());
		Divisor divisor = dag.addOperator("termGenerator", new Divisor());
		//divisor.setUnifier(new 
		Division division = dag.addOperator("division", new Division());
		Sum sum = dag.addOperator("sum", new Sum());
		sum.setCumulative(true);
	
    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());
		
		dag.addStream("countData", count.output, divisor.input);
		dag.addStream("divisorData", divisor.output, division.input);
		dag.addStream("termData", division.output, sum.data);
		dag.addStream("sumData", sum.sumDouble, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
