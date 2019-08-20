package cn.mastercom.noh.bigdata.mapreduce;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author IronSoul
 */
public class NumberCountMapper extends Mapper<LongWritable, Text, LongWritable, IntWritable> {

  private LongWritable outKey;
  private IntWritable outValue;

  @Override
  public void setup(Context context) throws IOException, InterruptedException {
    System.out.println("Run SetUp");
    outKey = new LongWritable();
    outValue = new IntWritable();
  }

  @Override
  public void cleanup(Context context) throws IOException, InterruptedException {
    System.out.println("Run CleanUp");
  }

  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString();
    String[] nums = line.split(" ");
    for (String num : nums) {
      try {
        Long.parseLong(num.trim());
      } catch (NumberFormatException e) {
        continue;
      }
      outKey.set(Long.parseLong(num));
      outValue.set(1);
      context.write(outKey, outValue);
    }
  }
}
