# Demo using MVI Repository Pattern

1. Retrieve data from an api with [Retrofit](https://square.github.io/retrofit/).
2. Cache data with [Room](https://developer.android.com/topic/libraries/architecture/room).
3. Display data in UI.

### Design Pattern
The project used MVI and Repository design pattern approach. State in app is defined by user's action which is called intent _(not the android Intent class)_ which the ViewModel will get and decide the state to be reflected to the View.

#### Libraries
* [Hilt](https://dagger.dev/hilt/) - Dependency injection
* [Room](https://developer.android.com/jetpack/androidx/releases/room) - Caching mechanism.
* [Retrofit](https://square.github.io/retrofit/) - API http network requests.
* [OkHttp](https://square.github.io/okhttp/) - Use as http client for logging interceptor.
* [Moshi](https://github.com/square/moshi) - Smaller and faster JSON serialization.
* [Navigation Component](https://developer.android.com/guide/navigation) - For screen navigation.
* [Timber](https://github.com/JakeWharton/timber) - Logging and crash reports.
* [Glide](https://github.com/bumptech/glide) - Image loader to views.
* [Material Design](https://material.io/) - Google's material design ui.

#### Setup
Run the following command to build the project
```
./gradlew build
```

#### Installation
Installing apk to device can be done with the following commands. Note that debug apk is used in the commands.
- via Gradle
```
./gradlew installDebug
```
- via adb tool
```
adb install build/outputs/apk/debug/app-debug.apk
```

#### Linting
Lint issues or warnings can be checked by running
```
./gradlew lint
```

#### JSON
```yaml
[
  {
    "id": 0,
    "title": "Iron Man",
    "body": "Inventor Tony Stark applies his genius for high-tech solutions to problems as Iron Man, the armored Avenger.",
    "image": "https://terrigen-cdn-dev.marvel.com/content/prod/1x/002irm_ons_crd_03.jpg",
    "category": "main"
  },
  {
      "id": 1,
      "title": "Captain America",
      "body": "America’s World War II Super-Soldier continues his fight in the present as an Avenger and untiring sentinel of liberty.",
      "image": "https://terrigen-cdn-dev.marvel.com/content/prod/1x/003cap_ons_crd_03.jpg",
      "category": "main"
  }
]
```
