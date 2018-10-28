/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.lib.math.RunningAverage;
import com.datatorrent.lib.io.ConsoleOutputOperator;

@ApplicationAnnotation(name="PiApproximator")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {

    RandomNumberGenerator randomGenerator = dag.addOperator("randomGenerator", new RandomNumberGenerator());
		PiCalculator calculator = dag.addOperator("calculator", new PiCalculator());
		calculator.setNumTuples(400);
	
    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("randomData", randomGenerator.output, calculator.input);
		dag.addStream("calculated", calculator.output, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
