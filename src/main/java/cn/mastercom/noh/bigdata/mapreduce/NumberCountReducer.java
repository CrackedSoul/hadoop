package cn.mastercom.noh.bigdata.mapreduce;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author IronSoul
 */
public class NumberCountReducer extends Reducer<LongWritable, IntWritable, LongWritable, IntWritable> {

  private LongWritable outKey;
  private IntWritable outValue;

  @Override
  public void setup(Context context) throws IOException, InterruptedException {
    System.out.println("Run SetUp");

  }

  @Override
  public void cleanup(Context context) throws IOException, InterruptedException {
    System.out.println("Run CleanUp");
  }

  @Override
  public void reduce(LongWritable key, Iterable<IntWritable> values, Context context
  ) throws IOException, InterruptedException {
    outKey = new LongWritable();
    outValue = new IntWritable();
    outKey.set(key.get());
    for (IntWritable value : values) {
      outValue.set(outValue.get() + value.get());
    }
    context.write(outKey, outValue);
  }
}
