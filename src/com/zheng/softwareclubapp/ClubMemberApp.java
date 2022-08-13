package com.zheng.softwareclubapp;

public class ClubMemberApp {

	public static void main(String[] args) {
		// Strings for file path
		String org_file_path = "./src/files_folder/original_members.txt";
		String new_file_path = "./src/files_folder/members.txt";
		String bytes_file_path = "./src/files_folder/bytes_members.txt";
		
		SoftwareDeveloperClub devClub = new SoftwareDeveloperClub();
		devClub.runMenu(new_file_path, org_file_path, bytes_file_path);

	}
}
