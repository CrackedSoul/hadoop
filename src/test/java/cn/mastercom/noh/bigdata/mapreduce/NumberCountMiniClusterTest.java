package cn.mastercom.noh.bigdata.mapreduce;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.mapred.ClusterMapReduceTestCase;

public class NumberCountMiniClusterTest extends ClusterMapReduceTestCase {
  public static class OutputLogFilter implements PathFilter {
    public boolean accept(Path path) {
      return !path.getName().startsWith("_");
    }
  }

  @Override
  protected void setUp() throws Exception {
    if (System.getProperty("test.build.data") == null) {
      System.setProperty("test.build.data", "/tmp");
    }
    if (System.getProperty("hadoop.log.dir") == null) {
      System.setProperty("hadoop.log.dir", "/tmp");
    }
    super.setUp();
  }
  public void  testMiniCluster() throws Exception {

    Path localInput = new Path("input");
    Path input = getInputDir();
    Path output = getOutputDir();
    getFileSystem().copyFromLocalFile(localInput, input);

    NumberCountDriver driver = new NumberCountDriver();
    Configuration conf = createJobConf();
    driver.setConf(conf);

    int exitCode = driver.run(new String[] {
        input.toString(), output.toString() });
    assertThat(exitCode, is(0));

    getFileSystem().copyToLocalFile(output, new Path("output"));

    Path[] outputFiles = FileUtil.stat2Paths(
        getFileSystem().listStatus(output, new OutputLogFilter()));
    InputStream in = getFileSystem().open(outputFiles[0]);
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String buff;
    while ((buff=reader.readLine())!=null){
      System.out.println(buff);
    }
  }

}
