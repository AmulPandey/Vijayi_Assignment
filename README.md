# Vijay Assignment

## Overview

This is an Android application developed as part of the "Vijay Assignment" project. The app showcases the use of Jetpack Compose for UI building, Hilt for Dependency Injection, and integration with a backend to display movie and TV show information. The project demonstrates a solid understanding of Android app development with modern technologies.

## Features

- **Home Screen**: Displays a list of movies and TV shows with a toggle to switch between them. Each item is clickable, leading to a detailed screen with more information.
- **Detail Screen**: Provides detailed information about each movie or TV show, such as title, description, release date, and source.
- **Shimmer Effect**: A loading animation for a smooth user experience while the app fetches data.
- **Navigation**: Uses Jetpack Navigation for seamless navigation between screens.
- **Hilt Dependency Injection**: The app is set up with Hilt for dependency injection to manage dependencies efficiently.
- **Data Fetching**: Integrates with a backend API to fetch movie and TV show details.
  
## Setup and Installation

### Challenges Faced

**Implementing Shimmer Effect**: Initially, I faced some issues with implementing a shimmer effect, but after experimenting with various approaches, I was able to make it work smoothly with Jetpack Compose.
**Navigation**: Understanding the new way of handling navigation in Jetpack Compose was a bit challenging at first. However, once I got the hang of it, the Navigation Component turned out to be intuitive.
**Data Handling with Hilt**: Setting up Hilt for Dependency Injection in the project was a new experience, and I had to spend some time understanding the proper configuration and annotations.

### Assumptions Made
**The project assumes that the API being used provides the necessary data in the expected format**.
**Internet connectivity is required to fetch the movie and TV show data from the API**.

### Steps to Run the App

1. Clone the repository:

   ```bash
   git clone https://github.com/AmulPandey/Vijayi_Assignment.git
