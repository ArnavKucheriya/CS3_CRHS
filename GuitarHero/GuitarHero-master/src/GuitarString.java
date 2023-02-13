public class GuitarString {
	RingBuffer ringBuffer;
	int capacity;
	int numTics;
	public GuitarString(double freq){
		ringBuffer = new RingBuffer((int) (44100/freq));
		setringBuffer(new double[ringBuffer.ary.length]);
		capacity = ringBuffer.ary.length;
		numTics=0;
	}
	public GuitarString(double[] init){
		ringBuffer= new RingBuffer(init.length);
		capacity = ringBuffer.ary.length;
		setringBuffer(init);
		numTics=0;
	}
	private void setringBuffer(double[] list){
		for(int i=0;i<list.length;i++){
			ringBuffer.enqueue(list[i]);
		}
	}
	public void pluck(){
		while(!ringBuffer.isEmpty()){
			ringBuffer.dequeue();
		}
		while(!ringBuffer.isFull()){
			double value = Math.random()*2;
			if (value ==1) ringBuffer.enqueue(Math.random()*.5);
			else ringBuffer.enqueue(-(Math.random()*.5));
		}
	}
	public void tic(){
		numTics++;
		ringBuffer.enqueue((ringBuffer.dequeue() + ringBuffer.peek())/2 * .994);
	}
	public double sample(){return ringBuffer.peek();}
	public int time(){return numTics;}
}


