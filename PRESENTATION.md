# Code Obfuscation

## Overview
Code obfuscation is a system in which normal, human-readable code is made more difficult to understand. The code should still function in exactly the same way pre- and post-obfuscation, but obfuscation should make code significantly harder to read.

There are multiple reasons to obfuscate code:
- Protect intellectual property: in the case of open-source code and projects, people might want to conceal their ideas to prevent them from being stolen and incorporated into other projects. To do this, code is obfuscated so that the ideas can't be copied. For instance, if a programmer wanted to publish a password cracker but didn't want their software to be stolen, they could run it through an obfuscator so that nobody could understand the process by which they go about password cracking.
- Hide malicious code: Hackers and cyber criminals use code obfuscation to make malicious code seem harmless or useless. They hide their viruses and malware in seemingly passive code so that malware detection devices don't detect it.
- For fun!: People obfuscate code for their own amusement or to make a sort of puzzle for how to crack the obfuscated code. There are even obfuscation contests where participants try to make creative projects that are impossible to understand. We'll talk a little more about this later!

## Types of Obfuscation
There are a lot of different types of code obfuscation, all of which have different effects on code that make it hard to read and understand. When all the types of obfuscation are put together, they can create a nearly impossible, uncrackable program. For ease of understanding, let's look at each one individually before putting them together.

### Rename Obfuscation
Rename obfuscation is probably the simplest and most understandable of any obfuscation method. The idea is simple: you rename all of the variables and functions that you create in a code method to be meaningless strings or even letters.

For example, look at the below lines of code (in C#, but the principle applies to anything):
```
// Pre obfuscation
private void CalcPayroll(SpecialList employeeGroup) {
    while (employeeGroup.HasMore()) {
        employee = employeeGroup.GetNext(true);
        employee.UpdateSalary();
        DistributeCheck(employee);
    }
}

// Post obfuscation
private void a(a b) {
    while (b.a()) {
        a = b.a(true);
        a.a();
        a(a);
    }
}
```
Because functions can have multiple forms, code can be even more confusing. If you just had the second form of the code, it would be really really hard to figure out how anything worked.

### Debug Obfuscation
Debug obfuscation is another pretty simple obfuscation method. It just removes all comments and debugging functions from the code. This is pretty effective (imagine reading over your code without any comments), but it's almost necessary when coupled with any other obfuscation method. No matter how obscure your code is, if it's written over with comments and debugging information you can figure out what it means.

It's hard to demonstrate debug obfuscation on a small scale, but just imagine one of your whole code files with no comments. Tough.

Small-scale example:
Before obfuscation
```
// function for multiplying two integers
// input: two integers
// returns: one integer
private int multiplier(int a, int b) {
    // multiplies the two integers
    return a * b;
}
```

After obfuscation
```
private int multiplier(int a, int b) {
    return a * b;
}
```

### Control Flow Obfuscation
Control flow obfuscation is where obfuscation starts to get a little trickier. There are limitless options for *how* to use control flow obfuscation in code, which makes it really hard to detect and reverse engineer. Control flow obfuscation is essentially messing up the logic of your code so that it's harder to read.

Here are some of the more common ways to accomplish that:
- **Dead ends**: creating bunches of code that do nothing and just fill up space in a program. This makes a potential deobfuscater have to go through many more lines of code before realizing that it's all useless and doesn't actually make changes to the program.
- **Splitting up code**: Taking functions and processes in code and splitting them up among other, smaller functions. This changes the flow of your code and makes it harder to follow where every part of the code leads.
- **Adding conditions**: Adds more parts to conditional statements to make them harder to understand. Involves statements of the form "and TRUE" and "or FALSE", where TRUE and FALSE are complex logical statements that don't depend on the rest of the code.
- **Complex looping structures**: Instead of simple `for` or `while` loops, control flow obfuscation usually makes much more complex looping structures, including nesting loops and conditional statements to make code much harder to read.

### Aggregation Obfuscation
Aggregation obfuscation is an obfuscation method that relies on breaking up arrays and data structures to store their data in separate places. This makes code more confusing and much harder to read.

As a simple example, look at the code below. Pre-obfuscation, there's one array of numbers to loop through. Post-obfuscation, there are three nested arrays. The code has the same effect, but it's harder to read. If the same process was carried out on larger datasets across more code, it would be much harder to understand.
```
array = [1, 2, 3, 4, 5, 6, 7, 8, 9]
for i in array:
    print(i)
```

*Through the obfuscator*

```
array = [[1, 2], [3, 4, 5], [6, 7], [8, 9]]
for i in array:
    for j in i:
        print(j)
```

When coupled with a bunch of other obfuscation methods, aggregation obfuscation makes it harder to work with data structures and deobfuscate code.