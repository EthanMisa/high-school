# ProtectMi by Ethan Misa

ProtectMi is a simple application that helps users generate randomized passwords and save them alongside the corresponding company names for future reference. Passwords are stored in a plain text file for easy access later.

### Generator
The Generator allows you to create randomized passwords based on your specific requirements. You can set the desired password length and choose whether to include letters, numbers, and special characters. Once generated, the password can be copied to your clipboard for easy use in other applications.

### Library
The Library enables you to store and retrieve passwords linked to specific companies. Using a simple file-based storage system, you can save passwords along with their associated company names. The application allows you to search for a company and display the corresponding password. You can paste passwords directly from the clipboard, making it easy to save ones generated in the Generator. All saved passwords remain accessible even after closing and reopening the application.

### How to run
Ensure you have both `java` and `javac` installed. Here are various commands.
```bash
# need to be within the src directory
cd src

# compile the two source files
javac Generator.java
javac ProtectMi.java 

# execute the program
java ProtectMi.java

# to reset the passwordFile.txt
echo "MasterPassword" > passwordFile.txt
```

⚠️ Disclaimer: This application provides no encryption or advanced security. Passwords are stored in a plain text file, making them accessible to anyone with access to the file. Use this application at your own risk. The author is not responsible for any issues that arise from using this tool.
