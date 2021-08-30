[![Version](https://img.shields.io/badge/Version-1.1.3-<COLOR>.svg)](https://shields.io/)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Comparing%20Performance%20Bar-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8242)

# ComparingPerformanceBar
Simple Progress View that you can compare things, like statistics of a Football match

![image](https://user-images.githubusercontent.com/4539769/119251900-c134ca80-bbb1-11eb-8f81-ed4276dd8040.png)


# Dependency

Add the following to your app module build.gradle file
```gradle
dependencies {
   implementation 'io.github.cliffgr:cpbar:<latest-version-number-here>'
}
```

# Usage

Percentage

```xml
 <com.cliff.comparingperformancebar.PercentageProgressBar
        android:id="@+id/percentageProgressBar"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:ppb_percentage="50"
        app:ppb_progressLeftColor="#77B9C1"
        app:ppb_progressRightColor="#45718B"
        app:ppb_textSize="18sp" />
```



You can set the value programmatically by calling the following method

```kotlin
 val progressBar: PercentageProgressBar = findViewById(R.id.percentageProgressBar)
 progressBar.setProgress(30.0f)
```

Value

```xml
  <com.cliff.comparingperformancebar.ValueProgressBar
        android:id="@+id/valuesProgressBar"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/percentageProgressBar"
        app:vpb_progressLeftColor="#77B9C1"
        app:vpb_progressRightColor="#45718B"
        app:vpb_percent="false"
        app:vpb_textSize="18sp"
        app:vpb_valueLeft="6"
        app:vpb_valueRight="10" />
```

You can set the value programmatically by calling the following method

```kotlin
  val valueProgressBar: ValueProgressBar = view.findViewById(R.id.valuesProgressBar)
  valueProgressBar.setValues(10.0f, 7.0f)
```

Value Percent

```text
  <!-- just set this attribute to true -->
  <com.cliff.comparingperformancebar.ValueProgressBar
        ...
        app:vpb_percent="true"
        ... />
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
