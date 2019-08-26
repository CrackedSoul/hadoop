package cn.mastercom.noh.bigdata.mapreduce;

import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class NumberCountReducerTest {
  @Before
    public void setup(){}

    @Test
    public void testReducer() throws IOException {
      new ReduceDriver<LongWritable, IntWritable,LongWritable,IntWritable>()
          .withReducer(new NumberCountReducer())
          .withInput(new LongWritable(232L),
              Arrays.asList(new IntWritable(3),
                  new IntWritable(1),
                  new IntWritable(4),
                  new IntWritable(2)))
          .withOutput(new LongWritable(232L),new IntWritable(10))
          .runTest();
    }
}
