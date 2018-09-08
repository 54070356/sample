package sample.javacore.lang;

import org.junit.Test;

public class TestDynamicParams {
	private int f1(int p1, String... p2) {
		if(p2.length == 0) {
			System.out.println("this is dynamic, parms=0");
		} else {
			System.out.println("this is dynamic, parms="+p2);
		}
		return 1;
	}
	
	private void f1(int p1, String p2) {
		System.out.println("this is static");
	}
	
	@Test
	public void testStatic() {
		this.f1(1, "hello");
	}
	
	@Test
	public void testDynamic() {
		this.f1(1);
	}
}
