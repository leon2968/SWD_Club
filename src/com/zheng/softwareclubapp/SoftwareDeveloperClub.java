package com.zheng.softwareclubapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SoftwareDeveloperClub {
	// arrayList of ClubMember
	private List<ClubMember> memberList = new ArrayList<>();
	
	private String header;
	private BufferedReader br_input = new BufferedReader(new InputStreamReader(System.in));

	/* 
	 * default constructor
	 */
	public SoftwareDeveloperClub() {
	}
	
	/*
	 * Display the arrayList content
	 */
	public void displayMembers() {
		System.out.println("The club has the following members : ");

		for(ClubMember member: memberList) {
			System.out.println(memberList.indexOf(member) + " : " + member);
		}
		System.out.println();
	}
	
	/**
	 * Add a member to the arrayList
	 */
	public void addMember() {
		System.out.println("Please enter the new member's name, city, state and favorite language(seperate by a single comma): ");
		try {
			String line = br_input.readLine();
			String[] memberInfo = line.split(",");
			ClubMember member = new ClubMember(memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3]);
			memberList.add(member);		
			System.out.println("The following member is added to the club: ");
			System.out.println(member);
		} catch(IOException e) {
			System.out.println("There is an error!");
			e.printStackTrace();
		} finally{
			System.out.println("Operation done.\n");
		}
	}
	
	/**
	 * Remove a member using its current index number
	 */
	public void removeMember() {
		displayMembers();
		System.out.println("Please enter the index of the member to be removed : ");
		try {
			int index = Integer.parseInt(br_input.readLine());
			System.out.println(memberList.remove(index) + " is removed from the club.");	
		} catch(IOException e) {
			System.out.println("There is an error!");
			e.printStackTrace();
		} finally{
			System.out.println("Operation done.\n");
		}
	}
	
	/**
	 * read contents of the file and populate the arrayList
	 */
	public void readFile(File file) {
		memberList.clear();
		System.out.println("Now loading club members from the file...");
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			header = br.readLine();

			String line = null;
			while((line = br.readLine())!=null) {
				String[] memberInfo = line.split("\\*{2}");
				//System.out.print(memberInfo);
				ClubMember member = new ClubMember(memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3]);
				memberList.add(member);
				
			}
			
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
			System.out.println("File(s) is not found!");
		} catch (IOException e ) {
			e.printStackTrace();
			System.out.println("There is an IO Exception!");
		} finally {
			System.out.println("Loading finished.\n");
		}
	}
	
	/**
	 * read contents of the file(bytes) and populate the arrayList
	 */
	public void readBytesFile(File file) {
		memberList.clear();
		System.out.println("Now loading club members from the file...");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8))){
			header = br.readLine();

			String line = null;
			while((line = br.readLine())!=null) {
				String[] memberInfo = line.split("\\*{2}");
				//System.out.print(memberInfo);
				ClubMember member = new ClubMember(memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3]);
				memberList.add(member);
				
			}
			
		}  catch (FileNotFoundException e ) {
			e.printStackTrace();
			System.out.println("File(s) is not found!");
		} catch (IOException e ) {
			e.printStackTrace();
			System.out.println("There is an IO Exception!");
		} finally {
			System.out.println("Loading finished.\n");
		}
	}
	

	
	/*
	 * save the contents of the arrayList to file
	 */
	public void writeToFile(File file) {
		System.out.println("Now writing changes to the file...");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

			bw.write(header+"\n");
			for(ClubMember member: memberList) {
				bw.write(member.getName() + "**" + member.getCity() + "**" +member.getState() + "**" +member.getFavlanguage() +"\n");
			}
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
			System.out.println("File(s) is not found!");
		} catch (IOException e ) {
			e.printStackTrace();
			System.out.println("There is an IO Exception!");
		}finally{
			System.out.println("Operation done.\n");
		}
	}
	
	/*
	 * save the contents of the arrayList to file in bytes
	 */
	public void writeToBytesFile(File file)  {
		System.out.println("Now writing changes to the bytes file...");
		try (FileOutputStream fos = new FileOutputStream(file)){
			byte[] bytes;
			bytes = (header+"\n").getBytes(StandardCharsets.UTF_8);;
			System.out.println(Arrays.toString(bytes));
			fos.write(bytes);
			for(ClubMember member: memberList) {
				String line = (member.getName() + "**" + member.getCity() + "**" +member.getState() + "**" +member.getFavlanguage() +"\n");
				bytes = line.getBytes(StandardCharsets.UTF_8);
				fos.write(bytes);
			}
			fos.flush();
			fos.close();
		} catch(IOException e) {
			System.out.println("There is an error!");
			e.printStackTrace();
		} finally{
			System.out.println("Operation done.\n");
		}
	}
	
	/*
	 * Display menu options for the user.
	 */
	public void displayMenu() {
		System.out.println("Select from the following menu: "
				+ "\n1: Display Members "
				+ "\n2: Remove a Member "
				+ "\n3: Add a Member "
				+ "\n4: Restore Member List "
				+ "\n5: Save the New Member List  "
				+ "\n6: (Bytes) Open the Member List File "
				+ "\n7: (Bytes) Save the Member List File "
				+ "\n0: Quit\n");
	}
	
	/*
	 * This is the method that handles the application, it reads inputs from the user,
	 * then call different methods to perform necessary operations.
	 */
	public void runMenu(String new_file_path, String org_file_path, String bytes_file_path) {
		try{
			System.out.println("Welcome to the Club Member App.");
			File file = new File(new_file_path);
			File orgFile = new File(org_file_path);
			File bytesFile = new File(bytes_file_path);
			
			//populate arrayList with previously saved file content
			readFile(file) ;
			
			//run menu options until user enters 0 to exit
			int option = -1;
			while(option != 0) {
				displayMenu();
				option = Integer.parseInt(br_input.readLine());
				switch(option) {
					case 1 : displayMembers(); break;	// display from arrayList
					case 2 : removeMember(); break;	//remove member from arrayList
					case 3 : addMember(); break; //add member to arrayList
					case 4 : readFile(orgFile); break; //clear arrayList and re-populate using original data
					case 5 : writeToFile(file); break; // write arrayList to the updated file
					case 6 : readBytesFile(bytesFile); break; // read bytes
					case 7 : writeToBytesFile(bytesFile); break; // write bytes
					case 0 : System.out.println("Thank you for using the Club Member App. Exiting now... ");
							System.exit(0);
					default : System.out.println("Invalid operation.");
				}
			}
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
			System.out.println("File(s) is not found!");
		} catch (IOException e ) {
			e.printStackTrace();
			System.out.println("There is an IO Exception!");
		}
	}
}
