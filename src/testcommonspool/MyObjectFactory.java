package testcommonspool;

import org.apache.commons.pool.PoolableObjectFactory;

public class MyObjectFactory implements PoolableObjectFactory<MyObject>{

	@Override
	public MyObject makeObject() throws Exception {
		System.out.println("obj made by thread " + Thread.currentThread().getName());
		return new MyObject();
	}

	@Override
	public void destroyObject(MyObject obj) throws Exception {
		obj = null;
		System.out.println("obj destroied");
	}

	@Override
	public boolean validateObject(MyObject obj) {
		System.out.println("obj validated by thread " + Thread.currentThread().getName());
		return true;
	}

	@Override
	public void activateObject(MyObject obj) throws Exception {
		System.out.println("obj activated by thread " + Thread.currentThread().getName());
	}

	@Override
	public void passivateObject(MyObject obj) throws Exception {
		System.out.println("obj passivated by thread " + Thread.currentThread().getName());
	}
	
}
