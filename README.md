# AndroidFragment
https://github.com/triveous/vodafone

AndroidTraining

Android Training programe

Ninja Mock
Wear
TV
butterknief
Android TV
Glass
Change Scrum Application Version Android 23 API to Lower Version
SVG
Vysar Chrome Plug in
Sketch Tool (MAC), Adobe comets,
pipete studio
pop prototyping -zeplin
invensionapp - prototype
marvelapp
RX -W/S call in loop
Retrofit - -W/S call in loop
Picaso android api
Glide
REcycler view
CenterCrap with percentage view
keypromoter

Step to install Git hub install git hub desktop from net i.e. https://desktop.github.com
---Git Hub C:\Users\admin\AppData\Local\GitHub\PortableGit_624c8416ee51e205b3f892d1d904e06e6f3c57c8\cmd\git.exe init

inside folder where all source code is kept C:\Users\admin\AppData\Local\GitHub\PortableGit_624c8416ee51e205b3f892d1d904e06e6f3c57c8\cmd\git.exe add . C:\Users\admin\AppData\Local\GitHub\PortableGit_624c8416ee51e205b3f892d1d904e06e6f3c57c8\cmd\git.exe commit -m "first commit" C:\Users\admin\AppData\Local\GitHub\PortableGit_624c8416ee51e205b3f892d1d904e06e6f3c57c8\cmd\git.exe remote add origin https://github.com/shdhumale/AndroidTraining.git C:\Users\admin\AppData\Local\GitHub\PortableGit_624c8416ee51e205b3f892d1d904e06e6f3c57c8\cmd\git.exe push -u origin master

Universal Image Loader

google tool box
newsapi.org
https://github.com/triveous/vodafone
https://gist.github.com/s0h4m/00da1fac9059bf405e2b33e40c44e8e4
1- Create an adapter 2- Create viewHolder inside adapter 3- make the adapter of type viewholder 4- create the constructor and the local list 5- implement the 3 methods 6- use glide in onBindViewHolder (add dependency) 7- in MainActivity add a layout mananger for the recycleView 8- set adapter to teh RecycleView 9- add internet permission to the manifest

Adding handler to card 1- Create Activity 2- Override onCreate 3- Design Layout with WebView 4- Mention in menifest 5- use setcontentview in DetailsActivity 6- add id to cardview 7- update adapter: viewholder 8- add an onclicklistener in onBindViewHolder 9.show toast with position
things to do

In the details activity get the position from the intent Based on the position get the NewsObject at that position Based on the news object a) change the title of the activity b) load the url (you have to initialize the webview) Create a horizontal progress bar in the details activity Show/Hide the progress bar when the page starts loading/finishes loading Implement "up functionality" in your details activity so that when you click on "up", you go back to the previous screen

------Day 3 1- Get the response from newsapi 2- Make sure it is valid (jsonparseronline or other parsing tools): http://www.jsoneditoronline.org/ 3- Go to jsonschematopojo: http://www.jsonschema2pojo.org/ 4- Paste the response (valid) 5- Change the source type to json 6- Give the correct packagename 7- Give the correct class name (any class name) 8- Change the annotation style to gson 9- Download and extract the zip file 9- Copy the files to the object package 10- Include the gson dependency: compile 'com.google.code.gson:gson:2.7' 11- Remove the generated annotation as well as the generated import to remove all errors

http://square.github.io/retrofit/ https://android-arsenal.com/

--- Day 3 retrofit 1-Add the following to build.gradle

    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
2-Create a networking package 3-Create a NewsAPI class inside the networking package 4-Define the API_KEY and BASE_URL as constants 5-Define an interface: NewsAPIInterface 6-Create a method for each endpoint, pass source and sortBy as query parameters 7-Annotate it with GET with the particular endpoint url along with the KEY 8-define a static NewsAPIInterface object 9-Create a static getNewsAPI method which will help us in accessing the singleton 10-in case the NewsAPIInterface is null, create an instance of Retrofit with a) the basurl b) the gson converter 11-create an instance of the newsapiinterface 12-In MainActivity, use NewsAPI.getNewsAPI().getArticles to get Call 13-use call.enqueue to get a callback for the network response 14-in the onResponse callback, use response.body() to get the NewsApiArticleResponse object 15-Use breakpoints/debugging to verify the response

----Day 3 Retrofit

1- Remove NewsObjects 2- Create CommonStuff.java and add a static List there, create getters and setters public class CommonStuff { public static List

articles;
  public static List<Article> getArticles() {
      return articles;
  }

  public static void setArticles(List<Article> articles) {
      CommonStuff.articles = articles;
  }
}

3- Update Adapter to reflect the following: - update the list from NewsObjects to Article - update constructor - update all the places where we access the NewsObject 4- When a response comes in MainActivity, do the following - store it in CommonStuff - create the adapter with the List we receive from the response like so:

  NewsApiArticleResponse newsApiArticleResponse = response.body();
  CommonStuff.setArticles(newsApiArticleResponse.getArticles());
  NewsAdapter newsAdapter = new NewsAdapter(newsApiArticleResponse.getArticles());
10- load the recyclerview like before 11- show the progressbar before the loading begins 12- hide it once the response is received 13- Update DetailsActivity like so: 14- get the List instead of List from CommonStuff 15- update NewsObjects references to Article

Day-3-Retrofit - 
Source-Article-All Applications 1-Create a new Activity, call it SourcesActivity (Use New>Activity>Empty Activity)

2-Also give change its intent-filter to Main and Launcher, so that it launches when the app is opened 

3-Write the retrofit API definition for the sources endpoint, which includes -getting a sample json response from newsapi.org -validating it in jsoneditoronline -creating the object (described before) in jsonshcematopojo -import the objects in our app -write a new method in our NewsAPI interface like so:  @GET("sources") Call<NewsApiSourcesResponse> getSources(); 
4-Add support for storing and retrieving the Sources in CommonStuff
```
     public static List<Source> sources;
     public static List<Source> getSources() {
         return sources;
     }
     public static void setSources(List<Source> sources) {
         CommonStuff.sources = sources;
     }
```     
5-Create "SourceAdapter" for our SourceActivity -create a view for our sources (image and name)
  -create the corresponding viewholders -create the List in the class along with the constructor
  -implement all the methods (onBindViewHolder, onCreateViewHolder(ViewGroup parent, int viewType), getItemCount()) like before 
  -once you click on the linearlayout (the root view for every item), launch MainActivity, make sure to pass the position of the item clicked
6-Create a layout for MainActivity which will have a progressbar and recyclerview like mainactivity 
7-MainActivity will make a retrofit network call for all the sources -once you receive the response, create the adapter and populate the recyclerview -also store the source list in CommonStuff
8-In MainActivity -retrieve the position passed from the SourceAdapter -retrieve the Source at that position from CommonStuff -update the original NewsAPI article call with the name and sortBy information from that source like so: 

```
Call responseCall = NewsAPI.getNewsAPI().getArticles(source.getId(), source.getSortBysAvailable().get(0));
```

9-This is because each source only supports a certain type of sortBy functionality and we just choose the first one
