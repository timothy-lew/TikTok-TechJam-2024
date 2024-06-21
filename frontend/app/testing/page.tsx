"use client"


export const Testing = () => {
  
    const callAPI = async () => {
        try {
            // const response = await fetch('http://localhost:8080/api/users/signup', {
            const response = await fetch('http://localhost:8080/api/login', {
        
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
                // 'Authorization': 'Bearer ' + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Njc1MzBmMmQ2MmFmMTZmNThjNjM3ODkiLCJpYXQiOjE3MTg5NzYyMTMsImV4cCI6MTcxOTA2MjYxM30.jMmofrfk1_JUng535BvGsZzHdMlM0yXqhUeRdAzTOIM"
              },
              // body: JSON.stringify(userSignUpDetails),
              body: JSON.stringify({
                "username": "alex",
                "password": "password",
                "email": "alex@gmail.com",
                "firstName": "alex",
                "lastName": "lee",
                "roles": ["ROLE_BUYER", "ROLE_SELLER"],
                "shippingAddress": "Block 123",
                "billingAddress": "Block 123",
                "defaultPaymentMethod": "Card",
                "businessName": "Shop 123",
                "businessDescription": "Tools 123"
            }),
        
            });
        
            const data = await response.json();

            console.log(data);
        }
        catch(error){
            console.log(error);
        }

    }
  
  
    return (
    <div>

        <button
            onClick={callAPI}
            className="bg-green-400 px-2 py-1"
        >
            Test
        </button>


    </div>
  )
}

export default Testing
