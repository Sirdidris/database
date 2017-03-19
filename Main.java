package data;

public class Main {

	public static void main(String[] args) {
		InfoFromFile f1 = new InfoFromFile();
		f1.getInfo("03-17 lik.csv");
		f1.writeToFile(f1.listOfFurnit);
	}

}
