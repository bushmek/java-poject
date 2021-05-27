package clases;

import java.io.Serializable;

/**
*
* author @cluckin
*/

public class Bicycle implements Serializable{

	private String id,type,coordynates,filial,status;
	public Bicycle() {
		
	}

	public Bicycle(String id,String type,String coordynates,String filial,String status) {
		this.id=id;
		this.type=type;
		this.coordynates=coordynates;
		this.filial=filial;
		this.status=status;
	}
	
	
	//geter's
	public String getID() {
		return id;
	}
	
	
	public String getType() {
		return type;
	}
	
	public String getCoordynates() {
		return coordynates;
	}
	
	public String getFilial() {
		return filial;
	}
	
	public String getStatus() {
		return status;
	}
	
	//get convert value from id to type of bicycle
	public String getConvertType() {
		if (type.equals("1"))
			return "Гірський";
		if (type.equals("2"))
			return "Cпортивний";
		if (type.equals("3"))
			return "Дитячий";
		else
			return "Стандартний";
	}
}

