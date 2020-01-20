# Flix
Flix is an app that allows users to browse movies that are currently playing from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).<br>
This app was built into 2 parts outlined below.

#### Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android
- [YouTube Android Player](https://developers.google.com/youtube/android/player/downloads/) - Loading and playing YouTube videos

#### APIs used:
- [The Movie Database API](http://docs.themoviedb.apiary.io/#) - Used for obtaining a list of movies currently playing in theaters
- [YouTube Data API v3](https://developers.google.com/youtube/v3/getting-started?hl=en_US) - Used for retrieving YouTube videos (trailers) to be played in app

## Flix Part 2
Submitted by: **Michael Lofton**<br>
Time spent: **5** hours spent in total

### User Stories

#### REQUIRED (10pts)

- [X] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [X] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS

- [ ] Trailers for popular movies are played automatically when the movie is selected (1 point).
  - [ ] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - [ ] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [ ] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [ ] Apply the popular ButterKnife annotation library to reduce view boilerplate. (1 point)
- [ ] Add a rounded corners for the images using the Glide transformations. (1 point)

#### CUSTOM BONUS
The following additional features are implemented:<br>
* Features:
    - [X] Added "Release Date" for a movie in the detail activity
* Structure & Best Practices:
    - [X] Followed convention/best practices by organizing the codebase's packages into separate categories 
* Error Handling:
    - [X] Added a custom toast message used to notify the user if the app fails to load the trailer for a selected film.
* UI:
    - [X] Matched the detail activity's UI with the theme of the app<br>
    - [X] Introduced padding to the detail activity for a selected movie.
    - [X] Changed the rating system to use 5 stars to be more user friendly.
    - [X] Added shared element activity transition with the title and movie overview between activities.
    

### App Walkthough GIF

Portrait                   |  Landscape
:-------------------------:|:-------------------------:
![Portrait](walkthrough/part2/PortraitWalkthrough.gif)  |  ![Landscape](walkthrough/part2/LandscapeWalkthrough.gif))
Gifs created with [Licecap](https://www.cockos.com/licecap/)

### Notes

Describe any challenges encountered while building the app.

---
## Flix Part 1
Submitted by: **Michael Lofton**
Time spent: **8** hours spent in total

### User Stories
#### REQUIRED (10pts)
- [X] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [X] (2pts) Views should be responsive for both landscape/portrait mode.
   - [X] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [X] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [X] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [X] (2pts) Improved the user interface by experimenting with styling and coloring.
- [ ] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF
Portrait                   |  Landscape
:-------------------------:|:-------------------------:
![Portrait](walkthrough/part1/PortraitWalkthrough.gif)  |  ![Landscape](walkthrough/part1/LandscapeWalkthrough.gif))
Gifs created with [Licecap](https://www.cockos.com/licecap/)

### Notes
Challenges:
 - It was tricky getting the placeholder to align properly to the image sizes. I used 2 different drawables (one for each orientation) and resized them using [this online tool](https://onlinepngtools.com/resize-png) so they would fit better. I tried to use glide's fitCenter and centerCrop method to have it scale (and crop if necessary) to fit the dimensions of the movie posters but all combinatinos of using those methods cut or distort the retrieved poster image. In the future, I think I could look into those functions further, or other image processing methods from the Glide library.
 
Placeholder:
- The placecholders images I used were from this website: https://www.iconfinder.com/. This work is licensed under a [Creative Commons Attribution 3.0 Unsupported License](http://creativecommons.org/licenses/by/3.0/)

UI Improvements:
- Bolded the title of each movie for better element distinction
- Added padding for each movie (row) for better separation
- Created primary and secondary colors for more interesting contrast and improved user experience
