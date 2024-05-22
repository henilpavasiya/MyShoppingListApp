# MyShoppingListApp

MyShoppingListApp is an Android application built using Kotlin and Jetpack Compose. The app allows users to create and manage a shopping list, with the additional feature of associating each item with a geographical location using Google Maps and the Geocoding API.

### Features
* Create, Edit, Delete Items: Manage shopping list items with ease.
* Location Integration: Select and associate a location with each shopping item using Google Maps.
* Geocoding: Convert geographical coordinates into human-readable addresses.
* Real-time Location: Get and use the device's current location.
### Prerequisites
* Android Studio installed on your development machine.
* An Android device or emulator for testing.
* A Google Maps API key.
### Installation
1. Clone the repository:
   git clone https://github.com/henilpavasiya/MyShoppingListApp.git   
3. Open the project in Android Studio
4. Add your Google Maps API key Create a local.properties file in the root of your project (if it doesn't exist) and add your API key:
   MAPS_API_KEY=YOUR_API_KEY_HERE   
7. Build and run the project Connect your Android device or start an emulator, then build and run the project through Android Studio.
## Usage
### Main Components
1. GeocodingApiService
    * Defines the Retrofit API service for geocoding.
2. LocationData and GeocodingResponse
    * Data classes representing location data and the geocoding API response.
3. LocationSelectionScreen
    * A composable function that displays a Google Map for location selection.
4. LocationUtils
    * Handles location permissions and location updates.
5. LocationViewModel
    * Manages location and address data.
6. MainActivity
    * Sets up the navigation and content for the app.
7. RetrofitClient
    * Creates a Retrofit client for the geocoding API.
8. ShoppingListApp
    * Displays the main UI for the shopping list.

## Navigation
The app uses Jetpack Navigation for navigating between the shopping list screen and the location selection screen.

## Geocoding
The app uses the Google Geocoding API to convert latitude and longitude into a human-readable address. Ensure you have enabled the Geocoding API in your Google Cloud Console and have a valid API key.

* Google Maps API
* Retrofit
* Jetpack Compose
* Android Jetpack Libraries
