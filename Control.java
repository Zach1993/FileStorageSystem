package pfpt;
import java.util.Scanner;
public class Control {

		public static void main(String[] args) {
			Functions func = new Functions();
			String cmd;
			Scanner scan = new Scanner(System.in);
			
			System.out.println(
					"_________________________________________________________\n" +
					"Commands:\n" +
					"	cd - create new entity (Drive)\n" +
					"	cf - create new entity (Folder)\n" +
					"	cz - create new entity (Zip)\n" +
					"	ct - create new entity (TextFile)\n" +
					"	d - delete (ObjFunc)\n" +
					"	m - move ObjFunc1 to Destination ObjFunc2 \n" +
					"	w - write to textFile\n" +
					"	p - print entries\n" +
					"	s - print size of File\n" +
					"	q - quit - exit the system\n" +
					"   help - display commands\n" +
					"_________________________________________________________\n");
			while(true){
				System.out.println("Please enter command");
				//scan.nextLine();
				cmd = scan.nextLine();
				
				if(cmd.equalsIgnoreCase("cd")){
					System.out.println("please enter name ");
					//scan.nextLine();
					String name = scan.nextLine();
					try {
						func.createDrive("drive", name);
					} catch (Except e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					}
				
				else if(cmd.equalsIgnoreCase("cf")){
					
					System.out.println("please enter name ");
					//scan.nextLine();
					String name = scan.nextLine();
					
					System.out.println("please enter file name to add folder to");
					//scan.nextLine();
					String path = scan.nextLine();
					func.createFolder("folder", name, path);
					}
				
				else if(cmd.equalsIgnoreCase("cz")){
				
					System.out.println("please enter name ");
					//scan.nextLine();
					String name = scan.nextLine();
					System.out.println("please enter file name to add zip to");
					//scan.nextLine();
					String path = scan.nextLine();
					func.createZip("zip", name, path);
					}
				else if(cmd.equalsIgnoreCase("ct")){

					System.out.println("please enter name ");
					//scan.nextLine();
					String name = scan.nextLine();
					System.out.println("please enter file to add textFile to");
					//scan.nextLine();
					String path = scan.nextLine();
					System.out.println("please enter text");
					//scan.nextLine();
					String text = scan.nextLine();
					func.createText("textFile", name, path, text);
					}
					 
				else if(cmd.equalsIgnoreCase("s")){
					System.out.println("Please enter path name");
					//scan.nextLine();
					String path = scan.nextLine();
					func.printSize(path);
					}
				
				else if(cmd.equalsIgnoreCase("m")){
					System.out.println("Please enter name of file to be moved");
					//scan.nextLine();
					String source = scan.nextLine();
					System.out.println("Please enter name of parent file");
					//scan.nextLine();
					String source_parent = scan.nextLine();
					System.out.println("Please enter name of destination file");
					//scan.nextLine();
					String dest = scan.nextLine();
					
						try {
							func.move(source, source_parent, dest);
						} catch (Except e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				else if(cmd.equalsIgnoreCase("w")){
					System.out.println("Please enter parent of textFile");
					//scan.nextLine();
					String path = scan.nextLine();
					System.out.println("Please enter textFile name to write to");
					//scan.nextLine();
					String name = scan.nextLine();
					System.out.println("Please enter text to write to textFile");
					//scan.nextLine();
					String entry = scan.nextLine();

					func.writeToText(path, entry, name);
					}
				
				else if(cmd.equalsIgnoreCase("p")){
					func.printDrivePaths();					
					}
				else if(cmd.equalsIgnoreCase("ps")){
					System.out.println("Please enter file name to print size");
					String name = scan.nextLine();
					func.printSize(name);					
					}
				
				else if(cmd.equalsIgnoreCase("q")){
					System.exit(0);
					}
				
				else if(cmd.equalsIgnoreCase("help")){
					System.out.println("Please enter command");
				}
			}	

		}

	}

	

