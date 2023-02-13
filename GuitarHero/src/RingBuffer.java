import java.util.NoSuchElementException;

public class RingBuffer {
    int last,first,size;
    double[] ary;
    public RingBuffer(int capacity){
        ary = new double[capacity];
        last =0;
        first =0;
        size =0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public boolean isFull(){
        return size ==ary.length;
    }
    public void enqueue(double x){
        if(isFull()) throw new IllegalStateException();
        ary[last++] = x;
        size ++;
        if(last >= ary.length) last =0;
    }
    public double dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        double XD = ary[first++];
        if(first >= ary.length) first =0;
        size--;
        return XD;
    }
    public double peek(){
        if (isEmpty()) throw new NoSuchElementException();
        return ary[first];
    }
    public String toString(){
        String output = "[";
        int tempSize = size();
        for(int i=first;i<last;i++){
            tempSize--;
            if(tempSize ==0)
                output+= ary[i];
            else
                output += ary[i] + ", ";
        }
        if(tempSize!=0){
            for(int i=first;i<ary.length;i++){
                tempSize--;
                if(tempSize ==0)
                    output+= ary[i];
                else
                    output += ary[i] + ", ";
            }
        }
        if(tempSize!=0){
            for(int i=0;i<last;i++){
                tempSize--;
                if(tempSize ==0)
                    output+= ary[i];
                else
                    output += ary[i] + ", ";
            }
        }
        output+= "]";
        return output;
    }
}



