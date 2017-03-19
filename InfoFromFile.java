package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoFromFile {
	
	String fileName = "";
	List<FurnitureGoods> listOfFurnit = new ArrayList<FurnitureGoods>();
	
	public void getInfo (String fileName){
		
		File file = new File(fileName);
		
		Scanner remainderInfo = null;
		try {
			remainderInfo = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Check your file name");
		}
		
		// pattern to find number and furniture goods code in file
		Pattern number = Pattern.compile("[0-9]+\\..+");
		
		//pattern to find semicolon not in place
		Pattern semicolon = Pattern.compile("[0-9]{11,}+; ");
		
		
		while (remainderInfo.hasNextLine()){
			String goods = remainderInfo.nextLine();
			
			//removes semicolon that isn't a split point
			Matcher n = semicolon.matcher(goods);
			if (n.find()){
				System.out.println(n.group());
				goods = goods.replaceAll(n.group(), n.group().replaceAll(";", ""));
			}
			
			String[] temp = goods.split(";");
			
			Matcher m = number.matcher(temp[1]);
			
			// creates a new Object and add it to list if line has needed parameters
			if (m.matches()){
				data.FurnitureGoods furnFitting = new data.FurnitureGoods();
				furnFitting.setCode(temp[1].substring(temp[1].indexOf(".")+1));
				furnFitting.setName(temp[3]);
				furnFitting.setUnitOfCount(temp[7]);
				furnFitting.setAmount(Float.parseFloat(temp[9]));
				furnFitting.setBuyPrice(Float.parseFloat(temp[10]));
				listOfFurnit.add(furnFitting);
			}
		}
		System.out.println(listOfFurnit.size());
	}
	
	public void writeToFile (List<FurnitureGoods> list){
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter("database.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Can't create file");
		}
		
		for (FurnitureGoods temp : list){
		writer.println(temp.getCode()+" "+temp.getName()+" "+temp.getUnitOfCount()+" "+temp.getAmount()+" "+temp.getBuyPrice());
		}
		
		writer.close();
	}
	
}
