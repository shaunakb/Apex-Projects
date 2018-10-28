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

@ApplicationAnnotation(name="ArcSin")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {

    TermGenerator termGenerator = dag.addOperator("startProcess", new TermGenerator());
		Coefficient coeff = dag.addOperator("coefficient", new Coefficient());
		MainTerm mainTerm = dag.addOperator("mainterm", new MainTerm());
		Product product = dag.addOperator("product", new Product());
		Sum sum = dag.addOperator("sum", new Sum());
		sum.setCumulative(true);		
	
    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("startCoeff", termGenerator.out1, coeff.input);
		dag.addStream("input", termGenerator.out2, mainTerm.input);
		dag.addStream("coeffData", coeff.output, product.num1);
		dag.addStream("mainTermData", mainTerm.output, product.num2);
		dag.addStream("termData", product.output, sum.data);
		dag.addStream("sumDouble", sum.sumDouble, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }
}
