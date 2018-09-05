package sample.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipStrUtil {
	private static final Charset CHARSET_RAW = StandardCharsets.UTF_8;
	private static final Charset CHARSET_ZIP = StandardCharsets.ISO_8859_1;

	public static String gzip(String str) throws IOException {
		if (null == str || str.length() < 1) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try(GZIPOutputStream gzip = new GZIPOutputStream(out)) {
			gzip.write(str.getBytes());
		}
		return out.toString(CHARSET_ZIP.name());
	}


	public static String gunzip(String str) throws IOException {
		if (null == str || str.length() <= 0) {
			return str;
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes(CHARSET_ZIP.name()));
		try(GZIPInputStream gzip = new GZIPInputStream(in);) {
			byte[] buffer = new byte[256];
			int n = 0;
			while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
				// 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
				out.write(buffer, 0, n);
			}
		}
		// 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
		return out.toString(CHARSET_RAW.name());
	}
}
