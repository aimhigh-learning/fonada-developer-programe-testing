#Write a JAVA program for following requirement.

##Aplication should create following threads

1. Thread1 which should read record from a csv file having records with following fields and write them into Database table. 

	1. VendorId e.g. 1
	2. TPS e.g. 250

	



base should be sync.



2. Thread2 which should read from a csv file having records with following fields and write them into Database table. 

	1. Number
	2. VendorId
	3. Messag

It should be noted that user can modify csv file from backend and thread should ensure that records in file and Database should be sync.

3. Thread3 which should read record from the table created by Thread1 and create as many threads as there are VendorId. 

Each thread then should read records from table created by Thread2 corresponding to VendorId and write them into another table having following fields. 
It should also flag records which have been read so that those are not read and written again. Writing speed should be as per the TPS for that vendorid.

	1. Number 
	2. VendorId
	3. Message