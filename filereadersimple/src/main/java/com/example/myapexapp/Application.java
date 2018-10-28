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
import com.datatorrent.lib.io.fs.AbstractFileInputOperator.FileLineInputOperator;

@ApplicationAnnotation(name="FileReaderSimple")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Sample DAG with 2 operators
    // Replace this code with the DAG you want to build

    FileLineInputOperator in = dag.addOperator("input", new FileLineInputOperator());

    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

		in.setDirectory("input-dir");

    dag.addStream("lineData", in.output, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
