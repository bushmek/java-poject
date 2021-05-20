package clases;

import java.io.Serializable;

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

