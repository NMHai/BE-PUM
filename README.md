# Introduction

Binary Emulation for Pushdown Model (BE-PUM) is a project currently led by Assoc. Prof. Quan Thanh Tho which aims at generating Pushdown Model on binary code of malwares. This project is a collaboration with Prof. Mizuhito Ogawa in JAIST (Japan Advanced Institute of Science and Technology, Japan). BE-PUM is a tool that generates a precise control fow graph (CFG) , as well as precise disassembly of x86 binary code. It can handle typical obfuscation techniques of malware, e.g., indirect jump, self-modification, overlapping instructions, and structured exception handler (SEH), which cover obfuscation techniques introduced by a packer. BE-PUM generates a model of binary code in an on-the-fly manner, following to the execution paths. The concolic testing is used at each step for extending a model.

# Publications

- Minh Hai Nguyen, Thien Binh Nguyen, Thanh Tho Quan and Mizuhito Ogawa (2013), A Hybrid Aproach for Control Flow Graph Construction from Binary Code, In Proceedings of the 20th Asia-Pacific Software Engineering Conference (APSEC 2013), Postgrad Symposium, Thailand

- N. M. Hai, O. Mizuhito, and Q. T. Tho. Pushdown model generation of malware. Technical report, Japan Advanced Institute of Science and Technology, Japan, 2014

- Nguyen Minh Hai, Mizuhito Ogawa, Quan Thanh Tho, Obfuscation code localization based on CFG generation of malware, The 8th International Symposium on Foundations & Practice of Security, Springer, Clermont-Ferrand, France, 2015

- Nguyen Minh Hai, Le Nguyen Dung and Quan Thanh Tho, Applying Symbolic Execution for Malware Analysis, The 2nd Symposium on Information Security (SOIS 2017), 02-03 December 2017, University of Information Technology Ho Chi Minh City, Vietnam (in Vietnamese).

# Download

If you experience difficulty downloading, do send me an email (nguyenmhai1984@gmail.com) and we shall contact you as soon as possible (within the same day).

Download: please send email to nguyenmhai1984@gmail.com

# System Requirement

Windows Operating System 32 bit: Windows XP, Vista, Windows 7 and Windows Server 2000/2003.
Java Runtime Environment 1.7 or higher.

# User Manual

- Clone the repository.


- Type the following line:

  - java -jar BE-PUM.jar [BinaryFilePath]
    - Example: java -jar BE-PUM.jar asm/virus.exe
	
  - Or java -jar BE-PUM.jar -adjmatrix [BinaryFilePath] (if you want to extract the image of malware).

