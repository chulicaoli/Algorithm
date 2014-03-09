package algorithm;
//在生产者消费者模型中，如果生产者和消费者的读写操作不频繁，可以用单缓冲队列，最好是用线程安全地的blockingqueue。
//但是当生产者和消费者的读写操作都很频繁的时候就不要用单缓冲队列了，改用环形缓冲队列。
public class RingBuffer {
	int NMAX = 3;
	int iput = 0;
	int iget = 0;
	int n = 0;

	double buffer[] = new double[3];

	public int addring(int i) {
		return (i + 1) == NMAX ? 0 : i + 1;
	}

	public double dequeue() {
		if (n > 0) {
			int pos = iget;
			iget = addring(iget);
			n--;
			System.out.println("get-->" + buffer[pos]);
			return buffer[pos];
		} else {
			System.out.println("Buffer is Empty");
			return 0.0;
		}
	}

	public void enqueue(double z) {
		if (n < NMAX) {
			buffer[iput] = z;
			System.out.println("put<--" + buffer[iput]);
			iput = addring(iput);
			n++;
		} else
			System.out.println("Buffer is full");
	}

	public static void main(String[] args) {
		RingBuffer cb = new RingBuffer();
		cb.enqueue(1);
		cb.enqueue(2);
		cb.enqueue(3);
		cb.dequeue();
		cb.enqueue(4);
		cb.dequeue();
		cb.dequeue();
		cb.dequeue();
	}
}