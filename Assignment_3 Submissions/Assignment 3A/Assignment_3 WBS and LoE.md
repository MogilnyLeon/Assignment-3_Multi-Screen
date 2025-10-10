# Assignment 3 WBS and LoE		
## TOTAL ESTIMATE: 5h 40min     	ACTUAL: ***7h 20min***
## Task 1: Project Preparation			
### LoE (estimated): ***1h***             LoE (actual): ***1h 15min***
	1.1 Work Breakdown Structure		15 min			15 min
	1.2 Level of Effort estimations		10 min			10 min
	1.3 Gantt chart				        15 min			25 min
	1.4 ReadMe.md				        10 min			10 min
	1.5 GitHub Repository set		    10 min			15 min
## Notes on Actuals:
	nothing unusual for actuals other than the extra time taken on the Gantt chart because I planned the chart on the wrong tim scale
	(Project scale rather than LoE scale, which would have worked for a phase-based WBS, but not for a deliverables-based WBS)
--------------------------------------------------------
## Task 2: Data Storing Wrapper			
### LoE (estimated): ***30 min***         LoE (actual):		***2h 50min***
	2.1: Create implementation		    10 min			2h 30min
	2.2: Delete implementation		    10 min			10 min
	2.3: Update implementation		    10 min			10 min
## Notes on Actuals:
	Important to know that I actually implemented a real ViewModel to the project, which increased my project load significantly,
	especially for the content creation implementation due to an incredibly difficult time trying to implement a url validator for images.
	I also had to learn how to "share" a ViewModel across multiple screens without loosing data, which I figured out by a creating a common root in the navigation BackStack.
--------------------------------------------------------
## Task 3: Initial UI implementation
### LoE (estimated): ***40 min***         LoE (actual): ***50 min***
	2.4: Setting Routes			        20 min			20 min
	2.5: Creating a Shared Layout		20 min			30 min
## Notes on Actuals:
	The LoE seems to be well in range of the estimate, the increase being warranted due to attempts at polishing the shared layout.
--------------------------------------------------------
## Task 4: Content Creation Screen
### LoE (estimated): ***1h 20min***       LoE (actual): ***45 min***
	3.1: Screen Layout			        20 min			15 min
	3.2: Content creation form		    1h				30 min
## Notes on Actuals:
	It took me less time to implement the creation form because most of that workload was transferred to the ViewModel.
--------------------------------------------------------
## Task 5: Content Listing Screen
### LoE (estimated): ***1h 5min***        LoE (actual): ***30 min***
	4.1: Screen Layout			        20 min			10 min
	4.2: Content List display		    45 min			20 min
## Notes on Actuals:
	Again, I overestimated the time it took me to create a simple list display of items with a delete functionality.
--------------------------------------------------------
## Task 6: Content Details Screen
### LoE (estimated): ***1h 5min***        LoE (actual): ***1h 10min***
	5.1: Screen Layout			        20 min			10 min
	5.2: Content Details card		    45 min			1h
## Notes on Actuals:
	I actually was quite on target for my estimate for the details screen, mainly because of the image display,
	which had to be fetched from a URL, requiring an external library to handle it: Coil.
	
	See references in the section below for the Coil library
## References:
- [Codelab exercise](https://developer.android.com/codelabs/basic-android-kotlin-compose-load-images#0)