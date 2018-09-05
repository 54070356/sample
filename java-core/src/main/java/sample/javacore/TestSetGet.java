package sample.javacore;

import org.junit.Test;

public class TestSetGet {
	@Test
	public void testSetLong() {
		Obj obj = new Obj();
		boolean t =false;
		obj.setLong(t? 1:null);
	}
	
	
	@Test
	public void test1() {
		System.out.println(1>=10);
		System.out.println(1<=10);
	}
	
	
	private static class Obj {
		private long l;
		public void setLong(long l) {
			this.l=l;
		}
	}
}
