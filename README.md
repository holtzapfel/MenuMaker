# MenuMaker

Tired of creating and recreating a settings menu?  Quit spending so much time on a menu that, if done correctly, nobody notices.  Allow me to do the hard work for you.  Use **Menu Maker** in your app with ease today!  

You will never have to design a menu again.

#### Screenshots
<img src="https://github.com/holtzapfel/MenuMaker/raw/master/dev/screenshots/device-2017-06-30-112934.png" width="275"><img src="https://github.com/holtzapfel/MenuMaker/raw/master/dev/screenshots/device-2017-06-30-113659.png" width="275"><img src="https://github.com/holtzapfel/MenuMaker/raw/master/dev/screenshots/device-2017-06-30-113808.png" width="275">

#### Some Ideas for Use
- Settings Menu
- Links List
- FAQs
- User Profile Editing
- Development Credits List
- Help Section
- Display Lists of Information
- _... the possibilities are endless!_

#### Features
- Easy to use API
- Create a deep structured menu with multiple pages
- Generate true/false menu items with switches
- Enable an optional Floating Action Button
- Customizable text options
- Add icons for a unique look
- Modify menu items while in use
- Compatible with API level 15 and above

## How to Use MenuMaker
**The best way to discover how to use MM is via the [MenuMaker Wiki](https://github.com/holtzapfel/MenuMaker/wiki).**  
### Just want to get started?
Follow the instructions below.
#### 1. Add Maven Jitpack repository
_Project level build.gradle_
```gradle
allprojects {
    repositories {
        ...
        maven { 
            url "https://jitpack.io" 
        }
    }
}
```
#### 2. Insert gradle dependency
_Module level build.gradle_
```gradle
compile 'com.github.holtzapfel:MenuMaker:1.0.4'
```
#### 2. Extend with **MMActivity**
```java
public class MainActivity extends MMActivity{
...
}
```
Those are the beginning steps.  Please read the [MenuMaker Wiki](https://github.com/holtzapfel/MenuMaker/wiki) for more usage information.  Enjoy!
