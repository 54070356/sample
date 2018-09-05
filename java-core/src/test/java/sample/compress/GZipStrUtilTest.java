package sample.compress;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import sample.compress.GZipStrUtil;

public class GZipStrUtilTest {

	@Test
	public void test_compress() throws IOException {
		URL url = GZipStrUtil.class.getClassLoader().getResource("zip-raw.txt");
		String filename = url.getFile();
		String str = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);

		float len0 = str.length();
		System.out.println("原始的字符串长度为------->" + len0);

		String ys = GZipStrUtil.gzip(str);
		float len1 = ys.length();
		System.out.println("压缩后的字符串----->" + ys);
		System.out.println("压缩后的字符串长度为----->" + len1);

		String jy = GZipStrUtil.gunzip(ys);
		System.out.println("解压缩后的字符串长度为--->" + jy.length());
		System.out.println("\n压缩比例为" + len1 / len0);

		// 判断
		if (str.equals(jy)) {
			System.out.println("先压缩再解压以后字符串和原来的是一模一样的");
		}
	}
}
