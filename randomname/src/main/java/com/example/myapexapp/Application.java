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

@ApplicationAnnotation(name="RandomName")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Sample DAG with 2 operators
    // Replace this code with the DAG you want to build

    LengthGenerator lengthGenerator = dag.addOperator("lengthGenerator", LengthGenerator.class);
    lengthGenerator.setNumTuples(500);

		WordGenerator wordGenerator = dag.addOperator("wordGenerator", WordGenerator.class);
	
    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("lengthData", lengthGenerator.out, wordGenerator.input).setLocality(Locality.CONTAINER_LOCAL);
		dag.addStream("wordData", wordGenerator.out, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
