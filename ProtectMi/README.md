# ProtectMi by Ethan Misa

The application Protect Mi allows the user to generate randomized passwords and save it to a file with corresponding companies. The user can access the passwords at a later date since the passwords are saved to a file.

When you first open the application, you are asked to choose a Master Password. This is the password you will use to access the application in the future. 

You are then taken to a title screen with two buttons Generator and Library:

### Generator
* The generator allows the user to generate randomized passwords with the specific requirements they wish. This includes the desired length, and whether or not to include letters, numbers, and special characters. The user can copy the generated password to their clipboard. 

### Library
* The library allows the user to store passwords along with the correlating company. It uses the read and write function of the file to store it. The user can search for previous companies imported and the application will display the password that relates to it. The user can save a password and then access in the future when they open the application back up. The user can paste passwords from the clipboard, making it easy for them to use the one they generated in the Generator panel.

For the input/output, the application uses a "passwordFile.txt" file. If the file is corrupt and the user would like to reset it, you will need to use a blank text file with the word "MasterPassword" on the first line. Be sure to name it "passwordFile.txt" so that it can be read by the application. If the user wants to edit the master password, or edit/remove any of the password imports, they can do so by editing the "passwordFile.txt" file.

Disclaimer: There is zero security with this application, the passwords are stored on a text file that can easily be accessed by other people. I am not responsible for anything that happens to your accounts. 
