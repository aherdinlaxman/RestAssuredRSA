Feature: adding new place feature
@AddPlace
   Scenario Outline: validate add place is working with api call
    Given add place payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with "post" http request
    Then Api call got success with status code 200
    And validate the response "status" as "OK"
    And  validate the placeId created maps to "<name>" using "getPlaceAPI" and "get" method
   Examples:

     |name             |language |address                          |
     |Dinesh house     |Marathi  | Grant road, ville parole, Mumbai|
     |Saurabh house    |Hindi    | Chan road, Pune, Maharashtra    |
     |deepali house    |English  | Oregon road, Navi Mumbai        |

@DeletePlace
  Scenario: validate delete place is working with api call
    Given delete place payload
    When User calls "deletePlaceAPI" with "delete" http request
    Then Api call got success with status code 200