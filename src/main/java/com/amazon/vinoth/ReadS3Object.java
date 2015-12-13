package com.amazon.vinoth;

import static com.amazon.vinoth.S3AWSClientUtil.listAllBucketNames;
import static com.amazon.vinoth.S3AWSClientUtil.listAllFilesInAFolder;
import static com.amazon.vinoth.S3AWSClientUtil.listAllKeysInABucket;
import static com.amazon.vinoth.S3AWSClientUtil.readFileAndPrintToConsole;

import java.io.IOException;
import java.util.List;

public class ReadS3Object {

	private static final String BUCKET_NAME = "vinothtest";
	private static final String FOLDER_NAME = "version1.8";
	private static final String FILE = "version1.7/manual_1.7.txt";

	public static void main(String[] args) throws IOException {

		printLine("PRINT ALL BUCKETS");
		List<String> bucketNames = listAllBucketNames();
		printList(bucketNames);

		printLine("PRINT ALL KEYS ");
		List<String> allKeys = listAllKeysInABucket(BUCKET_NAME);
		printList(allKeys);

		printLine("PRINT ALL FILES IN A GIVEN FOLDER");
		List<String> allFilesInAFolder = listAllFilesInAFolder(BUCKET_NAME,
				FOLDER_NAME);
		printList(allFilesInAFolder);

		printLine("PRINT THE CONTENTS OF A PARTICULAR KEY (FILE)");
		readFileAndPrintToConsole(BUCKET_NAME, FILE);
	}

	private static void printLine(String message) {
		System.out.println("----------------------- " + message
				+ " ------------------------");
	}

	private static void printList(List<String> list) {
		list.stream().forEach(System.out::println);
	}

}
