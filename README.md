# CS-360-Mobile-Architect-and-Programming

### Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

This is an application which is intended to meet the needs of the user desiring a simple login / registration page, coupled with a SQLite driven collections of events. This app also supports a simple permissions page for allowing a user to subscribe to SMS notifications.

### What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?

The screen simply required the three aforementioned screens for a login, a page displaying results, and a permissions or settings page. The simplicity allowed for a straightforward implementation of the requirements.

### How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?

The successful approach was to break down the features into self-composed activities that leverage the shared storage to interact with data.

### How did you test to ensure your code was functional? Why is this process important and what did it reveal?

Testing for this app was manual testing and QA to validate the project responds as expected.

### Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?

The innovation that allowed for a successful completion, was using the database to share the data, and relying on each activity to focus totally on its intent.

### In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

I believe the most successful implementation was the recycler view which displays the events. By leveraging the recycler view and the ability to notify of data changes, the UI updates as changes to the data within are made.