package themostbasicimplementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectPool {
	private int poolSize;
	private List<PooledObject> objects;
	
	public ObjectPool(){
		this(20);
	}
	
	public ObjectPool(int poolSize){
		this.poolSize = poolSize;
		this.objects = new ArrayList<PooledObject>();
	}
	
	public synchronized void createPool(){
		if(!objects.isEmpty()){
			return;
		}
		
		for(int i = 0;i < poolSize;i++){
			Object object = new Object();
			objects.add(new PooledObject(object));
		}
	}
	
	public synchronized Object getObject(){
		if(objects.isEmpty()){
			return null;
		}
		
		Object object = getFreeObject();
		while(object == null){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			object = getFreeObject();
		}
		
		return object;
	}
	
	private synchronized Object getFreeObject(){
		Object object = null;
		
		for(PooledObject pooledObject : objects){
			if(!pooledObject.isBusy()){
				pooledObject.setBusy(true);
				object = pooledObject.getObject();
				break;
			}
		}
		
		return object;
	}
	
	public synchronized void returnObject(Object object){
		if(objects.isEmpty()){
			return;
		}
		
		for(PooledObject pooledObject : objects){
			if(object == pooledObject.getObject()){
				pooledObject.setBusy(false);
				break;
			}
		}
	}
	
	public synchronized void closeObjectPool(){
		while(!objects.isEmpty()){
			Iterator<PooledObject> iterator = objects.iterator();
			while(iterator.hasNext()){
				PooledObject pooledObject = iterator.next();
				if(!pooledObject.isBusy()){
					iterator.remove();
				}
			}
		}
	}
}
