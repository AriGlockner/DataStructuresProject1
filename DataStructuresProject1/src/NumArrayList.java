import java.util.Random;

public class NumArrayList implements NumList {
	private double[] list;
	private int size;
	
	public NumArrayList() {
		list = new double[0];
		size = 0;
	}
	
	public NumArrayList(int size) {
		list = new double[size];
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	/*
	public int capacity() {
		
	}
	*/
	
	public void add(double value) {
		if (size == 0)
		{
			size++;
			list = new double[] { value };
			return;
		}
		double[] newList = new double[++size];
		for (int i = 0; i < size - 1; i++)
			newList[i] = list[i];
		newList[size-1] = value;
		list = newList;
	}
	
	
	public double lookup(int index) {
		return list[index];
	}
	
	@Override
	public String toString() {
		if (size == 0)
			return "";
		String str = "";
		for (double d : list)
			str += " " + d;
		return str.substring(1);
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		NumArrayList n1 = new NumArrayList();
		for (int i = 0; i < 5; i++)
			n1.add(rand.nextInt(10));
		System.out.println(n1);
		System.out.println(n1.lookup(3));
	}
}
