# HostelHub

## A Modern Android App for Hostel Discovery

### Overview
HostelFinder is a feature-rich Android application designed to help users discover and book hostels in various cities. It leverages modern Android development techniques and libraries to provide a seamless and user-friendly experience.

#### Key Features
Hostel Listing: Displays a list of hostels with essential details like name, rating, price, and images.
Hostel Details: Provides in-depth information about each hostel, including amenities, reviews, and location.

- Image Loading: Efficiently loads and displays high-quality images using Coil.
- Smooth Animations: Enhances user experience with Lottie animations for loading states and transitions.
- Robust Testing: Comprehensive unit, integration, and UI tests ensure app quality and stability.
- 
## Technical Stack
- Android SDK
- Kotlin
- Jetpack Compose
- Coroutines and Flow
- Retrofit
- Dagger-Hilt
- Moshi
- Coil
- Lottie
- Navigation Component
- RxKotlin
- Mockk
- JUnit 5
- Turbine
- Kotlin Serialization

## Project Structure
HostelFinder
```sh
├── app
│   ├── build.gradle.kts
│   ├── src
│   │   ├── androidTest
│   │   │   └── java
│   │   ├── main
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java
│   │   │   │   ├── com.example.hostelfinder
│   │   │   │   │   ├── data
│   │   │   │   │   ├── domain
│   │   │   │   │   └── presentation
│   │   │   ├── res
│   │   │   │   ├── values
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── styles.xml
│   │   │   │   └── kotlin
│   │   ├── test
│   │   │   ├── java
│   ├── gradle.properties
└── build.gradle.kts
```
Testing and Quality Assurance
Unit Tests: Cover core business logic and data models.
Integration Tests: Test interactions between components like repositories, data sources, and view models.
UI Tests: Verify UI behavior and user interactions using tools like Espresso.
