"# GET-Method-Sample" 

First, make sure you have the necessary permissions in your AndroidManifest.xml file:
<uses-permission android:name="android.permission.INTERNET" />

Next, add the Volley dependency to your app-level build.gradle file:
implementation 'com.android.volley:volley:1.2.1'

Add the following attribute to the <application> element:
android:usesCleartextTraffic="true"

created a layout file named item_fitness_data.xml in the res/layout directory with the following content:

