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