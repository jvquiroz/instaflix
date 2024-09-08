# Movie and TV Show App

This project is an Android app designed to showcase information about movies and TV shows using The Movie Database (TMDB) as the data source. It was created as part of a coding challenge for a senior Android developer position.

## Features

- **Main Navigation Drawer:** Allows users to select between Movies and TV Shows.
- **Bottom Tab Bar Navigation:** Provides additional navigation within each category, allowing users to browse different types of categories.
- **Offline Support:** In case of no internet connection or failure to retrieve data from the server, the app will attempt to load data from local cache and inform the user of this situation.
- **Load More:** Users can load additional movies and TV shows with a "Load More" button at the end of the list.

## Architecture

The project follows Google's recommended architecture with three layers:

- **UI Layer:** Handles the user interface using Jetpack Compose.
- **Domain Layer:** Contains model definitions (no use cases included).
- **Data Layer:** Manages data operations, including networking and local caching.

### Technology Stack

- **Networking:** Ktor client
- **Image Loading:** Coil
- **Dependency Injection:** Hilt
- **Local Caching:** Room
- **Architecture Pattern:** MVVM
- **UI Framework:** Jetpack Compose
- **Kotlin Version:** 2.0
- **Android Studio Version:** Koala patch 2

## Directory Structure
The app is organized into the following layers:

- **UI**: Contains all the composables, views, and navigation logic.
- **Domain**: Defines the models used across the app.
- **Data**: Manages data sources including local (Room) and remote (Ktor).

```bash
com.instaleap.instaflix
├── data
│   ├── local
│   ├── remote
│   └── repository
├── domain
│   └── model
└── ui
    ├── components
    ├── navigation
    └── theme

```

**Download the APK:** [Instaflix APK](https://github.com/jvquiroz/instaflix/blob/05c89d1da505ef14e4c9e2ac877f51fb5f778c0a/instaflix.apk)

## Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/your-repository.git
   ```
2. **Open the Project**
   
   The project was developed using Android Studio Koala 2024.1.1 Patch 2.
4. **Add TMDB API Key**
   Create a file named apikeys.properties in the root directory of the project and add your TMDB API key as follows:
   ```bash
   TMDB_KEY=your_api_key_here
   ```
## License
This project is licensed under the MIT License - see the LICENSE file for details.

