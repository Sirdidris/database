package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
				goods = goods.replaceAll(n.group(), n.group().replaceAll(";", ""));
			}
			
			String[] temp = goods.split(";");
			
			Matcher m = number.matcher(temp[1]);
			
			// creates a new Object and add it to list if line has needed parameters
			if (m.matches()){
				FurnitureGoods furnFitt = fillListRemainder(temp);
				listOfFurnit.add(furnFitt);
			}
		}
	}
	
	public void writeToFile (List<FurnitureGoods> list, String nameYFile){
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(nameYFile+".txt");
		} catch (FileNotFoundException e) {
			System.out.println("Can't create file");
		}
		
		for (FurnitureGoods temp : list){
		writer.println(temp.getCode()+";"+temp.getName()+";"+temp.getUnitOfCount()+";"+temp.getAmount()+";"
		+temp.getBuyPrice()+";"+temp.getSellPrice()+";"+temp.profitPerc);
		}
		
		writer.close();
	}
	
	public List<FurnitureGoods>  delDuplicate (List<FurnitureGoods> listDel, List<FurnitureGoods> toDelete){
		
			for (Iterator<FurnitureGoods> iter = listDel.listIterator(); iter.hasNext();){
				FurnitureGoods a = iter.next();
				for (FurnitureGoods value : toDelete){
					if (a.getCode().equals(value.getCode())){
						iter.remove();
						break;
					}
				}
			}
		return listDel;
		}
	
	public List<FurnitureGoods> findSame (List<FurnitureGoods> mainList, List<FurnitureGoods> sellList){
		
		for(Iterator<FurnitureGoods> iter = mainList.listIterator(); iter.hasNext();){
			FurnitureGoods a = iter.next();
			for (FurnitureGoods value : sellList){
				if (a.getCode().equals(value.getCode())){
					a.setSellPrice(value.getSellPrice());
				}
			}
		}
		return mainList;
	}
	
	public FurnitureGoods fillListRemainder (String[] line){
		data.FurnitureGoods furnFitting = new data.FurnitureGoods();
		furnFitting.setCode(line[1].substring(line[1].indexOf(".")+1));
		furnFitting.setName(line[3]);
		furnFitting.setUnitOfCount(line[7]);
		furnFitting.setAmount(Float.parseFloat(line[9]));
		furnFitting.setBuyPrice(Float.parseFloat(line[10]));
		return furnFitting;
	}
	
	public FurnitureGoods fillListSell (String[] line){
		FurnitureGoods furnFitting = new FurnitureGoods();
		furnFitting.setCode(line[1].substring(line[1].indexOf(".")+1));
		furnFitting.setSellPrice(Float.parseFloat(line[10].replace(",", ".")));
		return furnFitting;
	}
	
	public void smallProfit (List<FurnitureGoods> fullList){
		for (Iterator<FurnitureGoods> iter = fullList.listIterator(); iter.hasNext();){
			FurnitureGoods next = iter.next();
			next.profitPerc = (next.getSellPrice()/next.getBuyPrice())*100-100;
		}
	}
	
	public void getInfoDup (String fileName){
		
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
				goods = goods.replaceAll(n.group(), n.group().replaceAll(";", ""));
			}
			
			String[] temp = goods.split(";");
			
			Matcher m = number.matcher(temp[1]);
			
			// creates a new Object and add it to list if line has needed parameters
			if (m.matches()){
				FurnitureGoods furnFitt = fillListSell(temp);
				listOfFurnit.add(furnFitt);
			}
		}
	}
	
}
