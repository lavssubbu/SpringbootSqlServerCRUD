Add JDBC Driver to Project:

Go to File > Project Structure > Libraries.
Click the + icon to add a new library.
Select Java and navigate to your JDBC driver JAR file (like sqljdbc.jar).
Configure Module Dependencies:

After adding the library, ensure it is linked to your module by checking Modules under Project Structure.
Once you have added the library and configured the VM options, try running your application again, and it should recognize the JDBC driver and its associated DLL files.
