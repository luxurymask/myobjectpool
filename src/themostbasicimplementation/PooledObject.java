package themostbasicimplementation;

public class PooledObject {
	private boolean isBusy;
	private Object object;
	
	public PooledObject(Object object){
		this.object = object;
		isBusy = false;
	}
	
	public void setBusy(boolean isBusy){
		this.isBusy = isBusy;
	}
	
	public boolean isBusy(){
		return this.isBusy;
	}
	
	public Object getObject(){
		return object;
	}
}
