How to run :

Initialize app with data :
1. Create sample items in the menu using http://localhost:8080/menu endpoint.
2. Hit http://localhost:8080/order endpoint with order to get order total.
Detailed description of endpoints is as below :

Endpoints :

1. Post Items in Menu :
URL : http://localhost:8080/menu
Type : Post
Body :
 name (string)
 price (double)

Sample :
{
	"name": "veggie",
	"price": "25"
}


2. Get All items from Menu :
URL : http://localhost:8080/menu
Type : Get

Sample Resopnse :
[
    {
        "name": "veggie",
        "price": 3.0
    },
    {
        "name": "cheese",
        "price": 3.5
    }
]

3. Post Order:
URL : http://localhost:8080/order
Type : Post
Body :
Array of items:
 name (string)
 quantity (int)

Sample :
[
    {
        "plate": "veggie",
        "quantity": 4
    }
]

