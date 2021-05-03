# Hotel-Management-System
Hotel Management System is an Application built in Spring boot and provides features to manage a hotel

## Rest apis
1. AddHotel

Rest endpoint: http://serverip:serverport/hotel/addHotel
### Sample Input json
   {
            "averageRating": 8.0,
            "name": "Marriot",
            "city": "Mumbai",
            "availableDate": "2021-05-03",
            "noofroomsavailable": 3,
            "costOfStay": 1400.00,
             "facility":{
               "wifiAvailable":"Yes"
             }
        }
### Sample Output json

{
    "status": "success"
}

2. Delete Hotel

Http Method

Rest endpoint: http://serverip:serverport/api/hotel/deleteHotel/{hotelid}
### Sample output json
{
    "status": "success"
}
 

3. Update Hotel

Rest endpoint: http://serverip:serverport/api/hotel/updateHotel/{hotelid}
 
### Sample input json
   {
         
            "averageRating": 8.0,
            "name": "Marriot",
            "city": "Mumbai1",
            "availableDate": null,
            "noofroomsavailable": 3,
            "costOfStay": 1400.00,
             "facility":{
                 "id":2,
               "wifiAvailable":"Yes"
             }
        }
 
### Sample output json
{
    "status": "success"
}
 
 
4. Add User
Rest endpoint: http://serverip:serverport/api/user/addUser
### Sample input json
 
{
    "id":1,
    "firstName":"Monica",
    "gender":"Female"
}
 
### Sample output json
{
    "status": "success"
}
 

 
5. Update User

Rest endpoint: http://serverip:serverport/api/user/updateUser/{userid}
### Sample input json
{
 
    "firstName":"Mamta",
    "lastName":"Chhabria",
    "gender":"Female",
    "city":"Mumbai"
}
 
### Sample output json
{
    "status": "success"
}

 
6. Delete User

Rest endpoint:  http://serverip:serverport/api/user/deleteUser/{userid}
### Sample output json
{
    "status": "success"
}
 
 
 

7. Add Review

Rest endpoint:  http://serverip:serverport/api/review/addReview
### Sample input json
{
   "review":{ "id":2,
    "rating":9.5,
    "usercomments":"Best Service"
   },
    "hotelid":12,
    "userid":1
 
}
 
### Sample output json
{
    "status": "success"
}

8.Delete Review

Rest endpoint:  http://serverip:serverport/api/review/deleteReview/{reviewid}
### Sample output json
{
    "status": "success"
}

9. Show list of rating of the hotel

Rest endpoint:  http://serverip:serverport/api/hotel/getHotel/{hotelid}
### Sample output json
{
    "hoteldetails": {
        "averageRating": 9.0,
        "review": [
            {
                "id": 4,
                "rating": 9.5,
                "usercomments": "Best Service"
            }
        ]
    },
    "status": "success"
}
 
 

 
 
10. Show list of rating of the hotel based on user attributes

Rest endpoint: http://serverip:serverport/api/hotel/getHotel/12/{attributes}
For eg: gender:Female,firstName:Mamta
For e.g :http://serverip:serverport/api/hotel/getHotel/12/gender:Female,firstName:Mamta
### Sample output json
{
    "hoteldetails": {
        "averageRating": 9.0,
        "review": [
            {
                "id": 4,
                "rating": 9.5,
                "usercomments": "Best Service"
            }
        ]
    },
    "status": "success"
}
 
 
The parameters should be comma separated

11.Search hotel based on different parameters
 
Rest endpoint: http://serverip:serverport/api/hotel/findHotelByFilter?search={attributes}
### Sample input url

http://localhost:8089/api/hotel/findHotelByFilter?search=city:Mumbai

### Sample output json

{
    "hotels": [
        {
            "id": 15,
            "averageRating": 9.0,
            "name": "Monica",
            "city": "Mumbai",
            "availableDate": "2021-05-02T13:44:40.000+00:00",
            "noofroomsavailable": 4,
            "facility": {
                "id": 14,
                "wifiAvailable": "No",
                "restaurantAvailable": "Yes",
                "acAvailable": "Yes",
                "mealsIncluded": "Yes"
            },
            "costOfStay": 1000.00
        },
        {
            "id": 14,
            "averageRating": 9.0,
            "name": "Monica",
            "city": "Mumbai",
            "availableDate": "2021-05-02T13:41:26.000+00:00",
            "noofroomsavailable": 4,
            "facility": {
                "id": 13,
                "wifiAvailable": "No",
                "restaurantAvailable": "Yes",
                "acAvailable": "Yes",
                "mealsIncluded": "Yes"
            },
            "costOfStay": 1000.00
        },
        {
            "id": 12,
            "averageRating": 9.0,
            "name": "Monica",
            "city": "Mumbai",
            "availableDate": "2021-05-02T13:36:56.000+00:00",
            "noofroomsavailable": 4,
            "facility": null,
            "costOfStay": 1000.00
        },
        {
            "id": 21,
            "averageRating": 8.0,
            "name": "Marriot",
            "city": "Mumbai",
            "availableDate": null,
            "noofroomsavailable": 3,
            "facility": {
                "id": 3,
                "wifiAvailable": "Yes",
                "restaurantAvailable": null,
                "acAvailable": null,
                "mealsIncluded": null
            },
            "costOfStay": 1400.00
        },
        {
            "id": 17,
            "averageRating": 8.0,
            "name": "Marriot",
            "city": "Mumbai",
            "availableDate": "2021-05-03T00:00:00.000+00:00",
            "noofroomsavailable": 3,
            "facility": {
                "id": 16,
                "wifiAvailable": "Yes",
                "restaurantAvailable": null,
                "acAvailable": null,
                "mealsIncluded": null
            },
            "costOfStay": 1400.00
        }
    ],
    "status": "success"
}
 

