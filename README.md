# SimpleAWSClientForS3
Simple S3 Client for testing list "directories" and reading files inside a given "directory"

Required Software : Maven 3.0+ and JDK 8

Sample output:

----------------------- PRINT ALL BUCKETS ------------------------
vinothtest
----------------------- PRINT ALL KEYS  ------------------------
test_file.txt
version1.7/
version1.7/manual_1.7.txt
version1.7/setup_doc_1.7.txt
version1.8/
version1.8/manual_1.8.txt
version1.8/setup_doc_1.8.txt
----------------------- PRINT ALL FILES IN A GIVEN FOLDER ------------------------
version1.8/
version1.8/manual_1.8.txt
version1.8/setup_doc_1.8.txt
----------------------- PRINT THE CONTENTS OF A PARTICULAR KEY (FILE) ------------------------
This is manual version 1.7

