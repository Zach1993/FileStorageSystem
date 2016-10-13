package pfpt;

import java.util.ArrayList;




public class Functions {
	// private variables
	private ArrayList<ObjFunc> fLst = new ArrayList<ObjFunc>(); // holds objects with full paths
	private ArrayList<ObjFunc> aLst = new ArrayList<ObjFunc>(); // holds all folders, zips, and drives
	ObjFunc f;
	
	// functions
	public void createDrive(String type, String name)throws Except{
		if(checkName(name, null, type)==false){
		ObjFunc fun = new ObjFunc(type, name);
		fLst.add(fun);
		aLst.add(fun);
		}else 
			throw new Except("Drive already exists");
	}
	
	public void createFolder(String type, String name, String path){
		try{
		f = find(path);
		if(f.getType().equals("textFile")){
			throw new Except("Illegal File System Operation ");
		}else if(checkName(name, path, type)==true){
			throw new Except("Folder already exists in path");
		}
		else{
			ObjFunc fun = new ObjFunc(type, name, f);
			f.addChild(fun);
			aLst.add(fun);
		}
				
		}catch(Except e){
			System.out.println(e.getMessage());
		}
	}
	public void createZip(String type, String name, String path){
		try{
			f = find(path);
			if(f.getType().equals("textFile")){
				throw new Except("Illegal File System Operation ");
			}else if(checkName(name, path, type)==true){
				throw new Except("Zip already exists in path");
			}
			else{
				ObjFunc fun = new ObjFunc(type, name, f);
				f.addChild(fun); 
				aLst.add(fun);
			}
				
			}catch(Except e){
				System.out.println(e.getMessage());
			}
	}
	public void createText(String type, String name, String path, String text){
		try{
			f = find(path);
			if(f.getType().equals("textFile")){
				throw new Except("Illegal File System Operation");
			}else if(checkName(name, path, type)==true){
				throw new Except("textFile already exists in path");
			}
			else{
				ObjFunc fun = new ObjFunc(type, name, f, text);
				f.addChild(fun); 
				aLst.add(fun);
				
			}}catch(Except e){
				System.out.println(e.getMessage());
			}
	}
	
	
	public void delete(String name, String path){
		
	}
	
	
	public void move(String source, String source_parent, String destination) throws Except{
		ObjFunc temp1 = find(source);
		ObjFunc temp2 = find(destination);
		ObjFunc old_parent = find(source_parent);
		if(temp1.getType()=="drive"){
			throw new Except("Illegal File System Operation");
		}else
			old_parent.getChildren().remove(temp1);
			//fLst.add(old_parent);
			temp1.addParent(temp2);
			temp2.addChild(temp1);
			
	}
	
	public void writeToText(String file_path, String text, String name){
		ObjFunc func = null;
		ObjFunc text_obj;
		try{
		func = find(file_path);
		text_obj = func.getChild(name);
		
		System.out.println("text name: " + text_obj.getName());
		if(text_obj.getType().equals("textFile")){
			text_obj.changeText(text);
		}else
			throw new Except("Illegal File System Operation");
		}catch(Except e){
			System.out.println(e.getMessage());
		}
	}
	
	
	public void printAll(){
		System.out.println("printing all now:");
		for(int i = 0; i < aLst.size()-1; i++){
			ObjFunc f = aLst.get(i);
			System.out.println(f.toString());
		}
	}
	
	public void printDrivePaths() {
		if (fLst.size() >= 0){
		for(int index = 0; index < fLst.size(); index++){
			ObjFunc f = fLst.get(index);
			System.out.println(f.toString());	
		}
	}// if close
}
	
	public void printSize(String name){
		ObjFunc f = find(name);
		int s = f.getSize();
		System.out.println("size is: " + s);
	}
	
	
	/**
	 * checks if the chosen name is already being used in the 
	 * path that the file is being saved to
	 * @param name
	 * @param path
	 * @param type
	 * @return
	 */
	public boolean checkName(String name, String path, String type){
		String[] search;
		if(path==null){
			// check if name exists
			for(int i = 0; i < fLst.size(); i++){
				if(name.equals(fLst.get(i))){
					return true; // drive exists
				}
			}return false; // drive does not exist yet
		}else
			search = path.split("/");
			for(int i = 0; i < search.length; i++){
				if(name.equals(search[i])){
					return true; // name exists in path
				}
		}return false; // name does not exist yet
	}
	/**
	 * Finds the path and returns the drive object in path return null if path doesn't exist
	 * @param path
	 * @return
	 */
	public ObjFunc find(String path){
		System.out.println("in find path searching for: " + path);
		String[] search = path.split("/"); // split path so that we can find the drive in the file list
		ObjFunc file = null; // to access file objects
		
		try{
//		if(path == null){
//			
//		}
		if(aLst.size() > 0){
			for(int i = 0; i < aLst.size(); i++){
				ObjFunc f = aLst.get(i);
				if(f.getName().equalsIgnoreCase(search[0])){ // check the drive of every file object to see if the path exists
					file = f;
					break;
					
				}}}
			else{
					throw new Except("Path Not Found"); // throw error path not found, change to try above 
				}
					
			int index = 0;
		while(index < search.length){// check rest of list to make sure it matches
			if(search.length == 1){
				return file;
			}else
				if(search[index]==file.getChildren().get(index).getName()){
					index++;
				
				}else{
					throw new Except("Path Not Found");
				}
		
		}}catch(Except f){
			System.out.println(f.getMessage());
			return null;
		}return file;
	}
}

