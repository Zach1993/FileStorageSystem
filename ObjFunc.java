package pfpt;

import java.util.ArrayList;



public class ObjFunc {
	private String name;
	private String type;
	private String text;
	private ObjFunc path;
	
	//private ObjFunc parent;
	private ArrayList<ObjFunc> children;
	
	// Drive Constructor
	ObjFunc(String type, String name){
		this.name = name;
		this.type = type;
		this.path = null;
		//this.parent = null;
		this.children = new ArrayList<ObjFunc>();
	}
	
	// folder and zip constructor
	ObjFunc(String type, String name, ObjFunc path){
		this.name = name;
		this.type = type;
		this.path = path;
		//this.parent = path.getParent();
		this.children = new ArrayList<ObjFunc>();
	}
	
	// textFile constructor
	
	ObjFunc(String type, String name, ObjFunc path, String text){
		this.name = name;
		this.type = type;
		this.text = text;
		this.path = path;
		//this.parent = path.getParent();
		
	}
	
	// name functions
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	// text functions
	public void changeText(String text){
		this.text = text;
	}
	
	// parent functions
	public ObjFunc getParent(){
		// get children of path, and the last child will be the parent of new object
		if(path.children.size() == 0){
			return path;
		}
		int last_child = path.children.size()-1;
		ObjFunc parent_p = path.children.get(last_child);
		return parent_p; // parent
	}
	public void addParent(ObjFunc parent){
		this.path = parent.path;
	}
	
	
	// child functions
	// get children array
	public ArrayList<ObjFunc> getChildren(){
		return children;
	}
	
	// gets the child and returns it as an object
	public ObjFunc getChild(String name) throws Except{
		ObjFunc child = null;
		for(int i = 0; i < children.size(); i++){
			if(children.get(i).getName().equals(name)){
				child =  children.get(i);
			}else
				throw new Except("path not found");
			
		}return child;
	}
	
	
	// add child to children 
	public void addChild(ObjFunc child){
		children.add(child);
	}
	
	// remove child
	public void removeChild(ObjFunc child){
		for(int i = 0; i < children.size(); i++){
			if(children.get(i).equals(child)){
				children.remove(i);
			}
		}
	}
	
	// print functions
	@ Override public String toString(){
		String str = name;
		if(type == "textFile"){
			str = name + "(text: " + text + ")";
		}else{
		for(int i = 0; i < children.size(); i++){
			str += "/" + children.get(i);
		}}return str;
	}
	
	// add functions
	

	// type functions
	public String getType(){
		return type;
	}
	
	// size functions
	public int getSize(){
		int size = 1;
		
		if(type == "textFile"){
			size = text.length();

		}else if(children.size() == 0){
			size = 1;
			
		}else if(type.equalsIgnoreCase("zip")==true){
			for(ObjFunc f:children){
				size += f.getSize();
			} 
			size = size/2; // zipfile case
		}
		else{
			for(ObjFunc f:children){
				size += f.getSize();
			}	
		} return size;
		
	}
		
}
