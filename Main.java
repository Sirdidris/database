package data;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		InfoFromFile f1 = new InfoFromFile();
		f1.getInfo("03-17 lik.csv");
		//f1.writeToFile(f1.listOfFurnit);
		InfoFromFile f2 = new InfoFromFile();
		f2.getInfoDup("4K 03-17.csv");
		List<FurnitureGoods> full = f1.findSame(f1.listOfFurnit, f2.listOfFurnit);
		f1.writeToFile(full, "Su kainomis");
		f1.smallProfit(full);
		f1.writeToFile(full, "savikaina");
	}

}
