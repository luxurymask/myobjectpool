package testcommonspool;

import java.util.NoSuchElementException;

import org.apache.commons.pool.impl.GenericObjectPool;

public class Test {
	private GenericObjectPool<MyObject> pool = new GenericObjectPool<>(new MyObjectFactory());
	
	public static void main(String[] args){
		Test test = new Test();
		Thread returnThread = new Thread(new Runnable(){
			public void run(){
				MyObject returnObject = null;
				try {
					returnObject = test.pool.borrowObject();
					System.out.println("returnObject borrowed...");
				} catch (NoSuchElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					test.pool.returnObject(returnObject);
					System.out.println("returnObject returned...");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		returnThread.setName("returnThread");
		returnThread.start();
		
		Thread borrowThread = new Thread(new Runnable(){
			public void run(){
				try {
					MyObject obj = test.pool.borrowObject();
					System.out.println("obj borrowing..." + Thread.currentThread().getName());
					Thread.sleep(10000);
					System.out.println("obj borrowed by " + Thread.currentThread().getName());
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		borrowThread.setName("borrowThread");
		borrowThread.start();
	}
}
