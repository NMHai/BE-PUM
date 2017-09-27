#Introduction

Binary Emulation for Pushdown Model (BE-PUM) is a project currently led by Assoc. Prof. Quan Thanh Tho which aims at generating Pushdown Model on binary code of malwares. This project is a collaboration with Prof. Mizuhito Ogawa in JAIST (Japan Advanced Institute of Science and Technology, Japan).

#Publications

- Minh Hai Nguyen, Thien Binh Nguyen, Thanh Tho Quan and Mizuhito Ogawa (2013), A Hybrid Aproach for Control Flow Graph Construction from Binary Code, In Proceedings of the 20th Asia-Pacific Software Engineering Conference (APSEC 2013), Postgrad Symposium, Thailand

- N. M. Hai, O. Mizuhito, and Q. T. Tho. Pushdown model generation of malware. Technical report, Japan Advanced Institute of Science and Technology, Japan, 2014

- Nguyen Minh Hai, Mizuhito Ogawa, Quan Thanh Tho, “Obfuscation code localization based on CFG generation of malware”, The 8th International Symposium on Foundations & Practice of Security, Springer, Clermont-Ferrand, France, 2015

#Download

If you experience difficulty downloading, do send me an email (nguyenmhai1984@gmail.com) and we shall contact you as soon as possible (within the same day).

Download: please send email to nguyenmhai1984@gmail.com
or https://github.com/SmallSAVE/BE-PUM

#System Requirement

Windows Operating System: Windows XP, Vista, Windows 7 and Windows Server 2000/2003.
Java Runtime Environment 1.7 or higher.

#User Manual

- Download and extract file.

- Open CMD command line, enter the folder where contain BE-PUM.jar file.

- Type the following line:

  - java -jar BE-PUM.jar [BinaryFilePath]
    - Example: java -jar BE-PUM.jar asm/virus.exe

  - Or java -jar BE-PUM.jar -gui (if you want to run BE-PUM with graphic user interface).

- You may encounter the error of short of memory, please type: java -Xmx1024m -jar BE-PUM.jar -gui.
