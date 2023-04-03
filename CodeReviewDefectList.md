## Code Review Defect List

**Reviewer**: Keana Gindlesperger

| ID#  | Location (File and Line Number)  | Problem Description | Problem (Category) | Problem (Severity) |
|---|---|---|---|--|
| 1 | Character.java, Line 9 | printInfo() lacks a method banner comment | CG 3 | LOW |  
| 2 | Bard.java, Line 1 | Class lacks a class banner comment | CG 2 | LOW |  
| 3 | GamePlay.java, Line 1 | File lacks a file banner comment | CG 1 | LOW |  
| 4 | GamePlay.java, Line 186 | attack method is not implemented | FD | MJ |  
| 5 | GamePlay.java, Line 126 | If statement utilizes .getClass and .getName | CS 4 | LOW |  
| 6 | Main.java, Line7, Line 20, Line 33 | For statements use the literal number 9 repeteadly, should be coded as a constant | CS 6 | LOW |  
| 7 | BlackBoxGiven.java, Line 49| Reviewing the main branch, only one blackbox test is utilized | FD | MJ |  
| 8 | Charcter.java, Line 2 | Variables after and on this line for the class are all public | CG 5 | MJ |  

**Category**: 
> CS – Code Smell defect. 
> CG – Violation of a coding guideline. Provide the guideline number. 
> FD – Functional defect. Code will not produce the expected result. 
> MD – Miscellaneous defect, for all other defects.

**Severity**:
> BR - Blocker, must be fixed asap. 
> MJ – Major, of high importance but not a Blocker 
> LOW – Low.
