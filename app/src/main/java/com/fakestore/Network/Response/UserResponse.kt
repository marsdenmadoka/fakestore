package com.fakestore.Network.Response


/** will use responses when we want talk to the api from our repository direct since we not
saving this data to out local db for now and  we are not using entity and dao  for now
will use entity over response class when we need to save in our local db
 */

//will add the rest of the fields later
data class UserResponse(
    val id: Int,
    val email: String,
    val username: String,
    val password: String
)


//{
//    id:21,
//    email:'John@gmail.com',
//    username:'johnd',
//    password:'m38rmF$',
//    name:{
//        firstname:'John',
//        lastname:'Doe'
//},
//    address:{
//    city:'kilcoole',
//    street:'7835 new road',
//    number:3,
//    zipcode:'12926-3874',
//    geolocation:{
//        lat:'-37.3159',
//        long:'81.1496'
//}
//},
//    phone:'1-570-236-7033'
//}