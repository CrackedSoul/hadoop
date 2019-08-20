package cn.mastercom.noh.bigdata.mapreduce;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class NumberCountMapReduceTest {
@Before
  public void setup(){}
  @Test
  public void testMapReduce() throws IOException {
    new MapReduceDriver(new NumberCountMapper(),new NumberCountReducer())
        .withInput(new LongWritable(10L),new Text("230 361 230 345 243 361 243 243 265 361"))
        .withOutput(new LongWritable(230L),new IntWritable(2))
        .withOutput(new LongWritable(243L),new IntWritable(3))
        .withOutput(new LongWritable(265L),new IntWritable(1))
        .withOutput(new LongWritable(345L),new IntWritable(1))
        .withOutput(new LongWritable(361L),new IntWritable(3)).runTest();
  }
}
