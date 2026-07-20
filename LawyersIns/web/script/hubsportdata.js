function updateHubSportData(name, cname, email) {
    // Create the new request
    var xhr = new XMLHttpRequest();
    var url = 'https://api.hsforms.com/submissions/v3/integration/submit/3334505/572fd16f-23b1-4969-9327-2f9ae6fd1aaa';

    // Example request JSON:
    var data = {
        //"submittedAt": "1517927174000", // This millisecond timestamp is optional. Update the value from "1517927174000" to avoid an INVALID_TIMESTAMP error.
        "fields": [{
                "name": "email",
                "value": email
            }, {
                "name": "company",
                "value": cname
            }, {
                "name": "firstname",
                "value": name
            }
        ],

        "legalConsentOptions": {
            "consent": { // Include this object when GDPR options are enabled
                "consentToProcess": true,
                "text": "I agree to allow Example Company to store and process my personal data.",
                "communications": [{
                        "value": true,
                        "subscriptionTypeId": 999,
                        "text": "I agree to receive marketing communications from Example Company."
                    }
                ]
            }
        }
    }

    var final_data = JSON.stringify(data)

        xhr.open('POST', url, false);
    // Sets the value of the 'Content-Type' HTTP request headers to 'application/json'
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            //alert("su" + xhr.responseText); // Returns a 200 response if the submission is successful.
//        } else if (xhr.readyState == 4 && xhr.status == 400) {
//            alert(xhr.responseText); // Returns a 400 error the submission is rejected.
//        } else if (xhr.readyState == 4 && xhr.status == 403) {
//            alert(xhr.responseText); // Returns a 403 error if the portal isn't allowed to post submissions.
//        } else if (xhr.readyState == 4 && xhr.status == 404) {
//            alert(xhr.responseText); //Returns a 404 error if the formGuid isn't found
        }
    }

    // Sends the request

    xhr.send(final_data)
}
