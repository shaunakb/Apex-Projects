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

@ApplicationAnnotation(name="MyFirstApplication")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Sample DAG with 2 operators
    // Replace this code with the DAG you want to build

	RandomNameGenerator randNameGenerator = dag.addOperator("randNameGenerator", RandomNameGenerator.class);
	RandomNumberGenerator randNumGenerator = dag.addOperator("randNumGenerator", RandomNumberGenerator.class);
	randNumGenerator.setNumTuples(60);

	ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());
	dag.addStream("numToName", randNumGenerator.out, randNameGenerator.in).setLocality(Locality.CONTAINER_LOCAL);
	dag.addStream("bothToCons", randNameGenerator.out, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
