[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/ecp4su41)
# THIS DOCUMENT IS REQUIRED
## Group Info
Members: Adrian Veto, Junling Zhang

Topic: Code Obfuscation

## Overview
Code Obfuscation is a method of hiding code in such a way that it can't a) be decomplied, or b)is very hard to parse through.
There are also tournaments that are centered around obfuscation, one of which is the International Obfuscated C Code Contest.
There are multiple different ways to obfuscate code: String Encryption, Rename Obfuscation, Aggregation Obfuscation, Storage Obfuscation, Control Obfuscation, and Debug Obfuscation.

- String Encryption - encrypt the string within the code and decrypt at runtime. Additionally programmers can implement Custom Encoding and/or runtime arguments.
  - Custom Encoding involves the programmer creating their own encoder and decoder for private use.
   - Runtime Arguments involves changing the program to require an user provided decryption key at runtime to decode parts of the program

- Rename Obfuscation - rename variables and functions to unrelated words

- Aggregation Obfuscation - Split a single array into smaller subarrays

- Storage Obfuscation - Changes how data is stored in memory(Locally versus Globally)

- Control Obfuscation - Messing with the flow of the code to throw people off track(code that leads to nowhere)

- Debug Obfuscation - Removing Debug lines and preventing error messages from leaking to users

- Data Obfuscation - Disguising confidential and sensitive data and information through various methods like encryption and tokenization

- Address Obfuscation - Randomizes the internal/virtual address of variables between runtimes/executions

It is also possible to combine the different ways to obfuscate code, like using String Encryption and Rename Obfuscation together.
There are programs that are dedicated to help programmers with obfuscating code such as ProGuard, DexGuard, and JavaGuard.
There are also programs that can help with deobfuscation like Threadtear.
Vulnerabilities that already exist within the code does not get removed and it is reccommended to use other security measures in addtion to Code Obfuscation such as Access Control and Encryption.

## Instructions

PRESENTATION.md contains a text document made for presentations.

**Debug.java** - Compile it(through javac), then provide the name/path to a java file(like ***java Debug.java File.java***).
              This will replace the contents within the file to one with the comments removed.
              
    Ex: 
    
    javac Debug.java
    
    java Debug.java File.java

**Rename.java** - Compile it(through javac), then provide the name/path to a java file(like ***java Rename.java File.java***).
              This will replace the contents within the provided the file to one with randomized names for function and variable name.
              
     Ex: 
              
     javac Rename.java
              
     java Rename.java File.java

**string_obfuscation.java** usage
```
  make run ARGS="FILE.java"
```

## Sources
- [What is Obfuscation? - Preemptive](https://www.preemptive.com/what-is-obfuscation/#:~:text=Code%20Obfuscation%20is%20the%20process,the%20output%20of%20the%20program.)
- [What is Obfuscation? - GuardSquare](https://www.guardsquare.com/what-is-code-obfuscation)
- [Code Obfuscation: A comprehensive guide against reverse engineering](https://www.appsealing.com/code-obfuscation/)
- [International Obfuscated C Code Contest](https://www.ioccc.org/)
- [IOCCC 2020: Carlini](https://www.ioccc.org/2020/carlini/index.html)
