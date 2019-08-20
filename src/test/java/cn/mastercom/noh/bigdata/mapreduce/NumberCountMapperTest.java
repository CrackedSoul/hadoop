package cn.mastercom.noh.bigdata.mapreduce;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class NumberCountMapperTest {
  @Before
  public void setup(){}

  @Test
  public void testMapper_Normal_returnNormal() throws IOException {
    new MapDriver<LongWritable, Text,LongWritable, IntWritable>().withMapper(new NumberCountMapper())
        .withInput(new LongWritable(),new Text("324 310 262 202 272 312 214 381 241 206"))
        .withOutput(new LongWritable(324L),new IntWritable(1))
        .withOutput(new LongWritable(310L),new IntWritable(1))
        .withOutput(new LongWritable(262L),new IntWritable(1))
        .withOutput(new LongWritable(202L),new IntWritable(1))
        .withOutput(new LongWritable(272L),new IntWritable(1))
        .withOutput(new LongWritable(312L),new IntWritable(1))
        .withOutput(new LongWritable(214L),new IntWritable(1))
        .withOutput(new LongWritable(381L),new IntWritable(1))
        .withOutput(new LongWritable(241L),new IntWritable(1))
        .withOutput(new LongWritable(206L),new IntWritable(1))
        .runTest();
  }
  @Test
  public void testMapper_empty_returnEmpty() throws IOException {
    new MapDriver<LongWritable, Text,LongWritable, IntWritable>().withMapper(new NumberCountMapper())
        .withInput(new LongWritable(),new Text(""))
        .runTest();
  }
  @Test
  public void testMapper_ErrNum_returnLess() throws IOException {
    new MapDriver<LongWritable, Text,LongWritable, IntWritable>().withMapper(new NumberCountMapper())
        .withInput(new LongWritable(),new Text("324 310 2v62 202 272 31a2 214 381 241 206"))
        .withOutput(new LongWritable(324L),new IntWritable(1))
        .withOutput(new LongWritable(310L),new IntWritable(1))
        .withOutput(new LongWritable(202L),new IntWritable(1))
        .withOutput(new LongWritable(272L),new IntWritable(1))
        .withOutput(new LongWritable(214L),new IntWritable(1))
        .withOutput(new LongWritable(381L),new IntWritable(1))
        .withOutput(new LongWritable(241L),new IntWritable(1))
        .withOutput(new LongWritable(206L),new IntWritable(1))
        .runTest();
  }

}
