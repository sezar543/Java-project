# Inventory of Store

### Screenshot

![Inventory - screenshot](screenshots/screenshot1.JPG)

### Requirements 🔧
* Java version 8 or higher.

### Installation 🔌
1. Press the **Fork** button (top right the page) to save copy of this project on your account.

2. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:

       git clone https://github.com/HouariZegai/Calculator.git
3. Imported it in Intellij IDEA or any other Java IDE.
4. Run the application :D
---------------------------------------------
## Management of Inventory

The application will manage inventory of a store. It will be used by the manager of store. This application will consist of some features to better manage and organize a store's inventory such as:

- Keep track of quantity for each item.
- Keep track of percentage discount for each item The application will apply discount on items that are not sold enough number of them within certain number of days.

<em>The following features, and possibly other features, will be implemented in future for the next phases.  
- If the quantity of an item reaches to a minimum, the application will notify manager to source of that item (before it is entirely sold out)   
- If an item is not sold enough after certain number of days, it will notify manage that some discount will be applied to that item and the manager can approve or disapprove it.
 when, how and how much to apply discount on products, when to source supply for an item before the store is entirely short on it.</em>

## User Stories

- The user can enter the data for each new item in the inventory.
- The user can remove an item from the inventory.
- The user can check the quantity of each item.
- The user can check a list of all the discounts applied on items.
- The user can change the data for each item such as quantity, the discount amount applied,....
- The user can source and increase the quantity of items.
- The user can enter the quantity sold for each item. This will be done manually for this application but in real world, this can be done using other applications that enters the data and updates the inventory once an item is sold.
- When the user selects the quit option from the application menu, he is reminded to save his inventory to a file and have the option to do so or not.
- When the user starts the application, he is given the option to reload his inventory from a file.

Note that in this project, we control the concept of time manually and can move time to the next day because for the purpose of testing, it is not practical to wait few days and see how the discount will be applied on items.

P.S. Part of the codes of this project are taken with changes from the project **FitLifeGymChain**.

## Phase 4: Task 2

The events "loading from file", "removing an item", "adding an item" and "saving to file" are correctly displayed. For the event "loading from file", all items are also added to the file and are printed on the console too.

Fri Apr 01 14:05:23 PDT 2022 

The item with id 17 is added to the inventory Custom JTable .

Fri Apr 01 14:05:23 PDT 2022

The item with id 5 is added to the inventory Custom JTable .

Fri Apr 01 14:05:23 PDT 2022

The item with id 6 is added to the inventory Custom JTable .

Fri Apr 01 14:05:23 PDT 2022

The item with id 1 is added to the inventory Custom JTable .

Fri Apr 01 14:05:23 PDT 2022

The item with id 22 is added to the inventory Custom JTable .

Fri Apr 01 14:05:23 PDT 2022

The item with id 3 is added to the inventory Custom JTable .

Fri Apr 01 14:05:23 PDT 2022

The inventory is loaded from the file./data/inventory.json.

Fri Apr 01 14:05:48 PDT 2022

The item with id 2 is added to the inventory Custom JTable .

Fri Apr 01 14:05:54 PDT 2022

The inventory is saved to the file./data/inventory.json . 

##  Phase 4: Task 3

I do not find any coupling that can be improved.

## Citation

Citation: The persistence package is modeled from the JsonSerializationMethod.
