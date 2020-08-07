# PayDayBank
PayDay Bank is a tiny MVP banking mobile application, which enables users to view monthly expenditures, incomes and transactions. It is far from being a complete banking solution in its current state, but the adapted architecture was implemented with the question of scalability, testablity and maintainablity in mind.

 ![](https://github.com/kemalatli/PayDayBank/blob/main/art/day.jpg)
  ![](https://github.com/kemalatli/PayDayBank/blob/main/art/night.j##pg)
  
## Architecture
Pay Day App tries to follow MVVM architecture pattern. The app considers local database as the single source of truth, which adds offline capabilities to the app. In case of low and no connection states, the app is able to provide offline data at local database.

 ![](https://github.com/kemalatli/PayDayBank/blob/main/art/diagram.png)
 
 **View** is responsbile for showing the state living in the view model and dispatching ui events to view model. Having only 2 roles provide cleaner view layers and enables this layer to comply with SRP (Single Responsibility Principle). 
 
 **ViewModel** is a powerful layer for surviving configuration changes and keeping track of UI state as well as getting and coordinating data between repository and view layers. It holds the view state in a live data to be observed from the view classes such as fragment or activity. On the hand, having it's own coroutines scope enables getting data from repository layer without being affected from view recreation.
 
 **Repository** has a key role in having data integrity and orchestration across local and remote data sources. The repository first provides the data from local db and then it requests fresh data from remote data source. Once the fresh data comes from remote data source, repository is responsible to update the local db with local data source. Since we are using observer pattern throughout the application with kotlin coroutine flows and live data, updated data will be sent to all observers. This enables us having the local database as the single source of truth.
