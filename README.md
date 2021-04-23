[![Version](https://img.shields.io/badge/Version-1.0.0-<COLOR>.svg)](https://shields.io/)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Comparing%20Performance%20Bar-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8242)

# ComparingPerformanceBar
Simple Progress View that you can compare things, like statistics of a Football match

<img src="https://github.com/cliffgr/ComparingPerformanceBar/blob/master/art/Image1.png"/>

# Dependency

Add the following to your app module build.gradle file
```gradle
dependencies {
   implementation 'io.github.cliffgr:cpbar:<latest-version-number-here>'
}
```

# Usage

```xml
 <com.cliff.comparingperformancebar.PercentageProgressBar
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:percentage="50"
        app:progressLeftColor="#77B9C1"
        app:progressRightColor="#45718B"
        app:textSize="18sp" />
```

You can set the value programmatically by calling the following method

```java
 val progressBar: PercentageProgressBar = findViewById(R.id.percentageProgressBar)
 progressBar.setProgress(30.0f)
```

# Next Tasks

 * Animations
 * Shadows
 


License
--------


    Copyright 2021 Kostas Antoniou

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
