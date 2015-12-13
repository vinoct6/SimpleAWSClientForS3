package com.amazon.vinoth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;

public class S3AWSClientUtil {

	private S3AWSClientUtil() {
	}

	private static AmazonS3 getS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(
				"accessKey",
				"secretKey");
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		return s3Client;
	}

	private static void displayTextInputStream(InputStream input) throws IOException {
		// Read one text line at a time and display.
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;

			System.out.println("    " + line);
		}
		System.out.println();
	}

	static List<String> listAllKeysInABucket(String bucketName) {
		ListObjectsRequest listObjectRequest = new ListObjectsRequest()
				.withBucketName(bucketName);
		ObjectListing objects = getS3Client().listObjects(listObjectRequest);
		return objects.getObjectSummaries().stream().map(summary -> summary.getKey()).collect(Collectors.toList());
	}

	static List<String> listAllFilesInAFolder(String bucketName, String prefix) {
		// Make sure that the prefix ends with /, otherwise it does not seem to work
		prefix = addDelimiterToPrefix(prefix);
		ListObjectsRequest listObjectRequest = new ListObjectsRequest()
				.withBucketName(bucketName).withPrefix(prefix);
		ObjectListing objects = getS3Client().listObjects(listObjectRequest);
		return objects.getObjectSummaries().stream().map(summary -> summary.getKey()).collect(Collectors.toList());

	}
	
	static List<String> listAllObjects(String bucketName){
		ObjectListing o =getS3Client().listObjects(bucketName);
		return o.getCommonPrefixes();
	}
	
	static List<String> listAllBucketNames(){
		List<Bucket> buckets = getS3Client().listBuckets();
		return buckets.stream().map(bucket -> bucket.getName()).collect(Collectors.toList());
	}
	
	/**
	 * @param bucket
	 * @param key - A key is the "full path" to the file
	 * @throws IOException 
	 */
	static void readFileAndPrintToConsole(String bucket,String key) throws IOException{
		GetObjectRequest getRequest = new GetObjectRequest(bucket, key);
		S3Object s3object = getS3Client().getObject(getRequest);
		displayTextInputStream(s3object.getObjectContent());
	}
	
	private static String addDelimiterToPrefix(String prefix) {
		String delimiter = "/";
		if (!prefix.endsWith(delimiter)) {
			prefix += delimiter;
		}
		return prefix;
	}

}
