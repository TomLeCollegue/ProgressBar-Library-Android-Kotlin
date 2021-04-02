<h1 align="center">Progress Bar Library</h1>
<h3 align="center">Horizontal and Circular Animated progress View for Android</h3>
<p align="center">
  <img alt="exemples" src="https://github.com/TomLeCollegue/ProgressBar-Library-Android-Kotlin/blob/master/images/circle.png?raw=true" /></br>
	
  <img alt="exemples" src="https://github.com/TomLeCollegue/ProgressBar-Library-Android-Kotlin/blob/master/images/horizontalBar.png?raw=true" />
</p>
<h3 align="center">Implementation Exemple</h3>
<p align="center">
  <img alt="exemples" src="https://github.com/TomLeCollegue/ProgressBar-Library-Android-Kotlin/blob/master/images/implementation.gif?raw=true" />
</p>

### `Import the library` ###
[![](https://jitpack.io/v/TomLeCollegue/ProgressBar-Library-Android-Kotlin.svg)](https://jitpack.io/#TomLeCollegue/ProgressBar-Library-Android-Kotlin)

To get the Library into your project, Add jitPack to your build.gradle :
```gradle
allprojects {
	repositories {
		..
		maven { url 'https://jitpack.io' }
	}
}
  
 ```
Then add the dependency : 
```gradle
dependencies {
  implementation 'com.github.TomLeCollegue:ProgressBar-Library-Android-Kotlin:0.1.1'
}
```

### `Using the library` ###
In your layout : 
```xml
<com.tomlecollegue.progressbars.HorizontalProgressView
	android:layout_width="match_parent"
	android:layout_height="20dp"/>

<com.tomlecollegue.progressbars.CircleProgressView
        android:layout_width="250dp"
        android:layout_height="250dp"/>
```

You can add the following attributes in both progressBars:

```xml
<com.tomlecollegue.progressbars.CircleProgressView

	<!-- Change the color of the background stoke -->
	app:colorBackground="@color/light_gray"
	
	<!-- Change the color of the progress stoke -->
        app:colorProgress="@color/light_green"
	
	<!-- Change the init value of the progress -->
	app:defaultValue="69"
	
	<!-- Change the stokes width -->
	app:strokeWidth="16dp"
/>
```

To change the progress State in your kotlin file:
```kt
	val horizontalProgressView = view.findViewById<HorizontalProgressView>(R.id.horizontalProgressView)
	
	// Between 0 and 100 (%)
	horizontalProgressView.progress = 50
	// the transition animation is automatic
```



